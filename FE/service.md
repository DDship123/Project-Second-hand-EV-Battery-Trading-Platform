# Service Documentation - Spring Service Layer Concepts

## Spring Service Layer - Core Annotations và Architecture

### 1. @Service
**Định nghĩa**: Annotation đánh dấu một class là Service layer component, chứa business logic của ứng dụng.

**Chức năng**:
- Đăng ký class như một Spring Bean với stereotype "service"
- Cho phép Spring Framework tự động phát hiện và quản lý service
- Tách biệt business logic khỏi Controller và Repository layers
- Hỗ trợ Dependency Injection và AOP

**Ví dụ**:
```java
@Service
public class MemberService {
    // Business logic methods
}
```

### 2. Service Layer Architecture

**Vai trò của Service Layer**:
- **Business Logic**: Xử lý các quy tắc nghiệp vụ phức tạp
- **Transaction Management**: Quản lý transactions across multiple operations
- **Data Transformation**: Convert giữa DTOs và Entities
- **Validation**: Validate business rules trước khi persist data
- **Integration**: Tích hợp với external services và APIs

**Kiến trúc phân tầng**:
```
Controller Layer  -> Service Layer -> Repository Layer -> Database
     |                    |                 |
   HTTP Requests      Business Logic    Data Access
   Response Handling   Transactions     Entity Mapping
   Data Binding       Validation       Database Queries
```

### 3. Dependency Injection trong Service

**Constructor Injection (Recommended)**:
```java
@Service
public class MemberService {
    
    private final MemberRepository memberRepository;
    private final EmailService emailService;
    private final CloudinaryService cloudinaryService;
    
    // Constructor injection - bắt buộc dependencies
    public MemberService(MemberRepository memberRepository,
                        EmailService emailService,
                        CloudinaryService cloudinaryService) {
        this.memberRepository = memberRepository;
        this.emailService = emailService;
        this.cloudinaryService = cloudinaryService;
    }
}
```

**Field Injection (Không khuyến khích)**:
```java
@Service
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Autowired
    private EmailService emailService;
}
```

### 4. Service Method Patterns

#### 4.1 CRUD Operations
```java
@Service
public class MemberService {
    
    private final MemberRepository memberRepository;
    
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    
    // Create
    public ApiResponse<MemberResponse> createMember(MemberRequest request) {
        try {
            // Validate input
            if (memberRepository.existsByEmail(request.getEmail())) {
                return ApiResponse.error("Email đã tồn tại");
            }
            
            // Transform DTO to Entity
            Member member = new Member();
            member.setEmail(request.getEmail());
            member.setUsername(request.getUsername());
            member.setPassword(passwordEncoder.encode(request.getPassword()));
            member.setCreatedAt(LocalDateTime.now());
            
            // Save to database
            Member savedMember = memberRepository.save(member);
            
            // Transform Entity to Response DTO
            MemberResponse response = MemberResponse.builder()
                .id(savedMember.getId())
                .email(savedMember.getEmail())
                .username(savedMember.getUsername())
                .createdAt(savedMember.getCreatedAt())
                .build();
            
            return ApiResponse.success(response);
            
        } catch (Exception e) {
            log.error("Error creating member: ", e);
            return ApiResponse.error("Tạo tài khoản thất bại");
        }
    }
    
    // Read
    public ApiResponse<MemberResponse> getMemberById(Long id) {
        try {
            Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
            
            MemberResponse response = convertToResponse(member);
            return ApiResponse.success(response);
            
        } catch (EntityNotFoundException e) {
            return ApiResponse.error("Không tìm thấy thành viên");
        } catch (Exception e) {
            log.error("Error getting member: ", e);
            return ApiResponse.error("Lỗi hệ thống");
        }
    }
    
    // Update
    public ApiResponse<MemberResponse> updateMember(MemberRequest request) {
        try {
            Member member = memberRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
            
            // Update fields
            member.setUsername(request.getUsername());
            member.setPhone(request.getPhone());
            member.setAddress(request.getAddress());
            member.setUpdatedAt(LocalDateTime.now());
            
            Member updatedMember = memberRepository.save(member);
            MemberResponse response = convertToResponse(updatedMember);
            
            return ApiResponse.success(response);
            
        } catch (EntityNotFoundException e) {
            return ApiResponse.error("Không tìm thấy thành viên");
        } catch (Exception e) {
            log.error("Error updating member: ", e);
            return ApiResponse.error("Cập nhật thất bại");
        }
    }
    
    // Delete
    public ApiResponse<Void> deleteMember(Long id) {
        try {
            if (!memberRepository.existsById(id)) {
                return ApiResponse.error("Không tìm thấy thành viên");
            }
            
            memberRepository.deleteById(id);
            return ApiResponse.success(null);
            
        } catch (Exception e) {
            log.error("Error deleting member: ", e);
            return ApiResponse.error("Xóa thành viên thất bại");
        }
    }
}
```

#### 4.2 Business Logic Methods
```java
@Service
public class TransactionService {
    
    private final TransactionRepository transactionRepository;
    private final MemberService memberService;
    private final EmailService emailService;
    
    public TransactionService(TransactionRepository transactionRepository,
                            MemberService memberService,
                            EmailService emailService) {
        this.transactionRepository = transactionRepository;
        this.memberService = memberService;
        this.emailService = emailService;
    }
    
    @Transactional
    public ApiResponse<TransactionResponse> createTransaction(TransactionRequest request) {
        try {
            // Business validation
            if (!isValidTransaction(request)) {
                return ApiResponse.error("Giao dịch không hợp lệ");
            }
            
            // Check member balance
            Member buyer = memberService.getMemberById(request.getBuyerId()).getPayload();
            if (buyer.getBalance() < request.getAmount()) {
                return ApiResponse.error("Số dư không đủ");
            }
            
            // Create transaction
            Transaction transaction = new Transaction();
            transaction.setBuyerId(request.getBuyerId());
            transaction.setSellerId(request.getSellerId());
            transaction.setPostId(request.getPostId());
            transaction.setAmount(request.getAmount());
            transaction.setStatus(TransactionStatus.PENDING);
            transaction.setCreatedAt(LocalDateTime.now());
            
            Transaction savedTransaction = transactionRepository.save(transaction);
            
            // Update member balances
            updateMemberBalances(request.getBuyerId(), request.getSellerId(), request.getAmount());
            
            // Send notification emails
            sendTransactionNotifications(savedTransaction);
            
            TransactionResponse response = convertToResponse(savedTransaction);
            return ApiResponse.success(response);
            
        } catch (Exception e) {
            log.error("Error creating transaction: ", e);
            return ApiResponse.error("Tạo giao dịch thất bại");
        }
    }
    
    private boolean isValidTransaction(TransactionRequest request) {
        return request.getBuyerId() != null 
            && request.getSellerId() != null
            && request.getAmount() > 0
            && !request.getBuyerId().equals(request.getSellerId());
    }
    
    @Transactional
    private void updateMemberBalances(Long buyerId, Long sellerId, Double amount) {
        // Deduct from buyer
        Member buyer = memberRepository.findById(buyerId).orElseThrow();
        buyer.setBalance(buyer.getBalance() - amount);
        memberRepository.save(buyer);
        
        // Add to seller (hold in escrow)
        Member seller = memberRepository.findById(sellerId).orElseThrow();
        seller.setEscrowBalance(seller.getEscrowBalance() + amount);
        memberRepository.save(seller);
    }
    
    private void sendTransactionNotifications(Transaction transaction) {
        // Send email to buyer
        emailService.sendTransactionCreatedEmail(
            transaction.getBuyerId(), 
            transaction.getId()
        );
        
        // Send email to seller
        emailService.sendNewOrderEmail(
            transaction.getSellerId(), 
            transaction.getId()
        );
    }
}
```

### 5. Error Handling và Validation

#### 5.1 Custom Exceptions
```java
// Custom Exception Classes
public class BusinessException extends RuntimeException {
    private final String errorCode;
    
    public BusinessException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(String message) {
        super(message, "ENTITY_NOT_FOUND");
    }
}

public class InsufficientBalanceException extends BusinessException {
    public InsufficientBalanceException(String message) {
        super(message, "INSUFFICIENT_BALANCE");
    }
}
```

#### 5.2 Service với Exception Handling
```java
@Service
public class PostService {
    
    private final PostRepository postRepository;
    private final CloudinaryService cloudinaryService;
    
    public ApiResponse<PostResponse> createPost(PostRequest request, List<MultipartFile> images) {
        try {
            // Validate input
            validatePostRequest(request);
            
            // Upload images
            List<String> imageUrls = uploadImages(images);
            
            // Create post
            Post post = new Post();
            post.setTitle(request.getTitle());
            post.setDescription(request.getDescription());
            post.setPrice(request.getPrice());
            post.setImages(imageUrls);
            post.setStatus(PostStatus.ACTIVE);
            post.setCreatedAt(LocalDateTime.now());
            
            Post savedPost = postRepository.save(post);
            PostResponse response = convertToResponse(savedPost);
            
            return ApiResponse.success(response);
            
        } catch (ValidationException e) {
            return ApiResponse.error(e.getMessage());
        } catch (CloudinaryException e) {
            log.error("Error uploading images: ", e);
            return ApiResponse.error("Tải hình ảnh thất bại");
        } catch (Exception e) {
            log.error("Error creating post: ", e);
            return ApiResponse.error("Tạo bài đăng thất bại");
        }
    }
    
    private void validatePostRequest(PostRequest request) {
        if (StringUtils.isBlank(request.getTitle())) {
            throw new ValidationException("Tiêu đề không được để trống");
        }
        
        if (request.getPrice() <= 0) {
            throw new ValidationException("Giá phải lớn hơn 0");
        }
        
        if (StringUtils.isBlank(request.getDescription())) {
            throw new ValidationException("Mô tả không được để trống");
        }
    }
    
    private List<String> uploadImages(List<MultipartFile> images) {
        if (images.isEmpty()) {
            throw new ValidationException("Phải có ít nhất 1 hình ảnh");
        }
        
        if (images.size() > 4) {
            throw new ValidationException("Tối đa 4 hình ảnh");
        }
        
        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile image : images) {
            try {
                String imageUrl = cloudinaryService.uploadImage(image);
                imageUrls.add(imageUrl);
            } catch (Exception e) {
                throw new CloudinaryException("Không thể tải hình ảnh: " + image.getOriginalFilename());
            }
        }
        
        return imageUrls;
    }
}
```

### 6. Transaction Management

#### 6.1 @Transactional Annotation
```java
@Service
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final InventoryService inventoryService;
    private final PaymentService paymentService;
    private final EmailService emailService;
    
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse<OrderResponse> processOrder(OrderRequest request) {
        try {
            // 1. Create order
            Order order = createOrder(request);
            
            // 2. Reserve inventory
            inventoryService.reserveItems(request.getItems());
            
            // 3. Process payment
            PaymentResult paymentResult = paymentService.processPayment(
                request.getPaymentInfo(), 
                order.getTotalAmount()
            );
            
            if (!paymentResult.isSuccess()) {
                throw new PaymentException("Thanh toán thất bại");
            }
            
            // 4. Update order status
            order.setStatus(OrderStatus.PAID);
            order.setPaymentId(paymentResult.getPaymentId());
            orderRepository.save(order);
            
            // 5. Send confirmation email
            emailService.sendOrderConfirmation(order);
            
            OrderResponse response = convertToResponse(order);
            return ApiResponse.success(response);
            
        } catch (PaymentException e) {
            log.error("Payment failed for order: ", e);
            return ApiResponse.error("Thanh toán thất bại");
        } catch (InventoryException e) {
            log.error("Inventory error: ", e);
            return ApiResponse.error("Sản phẩm không đủ số lượng");
        } catch (Exception e) {
            log.error("Error processing order: ", e);
            return ApiResponse.error("Xử lý đơn hàng thất bại");
        }
    }
    
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        
        if (order.getStatus() == OrderStatus.CANCELLED) {
            throw new BusinessException("Đơn hàng đã bị hủy", "ORDER_ALREADY_CANCELLED");
        }
        
        // Release inventory
        inventoryService.releaseReservedItems(order.getItems());
        
        // Refund payment if applicable
        if (order.getStatus() == OrderStatus.PAID) {
            paymentService.refundPayment(order.getPaymentId(), order.getTotalAmount());
        }
        
        // Update order status
        order.setStatus(OrderStatus.CANCELLED);
        order.setCancelledAt(LocalDateTime.now());
        orderRepository.save(order);
        
        // Send cancellation email
        emailService.sendOrderCancellation(order);
    }
}
```

#### 6.2 Transaction Propagation
```java
@Service
public class AuditService {
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logActivity(String action, Long userId, String details) {
        // Luôn tạo transaction mới, không bị rollback khi parent transaction fail
        AuditLog log = new AuditLog();
        log.setAction(action);
        log.setUserId(userId);
        log.setDetails(details);
        log.setTimestamp(LocalDateTime.now());
        
        auditLogRepository.save(log);
    }
    
    @Transactional(propagation = Propagation.MANDATORY)
    public void validateBusinessRules(Object entity) {
        // Yêu cầu phải có transaction, ném exception nếu không có
        // Validation logic here
    }
}
```

### 7. Integration với External Services

#### 7.1 HTTP Client Service
```java
@Service
public class ExternalApiService {
    
    private final RestTemplate restTemplate;
    private final RetryTemplate retryTemplate;
    
    @Value("${external.api.base-url}")
    private String baseUrl;
    
    @Value("${external.api.api-key}")
    private String apiKey;
    
    public ExternalApiService(RestTemplate restTemplate, RetryTemplate retryTemplate) {
        this.restTemplate = restTemplate;
        this.retryTemplate = retryTemplate;
    }
    
    public ApiResponse<ExternalDataResponse> fetchExternalData(String dataId) {
        try {
            return retryTemplate.execute(context -> {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setBearerAuth(apiKey);
                
                HttpEntity<String> entity = new HttpEntity<>(headers);
                
                String url = baseUrl + "/data/" + dataId;
                ResponseEntity<ExternalDataResponse> response = restTemplate.exchange(
                    url, 
                    HttpMethod.GET, 
                    entity, 
                    ExternalDataResponse.class
                );
                
                return ApiResponse.success(response.getBody());
            });
            
        } catch (Exception e) {
            log.error("Error fetching external data: ", e);
            return ApiResponse.error("Không thể lấy dữ liệu từ hệ thống bên ngoài");
        }
    }
}
```

#### 7.2 File Upload Service
```java
@Service
public class CloudinaryService {
    
    private final Cloudinary cloudinary;
    
    public CloudinaryService(@Value("${cloudinary.cloud-name}") String cloudName,
                           @Value("${cloudinary.api-key}") String apiKey,
                           @Value("${cloudinary.api-secret}") String apiSecret) {
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", cloudName,
            "api_key", apiKey,
            "api_secret", apiSecret
        ));
    }
    
    public String uploadImage(MultipartFile file) {
        try {
            // Validate file
            validateImage(file);
            
            // Resize and optimize image
            byte[] optimizedImage = optimizeImage(file);
            
            // Upload to Cloudinary
            Map uploadResult = cloudinary.uploader().upload(optimizedImage,
                ObjectUtils.asMap(
                    "folder", "my_app_images",
                    "resource_type", "image",
                    "quality", "auto",
                    "format", "jpg",
                    "transformation", "c_limit,w_800,h_600,q_auto"
                ));
            
            return (String) uploadResult.get("secure_url");
            
        } catch (IOException e) {
            log.error("Error uploading image to Cloudinary: ", e);
            throw new FileUploadException("Tải hình ảnh thất bại");
        }
    }
    
    private void validateImage(MultipartFile file) {
        if (file.isEmpty()) {
            throw new ValidationException("File không được để trống");
        }
        
        if (file.getSize() > 10 * 1024 * 1024) { // 10MB
            throw new ValidationException("File quá lớn (tối đa 10MB)");
        }
        
        String contentType = file.getContentType();
        if (!Arrays.asList("image/jpeg", "image/png", "image/jpg").contains(contentType)) {
            throw new ValidationException("Chỉ hỗ trợ file JPG, JPEG, PNG");
        }
    }
    
    private byte[] optimizeImage(MultipartFile file) throws IOException {
        // Image optimization logic
        BufferedImage image = ImageIO.read(file.getInputStream());
        
        // Resize if too large
        if (image.getWidth() > 1200 || image.getHeight() > 1200) {
            image = resizeImage(image, 1200, 1200);
        }
        
        // Convert to JPEG and compress
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        
        return baos.toByteArray();
    }
}
```

### 8. Caching trong Service Layer

```java
@Service
public class ProductService {
    
    private final ProductRepository productRepository;
    
    @Cacheable(value = "products", key = "#id")
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        
        return convertToResponse(product);
    }
    
    @Cacheable(value = "products", key = "'category-' + #category")
    public List<ProductResponse> getProductsByCategory(String category) {
        List<Product> products = productRepository.findByCategory(category);
        return products.stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }
    
    @CacheEvict(value = "products", key = "#product.id")
    public ProductResponse updateProduct(ProductRequest product) {
        // Update logic
        Product updatedProduct = productRepository.save(convertToEntity(product));
        return convertToResponse(updatedProduct);
    }
    
    @CacheEvict(value = "products", allEntries = true)
    public void clearProductCache() {
        // Clear all product cache entries
    }
}
```

### 9. Service Testing

#### 9.1 Unit Test
```java
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    
    @Mock
    private MemberRepository memberRepository;
    
    @Mock
    private PasswordEncoder passwordEncoder;
    
    @InjectMocks
    private MemberService memberService;
    
    @Test
    void createMember_Success() {
        // Given
        MemberRequest request = MemberRequest.builder()
            .email("test@example.com")
            .username("testuser")
            .password("password123")
            .build();
        
        Member savedMember = new Member();
        savedMember.setId(1L);
        savedMember.setEmail(request.getEmail());
        savedMember.setUsername(request.getUsername());
        
        when(memberRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(memberRepository.save(any(Member.class))).thenReturn(savedMember);
        
        // When
        ApiResponse<MemberResponse> result = memberService.createMember(request);
        
        // Then
        assertThat(result.getStatus()).isEqualTo("SUCCESS");
        assertThat(result.getPayload().getEmail()).isEqualTo("test@example.com");
        
        verify(memberRepository).existsByEmail(request.getEmail());
        verify(memberRepository).save(any(Member.class));
    }
    
    @Test
    void createMember_EmailExists_ReturnsError() {
        // Given
        MemberRequest request = MemberRequest.builder()
            .email("existing@example.com")
            .build();
        
        when(memberRepository.existsByEmail(request.getEmail())).thenReturn(true);
        
        // When
        ApiResponse<MemberResponse> result = memberService.createMember(request);
        
        // Then
        assertThat(result.getStatus()).isEqualTo("ERROR");
        assertThat(result.getMessage()).isEqualTo("Email đã tồn tại");
        
        verify(memberRepository, never()).save(any(Member.class));
    }
}
```

## Best Practices

### 1. Service Design Principles
- **Single Responsibility**: Mỗi service chỉ chịu trách nhiệm cho một domain cụ thể
- **Dependency Inversion**: Depend on abstractions, not concretions
- **Open/Closed**: Open for extension, closed for modification
- **Interface Segregation**: Tạo interfaces nhỏ và focused

### 2. Error Handling
- Sử dụng custom exceptions với meaningful messages
- Log errors với appropriate log levels
- Return consistent response format (ApiResponse)
- Handle expected vs unexpected errors differently

### 3. Performance Optimization
- Sử dụng caching cho frequently accessed data
- Implement pagination cho large datasets
- Use async processing cho long-running operations
- Optimize database queries và avoid N+1 problems

### 4. Security Considerations
- Validate inputs tại service layer
- Implement authorization checks
- Sanitize data trước khi persist
- Use secure communication cho external services

### 5. Monitoring và Logging
- Log important business operations
- Use metrics để monitor performance
- Implement health checks
- Track business KPIs

```java
@Service
@Slf4j
public class OrderService {
    
    private final MeterRegistry meterRegistry;
    private final Timer.Sample sample;
    
    @EventListener
    public void handleOrderCreated(OrderCreatedEvent event) {
        log.info("Order created: {}", event.getOrderId());
        meterRegistry.counter("orders.created", "type", event.getOrderType()).increment();
    }
    
    public ApiResponse<OrderResponse> processOrder(OrderRequest request) {
        Timer.Sample sample = Timer.start(meterRegistry);
        
        try {
            // Process order logic
            ApiResponse<OrderResponse> response = doProcessOrder(request);
            
            sample.stop(Timer.builder("order.processing.time")
                .tag("status", "success")
                .register(meterRegistry));
            
            return response;
            
        } catch (Exception e) {
            sample.stop(Timer.builder("order.processing.time")
                .tag("status", "error")
                .register(meterRegistry));
            throw e;
        }
    }
}
```

## Tổng kết

Service layer là trung tâm của business logic trong Spring application. Nó cung cấp:

1. **Separation of Concerns**: Tách biệt business logic khỏi presentation và data access
2. **Transaction Management**: Đảm bảo data consistency
3. **Reusability**: Business logic có thể được sử dụng bởi multiple controllers
4. **Testability**: Dễ dàng unit test business logic
5. **Maintainability**: Centralized business rules dễ maintain và modify

Nhớ rằng Service layer không chỉ là CRUD operations mà còn chứa complex business logic, validation, integration với external systems, và orchestration của multiple operations để complete một business use case.
