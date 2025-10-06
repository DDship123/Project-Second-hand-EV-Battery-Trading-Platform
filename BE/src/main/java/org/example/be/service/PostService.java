package org.example.be.service;

import org.example.be.entity.Post;
import org.example.be.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
            post.setSeller(updatedPost.getSeller());
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

    // --- For You ---
    public List<Post> getPostsForYou(Integer memberId) {
        return postRepository.findAllForYou(memberId);
    }

    public List<Post> getPostsForYouByStatus(Integer memberId, String status) {
        return postRepository.findAllForYouByStatus(memberId, status);
    }

<<<<<<< HEAD
    // Get user's own posts
//    public List<Post> getPostsByMember(Integer memberId) {
//        return postRepository.findByMemberId(memberId);
//    }
}
=======
    // --- By Member ---
    public List<Post> getPostsByMember(Integer memberId) {
        return postRepository.findAllByMember(memberId);
    }

    // --- Latest Posts ---
    public List<Post> getLatestPosts(int limit) {
        return postRepository.findLatestPosts(PageRequest.of(0, limit));
    }
}
>>>>>>> c8355d0576d3841bc5f1157f8ce69f7acc983372
