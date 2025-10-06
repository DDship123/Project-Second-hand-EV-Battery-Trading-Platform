package org.example.fe.service;

import org.example.fe.entity.MemberResponse;
import org.example.fe.entity.ApiResponse;

public interface MemberService {
    ApiResponse<MemberResponse> signIn(String userName, String password);
    ApiResponse<MemberResponse> register(MemberResponse registerMember);
    ApiResponse<MemberResponse> getMemberInfo(int memberId);
    ApiResponse<MemberResponse> updateMember(MemberResponse updatedMember);

}
