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
    public boolean deletePost(Integer id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // --- For You ---
// Lấy các bài post mà chính member đó đăng (APPROVED)
    public List<Post> getPostsForYou(Integer memberId) {
        return postRepository.findAllForYou(memberId);
    }

    // Lấy các bài post của chính member đó theo trạng thái tùy chọn
    public List<Post> getPostsForYouByStatus(Integer memberId, String status) {
        return postRepository.findAllForYouByStatus(memberId, status);
    }

    // --- By Member ---
    public List<Post> getPostsByMember(Integer memberId) {
        return postRepository.findAllByMember(memberId);
    }

    // --- Latest Posts ---
    public List<Post> getLatestPosts(int limit) {
        return postRepository.findLatestPosts(PageRequest.of(0, Math.max(1, limit)));
    }

    // Lấy post mới nhất theo loại vehicle (không lọc status)
    public List<Post> getLatestVehiclePosts(int limit) {
        return postRepository.findLatestVehiclePosts(PageRequest.of(0, Math.max(1, limit)));
    }

    // Lấy latest battery posts (không lọc status)
    public List<Post> getLatestBatteryPosts(int limit) {
        return postRepository.findLatestPostsByType("battery", PageRequest.of(0, Math.max(1, limit)));
    }

    // Lấy tất cả post vehicle (không giới hạn, không lọc status)
    public List<Post> findAllVehiclePosts() {
        return postRepository.findLatestPostsByType("vehicle", PageRequest.of(0, Integer.MAX_VALUE));
    }


    // Lấy tất cả post battery (không giới hạn, không lọc status)
    public List<Post> findAllBatteryPosts() {
        return postRepository.findLatestPostsByType("battery", PageRequest.of(0, Integer.MAX_VALUE));
    }

    // Đếm số lượng post theo productType và status APPROVED
    public int countApprovedPostsByProductType(String productType) {
        return postRepository.countByStatus(productType);
    }
    // Đếm số lượng post theo location và status
    public int countPostsByLocationAndStatus(String location, String productType) {
        return postRepository.countByLocationAndProductType(location, productType);
    }

    // Lấy tất cả post theo city
    public List<Post> findAllByMemberCity(String city) {
        return postRepository.findAllPostByMemberCity(city);
    }

    // Lấy tất cả post theo city và productType
    public List<Post> findAllByMemberCityAndProductType(String city, String productType) {
        return postRepository.findAllPostsByMemberCityAndProductType(productType, city);
    }

    // Lấy tất cả post theo city, productType và title
    public List<Post> findAllByMemberCityAndProductTypeAndTitle(String city, String productType, String title) {
        return postRepository.findAllPostsByMemberCityAndProductTypeAndStatus(productType, city, title);
    }

    // Lấy tất cả post theo trạng thái (chờ, duyệt, xóa) sắp xếp mới nhất lên đầu (Tân)
    public List<Post> getAllPostsByStatus(String status) {
        return postRepository.findAllByStatusOrderByCreatedAtDesc(status);
    }

    // Lấy tất cả post theo nhiều trạng thái (truyền danh sách) (Tân)
    public List<Post> getAllPostsByStatuses(List<String> statuses) {
        return postRepository.findAllByStatusInOrderByCreatedAtDesc(statuses);
    }
    public List<Post> findAllPostByProductTypeAndPostTitle(String productType, String title) {
        return postRepository.findAllPostByProductTypeAndPostTitle(productType, title);
    }
    public Integer countPostByMemberId(int memberId) {
        return postRepository.countBySeller_MemberId(memberId);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }
}
