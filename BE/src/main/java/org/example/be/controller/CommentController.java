package org.example.be.controller;

import org.example.be.dto.response.ApiResponse;
import org.example.be.dto.response.CommentResponse;
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


    @PostMapping
    public ResponseEntity<ApiResponse<CommentResponse>> createComment(    @RequestParam("postId") int postId,
                                                                  @RequestParam("memberId") int memberId,
                                                                  @RequestParam("comment") String comment,
                                                                  @RequestParam("rating") int rating,
                                                                  @RequestParam("status") String status) {
        ApiResponse<CommentResponse> response = new ApiResponse<>();
        try {
            Comment comment1 = new Comment();
            comment1.setComment(comment);
            comment1.setRating(rating);
            comment1.setStatus(status);
            comment1.setCreatedAt(LocalDateTime.now());

            // Set relationships
            Member member = memberService.getMemberById(memberId);
            comment1.setMember(member);
            Post post = postService.getPostById(postId).orElse(null);
            comment1.setPost(post);
            Comment created = commentService.createComment(comment1);
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("timestamp", LocalDateTime.now());
            // Convert created Comment entity to CommentResponse DTO
            CommentResponse commentResponse = new CommentResponse();
            commentResponse.setCommentId(created.getCommentId());
            commentResponse.setComment(created.getComment());
            commentResponse.setRating(created.getRating());
            commentResponse.setStatus(created.getStatus());
            commentResponse.setCreatedAt(created.getCreatedAt());
            response.ok(commentResponse, (HashMap<String, Object>) metadata);
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

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<CommentResponse>>> getCommentsByStatus(@PathVariable String status) {
        ApiResponse<List<CommentResponse>> response = new ApiResponse<>();
        try {
            List<CommentResponse> comments = commentService.findAllCommentByStatus(status);
            response.ok(comments);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            response.error(error);
            return ResponseEntity.badRequest().body(response);
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

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<CommentResponse>> updateCommentStatus(
            @PathVariable Integer id,
            @RequestParam("status") String status
    ) {
        ApiResponse<CommentResponse> response = new ApiResponse<>();
        try {
            Comment comment = commentService.getCommentById(id);
            comment.setStatus(status);
            Comment updatedComment = commentService.updateComment(id, comment);
            // Convert updated Comment entity to CommentResponse DTO
            CommentResponse updated = new CommentResponse();
            updated.setCommentId(updatedComment.getCommentId());
            updated.setComment(updatedComment.getComment());
            updated.setRating(updatedComment.getRating());
            updated.setStatus(updatedComment.getStatus());
            updated.setCreatedAt(updatedComment.getCreatedAt());
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
