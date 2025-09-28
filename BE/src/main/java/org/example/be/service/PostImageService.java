package org.example.be.service;

import org.example.be.entity.PostImage;
import org.example.be.repository.PostImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostImageService {

    @Autowired
    private PostImageRepository postImageRepository;

    public PostImage createPostImage(PostImage postImage) {
        return postImageRepository.save(postImage);
    }

    public Optional<PostImage> getPostImageById(Integer id) {
        return postImageRepository.findById(id);
    }

    public List<PostImage> getAllPostImages() {
        return postImageRepository.findAll();
    }

    public PostImage updatePostImage(Integer id, PostImage updatedPostImage) {
        Optional<PostImage> existingPostImage = postImageRepository.findById(id);
        if (existingPostImage.isPresent()) {
            PostImage postImage = existingPostImage.get();
            postImage.setPostId(updatedPostImage.getPostId());
            postImage.setImageUrl(updatedPostImage.getImageUrl());
            return postImageRepository.save(postImage);
        }
        return null;
    }

    public void deletePostImage(Integer id) {
        postImageRepository.deleteById(id);
    }
}