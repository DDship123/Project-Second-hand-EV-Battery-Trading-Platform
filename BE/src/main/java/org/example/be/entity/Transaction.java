package org.example.be.entity;

import jakarta.persistence.*;
import lombok.Data;
<<<<<<< HEAD
import lombok.Getter;
import lombok.Setter;
=======

>>>>>>> 29e5a4496e0d101702e24d0e7d8ea1f1ebcf3c23

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

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Member buyer;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Member seller;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}