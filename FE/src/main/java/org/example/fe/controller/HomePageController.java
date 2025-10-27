package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.response.ApiResponse;
import org.example.fe.response.FavoriteResponse;
import org.example.fe.response.MemberResponse;
import org.example.fe.response.PostResponse;
import org.example.fe.service.PostService;
import org.example.fe.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomePageController {
    @Autowired
    private PostService postService;
    @Autowired
    private WishlistService wishlistService;

    @RequestMapping({ "/home"})
    public String homePage(Model model, HttpSession session) {
        MemberResponse memberResponse = (MemberResponse) session.getAttribute("user");
        if (memberResponse == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", memberResponse);
        model.addAttribute("guest", "false");

        ApiResponse<List<PostResponse>> apiResponse = postService.getLatestPost();
        model.addAttribute("posts", apiResponse.getPayload());
        ApiResponse<FavoriteResponse> firstFavorite = wishlistService.getFist(memberResponse.getMemberId());
        session.setAttribute("firstFavorite", firstFavorite.getPayload());
        model.addAttribute("firstFavorite", firstFavorite.getPayload());
        return "homePage";
    }

    @RequestMapping("")
    public String guestHomePage(Model model) {
        model.addAttribute("guest", "true");
        ApiResponse<List<PostResponse>> apiResponse = postService.getLatestPost();
        model.addAttribute("posts", apiResponse.getPayload());
        return "homePage";
    }
}
