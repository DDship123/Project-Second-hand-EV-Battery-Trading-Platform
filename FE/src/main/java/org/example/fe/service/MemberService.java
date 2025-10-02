package org.example.fe.service;

import org.example.fe.entity.MemberResponse;
import org.example.fe.model.response.ApiResponse;
import org.example.fe.model.response.MemberRegister;
import org.example.fe.model.response.MemberUpdate;

public interface MemberService {
    ApiResponse<MemberResponse> signIn(String userName, String password);
    ApiResponse<MemberResponse> register(MemberRegister memberRegister);
    ApiResponse<MemberResponse> getMemberInfo(int memberId);
    ApiResponse<MemberResponse> updateMember(MemberUpdate memberUpdate);

}
