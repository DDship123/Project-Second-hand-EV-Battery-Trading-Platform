package org.example.fe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {
    @RequestMapping({ "/home"})
    public String homePage(Model model) {
        model.addAttribute("guest", "false");
        return "homePage";
    }
    @RequestMapping("")
    public String guestHomePage(Model model) {
        model.addAttribute("guest", "true");
        return "homePage";
    }
}
