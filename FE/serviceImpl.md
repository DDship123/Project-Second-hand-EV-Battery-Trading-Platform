# serviceImpl.md

Tài liệu này giải thích các class và method phổ biến của Spring được sử dụng trong các lớp service implementation (ví dụ: trong BankServiceImpl).

## HttpHeaders
- **Mục đích**: Đại diện cho các HTTP headers của request hoặc response. Dùng để thiết lập metadata như Content-Type, Authorization, Accept và các custom headers.
- **Cách sử dụng**:
  ```java
  HttpHeaders headers = new HttpHeaders();
  headers.set("Content-Type", "application/json");
  headers.setBearerAuth(token);
  headers.setAccept(MediaType.APPLICATION_JSON);
  ```
- **Trong BankServiceImpl**:
  ```java
  HttpHeaders headers = new HttpHeaders();
  headers.set("Content-Type", "application/json");
  ```

### Thiết lập Content-Type cho Form Data
- **APPLICATION_FORM_URLENCODED**: Dùng khi gửi form data dạng URL-encoded (giống như HTML form submit)
  ```java
  HttpHeaders headers = new HttpHeaders();
  headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
  ```
- **Trong CommentServiceImpl**:
  ```java
  // Sử dụng cho POST/PUT requests với form data
  HttpHeaders headers = new HttpHeaders();
  headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
  ```
- **Khi nào sử dụng**:
  - Gửi form data với key-value pairs
  - Backend API expect dữ liệu dạng `application/x-www-form-urlencoded`
  - Thay thế cho việc gửi JSON khi API không hỗ trợ

### Thiết lập Accept Header
- **Accept Header**: Cho biết server kiểu dữ liệu nào client có thể nhận về trong response
- **Cách sử dụng với một MediaType**:
  ```java
  headers.setAccept(MediaType.APPLICATION_JSON);
  ```
- **Cách sử dụng với nhiều MediaType (từ ContractServiceImpl)**:
  ```java
  HttpHeaders headers = new HttpHeaders();
  headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
  headers.setAccept(java.util.List.of(MediaType.APPLICATION_JSON));
  ```

### Trong ContractServiceImpl:
```java
// Thiết lập headers cho upload contract image
HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);  // Gửi form data
headers.setAccept(java.util.List.of(MediaType.APPLICATION_JSON)); // Nhận JSON response
```

### Giải thích chi tiết Accept Header:
- **Mục đích**: Thông báo cho server biết client muốn nhận response ở định dạng nào
- **java.util.List.of(MediaType.APPLICATION_JSON)**:
  - Tạo một immutable list chứa một MediaType
  - Tương đương với `Arrays.asList(MediaType.APPLICATION_JSON)`
  - Có thể chứa nhiều MediaType nếu client chấp nhận nhiều định dạng
  
### Ví dụ Accept Header với nhiều định dạng:
```java
// Client chấp nhận cả JSON và XML
headers.setAccept(java.util.List.of(
    MediaType.APPLICATION_JSON,
    MediaType.APPLICATION_XML
));

// Client chấp nhận JSON với priority cao hơn
headers.setAccept(MediaType.parseMediaTypes("application/json;q=0.9, application/xml;q=0.8"));
```

### So sánh các cách thiết lập Accept:
```java
// 1. Một MediaType duy nhất
headers.setAccept(MediaType.APPLICATION_JSON);

// 2. List các MediaType (ContractServiceImpl style)
headers.setAccept(java.util.List.of(MediaType.APPLICATION_JSON));

// 3. Nhiều MediaType với priority
headers.setAccept(java.util.List.of(
    MediaType.APPLICATION_JSON,
    MediaType.TEXT_PLAIN
));

// 4. Parse từ string với quality values
headers.setAccept(MediaType.parseMediaTypes("application/json, text/plain;q=0.8"));
```

### Tại sao sử dụng List.of():
- **Type Safety**: Đảm bảo type an toàn với generics
- **Immutable**: Tạo ra immutable list, không thể modify sau khi tạo
- **Java 9+ Feature**: Cách hiện đại để tạo list trong Java
- **Performance**: Tối ưu hơn so với Arrays.asList() cho list nhỏ

### Lưu ý khi thiết lập Headers:
```java
// ✅ Đúng: Thiết lập cả Content-Type và Accept
HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // Dữ liệu gửi đi
headers.setAccept(java.util.List.of(MediaType.APPLICATION_JSON)); // Dữ liệu nhận về

// ❌ Sai: Chỉ thiết lập Content-Type mà không có Accept
HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
// Server có thể trả về định dạng không mong muốn
```
## MultiValueMap<String, String> và LinkedMultiValueMap
- **MultiValueMap**: Interface cho phép một key có nhiều values (khác với Map thông thường chỉ có 1 value per key)
- **LinkedMultiValueMap**: Implementation cụ thể của MultiValueMap, duy trì thứ tự insertion

### Cách sử dụng với Form Data:
```java
MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
formData.add("postId", String.valueOf(com.getPost().getPostsId()));
formData.add("memberId", String.valueOf(com.getMember().getMemberId()));
formData.add("comment", com.getComment());
formData.add("rating", String.valueOf(com.getRating()));
formData.add("status", com.getStatus());
```

### Trong CommentServiceImpl:
```java
// Tạo form data cho createComment
MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
formData.add("postId", String.valueOf(com.getPost().getPostsId()));
formData.add("memberId", String.valueOf(com.getMember().getMemberId()));
formData.add("comment", com.getComment());
formData.add("rating", String.valueOf(com.getRating()));
formData.add("status", com.getStatus());

// Tạo form data cho updateCommentStatus
MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
formData.add("status", status);
```

### Tại sao sử dụng MultiValueMap:
- **Tương thích với HTML forms**: Form data có thể có nhiều values cho cùng một key
- **Spring tự động serialize**: Spring biết cách convert MultiValueMap thành format `application/x-www-form-urlencoded`
- **Type safety**: Đảm bảo type safety với generics `<String, String>`

## HttpEntity<T>
- **Mục đích**: Là một container chung chứa HTTP request hoặc response body cùng với headers.
- **Các trường hợp sử dụng**:
  - Gửi request với cả body và headers (ví dụ: POST với JSON body)
  - Nhận response body với headers khi sử dụng RestTemplate
- **Ví dụ sử dụng**:
  ```java
  // Với body (POST request)
  HttpEntity<CreatePaymentRequest> requestEntity = new HttpEntity<>(req, headers);
  
  // Không có body (GET request)
  HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
  ```
- **Trong BankServiceImpl**:
  ```java
  // Tạo request entity với body cho POST
  HttpEntity<CreatePaymentRequest> requestEntity = new HttpEntity<>(req, headers);
  
  // Tạo request entity không có body cho GET
  HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
  ```

### HttpEntity với Form Data:
- **Trong CommentServiceImpl**: Sử dụng HttpEntity với MultiValueMap cho form submissions
  ```java
  HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);
  ```
- **Trong ContractServiceImpl**: Sử dụng với headers có Accept được thiết lập
  ```java
  HttpHeaders headers = new HttpHeaders();
  headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
  headers.setAccept(java.util.List.of(MediaType.APPLICATION_JSON));
  
  MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
  formData.add("url", contractImageUrl);
  
  HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);
  ```
- **Giải thích**:
  - **formData**: MultiValueMap chứa các key-value pairs của form
  - **headers**: HttpHeaders với Content-Type là APPLICATION_FORM_URLENCODED và Accept là APPLICATION_JSON
  - **Kết hợp**: HttpEntity đóng gói cả form data và headers thành một request entity hoàn chỉnh

### So sánh các dạng HttpEntity:
```java
// 1. JSON request body
HttpEntity<CreatePaymentRequest> jsonEntity = new HttpEntity<>(paymentRequest, jsonHeaders);

// 2. Form data request body  
HttpEntity<MultiValueMap<String, String>> formEntity = new HttpEntity<>(formData, formHeaders);

// 3. Không có body (GET request)
HttpEntity<Void> emptyEntity = new HttpEntity<>(headers);
```

## ResponseEntity<T>
- **Mục đích**: Đại diện cho toàn bộ HTTP response (status code, headers và body). Các method thực hiện HTTP calls thường trả về ResponseEntity để bạn có thể kiểm tra status và headers ngoài payload.
- **Ví dụ sử dụng**:
  ```java
  ResponseEntity<ApiResponse<PaymentUrlResponse>> apiResponse = restTemplate.exchange(...);
  
  // Kiểm tra status code
  if (apiResponse.getStatusCode().is2xxSuccessful()) {
      // Lấy body
      ApiResponse<PaymentUrlResponse> body = apiResponse.getBody();
  }
  ```
- **Trong BankServiceImpl**:
  ```java
  ResponseEntity<ApiResponse<PaymentUrlResponse>> apiResponse = restTemplate.exchange(...);
  if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
      response.ok(apiResponse.getBody().getPayload());
  }
  ```

## RestTemplate.exchange()
- **Mục đích**: Một method linh hoạt được cung cấp bởi RestTemplate để thực hiện HTTP requests (GET, POST, PUT, DELETE, v.v.) và nhận về một ResponseEntity có kiểu dữ liệu cụ thể.

- **Chữ ký method**:
  ```java
  public <T> ResponseEntity<T> exchange(
      String url, 
      HttpMethod method, 
      HttpEntity<?> requestEntity, 
      ParameterizedTypeReference<T> responseType, 
      Object... uriVariables
  )
  ```

- **Các tham số chính**:
  - **url**: URL endpoint (có thể bao gồm path/query parameters)
  - **method**: HttpMethod enum (GET, POST, PUT, DELETE, v.v.)
  - **requestEntity**: HttpEntity chứa headers và optional request body
  - **responseType**: Class<T> hoặc ParameterizedTypeReference<T> khi response sử dụng generic types
  - **uriVariables**: Các biến để thay thế trong URL template

- **Ví dụ sử dụng trong BankServiceImpl**:

  **1. POST Request với generic response type**:
  ```java
  ResponseEntity<ApiResponse<PaymentUrlResponse>> apiResponse = restTemplate.exchange(
      apiBaseUrl + "/api/v1/payments/vnpay/create",  // URL
      HttpMethod.POST,                               // HTTP Method
      requestEntity,                                 // Request entity (body + headers)
      new ParameterizedTypeReference<ApiResponse<PaymentUrlResponse>>(){} // Response type
  );
  ```

  **2. GET Request**:
  ```java
  ResponseEntity<ApiResponse<ReturnUrlResponse>> apiResponse = restTemplate.exchange(
      returnUrl,                                     // URL
      HttpMethod.GET,                               // HTTP Method  
      requestEntity,                                // Request entity (chỉ headers)
      new ParameterizedTypeReference<ApiResponse<ReturnUrlResponse>>(){} // Response type
  );
  ```

### Ví dụ trong CommentServiceImpl:

**3. POST Request với Form Data**:
```java
ResponseEntity<ApiResponse<CommentResponse>> apiResponse = restTemplate.exchange(
    apiBaseUrl + "/api/comments",                   // URL
    HttpMethod.POST,                               // HTTP Method
    requestEntity,                                 // HttpEntity<MultiValueMap<String, String>>
    new ParameterizedTypeReference<ApiResponse<CommentResponse>>(){} // Response type
);
```

**4. PUT Request với Form Data**:
```java
ResponseEntity<ApiResponse<CommentResponse>> apiResponse = restTemplate.exchange(
    apiBaseUrl + "/api/comments/update/" + commentId, // URL với path variable
    HttpMethod.PUT,                                   // HTTP Method
    requestEntity,                                    // HttpEntity<MultiValueMap<String, String>>
    new ParameterizedTypeReference<ApiResponse<CommentResponse>>(){} // Response type
);
```

- **Tại sao sử dụng ParameterizedTypeReference**:
  - Java có type erasure, nghĩa là thông tin về generic types bị mất tại runtime
  - Khi response type là generic (ví dụ: `ApiResponse<PaymentUrlResponse>`), RestTemplate không thể suy ra kiểu dữ liệu từ Class object
  - ParameterizedTypeReference giải quyết vấn đề này bằng cách preserve type information
  - Ví dụ:
    ```java
    // ❌ Không hoạt động với generic types
    Class<ApiResponse<PaymentUrlResponse>> responseType = ...; // Không thể tạo
    
    // ✅ Sử dụng ParameterizedTypeReference
    new ParameterizedTypeReference<ApiResponse<PaymentUrlResponse>>(){}
    ```

- **Xử lý Exception**:
  ```java
  try {
      ResponseEntity<ApiResponse<PaymentUrlResponse>> apiResponse = restTemplate.exchange(...);
      // Xử lý response
  } catch (RestClientException e) {
      // Xử lý lỗi network, timeout, v.v.
      Map<String, String> errorMap = new HashMap<>();
      errorMap.put("message", "Failed to call API: " + e.getMessage());
      response.error(errorMap);
  }
  ```

## Workflow hoàn chỉnh cho Form Data Request:

```java
// 1. Tạo headers với content type phù hợp
HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

// 2. Tạo form data với key-value pairs
MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
formData.add("postId", String.valueOf(postId));
formData.add("comment", commentText);
formData.add("rating", String.valueOf(rating));

// 3. Đóng gói form data และ headers vào HttpEntity
HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

// 4. Gửi request และ nhận response
ResponseEntity<ApiResponse<CommentResponse>> apiResponse = restTemplate.exchange(
    url, HttpMethod.POST, requestEntity, 
    new ParameterizedTypeReference<ApiResponse<CommentResponse>>(){}
);

// 5. Xử lý response
if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
    // Success
    return apiResponse.getBody().getPayload();
}
```

## Workflow hoàn chỉnh cho Form Data Request với Accept Header:

```java
// 1. Tạo headers với content type và accept type
HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // Gửi form data
headers.setAccept(java.util.List.of(MediaType.APPLICATION_JSON)); // Nhận JSON response

// 2. Tạo form data với key-value pairs
MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
formData.add("url", contractImageUrl);

// 3. Đóng gói form data và headers vào HttpEntity
HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

// 4. Gửi request và nhận response
ResponseEntity<String> apiResponse = restTemplate.exchange(
    url, HttpMethod.PUT, requestEntity, String.class
);

// 5. Xử lý response
if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
    // Parse JSON response manually để tránh generic type issues
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> body = mapper.readValue(apiResponse.getBody(), 
        new TypeReference<Map<String, Object>>() {});
    // Process response...
}
```

## UriComponentsBuilder
- **Mục đích**: Utility class của Spring để xây dựng URI một cách an toàn và linh hoạt. Đặc biệt hữu ích khi cần thêm query parameters, path variables hoặc xử lý encoding.
- **Package**: `org.springframework.web.util.UriComponentsBuilder`
- **Tại sao sử dụng**: Tránh việc nối chuỗi URL manual, tự động handle encoding, type-safe với parameters

### Cách sử dụng cơ bản:
```java
// Tạo URI với query parameters
UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl + "/api/endpoint")
    .queryParam("param1", value1)
    .queryParam("param2", value2);
String finalUrl = builder.toUriString();
```

### Trong MemberPlanUsageServiceImpl:
```java
// Tạo URI với query parameters cho registerPackage
UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiBaseUrl + "/api/member-plan-usages/register-package")
    .queryParam("memberId", memberId)
    .queryParam("planId", planId);

ResponseEntity<ApiResponse<MemberPlanUsageResponse>> apiResponse = restTemplate.exchange(
    builder.toUriString(),  // Convert thành String URL
    HttpMethod.PUT,
    entity,
    new ParameterizedTypeReference<ApiResponse<MemberPlanUsageResponse>>() {}
);
```

### Các method thường dùng:

#### 1. fromUriString()
- **Mục đích**: Tạo UriComponentsBuilder từ một base URI string
- **Ví dụ**:
  ```java
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:8001/api/users");
  ```

#### 2. queryParam()
- **Mục đích**: Thêm query parameter vào URL
- **Ví dụ**:
  ```java
  builder.queryParam("memberId", 123)
         .queryParam("planId", 456);
  // Kết quả: http://localhost:8001/api/users?memberId=123&planId=456
  ```

#### 3. pathSegment()
- **Mục đích**: Thêm path segment một cách an toàn (tự động encode)
- **Ví dụ**:
  ```java
  builder.pathSegment("users", userId.toString());
  // Thay vì: baseUrl + "/users/" + userId (không an toàn)
  ```

#### 4. toUriString()
- **Mục đích**: Convert UriComponentsBuilder thành String URL
- **Ví dụ**:
  ```java
  String finalUrl = builder.toUriString();
  ```

### So sánh với cách truyền thống:

#### ❌ Cách không khuyến khích (Manual string concatenation):
```java
// Không an toàn, không handle encoding
String url = apiBaseUrl + "/api/member-plan-usages/register-package?memberId=" + memberId + "&planId=" + planId;

// Vấn đề: 
// - Không handle special characters trong parameters
// - Dễ lỗi syntax
// - Khó đọc và maintain
```

#### ✅ Cách khuyến khích (UriComponentsBuilder):
```java
// An toàn, tự động encoding, dễ đọc
UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiBaseUrl + "/api/member-plan-usages/register-package")
    .queryParam("memberId", memberId)
    .queryParam("planId", planId);
String url = builder.toUriString();
```

### Các ví dụ nâng cao:

#### 1. Xử lý path variables:
```java
UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiBaseUrl + "/api/users/{userId}/posts/{postId}")
    .buildAndExpand(userId, postId)  // Thay thế {userId} và {postId}
    .encode();  // Encode toàn bộ URI
```

#### 2. Conditional parameters:
```java
UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiBaseUrl + "/api/posts");

if (category != null) {
    builder.queryParam("category", category);
}
if (status != null) {
    builder.queryParam("status", status);
}
if (page != null) {
    builder.queryParam("page", page);
}

String url = builder.toUriString();
```

#### 3. Multiple values cho cùng một parameter:
```java
// URL: /api/posts?tags=java&tags=spring&tags=rest
UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiBaseUrl + "/api/posts")
    .queryParam("tags", "java", "spring", "rest");
```

#### 4. Encoding đặc biệt:
```java
UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiBaseUrl + "/api/search")
    .queryParam("query", "special chars: @#$%^&*()")  // Tự động encode
    .encode();  // Explicit encoding
```

### Trong context của RestTemplate.exchange():

```java
// Pattern hoàn chỉnh từ MemberPlanUsageServiceImpl
public ApiResponse<MemberPlanUsageResponse> registerPackage(Integer memberId, Integer planId) {
    ApiResponse<MemberPlanUsageResponse> response = new ApiResponse<>();
    try {
        // 1. Tạo headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        
        // 2. Build URI với UriComponentsBuilder
        UriComponentsBuilder builder = UriComponentsBuilder
            .fromUriString(apiBaseUrl + "/api/member-plan-usages/register-package")
            .queryParam("memberId", memberId)
            .queryParam("planId", planId);
        
        // 3. Execute request với built URI
        ResponseEntity<ApiResponse<MemberPlanUsageResponse>> apiResponse = restTemplate.exchange(
            builder.toUriString(),  // URI đã được build
            HttpMethod.PUT,
            entity,
            new ParameterizedTypeReference<ApiResponse<MemberPlanUsageResponse>>() {}
        );
        
        // 4. Xử lý response
        if (apiResponse.getBody() != null) {
            response.ok(apiResponse.getBody().getPayload());
        } else {
            // Handle error...
        }
    } catch (Exception e) {
        // Handle exception...
    }
    return response;
}
```

### Lợi ích của UriComponentsBuilder:

1. **URL Encoding tự động**: Tự động encode special characters trong parameters
2. **Type Safety**: Compile-time checking cho parameters
3. **Readable Code**: Code dễ đọc và maintain hơn string concatenation
4. **Flexible**: Dễ dàng thêm/bớt parameters theo điều kiện
5. **Reusable**: Có thể reuse builder pattern cho nhiều URLs tương tự
6. **Error Prevention**: Tránh lỗi syntax trong URL construction

### Best Practices:

1. **Luôn sử dụng UriComponentsBuilder cho URLs có parameters**:
   ```java
   // ✅ Good
   UriComponentsBuilder.fromUriString(baseUrl).queryParam("id", id).toUriString()
   
   // ❌ Bad
   baseUrl + "?id=" + id
   ```

2. **Sử dụng pathSegment() cho dynamic paths**:
   ```java
   // ✅ Good
   builder.pathSegment("users", userId.toString())
   
   // ❌ Bad
   builder.fromUriString(baseUrl + "/users/" + userId)
   ```

3. **Handle null parameters**:
   ```java
   if (parameter != null) {
       builder.queryParam("param", parameter);
   }
   ```

4. **Reuse builder cho similar URLs**:
   ```java
   UriComponentsBuilder baseBuilder = UriComponentsBuilder.fromUriString(apiBaseUrl + "/api/posts");
   
   // Reuse cho different endpoints
   String url1 = baseBuilder.cloneBuilder().queryParam("status", "active").toUriString();
   String url2 = baseBuilder.cloneBuilder().queryParam("category", "tech").toUriString();
   ```
## Các lưu ý quan trọng cho Service Implementation:

1. **Luôn kiểm tra Response**:
   ```java
   if (apiResponse.getStatusCode().is2xxSuccessful() && apiResponse.getBody() != null) {
       // Xử lý thành công
   } else {
       // Xử lý lỗi
   }
   ```

2. **Thiết lập timeout và error handling**:
   - Cấu hình RestTemplate với connection timeout và read timeout phù hợp
   - Sử dụng try-catch để xử lý RestClientException và các subclasses

3. **Sử dụng DTO và wrapper response types**:
   - Tạo các class DTO phù hợp với contract của backend API
   - Sử dụng wrapper types như ApiResponse<T> để đơn giản hóa việc parse và xử lý lỗi

4. **Validate dữ liệu**:
   - Kiểm tra status code trước khi truy cập body
   - Validate non-null body trước khi sử dụng
   - Kiểm tra các trường bắt buộc trong response payload

5. **Logging và Monitoring**:
   - Log các request/response quan trọng để debug
   - Monitor performance và error rates của các external API calls

6. **Chọn Content-Type và Accept phù hợp**:
   - **Content-Type**: Định dạng dữ liệu bạn gửi đi
     - `application/json` cho JSON requests
     - `application/x-www-form-urlencoded` cho form data
     - `multipart/form-data` cho file uploads
   - **Accept**: Định dạng dữ liệu bạn muốn nhận về
     - `application/json` cho JSON responses
     - `text/html` cho HTML responses
     - Có thể chỉ định nhiều định dạng với priority
   - **Đảm bảo backend API support cả Content-Type bạn gửi và Accept type bạn yêu cầu**

7. **Xử lý Response phức tạp**:
   - Khi sử dụng generic types phức tạp, có thể nhận response dạng String và parse manual
   - Sử dụng ObjectMapper với TypeReference cho generic types
   - Handle parsing exceptions riêng biệt để tránh crash toàn bộ request

8. **Sử dụng UriComponentsBuilder cho URL construction**:
   - Thay thế string concatenation bằng UriComponentsBuilder
   - Tự động handle URL encoding cho parameters
   - Dễ dàng thêm conditional parameters
   - Code clean và maintainable hơn
   - Ví dụ: `UriComponentsBuilder.fromUriString(baseUrl).queryParam("id", id).toUriString()`
