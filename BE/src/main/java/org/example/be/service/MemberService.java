package org.example.be.service;

import org.example.be.dto.request.LoginRequest;
import org.example.be.dto.request.MemberRegisterRequest;
import org.example.be.dto.response.ApiResponse;
import org.example.be.dto.response.MemberResponse;
import org.example.be.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    public Member createMember(Member member);
    public List<Member> getAllMembers();
    public Member getMemberById(Integer id);
    public int countMemberByRole(String role);
    public ApiResponse<Member> updateMember(Integer id, Member memberDetails);
    public void deleteMember(Integer id);
    public ApiResponse<Member> register(MemberRegisterRequest request);
    public Optional<Member> login (LoginRequest request);
    public List<Member> getMembersByStatus(String status);
    public List<MemberResponse> getUsersWithMembershipPlan();
    public ApiResponse<MemberResponse> updateMemberStatus(MemberResponse memberResponse);

}
