package org.example.be.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "battery")
@Data
public class Battery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "battery_id")
    private Integer batteryId;

    private String condition;

    private String brand;

    private Integer capacity;
}