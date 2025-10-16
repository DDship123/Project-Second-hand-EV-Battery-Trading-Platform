package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.entity.MemberResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home/postForm")
public class postFormController {
    @GetMapping
    public String postForm(Model model, HttpSession session) {
        MemberResponse user = (MemberResponse) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        return "postFormPage";
    }
}
