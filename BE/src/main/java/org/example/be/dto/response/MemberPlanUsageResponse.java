package org.example.be.dto.response;

import org.example.be.entity.MembershipPlan;

import java.time.LocalDateTime;

public class MemberPlanUsageResponse {
    private int usageId;
    private MemberResponse member;
    private MembershipPlanResponse plan;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;

    public MemberPlanUsageResponse() {
    }

    public MemberPlanUsageResponse(MemberResponse member, MembershipPlanResponse plan, LocalDateTime startDate, LocalDateTime endDate, String status) {
        this.member = member;
        this.plan = plan;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public int getUsageId() {
        return usageId;
    }

    public void setUsageId(int usageId) {
        this.usageId = usageId;
    }

    public MemberResponse getMember() {
        return member;
    }

    public void setMember(MemberResponse member) {
        this.member = member;
    }

    public MembershipPlanResponse getPlan() {
        return plan;
    }

    public void setPlan(MembershipPlanResponse plan) {
        this.plan = plan;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
