package org.example.be.controller;

import org.example.be.dto.ApiResponse;
import org.example.be.entity.MemberPlanUsage;
import org.example.be.service.MemberPlanUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/member-plan-usages")
public class MemberPlanUsageController {

    @Autowired
    private MemberPlanUsageService memberPlanUsageService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createMemberPlanUsage(@RequestBody MemberPlanUsage usage) {
        MemberPlanUsage saved = memberPlanUsageService.createMemberPlanUsage(usage);
        ApiResponse<Object> response = new ApiResponse<>();
        response.ok(Map.of("memberPlanUsage", saved));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getMemberPlanUsageById(@PathVariable Integer id) {
        Optional<MemberPlanUsage> usage = memberPlanUsageService.getMemberPlanUsageById(id);
        ApiResponse<Object> response = new ApiResponse<>();
        if (usage.isPresent()) {
            response.ok(Map.of("memberPlanUsage", usage.get()));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "MemberPlanUsage not found"));
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllMemberPlanUsages() {
        List<MemberPlanUsage> list = memberPlanUsageService.getAllMemberPlanUsages();
        ApiResponse<Object> response = new ApiResponse<>();
        if (list.isEmpty()) {
            response.error(Map.of("message", "No MemberPlanUsages found"));
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(Map.of("memberPlanUsages", list));
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateMemberPlanUsage(@PathVariable Integer id, @RequestBody MemberPlanUsage usage) {
        MemberPlanUsage updated = memberPlanUsageService.updateMemberPlanUsage(id, usage);
        ApiResponse<Object> response = new ApiResponse<>();
        if (updated != null) {
            response.ok(Map.of("memberPlanUsage", updated));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "MemberPlanUsage not found"));
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteMemberPlanUsage(@PathVariable Integer id) {
        boolean deleted = memberPlanUsageService.deleteMemberPlanUsage(id);
        ApiResponse<Object> response = new ApiResponse<>();
        if (deleted) {
            response.ok(Map.of("message", "MemberPlanUsage deleted successfully"));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "MemberPlanUsage not found"));
            return ResponseEntity.status(404).body(response);
        }
    }
}
