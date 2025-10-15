window.addEventListener('load', function() {
    const batteries = document.querySelectorAll('.battery__item');
    batteries.forEach(battery => {
        battery.addEventListener('click', function() {
            const postId = battery.querySelector("input[type= 'hidden']").value;
            window.location.href = `/home/product/detail/${postId}`;
        });
    });
});