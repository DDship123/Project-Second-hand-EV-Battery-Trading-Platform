package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.entity.MemberResponse;
import org.example.fe.model.response.ApiResponse;
import org.example.fe.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginInController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("")
    public String login() {
        return "loginIn";
    }

    @PostMapping("/submit")
    public String submitLogin(@RequestParam(name = "username") String userName,
                              @RequestParam(name = "password") String password,
                              HttpSession session) {
        ApiResponse<MemberResponse> isAuthenticated = memberService.signIn(userName, password);
        if (isAuthenticated.getStatus().equals("SUCCESS")) {
            // Store user information in session
            session.setAttribute("user", isAuthenticated.getPayload());
            // Redirect to a success page or dashboard
            return "redirect:/home";
        } else {
            // Redirect back to login with an error message
            return "redirect:/login";
        }
    }
}
