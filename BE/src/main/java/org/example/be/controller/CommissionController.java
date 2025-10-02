package org.example.be.controller;

import org.example.be.entity.Commission;
import org.example.be.service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/commissions")
public class CommissionController {

    @Autowired
    private CommissionService commissionService;

    @PostMapping
    public ResponseEntity<Commission> createCommission(@RequestBody Commission commission) {
        return ResponseEntity.ok(commissionService.createCommission(commission));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commission> getCommissionById(@PathVariable Integer id) {
        Optional<Commission> commission = commissionService.getCommissionById(id);
        return commission.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Commission>> getAllCommissions() {
        List<Commission> commissions = commissionService.getAllCommissions();
        return ResponseEntity.ok(commissions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commission> updateCommission(@PathVariable Integer id, @RequestBody Commission commission) {
        Commission updatedCommission = commissionService.updateCommission(id, commission);
        if (updatedCommission != null) {
            return ResponseEntity.ok(updatedCommission);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommission(@PathVariable Integer id) {
        commissionService.deleteCommission(id);
        return ResponseEntity.noContent().build();
    }
}