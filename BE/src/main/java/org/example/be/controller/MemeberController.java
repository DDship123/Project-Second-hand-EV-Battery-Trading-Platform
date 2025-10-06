package org.example.be.controller;

import org.example.be.dto.ApiResponse;
import org.example.be.entity.Member;
import org.example.be.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemeberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return memberService.createMember(member);
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Integer id) {
        return memberService.getMemberById(id);
    }

    @ PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Member>> updateMember(@PathVariable Integer id, @RequestBody Member member) {
        Member updatedMember = memberService.updateMember(id, member);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.ok(updatedMember);
        if (updatedMember != null) {
            return ResponseEntity.ok(apiResponse);
        } else {

            return ResponseEntity.status(404).body(ApiResponse.failure(404, "Member not found"));
        }
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Integer id) {
        memberService.deleteMember(id);
    }
}
