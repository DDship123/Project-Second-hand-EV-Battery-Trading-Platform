package org.example.be.controller;

import org.example.be.dto.response.ApiResponse;
import org.example.be.entity.Vehicle;
import org.example.be.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<ApiResponse<Vehicle>> createVehicle(@RequestBody Vehicle vehicle) {
        Vehicle saved = vehicleService.createVehicle(vehicle);
        ApiResponse<Vehicle> response = new ApiResponse<>();
        response.ok(saved);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Vehicle>> getVehicleById(@PathVariable Integer id) {
        Optional<Vehicle> v = vehicleService.getVehicleById(id);
        ApiResponse<Vehicle> response = new ApiResponse<>();
        if (v.isPresent()) {
            response.ok(v.get());
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Vehicle not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Vehicle>>> getAllVehicles() {
        List<Vehicle> list = vehicleService.getAllVehicles();
        ApiResponse<List<Vehicle>> response = new ApiResponse<>();
        if (list.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No vehicles found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(list);
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Vehicle>> updateVehicle(@PathVariable Integer id, @RequestBody Vehicle vehicle) {
        Vehicle updated = vehicleService.updateVehicle(id, vehicle);
        ApiResponse<Vehicle> response = new ApiResponse<>();
        if (updated != null) {
            response.ok(updated);
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Vehicle not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteVehicle(@PathVariable Integer id) {
        boolean deleted = vehicleService.deleteVehicle(id);
        ApiResponse<Void> response = new ApiResponse<>();
        if (deleted) {
            response.ok();
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Vehicle not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }
}
