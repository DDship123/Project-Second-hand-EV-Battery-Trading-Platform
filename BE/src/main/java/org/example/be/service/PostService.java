package org.example.be.service;

import org.example.be.entity.Post;
import org.example.be.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // Create
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    // Read (Get by ID)
    public Optional<Post> getPostById(Integer id) {
        return postRepository.findById(id);
    }

    // Read (Get all)
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Update
    public Post updatePost(Integer id, Post updatedPost) {
        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isPresent()) {
            Post post = existingPost.get();
            post.setProduct(updatedPost.getProduct());
            post.setMember(updatedPost.getMember());
            post.setTitle(updatedPost.getTitle());
            post.setDescription(updatedPost.getDescription());
            post.setStatus(updatedPost.getStatus());
            post.setPrice(updatedPost.getPrice());
            post.setCreatedAt(updatedPost.getCreatedAt());
            return postRepository.save(post);
        }
        return null;
    }

    // Delete
    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }
}