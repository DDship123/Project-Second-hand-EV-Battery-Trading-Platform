package org.example.be.repository;

import org.example.be.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    // Lấy tất cả post for you (không lấy của chính mình, status = approved, product active)
    @Query("SELECT p FROM Post p " +
            "WHERE p.status = 'approved' " +
            "AND p.product.status = 'active' " +
            "AND p.seller.memberId <> :memberId " +
            "ORDER BY p.createdAt DESC")
    List<Post> findAllForYou(@Param("memberId") Integer memberId);

    // Lọc thêm theo status cho for-you feed
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

    // Lấy các post mới nhất (giới hạn bằng Pageable)
    @Query("SELECT p FROM Post p ORDER BY p.createdAt DESC")
    List<Post> findLatestPosts(Pageable pageable);

    // Alternative: Find posts for you (không cần filter product/status)
    @Query("SELECT p FROM Post p WHERE p.seller.memberId <> :memberId ORDER BY p.createdAt DESC")
    List<Post> findPostsForYou(@Param("memberId") Integer memberId);

    // Alternative: Find posts for you + filter theo status
    @Query("SELECT p FROM Post p " +
            "WHERE p.seller.memberId <> :memberId " +
            "AND p.status = :status " +
            "ORDER BY p.createdAt DESC")
    List<Post> findPostsForYouByStatus(@Param("memberId") Integer memberId,
                                       @Param("status") String status);
<<<<<<< HEAD

    // Lấy 8 post mới nhất chỉ cho vehicle (case-insensitive)
    @Query("SELECT p FROM Post p " +
            "WHERE LOWER(p.product.productType) = LOWER(:productType) " +
            "AND LOWER(p.status) = LOWER(:status) " +
=======
//    // Lấy 8 post mới nhất chỉ cho vehicle
//    @Query("SELECT p FROM Post p " +
//            "WHERE p.product.productType = 'vehicle' AND p.status = 'approved' " +
//            "ORDER BY p.createdAt DESC")
//    List<Post> findLatestVehiclePosts(Pageable pageable);

    // Lấy 8 post mới nhất chỉ cho vehicle
    @Query("SELECT p FROM Post p " +
            "WHERE p.product.productType = 'vehicle' AND p.status = 'ACTIVE' " +
            "ORDER BY p.createdAt DESC")
    List<Post> findLatestVehiclePosts(Pageable pageable);

//    // Lấy 8 post mới nhất chỉ cho battery
//    @Query("SELECT p FROM Post p " +
//            "WHERE p.product.productType = 'battery' AND p.status = 'approved' " +
//            "ORDER BY p.createdAt DESC")
//    List<Post> findLatestBatteryPosts(Pageable pageable);

    // Lấy 8 post mới nhất chỉ cho battery
    @Query("SELECT p FROM Post p " +
            "WHERE p.product.productType = 'battery' AND p.status = 'ACTIVE' " +
>>>>>>> duong
            "ORDER BY p.createdAt DESC")
    List<Post> findLatestPostsByType(@Param("productType") String productType,
                                     @Param("status") String status,
                                     Pageable pageable);

}