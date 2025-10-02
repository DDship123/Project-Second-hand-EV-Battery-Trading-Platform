package org.example.be.controller;

import org.example.be.entity.MemberPlanUsage;
import org.example.be.service.MemberPlanUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/member-plan-usages")
public class MemberPlanUsageController {

    @Autowired
    private MemberPlanUsageService memberPlanUsageService;

    @PostMapping
    public ResponseEntity<MemberPlanUsage> createMemberPlanUsage(@RequestBody MemberPlanUsage memberPlanUsage) {
        return ResponseEntity.ok(memberPlanUsageService.createMemberPlanUsage(memberPlanUsage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberPlanUsage> getMemberPlanUsageById(@PathVariable Integer id) {
        Optional<MemberPlanUsage> memberPlanUsage = memberPlanUsageService.getMemberPlanUsageById(id);
        return memberPlanUsage.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MemberPlanUsage>> getAllMemberPlanUsages() {
        List<MemberPlanUsage> memberPlanUsages = memberPlanUsageService.getAllMemberPlanUsages();
        return ResponseEntity.ok(memberPlanUsages);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberPlanUsage> updateMemberPlanUsage(@PathVariable Integer id, @RequestBody MemberPlanUsage memberPlanUsage) {
        MemberPlanUsage updatedMemberPlanUsage = memberPlanUsageService.updateMemberPlanUsage(id, memberPlanUsage);
        if (updatedMemberPlanUsage != null) {
            return ResponseEntity.ok(updatedMemberPlanUsage);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemberPlanUsage(@PathVariable Integer id) {
        memberPlanUsageService.deleteMemberPlanUsage(id);
        return ResponseEntity.noContent().build();
    }
}