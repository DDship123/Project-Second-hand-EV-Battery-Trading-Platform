package org.example.fe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchController {

    @RequestMapping("/home/vehicle")
    public String vehicle(Model model) {
        model.addAttribute("guest", "false");
        return "vehiclePage";
    }

    @RequestMapping("/home/battery")
    public String battery(Model model) {
        model.addAttribute("guest", "false");
        return "batteryPage";
    }
}
