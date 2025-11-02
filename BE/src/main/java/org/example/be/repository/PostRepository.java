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

    // --- FOR YOU ---
    // Lấy tất cả post mà CHÍNH member đó đăng (status = APPROVED)
    @Query("SELECT p FROM Post p " +
            "WHERE p.seller.memberId = :memberId " +
            "AND p.status = 'APPROVED' " +
            "ORDER BY p.createdAt DESC")
    List<Post> findAllForYou(@Param("memberId") Integer memberId);

    // Lọc thêm theo status nếu cần (APPROVED / PENDING / REJECTED ...)
    @Query("SELECT p FROM Post p " +
            "WHERE p.seller.memberId = :memberId " +
            "AND p.status = :status " +
            "ORDER BY p.createdAt DESC")
    List<Post> findAllForYouByStatus(@Param("memberId") Integer memberId,
                                     @Param("status") String status);


    // --- MEMBER POSTS (tổng quát, không lọc status) ---
    @Query("SELECT p FROM Post p WHERE p.seller.memberId = :memberId ORDER BY p.createdAt DESC")
    List<Post> findAllByMember(@Param("memberId") Integer memberId);


    // --- LATEST POSTS (chỉ lấy bài APPROVED) ---
    @Query("SELECT p FROM Post p WHERE p.status = 'APPROVED' ORDER BY p.createdAt DESC")
    List<Post> findLatestPosts(Pageable pageable);

    @Query("SELECT p FROM Post p " +
            "WHERE p.product.productType = 'vehicle' " +
            "AND p.status = 'APPROVED' " +
            "ORDER BY p.createdAt DESC")
    List<Post> findLatestVehiclePosts(Pageable pageable);

    @Query("SELECT p FROM Post p " +
            "WHERE p.product.productType = :productType " +
            "AND p.status = 'APPROVED' " +
            "ORDER BY p.createdAt DESC")
    List<Post> findLatestPostsByType(@Param("productType") String productType,
                                     Pageable pageable);

    @Query("SELECT COUNT(p) FROM Post p WHERE p.product.productType = :productType AND p.status = 'APPROVED'")
    int countByStatus(@Param("productType") String productType);

    @Query("SELECT COUNT(p) FROM Post p WHERE p.seller.city = :location " +
            "AND p.product.productType = :productType AND p.status = 'APPROVED'")
    int countByLocationAndProductType(@Param("location")String location,@Param("productType") String productType);


    // --- FILTER BY LOCATION ---
    @Query("SELECT p FROM Post p WHERE p.seller.city = :city AND p.status = 'APPROVED'")
    List<Post> findAllPostByMemberCity(@Param("city") String city);

    @Query("SELECT p FROM Post p " +
            "WHERE p.seller.city = :city " +
            "AND p.product.productType = :productType " +
            "AND p.status = 'APPROVED'")
    List<Post> findAllPostsByMemberCityAndProductType(@Param("productType") String productType,
                                                      @Param("city") String city);

    @Query("SELECT p FROM Post p " +
            "WHERE p.seller.city = :city " +
            "AND p.product.productType = :productType " +
            "AND LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%')) " +
            "AND p.status = 'APPROVED'")
    List<Post> findAllPostsByMemberCityAndProductTypeAndStatus(@Param("productType") String productType,
                                                               @Param("city") String city,
                                                               @Param("title") String title);


    // --- ADMIN / STATUS FILTER ---(Tân)
    @Query("SELECT p FROM Post p WHERE p.status = :status ORDER BY p.createdAt DESC")
    List<Post> findAllByStatusOrderByCreatedAtDesc(@Param("status") String status);
    // Lấy tất cả post theo nhiều trạng thái (chờ, duyệt, xóa) sắp xếp mới nhất lên đầu (Tân)
    @Query("SELECT p FROM Post p WHERE p.status IN :statuses ORDER BY p.createdAt DESC")
    List<Post> findAllByStatusInOrderByCreatedAtDesc(@Param("statuses") List<String> statuses);
    // --- SEARCH POSTS BY PRODUCT TYPE AND TITLE ---(Tân)
    @Query("SELECT p FROM Post p " +
            "WHERE p.product.productType = :productType " +
            "AND LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%')) " +
            "AND p.status = 'APPROVED'")
    List<Post> findAllPostByProductTypeAndPostTitle(@Param("productType") String productType, @Param("title") String title);

    @Query("SELECT COUNT(p) FROM Post p WHERE p.seller.memberId = :memberId AND p.status != 'REJECTED'")
    Integer countBySeller_MemberId(int memberId);
}
