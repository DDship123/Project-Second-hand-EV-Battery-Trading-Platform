package org.example.be.dto.response;

import java.math.BigDecimal;

public class MembershipPlanResponse {
    private Integer planId;
    private String name;
    private BigDecimal price;
    private Integer duration;
    private Integer maxPosts;
    private String priority;

    public MembershipPlanResponse() {
    }

    public MembershipPlanResponse(Integer planId, String name, BigDecimal price, Integer duration, Integer maxPosts, String priority) {
        this.planId = planId;
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.maxPosts = maxPosts;
        this.priority = priority;
    }

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