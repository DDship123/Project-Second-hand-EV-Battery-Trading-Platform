package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.entity.CommentResponse;
import org.example.fe.entity.MemberResponse;
import org.example.fe.entity.PostResponse;
import org.example.fe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/create/comment")
    public String createComment(@RequestParam("postId") int postId,
                                @RequestParam("rating") int rating,
                                @RequestParam("comment") String comment, HttpSession session, Model model) {
        MemberResponse memberResponse = (MemberResponse) session.getAttribute("user");

        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setMember(memberResponse);
        commentResponse.setRating(rating);
        commentResponse.setComment(comment);
        commentResponse.setStatus("PENDING");
        commentResponse.setCreatedAt(java.time.LocalDateTime.now());
        PostResponse postResponse = new PostResponse();
        postResponse.setPostsId(postId);
        commentResponse.setPost(postResponse);
        commentService.createComment(commentResponse);
        return "redirect:/home/product/detail/"+postId;
    }
}
