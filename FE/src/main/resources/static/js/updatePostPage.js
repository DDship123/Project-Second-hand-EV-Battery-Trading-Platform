window.addEventListener('DOMContentLoaded', () => {
    const uploadImageButtons = document.querySelectorAll(".registration__upload-button");
    const imageInputs = document.querySelectorAll(".registration__file-upload-form");
    uploadImageButtons.forEach((button, index) => {
        button.addEventListener("click", function (e) {
            e.preventDefault();
            imageInputs[index].classList.toggle("active");
        });
    });
});