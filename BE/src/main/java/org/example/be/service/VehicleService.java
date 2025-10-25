package org.example.be.service;

import org.example.be.entity.Vehicle;
import org.example.be.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Optional<Vehicle> getVehicleById(Integer id) {
        return vehicleRepository.findById(id);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle updateVehicle(Integer id, Vehicle updatedVehicle) {
        Optional<Vehicle> existingVehicle = vehicleRepository.findById(id);
        if (existingVehicle.isPresent()) {
            Vehicle vehicle = existingVehicle.get();
            vehicle.setBrand(updatedVehicle.getBrand());
            vehicle.setModel(updatedVehicle.getModel());
            vehicle.setMileage(updatedVehicle.getMileage());
            return vehicleRepository.save(vehicle);
        }
        return null;
    }

    public boolean deleteVehicle(Integer id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            return true;
        }
//        Vehicle vehicle = vehicleRepository.findById(id).orElse(null);
//        if (vehicle != null) {
//            vehicleRepository.delete(vehicle);
//            return true;
//        }
        return false;
    }

    public void deleteVehicle(Vehicle vehicle) {
        vehicleRepository.delete(vehicle);
    }
}