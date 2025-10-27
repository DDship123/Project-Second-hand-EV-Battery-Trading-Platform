package org.example.fe.service;

import org.example.fe.request.CreatePaymentRequest;
import org.example.fe.response.ApiResponse;
import org.example.fe.response.PaymentUrlResponse;
import org.example.fe.response.ReturnUrlResponse;

public interface BankService {
    public ApiResponse<PaymentUrlResponse> createBanking(CreatePaymentRequest req);
    public ApiResponse<ReturnUrlResponse> handleReturnUrlMultiParam(String returnUrl);
}
