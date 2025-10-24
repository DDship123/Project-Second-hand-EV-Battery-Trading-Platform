package org.example.be.dto.response;

public class FavoriteResponse {
    private int favoritesId;
    private MemberResponse member;
    private PostResponse post;
    // Constructors
    public FavoriteResponse() {
    }
    public FavoriteResponse(int favoritesId, MemberResponse member, PostResponse post) {
        this.favoritesId = favoritesId;
        this.member = member;
        this.post = post;
    }
    // Getters and Setters
    public int getFavoritesId() {
        return favoritesId;
    }
    public void setFavoritesId(int favoritesId) {
        this.favoritesId = favoritesId;
    }
    public MemberResponse getMember() {
        return member;
    }
    public void setMember(MemberResponse member) {
        this.member = member;
    }
    public PostResponse getPost() {
        return post;
    }
    public void setPost(PostResponse post) {
        this.post = post;
    }
}
