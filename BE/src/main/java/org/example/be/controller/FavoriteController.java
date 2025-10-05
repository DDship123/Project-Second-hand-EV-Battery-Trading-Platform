package org.example.be.controller;

import org.example.be.dto.ApiResponse;
import org.example.be.entity.Favorite;
import org.example.be.entity.Member;
import org.example.be.entity.Post;
import org.example.be.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @PostMapping
    public Favorite createFavorite(@RequestBody Favorite favorite) {
        return favoriteService.createFavorite(favorite);
    }

    @GetMapping
    public List<Favorite> getAllFavorites() {
        return favoriteService.getAllFavorites();
    }

    @GetMapping("/{id}")
    public Favorite getFavoriteById(@PathVariable Integer id) {
        return favoriteService.getFavoriteById(id);
    }

    @PutMapping("/{id}")
    public Favorite updateFavorite(@PathVariable Integer id, @RequestBody Favorite favorite) {
        return favoriteService.updateFavorite(id, favorite);
    }

    @DeleteMapping("/{id}")
    public void deleteFavorite(@PathVariable Integer id) {
        favoriteService.deleteFavorite(id);
    }

    @GetMapping("/latest")
    public ResponseEntity<ApiResponse<List<Post>>> getLatestFavorites(Member member) {
        List<Post> latestFavorites = favoriteService.getLatestFavoritesPosts(member);
        ApiResponse<List<Post>> response = new ApiResponse<>();
        response.ok(latestFavorites);
        return ResponseEntity.ok(response);
    }
}
