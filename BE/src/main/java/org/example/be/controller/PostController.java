package org.example.be.controller;

import org.example.be.entity.Post;
import org.example.be.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.createPost(post));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer id) {
        Optional<Post> post = postService.getPostById(id);
        return post.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Integer id, @RequestBody Post post) {
        Post updatedPost = postService.updatePost(id, post);
        if (updatedPost != null) {
            return ResponseEntity.ok(updatedPost);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/for-you/{memberId}")
    public ResponseEntity<List<Post>> getPostsForYou(@PathVariable Integer memberId) {
        List<Post> posts = postService.getPostsForYou(memberId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/for-you/{memberId}/status/{status}")
    public ResponseEntity<List<Post>> getPostsForYouByStatus(
            @PathVariable Integer memberId,
            @PathVariable String status) {
        List<Post> posts = postService.getPostsForYouByStatus(memberId, status);
        return ResponseEntity.ok(posts);
    }

//    @GetMapping("/member/{memberId}")
//    public ResponseEntity<List<Post>> getPostsByMember(@PathVariable Integer memberId) {
//        List<Post> posts = postService.getPostsByMember(memberId);
//        return ResponseEntity.ok(posts);
//    }
}