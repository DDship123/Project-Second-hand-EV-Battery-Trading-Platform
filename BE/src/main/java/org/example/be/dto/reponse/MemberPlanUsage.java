package org.example.be.dto.reponse;

import java.time.LocalDateTime;

public class MemberPlanUsage {
    private int usageId;
    private int memberId;
    private int planId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int usedPosts;
    private String status;

    public MemberPlanUsage() {
    }

    public MemberPlanUsage(int usageId, int memberId, int planId, LocalDateTime startDate, LocalDateTime endDate, int usedPosts, String status) {
        this.usageId = usageId;
        this.memberId = memberId;
        this.planId = planId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.usedPosts = usedPosts;
        this.status = status;
    }

    public int getUsageId() {
        return usageId;
    }

    public void setUsageId(int usageId) {
        this.usageId = usageId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
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
