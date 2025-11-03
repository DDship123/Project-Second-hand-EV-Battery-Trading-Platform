package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.config.CloudinaryService;
import org.example.fe.response.*;
import org.example.fe.service.PostService;
import org.example.fe.validate.UpdatePostValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
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
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        ApiResponse<PostResponse> response = postService.getPostDetail(postId);
        response.getPayload().setPriceInput(response.getPayload().getPrice().toString());
        session.setAttribute("postCurr", response.getPayload());
        model.addAttribute("post", response.getPayload());
        return "updatePostPage";
    }

    @PostMapping("/store/updatePost")
    public String updatePost(Model model,
                             PostResponse updatedPost, HttpSession session, @RequestParam("mainImage") MultipartFile mainImage,
                             @RequestParam(value = "subImages",required = false) List<MultipartFile> subImages) {
        PostResponse post = (PostResponse) session.getAttribute("postCurr");
        UpdatePostValidate validate = new UpdatePostValidate();
        boolean hasError = validate.errorUpdate(model, post, updatedPost);

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



        if(hasError){
            model.addAttribute("post", updatedPost);
            MemberResponse user = (MemberResponse) session.getAttribute("user");
            model.addAttribute("user", user);
            model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
            return "updatePostPage";
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
