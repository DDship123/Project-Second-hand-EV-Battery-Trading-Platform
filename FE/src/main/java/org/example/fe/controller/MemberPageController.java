package org.example.fe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class MemberPageController {
    @RequestMapping({"/personalInformation",""})
    public String personalInformation() {
        return "personalInformationPage";
    }
    @RequestMapping("/security")
    public String security() {
        return "securityPage";
    }

    @RequestMapping("/transactionHistory")
    public String transactionHistory() {
        return "memberTransactionHistoryPage";
    }
}
