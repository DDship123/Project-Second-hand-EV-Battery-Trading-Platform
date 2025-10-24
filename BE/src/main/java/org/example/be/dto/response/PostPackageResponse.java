package org.example.be.dto.response;

public class PostPackageResponse {
    private int planId;
    private String name;
    private double price;
    private int duration;
    private int maxPosts;
    private String priority;

    public PostPackageResponse() {
    }

    public PostPackageResponse(int planId, String name, double price, int duration, int maxPosts, String priority) {
        this.planId = planId;
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.maxPosts = maxPosts;
        this.priority = priority;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getMaxPosts() {
        return maxPosts;
    }

    public void setMaxPosts(int maxPosts) {
        this.maxPosts = maxPosts;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
