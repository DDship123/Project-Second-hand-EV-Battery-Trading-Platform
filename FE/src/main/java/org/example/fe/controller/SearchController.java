package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.response.ApiResponse;
import org.example.fe.response.MemberResponse;
import org.example.fe.response.PostResponse;
import org.example.fe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private PostService postService;

    @RequestMapping("/home/vehicle")
    public String vehicle(Model model, HttpSession session ,@RequestParam(name = "location",defaultValue = "default",required = false) String location,
                             @RequestParam(name = "title",defaultValue = "default",required = false) String postTitle,
                          @RequestParam(name = "successMessage", required = false) String successMessage,
                          @RequestParam(name = "errorMessage", required = false) String errorMessage) {
        MemberResponse memberResponse = (MemberResponse) session.getAttribute("user");
        if (memberResponse == null){
            model.addAttribute("guest", "true");
        }else{
            model.addAttribute("user", memberResponse);
            model.addAttribute("guest", "false");
            model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        }

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
            model.addAttribute("postTitle", postTitle);
            model.addAttribute("productType", "Xe điện");
            model.addAttribute("totalPage", apiResponse.getMetadata().get("totalPages"));
            return "vehiclePage";
        }
        model.addAttribute("productType", "Xe điện");
        model.addAttribute("location", location);
        model.addAttribute("postTitle", postTitle);
        ApiResponse<List<PostResponse>> apiResponse = postService.getLatestVehiclePosts();
        model.addAttribute("posts", apiResponse.getPayload());
        model.addAttribute("totalPage", apiResponse.getMetadata().get("totalPages"));
        if (successMessage != null) {
            model.addAttribute("successMessage", successMessage);
        }
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }
        return "vehiclePage";
    }

    @RequestMapping("/home/battery")
    public String battery(Model model, HttpSession session,@RequestParam(name = "location",defaultValue = "default",required = false) String location,
                          @RequestParam(name = "title",defaultValue = "default",required = false) String postTitle,
                          @RequestParam(name = "successMessage", required = false) String successMessage,
                          @RequestParam(name = "errorMessage", required = false) String errorMessage) {
        MemberResponse memberResponse = (MemberResponse) session.getAttribute("user");
        if (memberResponse == null){
            model.addAttribute("guest", "true");
        }else{
            model.addAttribute("user", memberResponse);
            model.addAttribute("guest", "false");
            model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        }
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
            model.addAttribute("postTitle", postTitle);
            model.addAttribute("productType", "Pin");
            model.addAttribute("totalPage", apiResponse.getMetadata().get("totalPages"));
            return "batteryPage";
        }
        model.addAttribute("productType", "Pin");
        model.addAttribute("location", location);
        model.addAttribute("postTitle", postTitle);
        ApiResponse<List<PostResponse>> apiResponse = postService.getLatestBatteryPosts();
        model.addAttribute("posts", apiResponse.getPayload());
        model.addAttribute("totalPage", apiResponse.getMetadata().get("totalPages"));
        if (successMessage != null) {
            model.addAttribute("successMessage", successMessage);
        }
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }
        return "batteryPage";
    }
}
