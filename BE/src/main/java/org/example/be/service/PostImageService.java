package org.example.be.service;

import org.example.be.entity.PostImage;

import java.util.List;
import java.util.Optional;

public interface PostImageService {
    PostImage createPostImage(PostImage postImage);
    Optional<PostImage> getPostImageById(Integer id);
    List<PostImage> getAllPostImages();
    PostImage updatePostImage(Integer id, PostImage updatedPostImage);
    List<PostImage> getPostImagesByPostId(Integer postId);
    boolean deletePostImage(Integer id);
}