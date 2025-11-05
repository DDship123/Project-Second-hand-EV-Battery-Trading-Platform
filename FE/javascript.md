# javascript.md

## JavaScript trong Spring Boot Project

Tài liệu này giải thích các khái niệm và patterns JavaScript được sử dụng trong project Spring Boot.

## DOM Selectors nâng cao

### 1. CSS Selector với multiple conditions
```javascript
// Từ header.js dòng 23
const productsDropdown = document.querySelector('.nav__link:not([href]):not(.active)');
```

**Giải thích chi tiết:**
- **document.querySelector()**: Tìm element đầu tiên match với CSS selector
- **'.nav__link'**: Class selector - tìm element có class "nav__link"
- **':not([href])'**: Pseudo-class selector - loại trừ elements có attribute "href"
- **':not(.active)'**: Pseudo-class selector - loại trừ elements có class "active"

**Mục đích trong context:**
- Tìm nav link không có href (không phải link thực) và không có class active
- Thường dùng cho dropdown menu button
- Element này sẽ trigger dropdown thay vì navigate

**Ví dụ HTML tương ứng:**
```html
<!-- Element này sẽ được chọn -->
<a class="nav__link">SẢN PHẨM <i class="fas fa-chevron-down"></i></a>

<!-- Element này KHÔNG được chọn (có href) -->
<a class="nav__link" href="/products">SẢN PHẨM</a>

<!-- Element này KHÔNG được chọn (có class active) -->
<a class="nav__link active">SẢN PHẨM</a>

<!-- Element này KHÔNG được chọn (có cả href và active) -->
<a class="nav__link active" href="/products">SẢN PHẨM</a>
```

**Cách sử dụng trong project:**
```javascript
// Từ header.js
if (productsDropdown) {
    productsDropdown.addEventListener('click', function(e) {
        // Toggle dropdown active class
        this.classList.toggle('dropdown-active');
        
        // Hiển thị/ẩn dropdown menu
        const dropdownMenu = this.querySelector('.nav__link__list');
        if (dropdownMenu) {
            dropdownMenu.style.display = 
                dropdownMenu.style.display === 'block' ? 'none' : 'block';
        }
    });
}
```

### Các CSS Selector patterns khác thường dùng:

#### Attribute Selectors
```javascript
// Chọn elements có attribute cụ thể
document.querySelector('[data-id]');                // Có data-id attribute
document.querySelector('[data-id="123"]');          // data-id = "123"
document.querySelector('[data-id^="user"]');        // data-id bắt đầu với "user"
document.querySelector('[data-id$="123"]');         // data-id kết thúc với "123"
document.querySelector('[data-id*="test"]');        // data-id chứa "test"

// Ví dụ từ project
document.querySelector('[data-transaction-id]');     // Button có transaction ID
document.querySelector('input[type="hidden"]');      // Hidden input trong wishlist
```

#### Pseudo-class Selectors
```javascript
// Structural pseudo-classes
document.querySelector('li:first-child');           // Li đầu tiên
document.querySelector('li:last-child');            // Li cuối cùng
document.querySelector('li:nth-child(2)');          // Li thứ 2

// State pseudo-classes
document.querySelector('input:checked');            // Input được check
document.querySelector('input:disabled');           // Input bị disabled
document.querySelector('a:not(.active)');           // Link không có class active
```

#### Combinators
```javascript
// Descendant combinator (space)
document.querySelector('.container .item');         // .item bên trong .container

// Child combinator (>)
document.querySelector('.container > .item');       // .item là con trực tiếp của .container

// Adjacent sibling (+)
document.querySelector('h1 + p');                   // p ngay sau h1

// General sibling (~)
document.querySelector('h1 ~ p');                   // p là sibling của h1
```

## URL và Query Parameters

### 2. URLSearchParams API
```javascript
// Từ searchBar.js dòng 115
const params = new URLSearchParams();
```

**Giải thích chi tiết:**
- **URLSearchParams**: Web API để làm việc với query parameters
- **new URLSearchParams()**: Tạo object trống để xây dựng query string
- **Không cần import**: Built-in trong modern browsers

**Các cách khởi tạo URLSearchParams:**

#### 1. Tạo trống (như trong project)
```javascript
const params = new URLSearchParams();
// Kết quả: object trống, sẵn sàng add parameters
```

#### 2. Từ current URL
```javascript
const params = new URLSearchParams(window.location.search);
// Nếu URL: https://example.com?category=xe-dien&page=2
// params sẽ chứa: category=xe-dien, page=2
```

#### 3. Từ string
```javascript
const params = new URLSearchParams('?category=xe-dien&page=2');
const params2 = new URLSearchParams('category=xe-dien&page=2'); // Không cần ?
```

#### 4. Từ object (không trực tiếp support, cần convert)
```javascript
const queryObj = { category: 'xe-dien', page: 2 };
const params = new URLSearchParams(Object.entries(queryObj));
```

**Các methods quan trọng:**

#### Thêm parameters
```javascript
// Từ searchBar.js context
const params = new URLSearchParams();

// Thêm single value
params.append('category', 'xe-dien');
params.append('keyword', 'honda');

// Set value (replace nếu đã tồn tại)
params.set('page', '1');
params.set('sort', 'price-asc');

// Thêm multiple values cho cùng key
params.append('tags', 'electric');
params.append('tags', 'eco-friendly');
```

#### Đọc parameters
```javascript
// Get single value
const category = params.get('category');        // 'xe-dien'
const page = params.get('page');               // '1'
const notExist = params.get('xyz');            // null

// Get all values for a key
const tags = params.getAll('tags');            // ['electric', 'eco-friendly']

// Check existence
const hasCategory = params.has('category');    // true
const hasPrice = params.has('price');          // false
```

#### Xóa parameters
```javascript
// Xóa specific parameter
params.delete('page');

// Xóa all values của một key
params.delete('tags'); // Xóa tất cả tags
```

#### Convert và sử dụng
```javascript
// Convert thành string
const queryString = params.toString();
// Kết quả: "category=xe-dien&keyword=honda&sort=price-asc"

// Sử dụng trong URL
const searchUrl = `/home/search?${params.toString()}`;
// Kết quả: "/home/search?category=xe-dien&keyword=honda&sort=price-asc"

// Redirect với parameters
window.location.href = searchUrl;
```

**Ví dụ thực tế từ searchBar.js:**
```javascript
// Build search URL với parameters
function performSearch() {
    let searchUrl = "/home";
    const params = new URLSearchParams();

    // Thêm search parameters based on form inputs
    if (productTypeInputValue === "Xe điện") {
        searchUrl = "/home/vehicle";
        params.append('type', 'electric');
    } else if (productTypeInputValue === "Pin xe") {
        searchUrl = "/home/battery";
        params.append('type', 'battery');
    }

    // Thêm keyword nếu có
    const keyword = document.querySelector('#search-input').value.trim();
    if (keyword) {
        params.append('keyword', keyword);
    }

    // Thêm price range nếu có
    const minPrice = document.querySelector('#min-price').value;
    const maxPrice = document.querySelector('#max-price').value;
    if (minPrice) params.append('minPrice', minPrice);
    if (maxPrice) params.append('maxPrice', maxPrice);

    // Build final URL
    const finalUrl = params.toString() ? 
        `${searchUrl}?${params.toString()}` : 
        searchUrl;

    // Navigate to search results
    window.location.href = finalUrl;
}
```

### URL Manipulation patterns khác:

#### Đọc current URL parameters
```javascript
// Từ wishListPage.js
const windowUrl = new URL(window.location.href);
if (windowUrl.searchParams.get("successMessage") !== null) {
    alert(windowUrl.searchParams.get("successMessage"));
}
if (windowUrl.searchParams.get("errorMessage") !== null) {
    alert(windowUrl.searchParams.get("errorMessage"));
}
```

#### Update URL without page reload
```javascript
// Update URL parameters without refresh
function updateUrlParams(newParams) {
    const url = new URL(window.location);
    
    // Clear existing params
    url.search = '';
    
    // Add new params
    Object.entries(newParams).forEach(([key, value]) => {
        if (value) url.searchParams.set(key, value);
    });
    
    // Update browser URL without reload
    window.history.pushState({}, '', url);
}

// Usage
updateUrlParams({
    category: 'xe-dien',
    page: 2,
    sort: 'price-desc'
});
```

#### Build complex search URLs
```javascript
// Advanced search URL builder
class SearchUrlBuilder {
    constructor(baseUrl = '/home') {
        this.baseUrl = baseUrl;
        this.params = new URLSearchParams();
    }
    
    category(value) {
        if (value && value !== 'Danh mục') {
            this.params.set('category', value);
        }
        return this;
    }
    
    keyword(value) {
        if (value && value.trim()) {
            this.params.set('keyword', value.trim());
        }
        return this;
    }
    
    priceRange(min, max) {
        if (min) this.params.set('minPrice', min);
        if (max) this.params.set('maxPrice', max);
        return this;
    }
    
    page(value) {
        if (value && value > 1) {
            this.params.set('page', value);
        }
        return this;
    }
    
    build() {
        const queryString = this.params.toString();
        return queryString ? `${this.baseUrl}?${queryString}` : this.baseUrl;
    }
}

// Usage
const searchUrl = new SearchUrlBuilder('/home/search')
    .category('xe-dien')
    .keyword('honda')
    .priceRange(1000000, 5000000)
    .page(1)
    .build();
// Result: "/home/search?category=xe-dien&keyword=honda&minPrice=1000000&maxPrice=5000000"
```

## Event Handling Patterns

### Event Delegation
```javascript
// Thay vì add listener cho từng element
document.querySelectorAll('.wishlist-item').forEach(item => {
    item.addEventListener('click', handleClick); // Tốn memory
});

// Sử dụng event delegation
document.querySelector('.wishlist-container').addEventListener('click', function(e) {
    const wishlistItem = e.target.closest('.wishlist-item');
    if (wishlistItem) {
        handleWishlistClick(wishlistItem);
    }
});
```

### Prevent Default và Stop Propagation
```javascript
// Từ header.js - dropdown click
productsDropdown.addEventListener('click', function(e) {
    e.preventDefault();     // Ngăn default action (navigate)
    e.stopPropagation();   // Ngăn event bubble up
    
    this.classList.toggle('dropdown-active');
});

// Click outside để đóng dropdown
document.addEventListener('click', function(e) {
    if (!e.target.closest('.products-dropdown')) {
        // Đóng tất cả dropdowns
        document.querySelectorAll('.dropdown-active')
            .forEach(dropdown => dropdown.classList.remove('dropdown-active'));
    }
});
```

## Practical Examples từ Project

### 1. Wishlist Item Click Handler
```javascript
// Từ wishListPage.js - Navigate to product detail
window.addEventListener('DOMContentLoaded', function() {
    const wishItems = document.querySelectorAll('.container__body__item');
    
    wishItems.forEach(item => {
        item.addEventListener('click', function(event) {
            // Lấy postId từ hidden input
            const postId = item.querySelector("input[type='hidden']").value;
            
            // Navigate đến product detail page
            window.location.href = `/home/product/detail/${postId}`;
        });
    });
});
```

### 2. URL-based Notifications
```javascript
// Đọc và hiển thị messages từ URL parameters
const windowUrl = new URL(window.location.href);

// Success message
const successMsg = windowUrl.searchParams.get("successMessage");
if (successMsg) {
    showNotification(successMsg, 'success');
}

// Error message  
const errorMsg = windowUrl.searchParams.get("errorMessage");
if (errorMsg) {
    showNotification(errorMsg, 'error');
}
```

### 3. Dynamic Content Loading
```javascript
// Load more content với URL parameters
function loadMoreItems(page = 1) {
    const params = new URLSearchParams(window.location.search);
    params.set('page', page);
    
    fetch(`/api/wishlist?${params.toString()}`)
        .then(response => response.json())
        .then(data => {
            appendItemsToContainer(data.items);
            updatePagination(data.pagination);
        })
        .catch(error => {
            showNotification('Lỗi tải dữ liệu', 'error');
        });
}
```

## Best Practices

### 1. Null-safe Operations
```javascript
// Kiểm tra element tồn tại trước khi sử dụng
const dropdown = document.querySelector('.nav__link:not([href]):not(.active)');
if (dropdown) {
    dropdown.addEventListener('click', handleDropdown);
}

// Null-safe với optional chaining (modern browsers)
const postId = item.querySelector("input[type='hidden']")?.value;
if (postId) {
    // Safe to use postId
}
```

### 2. URL Parameter Validation
```javascript
// Validate parameters before using
function getValidPageNumber() {
    const params = new URLSearchParams(window.location.search);
    const page = parseInt(params.get('page')) || 1;
    return Math.max(1, page); // Ensure minimum page is 1
}

// Sanitize search keywords
function sanitizeKeyword(keyword) {
    return keyword ? keyword.trim().replace(/[<>]/g, '') : '';
}
```

### 3. Performance Optimization
```javascript
// Debounce search input
let searchTimeout;
document.querySelector('#search-input').addEventListener('input', function(e) {
    clearTimeout(searchTimeout);
    searchTimeout = setTimeout(() => {
        performSearch(e.target.value);
    }, 300); // Wait 300ms after user stops typing
});

// Cache DOM queries
const searchElements = {
    input: document.querySelector('#search-input'),
    categorySelect: document.querySelector('#category-select'),
    submitBtn: document.querySelector('#search-submit')
};
```

JavaScript trong Spring Boot project chủ yếu tập trung vào DOM manipulation, event handling, và URL management để tạo ra user experience mượt mà và tương tác tốt với server-side rendering.
