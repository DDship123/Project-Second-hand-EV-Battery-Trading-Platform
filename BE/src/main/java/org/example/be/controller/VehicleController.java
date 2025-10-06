package org.example.be.controller;

import org.example.be.dto.ApiResponse;
import org.example.be.entity.Vehicle;
import org.example.be.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createVehicle(@RequestBody Vehicle vehicle) {
        Vehicle saved = vehicleService.createVehicle(vehicle);
        ApiResponse<Object> response = new ApiResponse<>();
        response.ok(Map.of("vehicle", saved));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getVehicleById(@PathVariable Integer id) {
        Optional<Vehicle> v = vehicleService.getVehicleById(id);
        ApiResponse<Object> response = new ApiResponse<>();
        if (v.isPresent()) {
            response.ok(Map.of("vehicle", v.get()));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "Vehicle not found"));
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllVehicles() {
        List<Vehicle> list = vehicleService.getAllVehicles();
        ApiResponse<Object> response = new ApiResponse<>();
        if (list.isEmpty()) {
            response.error(Map.of("message", "No vehicles found"));
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(Map.of("vehicles", list));
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateVehicle(@PathVariable Integer id, @RequestBody Vehicle vehicle) {
        Vehicle updated = vehicleService.updateVehicle(id, vehicle);
        ApiResponse<Object> response = new ApiResponse<>();
        if (updated != null) {
            response.ok(Map.of("vehicle", updated));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "Vehicle not found"));
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteVehicle(@PathVariable Integer id) {
        boolean deleted = vehicleService.deleteVehicle(id);
        ApiResponse<Object> response = new ApiResponse<>();
        if (deleted) {
            response.ok(Map.of("message", "Vehicle deleted successfully"));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "Vehicle not found"));
            return ResponseEntity.status(404).body(response);
        }
    }
}
