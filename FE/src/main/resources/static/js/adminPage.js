// adminPage.js

const permissionMenu = document.getElementById('permissionMenu');
const submenu = document.getElementById('submenu');
const dropdownArrow = permissionMenu.querySelector('.dropdown-arrow');
const submenuItems = document.querySelectorAll('.submenu-item');
const contentSections = document.querySelectorAll('.content-section');

// Toggle dropdown menu
permissionMenu.addEventListener('click', function() {
  submenu.classList.toggle('open');
  dropdownArrow.classList.toggle('open');
  permissionMenu.classList.toggle('active');
});

// Handle submenu item clicks and switch content
submenuItems.forEach(item => {
  item.addEventListener('click', function(e) {
    e.stopPropagation();

    // Remove active class from all items
    submenuItems.forEach(i => i.classList.remove('active'));

    // Add active class to clicked item
    this.classList.add('active');

    // Get the section to show
    const sectionToShow = this.getAttribute('data-submenu');

    // Hide all content sections
    contentSections.forEach(section => {
      section.classList.remove('active');
    });

    // Show the selected content section
    const targetSection = document.querySelector(`[data-section="${sectionToShow}"]`);
    if (targetSection) {
      targetSection.classList.add('active');
    }
  });
});