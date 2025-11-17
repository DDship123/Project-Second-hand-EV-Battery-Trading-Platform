package org.example.be.service.impl;

import org.example.be.entity.PostImage;
import org.example.be.repository.PostImageRepository;
import org.example.be.service.PostImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostImageServiceImpl implements PostImageService {

    @Autowired
    private PostImageRepository postImageRepository;

    @Override
    public PostImage createPostImage(PostImage postImage) {
        return postImageRepository.save(postImage);
    }

    @Override
    public Optional<PostImage> getPostImageById(Integer id) {
        return postImageRepository.findById(id);
    }

    @Override
    public List<PostImage> getAllPostImages() {
        return postImageRepository.findAll();
    }

    @Override
    public PostImage updatePostImage(Integer id, PostImage updatedPostImage) {
        Optional<PostImage> existingPostImage = postImageRepository.findById(id);
        if (existingPostImage.isPresent()) {
            PostImage postImage = existingPostImage.get();
            postImage.setPost(updatedPostImage.getPost());
            postImage.setImageUrl(updatedPostImage.getImageUrl());
            return postImageRepository.save(postImage);
        }
        return null;
    }

    @Override
    public List<PostImage> getPostImagesByPostId(Integer postId) {
        return postImageRepository.findByPostsId(postId);
    }

    @Override
    public boolean deletePostImage(Integer id) {
        Optional<PostImage> existing = postImageRepository.findById(id);
        if (existing.isPresent()) {
            postImageRepository.delete(existing.get());
            return true;
        } else {
            return false;
        }
    }
}