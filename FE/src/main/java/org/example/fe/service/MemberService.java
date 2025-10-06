package org.example.fe.service;

import org.example.fe.entity.MemberResponse;
import org.example.fe.model.response.ApiResponse;
import org.example.fe.model.MemberRegisterRequest;
import org.example.fe.model.MemberUpdateRequest;

public interface MemberService {
    ApiResponse<MemberResponse> signIn(String userName, String password);
    ApiResponse<MemberResponse> register(MemberResponse registerMember);
    ApiResponse<MemberResponse> getMemberInfo(int memberId);
    ApiResponse<MemberResponse> updateMember(MemberResponse updatedMember);

}
