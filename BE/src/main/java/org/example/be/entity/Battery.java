package org.example.be.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "battery")
@Data
@Getter
@Setter
public class Battery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "battery_id")
    private Integer batteryId;

    private String condition;

    private String brand;

    private Integer capacity;
}