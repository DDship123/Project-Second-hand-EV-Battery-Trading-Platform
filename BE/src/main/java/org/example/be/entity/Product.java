package org.example.be.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
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

    @Column(name = "product_type", length = 50, columnDefinition = "NVARCHAR(50)")
    private String productType;

    @Column(name = "name", length = 20, nullable = false, columnDefinition = "NVARCHAR(20)")
    private String name;

    @Column(name = "description", length = 500, columnDefinition = "NVARCHAR(500)")
    private String description;

    @Column(name = "status", length = 20, columnDefinition = "NVARCHAR(20)")
    private String status; // active, sold, removed

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    public Integer getProductsId() {
        return productsId;
    }

    public void setProductsId(Integer productsId) {
        this.productsId = productsId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
