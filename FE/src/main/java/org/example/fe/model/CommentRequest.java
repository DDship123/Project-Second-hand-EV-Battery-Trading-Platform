package org.example.fe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {
    private int postId;
    private int memberId;
    private String comment;
}
