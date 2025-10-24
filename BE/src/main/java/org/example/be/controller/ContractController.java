package org.example.be.controller;

import org.example.be.dto.response.ApiResponse;
import org.example.be.entity.Contract;
import org.example.be.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
}
