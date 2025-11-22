package org.example.be.controller;

import org.example.be.dto.response.ApiResponse;
import org.example.be.dto.response.MembershipPlanResponse;
import org.example.be.entity.MembershipPlan;
import org.example.be.service.MembershipPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
    @RequestMapping("/api/membership-plans")
public class MembershipPlanController {

    @Autowired
    private MembershipPlanService membershipPlanService;

    @PostMapping
    public ResponseEntity<ApiResponse<MembershipPlan>> createMembershipPlan(@RequestBody MembershipPlanResponse planResponse) {
        MembershipPlan plan = new MembershipPlan();
        plan.setName(planResponse.getName());
        plan.setPrice(planResponse.getPrice());
        plan.setDuration(planResponse.getDuration());
        plan.setMaxPosts(planResponse.getMaxPosts());
        plan.setPriority(planResponse.getPriority());
        plan.setStatus(planResponse.getStatus());
        MembershipPlan saved = membershipPlanService.createMembershipPlan(plan);
        ApiResponse<MembershipPlan> response = new ApiResponse<>();
        response.ok(saved);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MembershipPlanResponse>> getMembershipPlanById(@PathVariable Integer id) {
        Optional<MembershipPlan> plan = membershipPlanService.getMembershipPlanById(id);
        ApiResponse<MembershipPlanResponse> response = new ApiResponse<>();
        if (plan.isPresent()) {
            MembershipPlan p = plan.get();
            MembershipPlanResponse planResponse = new MembershipPlanResponse(
                    p.getPlanId(),
                    p.getName(),
                    p.getPrice(),
                    p.getDuration(),
                    p.getMaxPosts(),
                    p.getPriority(),
                    p.getStatus()
            );
            response.ok(planResponse);
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "MembershipPlan not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<ApiResponse<MembershipPlanResponse>> getMembershipPlanByMemberId(@PathVariable Integer memberId) {
        Optional<MembershipPlan> plan = membershipPlanService.getMembershipPlanByMemberId(memberId);
        ApiResponse<MembershipPlanResponse> response = new ApiResponse<>();
        if (plan.isPresent()) {
            MembershipPlan p = plan.get();
            MembershipPlanResponse planResponse = new MembershipPlanResponse(
                    p.getPlanId(),
                    p.getName(),
                    p.getPrice(),
                    p.getDuration(),
                    p.getMaxPosts(),
                    p.getPriority(),
                    p.getStatus()
            );
            response.ok(planResponse);
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "MembershipPlan not found for the member");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MembershipPlanResponse>>> getAllMembershipPlans() {
        List<MembershipPlan> list = membershipPlanService.getAllMembershipPlans();
        List<MembershipPlanResponse> response = list.stream()
                .map(plan -> new MembershipPlanResponse(
                        plan.getPlanId(),
                        plan.getName(),
                        plan.getPrice(),
                        plan.getDuration(),
                        plan.getMaxPosts(),
                        plan.getPriority(),
                        plan.getStatus()
                ))
                .toList();
        ApiResponse<List<MembershipPlanResponse>> apiResponse = new ApiResponse<>();
        if (response.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No MembershipPlans found");
            apiResponse.error(error);
            return ResponseEntity.status(404).body(apiResponse);
        }
        apiResponse.ok(response);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MembershipPlanResponse>> updateMembershipPlan(@PathVariable Integer id, @RequestBody MembershipPlanResponse planResponse) {
        Optional<MembershipPlan> updated = membershipPlanService.getMembershipPlanById(id);
        ApiResponse<MembershipPlanResponse> response = new ApiResponse<>();
        if (updated.isPresent()) {
            MembershipPlan plan = updated.get();
            plan.setName(planResponse.getName());
            plan.setPrice(planResponse.getPrice());
            plan.setDuration(planResponse.getDuration());
            plan.setMaxPosts(planResponse.getMaxPosts());
            plan.setPriority(planResponse.getPriority());
            plan.setStatus(planResponse.getStatus());
            membershipPlanService.createMembershipPlan(plan); // Save the updated plan
            MembershipPlanResponse updatedResponse = new MembershipPlanResponse(
                    plan.getPlanId(),
                    plan.getName(),
                    plan.getPrice(),
                    plan.getDuration(),
                    plan.getMaxPosts(),
                    plan.getPriority(),
                    plan.getStatus()
            );
            response.ok(updatedResponse);
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "MembershipPlan not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }
    @PutMapping("/status/{id}")
    public ResponseEntity<ApiResponse<MembershipPlanResponse>> updateMembershipPlanStatus(@PathVariable Integer id, @RequestBody String status) {
        Optional<MembershipPlan> updated = membershipPlanService.getMembershipPlanById(id);
        ApiResponse<MembershipPlanResponse> response = new ApiResponse<>();
        if (updated.isPresent()) {
            MembershipPlan plan = updated.get();
            plan.setStatus(status);
            membershipPlanService.createMembershipPlan(plan); // Save the updated plan
            MembershipPlanResponse planResponse = new MembershipPlanResponse(
                    plan.getPlanId(),
                    plan.getName(),
                    plan.getPrice(),
                    plan.getDuration(),
                    plan.getMaxPosts(),
                    plan.getPriority(),
                    plan.getStatus()
            );
            response.ok(planResponse);
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "MembershipPlan not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMembershipPlan(@PathVariable Integer id) {
        boolean deleted = membershipPlanService.deleteMembershipPlan(id);
        ApiResponse<Void> response = new ApiResponse<>();
        if (deleted) {
            response.ok();
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "MembershipPlan not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }
}
