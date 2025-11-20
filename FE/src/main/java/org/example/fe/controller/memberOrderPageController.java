package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.response.*;
import org.example.fe.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/home/order")
public class memberOrderPageController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommissionService commissionService;
    @Autowired
    private ContractService contractService;

    @GetMapping
    public String getTransactionPage(Model model, HttpSession session,
                                     @RequestParam(name = "transactionStatus",required = false) String transactionStatus,
                                     @RequestParam(name = "status", required = false, defaultValue = "REQUESTED") String status,
                                     @RequestParam(name = "successMessage", required = false) String successMessage,
                                     @RequestParam(name = "errorMessage", required = false) String errorMessage) {
        MemberResponse memberResponse = (MemberResponse) session.getAttribute("user");
        if (memberResponse == null) {
            return "redirect:/login?unauthorized=true";
        }
        model.addAttribute("user", memberResponse);
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        if (transactionStatus != null && transactionStatus.equals("SUCCESS")) {
            int transactionId = (int) session.getAttribute("id");
            transactionService.updateTransactionStatus(transactionId, "PAID");
            session.removeAttribute("id");
            session.removeAttribute("transactionType");
        } else if (transactionStatus != null && transactionStatus.equals("FAILED")) {
            // Xử lý khi thanh toán thất bại nếu cần
            session.removeAttribute("id");
            session.removeAttribute("transactionType");
        }
        ApiResponse<List<TransactionResponse>> apiResponse = transactionService.getAllBuyTransaction(memberResponse.getMemberId(),status);
        if (apiResponse.getPayload() == null || apiResponse.getPayload().isEmpty()) {
            model.addAttribute("noTransactions", true);
        } else {
            model.addAttribute("noTransactions", false);
            model.addAttribute("transactions", apiResponse.getPayload());
            model.addAttribute("seller", false);
        }
        if (successMessage != null) {
            model.addAttribute("successMessage", successMessage);
        }
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }
        return "memberOrder";
    }

    @GetMapping("/detail/{transactionId}")
    public String getBuyTransactionDetail(Model model, HttpSession session, @PathVariable(name = "transactionId") Integer transactionId) {
        MemberResponse memberResponse = (MemberResponse) session.getAttribute("user");
        if (memberResponse == null)
        {
            return "redirect:/login?unauthorized=true";
        }
        model.addAttribute("user", memberResponse);
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        ApiResponse<TransactionResponse> apiResponse = transactionService.getTransactionById(transactionId);
        ApiResponse<PostResponse> postApiResponse = postService.getPostDetail(apiResponse.getPayload().getPost().getPostsId());
        if (memberResponse.getMemberId() != apiResponse.getPayload().getBuyer().getMemberId() && !memberResponse.getRole().equals("ADMIN")) {
            return "redirect:/login?unauthorized=true";
        }
        if (apiResponse.getPayload() == null) {
            return "redirect:/home/order";
        } else {
            model.addAttribute("post", postApiResponse.getPayload());
            model.addAttribute("transaction", apiResponse.getPayload());
        }
        return "orderDetailBuyer";
    }

    @GetMapping("/sell")
    public String getSellTransactionPage(Model model, HttpSession session, @RequestParam(name = "status", required = false
            , defaultValue = "REQUESTED") String status, @RequestParam(name = "successMessage", required = false) String successMessage,
                                         @RequestParam(name = "errorMessage", required = false) String errorMessage) {
        MemberResponse memberResponse = (MemberResponse) session.getAttribute("user");
        if (memberResponse == null)
        {
            return "redirect:/login?unauthorized=true";
        }
        model.addAttribute("user", memberResponse);
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        ApiResponse<List<TransactionResponse>> apiResponse = transactionService.getAllSellTransaction(memberResponse.getMemberId(), status);
        if (apiResponse.getPayload() == null || apiResponse.getPayload().isEmpty()) {
            model.addAttribute("noTransactions", true);
        } else {
            model.addAttribute("noTransactions", false);
            model.addAttribute("seller", true);
            model.addAttribute("transactions", apiResponse.getPayload());
        }
        if (successMessage != null) {
            model.addAttribute("successMessage", successMessage);
        }
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }
        return "memberOrder";
    }

    @GetMapping("/sell/detail/{transactionId}")
    public String getSellTransactionDetail(Model model, HttpSession session,
                                           @PathVariable(name = "transactionId") Integer transactionId) {
        MemberResponse memberResponse = (MemberResponse) session.getAttribute("user");
        if (memberResponse == null)
        {
            return "redirect:/login?unauthorized=true";
        }
        model.addAttribute("user", memberResponse);
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        ApiResponse<TransactionResponse> apiResponse = transactionService.getTransactionById(transactionId);
        if (apiResponse.getPayload() == null) {
            return "redirect:/home/order";
        } else {
            ApiResponse<PostResponse> postApiResponse = postService.getPostDetail(apiResponse.getPayload().getPost().getPostsId());
            ApiResponse<CommissionResponse> commissionApiResponse = commissionService.getCommissionByTransactionId(transactionId);
            if (memberResponse.getMemberId() != postApiResponse.getPayload().getSeller().getMemberId() && !memberResponse.getRole().equals("ADMIN")) {
                return "redirect:/login?unauthorized=true";
            }
            if (apiResponse.getPayload().getStatus().equals("DELIVERED")){
                ApiResponse<ContractResponse> contractApiResponse = contractService.getContractByTransactionId(transactionId);
                model.addAttribute("contract", contractApiResponse.getPayload());
            }
            model.addAttribute("post", postApiResponse.getPayload());
            model.addAttribute("transaction", apiResponse.getPayload());
            model.addAttribute("commission", commissionApiResponse.getPayload());
        }
        return "orderDetailSeller";
    }
}
