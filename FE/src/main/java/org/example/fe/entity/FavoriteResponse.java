package org.example.fe.entity;

public class FavoriteResponse {
    private int favoriteId;
    private int memberId;
    private int postId;

    public FavoriteResponse() {
    }

    public FavoriteResponse(int favoriteId, int memberId, int postId) {
        this.favoriteId = favoriteId;
        this.memberId = memberId;
        this.postId = postId;
    }

    public int getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
