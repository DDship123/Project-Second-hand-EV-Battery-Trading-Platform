package org.example.be.controller;

import org.example.be.dto.response.ApiResponse;
import org.example.be.entity.PostImage;
import org.example.be.service.PostImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/post-images")
public class PostImageController {

    @Autowired
    private PostImageService postImageService;

    @PostMapping
    public ResponseEntity<ApiResponse<PostImage>> createPostImage(@RequestBody PostImage postImage) {
        PostImage saved = postImageService.createPostImage(postImage);
        ApiResponse<PostImage> response = new ApiResponse<>();
        response.ok(saved);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostImage>> getPostImageById(@PathVariable Integer id) {
        Optional<PostImage> postImage = postImageService.getPostImageById(id);
        ApiResponse<PostImage> response = new ApiResponse<>();
        if (postImage.isPresent()) {
            response.ok(postImage.get());
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "PostImage not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostImage>>> getAllPostImages() {
        List<PostImage> postImages = postImageService.getAllPostImages();
        ApiResponse<List<PostImage>> response = new ApiResponse<>();
        if (postImages.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No PostImages found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(postImages);
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PostImage>> updatePostImage(@PathVariable Integer id, @RequestBody PostImage postImage) {
        PostImage updated = postImageService.updatePostImage(id, postImage);
        ApiResponse<PostImage> response = new ApiResponse<>();
        if (updated != null) {
            response.ok(updated);
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "PostImage not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePostImage(@PathVariable Integer id) {
        boolean deleted = postImageService.deletePostImage(id);
        ApiResponse<Void> response = new ApiResponse<>();
        if (deleted) {
            response.ok();
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "PostImage not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }
}
