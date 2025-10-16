window.addEventListener("DOMContentLoaded", function () {
    const vehicleInfo = document.querySelector(".vehicle");
    const batteryInfo = document.querySelector(".battery");
    const categorySelect = document.querySelector(".registration__select");

    categorySelect.addEventListener("change", function () {
        if (categorySelect.value === "VEHICLE") {
            vehicleInfo.classList.add("active");
            batteryInfo.classList.remove("active");
        } else if (categorySelect.value === "BATTERY") {
            batteryInfo.classList.add("active");
            vehicleInfo.classList.remove("active");
        }
    });

    const uploadImageButtons = document.querySelectorAll(".registration__upload-button");
    const imageInputs = document.querySelectorAll(".registration__file-upload-form");
    uploadImageButtons.forEach((button, index) => {
        button.addEventListener("click", function (e) {
            e.preventDefault();
            imageInputs[index].classList.toggle("active");
        });
    });

    // const form = document.querySelector(".registration__content")
    // form.addEventListener("submit", function (e) {
    //     if (imageInputs[0].files.length === 0) {
    //         alert("Please upload at least one image.");
    //         e.preventDefault();
    //     }
    //     if (imageInputs[1].files.length > 4) {
    //         alert("You can upload a maximum of 4 images.");
    //         e.preventDefault();
    //     }
    // });
});
