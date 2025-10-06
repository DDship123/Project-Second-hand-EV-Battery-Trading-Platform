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
public class CommissionResponse {
    private int commissionId;
    private int transactionId;
    private double commissionRate;
    private double amount;
    private String status;
    private LocalDateTime createdAt;

}
