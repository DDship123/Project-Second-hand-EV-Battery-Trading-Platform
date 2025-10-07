package org.example.be.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contracts_id")
    private Integer contractsId;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @Column(name = "contract_url", columnDefinition = "NVARCHAR(500)")
    private String contractUrl;

    @Column(name = "signed_at")
    private LocalDateTime signedAt;

    @Column(columnDefinition = "NVARCHAR(20)")
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Integer getContractsId() {
        return contractsId;
    }

    public void setContractsId(Integer contractsId) {
        this.contractsId = contractsId;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
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