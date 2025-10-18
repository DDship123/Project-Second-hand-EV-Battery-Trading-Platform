package org.example.be.controller;

import org.example.be.dto.reponse.ApiResponse;
import org.example.be.dto.reponse.CommentResponse;
import org.example.be.entity.Comment;
import org.example.be.entity.Member;
import org.example.be.entity.Post;
import org.example.be.service.CommentService;
import org.example.be.service.MemberService;
import org.example.be.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;
    @Autowired
    private MemberService memberService;

//    @PostMapping
//    public ResponseEntity<ApiResponse<CommentResponse>> createComment(@RequestBody CommentResponse com) {
//        ApiResponse<CommentResponse> response = new ApiResponse<>();
//        try {
//            Comment comment1 = new Comment();
//            comment1.setCreatedAt(com.getCreatedAt());
//            comment1.setStatus(com.getStatus());
//            comment1.setComment(com.getComment());
//            comment1.setRating(com.getRating());
//
//            Member member = memberService.getMemberById(com.getMember().getMemberId());
////            member.setMemberId(com.getMember().getMemberId());
////            member.setUsername(com.getMember().getUsername());
////            member.setAvatarUrl(com.getMember().getAvatarUrl());
////            member.setEmail(com.getMember().getEmail());
////            member.setPhone(com.getMember().getPhone());
////            member.setAddress(com.getMember().getAddress());
////            member.setCreatedAt(com.getMember().getCreatedAt());
////            member.setStatus(com.getMember().getStatus());
////            member.setRole(com.getMember().getRole());
////            member.setPassword(com.getMember().getPassword());
////            member.setUsername(com.getMember().getUsername());
////            member.setAvatarUrl(com.getMember().getAvatarUrl());
////            member.setEmail(com.getMember().getEmail());
//
//            comment1.setMember(member);
//            Post post = postService.getPostById(com.getPost().getPostsId()).orElse(null);
//            comment1.setPost(post);
//            Comment created = commentService.createComment(comment1);
//            Map<String, Object> metadata = new HashMap<>();
//            metadata.put("timestamp", LocalDateTime.now());
//            response.ok();
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            Map<String, String> error = new HashMap<>();
//            error.put("message", e.getMessage());
//            response.error(error);
//            return ResponseEntity.badRequest().body(response);
//        }
//    }

    @PostMapping
    public ResponseEntity<ApiResponse<Comment>> createComment(@RequestBody CommentResponse comment) {
        ApiResponse<Comment> response = new ApiResponse<>();
        try {
            // Convert CommentResponse to Comment entity
            Comment comment1 = new Comment();
            comment.setComment(comment.getComment());
            comment.setRating(comment.getRating());
            comment.setStatus(comment.getStatus());
            comment.setCreatedAt(comment.getCreatedAt());

            // Set relationships
            Member member = new Member();
            member.setMemberId(comment.getMember().getMemberId());
            comment1.setMember(member);

            Post post = new Post();
            post.setPostsId(comment.getPost().getPostsId());
            comment1.setPost(post);

            Comment created = commentService.createComment(comment1);
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("timestamp", LocalDateTime.now());
            response.ok(created, (HashMap<String, Object>) metadata);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            response.error(error);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Comment>>> getAllComments() {
        ApiResponse<List<Comment>> response = new ApiResponse<>();
        try {
            List<Comment> comments = commentService.getAllComments();
            response.ok(comments);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            response.error(error);
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Comment>> getCommentById(@PathVariable Integer id) {
        ApiResponse<Comment> response = new ApiResponse<>();
        try {
            Comment comment = commentService.getCommentById(id);
            response.ok(comment);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            response.error(error);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Comment>> updateComment(
            @PathVariable Integer id,
            @RequestBody Comment comment
    ) {
        ApiResponse<Comment> response = new ApiResponse<>();
        try {
            Comment updated = commentService.updateComment(id, comment);
            response.ok(updated);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            response.error(error);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteComment(@PathVariable Integer id) {
        ApiResponse<Boolean> response = new ApiResponse<>();
        try {
            commentService.deleteComment(id);



            response.ok();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = Map.of("message", e.getMessage());
            response.error(error);
            return ResponseEntity.badRequest().body(response);
        }
    }
    // Lấy tất cả bình luận theo ID bài viết
    @GetMapping("/post/{postId}")
    public ResponseEntity<ApiResponse<List<CommentResponse>>> findAllCommentByPostId(@PathVariable Integer postId) {
        ApiResponse<List<CommentResponse>> response = new ApiResponse<>();
        try {
            List<CommentResponse> comments = commentService.findAllCommentByPostId(postId);
            response.ok(comments);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            response.error(error);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
