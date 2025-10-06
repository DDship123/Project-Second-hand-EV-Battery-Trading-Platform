package org.example.be.controller;

import org.example.be.dto.ApiResponse;
import org.example.be.entity.Battery;
import org.example.be.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/batteries")
public class BatteryController {

    @Autowired
    private BatteryService batteryService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createBattery(@RequestBody Battery battery) {
        Battery saved = batteryService.createBattery(battery);
        ApiResponse<Object> response = new ApiResponse<>();
        response.ok(Map.of("battery", saved));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getBatteryById(@PathVariable Integer id) {
        Optional<Battery> b = batteryService.getBatteryById(id);
        ApiResponse<Object> response = new ApiResponse<>();
        if (b.isPresent()) {
            response.ok(Map.of("battery", b.get()));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "Battery not found"));
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllBatteries() {
        List<Battery> list = batteryService.getAllBatteries();
        ApiResponse<Object> response = new ApiResponse<>();
        if (list.isEmpty()) {
            response.error(Map.of("message", "No batteries found"));
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(Map.of("batteries", list));
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateBattery(@PathVariable Integer id, @RequestBody Battery battery) {
        Battery updated = batteryService.updateBattery(id, battery);
        ApiResponse<Object> response = new ApiResponse<>();
        if (updated != null) {
            response.ok(Map.of("battery", updated));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "Battery not found"));
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteBattery(@PathVariable Integer id) {
        boolean deleted = batteryService.deleteBattery(id);
        ApiResponse<Object> response = new ApiResponse<>();
        if (deleted) {
            response.ok(Map.of("message", "Battery deleted successfully"));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "Battery not found"));
            return ResponseEntity.status(404).body(response);
        }
    }
}
