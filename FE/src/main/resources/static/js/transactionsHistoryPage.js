// Chức năng dropdown cho trạng thái giao dịch
document.addEventListener("DOMContentLoaded", function () {
    const statusTitle = document.querySelector(
        ".container__header__category__status__title"
    );
    const statusList = document.querySelector(
        ".container__header__category__status__List"
    );
    const statusSpan = statusTitle.querySelector("span");
    const statusListItems = statusList.querySelectorAll("li");
    const playIcon = statusTitle.querySelector("i");

    // Bật/tắt dropdown khi click vào tiêu đề trạng thái
    statusTitle.addEventListener("click", function (e) {
        e.stopPropagation();
        toggleDropdown();
    });

    // Xử lý việc chọn các mục trong danh sách
    statusListItems.forEach(function (item) {
        item.addEventListener("click", function (e) {
            e.stopPropagation();

            // Cập nhật text của span với text của mục được chọn
            statusSpan.textContent = this.textContent;

            // Đóng dropdown sau khi chọn
            closeDropdown();
        });
    });

    // Đóng dropdown khi click bên ngoài
    document.addEventListener("click", function () {
        closeDropdown();
    });

    // Ngăn dropdown đóng khi click bên trong danh sách
    statusList.addEventListener("click", function (e) {
        e.stopPropagation();
    });

    function toggleDropdown() {
        if (
            statusList.style.clipPath ===
            "polygon(0px 0px, 100% 0px, 100% 100%, 0px 100%)" ||
            statusList.style.clipPath === "polygon(0 0, 100% 0, 100% 100%, 0 100%)"
        ) {
            closeDropdown();
        } else {
            openDropdown();
        }
    }

    function openDropdown() {
        statusList.style.clipPath = "polygon(0 0, 100% 0, 100% 100%, 0 100%)";
        statusList.style.transition = "clip-path 0.3s ease-in-out";
        playIcon.style.transform = "rotate(270deg)";
        playIcon.style.transition = "transform 0.3s ease-in-out";
    }

    function closeDropdown() {
        statusList.style.clipPath = "polygon(0 0, 100% 0, 100% 0, 0 0)";
        statusList.style.transition = "clip-path 0.3s ease-in-out";
        playIcon.style.transform = "rotate(90deg)";
        playIcon.style.transition = "transform 0.3s ease-in-out";
    }
    const categoryBtn = document.querySelectorAll(".container__header__category button");
    categoryBtn.forEach((btn) => {
        btn.addEventListener("click", () => {
            categoryBtn.forEach((b) => b.classList.remove("active"));
            btn.classList.add("active");
        });
    });

    const dateBtn = document.querySelector(".container__header__category__date");
    const dateBtnIcon = dateBtn.querySelector("i");
    dateBtn.addEventListener("click", () => {
        dateBtnIcon.classList.toggle("rotated");
    })
});
