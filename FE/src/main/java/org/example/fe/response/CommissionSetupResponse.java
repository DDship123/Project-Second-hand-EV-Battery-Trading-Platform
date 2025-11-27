package org.example.fe.response;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class CommissionSetupResponse {
    private Long id;

    private Double commissionRate;

    private double minimum;

    private double maximum;

    private long minimumB;

    private long maximumB;

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

    public long getMinimumB() {
        return minimumB;
    }



    public void setMinimumB(double minimumB) {
        BigDecimal bd = new BigDecimal(minimumB);
        this.minimumB = bd.longValue();
    }

    public String getCommissionRateFormatted() {
        DecimalFormat formatter = new DecimalFormat("#");
        return formatter.format(this.commissionRate*100);
    }

    public long getMaximumB() {
        return maximumB;
    }

    public void setMaximumB(double maximumB) {
        BigDecimal bd = new BigDecimal(maximumB);
        this.maximumB = bd.longValue();
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
