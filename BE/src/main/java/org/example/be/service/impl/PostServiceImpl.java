package org.example.be.service.impl;

import org.example.be.entity.Post;
import org.example.be.repository.PostRepository;
import org.example.be.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Optional<Post> getPostById(Integer id) {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
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

    @Override
    public boolean deletePost(Integer id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public int countPostsByStatus(String status) {
        return postRepository.countByStatus(status);
    }

    @Override
    public List<Post> getPostsByMemberAndStatus(int memberId, String status) {
        return postRepository.findAllBySeller_MemberIdAndStatus(memberId, status);
    }

    @Override
    public List<Post> getPostsForYou(Integer memberId) {
        return postRepository.findAllForYou(memberId);
    }

    @Override
    public List<Post> getPostsForYouByStatus(Integer memberId, String status) {
        return postRepository.findAllForYouByStatus(memberId, status);
    }

    @Override
    public List<Post> getPostsByMember(Integer memberId) {
        return postRepository.findAllByMember(memberId);
    }

    @Override
    public List<Post> getLatestPosts(int limit) {
        return postRepository.findLatestPosts(PageRequest.of(0, Math.max(1, limit)));
    }

    @Override
    public List<Post> getLatestVehiclePosts(int limit) {
        return postRepository.findLatestVehiclePosts(PageRequest.of(0, Math.max(1, limit)));
    }

    @Override
    public List<Post> getLatestBatteryPosts(int limit) {
        return postRepository.findLatestPostsByType("battery", PageRequest.of(0, Math.max(1, limit)));
    }

    @Override
    public List<Post> findAllVehiclePosts() {
        return postRepository.findLatestPostsByType("vehicle", PageRequest.of(0, Integer.MAX_VALUE));
    }

    @Override
    public List<Post> findAllBatteryPosts() {
        return postRepository.findLatestPostsByType("battery", PageRequest.of(0, Integer.MAX_VALUE));
    }

    @Override
    public int countApprovedPostsByProductType(String productType) {
        return postRepository.countByProductType(productType);
    }

    @Override
    public int countPostsByLocationAndStatus(String location, String productType) {
        return postRepository.countByLocationAndProductType(location, productType);
    }

    @Override
    public List<Post> findAllByMemberCity(String city) {
        return postRepository.findAllPostByMemberCity(city);
    }

    @Override
    public List<Post> findAllByMemberCityAndProductType(String city, String productType) {
        return postRepository.findAllPostsByMemberCityAndProductType(productType, city);
    }

    @Override
    public List<Post> findAllByMemberCityAndProductTypeAndTitle(String city, String productType, String title) {
        return postRepository.findAllPostsByMemberCityAndProductTypeAndStatus(productType, city, title);
    }

    @Override
    public List<Post> getAllPostsByStatus(String status) {
        return postRepository.findAllByStatusOrderByCreatedAtDesc(status);
    }

    @Override
    public List<Post> getAllPostsByStatuses(java.util.List<String> statuses) {
        return postRepository.findAllByStatusInOrderByCreatedAtDesc(statuses);
    }

    @Override
    public List<Post> findAllPostByProductTypeAndPostTitle(String productType, String title) {
        return postRepository.findAllPostByProductTypeAndPostTitle(productType, title);
    }

    @Override
    public Integer countPostByMemberId(int memberId) {
        return postRepository.countBySeller_MemberId(memberId);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }
}