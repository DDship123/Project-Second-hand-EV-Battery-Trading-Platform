# Repository Layer Documentation - Second-hand EV Battery Trading Platform

## Repository Pattern và JPA Annotations

### 1. @Repository

**Định nghĩa:**
```java
//@Repository
//public interface PostRepository extends JpaRepository<Post, Long> {
//    // Repository methods
//}
//
//@Repository  
//public interface MemberRepository extends JpaRepository<Member, Long> {
//    Optional<Member> findByUsernameAndPassword(String username, String password);
//}
```

**Chức năng:**
- **Stereotype Annotation**: Đánh dấu class/interface là Data Access Layer
- **Exception Translation**: Tự động convert database exceptions thành Spring DataAccessException
- **Component Scan**: Spring Boot tự động detect và register bean
- **Best Practice**: Tách biệt business logic và data access logic

**Tại sao cần @Repository:**
```java
//// Không có @Repository - Raw exception
//public class UserDao {
//    public User findById(Long id) throws SQLException {
//        // SQLException sẽ leak ra ngoài
//    }
//}
//
//// Có @Repository - Wrapped exception  
//@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
//    // SQLException tự động convert thành DataAccessException
//}
```

**Hierarchy trong project:**
```
Controller → Service → Repository → Database
     ↓         ↓          ↓          ↓
  @RestController @Service @Repository  JPA/SQL
```

### 2. JpaRepository<T, ID>

**Định nghĩa:**
```java
//@Repository
//public interface PostRepository extends JpaRepository<Post, Long> {
//    //                                    Entity^   PK Type^
//}
//
//@Repository
//public interface MemberRepository extends JpaRepository<Member, Long> {
//    // Kế thừa tất cả CRUD operations
//}
```

**Chức năng:**
- **CRUD Operations**: Cung cấp sẵn các method cơ bản
- **Pagination**: Support phân trang và sorting
- **Batch Operations**: Insert/Update/Delete nhiều records
- **Query Generation**: Tự động generate SQL từ method names

**Built-in Methods:**
```java
//public interface JpaRepository<T, ID> {
//    // Basic CRUD
//    <S extends T> S save(S entity);           // INSERT/UPDATE
//    Optional<T> findById(ID id);              // SELECT by ID
//    List<T> findAll();                        // SELECT ALL
//    void deleteById(ID id);                   // DELETE by ID
//    void delete(T entity);                    // DELETE entity
//    
//    // Batch operations
//    <S extends T> List<S> saveAll(Iterable<S> entities);
//    void deleteAll();
//    void deleteAllById(Iterable<? extends ID> ids);
//    
//    // Pagination & Sorting
//    Page<T> findAll(Pageable pageable);
//    List<T> findAll(Sort sort);
//    
//    // Utility
//    boolean existsById(ID id);
//    long count();
//}
```

**Ví dụ sử dụng trong PostService:**
```java
//@Service
//public class PostService {
//    @Autowired
//    private PostRepository postRepository;
//    
//    public Post createPost(Post post) {
//        return postRepository.save(post);        // INSERT
//    }
//    
//    public Optional<Post> getPost(Long id) {
//        return postRepository.findById(id);      // SELECT
//    }
//    
//    public List<Post> getAllPosts() {
//        return postRepository.findAll();         // SELECT ALL
//    }
//    
//    public void deletePost(Long id) {
//        postRepository.deleteById(id);          // DELETE
//    }
//}
```

### 3. @Query

**Định nghĩa:**
```java
//@Repository
//public interface PostRepository extends JpaRepository<Post, Long> {
//    
//    @Query("SELECT p FROM Post p WHERE p.product.productType = :productType AND p.status = 'APPROVED' ORDER BY p.createdAt DESC")
//    List<Post> findLatestPostsByType(@Param("productType") String productType, Pageable pageable);
//    
//    @Query("SELECT COUNT(p) FROM Post p WHERE p.product.productType = :productType AND p.status = 'APPROVED'")
//    int countByStatus(@Param("productType") String productType);
//}
```

**Chức năng:**
- **Custom JPQL**: Viết query phức tạp bằng JPQL (Java Persistence Query Language)
- **Native SQL**: Có thể viết raw SQL với nativeQuery=true
- **Complex Logic**: Xử lý business logic phức tạp mà method naming không đủ
- **Performance**: Tối ưu query cho performance tốt hơn

**JPQL vs Native SQL:**
```java
//// JPQL (Java Persistence Query Language) - Entity based
//@Query("SELECT p FROM Post p WHERE p.seller.city = :location")
//List<Post> findByLocation(@Param("location") String location);
//
//// Native SQL - Table based  
//@Query(value = "SELECT * FROM post p JOIN member m ON p.seller_id = m.id WHERE m.city = :location", 
//       nativeQuery = true)
//List<Post> findByLocationNative(@Param("location") String location);
```

**Các loại @Query:**
```java
//// 1. SELECT Query
//@Query("SELECT p FROM Post p WHERE p.product.productType = :type")
//List<Post> findByProductType(@Param("type") String type);
//
//// 2. UPDATE Query
//@Modifying
//@Query("UPDATE Post p SET p.status = :status WHERE p.id = :id")
//int updatePostStatus(@Param("id") Long id, @Param("status") String status);
//
//// 3. DELETE Query
//@Modifying
//@Query("DELETE FROM Post p WHERE p.status = 'INACTIVE'")
//int deleteInactivePosts();
//
//// 4. Complex Join
//@Query("SELECT p FROM Post p " +
//       "JOIN p.product pr " +
//       "JOIN p.seller s " + 
//       "WHERE pr.productType = :type AND s.city = :city")
//List<Post> findPostsByTypeAndCity(@Param("type") String type, @Param("city") String city);
```

### 4. @Param

**Định nghĩa:**
```java
//@Query("SELECT p FROM Post p WHERE p.product.productType = :productType AND p.status = :status")
//List<Post> findByTypeAndStatus(@Param("productType") String type, @Param("status") String status);
//
//@Query("SELECT p FROM Post p WHERE p.createdAt BETWEEN :startDate AND :endDate")
//List<Post> findPostsInDateRange(@Param("startDate") LocalDateTime start, 
//                               @Param("endDate") LocalDateTime end);
```

**Chức năng:**
- **Parameter Binding**: Liên kết tham số method với placeholder trong query
- **Type Safety**: Đảm bảo type safety giữa parameter và query
- **Named Parameters**: Sử dụng tên parameter thay vì position (?, ?)
- **Readability**: Code dễ đọc và maintain hơn

**So sánh cách binding parameters:**
```java
//// 1. @Param (Recommended) - Named parameters
//@Query("SELECT p FROM Post p WHERE p.seller.username = :username AND p.status = :status")
//List<Post> findByUsernameAndStatus(@Param("username") String username, @Param("status") String status);
//
//// 2. Positional parameters (Không khuyến nghị)
//@Query("SELECT p FROM Post p WHERE p.seller.username = ?1 AND p.status = ?2")  
//List<Post> findByUsernameAndStatus(String username, String status);
//
//// 3. SpEL (Spring Expression Language)
//@Query("SELECT p FROM Post p WHERE p.seller.username = #{#username}")
//List<Post> findByUsername(@Param("username") String username);
```

**Lợi ích của Named Parameters:**
```java
//// Dễ hiểu
//@Param("productType") String type     // Rõ ràng type là productType
//@Param("city") String location        // Rõ ràng location là city
//
//// Dễ maintain - thay đổi thứ tự parameters không ảnh hưởng
//@Query("SELECT p FROM Post p WHERE p.seller.city = :city AND p.product.productType = :type")
//List<Post> findPosts(@Param("type") String productType, @Param("city") String city);
```

## Repository Examples trong Project

### PostRepository - Ví dụ thực tế

```java
//@Repository
//public interface PostRepository extends JpaRepository<Post, Long> {
//    
//    // 1. Method name query - Simple
//    List<Post> findByStatus(String status);
//    Optional<Post> findByIdAndStatus(Long id, String status);
//    
//    // 2. @Query với JPQL - Complex  
//    @Query("SELECT p FROM Post p " +
//           "WHERE p.product.productType = :productType " +
//           "AND p.status = 'APPROVED' " +
//           "ORDER BY p.createdAt DESC")
//    List<Post> findLatestPostsByType(@Param("productType") String productType, Pageable pageable);
//    
//    // 3. Count query
//    @Query("SELECT COUNT(p) FROM Post p WHERE p.product.productType = :productType AND p.status = 'APPROVED'")
//    int countByStatus(@Param("productType") String productType);
//    
//    // 4. Complex join với location
//    @Query("SELECT COUNT(p) FROM Post p WHERE p.seller.city = :location " +
//           "AND p.product.productType = :productType AND p.status = 'APPROVED'")
//    int countByLocationAndProductType(@Param("location") String location, 
//                                     @Param("productType") String productType);
//    
//    // 5. Update query
//    @Modifying
//    @Transactional
//    @Query("UPDATE Post p SET p.status = :status WHERE p.id = :id")
//    int updateStatus(@Param("id") Long id, @Param("status") String status);
//}
```

### MemberRepository - Authentication

```java
//@Repository
//public interface MemberRepository extends JpaRepository<Member, Long> {
//    
//    // Method name query
//    Optional<Member> findByUsername(String username);
//    Optional<Member> findByEmail(String email);
//    boolean existsByUsername(String username);
//    boolean existsByEmail(String email);
//    
//    // Custom query cho login
//    @Query("SELECT m FROM Member m WHERE m.username = :username AND m.password = :password")
//    Optional<Member> findByUsernameAndPassword(@Param("username") String username, 
//                                              @Param("password") String password);
//    
//    // Search members
//    @Query("SELECT m FROM Member m WHERE " +
//           "LOWER(m.username) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//           "LOWER(m.fullName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
//    List<Member> searchMembers(@Param("keyword") String keyword);
//}
```

## Best Practices

### 1. Repository Design
```java
//@Repository
//public interface PostRepository extends JpaRepository<Post, Long> {
//    
//    // ✅ Good - Specific method names
//    List<Post> findByStatusAndProductType(String status, String productType);
//    
//    // ✅ Good - Custom query cho complex logic
//    @Query("SELECT p FROM Post p WHERE p.createdAt >= :date ORDER BY p.createdAt DESC")
//    List<Post> findRecentPosts(@Param("date") LocalDateTime date);
//    
//    // ❌ Bad - Quá generic
//    List<Post> findByManyConditions(String a, String b, String c);
//}
```

### 2. Query Optimization
```java
//// ✅ Good - Pagination cho large datasets
//@Query("SELECT p FROM Post p WHERE p.status = :status ORDER BY p.createdAt DESC")
//Page<Post> findActivePostsPaginated(@Param("status") String status, Pageable pageable);
//
//// ✅ Good - Count query riêng để tối ưu
//@Query("SELECT COUNT(p) FROM Post p WHERE p.status = :status")
//long countActivePosts(@Param("status") String status);
//
//// ✅ Good - Fetch join để tránh N+1 problem
//@Query("SELECT p FROM Post p JOIN FETCH p.seller WHERE p.id = :id")
//Optional<Post> findWithSeller(@Param("id") Long id);
```

### 3. Transaction Management
```java
//@Repository
//public interface PostRepository extends JpaRepository<Post, Long> {
//    
//    // ✅ Modifying queries cần @Modifying và @Transactional
//    @Modifying
//    @Transactional
//    @Query("UPDATE Post p SET p.viewCount = p.viewCount + 1 WHERE p.id = :id")
//    int incrementViewCount(@Param("id") Long id);
//    
//    @Modifying  
//    @Transactional
//    @Query("DELETE FROM Post p WHERE p.status = 'DELETED' AND p.createdAt < :date")
//    int cleanupOldDeletedPosts(@Param("date") LocalDateTime date);
//}
```

## Tóm tắt Repository Layer

```
@Repository Interface
        ↓
JpaRepository<Entity, ID>
        ↓  
Built-in Methods + Custom @Query Methods
        ↓
@Param binding + JPQL/Native SQL
        ↓
Database Operations
```

**Workflow:**
1. **@Repository** - Đánh dấu là Data Access Layer
2. **JpaRepository** - Cung cấp CRUD operations cơ bản  
3. **@Query** - Custom queries cho business logic phức tạp
4. **@Param** - Bind parameters an toàn và rõ ràng
5. **Service Layer** - Sử dụng Repository để implement business logic
