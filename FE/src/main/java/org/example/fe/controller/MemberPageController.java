package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.config.CloudinaryService;
import org.example.fe.response.ApiResponse;
import org.example.fe.response.MemberResponse;
import org.example.fe.response.TransactionResponse;
import org.example.fe.service.MemberService;
import org.example.fe.service.TransactionService;
import org.example.fe.validate.UpdatePersonalInformationValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class MemberPageController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private TransactionService transactionService;

    @GetMapping({"/personalInformation",""})
    public String personalInformation(Model model, HttpSession session) {
        MemberResponse user = (MemberResponse) session.getAttribute("user");
        if(user == null){
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        return "personalInformationPage";
    }

    @PostMapping("/personalInformation")
    public String updatePersonalInformation(Model model, HttpSession session, @ModelAttribute MemberResponse updatedUser) {
        MemberResponse currentUser = (MemberResponse) session.getAttribute("user");

        if (currentUser == null) {
            return "redirect:/login";
        }



        ApiResponse<MemberResponse> response = memberService.updateMember(updatedUser);

        if (response.getStatus().equals("SUCCESS")) {
            model.addAttribute("successMessage", "Hồ sơ đã được cập nhật thành công");
            session.setAttribute("user", response.getPayload());
        } else {
            Map<String, String> errorMap = response.getError();

            //validate user update information
            UpdatePersonalInformationValidate validate = new UpdatePersonalInformationValidate();
            validate.error(model, errorMap, updatedUser, session);

                model.addAttribute("errorMessage", "Ho sơ cập nhật thất bại. Vui lòng kiểm tra lại thông tin.");
                model.addAttribute("user", currentUser);
                return "personalInformationPage";
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

    @GetMapping("/security")
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
        UpdatePersonalInformationValidate validate = new UpdatePersonalInformationValidate();
        boolean hasError = validate.errrorPassword(model,currentPassword,newPassword,confirmPassword,session);



        if(hasError){
            return "securityPage";
        }
        user.setPassword(newPassword);
        ApiResponse<MemberResponse> response = memberService.updateMember(user);

        model.addAttribute("user", user);
        if (response.getStatus().equals("SUCCESS")) {
            model.addAttribute("successMessage", "Cập nhật mật khẩu thành công");
        } else {
            model.addAttribute("errorMessage", "Cập nhật mật khẩu thất bại");
            model.addAttribute("user", user);
            return "securityPage";
        }
        model.addAttribute("user", response.getPayload());
        return "securityPage";
    }

    @GetMapping("/transactionHistory")
    public String transactionHistoryBuyer(Model model, HttpSession session) {
        MemberResponse user = (MemberResponse) session.getAttribute("user");
        model.addAttribute("user", user);
        ApiResponse<List<TransactionResponse>> apiResponse = transactionService.getAllBuyTransaction(user.getMemberId());
        if (apiResponse.getPayload() == null || apiResponse.getPayload().isEmpty()) {
            model.addAttribute("noTransactions", true);
        } else {
            model.addAttribute("noTransactions", false);
            model.addAttribute("transactions", apiResponse.getPayload());
        }
        return "memberTransactionHistoryPage";
    }
    @GetMapping("/transactionHistory/seller")
    public String transactionHistorySeller(Model model, HttpSession session) {
        MemberResponse user = (MemberResponse) session.getAttribute("user");
        model.addAttribute("user", user);
        ApiResponse<List<TransactionResponse>> apiResponse = transactionService.getAllSellTransaction(user.getMemberId());
        if (apiResponse.getPayload() == null || apiResponse.getPayload().isEmpty()) {
            model.addAttribute("noTransactions", true);
        } else {
            model.addAttribute("noTransactions", false);
            model.addAttribute("transactions", apiResponse.getPayload());
        }
        return "memberTransactionHistoryPage";
    }
}
