package org.example.be.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "membership_plans")
public class MembershipPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Integer planId;

    @Column(columnDefinition = "NVARCHAR(50)")
    private String name;

    private BigDecimal price;

    private Integer duration;

    @Column(name = "max_posts")
    private Integer maxPosts;

    @Column(columnDefinition = "NVARCHAR(20)")
    private String priority;

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getMaxPosts() {
        return maxPosts;
    }

    public void setMaxPosts(Integer maxPosts) {
        this.maxPosts = maxPosts;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}