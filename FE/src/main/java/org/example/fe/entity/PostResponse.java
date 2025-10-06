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
public class PostResponse {
    private int postId;
    private int productId;
    private int memberId;
    private String title;
    private String description;
    private String status;
    private double price;
    private LocalDateTime createdAt;

}
