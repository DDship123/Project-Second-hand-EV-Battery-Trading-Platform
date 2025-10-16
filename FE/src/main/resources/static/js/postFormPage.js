window.addEventListener("DOMContentLoaded", function () {
    const vehicleInfo = document.querySelector(".vehicle");
    const batteryInfo = document.querySelector(".battery");
    const categorySelect = document.querySelector(".registration__select");

    categorySelect.addEventListener("change", function () {
        if (categorySelect.value === "vehicle") {
            vehicleInfo.classList.add("active");
            batteryInfo.classList.remove("active");
        } else if (categorySelect.value === "battery") {
            batteryInfo.classList.add("active");
            vehicleInfo.classList.remove("active");
        }
    });
});
