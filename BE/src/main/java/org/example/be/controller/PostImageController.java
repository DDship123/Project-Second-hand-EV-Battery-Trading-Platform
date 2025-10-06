package org.example.be.controller;

import org.example.be.dto.ApiResponse;
import org.example.be.entity.PostImage;
import org.example.be.service.PostImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/post-images")
public class PostImageController {

    @Autowired
    private PostImageService postImageService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createPostImage(@RequestBody PostImage postImage) {
        PostImage saved = postImageService.createPostImage(postImage);
        ApiResponse<Object> response = new ApiResponse<>();
        response.ok(Map.of("postImage", saved));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getPostImageById(@PathVariable Integer id) {
        Optional<PostImage> postImage = postImageService.getPostImageById(id);
        ApiResponse<Object> response = new ApiResponse<>();
        if (postImage.isPresent()) {
            response.ok(Map.of("postImage", postImage.get()));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "PostImage not found"));
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllPostImages() {
        List<PostImage> postImages = postImageService.getAllPostImages();
        ApiResponse<Object> response = new ApiResponse<>();
        if (postImages.isEmpty()) {
            response.error(Map.of("message", "No PostImages found"));
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(Map.of("postImages", postImages));
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updatePostImage(@PathVariable Integer id, @RequestBody PostImage postImage) {
        PostImage updated = postImageService.updatePostImage(id, postImage);
        ApiResponse<Object> response = new ApiResponse<>();
        if (updated != null) {
            response.ok(Map.of("postImage", updated));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "PostImage not found"));
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deletePostImage(@PathVariable Integer id) {
        boolean deleted = postImageService.deletePostImage(id);
        ApiResponse<Object> response = new ApiResponse<>();
        if (deleted) {
            response.ok(Map.of("message", "PostImage deleted successfully"));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "PostImage not found"));
            return ResponseEntity.status(404).body(response);
        }
    }
}
