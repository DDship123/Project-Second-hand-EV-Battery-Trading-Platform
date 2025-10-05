package org.example.be.repository;

import org.example.be.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;


import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    // Lấy tất cả post for you (không lấy của chính mình, status approved, product active)
    @Query("SELECT p FROM Post p " +
            "WHERE p.status = 'approved' " +
            "AND p.product.status = 'active' " +
            "AND p.seller.memberId <> :memberId " +
            "ORDER BY p.createdAt DESC")
    List<Post> findAllForYou(@Param("memberId") Integer memberId);

    // Lọc thêm theo status
    @Query("SELECT p FROM Post p " +
            "WHERE p.status = :status " +
            "AND p.product.status = 'active' " +
            "AND p.seller.memberId <> :memberId " +
            "ORDER BY p.createdAt DESC")
    List<Post> findAllForYouByStatus(@Param("memberId") Integer memberId,
                                     @Param("status") String status);

    // Lấy tất cả post của 1 member
    @Query("SELECT p FROM Post p WHERE p.seller.memberId = :memberId ORDER BY p.createdAt DESC")
    List<Post> findAllByMember(@Param("memberId") Integer memberId);

    @Query("SELECT p FROM Post p ORDER BY p.createdAt DESC")
    List<Post> findLatestPosts(Pageable pageable);
}
