package org.example.be.service;

import org.example.be.dto.reponse.ApiResponse;
import org.example.be.dto.reponse.MemberResponse;
import org.example.be.dto.reponse.MembershipPlanResponse;
import org.example.be.dto.request.LoginRequest;
import org.example.be.dto.request.MemberRegisterRequest;
import org.example.be.entity.Member;
import org.example.be.entity.MemberPlanUsage;
import org.example.be.entity.MembershipPlan;
import org.example.be.repository.MemberPlanUsageRepository;
import org.example.be.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.View;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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

        Optional<Member> existingUsername = memberRepository.findByUsername(memberDetails.getUsername());
        if(existingUsername.isPresent() && !existingUsername.get().getMemberId().equals(id)) {

            error.put("username", "This username is already in use");

        }

        Optional<Member> existingEmail = memberRepository.findByEmail(memberDetails.getEmail());
        if(existingEmail.isPresent() && !existingEmail.get().getMemberId().equals(id)) {

            error.put("email", "This email is already in use");

        }


        Optional<Member> existingPhone = memberRepository.findByPhone(memberDetails.getPhone());
        if(member.getPhone().matches("^\\d{8,9}$")){
            error.put("phone", "Invalid phone number format!");
        }else if(existingPhone.isPresent() && !existingPhone.get().getMemberId().equals(id)){

            error.put("phone", "This phone number is already in use");

        }

        //New password
//        if(){
//            error.put("password", "New password and confirm password do not match");
//        }
//
        if (!error.isEmpty()) {
            response.error(error);
            return response;
        }

        if(memberDetails.getUsername() != null && !memberDetails.getUsername().isEmpty()){
            member.setUsername(memberDetails.getUsername());
        }
        if(memberDetails.getAddress() != null && !memberDetails.getAddress().isEmpty()){
            member.setAddress(memberDetails.getAddress());
        }
        if(memberDetails.getEmail() != null && !memberDetails.getEmail().isEmpty()){
            member.setEmail(memberDetails.getEmail());
        }
        if(memberDetails.getPhone() != null && !memberDetails.getPhone().isEmpty()){
            member.setPhone(memberDetails.getPhone());
        }
        if(memberDetails.getPassword() != null){
            member.setPassword(memberDetails.getPassword());
        }
        if(memberDetails.getRole() != null && !memberDetails.getRole().isEmpty()){
            member.setRole(memberDetails.getRole());
        }
        if(memberDetails.getStatus() != null && !memberDetails.getStatus().isEmpty()){
            member.setStatus(memberDetails.getStatus());
        }
        if(memberDetails.getCity() != null && !memberDetails.getCity().isEmpty()){
            member.setCity(memberDetails.getCity());
        }
        if(memberDetails.getAvatarUrl() != null && !memberDetails.getAvatarUrl().isEmpty()){
            member.setAvatarUrl(memberDetails.getAvatarUrl());
        }

//        member.setUsername(memberDetails.getUsername());
//        member.setAddress(memberDetails.getAddress());
//        member.setEmail(memberDetails.getEmail());
//        member.setPhone(memberDetails.getPhone());
//        member.setPassword(memberDetails.getPassword());
//        member.setRole(memberDetails.getRole());
//        member.setStatus(memberDetails.getStatus());
//        member.setCreatedAt(memberDetails.getCreatedAt());
//        member.setCity(memberDetails.getCity());
//        member.setAvatarUrl(memberDetails.getAvatarUrl());
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
    //(Tân)
    @Autowired
    private MemberPlanUsageRepository memberPlanUsageRepository;

    public Map<String, MemberResponse> getUsersWithMembershipPlan() {
//        List<Member> users = memberRepository.findAllByRoleAndStatus("USER", status);
        List<Member> users = memberRepository.findAllByRole("USER");
        Map<String, MemberResponse> result = new HashMap<>();

        for (Member member : users) {
            MemberResponse memberResponse = new MemberResponse();
            memberResponse.setMemberId(member.getMemberId());
            memberResponse.setUsername(member.getUsername());
            memberResponse.setEmail(member.getEmail());
            memberResponse.setPhone(member.getPhone());
            memberResponse.setAddress(member.getAddress());
            memberResponse.setCity(member.getCity());
            memberResponse.setRole(member.getRole());
            memberResponse.setStatus(member.getStatus());
            memberResponse.setAvatarUrl(member.getAvatarUrl());
            memberResponse.setCreatedAt(member.getCreatedAt());

            // Lấy MembershipPlan của Member
            Optional<MemberPlanUsage> planUsage = memberPlanUsageRepository.findByMember_MemberIdAndStatus(member.getMemberId(), "active");
//            Optional<MemberPlanUsage> planUsage = memberPlanUsageRepository.findByMember_MemberId(member.getMemberId());
            MembershipPlanResponse planResponse = null;

            if (planUsage.isPresent() && planUsage.get().getMembershipPlan() != null) {
                result.put(planUsage.get().getMembershipPlan().getName()+memberResponse.getMemberId() , memberResponse);
            }else {
                result.put("Chưa đăng ký"+memberResponse.getMemberId(), memberResponse);
            }

        }

        // Stream để sắp xếp theo memberId tăng dần
        List<Map.Entry<String, MemberResponse>> sortedEntries = result.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(entry -> entry.getValue().getMemberId()))
                .collect(Collectors.toList());
        // Nếu muốn kết quả cuối cùng vẫn là Map
        result = sortedEntries.stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        return result;
    }
    //Cập nhật status của member(Tân)
    public ApiResponse<MemberResponse> updateMemberStatus(MemberResponse memberResponse) {
        ApiResponse<MemberResponse> response = new ApiResponse<>();
        try {
            Member member = getMemberById(memberResponse.getMemberId());
            if (member == null) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Member not found");
                response.error(error);
                return response;
            }

            member.setStatus(memberResponse.getStatus());
            Member updated = memberRepository.save(member);

            MemberResponse result = new MemberResponse();
            result.setMemberId(updated.getMemberId());
            result.setUsername(updated.getUsername());
            result.setEmail(updated.getEmail());
            result.setPhone(updated.getPhone());
            result.setAddress(updated.getAddress());
            result.setCity(updated.getCity());
            result.setRole(updated.getRole());
            result.setStatus(updated.getStatus());
            result.setAvatarUrl(updated.getAvatarUrl());
            result.setCreatedAt(updated.getCreatedAt());

            HashMap<String, Object> metadata = new HashMap<>();
            metadata.put("updatedAt", LocalDateTime.now());
            response.ok(result, metadata);
            return response;
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            response.error(error);
            return response;
        }
    }
}