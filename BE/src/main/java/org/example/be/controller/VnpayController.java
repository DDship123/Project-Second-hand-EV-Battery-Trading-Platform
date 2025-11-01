package org.example.be.controller;

import jakarta.validation.Valid;
import org.example.be.config.VnpayProperties;
import org.example.be.dto.request.CreatePaymentRequest;
import org.example.be.dto.request.QueryRequest;
import org.example.be.dto.request.RefundRequest;
import org.example.be.dto.response.ApiResponse;
import org.example.be.dto.response.PaymentUrlResponse;
import org.example.be.dto.response.ReturnUrlResponse;
import org.example.be.service.VnpayService;
import org.example.be.util.VnpayUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("/api/v1/payments/vnpay")
@Validated
public class VnpayController {

    private final VnpayService service;
    private final VnpayProperties props;

    public VnpayController(VnpayService service, VnpayProperties props) {
        this.service = service;
        this.props = props;
    }

    // 1) Tạo URL thanh toán
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<PaymentUrlResponse>> create(@Valid @RequestBody CreatePaymentRequest req,
                                                     HttpServletRequest http) {
        String clientIp = VnpayUtil.clientIp(http.getHeader("X-FORWARDED-FOR"), http.getRemoteAddr());
        String url = service.buildPaymentUrl(req.getAmount(), req.getBankCode(), req.getLanguage(), clientIp);
        ApiResponse<PaymentUrlResponse> apiResponse = new ApiResponse<>();
        apiResponse.ok(new PaymentUrlResponse("00", "success", url));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/return")
    public RedirectView returnUrl(HttpServletRequest request) {
        Map<String, String> params = extractParams(request);

        // THÊM LOG GIÚP DEBUG VNPAY
        System.out.println("VNPAY Return Params: " + params);
        System.out.println("SecretKey: " + props.getSecretKey());

        String vnp_SecureHash = params.get("vnp_SecureHash");
        boolean valid = VnpayUtil.verify(params, vnp_SecureHash, props.getSecretKey());
        String txnStatus = params.getOrDefault("vnp_TransactionStatus", "");
        String responseCode = params.getOrDefault("vnp_ResponseCode", "");

        String status;
        if (valid) status = "00".equals(txnStatus) ? "SUCCESS" : "FAILED";
        else status = "INVALID_SIGNATURE";

//        ReturnUrlResponse body = new ReturnUrlResponse(valid, txnStatus, responseCode, status);
//        String url = "http://localhost:8888/home/order?status=ACCEPTED&transactionStatus=" + status;
        String url = "http://localhost:8888/home/vnpay-redirect?transactionStatus=" + status;
        return new RedirectView(url);
    }

    // 3) IPN (server-to-server) – VNPAY sẽ gọi vào đây với GET hoặc POST
    @RequestMapping(value = "/ipn", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<Map<String, String>> ipn(HttpServletRequest request) {
        Map<String, String> params = extractParams(request);
        String vnp_SecureHash = params.get("vnp_SecureHash");
        boolean valid = VnpayUtil.verify(params, vnp_SecureHash, props.getSecretKey());

        if (!valid) {
            // Trả đúng định dạng key theo tài liệu VNPAY (RspCode/Message)
            return ResponseEntity.ok(Map.of("RspCode", "97", "Message", "Invalid Checksum"));
        }

        // TODO: kiểm tra order tồn tại theo vnp_TxnRef
        boolean checkOrderId = true;

        // TODO: kiểm tra số tiền khớp: vnp_Amount/100 == amount trong DB
        boolean checkAmount = true;

        // TODO: kiểm tra trạng thái đơn chưa xác nhận trước đó
        boolean checkOrderStatus = true;

        if (!checkOrderId)    return ResponseEntity.ok(Map.of("RspCode", "01", "Message", "Order not Found"));
        if (!checkAmount)     return ResponseEntity.ok(Map.of("RspCode", "04", "Message", "Invalid Amount"));
        if (!checkOrderStatus) return ResponseEntity.ok(Map.of("RspCode", "02", "Message", "Order already confirmed"));

        // TODO: cập nhật DB theo vnp_ResponseCode/vnp_TransactionStatus
        // "00" = thanh toán thành công

        return ResponseEntity.ok(Map.of("RspCode", "00", "Message", "Confirm Success"));
    }

    // 4) QueryDR
    @PostMapping("/query")
    public ResponseEntity<String> query(@Valid @RequestBody QueryRequest req, HttpServletRequest http) {
        String clientIp = VnpayUtil.clientIp(http.getHeader("X-FORWARDED-FOR"), http.getRemoteAddr());
        return service.callQuery(req, clientIp);
    }

    // 5) Refund
    @PostMapping("/refund")
    public ResponseEntity<String> refund(@Valid @RequestBody RefundRequest req, HttpServletRequest http) {
        String clientIp = VnpayUtil.clientIp(http.getHeader("X-FORWARDED-FOR"), http.getRemoteAddr());
        return service.callRefund(req, clientIp);
    }

    private static Map<String, String> extractParams(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String name = e.nextElement();
            String val = request.getParameter(name);
            if (val != null) val = URLDecoder.decode(val, StandardCharsets.UTF_8);
            map.put(name, val);
        }
        return map;
    }
}