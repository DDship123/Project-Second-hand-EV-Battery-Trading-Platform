window.addEventListener("DOMContentLoaded", function () {
    const vehicleInfo = document.querySelector(".vehicle");
    const batteryInfo = document.querySelector(".battery");
    const categorySelect = document.querySelector("select[name='productType']");
    const form = document.querySelector(".registration__content");


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

    // Gọi ngay khi trang load để đảm bảo chỉ 1 phần active
    updateForm();

    // ===============================
    // Xử lý nút thêm ảnh
    // ===============================
    const uploadImageButtons = document.querySelectorAll(".registration__upload-button");
    const imageInputs = document.querySelectorAll(".registration__file-upload-form");
    uploadImageButtons.forEach((button, index) => {
        button.addEventListener("click", function (e) {
            e.preventDefault();
            imageInputs[index].classList.toggle("active");
        });
    });
});
