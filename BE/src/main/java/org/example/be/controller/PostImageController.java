package org.example.be.controller;

import org.example.be.entity.PostImage;
import org.example.be.service.PostImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/post-images")
public class PostImageController {

    @Autowired
    private PostImageService postImageService;

    @PostMapping
    public ResponseEntity<PostImage> createPostImage(@RequestBody PostImage postImage) {
        return ResponseEntity.ok(postImageService.createPostImage(postImage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostImage> getPostImageById(@PathVariable Integer id) {
        Optional<PostImage> postImage = postImageService.getPostImageById(id);
        return postImage.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PostImage>> getAllPostImages() {
        List<PostImage> postImages = postImageService.getAllPostImages();
        return ResponseEntity.ok(postImages);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostImage> updatePostImage(@PathVariable Integer id, @RequestBody PostImage postImage) {
        PostImage updatedPostImage = postImageService.updatePostImage(id, postImage);
        if (updatedPostImage != null) {
            return ResponseEntity.ok(updatedPostImage);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostImage(@PathVariable Integer id) {
        postImageService.deletePostImage(id);
        return ResponseEntity.noContent().build();
    }
}