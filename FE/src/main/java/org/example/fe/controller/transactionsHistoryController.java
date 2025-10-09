package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.entity.ApiResponse;
import org.example.fe.entity.MemberResponse;
import org.example.fe.entity.TransactionResponse;
import org.example.fe.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home/transactionsHistory")
public class transactionsHistoryController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public String getTransactionsHistory(Model model, HttpSession session) {
        MemberResponse memberResponse = (MemberResponse) session.getAttribute("user");
        model.addAttribute("user", memberResponse);
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        ApiResponse<List<TransactionResponse>> apiResponse = transactionService.getAllBuyTransaction(memberResponse.getMemberId());
        if (apiResponse.getPayload() == null || apiResponse.getPayload().isEmpty()) {
            model.addAttribute("noTransactions", true);
        } else {
            model.addAttribute("noTransactions", false);
            model.addAttribute("transactions", apiResponse.getPayload());
        }
        return "transactionsHistoryPage";
    }
    @GetMapping("/seller")
    public String getSellTransactionsHistory(Model model, HttpSession session) {
        MemberResponse memberResponse = (MemberResponse) session.getAttribute("user");
        model.addAttribute("user", memberResponse);
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        ApiResponse<List<TransactionResponse>> apiResponse = transactionService.getAllSellTransaction(memberResponse.getMemberId());
        if (apiResponse.getPayload() == null || apiResponse.getPayload().isEmpty()) {
            model.addAttribute("noTransactions", true);
        } else {
            model.addAttribute("noTransactions", false);
            model.addAttribute("transactions", apiResponse.getPayload());
        }
        return "transactionsHistoryPage";
    }
}
