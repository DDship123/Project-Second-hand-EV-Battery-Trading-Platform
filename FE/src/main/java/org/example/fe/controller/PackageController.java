package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.response.ApiResponse;
import org.example.fe.response.MemberPlanUsageResponse;
import org.example.fe.response.MemberResponse;
import org.example.fe.response.MembershipPlanResponse;
import org.example.fe.service.MemberPlanUsageService;
import org.example.fe.service.MembershipPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/home/packages")
public class PackageController {
    @Autowired
    private MembershipPlanService membershipPlanService;
    @Autowired
    private MemberPlanUsageService memberPlanUsageService;

    @GetMapping
    public String viewPackagesPage(Model model, HttpSession session,
                                   @RequestParam(name = "successMessage", required = false) String successMessage,
                                   @RequestParam(name = "errorMessage", required = false) String errorMessage) {
        MemberResponse memberResponse = (MemberResponse) session.getAttribute("user");
        if (memberResponse == null) {
            return "redirect:/login";
        }
        ApiResponse<List<MembershipPlanResponse>> apiResponse = membershipPlanService.getAllMembershipPlans();
        if (apiResponse.getError() != null) {
            model.addAttribute("error", apiResponse.getError().get("message"));
        }
       ApiResponse<MemberPlanUsageResponse> memberPlanUsageResponse = memberPlanUsageService.getMemberPlanUsageByMemberId(memberResponse.getMemberId());
        if (memberPlanUsageResponse.getError() != null) {
            model.addAttribute("error", memberPlanUsageResponse.getError().get("message"));
        }

        model.addAttribute("memberPlanUsage", memberPlanUsageResponse.getPayload());
        model.addAttribute("packages", apiResponse.getPayload());
        model.addAttribute("user", memberResponse);
        model.addAttribute("guest", "false");
        if (successMessage != null) {
            model.addAttribute("successMessage", successMessage);
        }
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }
        return "packagePage";
    }
    @GetMapping("/register/{planId}")
    public String registerPackagePage(Model model, HttpSession session, RedirectAttributes redirectAttributes,
                                      @PathVariable(name = "planId")Integer planId) {
        MemberResponse memberResponse = (MemberResponse) session.getAttribute("user");
        if (memberResponse == null) {
            return "redirect:/login";
        }
        ApiResponse<MemberPlanUsageResponse> apiResponse = memberPlanUsageService.registerPackage(memberResponse.getMemberId(), planId);
        if (apiResponse.getError() != null) {
            redirectAttributes.addFlashAttribute("errorMessage", apiResponse.getError().get("message"));
        }
        redirectAttributes.addAttribute("successMessage", "Đăng ký gói thành viên thành công qua VNPAY.");
        return "redirect:/home/packages";
    }
}
