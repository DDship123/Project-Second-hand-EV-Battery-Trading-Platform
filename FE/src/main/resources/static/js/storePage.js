window.addEventListener("DOMContentLoaded", () => {
    const productLists = document.querySelectorAll(".product-list");
    const tabs = document.querySelectorAll(".tabs__item");
    console.log(tabs, productLists);

    tabs.forEach((tab, index) => {
        tab.addEventListener("click", () => {
            productLists.forEach((pl) => pl.classList.remove("active"));
            tabs.forEach((t) => t.classList.remove("tabs__item--active"));
            tab.classList.add("tabs__item--active");
            productLists[index].classList.add("active");
        });
    });

    const deleteButtons = document.querySelectorAll(".deleteBtn");
    deleteButtons.forEach((button) => {
        button.addEventListener("click", (event) => {
            event.preventDefault();
            if (confirm("Are you sure you want to delete this product?")) {
                window.location.href = button.getAttribute("href"); 
            }
        });
    });
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
