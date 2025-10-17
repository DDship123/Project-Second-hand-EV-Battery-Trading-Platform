window.addEventListener("DOMContentLoaded", function () {
    const vehicleInfo = document.querySelector(".vehicle");
    const batteryInfo = document.querySelector(".battery");
    const categorySelect = document.querySelector(".registration__select");
    const form = document.querySelector(".registration__content");

    categorySelect.addEventListener("change", function () {
        let actionUrl = form.getAttribute("action");
        if (categorySelect.value === "VEHICLE") {
            vehicleInfo.classList.add("active");
            batteryInfo.classList.remove("active");

            const inputs = vehicleInfo.querySelectorAll("input, select, textarea");
            console.log(inputs);
            inputs.forEach(input => {
                input.setAttribute("required", "required");
                input.removeAttribute("disabled");
            });
            const batteryInputs = batteryInfo.querySelectorAll("input, select, textarea");
            console.log(batteryInputs);
            batteryInputs.forEach(input => {
                input.removeAttribute("required");
                input.setAttribute("disabled", "disabled");
            });

            if (actionUrl.includes("battery")) {
                actionUrl = actionUrl.replace("battery", "vehicle");
                form.setAttribute("action", actionUrl);
            }
        } else if (categorySelect.value === "BATTERY") {
            batteryInfo.classList.add("active");
            vehicleInfo.classList.remove("active");

            const inputs = batteryInfo.querySelectorAll("input, select, textarea");
            console.log(inputs);
            inputs.forEach(input => {
                input.setAttribute("required", "required");
                input.removeAttribute("disabled");
            });
            const vehicleInputs = vehicleInfo.querySelectorAll("input, select, textarea");
            console.log(vehicleInputs);
            vehicleInputs.forEach(input => {

                input.removeAttribute("required");
                input.setAttribute("disabled", "disabled");
            });

            if (actionUrl.includes("vehicle")) {
                actionUrl = actionUrl.replace("vehicle", "battery");
                form.setAttribute("action", actionUrl);
            }
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

});
