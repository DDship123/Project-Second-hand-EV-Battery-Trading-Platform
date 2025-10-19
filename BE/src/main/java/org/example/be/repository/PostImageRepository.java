package org.example.be.repository;

import org.example.be.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostImageRepository extends JpaRepository<PostImage, Integer> {
    @Query("SELECT pi FROM PostImage pi WHERE pi.post.postsId = :postId")
    List<PostImage> findByPostsId(Integer postId);
}