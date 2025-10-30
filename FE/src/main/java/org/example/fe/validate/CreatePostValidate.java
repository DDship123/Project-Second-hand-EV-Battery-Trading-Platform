package org.example.fe.validate;

import org.example.fe.response.PostResponse;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CreatePostValidate {
    public boolean errorVehicle(Model model,
                                MultipartFile mainImage,
                                List<MultipartFile> subImages,
                                String year,
                                String batteryCapacity,
                                PostResponse post,
                                String priceInput) {
        boolean hasErrors = false;

        if (mainImage == null || mainImage.isEmpty()) {
            model.addAttribute("mainImageError", "Bắt buộc phải có ảnh chính.");
            hasErrors = true;
        }
        if (subImages!=null && subImages.size()> 4)
        {
            model.addAttribute("subImagesError", "Bạn có thể tải lên tối đa 4 hình ảnh phụ.");
            hasErrors = true;
        }

        try{
            Integer.parseInt(year);
        }catch (NumberFormatException e){
            model.addAttribute("yearError", "Năm sản xuất phải là số nguyên");
            hasErrors = true;
        }


        try{
            Integer.parseInt(batteryCapacity);
        }catch (NumberFormatException e){
            model.addAttribute("vehicleBatteryCapacity", "Dung tích pin phải là số");
            hasErrors = true;
        }
        try {
            BigDecimal price = new BigDecimal(priceInput);
            post.setPrice(price);
        } catch (Exception e) {
            //nếu người dùng nhập chuỗi vào trong ô giá tiền thì sẽ có thông ba validate
            model.addAttribute("priceError", "Vui lòng nhập số");
            hasErrors = true;
        }
        return hasErrors;
    }

    public boolean errorBattery(Model model,
                                MultipartFile mainImage,
                                List<MultipartFile> subImages,
                                String batteryVoltage,
                                String batteryYearOfManufacture,
                                PostResponse post,
                                String priceInput) {
        boolean hasErrors = false;
        if (mainImage == null || mainImage.isEmpty()) {
            model.addAttribute("mainImageError", "Bắt buộc phải có ảnh chính.");
            hasErrors = true;
        }
        if (subImages != null && subImages.size() > 4) {
            model.addAttribute("subImagesError", "Bạn có thể tải lên tối đa 4 hình ảnh phụ.");
            hasErrors = true;

        }
        try {
            Integer.parseInt(batteryVoltage);
        }catch (NumberFormatException e){
            model.addAttribute("batteryVoltageError","Vui lòng nhập số");
            hasErrors = true;
        }

        try {
            Integer.parseInt(batteryYearOfManufacture);
        }catch (NumberFormatException e){
            model.addAttribute("batteryYearError","Vui lòng nhập số");
            hasErrors = true;
        }

        try {
            BigDecimal price = new BigDecimal(priceInput);
            post.setPrice(price);
        } catch (Exception e) {
            //nếu người dùng nhập chuỗi vào trong ô giá tiền thì sẽ có thông ba validate
            model.addAttribute("priceError", "Vui lòng nhập số");
            hasErrors = true;
        }

        return hasErrors;
    }
}
