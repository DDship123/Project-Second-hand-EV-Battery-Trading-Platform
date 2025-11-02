package org.example.be.controller;

import org.example.be.dto.response.ApiResponse;
import org.example.be.dto.response.MemberPlanUsageResponse;
import org.example.be.dto.response.MemberResponse;
import org.example.be.dto.response.MembershipPlanResponse;
import org.example.be.entity.Member;
import org.example.be.entity.MemberPlanUsage;
import org.example.be.entity.MembershipPlan;
import org.example.be.service.MemberPlanUsageService;
import org.example.be.service.MemberService;
import org.example.be.service.MembershipPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/member-plan-usages")
public class MemberPlanUsageController {

    @Autowired
    private MemberPlanUsageService memberPlanUsageService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MembershipPlanService membershipPlanService;

    @PostMapping
    public ResponseEntity<ApiResponse<MemberPlanUsage>> createMemberPlanUsage(@RequestBody MemberPlanUsage usage) {
        MemberPlanUsage saved = memberPlanUsageService.createMemberPlanUsage(usage);
        ApiResponse<MemberPlanUsage> response = new ApiResponse<>();
        response.ok(saved);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MemberPlanUsage>> getMemberPlanUsageById(@PathVariable Integer id) {
        Optional<MemberPlanUsage> usage = memberPlanUsageService.getMemberPlanUsageById(id);
        ApiResponse<MemberPlanUsage> response = new ApiResponse<>();
        if (usage.isPresent()) {
            response.ok(usage.get());
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "MemberPlanUsage not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<ApiResponse<MemberPlanUsageResponse>> getMemberPlanUsageByMemberId(@PathVariable Integer memberId) {
        Optional<MemberPlanUsage> usage = memberPlanUsageService.getMemberPlanUsageByMemberId(memberId);
        ApiResponse<MemberPlanUsageResponse> response = new ApiResponse<>();
        if (usage.isPresent()) {
            MemberPlanUsage u = usage.get();
            MemberPlanUsageResponse usageResponse = new MemberPlanUsageResponse();
            usageResponse.setUsageId(u.getUsageId());
            usageResponse.setStartDate(u.getStartDate());
            usageResponse.setEndDate(u.getEndDate());
            usageResponse.setStatus(u.getStatus());
            MembershipPlanResponse planResponse = new MembershipPlanResponse();
            planResponse.setPlanId(u.getMembershipPlan().getPlanId());
            usageResponse.setPlan(planResponse);
            response.ok(usageResponse);
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "MemberPlanUsage not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }

    @PutMapping("/register-package")
    public ResponseEntity<ApiResponse<MemberPlanUsageResponse>> registerPackage(@RequestParam("memberId")Integer memberId,
                                                                                @RequestParam("planId") Integer planId) {
        Member member = memberService.getMemberById(memberId);
        MembershipPlan plan = membershipPlanService.getMembershipPlanById(planId).orElse(null);
        if (member == null || plan == null) {
            ApiResponse<MemberPlanUsageResponse> response = new ApiResponse<>();
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Member or MembershipPlan not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
        MemberPlanUsage memberPlanUsage = memberPlanUsageService.registerPackage(member, plan);
        ApiResponse<MemberPlanUsageResponse> response = new ApiResponse<>();
        if (memberPlanUsage != null) {
            MemberPlanUsageResponse usageResponse = new MemberPlanUsageResponse();
            usageResponse.setUsageId(memberPlanUsage.getUsageId());
            usageResponse.setStartDate(memberPlanUsage.getStartDate());
            usageResponse.setEndDate(memberPlanUsage.getEndDate());
            usageResponse.setStatus(memberPlanUsage.getStatus());
            MembershipPlanResponse planResponse = new MembershipPlanResponse();
            planResponse.setPlanId(memberPlanUsage.getMembershipPlan().getPlanId());
            usageResponse.setPlan(planResponse);
            response.ok(usageResponse);
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Registration failed");
            response.error(error);
            return ResponseEntity.status(400).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MemberPlanUsage>>> getAllMemberPlanUsages() {
        List<MemberPlanUsage> list = memberPlanUsageService.getAllMemberPlanUsages();
        ApiResponse<List<MemberPlanUsage>> response = new ApiResponse<>();
        if (list.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No MemberPlanUsages found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(list);
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MemberPlanUsage>> updateMemberPlanUsage(@PathVariable Integer id, @RequestBody MemberPlanUsage usage) {
        MemberPlanUsage updated = memberPlanUsageService.updateMemberPlanUsage(id, usage);
        ApiResponse<MemberPlanUsage> response = new ApiResponse<>();
        if (updated != null) {
            response.ok(updated);
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "MemberPlanUsage not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMemberPlanUsage(@PathVariable Integer id) {
        boolean deleted = memberPlanUsageService.deleteMemberPlanUsage(id);
        ApiResponse<Void> response = new ApiResponse<>();
        if (deleted) {
            response.ok();
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "MemberPlanUsage not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }
}
