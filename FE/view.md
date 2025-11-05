# view.md

## Thymeleaf là gì trong Java Spring?

**Thymeleaf** là một template engine hiện đại cho Java, được thiết kế để tạo ra các trang web động. Thymeleaf là template engine mặc định trong Spring Boot và được sử dụng rộng rãi để tạo ra các view trong ứng dụng Spring MVC.

### Đặc điểm chính của Thymeleaf:
- **Natural Templates**: Template có thể được mở trực tiếp trong browser mà không cần server
- **Server-side rendering**: Xử lý template ở phía server trước khi gửi về client
- **Integration với Spring**: Tích hợp tốt với Spring Framework
- **XML/HTML5 compliant**: Tuân thủ chuẩn XML và HTML5
- **Rich expression language**: Hỗ trợ nhiều biểu thức phong phú

### Cách hoạt động:
1. Controller chuẩn bị dữ liệu và đưa vào Model
2. Thymeleaf engine xử lý template với dữ liệu từ Model
3. Tạo ra HTML cuối cùng gửi về browser

## Các thuộc tính Thymeleaf (th:*) chi tiết

### 1. th:text và th:utext
- **Mục đích**: Hiển thị text content
- **th:text**: Escape HTML characters (an toàn)
- **th:utext**: Không escape HTML (unescaped text)

```html
<!-- th:text - Safe text rendering -->
<p th:text="${user.username}">Default username</p>
<span th:text="${message}">Default message</span>

<!-- th:utext - Unescaped HTML -->
<div th:utext="${htmlContent}">Default HTML content</div>

<!-- Ví dụ trong project -->
<h1 th:text="'Xin chào, ' + ${user.username}">Xin chào, User</h1>
<p th:text="${post.description}">Mô tả sản phẩm</p>
```

### 2. th:if, th:unless
- **Mục đích**: Conditional rendering
- **th:if**: Hiển thị nếu điều kiện đúng
- **th:unless**: Hiển thị nếu điều kiện sai

```html
<!-- th:if - Hiển thị khi có điều kiện -->
<div th:if="${user != null}">
    <p th:text="'Chào mừng, ' + ${user.username}">Chào mừng user</p>
</div>

<button th:if="${transaction.getReview() == null}" 
        class="container__body__item__rating__btn">
    Đánh giá giao dịch
</button>

<!-- th:unless - Hiển thị khi không có điều kiện -->
<div th:unless="${posts.isEmpty()}">
    <p>Có sản phẩm để hiển thị</p>
</div>

<!-- Multiple conditions -->
<span th:if="${user.role == 'ADMIN'}">Admin Panel</span>
<span th:if="${post.status == 'ACTIVE' and post.price > 0}">Đang bán</span>
```

### 3. th:each
- **Mục đích**: Lặp qua collections (List, Array, Map)
- **Syntax**: `th:each="item : ${collection}"`

```html
<!-- Lặp qua List -->
<div th:each="post : ${posts}">
    <h3 th:text="${post.title}">Tiêu đề</h3>
    <p th:text="${post.description}">Mô tả</p>
    <span th:text="'Giá: ' + ${post.price} + ' VND'">Giá</span>
</div>

<!-- Với index -->
<table>
    <tr th:each="user, iterStat : ${users}">
        <td th:text="${iterStat.index + 1}">STT</td>
        <td th:text="${user.username}">Username</td>
        <td th:text="${user.email}">Email</td>
    </tr>
</table>

<!-- Ví dụ từ project -->
<div th:each="favorite : ${favorites}">
    <div class="wishlist__item">
        <img th:src="${favorite.getPost().getMainImage()}" alt="Product">
        <h4 th:text="${favorite.getPost().getTitle()}">Tên sản phẩm</h4>
        <p th:text="${favorite.getPost().getPrice()} + ' VND'">Giá</p>
    </div>
</div>
```

### 4. th:href
- **Mục đích**: Tạo links động
- **Syntax**: `th:href="@{/path}"`

```html
<!-- Static URL -->
<a th:href="@{/home}">Trang chủ</a>
<a th:href="@{/login}">Đăng nhập</a>

<!-- Dynamic URL với parameters -->
<a th:href="@{/product/{id}(id=${product.id})}">Xem chi tiết</a>
<a th:href="@{/user/edit/{userId}(userId=${user.id})}">Chỉnh sửa</a>

<!-- URL với query parameters -->
<a th:href="@{/search(keyword=${searchTerm},category=${category})}">Tìm kiếm</a>

<!-- Ví dụ từ project -->
<a th:href="@{'/home/wishlist/delete/' + ${favorite.getFavoritesId()}}">
    <i class="fa-solid fa-heart"></i>
</a>
<button><a th:href="@{/home/transactionsHistory}">Mua</a></button>
<button><a th:href="@{/home/transactionsHistory/seller}">Bán</a></button>
```

### 5. th:src
- **Mục đích**: Tạo src attribute động cho images, scripts
- **Syntax**: `th:src="@{/path}"`

```html
<!-- Static resources -->
<img th:src="@{/images/logo.png}" alt="Logo">
<script th:src="@{/js/app.js}"></script>

<!-- Dynamic image sources -->
<img th:src="${user.avatarUrl}" alt="Avatar">
<img th:src="${product.mainImage}" alt="Product Image">

<!-- Conditional src -->
<img th:src="${user.avatar != null ? user.avatar : '/images/default-avatar.png'}" 
     alt="User Avatar">

<!-- Ví dụ từ project -->
<img th:src="${favorite.getPost().getMainImage()}" alt="Product">
<img th:src="${post.getMainImage()}" alt="Main Image">
```

### 6. th:action và th:method
- **Mục đích**: Tạo form actions và methods
- **th:action**: URL để submit form
- **th:method**: HTTP method

```html
<!-- Basic form -->
<form th:action="@{/login}" method="post">
    <input type="text" name="username">
    <input type="password" name="password">
    <button type="submit">Đăng nhập</button>
</form>

<!-- Form với dynamic action -->
<form th:action="@{/user/update/{id}(id=${user.id})}" method="post">
    <input type="text" th:value="${user.username}" name="username">
    <button type="submit">Cập nhật</button>
</form>

<!-- Ví dụ từ project -->
<form method="post" th:action="@{/account/personalInformation}" th:object="${user}">
    <input type="text" th:field="*{username}" disabled="true">
    <input type="submit" value="Lưu thay đổi">
</form>
```

### 7. th:object và th:field
- **Mục đích**: Form binding với objects
- **th:object**: Bind form với một object
- **th:field**: Bind input với property của object

```html
<!-- Form binding với object -->
<form th:object="${user}" th:action="@{/user/save}" method="post">
    <!-- th:field tự động tạo name, id, value -->
    <input type="text" th:field="*{username}" placeholder="Username">
    <input type="email" th:field="*{email}" placeholder="Email">
    <input type="password" th:field="*{password}" placeholder="Password">
    <select th:field="*{role}">
        <option value="USER">User</option>
        <option value="ADMIN">Admin</option>
    </select>
    <button type="submit">Lưu</button>
</form>

<!-- Ví dụ từ project -->
<form th:object="${user}" th:action="@{/account/personalInformation}" method="post">
    <input type="text" id="name" th:field="*{username}" disabled="true">
    <input type="email" id="email" th:field="*{email}" disabled="true">
    <input type="text" id="phone" th:field="*{phone}" disabled="true">
    <select id="address" th:field="*{address}" disabled="true">
        <option value="">Chọn địa chỉ</option>
    </select>
</form>
```

### 8. th:value
- **Mục đích**: Set value attribute động
- **Syntax**: `th:value="${expression}"`

```html
<!-- Set value cho input -->
<input type="text" th:value="${user.username}" name="username">
<input type="hidden" th:value="${user.id}" name="userId">

<!-- Conditional values -->
<input type="text" th:value="${user.phone != null ? user.phone : ''}" name="phone">

<!-- Select option values -->
<select name="status">
    <option th:value="'ACTIVE'" th:selected="${post.status == 'ACTIVE'}">Hoạt động</option>
    <option th:value="'INACTIVE'" th:selected="${post.status == 'INACTIVE'}">Không hoạt động</option>
</select>
```

### 9. th:class, th:classappend
- **Mục đích**: Dynamic CSS classes
- **th:class**: Set toàn bộ class
- **th:classappend**: Thêm class vào existing classes

```html
<!-- Dynamic class based on condition -->
<div th:class="${user.isActive ? 'active-user' : 'inactive-user'}">User Status</div>

<!-- Append class conditionally -->
<button class="btn" th:classappend="${post.featured ? 'btn-featured' : ''}">
    Button
</button>

<!-- Multiple conditions -->
<div th:class="'user-card ' + ${user.role.toLowerCase()} + (${user.isOnline} ? ' online' : ' offline')">
    User Card
</div>

<!-- Ví dụ practical -->
<tr th:each="order : ${orders}" 
    th:class="${order.status == 'COMPLETED' ? 'completed' : 'pending'}">
    <td th:text="${order.id}">Order ID</td>
</tr>
```

### 10. th:style
- **Mục đích**: Dynamic inline styles
- **Syntax**: `th:style="${styleExpression}"`

```html
<!-- Dynamic background color -->
<div th:style="'background-color: ' + ${user.favoriteColor}">Colored Div</div>

<!-- Conditional styles -->
<p th:style="${post.urgent ? 'color: red; font-weight: bold;' : 'color: black;'}">
    Post Content
</p>

<!-- Multiple style properties -->
<div th:style="'width: ' + ${progress} + '%; height: 20px; background-color: blue;'">
    Progress Bar
</div>
```

### 11. th:attr
- **Mục đích**: Set any HTML attribute động
- **Syntax**: `th:attr="attribute=${value}"`

```html
<!-- Set custom attributes -->
<div th:attr="data-user-id=${user.id},data-role=${user.role}">User Info</div>

<!-- Set multiple attributes -->
<img th:attr="src=${product.image},alt=${product.name},title=${product.description}">

<!-- Conditional attributes -->
<input type="text" th:attr="readonly=${user.role != 'ADMIN' ? 'readonly' : null}">

<!-- Ví dụ với data attributes -->
<button th:attr="data-transaction-id=${transaction.getTransactionId()}" 
        class="rating-btn">
    Đánh giá
</button>
```

### 12. th:with
- **Mục đích**: Define local variables trong template
- **Syntax**: `th:with="variable=${expression}"`

```html
<!-- Define local variable -->
<div th:with="fullName=${user.firstName + ' ' + user.lastName}">
    <h2 th:text="${fullName}">Full Name</h2>
    <p th:text="'Welcome, ' + ${fullName}">Welcome message</p>
</div>

<!-- Multiple variables -->
<div th:with="total=${order.quantity * order.price}, discount=${total * 0.1}">
    <p th:text="'Total: ' + ${total}">Total</p>
    <p th:text="'Discount: ' + ${discount}">Discount</p>
    <p th:text="'Final: ' + ${total - discount}">Final Price</p>
</div>
```

### 13. th:switch và th:case
- **Mục đích**: Switch-case logic trong template
- **Syntax**: `th:switch="${expression}"` và `th:case="${value}"`

```html
<!-- Switch case example -->
<div th:switch="${user.role}">
    <p th:case="'ADMIN'">Administrator Panel</p>
    <p th:case="'SELLER'">Seller Dashboard</p>
    <p th:case="'BUYER'">Buyer Interface</p>
    <p th:case="*">Default User</p>
</div>

<!-- Order status example -->
<span th:switch="${order.status}">
    <span th:case="'PENDING'" class="status-pending">Đang xử lý</span>
    <span th:case="'COMPLETED'" class="status-completed">Hoàn thành</span>
    <span th:case="'CANCELLED'" class="status-cancelled">Đã hủy</span>
    <span th:case="*" class="status-unknown">Không xác định</span>
</span>
```

### 14. th:fragment và th:replace/th:insert
- **Mục đích**: Template fragments và inclusion
- **th:fragment**: Define reusable template pieces
- **th:replace**: Replace entire element
- **th:insert**: Insert content inside element

```html
<!-- Define fragment (trong commonComponents/header.html) -->
<div th:fragment="header(title)">
    <header>
        <h1 th:text="${title}">Default Title</h1>
        <nav>...</nav>
    </header>
</div>

<!-- Use fragment in other templates -->
<div th:replace="commonComponents/header :: header('Trang chủ')"></div>

<!-- Insert fragment -->
<div th:insert="commonComponents/footer :: footer"></div>

<!-- Ví dụ từ project structure -->
<div th:replace="commonComponents/navigation :: nav"></div>
```

## Biểu thức trong Thymeleaf

### 1. Variable Expressions: ${...}
```html
<!-- Access object properties -->
<p th:text="${user.username}">Username</p>
<p th:text="${post.createdAt}">Created Date</p>

<!-- Method calls -->
<p th:text="${user.getFullName()}">Full Name</p>
<p th:text="${#dates.format(post.createdAt, 'dd-MM-yyyy')}">Formatted Date</p>
```

### 2. Selection Variable Expressions: *{...}
```html
<!-- Use with th:object -->
<form th:object="${user}">
    <input th:field="*{username}"> <!-- Equivalent to ${user.username} -->
    <input th:field="*{email}">    <!-- Equivalent to ${user.email} -->
</form>
```

### 3. Message Expressions: #{...}
```html
<!-- Internationalization -->
<p th:text="#{welcome.message}">Welcome</p>
<p th:text="#{user.greeting(${user.name})}">Hello user</p>
```

### 4. Link URL Expressions: @{...}
```html
<!-- Static URLs -->
<a th:href="@{/home}">Home</a>

<!-- Dynamic URLs -->
<a th:href="@{/user/{id}(id=${user.id})}">User Profile</a>

<!-- With query parameters -->
<a th:href="@{/search(q=${query},page=${currentPage})}">Search</a>
```

### 5. Fragment Expressions: ~{...}
```html
<!-- Reference to fragments -->
<div th:insert="~{fragments/header :: header}"></div>
<div th:replace="~{fragments/footer :: footer}"></div>
```

## Utility Objects trong Thymeleaf

### #dates - Date formatting
```html
<!-- Format dates -->
<p th:text="#dates.format(${post.createdAt}, 'dd/MM/yyyy')">Date</p>
<p th:text="#dates.format(${post.createdAt}, 'dd-MM-yyyy HH:mm')">DateTime</p>

<!-- Date calculations -->
<p th:text="#dates.createNow()">Current Date</p>
<p th:if="#dates.year(${post.createdAt}) == #dates.year(#dates.createNow())">This Year</p>
```

### #strings - String operations
```html
<!-- String methods -->
<p th:text="#strings.toUpperCase(${user.username})">UPPERCASE</p>
<p th:text="#strings.substring(${post.description}, 0, 100)">Excerpt</p>
<p th:if="#strings.isEmpty(${user.phone})">No phone number</p>
```

### #numbers - Number formatting
```html
<!-- Format numbers -->
<p th:text="#numbers.formatDecimal(${product.price}, 0, 'COMMA', 0, 'POINT')">Price</p>
<p th:text="#numbers.formatCurrency(${order.total})">Total</p>
```

### #lists và #arrays - Collection operations
```html
<!-- Check if empty -->
<div th:if="not #lists.isEmpty(${posts})">
    <p th:text="#lists.size(${posts}) + ' sản phẩm'">Product count</p>
</div>

<!-- Contains check -->
<span th:if="#lists.contains(${user.roles}, 'ADMIN')">Admin User</span>
```

## Ví dụ thực tế từ project

### 1. Trang danh sách sản phẩm (từ homePage.html)
```html
<div class="products-grid">
    <div th:each="post : ${posts}" class="product-card">
        <!-- Product image -->
        <img th:src="${post.mainImage}" 
             th:alt="${post.title}" 
             class="product-image">
        
        <!-- Product info -->
        <h3 th:text="${post.title}">Tên sản phẩm</h3>
        <p th:text="${post.description}">Mô tả sản phẩm</p>
        
        <!-- Price formatting -->
        <span class="price" 
              th:text="${#numbers.formatDecimal(post.price, 0, 'COMMA', 0, 'POINT')} + ' VND'">
            Giá
        </span>
        
        <!-- Conditional buttons -->
        <div class="actions">
            <a th:href="@{/product/{id}(id=${post.id})}" class="btn-view">Xem chi tiết</a>
            <button th:if="${session.user != null}" 
                    th:onclick="'addToWishlist(' + ${post.id} + ')'"
                    class="btn-wishlist">
                Yêu thích
            </button>
        </div>
    </div>
    
    <!-- Empty state -->
    <div th:if="${#lists.isEmpty(posts)}" class="empty-state">
        <p>Không có sản phẩm nào</p>
    </div>
</div>
```

### 2. Form đăng ký/đăng nhập (từ signUp.html)
```html
<form th:object="${userRequest}" 
      th:action="@{/auth/register}" 
      method="post" 
      class="registration-form">
      
    <!-- Username field -->
    <div class="form-group">
        <label for="username">Tên đăng nhập</label>
        <input type="text" 
               id="username" 
               th:field="*{username}" 
               th:class="${#fields.hasErrors('username')} ? 'error' : ''"
               required>
        <span th:if="${#fields.hasErrors('username')}" 
              th:errors="*{username}" 
              class="error-message">
            Username error
        </span>
    </div>
    
    <!-- Email field -->
    <div class="form-group">
        <label for="email">Email</label>
        <input type="email" 
               id="email" 
               th:field="*{email}"
               th:class="${#fields.hasErrors('email')} ? 'error' : ''"
               required>
        <span th:if="${#fields.hasErrors('email')}" 
              th:errors="*{email}" 
              class="error-message">
            Email error
        </span>
    </div>
    
    <!-- Password field -->
    <div class="form-group">
        <label for="password">Mật khẩu</label>
        <input type="password" 
               id="password" 
               th:field="*{password}"
               required>
    </div>
    
    <button type="submit">Đăng ký</button>
</form>

<!-- Success/Error messages -->
<div th:if="${successMessage}" 
     class="alert alert-success" 
     th:text="${successMessage}">
    Success message
</div>
<div th:if="${errorMessage}" 
     class="alert alert-error" 
     th:text="${errorMessage}">
    Error message
</div>
```

### 3. Trang quản lý giao dịch (từ memberTransactionHistoryPage.html)
```html
<div class="transaction-history">
    <table class="transaction-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Sản phẩm</th>
                <th>Ngày tạo</th>
                <th>Trạng thái</th>
                <th>Giá</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <tr th:each="transaction : ${transactions}">
                <td th:text="${transaction.transactionId}">ID</td>
                <td th:text="${transaction.post.title}">Product</td>
                <td th:text="${#dates.format(transaction.createdAt, 'dd-MM-yyyy')}">Date</td>
                
                <!-- Status with different styles -->
                <td>
                    <span th:switch="${transaction.status}">
                        <span th:case="'COMPLETED'" class="status completed">Hoàn thành</span>
                        <span th:case="'PENDING'" class="status pending">Đang xử lý</span>
                        <span th:case="'CANCELLED'" class="status cancelled">Đã hủy</span>
                        <span th:case="*" class="status unknown">Không rõ</span>
                    </span>
                </td>
                
                <td th:text="${#numbers.formatDecimal(transaction.totalAmount, 0, 'COMMA', 0, 'POINT')} + ' VND'">
                    Price
                </td>
                
                <!-- Conditional action buttons -->
                <td>
                    <a th:href="@{/transaction/{id}(id=${transaction.transactionId})}" 
                       class="btn-view">Xem</a>
                    
                    <button th:if="${transaction.review == null and transaction.status == 'COMPLETED'}" 
                            th:data-transaction-id="${transaction.transactionId}"
                            class="btn-review">
                        Đánh giá
                    </button>
                </td>
            </tr>
        </tbody>
    </table>
    
    <!-- Pagination -->
    <div th:if="${totalPages > 1}" class="pagination">
        <a th:if="${currentPage > 0}" 
           th:href="@{/transactions(page=${currentPage - 1})}"
           class="page-link">
            Trước
        </a>
        
        <span th:each="pageNum : ${#numbers.sequence(0, totalPages - 1)}">
            <a th:href="@{/transactions(page=${pageNum})}" 
               th:text="${pageNum + 1}"
               th:class="${pageNum == currentPage} ? 'page-link active' : 'page-link'">
                Page
            </a>
        </span>
        
        <a th:if="${currentPage < totalPages - 1}" 
           th:href="@{/transactions(page=${currentPage + 1})}"
           class="page-link">
            Sau
        </a>
    </div>
</div>
```

## Best Practices khi sử dụng Thymeleaf

### 1. Sử dụng Safe Navigation
```html
<!-- Safe navigation với ?. -->
<p th:text="${user?.profile?.address}">Address</p>
<img th:src="${post?.mainImage}" th:alt="${post?.title}">
```

### 2. Conditional CSS Classes
```html
<!-- Better approach -->
<div th:class="'user-card' + (${user.isActive} ? ' active' : ' inactive')">
    User Info
</div>
```

### 3. Form Validation Display
```html
<input th:field="*{email}" 
       th:class="${#fields.hasErrors('email')} ? 'form-control error' : 'form-control'">
<span th:if="${#fields.hasErrors('email')}" 
      th:errors="*{email}" 
      class="error-text"></span>
```

### 4. Reusable Fragments
```html
<!-- fragments/common.html -->
<div th:fragment="alert(type, message)">
    <div th:class="'alert alert-' + ${type}" th:text="${message}"></div>
</div>

<!-- Usage -->
<div th:replace="fragments/common :: alert('success', ${successMessage})"></div>
```

### 5. Performance Tips
- Sử dụng `th:remove="tag"` để loại bỏ wrapper elements không cần thiết
- Cache fragments khi có thể
- Sử dụng `th:inline="none"` khi không cần inline processing
- Tránh logic phức tạp trong templates, đưa vào Controller

Thymeleaf cung cấp một cách mạnh mẽ và linh hoạt để tạo ra các view động trong ứng dụng Spring Boot, với syntax tự nhiên và dễ hiểu cho cả developers và designers.
