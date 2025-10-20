package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.entity.MemberResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home/admin")
public class AdminPageController {
    @GetMapping(value = {"", "/post-manage"})
    public String dashboard(Model model, HttpSession session) {
        MemberResponse member = (MemberResponse) session.getAttribute("user");
        if (!member.getRole().equals("ADMIN")) {
            return "redirect:/home";
        }
        model.addAttribute("admin", member);
        return "postManage";
    }

    @GetMapping("/member-manage")
    public String memberManage(Model model, HttpSession session) {
        MemberResponse member = (MemberResponse) session.getAttribute("user");
        if (!member.getRole().equals("ADMIN")) {
            return "redirect:/home";
        }
        model.addAttribute("admin", member);
        return "userManage";
    }

    @GetMapping("/comment-review-manage")
    public String commentManage(Model model, HttpSession session) {
        MemberResponse member = (MemberResponse) session.getAttribute("user");
        if (!member.getRole().equals("ADMIN")) {
            return "redirect:/home";
        }
        model.addAttribute("admin", member);
        return "commentManage";
    }

    @GetMapping("/transaction-manage")
    public String transactionManage(Model model, HttpSession session) {
        MemberResponse member = (MemberResponse) session.getAttribute("user");
        if (!member.getRole().equals("ADMIN")) {
            return "redirect:/home";
        }
        model.addAttribute("admin", member);
        return "transactionManage";
    }
}
