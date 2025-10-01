package org.example.be.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "products_id")
    private Integer productsId;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;   // FK

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;  // FK

    @OneToOne
    @JoinColumn(name = "battery_id")
    private Battery battery;  // FK

    @Column(name = "product_type", length = 50)
    private String productType;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "status", length = 20)
    private String status; // active, sold, removed

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
