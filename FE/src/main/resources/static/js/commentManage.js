window.addEventListener('load', function() {
    const selectionBtns = document.querySelectorAll(".filter-tabs .tab-btn");
    selectionBtns.forEach(function(btn) {
        btn.addEventListener('click', function() {
            const selectedStatus = this.getAttribute('data-tab');
            if (selectedStatus === "comments") {
                window.location.href = '/home/admin/comment-review-manage';
            }else {
                window.location.href = `/home/admin/comment-review-manage/review`;
            }
        });
    });

    if (window.location.href.includes('/review')) {
        selectionBtns[0].classList.remove('active');
        selectionBtns[1].classList.add('active');
    }else {
        selectionBtns[1].classList.remove('active');
        selectionBtns[0].classList.add('active');
    }

    commentBtns();

    reviewBtns();

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

function reviewBtns(){
    const approveBtn = document.querySelectorAll("#reviewsTab .review-approve-btn");
    approveBtn.forEach(function(btn) {
        btn.addEventListener('click', function() {
            const reviewId = this.getAttribute('data-comment-id');
            if (confirm('Bạn có chắc chắn muốn duyệt review này không?')) {
                window.location.href = `/home/admin/comment-review-manage/review/update-status?reviewId=${reviewId}&status=APPROVED`;
            }
        });
    });

    const rejectBtn = document.querySelectorAll("#reviewsTab .review-reject-btn");
    rejectBtn.forEach(function(btn) {
        btn.addEventListener('click', function() {
            const reviewId = this.getAttribute('data-comment-id');
            if (confirm('Bạn có chắc chắn muốn từ chối review này không?')) {
                window.location.href = `/home/admin/comment-review-manage/review/update-status?reviewId=${reviewId}&status=REJECTED`;
            }
        });
    });
}


function commentBtns(){
    const approveBtn = document.querySelectorAll("#commentsTab .comment-approve-btn");
    approveBtn.forEach(function(btn) {
        btn.addEventListener('click', function() {
            const commentId = this.getAttribute('data-comment-id');
            if (confirm('Bạn có chắc chắn muốn duyệt bình luận này không?')) {
                window.location.href = `/home/admin/comment-manage/update-status?commentId=${commentId}&status=APPROVED`;
            }
        });
    });

    const deleteBtn = document.querySelectorAll("#commentsTab .comment-reject-btn");
    deleteBtn.forEach(function(btn) {
        btn.addEventListener('click', function() {
            const commentId = this.getAttribute('data-comment-id');
            if (confirm('Bạn có chắc chắn muốn bỏ bình luận này không?')) {
                window.location.href = `/home/admin/comment-manage/update-status?commentId=${commentId}&status=REJECTED`;
            }
        });
    });
}