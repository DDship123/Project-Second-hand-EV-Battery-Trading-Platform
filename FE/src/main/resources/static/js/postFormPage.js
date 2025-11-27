window.addEventListener("DOMContentLoaded", function () {
    const vehicleInfo = document.querySelector(".vehicle");
    const batteryInfo = document.querySelector(".battery");
    let categorySelect = document.querySelector("select[name='productType']");
    if (categorySelect === null) {
        categorySelect = document.querySelector(".product__type");
    }
    const form = document.querySelector(".registration__content");

    // ===============================
    // Xử lý nút thêm ảnh
    // ===============================
    const uploadImageButtons = document.querySelectorAll(".registration__upload-button");
    const imageInputs = document.querySelectorAll(".registration__file-upload-form");
    uploadImageButtons.forEach((button, index) => {
        button.addEventListener("click", function (e) {
            e.preventDefault();
            e.stopPropagation();

            // Explicitly prevent form submission
            const form = button.closest('form');
            if (form) {
                form.addEventListener('submit', function(event) {
                    event.preventDefault();
                    return false;
                }, { once: true });
            }

            imageInputs[index].classList.toggle("active");
            return false;
        });
    });

    function updateForm() {
        let actionUrl = form.getAttribute("action");

        if (categorySelect.value === "VEHICLE") {
            // Hiển thị xe
            vehicleInfo.classList.add("active");
            batteryInfo.classList.remove("active");

            // Enable phần xe
            vehicleInfo.querySelectorAll("input, select, textarea").forEach(input => {
                input.removeAttribute("disabled");
                input.setAttribute("required", "required");
            });

            // Disable phần pin
            batteryInfo.querySelectorAll("input, select, textarea").forEach(input => {
                input.setAttribute("disabled", "disabled");
                input.removeAttribute("required");
            });

            // Cập nhật action
            if (actionUrl.includes("battery")) {
                form.setAttribute("action", actionUrl.replace("battery", "vehicle"));
            }

        } else if (categorySelect.value === "BATTERY") {
            // Hiển thị pin
            batteryInfo.classList.add("active");
            vehicleInfo.classList.remove("active");

            // Enable phần pin
            batteryInfo.querySelectorAll("input, select, textarea").forEach(input => {
                input.removeAttribute("disabled");
                input.setAttribute("required", "required");
            });

            // Disable phần xe
            vehicleInfo.querySelectorAll("input, select, textarea").forEach(input => {
                input.setAttribute("disabled", "disabled");
                input.removeAttribute("required");
            });

            // Cập nhật action
            if (actionUrl.includes("vehicle")) {
                form.setAttribute("action", actionUrl.replace("vehicle", "battery"));
            }
        }
    }

    // Khi chọn lại loại sản phẩm
    categorySelect.addEventListener("change", updateForm);

    // ✅ Gọi ngay khi trang load để đảm bảo chỉ 1 phần active
    updateForm();

});
