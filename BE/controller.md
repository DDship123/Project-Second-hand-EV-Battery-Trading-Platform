# Project Documentation - Second-hand EV Battery Trading Platform

// ...existing code...

## Best Practices

// ...existing code...

4. **Security**: Hash password, validate token

## Spring Boot Annotations - Giải thích chi tiết

### 1. @RestController

**Định nghĩa:**
```java
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    // ...
}
```

**Chức năng:**
- **Kết hợp @Controller + @ResponseBody**: Tự động serialize object thành JSON
- **RESTful API**: Tạo REST API endpoints thay vì trả về view (HTML)
- **Response tự động**: Tất cả method sẽ trả về dữ liệu JSON, không cần @ResponseBody
- **Stereotype annotation**: Spring tự động detect và register bean

**So sánh:**
```java
 Với @Controller (truyền thống)
@Controller
public class WebController {
    @RequestMapping("/user")
    @ResponseBody  // Cần thêm @ResponseBody để trả JSON
    public User getUser() {
        return new User();
    }
}

// Với @RestController (hiện đại)
@RestController  
public class ApiController {
    @RequestMapping("/user")
    public User getUser() {  // Tự động trả JSON
        return new User();
    }
}
```

### 2. @RequestMapping

**Định nghĩa:**
```java
@RestController
@RequestMapping("/api/auth")  // Base URL cho toàn controller
public class AuthController {
    
    @PostMapping("/login")    // Tương đương @RequestMapping(value="/login", method=POST)
    public ResponseEntity<?> login() {
        // URL cuối cùng: /api/auth/login
    }
}
```

**Chức năng:**
- **URL Mapping**: Ánh xạ HTTP request tới method cụ thể
- **Base Path**: Khi dùng ở class level, tạo base URL cho tất cả methods
- **HTTP Methods**: Hỗ trợ GET, POST, PUT, DELETE, PATCH
- **Parameters**: Có thể nhận path variables, query parameters

**Các biến thể:**
```java
@RequestMapping(value = "/api/auth", method = RequestMethod.POST)
@PostMapping("/api/auth")        // Shortcut cho POST
@GetMapping("/api/auth")         // Shortcut cho GET  
@PutMapping("/api/auth")         // Shortcut cho PUT
@DeleteMapping("/api/auth")      // Shortcut cho DELETE
```

### 3. @Autowired

**Định nghĩa:**
```java
@RestController
public class AuthController {
    
    @Autowired
    private MemberService memberService;  // Dependency Injection
    
    @Autowired
    private MemberPlanUsageService memberPlanUsageService;
}
```

**Chức năng:**
- **Dependency Injection (DI)**: Tự động inject bean dependencies
- **IoC Container**: Spring tự động tìm và inject bean phù hợp
- **Loose Coupling**: Giảm sự phụ thuộc cứng giữa các class
- **Auto-wiring**: Không cần manually tạo object

**Các cách inject:**
```java
public class AuthController {
    
    // 1. Field Injection (đang dùng)
    @Autowired
    private MemberService memberService;
    
    // 2. Constructor Injection (recommended)
    private final MemberService memberService;
    
    @Autowired
    public AuthController(MemberService memberService) {
        this.memberService = memberService;
    }
    
    // 3. Setter Injection
    private MemberService memberService;
    
    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
}
```

**Constructor Injection được khuyến nghị vì:**
- Immutable dependencies (final fields)
- Mandatory dependencies rõ ràng
- Easy testing (không cần Spring context)
- Fail-fast nếu thiếu dependency

### 4. @PostMapping

**Định nghĩa:**
```java
@PostMapping("/register")
public ResponseEntity<ApiResponse<Member>> register(@RequestBody MemberRegisterRequest request) {
    // Xử lý HTTP POST request tới /register
}

@PostMapping("/login")  
public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginRequest request) {
    // Xử lý HTTP POST request tới /login
}
```

**Chức năng:**
- **HTTP POST**: Chỉ xử lý POST requests
- **Shortcut annotation**: Thay thế @RequestMapping(method = RequestMethod.POST)
- **Data Creation**: Thường dùng cho tạo mới dữ liệu (register, login, create)
- **Request Body**: Thường nhận dữ liệu từ request body

**Các HTTP Methods khác:**
```java
@GetMapping("/users")       // Lấy dữ liệu - SELECT
@PostMapping("/users")      // Tạo mới - INSERT  
@PutMapping("/users/{id}")  // Cập nhật toàn bộ - UPDATE
@PatchMapping("/users/{id}") // Cập nhật một phần - PARTIAL UPDATE
@DeleteMapping("/users/{id}") // Xóa - DELETE
```

### 5. @RequestBody

**Định nghĩa:**
```java
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    // request được deserialize từ JSON trong HTTP body
}

@PostMapping("/register")  
public ResponseEntity<?> register(@RequestBody MemberRegisterRequest request) {
    // request được tạo từ JSON payload
}
```

**Chức năng:**
- **JSON to Object**: Tự động convert JSON từ request body thành Java object
- **Deserialization**: Sử dụng Jackson để parse JSON
- **HTTP Body**: Đọc dữ liệu từ request body (không phải URL parameters)
- **Content-Type**: Thường dùng với "application/json"

**Ví dụ thực tế:**

**HTTP Request:**
```bash
POST /api/auth/login
Content-Type: application/json

{
    "username": "user123",
    "password": "password123"
}
```

**Java Object:**
```java
public class LoginRequest {
    private String username;
    private String password;
    
    // getters và setters
}

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    // request.getUsername() = "user123"
    // request.getPassword() = "password123"
}
```

**So sánh với các annotation khác:**
```java
// @RequestBody - Từ HTTP body (JSON)
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) { }

// @RequestParam - Từ URL parameters  
@GetMapping("/search")
public List<User> search(@RequestParam String keyword) {
    // URL: /search?keyword=john
}

// @PathVariable - Từ URL path
@GetMapping("/users/{id}")
public User getUser(@PathVariable Long id) {
    // URL: /users/123 -> id = 123
}

// @RequestHeader - Từ HTTP headers
@PostMapping("/upload")
public String upload(@RequestHeader("Authorization") String token) { }
```

## Tóm tắt workflow trong AuthController

```java
@RestController                    // 1. Đánh dấu đây là REST API controller
@RequestMapping("/api/auth")       // 2. Base URL cho tất cả endpoints
public class AuthController {
    
    @Autowired                     // 3. Inject service dependencies
    private MemberService memberService;
    
    @PostMapping("/login")         // 4. Handle POST /api/auth/login
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        //                         // 5. Convert JSON body thành LoginRequest object
        Optional<Member> user = memberService.login(request);
        // 6. Trả về JSON response
        return ResponseEntity.ok(response);
    }
}
```

**Request Flow:**
1. Client gửi POST request tới `/api/auth/login` với JSON body
2. Spring Boot nhận request và route tới `AuthController.login()`
3. `@RequestBody` convert JSON thành `LoginRequest` object
4. Method xử lý business logic với injected services
5. Trả về `ResponseEntity` được auto-convert thành JSON response

## Thêm các Annotations và Classes quan trọng

### 6. @PathVariable

**Định nghĩa:**
```java
//@GetMapping("/users/{id}")
//public ResponseEntity<User> getUserById(@PathVariable Long id) {
//    // URL: /users/123 -> id = 123
//    User user = userService.findById(id);
//    return ResponseEntity.ok(user);
//}
//
//@GetMapping("/posts/{postId}/comments/{commentId}")
//public ResponseEntity<Comment> getComment(@PathVariable Long postId, 
//                                        @PathVariable Long commentId) {
//    // URL: /posts/456/comments/789 -> postId = 456, commentId = 789
//}
```

**Chức năng:**
- **URL Path Parameters**: Lấy giá trị từ URL path segments
- **Dynamic URLs**: Tạo RESTful URLs với tham số động
- **Type Conversion**: Tự động convert String sang các kiểu dữ liệu khác
- **Required by default**: PathVariable bắt buộc phải có trong URL

**Ví dụ trong VnpayController:**
```java
//@GetMapping("/transaction/{txnRef}")
//public ResponseEntity<Transaction> getTransaction(@PathVariable String txnRef) {
//    // URL: /api/v1/payments/vnpay/transaction/VNP123456
//    // txnRef = "VNP123456"
//}
```

**Tùy chọn nâng cao:**
```java
//// Tên biến khác tên trong URL
//@GetMapping("/users/{userId}")
//public User getUser(@PathVariable("userId") Long id) { }
//
//// Optional PathVariable  
//@GetMapping({"/products", "/products/{category}"})
//public List<Product> getProducts(@PathVariable(required = false) String category) {
//    // URL: /products hoặc /products/battery
//}
```

### 7. @RequestParam

**Định nghĩa:**
```java
//@GetMapping("/search")
//public ResponseEntity<List<Post>> searchPosts(@RequestParam String keyword,
//                                            @RequestParam(defaultValue = "0") int page,
//                                            @RequestParam(defaultValue = "10") int size) {
//    // URL: /search?keyword=battery&page=1&size=20
//    // keyword = "battery", page = 1, size = 20
//}
```

**Chức năng:**
- **Query Parameters**: Lấy giá trị từ URL query string (?param=value)
- **Optional Parameters**: Có thể có hoặc không có trong URL
- **Default Values**: Set giá trị mặc định nếu không có
- **Type Conversion**: Auto convert từ String sang các kiểu khác

**Ví dụ trong VnpayController:**
```java
//@GetMapping("/return")
//public RedirectView returnUrl(HttpServletRequest request,
//                            @RequestParam(required = false) String vnp_ResponseCode,
//                            @RequestParam(required = false) String vnp_TransactionStatus) {
//    // URL: /return?vnp_ResponseCode=00&vnp_TransactionStatus=00
//    // Tuy nhiên trong code thực tế dùng HttpServletRequest để lấy tất cả params
//}
```

**Các tùy chọn:**
```java
//// Required parameter (mặc định)
//@RequestParam String keyword
//
//// Optional với default value
//@RequestParam(defaultValue = "all") String category
//
//// Optional không có default (có thể null)
//@RequestParam(required = false) String filter
//
//// Nhận array/list
//@RequestParam List<String> tags
//// URL: /search?tags=battery&tags=vehicle
//
//// Nhận tất cả parameters
//@RequestParam Map<String, String> allParams
```

### 8. HttpServletRequest

**Định nghĩa:**
```java
//@GetMapping("/return")
//public RedirectView returnUrl(HttpServletRequest request) {
//    // Truy cập toàn bộ thông tin HTTP request
//    String userAgent = request.getHeader("User-Agent");
//    String clientIP = request.getRemoteAddr();
//    Map<String, String> params = extractParams(request);
//}
```

**Chức năng:**
- **Raw HTTP Request**: Truy cập trực tiếp vào HTTP request object
- **Headers**: Lấy tất cả HTTP headers
- **Parameters**: Lấy tất cả query parameters và form data  
- **Client Info**: IP address, user agent, session info
- **Flexibility**: Xử lý các trường hợp đặc biệt mà annotations không cover

**Trong VnpayController - extractParams method:**
```java
//private static Map<String, String> extractParams(HttpServletRequest request) {
//    Map<String, String> map = new HashMap<>();
//    Enumeration<String> e = request.getParameterNames();
//    while (e.hasMoreElements()) {
//        String name = e.nextElement();
//        String val = request.parameter(name);
//        if (val != null) val = URLDecoder.decode(val, StandardCharsets.UTF_8);
//        map.put(name, val);
//    }
//    return map;
//}
```

**Các method hữu ích:**
```java
// Headers
//request.getHeader("Authorization")
//request.getHeader("Content-Type")
//request.getHeader("X-FORWARDED-FOR")
//
//// Parameters  
//request.getParameter("vnp_ResponseCode")
//request.getParameterNames() // Enumeration<String>
//request.getParameterMap()   // Map<String, String[]>
//
//// Client Info
//request.getRemoteAddr()     // IP client
//request.getRemoteHost()     // hostname client
//request.getSession()        // HTTP session
//
//// Request Info
//request.getMethod()         // GET, POST, PUT...
//request.getRequestURI()     // /api/v1/payments/vnpay/return
//request.getQueryString()    // vnp_ResponseCode=00&vnp_TransactionStatus=00
```

**Khi nào dùng HttpServletRequest:**
- Cần lấy tất cả parameters (như VNPAY return URL)
- Xử lý custom headers
- Lấy client IP cho logging/security
- Truy cập session data
- Các trường hợp phức tạp mà @RequestParam/@PathVariable không đủ

### 9. RedirectView

**Định nghĩa:**
```java
//@GetMapping("/return")
//public RedirectView returnUrl(HttpServletRequest request) {
//    // Xử lý payment result từ VNPAY
//    Map<String, String> params = extractParams(request);
//    String status = processPaymentResult(params);
//    
//    // Redirect về frontend với kết quả
//    String url = "http://localhost:8888/home/vnpay-redirect?transactionStatus=" + status;
//    return new RedirectView(url);
//}
```

**Chức năng:**
- **HTTP 302 Redirect**: Gửi HTTP redirect response về client
- **Browser Navigation**: Browser tự động chuyển hướng đến URL mới  
- **Cross-Domain**: Có thể redirect sang domain khác
- **Status Code**: Mặc định 302 (Found), có thể custom

**Trong VnpayController:**
```java
//String url = "http://localhost:8888/home/vnpay-redirect?transactionStatus=" + status;
//return new RedirectView(url);
```

**Tại sao dùng RedirectView trong VNPAY:**
1. **Payment Flow**: VNPAY → Backend → Frontend
2. **User Experience**: Sau thanh toán, user quay về trang web chính
3. **Status Passing**: Truyền kết quả thanh toán qua URL parameter
4. **Security**: Xử lý verification ở backend trước khi redirect

**Các tùy chọn RedirectView:**
```java
//// Basic redirect
//return new RedirectView("http://example.com");
//
//// Với custom status code
//RedirectView redirectView = new RedirectView("http://example.com");
//redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY); // 301
//return redirectView;
//
//// Relative URL (trong cùng domain)
//return new RedirectView("/success");
//
//// Với context path
//RedirectView redirectView = new RedirectView("/success");
//redirectView.setContextRelative(true);
//return redirectView;
```

**So sánh các cách redirect:**
```java
//// 1. RedirectView (recommended cho @Controller)
//@GetMapping("/old-url")
//public RedirectView redirect() {
//    return new RedirectView("/new-url");
//}
//
//// 2. ResponseEntity với HTTP 302
//@GetMapping("/old-url")
//public ResponseEntity<Void> redirect() {
//    HttpHeaders headers = new HttpHeaders();
//    headers.setLocation(URI.create("/new-url"));
//    return new ResponseEntity<>(headers, HttpStatus.FOUND);
//}
//
//// 3. String với "redirect:" prefix (chỉ work với view resolver)
//@GetMapping("/old-url")
//public String redirect() {
//    return "redirect:/new-url";  // Chỉ work khi có view resolver
//}
```

## VNPAY Payment Flow - Ví dụ thực tế

**1. Create Payment:**
```java
//@PostMapping("/create")
//public ResponseEntity<ApiResponse<PaymentUrlResponse>> create(
//    @RequestBody CreatePaymentRequest req,    // JSON từ client
//    HttpServletRequest http) {                // Lấy client IP
//    
//    String clientIp = VnpayUtil.clientIp(
//        http.getHeader("X-FORWARDED-FOR"), 
//        http.getRemoteAddr());
//        
//    // Tạo URL thanh toán VNPAY
//    String paymentUrl = service.buildPaymentUrl(...);
//    return ResponseEntity.ok(response);
//}
```

**2. Handle Return:**
```java
//@GetMapping("/return")  
//public RedirectView returnUrl(HttpServletRequest request) {
//    // VNPAY gửi user về đây với các parameters
//    // URL: /return?vnp_ResponseCode=00&vnp_TransactionStatus=00&vnp_SecureHash=...
//    
//    Map<String, String> params = extractParams(request);  // Lấy tất cả params
//    boolean valid = VnpayUtil.verify(...);                // Verify chữ ký
//    String status = determineStatus(valid, params);       // Xác định trạng thái
//    
//    // Redirect về frontend với kết quả  
//    String frontendUrl = "http://localhost:8888/home/vnpay-redirect?transactionStatus=" + status;
//    return new RedirectView(frontendUrl);
//}
```

**3. Complete Flow:**
```
Client → POST /create → VNPAY → User Payment → VNPAY Return → GET /return → RedirectView → Frontend
```
