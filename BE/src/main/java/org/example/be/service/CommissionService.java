package org.example.be.service;

import org.example.be.entity.Commission;
import org.example.be.repository.CommissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommissionService {

    @Autowired
    private CommissionRepository commissionRepository;

    public Commission createCommission(Commission commission) {
        return commissionRepository.save(commission);
    }

    public Optional<Commission> getCommissionById(Integer id) {
        return commissionRepository.findById(id);
    }

    public List<Commission> getAllCommissions() {
        return commissionRepository.findAll();
    }

    public Commission updateCommission(Integer id, Commission updatedCommission) {
        Optional<Commission> existingCommission = commissionRepository.findById(id);
        if (existingCommission.isPresent()) {
            Commission commission = existingCommission.get();
            commission.setTransactionId(updatedCommission.getTransactionId());
            commission.setCommissionRate(updatedCommission.getCommissionRate());
            commission.setAmount(updatedCommission.getAmount());
            commission.setStatus(updatedCommission.getStatus());
            commission.setCreatedAt(updatedCommission.getCreatedAt());
            return commissionRepository.save(commission);
        }
        return null;
    }

    public void deleteCommission(Integer id) {
        commissionRepository.deleteById(id);
    }
}