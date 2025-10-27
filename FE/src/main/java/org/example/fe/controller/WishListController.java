package org.example.fe.controller;

import jakarta.servlet.http.HttpSession;
import org.example.fe.response.ApiResponse;
import org.example.fe.response.FavoriteResponse;
import org.example.fe.response.MemberResponse;
import org.example.fe.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/home/wishList")
public class WishListController {
    @Autowired
    private WishlistService wishlistService;
    @GetMapping
    public String getWishList(Model model, HttpSession session) {
        MemberResponse memberResponse = (MemberResponse) session.getAttribute("user");
        model.addAttribute("user", memberResponse);
        model.addAttribute("firstFavorite", session.getAttribute("firstFavorite"));
        ApiResponse<List<FavoriteResponse>> apiResponse = wishlistService.getAll(memberResponse.getMemberId());
        model.addAttribute("wishlists", apiResponse.getPayload());
        return "wishListPage";
    }

    @GetMapping("/delete/{favoriteId}")
    public String deleteWishList(@PathVariable Integer favoriteId, RedirectAttributes redirectAttributes) {
        boolean deleted = wishlistService.deleteWishlist(favoriteId).getPayload();
        if (deleted) {
            redirectAttributes.addFlashAttribute("successMessage", "Deleted from wishlist successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete from wishlist.");
        }
        return "redirect:/home/wishList";
    }

    @GetMapping("/add/{postId}")
    public String addWishList(@PathVariable Integer postId, HttpSession session, RedirectAttributes redirectAttributes) {
        MemberResponse memberResponse = (MemberResponse) session.getAttribute("user");
        ApiResponse<FavoriteResponse> apiResponse = wishlistService.addWishlist(memberResponse.getMemberId(), postId);
        if (apiResponse.getPayload() != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Added to wishlist successfully!");
            session.setAttribute("firstFavorite", apiResponse.getPayload());
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add to wishlist. " + apiResponse.getError().get("message"));
        }
        return "redirect:/home";
    }
}
