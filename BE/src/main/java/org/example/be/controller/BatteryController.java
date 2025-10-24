package org.example.be.controller;

import org.example.be.dto.response.ApiResponse;
import org.example.be.entity.Battery;
import org.example.be.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/batteries")
public class BatteryController {

    @Autowired
    private BatteryService batteryService;

    @PostMapping
    public ResponseEntity<ApiResponse<Battery>> createBattery(@RequestBody Battery battery) {
        Battery saved = batteryService.createBattery(battery);
        ApiResponse<Battery> response = new ApiResponse<>();
        response.ok(saved);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Battery>> getBatteryById(@PathVariable Integer id) {
        Optional<Battery> b = batteryService.getBatteryById(id);
        ApiResponse<Battery> response = new ApiResponse<>();
        if (b.isPresent()) {
            response.ok(b.get());
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Battery not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Battery>>> getAllBatteries() {
        List<Battery> list = batteryService.getAllBatteries();
        ApiResponse<List<Battery>> response = new ApiResponse<>();
        if (list.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No batteries found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(list);
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Battery>> updateBattery(@PathVariable Integer id, @RequestBody Battery battery) {
        Battery updated = batteryService.updateBattery(id, battery);
        ApiResponse<Battery> response = new ApiResponse<>();
        if (updated != null) {
            response.ok(updated);
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Battery not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBattery(@PathVariable Integer id) {
        boolean deleted = batteryService.deleteBattery(id);
        ApiResponse<Void> response = new ApiResponse<>();
        if (deleted) {
            response.ok();
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Battery not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }
}
