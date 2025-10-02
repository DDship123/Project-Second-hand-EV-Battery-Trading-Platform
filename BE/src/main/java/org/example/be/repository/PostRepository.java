package org.example.be.repository;

import org.example.be.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    // Find posts by member ID
    List<Post> findByMemberId(Integer memberId);

    // Find posts excluding a specific member (for "For You" feed)
    @Query("SELECT p FROM Post p WHERE p.memberId != :memberId ORDER BY p.createdAt DESC")
    List<Post> findPostsForYou(@Param("memberId") Integer memberId);

    // Alternative: Find all posts except user's own, with status filter
    @Query("SELECT p FROM Post p WHERE p.memberId != :memberId AND p.status = :status ORDER BY p.createdAt DESC")
    List<Post> findPostsForYouByStatus(@Param("memberId") Integer memberId, @Param("status") String status);
}