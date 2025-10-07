package org.example.fe.controller;

import com.cloudinary.Cloudinary;
import jakarta.servlet.http.HttpSession;
import org.example.fe.config.CloudinaryService;
import org.example.fe.entity.MemberResponse;
import org.example.fe.entity.ApiResponse;
import org.example.fe.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/account")
public class MemberPageController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private CloudinaryService cloudinaryService;

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
            session.setAttribute("user", response.getPayload());
        } else {
            model.addAttribute("errorMessage", "Failed to update profile");
        }
        model.addAttribute("user", response.getPayload());
        return "personalInformationPage";
    }

    @PostMapping("/personalInformation/uploadAvatar")
    public String uploadAvatar(@RequestParam(name = "avatar") MultipartFile avatarImage, HttpSession session) {
        MemberResponse user = (MemberResponse) session.getAttribute("user");
        try {
            String imageUrl = cloudinaryService.uploadImage(avatarImage);
            user.setAvatarUrl(imageUrl);
            ApiResponse<MemberResponse> response = memberService.updateMember(user);
            if (response.getStatus().equals("SUCCESS")) {
                session.setAttribute("user", response.getPayload());
                return "redirect:/account/personalInformation";
            }
            return "redirect:/account/personalInformation?error=true";
        } catch (Exception e) {
            return "redirect:/account/personalInformation?error=true";
        }
    }

    @RequestMapping("/security")
    public String security(Model model, HttpSession session) {
        MemberResponse user = (MemberResponse) session.getAttribute("user");
        model.addAttribute("user", user);
        return "securityPage";
    }

    @PostMapping("/security")
    public String updatePassword(Model model, HttpSession session,
                                 @RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword) {
        MemberResponse user = (MemberResponse) session.getAttribute("user");
        if (!user.getPassword().equals(currentPassword)) {
            model.addAttribute("errorMessage", "Current password is incorrect");
            model.addAttribute("user", user);
            return "securityPage";
        }
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("errorMessage", "New password and confirm password do not match");
            model.addAttribute("user", user);
            return "securityPage";
        }
        user.setPassword(newPassword);
        ApiResponse<MemberResponse> response = memberService.updateMember(user);
        model.addAttribute("user", user);
        if (response.getStatus().equals("SUCCESS")) {
            model.addAttribute("successMessage", "Password updated successfully");
        } else {
            model.addAttribute("errorMessage", "Failed to update password");
        }
        return "securityPage";
    }

    @RequestMapping("/transactionHistory")
    public String transactionHistory(Model model, HttpSession session) {
        MemberResponse user = (MemberResponse) session.getAttribute("user");
        model.addAttribute("user", user);
        return "memberTransactionHistoryPage";
    }
}
