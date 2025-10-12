package org.example.be.controller;

import org.example.be.dto.reponse.*;
import org.example.be.entity.Post;
import org.example.be.entity.PostImage;
import org.example.be.service.CommentService;
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

    @Autowired
    private CommentService commentService;

    private PostResponse mapToResponse(Post post) {
        if (post == null) {
            return null;
        }

        // Xử lý images - chỉ lấy URL string
        List<String> images = post.getPostImages() != null && !post.getPostImages().isEmpty()
                ? post.getPostImages().stream()
                .map(PostImage::getImageUrl)
                .filter(url -> url != null && !url.trim().isEmpty())
                .collect(Collectors.toList())
                : List.of();

        MemberResponse sellerResponse = null;
        if (post.getSeller() != null) {
            sellerResponse = new MemberResponse();
            sellerResponse.setMemberId(post.getSeller().getMemberId());
            sellerResponse.setCity(post.getSeller().getCity());
            sellerResponse.setUsername(post.getSeller().getUsername());
            sellerResponse.setAvatarUrl(post.getSeller().getAvatarUrl());
        }

        ProductResponse productResponse = null;
        if (post.getProduct() != null) {
            productResponse = new ProductResponse();
            productResponse.setProductId(post.getProduct().getProductsId());
            productResponse.setProductName(post.getProduct().getName());
            productResponse.setProductType(post.getProduct().getProductType());
            productResponse.setStatus(post.getProduct().getStatus());
            productResponse.setDescription(post.getProduct().getDescription());
            productResponse.setCreatedAt(post.getProduct().getCreatedAt());

            // Sửa: truy cập memberId thông qua relationship
            if (post.getProduct().getMember() != null) {
                productResponse.setMemberId(post.getProduct().getMember().getMemberId());
            }


            if (post.getProduct().getVehicle() != null) {
                VehicleResponse vehicleResponse = new VehicleResponse();
                vehicleResponse.setVehicleId(post.getProduct().getVehicle().getVehicleId());
                vehicleResponse.setBrand(post.getProduct().getVehicle().getBrand());
                vehicleResponse.setModel(post.getProduct().getVehicle().getModel());
                vehicleResponse.setMileage(post.getProduct().getVehicle().getMileage());
                productResponse.setVehicle(vehicleResponse);
            }


            if (post.getProduct().getBattery() != null) {
                BatteryResponse batteryResponse = new BatteryResponse();
                batteryResponse.setBatteryId(post.getProduct().getBattery().getBatteryId());
                batteryResponse.setCondition(post.getProduct().getBattery().getCondition());
                batteryResponse.setBrand(post.getProduct().getBattery().getBrand());
                batteryResponse.setCapacity(post.getProduct().getBattery().getCapacityAh());
                productResponse.setBattery(batteryResponse);
            }
        }

        List<CommentResponse> commentResponses = commentService.findAllCommentByPostId(post.getPostsId());
        PostResponse postResponse = new PostResponse();
        postResponse.setPostsId(post.getPostsId());
        postResponse.setTitle(post.getTitle());
        postResponse.setDescription(post.getDescription());
        postResponse.setStatus(post.getStatus());
        postResponse.setPrice(post.getPrice());
        postResponse.setCreatedAt(post.getCreatedAt());
        postResponse.setSeller(sellerResponse);
        postResponse.setProduct(productResponse);
        postResponse.setImages(images);
        postResponse.setComments(commentResponses);
        return postResponse;
    }

    // --- CREATE POST ---
    @PostMapping
    public ResponseEntity<ApiResponse<PostResponse>> createPost(@RequestBody Post post) {
        Post saved = postService.createPost(post);
        ApiResponse<PostResponse> response = new ApiResponse<>();
        response.ok(mapToResponse(saved));
        return ResponseEntity.ok(response);
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
    public ResponseEntity<ApiResponse<List<PostResponse>>> getLatestPosts(@RequestParam(defaultValue = "8",required = false) int limit) {
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
    // --- GET ALL VEHICLE POSTS ---
    @GetMapping("/all/vehicle")
    public ResponseEntity<ApiResponse<List<PostResponse>>> findAllVehiclePosts() {
        List<PostResponse> posts = postService.findAllVehiclePosts().stream()
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
    // --- GET ALL BATTERY POSTS ---
    @GetMapping("/all/battery")
    public ResponseEntity<ApiResponse<List<PostResponse>>> findAllBatteryPosts() {
        List<PostResponse> posts = postService.findAllBatteryPosts().stream()
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


    // -- GET ALL POST BY MEMBER CITY --
    @GetMapping("/city")
    public ResponseEntity<ApiResponse<List<PostResponse>>> findAllPostByMember(@RequestParam String city) {
        List<PostResponse> posts = postService.findAllByMemberCity(city).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        ApiResponse<List<PostResponse>> response = new ApiResponse<>();
        response.ok(posts);
        return ResponseEntity.ok(response);
    }

    // -- GET ALL POST BY MEMBER CITY AND PRODUCT TYPE --
    @GetMapping("/city-type")
    public ResponseEntity<ApiResponse<List<PostResponse>>> findAllPostsByMemberCityAndProductType(
            @RequestParam String productType,
            @RequestParam String city) {

        List<PostResponse> posts = postService.findAllByMemberCityAndProductType(city, productType).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        ApiResponse<List<PostResponse>> response = new ApiResponse<>();
        response.ok(posts);
        return ResponseEntity.ok(response);
    }

    // -- GET ALL POST BY MEMBER CITY AND PRODUCT TYPE AND TITLE --
    @GetMapping("/city-type-title")
    public ResponseEntity<ApiResponse<List<PostResponse>>> findAllPostsByMemberCityAndProductTypeAndTitle(
            @RequestParam String productType,
            @RequestParam String city,
            @RequestParam String title) {

        List<PostResponse> posts = postService.findAllByMemberCityAndProductTypeAndTitle(city, productType, title)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        ApiResponse<List<PostResponse>> response = new ApiResponse<>();
        response.ok(posts);
        return ResponseEntity.ok(response);
    }

}