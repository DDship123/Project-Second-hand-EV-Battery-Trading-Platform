// ========================================
// SỰ KIỆN DOM CONTENT LOADED
// ========================================
document.addEventListener("DOMContentLoaded", function () {
    // ========================================
    // CHỨC NĂNG DROPDOWN
    // ========================================

    // Các phần tử Dropdown Danh mục
    const categoryDropdownSelected =
        document.getElementById("categoryDropdown");
    const categoryDropdownOptions =
        document.getElementById("categoryOptions");
    const categorySelectedText =
        categoryDropdownSelected.querySelector(".selected-text");
    const categoryOptions =
        categoryDropdownOptions.querySelectorAll(".dropdown-option");

    // Các phần tử Dropdown Vị trí
    const locationDropdownSelected =
        document.getElementById("locationDropdown");
    const locationDropdownOptions =
        document.getElementById("locationOptions");
    const locationSelectedText =
        locationDropdownSelected.querySelector(".selected-text");
    const locationOptions =
        locationDropdownOptions.querySelectorAll(".dropdown-option");

    /**
     * Thiết lập chức năng dropdown
     * @param {Element} dropdownSelected - Phần tử kích hoạt dropdown
     * @param {Element} dropdownOptions - Container chứa các tùy chọn dropdown
     * @param {Element} selectedText - Phần tử hiển thị văn bản được chọn
     * @param {NodeList} options - Các phần tử tùy chọn dropdown
     */
    function setupDropdown(
        dropdownSelected,
        dropdownOptions,
        selectedText,
        options
    ) {
        // Bật/tắt dropdown khi click
        dropdownSelected.addEventListener("click", function (e) {
            e.stopPropagation();

            // Đóng các dropdown khác đang mở
            document
                .querySelectorAll(".dropdown-selected.active")
                .forEach((dropdown) => {
                    if (dropdown !== dropdownSelected) {
                        dropdown.classList.remove("active");
                        dropdown.nextElementSibling.classList.remove("show");
                    }
                });

            // Bật/tắt dropdown hiện tại
            dropdownSelected.classList.toggle("active");
            dropdownOptions.classList.toggle("show");
        });

        // Xử lý việc chọn tùy chọn
        options.forEach((option) => {
            option.addEventListener("click", function (e) {
                e.stopPropagation();

                // Cập nhật trạng thái lựa chọn
                options.forEach((opt) => opt.classList.remove("selected"));
                this.classList.add("selected");

                // Cập nhật văn bản hiển thị
                selectedText.textContent = this.textContent;

                // Đóng dropdown
                dropdownSelected.classList.remove("active");
                dropdownOptions.classList.remove("show");

                // Lưu trữ giá trị được chọn
                dropdownSelected.dataset.value = this.dataset.value;

                // Tùy chọn: Ghi log lựa chọn để debug
                console.log(
                    "Selected:",
                    this.dataset.value,
                    ":",
                    this.textContent
                );
            });
        });
    }

    // Khởi tạo cả hai dropdown
    setupDropdown(
        categoryDropdownSelected,
        categoryDropdownOptions,
        categorySelectedText,
        categoryOptions
    );

    setupDropdown(
        locationDropdownSelected,
        locationDropdownOptions,
        locationSelectedText,
        locationOptions
    );

    // Đóng dropdown khi click bên ngoài
    document.addEventListener("click", function () {
        document
            .querySelectorAll(".dropdown-selected.active")
            .forEach((dropdown) => {
                dropdown.classList.remove("active");
                dropdown.nextElementSibling.classList.remove("show");
            });
    });

    // ========================================
    // CHỨC NĂNG BĂT/TẮT PHẦN QUY TẮC
    // ========================================

    // Lấy các phần tử cho việc bật/tắt quy tắc
    const aboutTitleIcon = document.querySelector(".about__title i");
    const rulesSection = document.querySelectorAll(".rules")[1];

    // Thêm event listener cho chức năng bật/tắt
    if (aboutTitleIcon && rulesSection) {
        aboutTitleIcon.addEventListener("click", function () {
            // Bật/tắt các class cho animation
            rulesSection.classList.toggle("rules--appear");
            aboutTitleIcon.classList.toggle("playIcon--active");
        });
    }
});