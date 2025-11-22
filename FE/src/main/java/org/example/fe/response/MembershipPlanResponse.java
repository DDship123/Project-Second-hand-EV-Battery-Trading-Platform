package org.example.fe.response;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class MembershipPlanResponse {
    private Integer planId;
    private String name;
    private BigDecimal price;
    private int duration;
    private int maxPosts;
    private String priority;

    private String status;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPlanId() {
        return planId;
    }

    public MembershipPlanResponse() {
    }

    public MembershipPlanResponse(Integer planId, String name, BigDecimal price, Integer duration, Integer maxPosts, String priority, String status) {
        this.planId = planId;
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.maxPosts = maxPosts;
        this.priority = priority;
        this.status = status;
    }

    public String getPriceFormated() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("###,###", symbols);
        return decimalFormat.format(price);  // Format BigDecimal thành String
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
