package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.config.CloudinaryService;
import org.example.fe.response.*;
import org.example.fe.service.PostService;
import org.example.fe.validate.CreatePostValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home/postForm")
public class CreatePostController {
    @Autowired
    private PostService postService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public String postForm(Model model, HttpSession session) {
        MemberResponse user = (MemberResponse) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Redirect to login page if user is not logged in
        }
        model.addAttribute("user", user);
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        return "createPostPage";
    }
    @PostMapping("/submit/vehicle")
    public String submitVehiclePost(Model model,HttpSession session, @RequestParam("mainImage") MultipartFile mainImage,
                                 @RequestParam(value = "subImages",required = false) List<MultipartFile> subImages,
                                 @RequestParam("postTitle") String postTitle,
                                 @RequestParam("postDescription") String postDescription,
                                 @RequestParam("productType") String productType,
                                 @RequestParam("productName") String productName,
                                 @RequestParam("productDescription") String productDescription,
                                 @RequestParam("productStatus") String productStatus,
                                 @RequestParam("vehicleBrand") String vehicleBrand,
                                 @RequestParam("vehicleCondition") String vehicleCondition,
                                 @RequestParam("vehicleModel") String vehicleModel,
                                 @RequestParam("vehicleRegistrationYear") String year,
                                 @RequestParam("vehicleOrigin") String origin,
                                 @RequestParam("VehicleMileage") Integer mileage,
                                 @RequestParam("vehicleBatteryCapacity") String batteryCapacity,
                                 @RequestParam("productPrice") String priceInput) {
        MemberResponse user = (MemberResponse) session.getAttribute("user");
        CreatePostValidate validate = new CreatePostValidate();
        PostResponse post = new PostResponse();
        model.addAttribute("user", user);
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));

        boolean hasErrors = validate.errorVehicle(model,mainImage,subImages,year,batteryCapacity,post,priceInput);

//        if (mainImage == null || mainImage.isEmpty()) {
//            model.addAttribute("mainImageError", "Bắt buộc phải có ảnh chính.");
//            hasErrors = true;
////            return "createPostPage";
//        }
//        if (subImages!=null && subImages.size()> 4)
//        {
//            model.addAttribute("subImagesError", "You can upload up to 4 sub images.");
//            hasErrors = true;
////            return "createPostPage";
//        }
//
////        Map<String, String> errors = new HashMap<>();
//
//        Integer mileage = null;
//        try{
//            Integer.parseInt(year);
//        }catch (NumberFormatException e){
////            errors.put("yearError","Year must be an integer");
//            model.addAttribute("yearError", "Năm sản xuất phải là số nguyên");
//            hasErrors = true;
//        }
//
//        try{
//            mileage =  Integer.parseInt(mileageStr);
//        }catch (NumberFormatException e){
////            errors.put("mileageError","Mileage must be an integer");
//            model.addAttribute("mileageError", "Km phải là số");
//            hasErrors = true;
//        }
//
//        try{
//           Integer.parseInt(batteryCapacity);
//        }catch (NumberFormatException e){
//
////            errors.put("batteryCapacityError","Battery capacity must be an integer");
//            model.addAttribute("vehicleBatteryCapacity", "Dung tích pin phải là số");
//            hasErrors = true;
//        }
//
////        if(!errors.isEmpty()){
////            model.addAttribute("errors",errors);
////            return "createPostPage";
////        }

        if(hasErrors){
            model.addAttribute("user", user);
            model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
           return "createPostPage";
        }




        post.setTitle(postTitle);
        post.setDescription(postDescription);
        post.setPriceInput(priceInput);
//        post.setPrice(price);
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
                    if (subImage.getName() != null  && !subImage.isEmpty()) {
                        String subImageUrl = cloudinaryService.uploadImage(subImage);
                        imageUrls.add(subImageUrl);
                    }
                }
            }
            post.setImages(imageUrls);
        }catch (Exception e)
        {
            model.addAttribute("imageUploadError", "Error uploading images: " + e.getMessage());
            return "createPostPage";
        }


        ProductResponse product = new ProductResponse();
        product.setProductName(productName);
        product.setProductType(productType);
        product.setDescription(productDescription);
        product.setStatus(productStatus);
        product.setCreatedAt(LocalDateTime.now());
        product.setMemberId(user.getMemberId());

        VehicleResponse vehicle = new VehicleResponse();
        vehicle.setModel(vehicleModel);
        vehicle.setRegistrationYear((year));
        vehicle.setOrigin(origin);
        vehicle.setMileage(mileage);
        vehicle.setBatteryCapacity(batteryCapacity);
        vehicle.setBrand(vehicleBrand);
        vehicle.setCondition(vehicleCondition);
        vehicle.setName(productName);

        product.setVehicle(vehicle);
        post.setProduct(product);

        ApiResponse<PostResponse> apiResponse = postService.create(post);
        if (apiResponse.getStatus().equals("SUCCESS")) {
            return "redirect:/home/store"; // Redirect to home page after successful post creation
        } else {
            model.addAttribute("postError", apiResponse.getError());
        }
        return "createPostPage"; // For now, just return to the form page
    }

    @PostMapping("/submit/battery")
    public String submitBatteryPost(Model model,HttpSession session, @RequestParam("mainImage") MultipartFile mainImage,
                                 @RequestParam(value = "subImages",required = false) List<MultipartFile> subImages,
                                 @RequestParam("postTitle") String postTitle,
                                 @RequestParam("postDescription") String postDescription,
                                 @RequestParam("productType") String productType,
                                 @RequestParam("productName") String productName,
                                 @RequestParam("productDescription") String productDescription,
                                 @RequestParam("productStatus") String productStatus,
                                 @RequestParam("batteryBrand") String batteryBrand,
                                 @RequestParam("batteryCondition") String batteryCondition,
                                 @RequestParam("batteryCapacityAh") Integer batteryCapacity,
                                 @RequestParam("batteryVoltage") String batteryVoltage,
                                 @RequestParam("batteryManufactureYear") String batteryYearOfManufacture,
                                 @RequestParam("batteryOrigin") String batteryOrigin,
                                 @RequestParam("productPrice") String priceInput) {
        MemberResponse user = (MemberResponse) session.getAttribute("user");
        CreatePostValidate validate = new CreatePostValidate();
        PostResponse post = new PostResponse();
        model.addAttribute("user", user);
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        boolean hasErrors = validate.errorBattery(model,mainImage,subImages,batteryVoltage,batteryYearOfManufacture, post,priceInput);

//        if (mainImage == null || mainImage.isEmpty()) {
//            model.addAttribute("mainImageError", "Bắt buộc phải có ảnh chính.");
//            hasErrors = true;
////            return "createPostPage";
//        }
//        if (subImages != null && subImages.size() > 4) {
//            model.addAttribute("subImagesError", "You can upload up to 4 sub images.");
//                hasErrors = true;
////            return "createPostPage";
//        }
//        Integer batteryCapacity = null;
//        try{
//           batteryCapacity = Integer.parseInt(batteryCapacityStr);
//        }catch (NumberFormatException e){
//            model.addAttribute("batteryCapacityError","Vui lòng nhập số");
//            hasErrors = true;
//        }
//        try {
//            Integer.parseInt(batteryVoltage);
//        }catch (NumberFormatException e){
//            model.addAttribute("batteryVoltageError","Vui lòng nhập số");
//            hasErrors = true;
//        }
//
//        try {
//            Integer.parseInt(batteryYearOfManufacture);
//        }catch (NumberFormatException e){
//            model.addAttribute("batteryYearError","Vui lòng nhập số");
//            hasErrors = true;
//        }

        if(hasErrors){
            model.addAttribute("user", user);
            model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
            return "createPostPage";
        }


        post.setTitle(postTitle);
        post.setDescription(postDescription);
        post.setPriceInput(priceInput);
//        post.setPrice(price);
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
        } catch (Exception e) {
            model.addAttribute("imageUploadError", "Error uploading images: " + e.getMessage());
            return "createPostPage";
        }
        ProductResponse product = new ProductResponse();
        product.setProductName(productName);
        product.setProductType(productType);
        product.setDescription(productDescription);
        product.setStatus(productStatus);
        product.setCreatedAt(LocalDateTime.now());
        product.setMemberId(user.getMemberId());
        // Create and set Battery entity
        BatteryResponse battery = new BatteryResponse();
        battery.setBrand(batteryBrand);
        battery.setCondition(batteryCondition);
        battery.setCapacity(batteryCapacity);
        battery.setVoltage(batteryVoltage);
        battery.setYearOfManufacture(String.valueOf(batteryYearOfManufacture));
        battery.setOrigin(batteryOrigin);
        battery.setName(productName);

        product.setBattery(battery);
        post.setProduct(product);
        ApiResponse<PostResponse> apiResponse = postService.create(post);
        if (apiResponse.getStatus().equals("SUCCESS")) {
            return "redirect:/home/store"; // Redirect to home page after successful post creation
        } else {
            model.addAttribute("postError", apiResponse.getError());
        }
        return "createPostPage"; // For now, just return to the form page
    }
}
