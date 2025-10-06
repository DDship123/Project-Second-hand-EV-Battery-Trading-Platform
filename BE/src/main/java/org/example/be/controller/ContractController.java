package org.example.be.controller;

import org.example.be.dto.ApiResponse;
import org.example.be.entity.Contract;
import org.example.be.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createContract(@RequestBody Contract contract) {
        Contract saved = contractService.createContract(contract);
        ApiResponse<Object> response = new ApiResponse<>();
        response.ok(Map.of("contract", saved));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getContractById(@PathVariable Integer id) {
        Optional<Contract> contract = contractService.getContractById(id);
        ApiResponse<Object> response = new ApiResponse<>();
        if (contract.isPresent()) {
            response.ok(Map.of("contract", contract.get()));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "Contract not found"));
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllContracts() {
        List<Contract> list = contractService.getAllContracts();
        ApiResponse<Object> response = new ApiResponse<>();
        if (list.isEmpty()) {
            response.error(Map.of("message", "No contracts found"));
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(Map.of("contracts", list));
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateContract(@PathVariable Integer id, @RequestBody Contract contract) {
        Contract updated = contractService.updateContract(id, contract);
        ApiResponse<Object> response = new ApiResponse<>();
        if (updated != null) {
            response.ok(Map.of("contract", updated));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "Contract not found"));
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteContract(@PathVariable Integer id) {
        boolean deleted = contractService.deleteContract(id);
        ApiResponse<Object> response = new ApiResponse<>();
        if (deleted) {
            response.ok(Map.of("message", "Contract deleted successfully"));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "Contract not found"));
            return ResponseEntity.status(404).body(response);
        }
    }
}
