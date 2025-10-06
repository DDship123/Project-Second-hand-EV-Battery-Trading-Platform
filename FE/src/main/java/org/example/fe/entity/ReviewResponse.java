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
public class ReviewResponse {
    private int reviewId;
    private int sellerId;
    private int reviewerId;
    private int transactionId;
    private int rating;
    private String comment;
    private String status;
    private LocalDateTime createdAt;

}
