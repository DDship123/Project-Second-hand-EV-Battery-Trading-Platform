package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.entity.ApiResponse;
import org.example.fe.entity.MemberResponse;
import org.example.fe.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    private MemberService memberService;

    @RequestMapping("")
    public String signup() {
        return "signUp";
    }

    @PostMapping("")
    public String handleSignUp(HttpSession session, @RequestParam(name = "username") String username,
                               @RequestParam(name = "email") String email,
                               @RequestParam(name = "password") String password,
                               @RequestParam(name = "phone") String phone) {
        MemberResponse register = new MemberResponse();
        register.setUsername(username);
        register.setEmail(email);
        register.setPassword(password);
        register.setPhone(phone);
        ApiResponse<MemberResponse> apiResponse = memberService.register(register);
        if (apiResponse.getStatus().equals("SUCCESS")) {
            MemberResponse memberResponse = apiResponse.getPayload();
            session.setAttribute("user", memberResponse);
        } else {
            return "redirect:/signup?error=true";
        }
        return "redirect:/home";

    }
}
