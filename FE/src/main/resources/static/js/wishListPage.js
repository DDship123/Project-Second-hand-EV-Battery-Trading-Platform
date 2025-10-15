// /**
//  * Wishlist Page JavaScript
//  * Xử lý các chức năng trong trang danh sách yêu thích
//  */
//
// document.addEventListener("DOMContentLoaded", function () {
//     // Lấy tất cả các nút heart trong wishlist
//     const heartButtons = document.querySelectorAll(".btn__heart");
//
//     // Thêm event listener cho mỗi nút heart
//     heartButtons.forEach((button) => {
//         button.addEventListener("click", function (event) {
//             event.preventDefault();
//
//             // Lấy item container chứa nút này
//             const itemContainer = this.closest(".container__body__item");
//             const itemTitle = itemContainer.querySelector(
//                 ".container__body__item__title"
//             ).textContent;
//
//             // Hiển thị confirm dialog
//             const confirmRemove = confirm(
//                 `Bạn có chắc chắn muốn bỏ "${itemTitle}" khỏi danh sách yêu thích không?`
//             );
//
//             if (confirmRemove) {
//                 // Thêm hiệu ứng fade out
//                 itemContainer.style.transition = "all 0.5s ease";
//                 itemContainer.style.opacity = "0";
//                 itemContainer.style.transform = "translateX(-100%)";
//
//                 // Xóa item sau khi animation hoàn thành
//                 setTimeout(() => {
//                     itemContainer.remove();
//
//                     // Kiểm tra nếu không còn item nào trong wishlist
//                     const remainingItems = document.querySelectorAll(
//                         ".container__body__item"
//                     );
//                     if (remainingItems.length === 0) {
//                         showEmptyWishlistMessage();
//                     }
//                 }, 500);
//
//                 // Hiển thị thông báo thành công
//                 showNotification("Đã xóa khỏi danh sách yêu thích", "success");
//             }
//         });
//
//         // Thêm hiệu ứng hover cho heart button
//         button.addEventListener("mouseenter", function () {
//             const icon = this.querySelector("i");
//             if (this.classList.contains("btn__heart--active")) {
//                 icon.classList.remove("fas");
//                 icon.classList.add("far");
//             }
//         });
//
//         button.addEventListener("mouseleave", function () {
//             const icon = this.querySelector("i");
//             if (this.classList.contains("btn__heart--active")) {
//                 icon.classList.remove("far");
//                 icon.classList.add("fas");
//             }
//         });
//     });
// });
//
// /**
//  * Hiển thị thông báo khi wishlist trống
//  */
// function showEmptyWishlistMessage() {
//     const containerBody = document.querySelector(".container__body");
//     containerBody.innerHTML = `
//     <div class="empty__wishlist">
//       <div class="empty__wishlist__icon">
//         <i class="far fa-heart"></i>
//       </div>
//       <h3 class="empty__wishlist__title">Danh sách yêu thích trống</h3>
//       <p class="empty__wishlist__message">Bạn chưa có sản phẩm nào trong danh sách yêu thích</p>
//       <a href="/" class="empty__wishlist__button">Khám phá sản phẩm</a>
//     </div>
//   `;
// }
//
// /**
//  * Hiển thị thông báo
//  * @param {string} message - Nội dung thông báo
//  * @param {string} type - Loại thông báo (success, error, info)
//  */
// function showNotification(message, type = "info") {
//     // Tạo element thông báo
//     const notification = document.createElement("div");
//     notification.className = `notification notification--${type}`;
//     notification.innerHTML = `
//     <div class="notification__content">
//       <i class="fas fa-${
//         type === "success"
//             ? "check-circle"
//             : type === "error"
//                 ? "exclamation-circle"
//                 : "info-circle"
//     }"></i>
//       <span>${message}</span>
//     </div>
//   `;
//
//     // Thêm styles cho notification
//     notification.style.cssText = `
//     position: fixed;
//     top: 20px;
//     right: 20px;
//     z-index: 10000;
//     background: ${
//         type === "success" ? "#28a745" : type === "error" ? "#dc3545" : "#17a2b8"
//     };
//     color: white;
//     padding: 15px 20px;
//     border-radius: 8px;
//     box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
//     transform: translateX(100%);
//     transition: transform 0.3s ease;
//   `;
//
//     // Thêm vào body
//     document.body.appendChild(notification);
//
//     // Hiển thị với animation
//     setTimeout(() => {
//         notification.style.transform = "translateX(0)";
//     }, 10);
//
//     // Tự động ẩn sau 3 giây
//     setTimeout(() => {
//         notification.style.transform = "translateX(100%)";
//         setTimeout(() => {
//             if (notification.parentNode) {
//                 notification.parentNode.removeChild(notification);
//             }
//         }, 300);
//     }, 3000);
// }
window.addEventListener('DOMContentLoaded', function() {
    const wishItems = document.querySelectorAll('.container__body__item');
    wishItems.forEach(item => {
        item.addEventListener('click', function(event) {
            const postId =  item.querySelector("input[type='hidden']").value;
            window.location.href = `/home/product/detail/${postId}`;
        }
        );
    });
});