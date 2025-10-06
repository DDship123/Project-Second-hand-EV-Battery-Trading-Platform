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
public class CommentResponse {
    private int commentId;
    private int postId;
    private int memberId;
    private int rating;
    private String comment;
    private String status;
    private LocalDateTime createdAt;

}
