package org.example.fe.service;

import org.example.fe.response.ApiResponse;
import org.example.fe.response.MemberPlanUsageResponse;

public interface MemberPlanUsageService {
    public ApiResponse<MemberPlanUsageResponse> getMemberPlanUsageByMemberId(Integer memberId);
    public ApiResponse<MemberPlanUsageResponse> registerPackage(Integer memberId, Integer planId);
}
