package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.response.ApiResponse;
import org.example.fe.response.MemberResponse;
import org.example.fe.response.PostResponse;
import org.example.fe.response.ReviewResponse;
import org.example.fe.service.PostService;
import org.example.fe.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/product/detail/{postId}")
    public String postDetail(Model model, HttpSession session, @PathVariable int postId) {
        MemberResponse memberResponse = (MemberResponse) session.getAttribute("user");
        model.addAttribute("user", memberResponse);
        model.addAttribute("guest", "false");
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        ApiResponse<PostResponse> apiResponse = postService.getPostDetail(postId);
        ApiResponse<List<ReviewResponse>> reviewResponses = reviewService.FindAllReviewBySellerId(apiResponse.getPayload().getSeller().getMemberId());
        model.addAttribute("reviews", reviewResponses.getPayload());
        model.addAttribute("post", apiResponse.getPayload());
        return "productDetailsPage";
    }
}
