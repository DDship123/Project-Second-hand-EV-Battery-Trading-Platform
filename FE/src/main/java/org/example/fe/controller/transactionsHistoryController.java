package org.example.fe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/home")
public class transactionsHistoryController {

    @RequestMapping("/home/transactionsHistory")
    public String getTransactionsHistory() {
        return "transactionsHistoryPage";
    }
}
