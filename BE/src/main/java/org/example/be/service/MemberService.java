package org.example.be.service;

import org.example.be.dto.reponse.ApiResponse;
import org.example.be.dto.request.LoginRequest;
import org.example.be.dto.request.MemberRegisterRequest;
import org.example.be.entity.Member;
import org.example.be.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Integer id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.orElse(null);
    }

    public Member updateMember(Integer id, Member memberDetails) {
        Member member = getMemberById(id);
        if (member == null) {
            return null;
        }
        member.setUsername(memberDetails.getUsername());
        member.setAddress(memberDetails.getAddress());
        member.setEmail(memberDetails.getEmail());
        member.setPhone(memberDetails.getPhone());
        member.setPassword(memberDetails.getPassword());
        member.setRole(memberDetails.getRole());
        member.setStatus(memberDetails.getStatus());
        member.setCreatedAt(memberDetails.getCreatedAt());
        member.setCity(memberDetails.getCity());
        member.setAvatarUrl(memberDetails.getAvatarUrl());
        return memberRepository.save(member);
    }

    public void deleteMember(Integer id) {
        memberRepository.deleteById(id);
    }

    public ApiResponse<Member> register(MemberRegisterRequest request) {
        ApiResponse<Member> response = new ApiResponse<>();

        // Kiểm tra email tồn tại
        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            Map<String, String> error = new HashMap<>();
            error.put("email", "This email is already in use");
            response.error(error);
            return response;
        }

        // Kiểm tra phone tồn tại
        if (memberRepository.findByPhone(request.getPhone()).isPresent()) {
            Map<String, String> error = new HashMap<>();
            error.put("phone", "This phone number is already in use");
            response.error(error);
            return response;
        }

        // Tạo mới member
        Member member = new Member();
        member.setUsername(request.getUsername());
        member.setEmail(request.getEmail());
        member.setPhone(request.getPhone());
        member.setPassword(request.getPassword());
        member.setRole("MEMBER");
        member.setStatus("ACTIVE");
        member.setCreatedAt(LocalDateTime.now());

        Member saved = memberRepository.save(member);


        HashMap<String, Object> metadata = new HashMap<>();
        metadata.put("registeredAt", LocalDateTime.now());

        response.ok(saved, metadata);
        return response;
    }

    public Optional<Member> login (LoginRequest request) {
        return memberRepository.findByUsernameAndPassword(
                request.getUsername(),
                request.getPassword()
        );
    }

    public List<Member> getMembersByStatus(String status) {
        return memberRepository.getMembersByStatus(status);
    }
}