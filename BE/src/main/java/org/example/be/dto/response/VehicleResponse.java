package org.example.be.dto.response;

public class VehicleResponse {
    private int vehicleId;
    private String name;
    private String brand;
    private String model;
    private int mileage;
    private String registrationYear;
    private String condition;
    private String origin;
    private String batteryCapacity;

    public VehicleResponse() {
    }

    public VehicleResponse(int vehicleId, String name, String brand, String model,
                           int mileage, String registrationYear, String condition,
                           String origin, String batteryCapacity) {
        this.vehicleId = vehicleId;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.registrationYear = registrationYear;
        this.condition = condition;
        this.origin = origin;
        this.batteryCapacity = batteryCapacity;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
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

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getRegistrationYear() {
        return registrationYear;
    }

    public void setRegistrationYear(String registrationYear) {
        this.registrationYear = registrationYear;
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

    public String getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(String batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }
}
