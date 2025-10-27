package org.example.be.dto.response;

import java.time.LocalDateTime;

public class CommissionResponse {
    private int commissionId;
    private double commissionRate;
    private double amount;
    private String status;
    private LocalDateTime createdAt;

    public CommissionResponse() {
    }

    public CommissionResponse(double commissionRate, double amount, String status, LocalDateTime createdAt) {
        this.commissionRate = commissionRate;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public CommissionResponse(int commissionId, double commissionRate, double amount, String status, LocalDateTime createdAt) {
        this.commissionId = commissionId;
        this.commissionRate = commissionRate;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getCommissionId() {
        return commissionId;
    }

    public void setCommissionId(int commissionId) {
        this.commissionId = commissionId;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
