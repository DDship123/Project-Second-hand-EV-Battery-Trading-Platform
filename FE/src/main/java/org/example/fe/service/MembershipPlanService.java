package org.example.fe.service;

import org.example.fe.response.ApiResponse;
import org.example.fe.response.MembershipPlanResponse;

import java.util.List;

public interface MembershipPlanService {
    public ApiResponse<MembershipPlanResponse> getMembershipPlanByMemberId(Integer memberId);
    public ApiResponse<List<MembershipPlanResponse>> getAllMembershipPlans();
}
