package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.response.ApiResponse;
import org.example.fe.response.MemberResponse;
import org.example.fe.response.ReviewResponse;
import org.example.fe.response.TransactionResponse;
import org.example.fe.service.ReviewService;
import org.example.fe.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/home/transactionsHistory")
public class transactionsHistoryPageController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public String getTransactionsHistory(Model model, HttpSession session,
                                         @RequestParam(value = "successMessage", required = false) String successMessage,
                                         @RequestParam(value = "errorMessage", required = false) String errorMessage,
                                         @RequestParam(name = "status", required = false) String status) {
        MemberResponse memberResponse = (MemberResponse) session.getAttribute("user");
        if (memberResponse == null) {
            return "redirect:/login?unauthorized=true";
        }
        model.addAttribute("user", memberResponse);
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        if (status == null || status.isEmpty()) {
            status = "C-C-ALL";
        }
        ApiResponse<List<TransactionResponse>> apiResponse = transactionService.getAllBuyTransaction(memberResponse.getMemberId(), status);
//        ApiResponse<List<TransactionResponse>> apiResponse = transactionService.getAllBuyTransaction(memberResponse.getMemberId());
        if (apiResponse.getPayload() == null || apiResponse.getPayload().isEmpty()) {
            model.addAttribute("noTransactions", true);
        } else {
            model.addAttribute("noTransactions", false);
            model.addAttribute("transactions", apiResponse.getPayload());
        }
        if (successMessage != null) {
            model.addAttribute("successMessage", successMessage);
        }
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }
        return "transactionsHistoryPage";
    }

    @GetMapping("/seller")
    public String getSellTransactionsHistory(Model model, HttpSession session,
                                             @RequestParam(name = "status", required = false) String status) {
        MemberResponse memberResponse = (MemberResponse) session.getAttribute("user");
        if (memberResponse == null) {
            return "redirect:/login?unauthorized=true";
        }
        model.addAttribute("user", memberResponse);
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        if (status == null || status.isEmpty()) {
            status = "C-C-ALL";
        }
        ApiResponse<List<TransactionResponse>> apiResponse = transactionService.getAllSellTransaction(memberResponse.getMemberId(), status);
//        ApiResponse<List<TransactionResponse>> apiResponse = transactionService.getAllSellTransaction(memberResponse.getMemberId());
        if (apiResponse.getPayload() == null || apiResponse.getPayload().isEmpty()) {
            model.addAttribute("noTransactions", true);
        } else {
            model.addAttribute("noTransactions", false);
            model.addAttribute("transactions", apiResponse.getPayload());
        }
        return "transactionsHistoryPage";
    }

    @PostMapping("/rate")
    public String rateTransaction(HttpSession session, RedirectAttributes redirectAttributes
            , @RequestParam("transactionId") Integer transactionId,@RequestParam("sellerId") Integer sellerId
            ,@RequestParam("rating") Integer rating,@RequestParam("reviewText") String comment) {
        MemberResponse memberResponse = (MemberResponse) session.getAttribute("user");
        if (memberResponse == null) {
            return "redirect:/login";
        }
        ApiResponse<ReviewResponse> apiResponse = reviewService.create(transactionId, sellerId, memberResponse.getMemberId(), rating, comment);
        if (apiResponse.getPayload() != null) {
            redirectAttributes.addAttribute("successMessage", "Gửi đánh giá thành công.");
        } else {
            redirectAttributes.addAttribute("errorMessage", "Gửi đánh giá thất bại.");
        }
        return "redirect:/home/transactionsHistory";
    }
}
