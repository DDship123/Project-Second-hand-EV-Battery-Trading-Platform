package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.response.ApiResponse;
import org.example.fe.response.MemberResponse;
import org.example.fe.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

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
                               @RequestParam(name = "phone") String phone, Model model) {
        MemberResponse register = new MemberResponse();
        register.setUsername(username);
        register.setEmail(email);
        register.setPassword(password);
        register.setPhone(phone);

        model.addAttribute("username", username);
        model.addAttribute("email", email);
        model.addAttribute("phone", phone);

        ApiResponse<MemberResponse> apiResponse = memberService.register(register);
        if (apiResponse.getStatus().equals("SUCCESS")) {
            MemberResponse memberResponse = apiResponse.getPayload();
            session.setAttribute("user", memberResponse);
            return "redirect:/home";
        } else {
            Map<String, String> errorMap = apiResponse.getError();

            if (!errorMap.isEmpty()) {
                if (errorMap.containsKey("email")) {
                    model.addAttribute("emailError", errorMap.get("email"));
                }
                if (errorMap.containsKey("username")) {
                    model.addAttribute("usernameError", errorMap.get("username"));
                }
                if (errorMap.containsKey("phone")) {
                    model.addAttribute("phoneError", errorMap.get("phone"));
                }
                if (errorMap.containsKey("message")) {
                    model.addAttribute("message", errorMap.get("message"));
                }
            }
            return "signUp";
            }
        }
    }

