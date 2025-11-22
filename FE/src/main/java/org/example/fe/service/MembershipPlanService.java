package org.example.fe.service;

import org.example.fe.response.ApiResponse;
import org.example.fe.response.MembershipPlanResponse;

import java.util.List;

public interface MembershipPlanService {
    public ApiResponse<MembershipPlanResponse> getMembershipPlanByMemberId(Integer memberId);
    public ApiResponse<List<MembershipPlanResponse>> getAllMembershipPlans();
    public ApiResponse<MembershipPlanResponse> updateMembershipPlanStatus(Integer planId, String status);
    public ApiResponse<MembershipPlanResponse> createMembershipPlan(MembershipPlanResponse plan);
    public ApiResponse<MembershipPlanResponse> getMembershipPlanById(Integer planId);
    public ApiResponse<MembershipPlanResponse> updateMembershipPlan(Integer planId, MembershipPlanResponse plan);
}
