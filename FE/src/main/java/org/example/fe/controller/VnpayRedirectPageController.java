package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/home/vnpay-redirect")
public class VnpayRedirectPageController {
    @Autowired
    private TransactionService transactionService;
    @GetMapping
    public String vnpayRedirectPage(RedirectAttributes redirectAttributes,
                                    HttpSession session, @RequestParam(name = "transactionStatus") String statusTransaction) {
        String transactionType = (String) session.getAttribute("transactionType");
        if (transactionType != null && transactionType.equals("order")) {
            if (statusTransaction.equals("SUCCESS")) {
                redirectAttributes.addAttribute("successMessage", "Thanh toán VNPAY thành công.");
                redirectAttributes.addAttribute("transactionStatus", "SUCCESS");
            } else if (statusTransaction.equals("FAILED")) {
                redirectAttributes.addAttribute("errorMessage", "Thanh toán VNPAY không thành công.");
                redirectAttributes.addAttribute("transactionStatus", "FAILED");
            }
            return "redirect:/home/order?status=ACCEPTED";
        } else if (transactionType != null && transactionType.equals("package")) {
             if (statusTransaction.equals("FAILED")) {
                redirectAttributes.addAttribute("errorMessage", "Đăng ký gói thành viên không thành công qua VNPAY.");
                return "redirect:/home/packages";
            }
            int newPlanId = (int) session.getAttribute("id");
            return "redirect:/home/packages/register/"+newPlanId;
        } else {
            return "redirect:/home";
        }
    }
}
