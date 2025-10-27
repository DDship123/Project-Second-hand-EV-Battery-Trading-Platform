window.addEventListener('load', function() {
    const  vehicles = document.querySelectorAll('.vehicle__item');
    vehicles.forEach(vehicle => {
        vehicle.addEventListener('click', function() {
            const postId = vehicle.querySelector("input[type='hidden']").value;
            window.location.href = `/home/product/detail/${postId}`;
        });
    });
    const urlParams = new URLSearchParams(window.location.search);
    const successMessage = urlParams.get('successMessage');
    if (successMessage) {
        alert(successMessage);
    }
    const errorMessage = urlParams.get('errorMessage');
    if (errorMessage) {
        alert(errorMessage);
    }
});