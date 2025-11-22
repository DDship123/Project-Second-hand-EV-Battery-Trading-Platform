window.addEventListener('DOMContentLoaded', function() {
    let currentUrl = new URL(window.location.href);
    let urlParams = currentUrl.searchParams;

    if (urlParams.get("successMessage") !== null) {
        alert(urlParams.get("successMessage"));
        urlParams.delete("successMessage");
        // Cập nhật URL mà không tải lại trang
        history.replaceState({}, '', currentUrl.toString());
    } else if (urlParams.get("errorMessage") !== null) {
        alert(urlParams.get("errorMessage"));
        urlParams.delete("errorMessage");
        // Cập nhật URL mà không tải lại trang
        history.replaceState({}, '', currentUrl.toString());
    }
});