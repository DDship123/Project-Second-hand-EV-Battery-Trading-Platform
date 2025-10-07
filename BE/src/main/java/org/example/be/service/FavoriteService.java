package org.example.be.service;

import org.example.be.entity.Favorite;
import org.example.be.entity.Member;
import org.example.be.entity.Post;
import org.example.be.entity.PostImage;
import org.example.be.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Favorite getFistFavoriteByMemberID(int memberId){
        Optional<Favorite> data = favoriteRepository.findFirstByMemberId(memberId);
        return transferToFavorite(data);
    }
    public List<Favorite> getAllFavoriteByMemberID(int memberId){
        List<Favorite> favorites = favoriteRepository.findAllByMemberId(memberId);
        List<Favorite> result = new ArrayList<>();
        for (Optional<Favorite> data : favorites.stream().map(Optional::of).toList()) {
            result.add(transferToFavorite(data));
        }
        return result;
    }

    public Favorite transferToFavorite( Optional<Favorite> data) {
        Member member = new Member();
        member.setMemberId(data.get().getMember().getMemberId());

        Post post = new Post();
        post.setPostsId(data.get().getPost().getPostsId());
        post.setTitle(data.get().getPost().getTitle());
        post.setPrice(data.get().getPost().getPrice());

        PostImage postImage = new PostImage();
        postImage.setPostImagesId(data.get().getPost().getPostImages().get(0).getPostImagesId());
        postImage.setImageUrl(data.get().getPost().getPostImages().get(0).getImageUrl());

        List<PostImage> list = new ArrayList<>();
        list.add(postImage);
        post.setPostImages(list);

        Member seller = new Member();
        seller.setMemberId(data.get().getPost().getSeller().getMemberId());
        seller.setUsername(data.get().getPost().getSeller().getUsername());

        post.setSeller(seller);
        Favorite favorite = new Favorite();
        favorite.setFavoritesId(data.get().getFavoritesId());
        favorite.setMember(member);
        favorite.setPost(post);
        return favorite;
    }
}
