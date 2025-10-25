package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.entity.MemberResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home/order")
public class memberOrderPageController {
    @GetMapping
    public String getTransactionPage(Model model, HttpSession session) {
        MemberResponse memberResponse = (MemberResponse) session.getAttribute("user");
        model.addAttribute("user", memberResponse);
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        return "memberOrder";
    }
}
