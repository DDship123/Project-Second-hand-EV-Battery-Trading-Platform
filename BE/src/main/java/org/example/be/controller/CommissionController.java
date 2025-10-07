package org.example.be.controller;

import org.example.be.dto.reponse.ApiResponse;
import org.example.be.entity.Commission;
import org.example.be.service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/commissions")
public class CommissionController {

    @Autowired
    private CommissionService commissionService;

    @PostMapping
    public ResponseEntity<ApiResponse<Commission>> createCommission(@RequestBody Commission commission) {
        Commission saved = commissionService.createCommission(commission);
        ApiResponse<Commission> response = new ApiResponse<>();
        response.ok(saved);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Commission>> getCommissionById(@PathVariable Integer id) {
        Optional<Commission> commission = commissionService.getCommissionById(id);
        ApiResponse<Commission> response = new ApiResponse<>();
        if (commission.isPresent()) {
            response.ok(commission.get());
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Commission not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Commission>>> getAllCommissions() {
        List<Commission> list = commissionService.getAllCommissions();
        ApiResponse<List<Commission>> response = new ApiResponse<>();
        if (list.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No commissions found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(list);
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Commission>> updateCommission(@PathVariable Integer id, @RequestBody Commission commission) {
        Commission updated = commissionService.updateCommission(id, commission);
        ApiResponse<Commission> response = new ApiResponse<>();
        if (updated != null) {
            response.ok(updated);
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Commission not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCommission(@PathVariable Integer id) {
        boolean deleted = commissionService.deleteCommission(id);
        ApiResponse<Void> response = new ApiResponse<>();
        if (deleted) {
            response.ok();
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Commission not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }
}
