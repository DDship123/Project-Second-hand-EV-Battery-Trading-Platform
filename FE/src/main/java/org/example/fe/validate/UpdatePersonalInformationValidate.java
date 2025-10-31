package org.example.fe.validate;

import jakarta.servlet.http.HttpSession;
import org.example.fe.response.MemberResponse;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import java.util.Map;

@Component
public class UpdatePersonalInformationValidate {
    public void error(Model model, Map<String, String> errorMap,
                      MemberResponse updatedUser,
                      HttpSession session) {
        if(errorMap == null || errorMap.isEmpty()) {
            return;
        }
        MemberResponse currentUser = (MemberResponse) session.getAttribute("user");

        boolean usernameChanged = !currentUser.getUsername().equals(updatedUser.getUsername());
        boolean emailChanged = !currentUser.getEmail().equals(updatedUser.getEmail());
        boolean phoneChanged = !currentUser.getPhone().equals(updatedUser.getPhone());

            if (emailChanged) {
                if (errorMap.containsKey("email")) {
                    model.addAttribute("emailError", errorMap.get("email"));
                }
            }
            if (usernameChanged) {
                if (errorMap.containsKey("username")) {
                    model.addAttribute("usernameError", errorMap.get("username"));
                }
            }
            if (phoneChanged) {
                if (errorMap.containsKey("phone")) {
                    model.addAttribute("phoneError", errorMap.get("phone"));
                }
            }
    }

    public boolean errrorPassword(Model model,
                               String currentPassword,
                               String newPassword,
                               String confirmPassword,
                               HttpSession session) {

        MemberResponse user = (MemberResponse) session.getAttribute("user");
        boolean hasError = false;


            if (!user.getPassword().equals(currentPassword)) {
                model.addAttribute("errorMessage", "Sai mật khẩu");
                model.addAttribute("user", user);
                hasError = true;
            }

            if(newPassword.equals(currentPassword)){
                model.addAttribute("passwordError", "Mật khẩu mới phải khác với mật khẩu hiện tại");
                model.addAttribute("user", user);
                hasError = true;

            }

            if (!newPassword.equals(confirmPassword)) {
                model.addAttribute("passwordError", "Mật khẩu mới và mật khẩu xác nhận không khớp");
                model.addAttribute("user", user);
                hasError = true;
            }
        return hasError;
    }
}
