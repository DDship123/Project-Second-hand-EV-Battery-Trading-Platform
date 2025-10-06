package org.example.fe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatteryResponse {
    private int batteryId;
    private String condition;
    private String brand;
    private int capacity;

}
