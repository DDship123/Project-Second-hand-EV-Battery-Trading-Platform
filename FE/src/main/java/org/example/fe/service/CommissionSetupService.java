package org.example.fe.service;

import org.example.fe.response.ApiResponse;
import org.example.fe.response.CommissionSetupResponse;

import java.util.List;

public interface CommissionSetupService {

    public ApiResponse<List<CommissionSetupResponse>> getAllCommissionSetups();

    public ApiResponse<CommissionSetupResponse>  getCommissionSetupById(Long id);

    public ApiResponse<CommissionSetupResponse>  saveCommissionSetup(CommissionSetupResponse commissionSetup);

    public ApiResponse<CommissionSetupResponse>  updateCommissionSetup(Long id, CommissionSetupResponse commissionSetup);

}
