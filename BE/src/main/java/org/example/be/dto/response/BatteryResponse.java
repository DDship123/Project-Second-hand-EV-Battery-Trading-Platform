package org.example.be.dto.response;

public class BatteryResponse {
    private int batteryId;
    private String condition;
    private String brand;
    private int capacity;
    private String voltage;
    private String yearOfManufacture;
    private String origin;
    private String name;

    public BatteryResponse() {
    }

    public BatteryResponse(int batteryId, String condition, String brand,
                           int capacity, String voltage, String yearOfManufacture,
                           String origin, String name) {
        this.batteryId = batteryId;
        this.condition = condition;
        this.brand = brand;
        this.capacity = capacity;
        this.voltage = voltage;
        this.yearOfManufacture = yearOfManufacture;
        this.origin = origin;
        this.name = name;
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

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(String yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
