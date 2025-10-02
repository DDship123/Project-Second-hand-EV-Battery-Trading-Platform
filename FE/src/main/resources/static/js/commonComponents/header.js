window.addEventListener('DOMContentLoaded', function() {
    const transactionHistoryBtn = document.querySelector(".btn__history");
    const windowHref = window.location.href.toString();

    transactionHistoryBtn.addEventListener("click", function() {
        if(windowHref.includes("transactionsHistory")) {
            return;
        }
        window.location.href = "/home/transactionsHistory";
    });

    // Lấy các phần tử DOM
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

    // Đóng wishlist khi click ra ngoài
    document.addEventListener("click", function (event) {
        if (
            !wishlistBtn.contains(event.target) &&
            !wishlistList.contains(event.target)
        ) {
            wishlistList.classList.remove("show");
            wishlistBtn.classList.remove("active");
        }
    });

    // Ngăn wishlist đóng khi click vào bên trong nó
    wishlistList.addEventListener("click", function (event) {
        event.stopPropagation();
    });

    const header = document.querySelector(".header");
    if (!windowHref.includes("home") || windowHref.length > 26) {
        header.style.marginBottom = "30px";
    }

    const userDropdown = document.querySelector(".user__dropdown");
    const userDropdownContent = document.querySelector(".user__dropdown__content");
    userDropdown.addEventListener("click", function(event) {
        event.stopPropagation(); // Ngăn event lan truyền lên

        // Bật/tắt hiển thị dropdown
        userDropdownContent.classList.toggle("show");
    });

    // Đóng dropdown khi click ra ngoài
    document.addEventListener("click", function (event) {
        if (
            !userDropdownContent.contains(event.target)
        ) {
            userDropdownContent.classList.remove("show");
        }
    });

});