package org.example.be.dto.response;

import java.time.LocalDateTime;

public class ContractResponse {
    private int contractId;
    private TransactionResponse transaction;
    private String contractUrl;
    private LocalDateTime signedAt;
    private String status;
    private LocalDateTime createdAt;

    public ContractResponse() {
    }
    public ContractResponse(int contractId, TransactionResponse transaction, String contractUrl, LocalDateTime signedAt, String status, LocalDateTime createdAt) {
        this.contractId = contractId;
        this.transaction = transaction;
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

    public TransactionResponse getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionResponse transaction) {
        this.transaction = transaction;
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
