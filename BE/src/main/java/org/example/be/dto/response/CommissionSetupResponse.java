package org.example.be.dto.response;

import java.time.LocalDateTime;

public class CommissionSetupResponse {
    private Long id;

    private Double commissionRate;

    private double minimum;

    private double maximum;

    private String status;

    private String ProductType;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public CommissionSetupResponse() {
    }

    public CommissionSetupResponse(Double commissionRate, double minimum, double maximum, String status, String productType) {
        this.commissionRate = commissionRate;
        this.minimum = minimum;
        this.maximum = maximum;
        this.status = status;
        ProductType = productType;
    }

    public Double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(Double commissionRate) {
        this.commissionRate = commissionRate;
    }

    public double getMinimum() {
        return minimum;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    public double getMaximum() {
        return maximum;
    }

    public void setMaximum(double maximum) {
        this.maximum = maximum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
