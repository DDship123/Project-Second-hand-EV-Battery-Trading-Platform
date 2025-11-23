window.addEventListener('load', function() {
    const postDetailModal = document.getElementById('postDetailModal');

    // Mở modal nếu đang ở trang chi tiết
    if (window.location.pathname.includes('/detail') || window.location.href.includes('postId=')) {
        postDetailModal.style.display = 'block';

        const closeBtn = postDetailModal.querySelector('.close');
        if (closeBtn) {
            closeBtn.addEventListener('click', function () {
                postDetailModal.style.display = 'none';

                const newUrl = new URL(window.location.origin + '/home/admin/post-manage');
                const currentStatus = new URL(window.location.href).searchParams.get('status');
                if (currentStatus) {
                    newUrl.searchParams.set('status', currentStatus);
                }
                window.history.replaceState({}, document.title, newUrl.toString());
            });
        }
    }

    // Xử lý nút duyệt/từ chối
    document.querySelectorAll('.action-btn.approve').forEach(approvePost);
    document.querySelectorAll('.action-btn.reject').forEach(rejectPost);

    const buttonDetails = document.querySelectorAll('#postDetailModal .action-btn');
    if (buttonDetails.length === 2) {
        approvePost(buttonDetails[0]);
        rejectPost(buttonDetails[1]);
    }

    // XỬ LÝ TAB – CHỈ MỘT LẦN, DÙNG URL SẠCH
    const filterTabs = document.querySelector(".filter-tabs");
    if (filterTabs) {
        const tabBtn = filterTabs.querySelectorAll("button");

        // Highlight tab hiện tại
        const currentStatus = new URL(window.location.href).searchParams.get("status") || "PENDING";
        tabBtn.forEach(btn => {
            btn.classList.remove("active");
            const text = btn.innerText.trim();
            if ((currentStatus === "PENDING" && text.includes("Đợi duyệt")) ||
                (currentStatus === "APPROVED" && text.includes("Đã duyệt")) ||
                (currentStatus === "REJECTED" && text.includes("Từ chối")) ||
                (currentStatus === "SOLD" && text.includes("Đã bán"))) {
                btn.classList.add("active");
            }
        });

        // Gắn sự kiện click – chỉ 1 lần, luôn dùng URL sạch
        tabBtn.forEach(btn => {
            btn.onclick = function() {
                const baseUrl = new URL('/home/admin/post-manage', window.location.origin);
                const text = this.innerText.trim();

                if (text.includes("Đợi duyệt")) baseUrl.searchParams.set("status", "PENDING");
                else if (text.includes("Đã duyệt")) baseUrl.searchParams.set("status", "APPROVED");
                else if (text.includes("Từ chối")) baseUrl.searchParams.set("status", "REJECTED");
                else if (text.includes("Đã bán")) baseUrl.searchParams.set("status", "SOLD");

                window.location.href = baseUrl.toString();
            };
        });
    }

    // Hiển thị thông báo
    const urlParams = new URLSearchParams(window.location.search);
    const successMessage = urlParams.get('successMessage');
    const errorMessage = urlParams.get('errorMessage');
    if (successMessage) alert(decodeURIComponent(successMessage));
    if (errorMessage) alert(decodeURIComponent(errorMessage));
});

function approvePost(button) {
    button.addEventListener('click', function() {
        const postId = this.getAttribute('data-post-id');
        if (confirm('Bạn có chắc chắn muốn duyệt bài viết này không?')) {
            window.location.href = `/home/admin/post-manage/update-status?postId=${postId}&status=APPROVED`;
        }
    });
}

function rejectPost(button) {
    button.addEventListener('click', function() {
        const postId = this.getAttribute('data-post-id');
        if (confirm('Bạn có chắc chắn muốn từ chối bài viết này không?')) {
            window.location.href = `/home/admin/post-manage/update-status?postId=${postId}&status=REJECTED`;
        }
    });
}