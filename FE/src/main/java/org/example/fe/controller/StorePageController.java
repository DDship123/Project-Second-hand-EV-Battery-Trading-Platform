package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.entity.ApiResponse;
import org.example.fe.entity.MemberResponse;
import org.example.fe.entity.PostResponse;
import org.example.fe.entity.ReviewResponse;
import org.example.fe.service.PostService;
import org.example.fe.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home/store")
public class StorePageController {
    @Autowired
    private PostService postService;
    @Autowired
    private ReviewService reviewService;
    @GetMapping
    public String storePage(Model model, HttpSession session) {
        MemberResponse user = (MemberResponse) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        ApiResponse<List<PostResponse>> response = postService.getAllPostByMemberId(user.getMemberId());
        model.addAttribute("posts", response.getPayload());
        List<ReviewResponse> reviews = reviewService.FindAllReviewBySellerId(user.getMemberId()).getPayload();
        double averageRating = 0.0;
        if (reviews != null && !reviews.isEmpty()) {
            double totalRating = reviews.stream().mapToInt(ReviewResponse::getRating).sum();
            averageRating = totalRating / reviews.size();
        }
        model.addAttribute("averageRating", averageRating);
        model.addAttribute("reviews", reviews);
        return "storePage";
    }
}
