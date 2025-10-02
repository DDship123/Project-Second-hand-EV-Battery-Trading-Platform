package org.example.be.controller;


import lombok.RequiredArgsConstructor;
import org.example.be.dto.ApiResponse;
import org.example.be.dto.MemberRegisterRequest;
import org.example.be.entity.Member;
import org.example.be.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Member>> register(@RequestBody MemberRegisterRequest request) {
        ApiResponse<Member> response = memberService.register(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
