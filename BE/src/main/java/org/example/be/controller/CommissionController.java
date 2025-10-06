package org.example.be.controller;

import org.example.be.dto.ApiResponse;
import org.example.be.entity.Commission;
import org.example.be.service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/commissions")
public class CommissionController {

    @Autowired
    private CommissionService commissionService;

    // --- CREATE COMMISSION ---
    @PostMapping
    public ResponseEntity<ApiResponse<?>> createCommission(@RequestBody Commission commission) {
        Commission saved = commissionService.createCommission(commission);
        ApiResponse<Object> response = new ApiResponse<>();
        response.ok(Map.of(
                "message", "Commission created successfully",
                "commission", saved
        ));
        return ResponseEntity.ok(response);
    }

    // --- GET BY ID ---
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getCommissionById(@PathVariable Integer id) {
        Optional<Commission> commission = commissionService.getCommissionById(id);
        ApiResponse<Object> response = new ApiResponse<>();
        if (commission.isPresent()) {
            response.ok(Map.of(
                    "message", "Commission fetched successfully",
                    "commission", commission.get()
            ));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "Commission not found"));
            return ResponseEntity.status(404).body(response);
        }
    }

    // --- GET ALL ---
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllCommissions() {
        List<Commission> commissions = commissionService.getAllCommissions();
        ApiResponse<Object> response = new ApiResponse<>();
        response.ok(Map.of(
                "message", "All commissions fetched successfully",
                "commissions", commissions
        ));
        return ResponseEntity.ok(response);
    }

    // --- UPDATE ---
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateCommission(@PathVariable Integer id, @RequestBody Commission commission) {
        Commission updatedCommission = commissionService.updateCommission(id, commission);
        ApiResponse<Object> response = new ApiResponse<>();
        if (updatedCommission != null) {
            response.ok(Map.of(
                    "message", "Commission updated successfully",
                    "commission", updatedCommission
            ));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "Commission not found"));
            return ResponseEntity.status(404).body(response);
        }
    }

    // --- DELETE ---
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteCommission(@PathVariable Integer id) {
        boolean deleted = commissionService.deleteCommission(id);
        ApiResponse<Object> response = new ApiResponse<>();
        if (deleted) {
            response.ok(Map.of("message", "Commission deleted successfully"));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "Commission not found"));
            return ResponseEntity.status(404).body(response);
        }
    }
}
