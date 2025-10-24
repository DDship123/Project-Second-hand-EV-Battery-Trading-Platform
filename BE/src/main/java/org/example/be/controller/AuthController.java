package org.example.be.controller;


import org.example.be.dto.response.ApiResponse;
import org.example.be.dto.response.MemberResponse;
import org.example.be.dto.request.LoginRequest;
import org.example.be.dto.request.MemberRegisterRequest;
import org.example.be.entity.Member;
import org.example.be.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private MemberService memberService;



    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Member>> register(@RequestBody MemberRegisterRequest request) {
        ApiResponse<Member> response = memberService.register(request);
        return ResponseEntity.status(response.getStatus().equals("SUCCESS") ? 201 : 400).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginRequest request) {
        Optional<Member> user = memberService.login(request);
        if (user.isPresent()) {
            ApiResponse<Member> response = new ApiResponse<>();
            response.ok(user.get());
            return ResponseEntity.ok(response);
        } else {
            ApiResponse<MemberResponse> response = new ApiResponse<>();
            response.error(Map.of("message", "Invalid username or password"));
            return ResponseEntity.ok(response);
        }
    }
}