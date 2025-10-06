package org.example.be.controller;

import org.example.be.dto.ApiResponse;
import org.example.be.dto.PostResponse;
import org.example.be.entity.Post;
import org.example.be.entity.PostImage;
import org.example.be.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // --- Helper chuyển Post -> PostResponse ---
    private PostResponse mapToResponse(Post post) {
        List<String> images = post.getPostImages() != null
                ? post.getPostImages().stream().map(PostImage::getImageUrl).collect(Collectors.toList())
                : List.of();

        return new PostResponse(
                post.getPostsId(),
                post.getTitle(),
                post.getDescription(),
                post.getStatus(),
                post.getPrice(),
                post.getCreatedAt(),
                post.getSeller().getMemberId(),
                post.getSeller().getUsername(),
                post.getSeller().getAvatarUrl(),
                post.getProduct().getProductsId(),
                post.getProduct().getName(),
                post.getProduct().getProductType(),
                post.getProduct().getStatus(),
                images
        );
    }

    // --- CREATE POST ---
    @PostMapping
    public ResponseEntity<ApiResponse<PostResponse>> createPost(@RequestBody Post post) {
        Post saved = postService.createPost(post);
        ApiResponse<PostResponse> response = new ApiResponse<>();
        response.ok(mapToResponse(saved));
        return ResponseEntity.ok(response);
    }

    // --- GET POST BY ID ---
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> getPostById(@PathVariable Integer id) {
        Optional<Post> post = postService.getPostById(id);
        ApiResponse<PostResponse> response = new ApiResponse<>();
        if (post.isPresent()) {
            response.ok(mapToResponse(post.get()));
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Post not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }

    // --- GET ALL POSTS ---
    @GetMapping
    public ResponseEntity<ApiResponse<List<PostResponse>>> getAllPosts() {
        List<PostResponse> posts = postService.getAllPosts().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        ApiResponse<List<PostResponse>> response = new ApiResponse<>();
        if (posts.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No posts found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(posts);
            return ResponseEntity.ok(response);
        }
    }

    // --- GET POSTS FOR YOU ---
    @GetMapping("/for-you/{memberId}")
    public ResponseEntity<ApiResponse<List<PostResponse>>> getPostsForYou(@PathVariable Integer memberId) {
        List<PostResponse> posts = postService.getPostsForYou(memberId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        ApiResponse<List<PostResponse>> response = new ApiResponse<>();
        if (posts.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No posts found for this member");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(posts);
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/for-you/{memberId}/status/{status}")
    public ResponseEntity<ApiResponse<List<PostResponse>>> getPostsForYouByStatus(
            @PathVariable Integer memberId,
            @PathVariable String status) {
        List<PostResponse> posts = postService.getPostsForYouByStatus(memberId, status).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        ApiResponse<List<PostResponse>> response = new ApiResponse<>();
        if (posts.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No posts found for this member with status: " + status);
            response.error(error);
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(posts);
            return ResponseEntity.ok(response);
        }
    }

    // --- GET POSTS BY MEMBER ---
    @GetMapping("/member/{memberId}")
    public ResponseEntity<ApiResponse<List<PostResponse>>> getPostsByMember(@PathVariable Integer memberId) {
        List<PostResponse> posts = postService.getPostsByMember(memberId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        ApiResponse<List<PostResponse>> response = new ApiResponse<>();
        if (posts.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No posts found for this member");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(posts);
            return ResponseEntity.ok(response);
        }
    }

    // --- GET LATEST POSTS ---
    @GetMapping("/latest")
    public ResponseEntity<ApiResponse<List<PostResponse>>> getLatestPosts(@RequestParam(defaultValue = "5") int limit) {
        List<PostResponse> posts = postService.getLatestPosts(limit).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        ApiResponse<List<PostResponse>> response = new ApiResponse<>();
        if (posts.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No latest posts found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(posts);
            return ResponseEntity.ok(response);
        }
    }

    // --- UPDATE POST ---
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> updatePost(@PathVariable Integer id, @RequestBody Post post) {
        Post updatedPost = postService.updatePost(id, post);
        ApiResponse<PostResponse> response = new ApiResponse<>();
        if (updatedPost != null) {
            response.ok(mapToResponse(updatedPost));
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Post not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }

    // --- DELETE POST ---
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable Integer id) {
        boolean deleted = postService.deletePost(id);
        ApiResponse<Void> response = new ApiResponse<>();
        if (deleted) {
            response.ok(); // thành công không cần message
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Post not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }
    // --- GET LATEST VEHICLE POSTS ---
    @GetMapping("/latest/vehicle")
    public ResponseEntity<ApiResponse<List<PostResponse>>> getLatestVehiclePosts() {
        List<PostResponse> posts = postService.getLatestVehiclePosts(8).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        ApiResponse<List<PostResponse>> response = new ApiResponse<>();
        if (posts.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No vehicle posts found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(posts);
            return ResponseEntity.ok(response);
        }
    }

    // --- GET LATEST BATTERY POSTS ---
    @GetMapping("/latest/battery")
    public ResponseEntity<ApiResponse<List<PostResponse>>> getLatestBatteryPosts() {
        List<PostResponse> posts = postService.getLatestBatteryPosts(8).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        ApiResponse<List<PostResponse>> response = new ApiResponse<>();
        if (posts.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No battery posts found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(posts);
            return ResponseEntity.ok(response);
        }
    }
}
