package org.example.be.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "membership_plans")
@Data
public class MembershipPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Integer planId;

    private String name;

    private BigDecimal price;

    private Integer duration;

    @Column(name = "max_posts")
    private Integer maxPosts;

    private String priority;
}