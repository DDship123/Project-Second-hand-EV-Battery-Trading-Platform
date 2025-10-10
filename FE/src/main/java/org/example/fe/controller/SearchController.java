package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.entity.ApiResponse;
import org.example.fe.entity.PostResponse;
import org.example.fe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private PostService postService;

    @RequestMapping("/home/vehicle")
    public String vehicle(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        model.addAttribute("guest", "false");
        ApiResponse<List<PostResponse>> apiResponse = postService.getLatestVehiclePosts();
        model.addAttribute("posts", apiResponse.getPayload());
        return "vehiclePage";
    }

    @RequestMapping("/home/battery")
    public String battery(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        model.addAttribute("guest", "false");
        ApiResponse<List<PostResponse>> apiResponse = postService.getLatestBatteryPosts();
        model.addAttribute("posts", apiResponse.getPayload());
        return "batteryPage";
    }
}
