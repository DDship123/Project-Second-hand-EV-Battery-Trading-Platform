package org.example.be.service;

import org.example.be.entity.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    Vehicle createVehicle(Vehicle vehicle);
    Optional<Vehicle> getVehicleById(Integer id);
    List<Vehicle> getAllVehicles();
    Vehicle updateVehicle(Integer id, Vehicle updatedVehicle);
    boolean deleteVehicle(Integer id);
    void deleteVehicle(Vehicle vehicle);
}