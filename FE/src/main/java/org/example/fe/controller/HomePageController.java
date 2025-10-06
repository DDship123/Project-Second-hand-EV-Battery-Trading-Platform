package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.entity.MemberResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {
    @RequestMapping({ "/home"})
    public String homePage(Model model, HttpSession session) {
        MemberResponse memberResponse = (MemberResponse) session.getAttribute("user");
        if (memberResponse == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", memberResponse);
        model.addAttribute("guest", "false");

        return "homePage";
    }

    @RequestMapping("")
    public String guestHomePage(Model model) {
        model.addAttribute("guest", "true");
        return "homePage";
    }
}
