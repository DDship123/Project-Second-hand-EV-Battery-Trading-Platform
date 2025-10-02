package org.example.be.controller;

import org.example.be.entity.Battery;
import org.example.be.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/batteries")
public class BatteryController {

    @Autowired
    private BatteryService batteryService;

    @PostMapping
    public ResponseEntity<Battery> createBattery(@RequestBody Battery battery) {
        return ResponseEntity.ok(batteryService.createBattery(battery));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Battery> getBatteryById(@PathVariable Integer id) {
        Optional<Battery> battery = batteryService.getBatteryById(id);
        return battery.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Battery>> getAllBatteries() {
        List<Battery> batteries = batteryService.getAllBatteries();
        return ResponseEntity.ok(batteries);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Battery> updateBattery(@PathVariable Integer id, @RequestBody Battery battery) {
        Battery updatedBattery = batteryService.updateBattery(id, battery);
        if (updatedBattery != null) {
            return ResponseEntity.ok(updatedBattery);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBattery(@PathVariable Integer id) {
        batteryService.deleteBattery(id);
        return ResponseEntity.noContent().build();
    }
}