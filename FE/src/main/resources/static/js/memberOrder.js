
// memberOrder.js

// Main tab switching
const mainTabButtons = document.querySelectorAll('[data-main-tab]');
const mainTabContents = document.querySelectorAll('.main-tab-content');
const searchInput = document.querySelector('.search-box input');

mainTabButtons.forEach(button => {
  button.addEventListener('click', function () {
    mainTabButtons.forEach(btn => btn.classList.remove('active'));
    this.classList.add('active');

    mainTabContents.forEach(content => content.classList.remove('active'));
    document.getElementById(this.getAttribute('data-main-tab') + 'Tab').classList.add('active');

    // Reset search when switching main tabs
    searchInput.value = '';
    filterOrders();
  });
});

// Sub tab switching for each main tab
const subSections = document.querySelectorAll('.main-tab-content');

subSections.forEach(section => {
  const subTabSelect = section.querySelector('.tab-select');
  const subTabContents = section.querySelectorAll('.sub-tab-content');

  subTabSelect.addEventListener('change', function () {
    const subTab = this.value;

    subTabContents.forEach(content => content.classList.remove('active'));
    const mainTabId = section.id.replace('Tab', '');
    const targetId = mainTabId + subTab.charAt(0).toUpperCase() + subTab.slice(1) + 'SubTab';
    document.getElementById(targetId).classList.add('active');

    // TODO: fetch orders for this tab from API
    // Example:
    // async function fetchOrders(tabType, subTab) {
    //   const response = await fetch(`/api/orders?type=${tabType}&status=${subTab}`);
    //   const data = await response.json();
    //   // Bind data to the list
    //   renderOrders(data, targetId);
    // }
    // fetchOrders(mainTabId.toLowerCase(), subTab);

    filterOrders();
  });
});

// Search functionality (applies to current active tab and sub-tab)
searchInput.addEventListener('input', filterOrders);

function filterOrders() {
  const search = searchInput.value.toLowerCase();
  const activeMainTab = document.querySelector('.main-tab-content.active');
  const activeSubTab = activeMainTab.querySelector('.sub-tab-content.active');
  const cards = activeSubTab.querySelectorAll('.order-management__card');

  cards.forEach(card => {
    const title = card.querySelector('.order-management__card-title').textContent.toLowerCase();
    const id = card.querySelector('.order-management__card-id').textContent.toLowerCase();
    card.style.display = (title.includes(search) || id.includes(search)) ? '' : 'none';
  });
}

// Function to render orders (for data-binding, hardcoded for demo)
function renderOrders(data, targetId) {
  // TODO: Implement dynamic rendering from API data
  // For example:
  // const list = document.querySelector(`#${targetId} .order-management__list`);
  // list.innerHTML = '';
  // data.forEach(order => {
  //   const card = document.createElement('a');
  //   card.href = `orderDetail.html?id=${order.id}`;
  //   card.classList.add('order-management__card');
  //   // Build card HTML based on order data
  //   // Set status class and text based on order.status
  //   // e.g., if (order.status === 'pending') { statusText = 'Pending Approval'; statusClass = '--pending'; }
  //   list.appendChild(card);
  // });
}