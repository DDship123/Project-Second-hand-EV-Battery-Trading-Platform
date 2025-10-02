window.addEventListener('load', function() {
    const goBackButton = document.querySelector(".back");
    goBackButton.addEventListener('click', function() {
            // window.location.href = "http://localhost:8888/home";
        window.location.href = "/home";
    });
});