package org.example.fe.service;

import org.example.fe.entity.ApiResponse;
import org.example.fe.entity.MemberResponse;

import java.util.List;

public interface MemberService {
    ApiResponse<MemberResponse> signIn(String userName, String password);
    ApiResponse<MemberResponse> register(MemberResponse registerMember);
    ApiResponse<MemberResponse> getMemberInfo(int memberId);
    ApiResponse<MemberResponse> updateMember(MemberResponse updatedMember);

}
