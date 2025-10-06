package org.example.fe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private int productId;
    private int memberId;
    private String productType;
    private int vehicleId;
    private int batteryId;
    private String productName;
    private String description;
    private String status;
    private LocalDateTime createdAt;

}
