package org.example.be.dto.reponse;

import org.example.be.entity.Member;
import org.example.be.entity.MembershipPlan;

import java.time.LocalDateTime;

public class MemberPlanUsage {
    private int usageId;
    private Member member;
    private MembershipPlan plan;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int usedPosts;
    private String status;

    public MemberPlanUsage() {
    }

    public MemberPlanUsage(int usageId, Member member, MembershipPlan plan, LocalDateTime startDate, LocalDateTime endDate, int usedPosts, String status) {
        this.usageId = usageId;
        this.member = member;
        this.plan = plan;
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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public MembershipPlan getPlan() {
        return plan;
    }

    public void setPlan(MembershipPlan plan) {
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
