package org.example.be.service;

import org.example.be.entity.Favorite;
import org.example.be.entity.Member;
import org.example.be.entity.Post;
import org.example.be.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;

    public Favorite createFavorite(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    public Favorite getFavoriteById(Integer id) {
        Optional<Favorite> favorite = favoriteRepository.findById(id);
        return favorite.orElse(null);
    }

    public Favorite updateFavorite(Integer id, Favorite favoriteDetails) {
        Favorite favorite = getFavoriteById(id);
        if (favorite == null) {
            return null;
        }
        favorite.setMember(favoriteDetails.getMember());
        favorite.setPost(favoriteDetails.getPost());
        return favoriteRepository.save(favorite);
    }

    public void deleteFavorite(Integer id) {
        favoriteRepository.deleteById(id);
    }

    public List<Post> getLatestFavoritesPosts(Member member){
        return favoriteRepository.findLatestPostsByMember(member);
    }
}
