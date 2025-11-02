package org.example.fe.validate;


import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import java.util.Map;

@Component
public class SignUpValidate {
    public void error(Model model, Map<String, String> errorMap) {
        if(errorMap == null || errorMap.isEmpty()) {
            return;
        }
            if (errorMap.containsKey("email")) {
                model.addAttribute("emailError", errorMap.get("email"));
            }
            if (errorMap.containsKey("username")) {
                model.addAttribute("usernameError", errorMap.get("username"));
            }
            if (errorMap.containsKey("phone")) {
                model.addAttribute("phoneError", errorMap.get("phone"));
            }
            if (errorMap.containsKey("message")) {
                model.addAttribute("message", errorMap.get("message"));

        }
    }
}
