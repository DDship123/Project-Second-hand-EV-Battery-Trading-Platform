package org.example.be.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer commentId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "rating")
    private int rating;

    @Column(name = "comment", length = 1000)
    private String comment;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "created_at")
    private String createdAt;


}
