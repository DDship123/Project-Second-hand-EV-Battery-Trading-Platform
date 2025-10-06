package org.example.be.controller;

import org.example.be.dto.ApiResponse;
import org.example.be.dto.PostResponse;
import org.example.be.entity.Post;
import org.example.be.entity.PostImage;
import org.example.be.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
    public ResponseEntity<ApiResponse<?>> createPost(@RequestBody Post post) {
        Post saved = postService.createPost(post);
        ApiResponse<Object> response = new ApiResponse<>();
        response.ok(Map.of(
                "post", mapToResponse(saved)
        ));
        return ResponseEntity.ok(response);
    }

    // --- GET POST BY ID ---
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getPostById(@PathVariable Integer id) {
        Optional<Post> post = postService.getPostById(id);
        ApiResponse<Object> response = new ApiResponse<>();
        if (post.isPresent()) {
            response.ok(Map.of(
                    "post", mapToResponse(post.get())
            ));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "Post not found"));
            return ResponseEntity.status(404).body(response);
        }
    }

    // --- GET ALL POSTS ---
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllPosts() {
        List<PostResponse> posts = postService.getAllPosts().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        ApiResponse<Object> response = new ApiResponse<>();
        if (posts.isEmpty()) {
            response.error(Map.of("message", "No posts found"));
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(Map.of("posts", posts));
            return ResponseEntity.ok(response);
        }
    }

    // --- GET POSTS FOR YOU ---
    @GetMapping("/for-you/{memberId}")
    public ResponseEntity<ApiResponse<?>> getPostsForYou(@PathVariable Integer memberId) {
        List<PostResponse> posts = postService.getPostsForYou(memberId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        ApiResponse<Object> response = new ApiResponse<>();
        if (posts.isEmpty()) {
            response.error(Map.of("message", "No posts found for this member"));
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(Map.of("posts", posts));
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/for-you/{memberId}/status/{status}")
    public ResponseEntity<ApiResponse<?>> getPostsForYouByStatus(
            @PathVariable Integer memberId,
            @PathVariable String status) {
        List<PostResponse> posts = postService.getPostsForYouByStatus(memberId, status).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        ApiResponse<Object> response = new ApiResponse<>();
        if (posts.isEmpty()) {
            response.error(Map.of("message", "No posts found for this member with status: " + status));
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(Map.of("posts", posts));
            return ResponseEntity.ok(response);
        }
    }

    // --- GET POSTS BY MEMBER ---
    @GetMapping("/member/{memberId}")
    public ResponseEntity<ApiResponse<?>> getPostsByMember(@PathVariable Integer memberId) {
        List<PostResponse> posts = postService.getPostsByMember(memberId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        ApiResponse<Object> response = new ApiResponse<>();
        if (posts.isEmpty()) {
            response.error(Map.of("message", "No posts found for this member"));
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(Map.of("posts", posts));
            return ResponseEntity.ok(response);
        }
    }

    // --- GET LATEST POSTS ---
    @GetMapping("/latest")
    public ResponseEntity<ApiResponse<?>> getLatestPosts(@RequestParam(defaultValue = "5") int limit) {
        List<PostResponse> posts = postService.getLatestPosts(limit).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        ApiResponse<Object> response = new ApiResponse<>();
        if (posts.isEmpty()) {
            response.error(Map.of("message", "No latest posts found"));
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(Map.of("posts", posts));
            return ResponseEntity.ok(response);
        }
    }

    // --- UPDATE POST ---
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updatePost(@PathVariable Integer id, @RequestBody Post post) {
        Post updatedPost = postService.updatePost(id, post);
        ApiResponse<Object> response = new ApiResponse<>();
        if (updatedPost != null) {
            response.ok(Map.of(
                    "post", mapToResponse(updatedPost)
            ));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "Post not found"));
            return ResponseEntity.status(404).body(response);
        }
    }

    // --- DELETE POST ---
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deletePost(@PathVariable Integer id) {
        boolean deleted = postService.deletePost(id);
        ApiResponse<Object> response = new ApiResponse<>();
        if (deleted) {
            response.ok(Map.of("message", "Post deleted successfully"));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "Post not found"));
            return ResponseEntity.status(404).body(response);
        }
    }
}
