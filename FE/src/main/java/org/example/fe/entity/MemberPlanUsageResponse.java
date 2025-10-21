package org.example.fe.entity;

import java.time.LocalDate;

public class MemberPlanUsageResponse {
    private Integer usageId;
    private Integer memberId;
    private Integer planId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int usedPosts;
    private String status;

    public MemberPlanUsageResponse() {
    }

    public MemberPlanUsageResponse(Integer usageId, Integer memberId, Integer planId, LocalDate startDate, LocalDate endDate, int usedPosts, String status) {
        this.usageId = usageId;
        this.memberId = memberId;
        this.planId = planId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.usedPosts = usedPosts;
        this.status = status;
    }

    public Integer getUsageId() {
        return usageId;
    }

    public void setUsageId(Integer usageId) {
        this.usageId = usageId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getUsedPosts() {
        return usedPosts;
    }

    public void setUsedPosts(int usedPosts) {
        this.usedPosts = usedPosts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
