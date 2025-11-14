package org.example.be.controller;

import org.example.be.dto.response.ApiResponse;
import org.example.be.dto.response.CommissionSetupResponse;
import org.example.be.entity.CommissionSetup;
import org.example.be.service.CommissionService;
import org.example.be.service.CommissionSetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/commission-setup")
public class CommissionSetupController {
    @Autowired
    private CommissionSetupService commissionSetupService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<CommissionSetupResponse>>> getAllCommissionSetups() {
        ApiResponse<List<CommissionSetupResponse>> apiResponse = new ApiResponse<>();
        List<CommissionSetup> commissionSetups = commissionSetupService.getAllCommissionSetups();
        if (commissionSetups.isEmpty()) {
            Map<String, String> error = Map.of("message", "Không có cấu hình hoa hồng nào");
            apiResponse.error(error);
            return ResponseEntity.status(404).body(apiResponse);
        }
        List<CommissionSetupResponse> responseList = commissionSetups.stream().map(cs -> {
            CommissionSetupResponse response = new CommissionSetupResponse();
            response.setId(cs.getId());
            response.setProductType(cs.getProductType());
            response.setMinimum(cs.getMinimum());
            response.setMaximum(cs.getMaximum());
            response.setCommissionRate(cs.getCommissionRate());
            response.setStatus(cs.getStatus());
            response.setCreatedAt(cs.getCreatedAt());
            response.setUpdatedAt(cs.getUpdatedAt());
            return response;
        }).toList();
        apiResponse.ok(responseList);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CommissionSetupResponse>> getCommissionSetupById(@PathVariable Long id) {
        ApiResponse<CommissionSetupResponse> apiResponse = new ApiResponse<>();
        CommissionSetup commissionSetup = commissionSetupService.getCommissionSetupById(id);
        if (commissionSetup == null) {
            Map<String, String> error = Map.of("message", "Không tìm thấy cấu hình hoa hồng");
            apiResponse.error(error);
            return ResponseEntity.status(404).body(apiResponse);
        }
        CommissionSetupResponse response = new CommissionSetupResponse();
        response.setId(commissionSetup.getId());
        response.setProductType(commissionSetup.getProductType());
        response.setMinimum(commissionSetup.getMinimum());
        response.setMaximum(commissionSetup.getMaximum());
        response.setCommissionRate(commissionSetup.getCommissionRate());
        response.setStatus(commissionSetup.getStatus());
        response.setCreatedAt(commissionSetup.getCreatedAt());
        response.setUpdatedAt(commissionSetup.getUpdatedAt());
        apiResponse.ok(response);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CommissionSetupResponse>> createCommissionSetup(@RequestBody CommissionSetupResponse commissionSetup) {
        ApiResponse<CommissionSetupResponse> apiResponse = new ApiResponse<>();
        CommissionSetup saveCommissionSetup = new CommissionSetup();
        saveCommissionSetup.setProductType(commissionSetup.getProductType());
        saveCommissionSetup.setMinimum(commissionSetup.getMinimum());
        saveCommissionSetup.setMaximum(commissionSetup.getMaximum());
        saveCommissionSetup.setCommissionRate(commissionSetup.getCommissionRate());
        saveCommissionSetup.setStatus("ACTIVE");
        saveCommissionSetup.setCreatedAt(commissionSetup.getCreatedAt());
        saveCommissionSetup.setUpdatedAt(commissionSetup.getUpdatedAt());
        CommissionSetup created = commissionSetupService.saveCommissionSetup(saveCommissionSetup);
        commissionSetup.setId(created.getId());
        apiResponse.ok(commissionSetup);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<CommissionSetupResponse>> updateCommissionSetup(@PathVariable Long id, @RequestBody CommissionSetupResponse commissionSetup) {
        ApiResponse<CommissionSetupResponse> apiResponse = new ApiResponse<>();
        CommissionSetup existing = commissionSetupService.getCommissionSetupById(id);
        if (existing == null) {
            Map<String, String> error = Map.of("message", "Không tìm thấy cấu hình hoa hồng");
            apiResponse.error(error);
            return ResponseEntity.status(404).body(apiResponse);
        }
        existing.setProductType(commissionSetup.getProductType());
        existing.setMinimum(commissionSetup.getMinimum());
        existing.setMaximum(commissionSetup.getMaximum());
        existing.setCommissionRate(commissionSetup.getCommissionRate());
        existing.setStatus(commissionSetup.getStatus());
        existing.setUpdatedAt(commissionSetup.getUpdatedAt());
        CommissionSetup updated = commissionSetupService.updateCommissionSetup(existing);
        commissionSetup.setId(updated.getId());
        apiResponse.ok(commissionSetup);
        return ResponseEntity.ok(apiResponse);
    }
}
