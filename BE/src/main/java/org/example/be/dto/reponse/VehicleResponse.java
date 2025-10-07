package org.example.be.dto.reponse;

public class VehicleResponse {
    private int vehicleId;
    private String brand;
    private String model;
    private int mileage;

    public VehicleResponse() {
    }

    public VehicleResponse(int vehicleId, String brand, String model, int mileage) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
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
}
