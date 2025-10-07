package org.example.be.controller;

import org.example.be.dto.reponse.ApiResponse;
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
    public ResponseEntity<ApiResponse<MembershipPlan>> createMembershipPlan(@RequestBody MembershipPlan plan) {
        MembershipPlan saved = membershipPlanService.createMembershipPlan(plan);
        ApiResponse<MembershipPlan> response = new ApiResponse<>();
        response.ok(saved);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MembershipPlan>> getMembershipPlanById(@PathVariable Integer id) {
        Optional<MembershipPlan> plan = membershipPlanService.getMembershipPlanById(id);
        ApiResponse<MembershipPlan> response = new ApiResponse<>();
        if (plan.isPresent()) {
            response.ok(plan.get());
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "MembershipPlan not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MembershipPlan>>> getAllMembershipPlans() {
        List<MembershipPlan> list = membershipPlanService.getAllMembershipPlans();
        ApiResponse<List<MembershipPlan>> response = new ApiResponse<>();
        if (list.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No MembershipPlans found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(list);
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MembershipPlan>> updateMembershipPlan(@PathVariable Integer id, @RequestBody MembershipPlan plan) {
        MembershipPlan updated = membershipPlanService.updateMembershipPlan(id, plan);
        ApiResponse<MembershipPlan> response = new ApiResponse<>();
        if (updated != null) {
            response.ok(updated);
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
