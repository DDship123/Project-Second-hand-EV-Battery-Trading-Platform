package org.example.be.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "reviews")
public class    Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviews_id")
    private Integer reviewsId;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Member seller;      // FK

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private Member reviewer;    // FK

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction; // FK

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "comment", length = 1000)
    private String comment;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
