package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.entity.MemberResponse;
import org.example.fe.entity.ApiResponse;
import org.example.fe.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class MemberPageController {
    @Autowired
    private MemberService memberService;

    @RequestMapping({"/personalInformation",""})
    public String personalInformation(Model model, HttpSession session) {
        MemberResponse user = (MemberResponse) session.getAttribute("user");
        model.addAttribute("user", user);
        return "personalInformationPage";
    }

    @PostMapping("/personalInformation")
    public String updatePersonalInformation(Model model, HttpSession session, @ModelAttribute MemberResponse updatedUser) {
        ApiResponse<MemberResponse> response = memberService.updateMember(updatedUser);
        if (response.getStatus().equals("SUCCESS")) {
            model.addAttribute("successMessage", "Profile updated successfully");
        } else {
            model.addAttribute("errorMessage", "Failed to update profile");
        }
        model.addAttribute("user", response.getPayload());
        return "personalInformationPage";
    }

    @RequestMapping("/security")
    public String security() {
        return "securityPage";
    }

    @RequestMapping("/transactionHistory")
    public String transactionHistory() {
        return "memberTransactionHistoryPage";
    }
}
