window.addEventListener('load', function() {
    console.log(window.location.href);
    let menuItems = document.querySelectorAll('.sideBar__menu li');
    toggleSidebar(menuItems);
    goToPage(menuItems);
})
function toggleSidebar(menuItems) {
    menuItems.forEach(item => {
        if(window.location.href.includes(item.id)){
            item.classList.add('sidebar--active');
        } else {
            item.classList.remove('sidebar--active');
        }
    });
}
function goToPage(menuItems) {
    menuItems.forEach(item => {
        item.addEventListener('click', function () {
            let page = item.id;
            console.log(page);
            window.location.href = `/account/${page}`;
        });
    });
}