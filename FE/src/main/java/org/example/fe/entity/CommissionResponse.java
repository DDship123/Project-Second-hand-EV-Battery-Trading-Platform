package org.example.fe.entity;

import java.time.LocalDateTime;

public class CommissionResponse {
    private int commissionId;
    private int transactionId;
    private double commissionRate;
    private double amount;
    private String status;
    private LocalDateTime createdAt;

    public CommissionResponse() {
    }

    public CommissionResponse(int commissionId, int transactionId, double commissionRate, double amount, String status, LocalDateTime createdAt) {
        this.commissionId = commissionId;
        this.transactionId = transactionId;
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

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
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
