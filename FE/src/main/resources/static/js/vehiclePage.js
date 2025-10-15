window.addEventListener('load', function() {
    const  vehicles = document.querySelectorAll('.vehicle__item');
    vehicles.forEach(vehicle => {
        vehicle.addEventListener('click', function() {
            const postId = vehicle.querySelector("input[type='hidden']").value;
            console.log("Post ID:", postId);
        });
    });
});