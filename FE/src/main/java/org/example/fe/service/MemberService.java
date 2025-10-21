package org.example.fe.service;

import org.example.fe.entity.ApiResponse;
import org.example.fe.entity.MemberResponse;

import java.util.List;
import java.util.Map;

public interface MemberService {
    ApiResponse<MemberResponse> signIn(String userName, String password);
    ApiResponse<MemberResponse> register(MemberResponse registerMember);
    ApiResponse<MemberResponse> getMemberInfo(int memberId);
    ApiResponse<MemberResponse> updateMember(MemberResponse updatedMember);
    ApiResponse<Map<String,MemberResponse>> getUser(String status);
    ApiResponse<MemberResponse> updateStatus(MemberResponse member);
}
