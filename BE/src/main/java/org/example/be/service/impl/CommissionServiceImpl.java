package org.example.be.service.impl;

import org.example.be.entity.Commission;
import org.example.be.repository.CommissionRepository;
import org.example.be.service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommissionServiceImpl implements CommissionService {

    @Autowired
    private CommissionRepository commissionRepository;

    @Override
    public Commission createCommission(Commission commission) {
        return commissionRepository.save(commission);
    }

    @Override
    public Optional<Commission> getCommissionById(Integer id) {
        return commissionRepository.findById(id);
    }

    @Override
    public List<Commission> getAllCommissions() {
        return commissionRepository.findAll();
    }

    @Override
    public Commission updateCommission(Integer id, Commission updatedCommission) {
        Optional<Commission> existingCommission = commissionRepository.findById(id);
        if (existingCommission.isPresent()) {
            Commission commission = existingCommission.get();
            commission.setTransaction(updatedCommission.getTransaction());
            commission.setCommissionRate(updatedCommission.getCommissionRate());
            commission.setAmount(updatedCommission.getAmount());
            commission.setStatus(updatedCommission.getStatus());
            commission.setCreatedAt(updatedCommission.getCreatedAt());
            return commissionRepository.save(commission);
        }
        return null;
    }

    @Override
    public boolean deleteCommission(Integer id) {
        if (commissionRepository.existsById(id)) {
            commissionRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Commission getCommissionByTransactionId(Integer transactionId) {
        return commissionRepository.findByTransaction_TransactionId(transactionId);
    }

    @Override
    public double calculateTotalCommission() {
        List<Commission> commissions = commissionRepository.findAllComplete();
        double totalCommission = 0.0;
        for (Commission commission : commissions) {
            totalCommission += commission.getAmount().doubleValue();
        }
        return totalCommission;
    }
}