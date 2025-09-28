package org.example.fe.entity;

public class BatteryResponse {
    private int batteryId;
    private String condition;
    private String brand;
    private int capacity;

    public BatteryResponse() {
    }

    public BatteryResponse(int batteryId, String condition, String brand, int capacity) {
        this.batteryId = batteryId;
        this.condition = condition;
        this.brand = brand;
        this.capacity = capacity;
    }

    public int getBatteryId() {
        return batteryId;
    }

    public void setBatteryId(int batteryId) {
        this.batteryId = batteryId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
