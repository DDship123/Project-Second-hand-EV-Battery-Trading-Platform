package org.example.fe.service;

import org.example.fe.response.ApiResponse;
import org.example.fe.response.MemberResponse;

import java.util.List;

public interface MemberService {
    ApiResponse<MemberResponse> signIn(String userName, String password);
    ApiResponse<MemberResponse> register(MemberResponse registerMember);
    ApiResponse<MemberResponse> getMemberInfo(int memberId);
    ApiResponse<MemberResponse> updateMember(MemberResponse updatedMember);
    ApiResponse<List<MemberResponse>> getUser();
    ApiResponse<MemberResponse> updateStatus(MemberResponse member);
}
