package org.example.fe.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.fe.response.ApiResponse;
import org.example.fe.response.MemberResponse;
import org.example.fe.response.TransactionResponse;
import org.example.fe.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/home/transaction/{postId}")
    public String createTransaction(RedirectAttributes redirectAttributes, Model model, HttpSession session,
                                    @PathVariable("postId") Integer postId,
                                    HttpServletRequest request) {
        MemberResponse user = (MemberResponse) session.getAttribute("user");
        ApiResponse<TransactionResponse> apiResponse = transactionService.createTransaction(user.getMemberId(), postId);
        if (apiResponse.getStatus().equals("SUCCESS")) {
            redirectAttributes.addAttribute("successMessage", "Gửi yêu cầu mua hàng thành công!");
        } else {
            redirectAttributes.addAttribute("errorMessage", "Gửi yêu cầu mua hàng Thất bại!");
        }
        // Lấy URL trước đó
        String previousUrl = request.getHeader("referer");

        // Kiểm tra null và validate URL
        if (previousUrl != null && isValidRedirectUrl(previousUrl, request)) {
            return "redirect:" + previousUrl;
        }

        // Fallback: redirect về trang chủ nếu không có referer hợp lệ
        return "redirect:/home";
    }

    @GetMapping("/home/transaction/update-status/{transactionId}/{status}")
    public String updateSellTransactionStatus(RedirectAttributes redirectAttributes, Model model,
                                          @PathVariable("transactionId") Integer transactionId,
                                          @PathVariable("status") String status,
                                          HttpServletRequest request) {
        ApiResponse<TransactionResponse> apiResponse = transactionService.updateTransactionStatus(transactionId, status);
        if (apiResponse.getStatus().equals("SUCCESS")) {
            redirectAttributes.addAttribute("successMessage", "Cập nhật trạng thái giao dịch thành công!");
        } else {
            redirectAttributes.addAttribute("errorMessage", "Cập nhật trạng thái giao dịch thất bại!");
        }
        return "redirect:/home/order/sell";
    }
    @GetMapping("/home/buyTransaction/update-status/{transactionId}/{status}")
    public String updateBuyTransactionStatus(RedirectAttributes redirectAttributes, Model model,
                                              @PathVariable("transactionId") Integer transactionId,
                                              @PathVariable("status") String status,
                                              HttpServletRequest request) {
        ApiResponse<TransactionResponse> apiResponse = transactionService.updateTransactionStatus(transactionId, status);
        if (apiResponse.getStatus().equals("SUCCESS")) {
            redirectAttributes.addAttribute("successMessage", "Cập nhật trạng thái giao dịch thành công!");
        } else {
            redirectAttributes.addAttribute("errorMessage", "Cập nhật trạng thái giao dịch thất bại!");
        }
        return "redirect:/home/order";
    }


    // Method validate URL để đảm bảo an toàn
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
