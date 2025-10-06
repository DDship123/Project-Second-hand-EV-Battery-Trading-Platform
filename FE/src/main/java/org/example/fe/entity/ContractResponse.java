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
public class ContractResponse {
    private int contractId;
    private int transactionId;
    private String contractUrl;
    private LocalDateTime signedAt;
    private String status;
    private LocalDateTime createdAt;

}
