/**
 * Image Gallery Carousel cho Product Details Page
 * Xử lý việc chuyển đổi ảnh sản phẩm khi click vào nút trái/phải
 */

// Đợi trang web load hoàn toàn trước khi thực thi JavaScript
window.addEventListener("load", function () {
    // === LẤY CÁC ELEMENT CẦN THIẾT ===

    // Lấy nút mũi tên trái (previous image)
    let leftImagebtn = document.querySelector(
        ".container__item__infor__image__main i:nth-child(2)"
    );

    // Lấy nút mũi tên phải (next image)
    let rightImagebtn = document.querySelector(
        ".container__item__infor__image__main i:last-child"
    );

    // Lấy ảnh chính (ảnh lớn đang hiển thị)
    let mainImage = document.querySelector(
        ".container__item__infor__image__main img"
    );

    // Lấy danh sách tất cả ảnh nhỏ (thumbnail images)
    let imageList = document.querySelectorAll(
        ".container__item__infor__image__list img"
    );

    // === SỰ KIỆN CLICK NÚT TRÁI (PREVIOUS) ===
    leftImagebtn.addEventListener("click", function () {
        // Tìm vị trí (index) của ảnh hiện tại trong danh sách
        // Array.from() chuyển NodeList thành Array để sử dụng findIndex()
        // findIndex() tìm index của ảnh có src giống với ảnh chính
        let currentIndex = Array.from(imageList).findIndex(
            (img) => img.src === mainImage.src
        );

        // Tính index của ảnh trước đó với wrap-around
        // - Trừ 1 để lùi về ảnh trước
        // - Cộng imageList.length để tránh số âm
        // - Modulo để wrap-around: từ ảnh đầu (0) quay về ảnh cuối
        let prevIndex = (currentIndex - 1 + imageList.length) % imageList.length;

        // Cập nhật src của ảnh chính thành ảnh trước đó
        mainImage.src = imageList[prevIndex].src;
    });

    // === SỰ KIỆN CLICK NÚT PHẢI (NEXT) ===
    rightImagebtn.addEventListener("click", function () {
        // Tìm vị trí (index) của ảnh hiện tại trong danh sách
        let currentIndex = Array.from(imageList).findIndex(
            (img) => img.src === mainImage.src
        );

        // Tính index của ảnh tiếp theo với wrap-around
        // - Cộng 1 để tiến tới ảnh sau
        // - Modulo để wrap-around: từ ảnh cuối quay về ảnh đầu (0)
        let nextIndex = (currentIndex + 1) % imageList.length;

        // Cập nhật src của ảnh chính thành ảnh tiếp theo
        mainImage.src = imageList[nextIndex].src;
    });

    /*
     * CÁCH HOẠT ĐỘNG:
     *
     * Ví dụ có 4 ảnh: [Ảnh0, Ảnh1, Ảnh2, Ảnh3]
     *
     * Nếu đang ở Ảnh2 (index = 2):
     * - Click trái: prevIndex = (2-1+4) % 4 = 1 → Ảnh1
     * - Click phải: nextIndex = (2+1) % 4 = 3 → Ảnh3
     *
     * Nếu đang ở Ảnh0 (index = 0):
     * - Click trái: prevIndex = (0-1+4) % 4 = 3 → Ảnh3 (wrap to end)
     * - Click phải: nextIndex = (0+1) % 4 = 1 → Ảnh1
     *
     * Nếu đang ở Ảnh3 (index = 3):
     * - Click trái: prevIndex = (3-1+4) % 4 = 2 → Ảnh2
     * - Click phải: nextIndex = (3+1) % 4 = 0 → Ảnh0 (wrap to start)
     */

    const transactionBtn = document.querySelector(".container__item__infor__details__action");
    transactionBtn.addEventListener("click", function () {
        const postId = transactionBtn.getAttribute("data-postId");
        if (confirm("Bạn có chắc chắn muốn thực hiện giao dịch cho sản phẩm này không?")) {
            window.location.href = `/home/transaction/${postId}`;
        }
    });

    const urlParams = new URLSearchParams(window.location.search);
    const successMessage = urlParams.get('successMessage');
    if (successMessage) {
        alert(successMessage);
    }
    const errorMessage = urlParams.get('errorMessage');
    if (errorMessage) {
        alert(errorMessage);
    }
});
