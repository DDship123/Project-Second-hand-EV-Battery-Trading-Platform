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
public class TransactionResponse {
    private int transactionId;
    private int buyerId;
    private int sellerId;
    private int postId;
    private String status;
    private LocalDateTime createdAt;

}
