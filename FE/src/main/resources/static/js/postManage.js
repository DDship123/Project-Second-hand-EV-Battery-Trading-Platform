window.addEventListener('load', function() {
    const postDetailModal = document.getElementById('postDetailModal');
    if (window.location.href.includes('detail')) {
        postDetailModal.style.display = 'block';
        const closeBtn = postDetailModal.querySelector('.close');
        if (closeBtn) {
            closeBtn.addEventListener('click', function () {
                postDetailModal.style.display = 'none';

                // LẤY status từ URL hiện tại (trước khi đóng)
                const currentUrl = new URL(window.location.href);
                const currentStatus = currentUrl.searchParams.get('status');

                // Tạo URL sạch: luôn về /post-manage + giữ nguyên status hiện tại
                const cleanUrl = new URL('/home/admin/post-manage', window.location.origin);

                // Nếu có status (đang ở tab nào thì giữ tab đó)
                if (currentStatus) {
                    cleanUrl.searchParams.set('status', currentStatus);
                } else {
                    cleanUrl.searchParams.set('status', 'PENDING'); // mặc định nếu không có
                }

                // Cập nhật URL mà không reload
                window.history.replaceState({}, document.title, cleanUrl.toString());
            });
        }
    }

    const approveButtons = document.querySelectorAll('.action-btn.approve');
    approveButtons.forEach(function(button) {
        approvePost(button);
    });

    const rejectButtons = document.querySelectorAll('.action-btn.reject');
    rejectButtons.forEach(function(button) {
        rejectPost(button);
    });


    const buttonDetails = document.querySelectorAll('#postDetailModal .action-btn');
    if (buttonDetails !== null && buttonDetails.length === 2){
        buttonDetails[0].addEventListener('click', function() {
            approvePost(buttonDetails[0]);
        });
        buttonDetails[1].addEventListener('click', function() {
            rejectPost(buttonDetails[1]);
        });
    }



    const filterTabs = document.querySelector(".filter-tabs");
    if (filterTabs) {
        const tabBtn = filterTabs.querySelectorAll("button");
        const windowUrl = new URL(window.location.href);
        const currentStatus = windowUrl.searchParams.get("status");
        if (currentStatus !== null) {
            tabBtn.forEach(function(btn) {
                if (currentStatus === "PENDING" && btn.innerText === "Đợi duyệt"){
                    btn.classList.add("active");
                }else if (currentStatus === "APPROVED" && btn.innerText === "Đã duyệt"){
                    btn.classList.add("active");
                }else if (currentStatus === "REJECTED" && btn.innerText === "Từ chối"){
                    btn.classList.add("active");
                }else if (currentStatus === "SOLD" && btn.innerText === "Đã bán"){
                    btn.classList.add("active");
                }
                else {
                    btn.classList.remove("active");
                }
            });
        }

        tabBtn.forEach(function(btn) {
            btn.addEventListener("click", function() {
                if (windowUrl.searchParams.has("successMessage")){
                    windowUrl.searchParams.delete("successMessage");
                }else if (windowUrl.searchParams.has("errorMessage")){
                    windowUrl.searchParams.delete("errorMessage");
                }

                if (windowUrl.searchParams.has("postId")){
                    windowUrl.searchParams.delete("postId");
                }
                if (btn.innerText === "Đợi duyệt"){
                    windowUrl.searchParams.set("status", "PENDING");
                    window.location.href = windowUrl.toString().replaceAll("/detail","");
                }else if (btn.innerText === "Đã duyệt"){
                    windowUrl.searchParams.set("status", "APPROVED");
                    window.location.href = windowUrl.toString().replaceAll("/detail","");
                }else if (btn.innerText === "Từ chối"){
                    windowUrl.searchParams.set("status", "REJECTED");
                    window.location.href = windowUrl.toString().replaceAll("/detail","");
                }else if (btn.innerText === "Đã bán"){
                    windowUrl.searchParams.set("status", "SOLD");
                    window.location.href = windowUrl.toString().replaceAll("/detail","");
                }
            });
        });
    }

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