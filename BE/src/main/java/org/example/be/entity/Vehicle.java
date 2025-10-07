package org.example.be.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Integer vehicleId;

    @Column(name = "name", columnDefinition = "NVARCHAR(100)")
    private String name;

    @Column(name = "brand", columnDefinition = "NVARCHAR(50)")
    private String brand;

    @Column(name = "model", columnDefinition = "NVARCHAR(50)")
    private String model;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "condition", columnDefinition = "NVARCHAR(20)")
    private String condition;

    @Column(name = "register_year", columnDefinition = "NVARCHAR(4)")
    private String registerYear;

    @Column(name = "battery_capacity", columnDefinition = "NVARCHAR(20)")
    private String batteryCapacity;

    @Column(name = "origin", columnDefinition = "NVARCHAR(50)")
    private String origin;

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getRegisterYear() {
        return registerYear;
    }

    public void setRegisterYear(String registerYear) {
        this.registerYear = registerYear;
    }

    public String getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(String batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}