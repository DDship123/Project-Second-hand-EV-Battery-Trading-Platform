package org.example.be.service;

import org.example.be.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post createPost(Post post);
    Optional<Post> getPostById(Integer id);
    List<Post> getAllPosts();
    Post updatePost(Integer id, Post updatedPost);
    boolean deletePost(Integer id);

    int countPostsByStatus(String status);
    List<Post> getPostsByMemberAndStatus(int memberId, String status);

    // --- For You ---
    List<Post> getPostsForYou(Integer memberId);
    List<Post> getPostsForYouByStatus(Integer memberId, String status);

    // --- By Member ---
    List<Post> getPostsByMember(Integer memberId);

    // --- Latest Posts ---
    List<Post> getLatestPosts(int limit);
    List<Post> getLatestVehiclePosts(int limit);
    List<Post> getLatestBatteryPosts(int limit);
    List<Post> findAllVehiclePosts();
    List<Post> findAllBatteryPosts();

    int countApprovedPostsByProductType(String productType);
    int countPostsByLocationAndStatus(String location, String productType);

    List<Post> findAllByMemberCity(String city);
    List<Post> findAllByMemberCityAndProductType(String city, String productType);
    List<Post> findAllByMemberCityAndProductTypeAndTitle(String city, String productType, String title);

    List<Post> getAllPostsByStatus(String status);
    List<Post> getAllPostsByStatuses(java.util.List<String> statuses);
    List<Post> findAllPostByProductTypeAndPostTitle(String productType, String title);
    Integer countPostByMemberId(int memberId);

    Post save(Post post);
}