package org.example.be.service;

import org.example.be.entity.CommissionSetup;

import java.util.List;

public interface CommissionSetupService {
    public CommissionSetup findCommissionSetupByProductTypeAndPrice(String productType, double price);

    public List<CommissionSetup> getAllCommissionSetups();

    public CommissionSetup getCommissionSetupById(Long id);

    public CommissionSetup saveCommissionSetup(CommissionSetup commissionSetup);

    public CommissionSetup getDefaultCommissionSetup(String productType);

    public CommissionSetup updateCommissionSetup(CommissionSetup commissionSetup);


}
