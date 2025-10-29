package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.config.CloudinaryService;
import org.example.fe.response.*;
import org.example.fe.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/home")
public class updatePostController {
    @Autowired
    private PostService postService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/store/updatePost/{postId}")
    public String showUpdatePostPage(Model model, HttpSession session, @PathVariable("postId") Integer postId) {
        MemberResponse user = (MemberResponse) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        ApiResponse<PostResponse> response = postService.getPostDetail(postId);
        response.getPayload().setPriceInput(response.getPayload().getPrice().toString());
        session.setAttribute("postCurr", response.getPayload());
        model.addAttribute("post", response.getPayload());
        return "updatePostPage";
    }

    @PostMapping("/store/updatePost")
    public String updatePost(Model model,
                             PostResponse updatedPost, HttpSession session, @RequestParam("mainImage") MultipartFile mainImage,
                             @RequestParam(value = "subImages",required = false) List<MultipartFile> subImages) {
        PostResponse post = (PostResponse) session.getAttribute("postCurr");
        boolean hasError = false;
        try {
            if (mainImage != null && !mainImage.isEmpty()) {
               String mainImageUrl = cloudinaryService.uploadImage(mainImage);
               updatedPost.getImages().set(0, mainImageUrl);
            }
            if (subImages != null && !subImages.isEmpty()) {
                for (int i = 0; i < subImages.size(); i++) {
                    MultipartFile subImage = subImages.get(i);
                    if (subImage != null && !subImage.isEmpty()) {
                        String subImageUrl = cloudinaryService.uploadImage(subImage);
                        if (i + 1 < updatedPost.getImages().size()) {
                            updatedPost.getImages().set(i + 1, subImageUrl);
                        } else {
                            updatedPost.getImages().add(subImageUrl);
                        }
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        if(post.getProduct().getVehicle() != null){
            VehicleResponse vehicle = post.getProduct().getVehicle();
            VehicleResponse newVehicle = updatedPost.getProduct().getVehicle();
            boolean yearChange = !vehicle.getRegistrationYear().equals(newVehicle.getRegistrationYear());
            boolean vehicleBatteryCapacity = vehicle.getBatteryCapacity().equals(newVehicle.getBatteryCapacity());

            try{
                //kiểm tra user có thay đỏi năm không
                if(yearChange){
                    Integer.parseInt(newVehicle.getRegistrationYear());
                }
            }catch (NumberFormatException e){
                //nếu người dùng nhập chuỗi vào thì sẽ thông báo validate
                model.addAttribute("yearError", "Vui lòng nhập số");
                hasError = true;
            }

            try{
                if(vehicleBatteryCapacity){
                    Integer.parseInt(newVehicle.getBatteryCapacity());
                }
            }catch (NumberFormatException e){
                model.addAttribute("batteryCapacityError","Vui lòng nhập số");
                hasError = true;
            }
        }
        else{
            BatteryResponse batterryCurrency = post.getProduct().getBattery();
            BatteryResponse newBatteryCurrency = updatedPost.getProduct().getBattery();
            boolean yearChange = !batterryCurrency.getYearOfManufacture().equals(newBatteryCurrency.getYearOfManufacture());
            boolean voltageChange = !batterryCurrency.getVoltage().equals(newBatteryCurrency.getVoltage());

            try{
                if(yearChange){
                    Integer.parseInt(newBatteryCurrency.getYearOfManufacture());
                }
            }catch (NumberFormatException e){
                model.addAttribute("yearError","Vui lòng nhập số");
                hasError = true;
            }

            try{
                if(voltageChange){
                    Integer.parseInt(newBatteryCurrency.getVoltage());
                }
            }catch (NumberFormatException e){
                model.addAttribute("voltageError","Vui lòng nhập số");
                hasError = true;
            }

        }

        try {
            BigDecimal price = new BigDecimal(updatedPost.getPriceInput());
            updatedPost.setPrice(price);
        } catch (Exception e) {
            //nếu người dùng nhập chuỗi vào trong ô giá tiền thì sẽ có thông ba validate
            model.addAttribute("priceError", "Vui lòng nhập số");
            hasError = true;
        }


        if(hasError){
            model.addAttribute("post", updatedPost);
            MemberResponse user = (MemberResponse) session.getAttribute("user");
            model.addAttribute("user", user);
            model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
            return "updatePostPage";
        }

        ApiResponse<PostResponse> response = postService.update( updatedPost);
        if (response.getPayload() != null) {
            return "redirect:/home/store";
        } else {
            model.addAttribute("error", response.getError());
            model.addAttribute("post", updatedPost);
            MemberResponse user = (MemberResponse) session.getAttribute("user");
            model.addAttribute("user", user);
            model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
            return "updatePostPage";
        }
    }
}
