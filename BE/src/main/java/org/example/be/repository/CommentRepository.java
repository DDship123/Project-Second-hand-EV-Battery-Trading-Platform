package org.example.be.repository;

import org.example.be.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByPost_PostsId(Integer postId);

    List<Comment> findAllByStatus(String status);
}
