package org.example.be.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "battery")
public class Battery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "battery_id",unique = true, nullable = false)
    private Integer batteryId;

    @Column(name = "name", columnDefinition = "NVARCHAR(100)",nullable = false)
    private String name;

    @Column(name = "brand", columnDefinition = "NVARCHAR(50)",nullable = false)
    private String brand;

    @Column(name = "year_at", columnDefinition = "NVARCHAR(4)", nullable = false)
    @Size(max = 4, message = "Year length is 4")
    private String yearAt;

    @Column(name = "voltage_v", columnDefinition = "NVARCHAR(10)", nullable = false)
    private String voltageV;

    @Column(name = "capacity_ah", nullable = false)
    @NotNull(message = "Capacity must not be null")
    @Min(value = 1, message = "Capacity must be greater than 0")
    private Integer capacityAh;

    @Column(name = "condition", columnDefinition = "NVARCHAR(20)",nullable = false)
    private String condition;

    @Column(name = "origin", columnDefinition = "NVARCHAR(50)", nullable = false)
    private String origin;

    public Integer getBatteryId() {
        return batteryId;
    }

    public void setBatteryId(Integer batteryId) {
        this.batteryId = batteryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getYearAt() {
        return yearAt;
    }

    public void setYearAt(String yearAt) {
        this.yearAt = yearAt;
    }

    public String getVoltageV() {
        return voltageV;
    }

    public void setVoltageV(String voltageV) {
        this.voltageV = voltageV;
    }

    public Integer getCapacityAh() {
        return capacityAh;
    }

    public void setCapacityAh(Integer capacityAh) {
        this.capacityAh = capacityAh;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}