package org.example.be.service;

import org.example.be.entity.Favorite;
import org.example.be.entity.Member;
import org.example.be.entity.Post;

import java.util.List;
import java.util.Optional;

public interface FavoriteService {
    public Favorite createFavorite(Favorite favorite);
    public List<Favorite> getAllFavorites();
    public Favorite getFavoriteById(Integer id);
    public Favorite updateFavorite(Integer id, Favorite favoriteDetails);
    public void deleteFavorite(Integer id);
    public List<Post> getLatestFavoritesPosts(Member member);
    public Favorite getFistFavoriteByMemberID(int memberId);
    public List<Favorite> getAllFavoriteByMemberID(int memberId);
    public Favorite transferToFavorite(Optional<Favorite> data);

}
