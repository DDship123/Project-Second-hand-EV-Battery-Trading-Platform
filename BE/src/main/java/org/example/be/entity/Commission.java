package org.example.be.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "commissions")
@Data
@Getter
@Setter
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

    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}