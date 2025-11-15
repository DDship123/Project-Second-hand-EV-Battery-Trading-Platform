window.addEventListener('load', function() {
    const windowUrl = new URL(window.location.href);
    const unauthorized = windowUrl.searchParams.get('unauthorized');
    if (unauthorized === 'true') {
        alert("Bạn không có quyền truy cập vào trang này. Vui lòng đăng nhập.");
    }
});