window.addEventListener('load', function() {
    const approveBtn = document.querySelectorAll("#commentsTab .comment-approve-btn");
    approveBtn.forEach(function(btn) {
        btn.addEventListener('click', function() {
            const commentId = this.getAttribute('data-comment-id');
            if (confirm('Bạn có chắc chắn muốn duyệt bình luận này không?')) {
                window.location.href = `home/admin/comment-manage/update-status?commentId=${commentId}&status=APPROVED`;
            }
        });
    });

    const deleteBtn = document.querySelectorAll("#commentsTab .comment-delete-btn");
    deleteBtn.forEach(function(btn) {
        btn.addEventListener('click', function() {
            const commentId = this.getAttribute('data-comment-id');
            if (confirm('Bạn có chắc chắn muốn bỏ bình luận này không?')) {
                window.location.href = `home/admin/comment-manage/update-status?commentId=${commentId}&status=REJECTED`;
            }
        });
    });
});