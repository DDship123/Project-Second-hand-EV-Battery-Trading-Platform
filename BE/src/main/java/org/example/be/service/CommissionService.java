package org.example.be.service;

import org.example.be.entity.Commission;

import java.util.List;
import java.util.Optional;

public interface CommissionService {
    Commission createCommission(Commission commission);
    Optional<Commission> getCommissionById(Integer id);
    List<Commission> getAllCommissions();
    Commission updateCommission(Integer id, Commission updatedCommission);
    boolean deleteCommission(Integer id);

    Commission getCommissionByTransactionId(Integer transactionId);

    double calculateTotalCommission();
}