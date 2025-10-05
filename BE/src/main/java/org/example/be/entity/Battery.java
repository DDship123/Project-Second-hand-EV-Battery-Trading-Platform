package org.example.be.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "battery")
public class Battery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "battery_id")
    private Integer batteryId;

    @Column(name = "name")
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "year_at")
    private String yearAt;

    @Column(name = "voltage_v")
    private String voltageV;

    @Column(name = "capacity_ah")
    private Integer capacityAh;

    @Column(name = "condition")
    private String condition;

    @Column(name = "origin")
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