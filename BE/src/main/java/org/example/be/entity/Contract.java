package org.example.be.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "contracts")
@Data
@Getter
@Setter
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contracts_id")
    private Integer contractsId;

    @Column(name = "transaction_id", unique = true)
    private Integer transactionId;

    @Column(name = "contract_url")
    private String contractUrl;

    @Column(name = "signed_at")
    private LocalDateTime signedAt;

    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}