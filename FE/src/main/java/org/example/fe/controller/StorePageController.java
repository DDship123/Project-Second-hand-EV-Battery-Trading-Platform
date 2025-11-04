package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.response.*;
import org.example.fe.service.PostService;
import org.example.fe.service.ReviewService;
import org.example.fe.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home/store")
public class StorePageController {
    @Autowired
    private PostService postService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public String storePage(Model model, HttpSession session) {
        MemberResponse user = (MemberResponse) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        ApiResponse<List<PostResponse>> response = postService.getAllPostByMemberId(user.getMemberId());
        if (response.getPayload() == null || response.getPayload().isEmpty()) {
            model.addAttribute("noPostMessage", "Bạn chưa có bài đăng nào. Hãy tạo bài đăng để bắt đầu bán hàng!");
        }
        model.addAttribute("posts", response.getPayload());
        List<ReviewResponse> reviews = reviewService.FindAllReviewBySellerId(user.getMemberId()).getPayload();
        double averageRating = 0.0;
        if (reviews != null && !reviews.isEmpty()) {
            double totalRating = reviews.stream().mapToInt(ReviewResponse::getRating).sum();
            averageRating = totalRating / reviews.size();
        }
        model.addAttribute("averageRating", averageRating);
        model.addAttribute("reviews", reviews);

        ApiResponse<List<TransactionResponse>> transactionResponse = transactionService.getAllSellTransaction(user.getMemberId());
        model.addAttribute("transactions", transactionResponse.getPayload());
        return "storePage";
    }


    @GetMapping("/deletePost/{postId}")
    public String deletePost(Model model, HttpSession session, @PathVariable("postId") Integer postId) {
        ApiResponse<PostResponse> response = postService.delete(postId);
        return "redirect:/home/store";
    }

}
