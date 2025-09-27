package org.example.fe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginInController {
    @RequestMapping("")
    public String login() {
        return "loginIn";
    }
}
