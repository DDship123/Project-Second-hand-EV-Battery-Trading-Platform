package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.entity.ApiResponse;
import org.example.fe.entity.PostResponse;
import org.example.fe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private PostService postService;

    @RequestMapping("/home/vehicle")
    public String vehicle(Model model, HttpSession session ,@RequestParam(value = "location",required = false) String location,
                             @RequestParam(value = "title",required = false) String postTitle) {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        model.addAttribute("guest", "false");

        if (!location.equals("default") && !postTitle.equals("default")) {
            ApiResponse<List<PostResponse>> apiResponse = postService.findAllPostByMemberCityAndProductTypeAndTitle("VEHICLE", location, postTitle);
            model.addAttribute("posts", apiResponse.getPayload());
            model.addAttribute("postTitle", postTitle);
            model.addAttribute("location", location);
            model.addAttribute("productType", "Xe điện");
            return "vehiclePage";
        } else if (!location.equals("default")) {
            ApiResponse<List<PostResponse>> apiResponse = postService.findAllPostByMemberCityAndProductType(location, "VEHICLE");
            model.addAttribute("posts", apiResponse.getPayload());
            model.addAttribute("location", location);
            model.addAttribute("postTitle", "default");
            model.addAttribute("productType", "Xe điện");
            return "vehiclePage";
        }
        model.addAttribute("productType", "Xe điện");
        model.addAttribute("location", "default");
        model.addAttribute("postTitle", "default");
        ApiResponse<List<PostResponse>> apiResponse = postService.getLatestVehiclePosts();
        model.addAttribute("posts", apiResponse.getPayload());
        return "vehiclePage";
    }

    @RequestMapping("/home/battery")
    public String battery(Model model, HttpSession session,@RequestParam(value = "location",required = false) String location,
                          @RequestParam(value = "title",required = false) String postTitle) {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        model.addAttribute("guest", "false");

        if (!location.equals("default") && !postTitle.equals("default")) {
            ApiResponse<List<PostResponse>> apiResponse = postService.findAllPostByMemberCityAndProductTypeAndTitle("BATTERY", location, postTitle);
            model.addAttribute("posts", apiResponse.getPayload());
            model.addAttribute("postTitle", postTitle);
            model.addAttribute("location", location);
            model.addAttribute("productType", "Pin");
            return "batteryPage";
        } else if (!location.equals("default")) {
            ApiResponse<List<PostResponse>> apiResponse = postService.findAllPostByMemberCityAndProductType(location, "BATTERY");
            model.addAttribute("posts", apiResponse.getPayload());
            model.addAttribute("location", location);
            model.addAttribute("postTitle", "default");
            model.addAttribute("productType", "Pin");
            return "batteryPage";
        }
        model.addAttribute("productType", "Pin");
        model.addAttribute("location", "default");
        model.addAttribute("postTitle", "default");
        ApiResponse<List<PostResponse>> apiResponse = postService.getLatestBatteryPosts();
        model.addAttribute("posts", apiResponse.getPayload());
        return "batteryPage";
    }
}
