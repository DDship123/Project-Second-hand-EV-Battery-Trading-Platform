package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.config.CloudinaryService;
import org.example.fe.entity.MemberResponse;
import org.example.fe.entity.PostResponse;
import org.example.fe.entity.ProductResponse;
import org.example.fe.entity.VehicleResponse;
import org.example.fe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home/postForm")
public class postFormController {
    @Autowired
    private PostService postService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public String postForm(Model model, HttpSession session) {
        MemberResponse user = (MemberResponse) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        return "postFormPage";
    }
    @PostMapping("/submit/vehicle")
    public String submitVehiclePost(Model model,HttpSession session, @RequestParam("mainImage") MultipartFile mainImage,
                                 @RequestParam(value = "subImages",required = false) List<MultipartFile> subImages,
                                 @RequestParam("postTitle") String postTitle,
                                 @RequestParam("postDescription") String postDescription,
                                 @RequestParam("productType") String productType,
                                 @RequestParam("productName") String productName,
                                 @RequestParam("productDescription") String productDescription,
                                 @RequestParam("vehicleStatus") String vehicleStatus,
                                 @RequestParam("vehicleBrand") String vehicleBrand,
                                 @RequestParam("vehicleModel") String vehicleModel,
                                 @RequestParam("vehicleRegistrationYear") Integer year,
                                 @RequestParam("vehicleOrigin") String origin,
                                 @RequestParam("VehicleMileage") Integer mileage,
                                 @RequestParam("vehicleBatteryCapacity") String batteryCapacity,
                                 @RequestParam("productPrice") BigDecimal price) {
        MemberResponse user = (MemberResponse) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        if (mainImage == null || mainImage.isEmpty()) {
            model.addAttribute("mainImageError", "Bắt buộc phải có ảnh chính.");
            return "postFormPage";
        }
        if (subImages!=null && subImages.size()> 4)
        {
            model.addAttribute("subImagesError", "You can upload up to 4 sub images.");
            return "postFormPage";
        }
        PostResponse post = new PostResponse();
        post.setTitle(postTitle);
        post.setDescription(postDescription);
        post.setPrice(price);
        post.setSeller(user);
        post.setCreatedAt(LocalDateTime.now());
        post.setStatus("PENDING");

        List<String> imageUrls = new ArrayList<>();
        try {
            if (mainImage != null && !mainImage.isEmpty()) {
                String mainImageUrl = cloudinaryService.uploadImage(mainImage);
                imageUrls.add(mainImageUrl);
            }
            if (subImages != null && !subImages.isEmpty()) {
                for (MultipartFile subImage : subImages) {
                    if (subImage != null && !subImage.isEmpty()) {
                        String subImageUrl = cloudinaryService.uploadImage(subImage);
                        imageUrls.add(subImageUrl);
                    }
                }
            }
            post.setImages(imageUrls);
        }catch (Exception e)
        {
            model.addAttribute("imageUploadError", "Error uploading images: " + e.getMessage());
            return "postFormPage";
        }


        ProductResponse product = new ProductResponse();
        product.setProductName(productName);
        product.setProductType(productType);
        product.setDescription(productDescription);
        product.setStatus(vehicleStatus);
        product.setCreatedAt(LocalDateTime.now());
        product.setMemberId(user.getMemberId());

        VehicleResponse vehicle = new VehicleResponse();
        vehicle.setModel(vehicleModel);
        vehicle.setRegistrationYear( String.valueOf(year));
        vehicle.setOrigin(origin);
        vehicle.setMileage(mileage);
        vehicle.setBatteryCapacity(batteryCapacity);
        vehicle.setBrand(vehicleBrand);


        product.setVehicle(vehicle);
        post.setProduct(product);

        postService.create(post);
//        return "redirect:/home"; // Redirect to home page after submission
        return "postFormPage"; // For now, just return to the form page
    }
}
