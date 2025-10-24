package org.example.be.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CreatePaymentRequest {

    @NotNull
    @Min(1)
    private Long amount;     // VND, ví dụ 10000

    // "", "VNBANK", "INTCARD", "VNPAYQR" (có thể để trống => cổng chung)
    private String bankCode;

    // "vn" hoặc "en" (có thể để trống => mặc định "vn")
    private String language;

    public Long getAmount() { return amount; }
    public void setAmount(Long amount) { this.amount = amount; }

    public String getBankCode() { return bankCode; }
    public void setBankCode(String bankCode) { this.bankCode = bankCode; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
}