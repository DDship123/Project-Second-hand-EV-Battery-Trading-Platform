package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.entity.ApiResponse;
import org.example.fe.entity.MemberResponse;
import org.example.fe.entity.PostResponse;
import org.example.fe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class updatePostController {
    @Autowired
    private PostService postService;
    @GetMapping("/store/updatePost/{postId}")
    public String showUpdatePostPage(Model model, HttpSession session, @PathVariable("postId") Integer postId) {
        MemberResponse user = (MemberResponse) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        ApiResponse<PostResponse> response = postService.getPostDetail(postId);
        model.addAttribute("post", response.getPayload());
        return "updatePostPage";
    }

    @PostMapping("/store/updatePost")
    public String updatePostBattery(Model model,
                                    PostResponse updatedPost,HttpSession session) {
        ApiResponse<PostResponse> response = postService.update( updatedPost);
        if (response.getPayload() != null) {
            return "redirect:/home/store";
        } else {
            model.addAttribute("error", response.getError());
            model.addAttribute("post", updatedPost);
            MemberResponse user = (MemberResponse) session.getAttribute("user");
            model.addAttribute("user", user);
            model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
            return "updatePostPage";
        }
    }
}
