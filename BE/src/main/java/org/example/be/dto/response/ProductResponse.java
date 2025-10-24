package org.example.be.dto.response;

import java.time.LocalDateTime;

public class ProductResponse {
    private int productId;
    private int memberId;
    private String productType;
    private VehicleResponse vehicle;
    private BatteryResponse battery;
    private String productName;
    private String description;
    private String status;
    private LocalDateTime createdAt;

    public ProductResponse() {
    }

    public ProductResponse(int productId, int memberId, String productType,
                           VehicleResponse vehicle, BatteryResponse battery,
                           String productName, String description, String status,
                           LocalDateTime createdAt) {
        this.productId = productId;
        this.memberId = memberId;
        this.productType = productType;
        this.vehicle = vehicle;
        this.battery = battery;
        this.productName = productName;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public VehicleResponse getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleResponse vehicle) {
        this.vehicle = vehicle;
    }

    public BatteryResponse getBattery() {
        return battery;
    }

    public void setBattery(BatteryResponse battery) {
        this.battery = battery;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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