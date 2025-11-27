/**
 * Image Gallery Carousel cho Product Details Page
 * Xử lý việc chuyển đổi ảnh sản phẩm khi click vào nút trái/phải
 */

// Đợi trang web load hoàn toàn trước khi thực thi JavaScript
window.addEventListener("load", function () {
    // === LẤY CÁC ELEMENT CẦN THIẾT ===

    // Lấy nút mũi tên trái (previous image)
    // let leftImagebtn = document.querySelector(
    //     ".container__item__infor__image__main i:nth-child(2)"
    // );
    //
    // // Lấy nút mũi tên phải (next image)
    // let rightImagebtn = document.querySelector(
    //     ".container__item__infor__image__main i:last-child"
    // );

    // Lấy ảnh chính (ảnh lớn đang hiển thị)
    let mainImage = document.querySelector(
        ".container__item__infor__image__main img"
    );

    // Lấy danh sách tất cả ảnh nhỏ (thumbnail images)
    let imageList = document.querySelectorAll(
        ".container__item__infor__image__list img"
    );

    imageList.forEach(function (image) {
        image.addEventListener("click", function () {
            // Lấy src của ảnh nhỏ được click
            let newSrc = this.src;
            let currentSrc = mainImage.src;
            // Đổi src của ảnh chính thành src của ảnh nhỏ được click
            mainImage.src = newSrc;
            this.src = currentSrc;
        });
    });



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
