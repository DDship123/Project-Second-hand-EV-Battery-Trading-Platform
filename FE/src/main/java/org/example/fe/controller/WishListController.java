package org.example.fe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/home")
public class WishListController {
    @RequestMapping("/home/wishList")
    public String getWishList() {
        return "wishListPage";
    }
}
