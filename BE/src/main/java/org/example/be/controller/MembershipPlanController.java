package org.example.be.controller;

import org.example.be.dto.ApiResponse;
import org.example.be.entity.MembershipPlan;
import org.example.be.service.MembershipPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/membership-plans")
public class MembershipPlanController {

    @Autowired
    private MembershipPlanService membershipPlanService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createMembershipPlan(@RequestBody MembershipPlan plan) {
        MembershipPlan saved = membershipPlanService.createMembershipPlan(plan);
        ApiResponse<Object> response = new ApiResponse<>();
        response.ok(Map.of("membershipPlan", saved));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getMembershipPlanById(@PathVariable Integer id) {
        Optional<MembershipPlan> plan = membershipPlanService.getMembershipPlanById(id);
        ApiResponse<Object> response = new ApiResponse<>();
        if (plan.isPresent()) {
            response.ok(Map.of("membershipPlan", plan.get()));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "MembershipPlan not found"));
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllMembershipPlans() {
        List<MembershipPlan> list = membershipPlanService.getAllMembershipPlans();
        ApiResponse<Object> response = new ApiResponse<>();
        if (list.isEmpty()) {
            response.error(Map.of("message", "No MembershipPlans found"));
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(Map.of("membershipPlans", list));
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateMembershipPlan(@PathVariable Integer id, @RequestBody MembershipPlan plan) {
        MembershipPlan updated = membershipPlanService.updateMembershipPlan(id, plan);
        ApiResponse<Object> response = new ApiResponse<>();
        if (updated != null) {
            response.ok(Map.of("membershipPlan", updated));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "MembershipPlan not found"));
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteMembershipPlan(@PathVariable Integer id) {
        boolean deleted = membershipPlanService.deleteMembershipPlan(id);
        ApiResponse<Object> response = new ApiResponse<>();
        if (deleted) {
            response.ok(Map.of("message", "MembershipPlan deleted successfully"));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "MembershipPlan not found"));
            return ResponseEntity.status(404).body(response);
        }
    }
}
