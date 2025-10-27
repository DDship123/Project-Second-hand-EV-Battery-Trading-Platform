package org.example.fe.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.fe.request.CreatePaymentRequest;
import org.example.fe.response.ApiResponse;
import org.example.fe.response.PaymentUrlResponse;
import org.example.fe.response.ReturnUrlResponse;
import org.example.fe.service.BankService;
import org.example.fe.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/banking")
public class BankingController {
    @Autowired
    private BankService bankService;
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/create/{transactionId}/{amount}")
    public String createBanking(Model model, HttpSession session, RedirectAttributes redirectAttributes, @PathVariable("transactionId") Integer transactionId , @PathVariable("amount") Double amount, HttpServletRequest request) {
        CreatePaymentRequest req = new CreatePaymentRequest();
        req.setAmount(amount.longValue());
        req.setBankCode("");
        req.setLanguage("vn");
        ApiResponse<PaymentUrlResponse> response = bankService.createBanking(req);
        String previousUrl = request.getHeader("referer");
        if (response.getPayload() != null) {
            String paymentUrl = response.getPayload().getPaymentUrl();
            session.setAttribute("updateTransactionId", transactionId);
            return "redirect:"+paymentUrl;  // Trả về view banking.html
        }else {
            model.addAttribute("errorMessage", "Thanh toán không thành công.");
            // Lấy URL trước đó
            // Kiểm tra null và validate URL
            if (previousUrl != null && isValidRedirectUrl(previousUrl, request)) {
                return "redirect:" + previousUrl;
            }
        }
        return "redirect:/home";
    }// Method validate URL để đảm bảo an toàn
    private boolean isValidRedirectUrl(String url, HttpServletRequest request) {
        try {
            // Chỉ cho phép redirect trong cùng domain
            String serverName = request.getServerName();
            return url.contains(serverName) || url.startsWith("/");
        } catch (Exception e) {
            return false;
        }
    }

}
