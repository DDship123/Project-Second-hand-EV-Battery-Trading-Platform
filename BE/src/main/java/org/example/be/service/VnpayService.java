package org.example.be.service;

import org.example.be.config.VnpayProperties;
import org.example.be.dto.request.QueryRequest;
import org.example.be.dto.request.RefundRequest;
import org.example.be.util.VnpayUtil;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class VnpayService {

    private final VnpayProperties props;
    private final RestTemplate restTemplate = new RestTemplate();

    public VnpayService(VnpayProperties props) {
        this.props = props;
    }

    // Tạo URL thanh toán (redirect/popup)
    public String buildPaymentUrl(Long amountVnd, String bankCode, String language, String clientIp) {
        if (amountVnd == null || amountVnd <= 0) {
            throw new IllegalArgumentException("amount must be > 0");
        }

        String vnp_TxnRef = VnpayUtil.randomNumeric(8);
        long vnpAmount = amountVnd * 100; // VNPAY yêu cầu nhân 100

        Map<String, String> params = new HashMap<>();
        params.put("vnp_Version", props.getVersion());
        params.put("vnp_Command", "pay");
        params.put("vnp_TmnCode", props.getTmnCode());
        params.put("vnp_Amount", String.valueOf(vnpAmount));
        params.put("vnp_CurrCode", "VND");

        if (bankCode != null && !bankCode.isBlank()) {
            params.put("vnp_BankCode", bankCode);
        }

        params.put("vnp_TxnRef", vnp_TxnRef);
        params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        params.put("vnp_OrderType", defaultOr(props.getDefaultOrderType(), "other"));
        params.put("vnp_Locale", (language != null && !language.isBlank()) ? language : defaultOr(props.getDefaultLocale(), "vn"));
        params.put("vnp_ReturnUrl", props.getReturnUrl());
        params.put("vnp_IpAddr", clientIp);
        params.put("vnp_CreateDate", VnpayUtil.now(props.getTimezone()));
        params.put("vnp_ExpireDate", VnpayUtil.plusMinutes(props.getTimezone(), 15));

        Map<String, String> signed = VnpayUtil.sign(params, props.getSecretKey());

        // THÊM LOG GIÚP DEBUG:
        System.out.println("hashData (SIGN): " + signed.get("hashData"));
        System.out.println("secureHash (SIGN): " + signed.get("secureHash"));

        return props.getPayUrl() + "?" + signed.get("query") + "&vnp_SecureHash=" + signed.get("secureHash");
    }

    // Gọi API querydr
    public ResponseEntity<String> callQuery(QueryRequest req, String clientIp) {
        String vnp_RequestId = VnpayUtil.randomNumeric(8);
        String vnp_OrderInfo = "Kiem tra ket qua GD OrderId:" + req.getOrderId();
        String vnp_CreateDate = VnpayUtil.now(props.getTimezone());

        // hashData theo tài liệu VNPAY: join bằng '|'
        String hashData = String.join("|",
                vnp_RequestId, props.getVersion(), "querydr", props.getTmnCode(),
                req.getOrderId(), req.getTransDate(), vnp_CreateDate, clientIp, vnp_OrderInfo
        );
        String vnp_SecureHash = VnpayUtil.hmacSHA512(props.getSecretKey(), hashData);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("vnp_RequestId", vnp_RequestId);
        body.put("vnp_Version", props.getVersion());
        body.put("vnp_Command", "querydr");
        body.put("vnp_TmnCode", props.getTmnCode());
        body.put("vnp_TxnRef", req.getOrderId());
        body.put("vnp_OrderInfo", vnp_OrderInfo);
        body.put("vnp_TransactionDate", req.getTransDate());
        body.put("vnp_CreateDate", vnp_CreateDate);
        body.put("vnp_IpAddr", clientIp);
        body.put("vnp_SecureHash", vnp_SecureHash);

        return postJson(props.getApiUrl(), body);
    }

    // Gọi API refund
    public ResponseEntity<String> callRefund(RefundRequest req, String clientIp) {
        String vnp_RequestId = VnpayUtil.randomNumeric(8);
        String vnp_CreateDate = VnpayUtil.now(props.getTimezone());
        long amount = req.getAmount() * 100;
        String vnp_TransactionNo = ""; // Nếu có thì điền
        String vnp_OrderInfo = "Hoan tien GD OrderId:" + req.getOrderId();

        String hashData = String.join("|",
                vnp_RequestId, props.getVersion(), "refund", props.getTmnCode(),
                req.getTranType(), req.getOrderId(), String.valueOf(amount), vnp_TransactionNo,
                req.getTransDate(), req.getUser(), vnp_CreateDate, clientIp, vnp_OrderInfo
        );
        String vnp_SecureHash = VnpayUtil.hmacSHA512(props.getSecretKey(), hashData);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("vnp_RequestId", vnp_RequestId);
        body.put("vnp_Version", props.getVersion());
        body.put("vnp_Command", "refund");
        body.put("vnp_TmnCode", props.getTmnCode());
        body.put("vnp_TransactionType", req.getTranType());
        body.put("vnp_TxnRef", req.getOrderId());
        body.put("vnp_Amount", String.valueOf(amount));
        body.put("vnp_OrderInfo", vnp_OrderInfo);
        if (vnp_TransactionNo != null && !vnp_TransactionNo.isBlank()) {
            body.put("vnp_TransactionNo", vnp_TransactionNo);
        }
        body.put("vnp_TransactionDate", req.getTransDate());
        body.put("vnp_CreateBy", req.getUser());
        body.put("vnp_CreateDate", vnp_CreateDate);
        body.put("vnp_IpAddr", clientIp);
        body.put("vnp_SecureHash", vnp_SecureHash);

        return postJson(props.getApiUrl(), body);
    }

    private ResponseEntity<String> postJson(String url, Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);
        return restTemplate.postForEntity(url, entity, String.class);
    }

    private static String defaultOr(String v, String def) {
        return (v == null || v.isBlank()) ? def : v;
    }
}