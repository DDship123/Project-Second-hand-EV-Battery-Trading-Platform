package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.response.*;
import org.example.fe.service.PostService;
import org.example.fe.service.ReviewService;
import org.example.fe.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/home")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private WishlistService wishlistService;

    @GetMapping("/product/detail/{postId}")
    public String postDetail(Model model, HttpSession session, @PathVariable int postId,
                             @RequestParam(name = "successMessage", required = false) String successMessage,
                             @RequestParam(name = "errorMessage", required = false) String errorMessage) {

        boolean isFavorite;
        MemberResponse memberResponse = (MemberResponse) session.getAttribute("user");

        if (memberResponse == null){
            model.addAttribute("guest", "true");
        } else {
            ApiResponse<List<FavoriteResponse>> all = wishlistService.getAll(memberResponse.getMemberId());

            isFavorite = all.getPayload().stream()
                    .anyMatch(f -> f.getPost().getPostsId().equals(postId));

            model.addAttribute("user", memberResponse);
            model.addAttribute("guest", "false");
            model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
            model.addAttribute("isFavorite", isFavorite);
        }

        ApiResponse<PostResponse> apiResponse = postService.getPostDetail(postId);
        ApiResponse<List<ReviewResponse>> reviewResponses =
                reviewService.FindAllReviewBySellerId(apiResponse.getPayload().getSeller().getMemberId());

        model.addAttribute("reviews", reviewResponses.getPayload());
        model.addAttribute("post", apiResponse.getPayload());

        if (successMessage != null) model.addAttribute("successMessage", successMessage);
        if (errorMessage != null) model.addAttribute("errorMessage", errorMessage);

        return "productDetailsPage";
    }

}
