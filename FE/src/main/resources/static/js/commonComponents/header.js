window.addEventListener('DOMContentLoaded', function() {
    const transactionHistoryBtn = document.querySelector(".btn__history");
    const windowHref = window.location.href.toString();

    transactionHistoryBtn.addEventListener("click", function() {
        if(windowHref.includes("transactionsHistory")) {
            return;
        }
        window.location.href = "/home/transactionsHistory";
    });

    const addPostBtn = document.querySelector(".btn__plus");
    addPostBtn.addEventListener("click", function() {
        if(windowHref.includes("postForm")) {
            return;
        }
        window.location.href = "/home/postForm";
    });

    // =====================================================
    //    PRODUCTS DROPDOWN MENU FUNCTIONALITY
    // =====================================================
    const productsDropdown = document.querySelector('.nav__link:not([href]):not(.active)');

    if (productsDropdown) {
        productsDropdown.addEventListener('click', function(e) {
            // e.preventDefault();
            // e.stopPropagation();

            // Toggle dropdown active class
            this.classList.toggle('dropdown-active');
        });
    }

    // =====================================================
    //    WISHLIST DROPDOWN FUNCTIONALITY
    // =====================================================
    const wishlistBtn = document.querySelector(".btn__wishlist");
    const wishlistList = document.querySelector(".wishlist__list");

    // Bật/tắt wishlist khi click vào nút
    wishlistBtn.addEventListener("click", function (event) {
        event.stopPropagation(); // Ngăn event lan truyền lên

        // Bật/tắt hiển thị wishlist
        wishlistList.classList.toggle("show");

        // Bật/tắt trạng thái active cho nút (vô hiệu hóa tooltip hover)
        wishlistBtn.classList.toggle("active");
    });

    // Ngăn wishlist đóng khi click vào bên trong nó
    wishlistList.addEventListener("click", function (event) {
        event.stopPropagation();
    });

    // =====================================================
    //    USER DROPDOWN FUNCTIONALITY
    // =====================================================
    const userDropdown = document.querySelector(".user__dropdown");
    const userDropdownContent = document.querySelector(".user__dropdown__content");

    userDropdown.addEventListener("click", function(event) {
        event.stopPropagation(); // Ngăn event lan truyền lên

        // Bật/tắt hiển thị dropdown
        userDropdownContent.classList.toggle("show");
    });

    // =====================================================
    //    GLOBAL CLICK HANDLER - CLOSE ALL DROPDOWNS
    // =====================================================
    document.addEventListener("click", function (event) {
        // Close products dropdown
        if (productsDropdown && !productsDropdown.contains(event.target)) {
            productsDropdown.classList.remove('dropdown-active');
        }

        // Close wishlist dropdown
        if (
            !wishlistBtn.contains(event.target) &&
            !wishlistList.contains(event.target)
        ) {
            wishlistList.classList.remove("show");
            wishlistBtn.classList.remove("active");
        }

        // Close user dropdown
        if (!userDropdown.contains(event.target)) {
            userDropdownContent.classList.remove("show");
        }
    });

    // // =====================================================
    // //    KEYBOARD NAVIGATION
    // // =====================================================
    // document.addEventListener('keydown', function(e) {
    //     if (e.key === 'Escape') {
    //         // Close all dropdowns on Escape key
    //         if (productsDropdown) {
    //             productsDropdown.classList.remove('dropdown-active');
    //         }
    //         wishlistList.classList.remove("show");
    //         wishlistBtn.classList.remove("active");
    //         userDropdownContent.classList.remove("show");
    //     }
    // });

    // =====================================================
    //    HEADER MARGIN ADJUSTMENT
    // =====================================================
    const header = document.querySelector(".header");
    if (!windowHref.includes("home") || windowHref.length > 26) {
        header.style.marginBottom = "30px";
    }

    const headerLogo = document.querySelector(".header__logo");
    headerLogo.addEventListener("click", function() {
        window.location.href = "/home";
    });
});