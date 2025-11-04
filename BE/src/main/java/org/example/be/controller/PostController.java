package org.example.be.controller;

import org.example.be.dto.response.*;
import org.example.be.entity.*;
import org.example.be.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostImageService postImageService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BatteryService batteryService;

    @Autowired
    private VehicleService vehicleService;

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
                vehicleResponse.setRegistrationYear(post.getProduct().getVehicle().getRegisterYear());
                vehicleResponse.setOrigin(post.getProduct().getVehicle().getOrigin());
                vehicleResponse.setBatteryCapacity(post.getProduct().getVehicle().getBatteryCapacity());
                vehicleResponse.setCondition(post.getProduct().getVehicle().getCondition());
                productResponse.setVehicle(vehicleResponse);
            }


            if (post.getProduct().getBattery() != null) {
                BatteryResponse batteryResponse = new BatteryResponse();
                batteryResponse.setBatteryId(post.getProduct().getBattery().getBatteryId());
                batteryResponse.setCondition(post.getProduct().getBattery().getCondition());
                batteryResponse.setBrand(post.getProduct().getBattery().getBrand());
                batteryResponse.setCapacity(post.getProduct().getBattery().getCapacityAh());
                batteryResponse.setVoltage(post.getProduct().getBattery().getVoltageV());
                batteryResponse.setYearOfManufacture(post.getProduct().getBattery().getYearAt());
                batteryResponse.setOrigin(post.getProduct().getBattery().getOrigin());
                batteryResponse.setName(post.getProduct().getBattery().getName());
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
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<PostResponse>> createPost(@RequestBody PostResponse postRequest) {
        if (postRequest == null) {
            ApiResponse<PostResponse> response = new ApiResponse<>();
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Post data is required");
            response.error(error);
            return ResponseEntity.badRequest().body(response);
        }

        Map<String,String> error = validatePost(postRequest);

        if(!error.isEmpty()) {
            ApiResponse<PostResponse> response = new ApiResponse<>();
            response.error(error);
            return ResponseEntity.badRequest().body(response);
        }

        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setDescription(postRequest.getDescription());
        post.setStatus(postRequest.getStatus());
        post.setPrice(postRequest.getPrice());
        post.setCreatedAt(postRequest.getCreatedAt());

        Member seller = memberService.getMemberById(postRequest.getSeller().getMemberId());
        post.setSeller(seller);

        Product product = new Product();
        product.setName(postRequest.getProduct().getProductName());
        product.setProductType(postRequest.getProduct().getProductType());
        product.setDescription(postRequest.getProduct().getDescription());
        product.setStatus(postRequest.getProduct().getStatus());
        product.setCreatedAt(postRequest.getProduct().getCreatedAt());
        product.setMember(seller);

        if (product.getProductType().equals("VEHICLE"))
        {
            if (postRequest.getProduct().getVehicle() != null) {
                Vehicle vehicle = new Vehicle();
                vehicle.setBrand(postRequest.getProduct().getVehicle().getBrand());
                vehicle.setModel(postRequest.getProduct().getVehicle().getModel());
                vehicle.setMileage(postRequest.getProduct().getVehicle().getMileage());
                vehicle.setRegisterYear(postRequest.getProduct().getVehicle().getRegistrationYear());
                vehicle.setOrigin(postRequest.getProduct().getVehicle().getOrigin());
                vehicle.setBatteryCapacity(postRequest.getProduct().getVehicle().getBatteryCapacity()+" kWh");
                vehicle.setCondition(postRequest.getProduct().getVehicle().getCondition());
                vehicle.setName(postRequest.getProduct().getVehicle().getName());
                product.setVehicle(vehicleService.createVehicle(vehicle));
            }
        } else if (product.getProductType().equals("BATTERY"))
        {
            if (postRequest.getProduct().getBattery() != null) {
                Battery battery = new Battery();
                battery.setCondition(postRequest.getProduct().getBattery().getCondition());
                battery.setBrand(postRequest.getProduct().getBattery().getBrand());
                battery.setCapacityAh(postRequest.getProduct().getBattery().getCapacity());
                battery.setVoltageV(postRequest.getProduct().getBattery().getVoltage()+ " V");
                battery.setYearAt(postRequest.getProduct().getBattery().getYearOfManufacture());
                battery.setOrigin(postRequest.getProduct().getBattery().getOrigin());
                battery.setName(postRequest.getProduct().getBattery().getName());
                product.setBattery(batteryService.createBattery(battery));
            }
        }
         post.setProduct(productService.createProduct(product));

        Post savedPost = postService.createPost(post);

        if (postRequest.getImages() != null && !postRequest.getImages().isEmpty()) {
            for (String imageUrl : postRequest.getImages()) {
                PostImage postImage = new PostImage();
                postImage.setImageUrl(imageUrl);
                postImage.setPost(savedPost); // Thiết lập quan hệ với Post
                postImageService.createPostImage(postImage);
            }
        }




        ApiResponse<PostResponse> response = new ApiResponse<>();
        response.ok(mapToResponse(savedPost));
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
    @GetMapping("/count/{memberId}")
    public ResponseEntity<ApiResponse<Integer>> countPostsByMemberId(@PathVariable Integer memberId) {
        int count = postService.countPostByMemberId(memberId);
        ApiResponse<Integer> response = new ApiResponse<>();
        response.ok(count);
        return ResponseEntity.ok(response);
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
    public ResponseEntity<ApiResponse<PostResponse>> updatePost(@PathVariable Integer id, @RequestBody PostResponse post) {
        Optional<Post> existingPostOpt = postService.getPostById(id);
        ApiResponse<PostResponse> response = new ApiResponse<>();
        if (existingPostOpt.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Post not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }

        Post existingPost = existingPostOpt.get();
        existingPost.setTitle(post.getTitle());
        existingPost.setDescription(post.getDescription());
        existingPost.setStatus(post.getStatus());
        existingPost.setPrice(post.getPrice());
        existingPost.setStatus("PENDING"); // Khi cập nhật, đặt trạng thái về PENDING lại

        List<PostImage> updatedImages = new ArrayList<>();

        if (post.getImages() != null) {
            List<PostImage> existingImages = postImageService.getPostImagesByPostId(existingPost.getPostsId());
            int existingImagesSize = existingImages.size();
            for (int i = 0; i < post.getImages().size(); i++){
                String imageUrl = post.getImages().get(i);
                PostImage postImage = new PostImage();
                postImage.setImageUrl(imageUrl);
                postImage.setPost(existingPost);
                if (i < existingImagesSize) {
                    // Cập nhật hình ảnh hiện có
                    PostImage existingImage = existingImages.get(i);
                    postImage.setPostImagesId(existingImage.getPostImagesId());
                    updatedImages.add(postImageService.updatePostImage(existingImage.getPostImagesId(), postImage));
                } else {
                    // Tạo hình ảnh mới
                    updatedImages.add(postImageService.createPostImage(postImage));
                }
            }
            // Xóa các hình ảnh thừa nếu có
            for (int i = post.getImages().size(); i < existingImagesSize; i++) {
                PostImage imageToDelete = existingImages.get(i);
                postImageService.deletePostImage(imageToDelete.getPostImagesId());
            }
        }
        existingPost.setPostImages(updatedImages);

        Product product = existingPost.getProduct();
        product.setName(post.getProduct().getProductName());
        product.setDescription(post.getProduct().getDescription());
        product.setStatus(post.getProduct().getStatus());
        existingPost.setProduct(productService.updateProduct(product.getProductsId(), product));


        if (existingPost.getProduct().getProductType().equals("VEHICLE") && existingPost.getProduct().getVehicle() != null) {
            Vehicle vehicle = existingPost.getProduct().getVehicle();
            vehicle.setBrand(post.getProduct().getVehicle().getBrand());
            vehicle.setModel(post.getProduct().getVehicle().getModel());
            vehicle.setMileage(post.getProduct().getVehicle().getMileage());
            vehicle.setRegisterYear(post.getProduct().getVehicle().getRegistrationYear());
            vehicle.setOrigin(post.getProduct().getVehicle().getOrigin());
            vehicle.setBatteryCapacity(post.getProduct().getVehicle().getBatteryCapacity() + " kWh");
            vehicleService.updateVehicle(vehicle.getVehicleId(), vehicle);
        } else if (existingPost.getProduct().getProductType().equals("BATTERY") && existingPost.getProduct().getBattery() != null) {
            Battery battery = existingPost.getProduct().getBattery();
            battery.setCondition(post.getProduct().getBattery().getCondition());
            battery.setBrand(post.getProduct().getBattery().getBrand());
            battery.setCapacityAh(post.getProduct().getBattery().getCapacity());
            battery.setVoltageV(post.getProduct().getBattery().getVoltage()+ " V");
            battery.setYearAt(post.getProduct().getBattery().getYearOfManufacture());
            battery.setOrigin(post.getProduct().getBattery().getOrigin());
            battery.setName(post.getProduct().getBattery().getName());
            batteryService.updateBattery(battery.getBatteryId(), battery);
        }

        Post updatedPost = postService.updatePost(existingPost.getPostsId(),existingPost);
        response.ok(mapToResponse(updatedPost));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/admin/update/status")
    public ResponseEntity<ApiResponse<PostResponse>> updatePostStatusByAdmin(
            @RequestParam Integer postsId,
            @RequestParam String status) {
        Optional<Post> existingPostOpt = postService.getPostById(postsId);
        ApiResponse<PostResponse> response = new ApiResponse<>();
        if (existingPostOpt.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Post not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }

        Post existingPost = existingPostOpt.get();
        existingPost.setStatus(status);

        Post updatedPost = postService.updatePost(existingPost.getPostsId(), existingPost);
        response.ok(mapToResponse(updatedPost));
        return ResponseEntity.ok(response);
    }



    // --- DELETE POST ---
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable Integer id) {
        Post post = postService.getPostById(id).orElse(null);
        if (post != null) {
            // Xóa tất cả hình ảnh liên quan đến bài đăng
            List<PostImage> postImages = postImageService.getPostImagesByPostId(id);
            for (PostImage postImage : postImages) {
                postImageService.deletePostImage(postImage.getPostImagesId());
            }

            if (post.getProduct() != null) {
                Product product = post.getProduct();
                Integer vehicleId = null;
                Integer batteryId = null;
                // Gỡ liên kết với giữa product và vehicle
                if(product.getBattery() != null) {
                    //lấy id cua3 battery trc khi gỡ liên kết
                    batteryId = product.getBattery().getBatteryId();
                    product.setBattery(null);
                }
                if(product.getVehicle() != null) {
                    //lấy id của vehicle trc khi gỡ liên kết
                    vehicleId = product.getVehicle().getVehicleId();
                    product.setVehicle(null);
                }
                //lưu lại sau khi gỡ liên kết
                productService.save(product);

                // Xóa vehicle hoặc battery liên quan đến product
                if (vehicleId != null) {
                    vehicleService.deleteVehicle(vehicleId);
                }
                if (batteryId != null) {
                    batteryService.deleteBattery(batteryId);
                }
                // gỡ liên kết giữa product và post
                post.setProduct(null);

                //lưu lại sau khi gỡ liên kết
                postService.save(post);
//                postService.flush();

                // Xóa product
                productService.deleteProduct(product.getProductsId());

            }
        }else {
            ApiResponse<Void> response = new ApiResponse<>();
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Post not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
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
        int count = postService.countApprovedPostsByProductType("vehicle");
        int totalPages = 0;
        if (count % 8 == 0) {
            totalPages = count / 8;
        } else {
            totalPages = (count / 8) + 1;
        }
        if (posts.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No vehicle posts found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(posts);
            Map<String, Object> metaData = new HashMap<>();
            metaData.put("totalPages", totalPages);
            response.setMetadata(metaData);
            return ResponseEntity.ok(response);
        }
    }

    // --- GET LATEST BATTERY POSTS ---
    @GetMapping("/latest/battery")
    public ResponseEntity<ApiResponse<List<PostResponse>>> getLatestBatteryPosts() {
        List<PostResponse> posts = postService.getLatestBatteryPosts(8).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        int count = postService.countApprovedPostsByProductType("battery");
        int totalPages = 0;
        if (count % 8 == 0) {
            totalPages = count / 8;
        } else {
            totalPages = (count / 8) + 1;
        }
        ApiResponse<List<PostResponse>> response = new ApiResponse<>();
        if (posts.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No battery posts found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(posts);
            Map<String, Object> metaData = new HashMap<>();
            metaData.put("totalPages", totalPages);
            response.setMetadata(metaData);
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
            Map<String, Object> metaData = new HashMap<>();
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> getPostById(@PathVariable Integer id) {
        Optional<Post> postOpt = postService.getPostById(id);
        ApiResponse<PostResponse> response = new ApiResponse<>();

        if (postOpt.isPresent()) {
            PostResponse postResponse = mapToResponse(postOpt.get());
            postResponse.getSeller().setAddress(postOpt.get().getSeller().getAddress());
            postResponse.getSeller().setEmail(postOpt.get().getSeller().getEmail());
            postResponse.getSeller().setPhone(postOpt.get().getSeller().getPhone());
            response.ok(postResponse);
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Post not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
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
        int count = postService.countPostsByLocationAndStatus(city, productType);
        int totalPages = 0;
        if (count % 8 == 0) {
            totalPages = count / 8;
        } else {
            totalPages = (count / 8) + 1;
        }
        ApiResponse<List<PostResponse>> response = new ApiResponse<>();
        response.ok(posts);
        Map<String, Object> metaData = new HashMap<>();
        metaData.put("totalPages", totalPages);
        response.setMetadata(metaData);
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
        int size = posts.size();
        int totalPages = 0;
        if (size % 8 == 0) {
            totalPages = size / 8;
        } else {
            totalPages = (size / 8) + 1;
        }

        ApiResponse<List<PostResponse>> response = new ApiResponse<>();
        Map<String, Object> metaData = new HashMap<>();
        metaData.put("totalPages", totalPages);
        response.setMetadata(metaData);
        response.ok(posts);
        return ResponseEntity.ok(response);
    }
    // -- GET ALL POST BY PRODUCT TYPE AND POST TITLE --(Tân)
    @GetMapping("/type-title")
    public ResponseEntity<ApiResponse<List<PostResponse>>> findAllPostByProductTypeAndPostTitle(
            @RequestParam String productType,
            @RequestParam String title) {

        List<PostResponse> posts = postService.findAllPostByProductTypeAndPostTitle(productType, title)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        int size = posts.size();
        int totalPages = 0;
        if (size % 8 == 0) {
            totalPages = size / 8;
        } else {
            totalPages = (size / 8) + 1;
        }

        ApiResponse<List<PostResponse>> response = new ApiResponse<>();
        Map<String, Object> metaData = new HashMap<>();
        metaData.put("totalPages", totalPages);
        response.setMetadata(metaData);
        response.ok(posts);
        return ResponseEntity.ok(response);
    }

    // --- ADMIN: GET ALL POSTS BY STATUS ---(Tân)
    @GetMapping("/admin/status/{status}")
    public ResponseEntity<ApiResponse<List<PostResponse>>> getAllPostsByStatusForAdmin(@PathVariable String status) {
        List<PostResponse> posts = postService.getAllPostsByStatus(status).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        for (PostResponse post : posts) {
            Member seller = memberService.getMemberById(post.getSeller().getMemberId());
            if (seller != null) {
                MemberResponse sellerResponse = new MemberResponse();
                sellerResponse.setMemberId(seller.getMemberId());
                sellerResponse.setEmail(seller.getEmail());
                sellerResponse.setUsername(seller.getUsername());
                post.setSeller(sellerResponse);
            }
        }
        ApiResponse<List<PostResponse>> response = new ApiResponse<>();
        if (posts.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No posts found with status: " + status);
            response.error(error);
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(posts);
//            Map<String, Object> metaData = new HashMap<>();
//            metaData.put("totalPosts", posts.size());
//            response.setMetadata(metaData);
            return ResponseEntity.ok(response);
        }
    }

    // --- ADMIN: GET ALL POSTS BY MULTIPLE STATUS ---(Tân)
    @GetMapping("/admin/statuses")
    public ResponseEntity<ApiResponse<List<PostResponse>>> getAllPostsByStatusesForAdmin(@RequestParam List<String> statuses) {
        List<PostResponse> posts = postService.getAllPostsByStatuses(statuses).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        ApiResponse<List<PostResponse>> response = new ApiResponse<>();
        if (posts.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No posts found for statuses: " + statuses);
            response.error(error);
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(posts);
            return ResponseEntity.ok(response);
        }
    }

    private Map<String, String> validatePost(PostResponse post) {
        Map<String, String> errors = new HashMap<>();
        if(post.getProduct() == null){
            errors.put("product", "Product is required");
            return errors;
        }

        String productType = post.getProduct().getProductType();
        if(productType == null||productType.isEmpty()){
            errors.put("productType", "Product type is required");
            return errors;
        }

        if(productType.equalsIgnoreCase("VEHICLE")){
            VehicleResponse v = post.getProduct().getVehicle();
            if(v == null){
                errors.put("vehicle", "Vehicle is required");
            }else{
                if(!String.valueOf(v.getRegistrationYear()).matches("\\d{4}")){
                    errors.put("vehicle", "Registration year is invalid");
                }
                if(!String.valueOf(v.getMileage()).matches("\\d+")){
                    errors.put("vehicle", "Mileage is invalid");
                }
                if(!String.valueOf(v.getBatteryCapacity()).matches("\\d+(\\.\\d+)?")){
                    errors.put("vehicle", "Battery Capacity is invalid");
                }
            }

        }

        if(productType.equalsIgnoreCase("BATTERY")){
            BatteryResponse b = post.getProduct().getBattery();
            if(!String.valueOf(b.getCapacity()).matches("\\d+(\\.\\d+)?")){
                errors.put("vehicle", "Capacity is invalid");
            }
            if(!String.valueOf(b.getVoltage()).matches("\\d+(\\.\\d+)?")){
                errors.put("vehicle", "Voltage is invalid");
            }
            if(!String.valueOf(b.getYearOfManufacture()).matches("\\d{4}")){
                errors.put("vehicle", "Year of Manufacture is invalid");
            }
        }
        return errors;
    }
}