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
    private int productsId;

    private int memberId;   // FK
    private int vehicleId;  // FK
    private int batteryId;  // FK

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

    public int getProductsId() {
        return productsId;
    }

    public void setProductsId(int productsId) {
        this.productsId = productsId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getBatteryId() {
        return batteryId;
    }

    public void setBatteryId(int batteryId) {
        this.batteryId = batteryId;
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
