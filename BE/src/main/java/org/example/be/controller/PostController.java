package org.example.be.controller;

import org.example.be.dto.PostResponse;
import org.example.be.entity.Post;
import org.example.be.entity.PostImage;
import org.example.be.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // helper chuyển Post -> PostResponse
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

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody Post post) {
        Post saved = postService.createPost(post);
        return ResponseEntity.ok(mapToResponse(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Integer id) {
        Optional<Post> post = postService.getPostById(id);
        return post.map(value -> ResponseEntity.ok(mapToResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        List<PostResponse> posts = postService.getAllPosts().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Integer id, @RequestBody Post post) {
        Post updatedPost = postService.updatePost(id, post);
        if (updatedPost != null) {
            return ResponseEntity.ok(mapToResponse(updatedPost));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    // --- For You ---
    @GetMapping("/for-you/{memberId}")
    public ResponseEntity<List<PostResponse>> getPostsForYou(@PathVariable Integer memberId) {
        List<PostResponse> posts = postService.getPostsForYou(memberId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/for-you/{memberId}/status/{status}")
    public ResponseEntity<List<PostResponse>> getPostsForYouByStatus(
            @PathVariable Integer memberId,
            @PathVariable String status) {
        List<PostResponse> posts = postService.getPostsForYouByStatus(memberId, status).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<PostResponse>> getPostsByMember(@PathVariable Integer memberId) {
        List<PostResponse> posts = postService.getPostsByMember(memberId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/latest")
    public ResponseEntity<List<Post>> getLatestPosts(
            @RequestParam(defaultValue = "5") int limit) {
        List<Post> latestPosts = postService.getLatestPosts(limit);
        return ResponseEntity.ok(latestPosts);
    }

}
