package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.config.CloudinaryService;
import org.example.fe.entity.ApiResponse;
import org.example.fe.entity.MemberResponse;
import org.example.fe.entity.PostResponse;
import org.example.fe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/home")
public class updatePostController {
    @Autowired
    private PostService postService;
    @Autowired
    private CloudinaryService cloudinaryService;

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
    public String updatePost(Model model,
                             PostResponse updatedPost, HttpSession session, @RequestParam("mainImage") MultipartFile mainImage,
                             @RequestParam(value = "subImages",required = false) List<MultipartFile> subImages) {
        try {
            if (mainImage != null && !mainImage.isEmpty()) {
               String mainImageUrl = cloudinaryService.uploadImage(mainImage);
               updatedPost.getImages().set(0, mainImageUrl);
            }
            if (subImages != null && !subImages.isEmpty()) {
                for (int i = 0; i < subImages.size(); i++) {
                    MultipartFile subImage = subImages.get(i);
                    if (subImage != null && !subImage.isEmpty()) {
                        String subImageUrl = cloudinaryService.uploadImage(subImage);
                        if (i + 1 < updatedPost.getImages().size()) {
                            updatedPost.getImages().set(i + 1, subImageUrl);
                        } else {
                            updatedPost.getImages().add(subImageUrl);
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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
