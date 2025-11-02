window.addEventListener("load",function (){
    const packageElements = document.querySelectorAll('.packages__card');
    packageElements.forEach(function(card) {
        const btn = card.querySelector('button');
        if (btn) {
            if (btn.innerText !== 'Miễn Phí'){
                btn.addEventListener('click', function() {
                    const planId = btn.getAttribute('data-plan-id');
                    const memberId = btn.getAttribute('data-member-id');
                    const price = btn.getAttribute('data-price');
                    let windowUrl = new URL(window.location.href);
                    windowUrl.searchParams.set('planId', planId);
                    windowUrl.searchParams.set('price', price);
                    window.location.href = windowUrl.toString().replace("home","banking/register");
                });
            }
        }
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
})