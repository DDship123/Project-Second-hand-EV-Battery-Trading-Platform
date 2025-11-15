package org.example.be.service.impl;

import org.example.be.entity.Battery;
import org.example.be.repository.BatteryRepository;
import org.example.be.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BatteryServiceImpl implements BatteryService {

    @Autowired
    private BatteryRepository batteryRepository;

    @Override
    public Battery createBattery(Battery battery) {
        return batteryRepository.save(battery);
    }

    @Override
    public Optional<Battery> getBatteryById(Integer id) {
        return batteryRepository.findById(id);
    }

    @Override
    public List<Battery> getAllBatteries() {
        return batteryRepository.findAll();
    }

    @Override
    public Battery updateBattery(Integer id, Battery updatedBattery) {
        Optional<Battery> existingBattery = batteryRepository.findById(id);
        if (existingBattery.isPresent()) {
            Battery battery = existingBattery.get();
            battery.setCondition(updatedBattery.getCondition());
            battery.setBrand(updatedBattery.getBrand());
            battery.setCapacityAh(updatedBattery.getCapacityAh());
            return batteryRepository.save(battery);
        }
        return null;
    }

    @Override
    public boolean deleteBattery(Integer id) {
        if (batteryRepository.existsById(id)) {
            batteryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}