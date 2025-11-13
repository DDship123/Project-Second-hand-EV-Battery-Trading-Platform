package org.example.be.service.impl;

import org.example.be.entity.CommissionSetup;
import org.example.be.repository.CommissionSetupRepository;
import org.example.be.service.CommissionSetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommissionSetupServiceImpl  implements CommissionSetupService {
    @Autowired
    private CommissionSetupRepository commissionSetupRepository;

    @Override
    public CommissionSetup findCommissionSetupByProductTypeAndPrice(String productType, double price) {
        return commissionSetupRepository.findByProductTypeAndPrice(productType, price);
    }

    @Override
    public List<CommissionSetup> getAllCommissionSetups() {
        return commissionSetupRepository.findAll();
    }

    @Override
    public CommissionSetup getCommissionSetupById(Long id) {
        return commissionSetupRepository.findById(id).orElse(null);
    }

    @Override
    public CommissionSetup saveCommissionSetup(CommissionSetup commissionSetup) {
        return commissionSetupRepository.save(commissionSetup);
    }

    @Override
    public CommissionSetup getDefaultCommissionSetup(String productType) {
        return commissionSetupRepository.getDefaultCommissionSetup(productType);
    }

}
