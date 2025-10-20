window.addEventListener('load', function() {
    const postDetailModal = document.getElementById('postDetailModal');
    if (window.location.href.includes('postId')) {
        postDetailModal.style.display = 'block';
    }
});