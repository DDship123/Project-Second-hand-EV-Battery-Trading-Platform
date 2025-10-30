package org.example.fe.validate;

import org.example.fe.response.BatteryResponse;
import org.example.fe.response.PostResponse;
import org.example.fe.response.VehicleResponse;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.math.BigDecimal;

@Component
public class UpdatePostValidate {
    public boolean errorUpdate(Model model,
                               PostResponse post,
                               PostResponse updatedPost){
        boolean hasError = false;
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
        return  hasError;
    }
}
