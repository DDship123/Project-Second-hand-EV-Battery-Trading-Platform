package org.example.be.controller;

import org.example.be.dto.reponse.ApiResponse;
import org.example.be.entity.Comment;
import org.example.be.service.CommentService;
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

    @PostMapping
    public ResponseEntity<ApiResponse<Comment>> createComment(@RequestBody Comment comment) {
        ApiResponse<Comment> response = new ApiResponse<>();
        try {
            Comment created = commentService.createComment(comment);
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
    public ResponseEntity<ApiResponse<List<Comment>>> findAllCommentByPostId(@PathVariable Integer postId) {
        ApiResponse<List<Comment>> response = new ApiResponse<>();
        try {
            List<Comment> comments = commentService.findAllCommentByPostId(postId);
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
