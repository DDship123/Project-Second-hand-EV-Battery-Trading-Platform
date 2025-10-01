package org.example.be.service;

import org.example.be.entity.Member;
import org.example.be.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
