package org.example.be.controller;

import org.example.be.dto.response.ApiResponse;
import org.example.be.dto.response.ContractResponse;
import org.example.be.entity.Contract;
import org.example.be.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @PostMapping
    public ResponseEntity<ApiResponse<Contract>> createContract(@RequestBody Contract contract) {
        Contract saved = contractService.createContract(contract);
        ApiResponse<Contract> response = new ApiResponse<>();
        response.ok(saved);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Contract>> getContractById(@PathVariable Integer id) {
        Optional<Contract> contract = contractService.getContractById(id);
        ApiResponse<Contract> response = new ApiResponse<>();
        if (contract.isPresent()) {
            response.ok(contract.get());
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Contract not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Contract>>> getAllContracts() {
        List<Contract> list = contractService.getAllContracts();
        ApiResponse<List<Contract>> response = new ApiResponse<>();
        if (list.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No contracts found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(list);
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Contract>> updateContract(@PathVariable Integer id, @RequestBody Contract contract) {
        Contract updated = contractService.updateContract(id, contract);
        ApiResponse<Contract> response = new ApiResponse<>();
        if (updated != null) {
            response.ok(updated);
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Contract not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteContract(@PathVariable Integer id) {
        boolean deleted = contractService.deleteContract(id);
        ApiResponse<Void> response = new ApiResponse<>();
        if (deleted) {
            response.ok();
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Contract not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }

    // Đảm bảo có Contract cho transaction (nếu chưa có -> tạo "UNSIGN")
    @PostMapping("/by-transaction/{transactionId}/ensure")
    public ResponseEntity<ApiResponse<Contract>> ensureContract(@PathVariable Integer transactionId) {
        ApiResponse<Contract> res = new ApiResponse<>();
        try {
            Contract payload = contractService.ensureForTransaction(transactionId);
            res.ok(payload);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            res.error(error);
            return ResponseEntity.badRequest().body(res);
        }
    }

    // Lấy contract theo transaction
    @GetMapping("/by-transaction/{transactionId}")
    public ResponseEntity<ApiResponse<ContractResponse>> getByTransaction(@PathVariable Integer transactionId) {
        ApiResponse<ContractResponse> res = new ApiResponse<>();
        try {
            Contract payload = contractService.getByTransactionId(transactionId);
            ContractResponse contractResponse = new ContractResponse();
            contractResponse.setContractId(payload.getContractsId());
            contractResponse.setContractUrl(payload.getContractUrl());
            contractResponse.setStatus(payload.getStatus());
            contractResponse.setCreatedAt(payload.getCreatedAt());
            contractResponse.setSignedAt(payload.getSignedAt());
            res.ok(contractResponse);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            res.error(error);
            return ResponseEntity.badRequest().body(res);
        }
    }

    // Cập nhật URL ảnh hợp đồng đã ký (yêu cầu Transaction = DELIVERED). Set status=SIGNED + signedAt.
    @PutMapping("/by-transaction/{transactionId}/signed-url")
    public ResponseEntity<ApiResponse<Map<String, Object>>> updateSignedUrl(
            @PathVariable Integer transactionId,
            @RequestParam("url") String signedUrl) {

        ApiResponse<Map<String, Object>> res = new ApiResponse<>();
        try {
            Contract saved = contractService.setSignedUrl(transactionId, signedUrl);

            // Trả payload RÚT GỌN để tránh serialize toàn bộ entity
            Map<String, Object> payload = new HashMap<>();
            payload.put("contractId", saved.getContractsId());
            payload.put("contractUrl", saved.getContractUrl());
            payload.put("status", saved.getStatus());
            payload.put("signedAt", saved.getSignedAt());

            res.ok(payload);
            return ResponseEntity.ok(res);
        } catch (IllegalStateException ise) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", ise.getMessage());
            res.error(error);
            return ResponseEntity.status(409).body(res);
        } catch (Exception e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            res.error(error);
            return ResponseEntity.badRequest().body(res);
        }
    }

    // Tải hợp đồng mẫu
    @GetMapping("/by-transaction/{transactionId}/template")
    public ResponseEntity<Resource> downloadTemplate(@PathVariable Integer transactionId) {
        try {

            Resource template = new ClassPathResource("contracts/hop-dong-mua-ban-xe-may.pdf");

            if (!template.exists()) {
                return ResponseEntity.notFound().build();
            }

            ContentDisposition cd = ContentDisposition
                    .attachment()
                    .filename("hop-dong-mua-ban-xe-may.pdf", StandardCharsets.UTF_8)
                    .build();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, cd.toString())
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(template);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}