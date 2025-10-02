package org.example.be.service;

import org.example.be.dto.ApiResponse;
import org.example.be.dto.MemberRegisterRequest;
import org.example.be.entity.Member;
import org.example.be.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
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
        return memberRepository.save(member);
    }

    public void deleteMember(Integer id) {
        memberRepository.deleteById(id);
    }

    public ApiResponse<Member> register(MemberRegisterRequest request) {
        // Check email/phone đã tồn tại
        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            return ApiResponse.error(400, "Email already exists", new HashMap<>() {{
                put("email", "This email is already in use");
            }});
        }
        if (memberRepository.findByPhone(request.getPhone()).isPresent()) {
            return ApiResponse.error(400, "Phone already exists", new HashMap<>() {{
                put("phone", "This phone is already in use");
            }});
        }

        // Tạo mới Member
        Member member = new Member();
        member.setUsername(request.getUsername());
        member.setAddress(request.getAddress());
        member.setEmail(request.getEmail());
        member.setPhone(request.getPhone());
        member.setPassword(request.getPassword());
        member.setRole("MEMBER");
        member.setStatus("ACTIVE");
        member.setCreatedAt(LocalDateTime.now());

        Member saved = memberRepository.save(member);

        return ApiResponse.ok(saved);
    }
}
