package org.example.be.service;

import org.example.be.entity.Battery;

import java.util.List;
import java.util.Optional;

public interface BatteryService {
    Battery createBattery(Battery battery);
    Optional<Battery> getBatteryById(Integer id);
    List<Battery> getAllBatteries();
    Battery updateBattery(Integer id, Battery updatedBattery);
    boolean deleteBattery(Integer id);
}