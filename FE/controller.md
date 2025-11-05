# Controller Documentation - Spring MVC Annotations and Concepts

## Spring MVC Core Annotations và Concepts

### 1. @Controller
**Định nghĩa**: Annotation đánh dấu một class là Spring MVC Controller, chịu trách nhiệm xử lý HTTP requests.

**Chức năng**:
- Đăng ký class như một Spring Bean
- Cho phép Spring Framework tự động phát hiện và quản lý controller
- Kết hợp với @RequestMapping để định nghĩa endpoints

**Ví dụ**:
```java
@Controller
public class MemberController {
    // Các method xử lý request
}
```

### 2. @RequestMapping
**Định nghĩa**: Annotation mapping HTTP requests đến specific handler methods hoặc classes.

**Thuộc tính chính**:
- `value/path`: Đường dẫn URL
- `method`: HTTP method (GET, POST, PUT, DELETE)
- `params`: Request parameters cần thiết
- `headers`: HTTP headers yêu cầu
- `consumes`: Content-Type của request
- `produces`: Content-Type của response

**Cách sử dụng**:
```java
//@Controller
//@RequestMapping("/account") // Class-level mapping
//public class MemberController {
//    
//    @RequestMapping(value = "/personalInformation", method = RequestMethod.GET)
//    public String getPersonalInfo(Model model) {
//        return "personalInformationPage";
//    }
//    
//    @RequestMapping(value = "/personalInformation", method = RequestMethod.POST)
//    public String updatePersonalInfo(@ModelAttribute MemberResponse user) {
//        // Logic xử lý
//        return "redirect:/account/personalInformation";
//    }
//}
```

**Shortcuts phổ biến**:
- `@GetMapping("/path")` = `@RequestMapping(value="/path", method=RequestMethod.GET)`
- `@PostMapping("/path")` = `@RequestMapping(value="/path", method=RequestMethod.POST)`

### 3. RedirectAttributes
**Định nghĩa**: Interface cho phép truyền attributes qua redirect, sử dụng trong POST-redirect-GET pattern.

**Chức năng**:
- `addAttribute()`: Thêm attribute vào URL query string
- `addFlashAttribute()`: Thêm attribute tạm thời (chỉ tồn tại qua 1 redirect)

**Ví dụ**:
```java
//@PostMapping("/personalInformation")
//public String updateProfile(@ModelAttribute MemberResponse user, 
//                          RedirectAttributes redirectAttributes) {
//    try {
//        memberService.updateMember(user);
//        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thành công!");
//    } catch (Exception e) {
//        redirectAttributes.addFlashAttribute("errorMessage", "Cập nhật thất bại!");
//    }
//    return "redirect:/account/personalInformation";
//}
```

**Lưu ý**: Flash attributes sẽ tự động bị xóa sau khi sử dụng, tránh duplicate submissions.

### 4. HttpSession
**Định nghĩa**: Interface quản lý session data giữa multiple requests từ cùng một user.

**Chức năng**:
- Lưu trữ thông tin user qua nhiều requests
- Quản lý trạng thái đăng nhập
- Lưu trữ dữ liệu tạm thời

**Phương thức chính**:
- `setAttribute(String name, Object value)`: Lưu data
- `getAttribute(String name)`: Lấy data
- `removeAttribute(String name)`: Xóa data
- `invalidate()`: Hủy session (logout)

**Ví dụ**:
```java
//@GetMapping("/login")
//public String login(@RequestParam String username, 
//                   @RequestParam String password,
//                   HttpSession session) {
//    if (authService.authenticate(username, password)) {
//        session.setAttribute("currentUser", user);
//        session.setAttribute("isLoggedIn", true);
//        return "redirect:/home";
//    }
//    return "loginIn";
//}
//
//@GetMapping("/logout")
//public String logout(HttpSession session) {
//    session.invalidate(); // Xóa toàn bộ session
//    return "redirect:/login";
//}
```

### 5. Model
**Định nghĩa**: Interface chứa data được truyền từ Controller đến View (Thymeleaf template).

**Chức năng**:
- Truyền data từ backend đến frontend
- Binding data với Thymeleaf expressions
- Tạo dynamic content cho templates

**Phương thức chính**:
- `addAttribute(String name, Object value)`: Thêm attribute
- `addAttribute(Object value)`: Thêm attribute với tên tự động
- `addAllAttributes(Map<String, ?> attributes)`: Thêm nhiều attributes

**Ví dụ**:
```java
//@GetMapping("/personalInformation")
//public String getPersonalInfo(Model model, HttpSession session) {
//    MemberResponse user = (MemberResponse) session.getAttribute("currentUser");
//    
//    model.addAttribute("user", user);
//    model.addAttribute("pageTitle", "Thông tin cá nhân");
//    model.addAttribute("cities", cityService.getAllCities());
//    
//    return "personalInformationPage"; // Trả về template name
//}
```

**Sử dụng trong Thymeleaf**:
```html
<!--<h1 th:text="${pageTitle}"></h1>-->
<!--<input th:field="${user.username}" type="text" />-->
<!--<select th:field="${user.address}">-->
<!--    <option th:each="city : ${cities}" th:value="${city}" th:text="${city}"></option>-->
<!--</select>-->
```

### 6. @PathVariable
**Định nghĩa**: Annotation extract giá trị từ URL path và bind vào method parameters.

**Cú pháp**: `@PathVariable("variableName") Type parameterName`

**Ví dụ**:
```java
//// URL: /posts/123/comments/456
//@GetMapping("/posts/{postId}/comments/{commentId}")
//public String getComment(@PathVariable("postId") Long postId,
//                        @PathVariable("commentId") Long commentId,
//                        Model model) {
//    Comment comment = commentService.getComment(postId, commentId);
//    model.addAttribute("comment", comment);
//    return "commentDetails";
//}
//
//// URL: /wishlist/delete/789
//@GetMapping("/wishlist/delete/{id}")
//public String deleteWishlistItem(@PathVariable Long id) {
//    wishlistService.deleteItem(id);
//    return "redirect:/home/wishlist";
//}
```

**Lưu ý**: Tên biến trong URL {} phải match với parameter name hoặc sử dụng value attribute.

### 7. @RequestParam
**Định nghĩa**: Annotation bind HTTP request parameters (query string, form data) vào method parameters.

**Thuộc tính**:
- `value/name`: Tên parameter trong request
- `required`: Bắt buộc hay không (default: true)
- `defaultValue`: Giá trị mặc định nếu không có

**Ví dụ**:
```java
//// URL: /search?keyword=battery&category=electric&page=1
//@GetMapping("/search")
//public String search(@RequestParam("keyword") String keyword,
//                    @RequestParam(value = "category", required = false) String category,
//                    @RequestParam(value = "page", defaultValue = "1") int page,
//                    Model model) {
//    
//    List<Post> posts = postService.search(keyword, category, page);
//    model.addAttribute("posts", posts);
//    model.addAttribute("currentPage", page);
//    
//    return "searchResults";
//}
//
//// Form submission
//@PostMapping("/contact")
//public String submitContact(@RequestParam("email") String email,
//                           @RequestParam("message") String message,
//                           RedirectAttributes redirectAttributes) {
//    contactService.sendMessage(email, message);
//    redirectAttributes.addFlashAttribute("successMessage", "Tin nhắn đã được gửi!");
//    return "redirect:/contact";
//}
```

## Tổng kết và Best Practices

### Kết hợp các Annotation
```java
//@Controller
//@RequestMapping("/transactions")
//public class TransactionController {
//    
//    @GetMapping("/{transactionId}")
//    public String getTransaction(@PathVariable Long transactionId,
//                               @RequestParam(value = "view", defaultValue = "details") String viewType,
//                               Model model,
//                               HttpSession session) {
//        
//        MemberResponse currentUser = (MemberResponse) session.getAttribute("currentUser");
//        Transaction transaction = transactionService.getById(transactionId);
//        
//        model.addAttribute("transaction", transaction);
//        model.addAttribute("user", currentUser);
//        model.addAttribute("viewType", viewType);
//        
//        return "transactionDetails";
//    }
//    
//    @PostMapping("/{transactionId}/rate")
//    public String rateTransaction(@PathVariable Long transactionId,
//                                @RequestParam("rating") double rating,
//                                @RequestParam("reviewText") String reviewText,
//                                HttpSession session,
//                                RedirectAttributes redirectAttributes) {
//        
//        MemberResponse currentUser = (MemberResponse) session.getAttribute("currentUser");
//        
//        try {
//            reviewService.createReview(transactionId, currentUser.getId(), rating, reviewText);
//            redirectAttributes.addFlashAttribute("successMessage", "Đánh giá thành công!");
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("errorMessage", "Đánh giá thất bại!");
//        }
//        
//        return "redirect:/transactions/" + transactionId;
//    }
//}
```

### Những điều cần nhớ
1. **@Controller** - Đánh dấu class là Spring MVC controller
2. **@RequestMapping** - Định nghĩa URL mapping cho class hoặc method
3. **RedirectAttributes** - Truyền data qua redirect, tránh duplicate submissions
4. **HttpSession** - Quản lý trạng thái user qua nhiều requests
5. **Model** - Truyền data từ controller đến view template
6. **@PathVariable** - Extract data từ URL path
7. **@RequestParam** - Extract data từ query string hoặc form

### Error Handling Tips
- Luôn validate input từ @RequestParam và @PathVariable
- Sử dụng try-catch với RedirectAttributes để thông báo lỗi
- Check session attributes trước khi sử dụng
- Sử dụng required=false cho optional parameters
