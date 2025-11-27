package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.response.ApiResponse;
import org.example.fe.response.CommentResponse;
import org.example.fe.response.MemberResponse;
import org.example.fe.response.PostResponse;
import org.example.fe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/create/comment")
    public String createComment(@RequestParam("postId") int postId,
                                @RequestParam("rating") int rating,
                                @RequestParam("comment") String comment, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        MemberResponse memberResponse = (MemberResponse) session.getAttribute("user");

        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setMember(memberResponse);
        commentResponse.setRating(rating);
        commentResponse.setComment(comment);
        commentResponse.setStatus("PENDING");
        PostResponse postResponse = new PostResponse();
        postResponse.setPostsId(postId);
        commentResponse.setPost(postResponse);
        ApiResponse<CommentResponse> response = commentService.createComment(commentResponse);
        if (response.getPayload() != null) {
            redirectAttributes.addAttribute("successMessage", "Gửi bình luận thành công.");
        } else {
            redirectAttributes.addAttribute("errorMessage", "Gửi bình luận thất bại. Vui lòng thử lại.");
        }
        return "redirect:/home/product/detail/"+postId;
    }
}
