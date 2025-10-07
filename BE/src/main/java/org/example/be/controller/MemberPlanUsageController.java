package org.example.be.controller;

import org.example.be.dto.reponse.ApiResponse;
import org.example.be.entity.MemberPlanUsage;
import org.example.be.service.MemberPlanUsageService;
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
