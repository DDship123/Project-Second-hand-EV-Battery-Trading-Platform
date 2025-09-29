package org.example.be.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transactions_id")
    private Integer transactionsId;

    @Column(name = "buyer_id")
    private Integer buyerId;

    @Column(name = "seller_id")
    private Integer sellerId;

    @Column(name = "post_id")
    private Integer postId;

    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}