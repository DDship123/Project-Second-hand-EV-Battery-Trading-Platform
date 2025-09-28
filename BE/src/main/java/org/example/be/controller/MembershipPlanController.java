package org.example.be.controller;

import org.example.be.entity.MembershipPlan;
import org.example.be.service.MembershipPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/membership-plans")
public class MembershipPlanController {

    @Autowired
    private MembershipPlanService membershipPlanService;

    @PostMapping
    public ResponseEntity<MembershipPlan> createMembershipPlan(@RequestBody MembershipPlan membershipPlan) {
        return ResponseEntity.ok(membershipPlanService.createMembershipPlan(membershipPlan));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembershipPlan> getMembershipPlanById(@PathVariable Integer id) {
        Optional<MembershipPlan> membershipPlan = membershipPlanService.getMembershipPlanById(id);
        return membershipPlan.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MembershipPlan>> getAllMembershipPlans() {
        List<MembershipPlan> membershipPlans = membershipPlanService.getAllMembershipPlans();
        return ResponseEntity.ok(membershipPlans);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MembershipPlan> updateMembershipPlan(@PathVariable Integer id, @RequestBody MembershipPlan membershipPlan) {
        MembershipPlan updatedMembershipPlan = membershipPlanService.updateMembershipPlan(id, membershipPlan);
        if (updatedMembershipPlan != null) {
            return ResponseEntity.ok(updatedMembershipPlan);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembershipPlan(@PathVariable Integer id) {
        membershipPlanService.deleteMembershipPlan(id);
        return ResponseEntity.noContent().build();
    }
}