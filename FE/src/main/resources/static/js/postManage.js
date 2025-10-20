window.addEventListener('load', function() {
    const postDetailModal = document.getElementById('postDetailModal');
    if (window.location.href.includes('detail')) {
        postDetailModal.style.display = 'block';
        const closeBtn = postDetailModal.querySelector('.close');
        closeBtn.addEventListener('click', function() {
            postDetailModal.style.display = 'none';
            // Tạo một đối tượng URL từ địa chỉ hiện tại của trang web
            const url = new URL(window.location.href);
            // Xóa tham số 'postId' ra khỏi phần query string của URL (nếu có)
            // url.searchParams.delete('postId');
            // Cập nhật lại URL trên thanh địa chỉ của trình duyệt
            // bằng URL mới (đã xóa 'postId') mà không tải lại trang
            window.history.replaceState({}, document.title, url.toString());
            // window.location.href = '/home/admin';
        });
    }

    const approveButtons = document.querySelectorAll('.action-btn.approve');
    approveButtons.forEach(function(button) {
        button.addEventListener('click', function() {
            const postId = this.getAttribute('data-post-id');
            if (confirm('Bạn có chắc chắn muốn duyệt bài viết này không?')) {
                window.location.href = `/home/admin/post-manage/update-status?postId=${postId}&status=APPROVED`;
            }
        });
    });

    const rejectButtons = document.querySelectorAll('.action-btn.reject');
    rejectButtons.forEach(function(button) {
        button.addEventListener('click', function() {
            const postId = this.getAttribute('data-post-id');
            if (confirm('Bạn có chắc chắn muốn từ chối bài viết này không?')) {
                window.location.href = `/home/admin/post-manage/update-status?postId=${postId}&status=REJECTED`;
            }
        });
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