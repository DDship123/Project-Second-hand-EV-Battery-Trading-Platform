package org.example.be.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "commissions")
public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comissions_id")
    private Integer commissionsId;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @Column(name = "commission_rate")
    private BigDecimal commissionRate;

    private BigDecimal amount;

    @Column(columnDefinition = "NVARCHAR(20)")
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Integer getCommissionsId() {
        return commissionsId;
    }

    public void setCommissionsId(Integer commissionsId) {
        this.commissionsId = commissionsId;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
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