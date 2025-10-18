package org.example.be.service;

import org.example.be.dto.reponse.ApiResponse;
import org.example.be.dto.request.LoginRequest;
import org.example.be.dto.request.MemberRegisterRequest;
import org.example.be.entity.Member;
import org.example.be.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.View;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private View error;

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

    public ApiResponse<Member> updateMember(Integer id, Member memberDetails) {
        Member member = getMemberById(id);
        ApiResponse<Member> response = new ApiResponse<>();
        Map<String , String> error = new HashMap<>();
        if (member == null) {
            error.put("message", "Member not found");
            response.error(error);
            return response;
        }
        if(memberRepository.findByUsername(memberDetails.getUsername()).isPresent()){

            error.put("username", "This username is already in use");

        }
        if(memberRepository.findByEmail(memberDetails.getEmail()).isPresent()){

            error.put("email", "This email is already in use");

        }

        if(member.getPhone().matches("^\\d{10,11}$")){
            error.put("phone", "Invalid phone number format!");
        }else if(memberRepository.findByPhone(memberDetails.getPhone()).isPresent()){

            error.put("phone", "This phone number is already in use");

        }

        //New password
        if(member.getPassword().equals(memberDetails.getPassword())){
            error.put("password", "New password and confirm password do not match");
        }

        if (!error.isEmpty()) {
            response.error(error);
            return response;
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
        Member saved = memberRepository.save(member);

        HashMap<String, Object> metadata = new HashMap<>();
        metadata.put("updatedAt", LocalDateTime.now());
        response.ok(saved, metadata);
        return response;
    }

    public void deleteMember(Integer id) {
        memberRepository.deleteById(id);
    }

    public ApiResponse<Member> register(MemberRegisterRequest request) {
        ApiResponse<Member> response = new ApiResponse<>();
        Map<String, String> error = new HashMap<>();

        // Email Exists
        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {

            error.put("email", "This email is already in use");
            response.error(error);
        }

        // Phone Exists and Phone number format
        if(!request.getPhone().matches("^\\d{10,11}$")){
            error.put("phone", "Invalid phone number format!");
            response.error(error);
        } else if (memberRepository.findByPhone(request.getPhone()).isPresent()) {

            error.put("phone", "This phone number is already in use");
            response.error(error);
        }

        // Username Exists
        if (memberRepository.findByUsername(request.getUsername()).isPresent()) {

            error.put("username", "This username is already in use");
            response.error(error);
        }
        if (!error.isEmpty()) {
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