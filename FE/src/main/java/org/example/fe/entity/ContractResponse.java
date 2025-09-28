package org.example.fe.entity;

import java.time.LocalDateTime;

public class ContractResponse {
    private int contractId;
    private int transactionId;
    private String contractUrl;
    private LocalDateTime signedAt;
    private String status;
    private LocalDateTime createdAt;

    public ContractResponse() {
    }

    public ContractResponse(int contractId, int transactionId, String contractUrl, LocalDateTime signedAt, String status, LocalDateTime createdAt) {
        this.contractId = contractId;
        this.transactionId = transactionId;
        this.contractUrl = contractUrl;
        this.signedAt = signedAt;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }

    public LocalDateTime getSignedAt() {
        return signedAt;
    }

    public void setSignedAt(LocalDateTime signedAt) {
        this.signedAt = signedAt;
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
