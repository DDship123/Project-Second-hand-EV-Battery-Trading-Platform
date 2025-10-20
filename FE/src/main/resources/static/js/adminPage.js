// adminPage.js

const permissionMenu = document.getElementById('permissionMenu');
const submenu = document.getElementById('submenu');
const dropdownArrow = permissionMenu.querySelector('.dropdown-arrow');
const submenuItems = document.querySelectorAll('.submenu-item');
const contentSections = document.querySelectorAll('.content-section');
const notification = document.getElementById('notification');

window.addEventListener('DOMContentLoaded', () => {
  submenu.classList.add('open');
  dropdownArrow.classList.add('open');
  permissionMenu.classList.add('active');

  if (window.location.pathname.includes('post-manage')) {
    submenuItems[0].classList.add('active');
  }else if (window.location.pathname.includes('transaction-manage')) {
    submenuItems[1].classList.add('active');
  }else if (window.location.pathname.includes('member-manage')) {
    submenuItems[2].classList.add('active');
  }else if (window.location.pathname.includes('comment-review-manage')) {
    submenuItems[3].classList.add('active');
  }else if (window.location.pathname.includes('fee-manage')) {
    submenuItems[4].classList.add('active');
  } else {
    // Default to first submenu item
    submenuItems[0].classList.add('active');
  }
});


function deactivateAllMenus() {
  document.querySelectorAll('.menu-item.active').forEach(el => el.classList.remove('active'));
  document.querySelectorAll('.submenu-item.active').forEach(el => el.classList.remove('active'));
  submenu.classList.remove('open');
  dropdownArrow.classList.remove('open');
}

// Toggle dropdown menu
permissionMenu.addEventListener('click', function () {
  const wasOpen = submenu.classList.contains('open');
  submenu.classList.toggle('open');
  dropdownArrow.classList.toggle('open');
  const nowOpen = submenu.classList.contains('open');

  if (nowOpen) {
    // If no active submenu item, select the first one (Post Management)
    if (!submenu.querySelector('.submenu-item.active') && !wasOpen) {
      deactivateAllMenus();
      const firstSub = submenuItems[0];
      firstSub.classList.add('active');
      permissionMenu.classList.add('active');
      submenu.classList.add('open');
      dropdownArrow.classList.add('open');

      // Show the content
      const sectionToShow = firstSub.getAttribute('data-submenu');
      contentSections.forEach(section => section.classList.remove('active'));
      const targetSection = document.querySelector(`[data-section="${sectionToShow}"]`);
      if (targetSection) {
        targetSection.classList.add('active');
      }
    }
  } else {
    // If closing, do not change active if a sub was selected, but since we're only highlighting selected, keep content
  }
});

// Handle submenu item clicks and switch content
submenuItems.forEach(item => {
  item.addEventListener('click', function (e) {
    e.stopPropagation();
    deactivateAllMenus();
    // this.classList.add('active');
    this.classList.remove('active');
    permissionMenu.classList.add('active');
    submenu.classList.add('open');
    dropdownArrow.classList.add('open');

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
    switch (item.textContent.trim()) {
      case 'Quản lí đăng bài':
        window.location.href = '/home/admin/post-manage';
        break;
      case 'Quản lí giao dịch':
        window.location.href = '/home/admin/transaction-manage';
        break;
      case 'Quản lí người dùng':
        window.location.href = '/home/admin/member-manage';
        break;
      case 'Quản lí bình luận':
        window.location.href = '/home/admin/comment-review-manage';
        break;
      case 'Cấu hình phí & Hoa hồng':
        window.location.href = '/home/admin/fee-manage';
        break;
      default:
        break;
    }
  });
});

// Set initial active menu based on active content
const activeSectionEl = document.querySelector('.content-section.active');
if (activeSectionEl) {
  const activeSection = activeSectionEl.getAttribute('data-section');
  const activeSubItem = document.querySelector(`.submenu-item[data-submenu="${activeSection}"]`);
  if (activeSubItem) {
    deactivateAllMenus();
    activeSubItem.classList.add('active');
    permissionMenu.classList.add('active');
    submenu.classList.add('open');
    dropdownArrow.classList.add('open');
  }
}

// Show notification
function showNotification(message) {
  notification.innerHTML = '<i class="fa-solid fa-check"></i> ' + message;
  notification.classList.add('show');
  setTimeout(() => {
    notification.classList.remove('show');
  }, 3000); // Notification disappears after 3 seconds
}

// Post Modal functionality
const postModal = document.getElementById('postDetailModal');
const postCloseBtn = postModal ? postModal.querySelector('.close') : null;
const detailLinks = document.querySelectorAll('.detail-link');

detailLinks.forEach(link => {
  link.addEventListener('click', function (e) {
    e.preventDefault();
    const row = this.closest('tr');
    const postId = row.getAttribute('data-post-id');

    // In the future, here you will connect to the API to fetch data for the specific postId.
    // Example:
    // async function fetchPostDetail(postId) {
    //   const response = await fetch(`/api/posts/${postId}`);
    //   const data = await response.json();
    //   return data; // Assuming data has { vehicle: {...}, seller: {...} }
    // }
    // const data = await fetchPostDetail(postId);

    // For now, using hardcoded data
    const data = postData[postId];

    if (data) {
      // Populate modal
      // Here is where you will set the values from the API response into each field.
      // For example, replace data.vehicle.image with apiResponse.vehicle.image, etc.

      document.getElementById('vehicleImage').src = data.vehicle.image; // Set from API response.vehicle.image
      document.getElementById('vehicleBrand').textContent = data.vehicle.brand; // Set from API response.vehicle.brand
      document.getElementById('vehicleModel').textContent = data.vehicle.model; // Set from API response.vehicle.model
      document.getElementById('vehicleRegisterYear').textContent = data.vehicle.registerYear; // Set from API response.vehicle.registerYear
      document.getElementById('vehicleMileage').textContent = data.vehicle.mileage.toLocaleString(); // Set from API response.vehicle.mileage
      document.getElementById('vehicleCondition').textContent = data.vehicle.condition; // Set from API response.vehicle.condition
      document.getElementById('vehicleBatteryCapacity').textContent = data.vehicle.batteryCapacity; // Set from API response.vehicle.batteryCapacity
      document.getElementById('vehicleOrigin').textContent = data.vehicle.origin; // Set from API response.vehicle.origin
      document.getElementById('vehiclePrice').textContent = data.vehicle.price.toLocaleString(); // Set from API response.vehicle.price
      document.getElementById('vehicleDescription').textContent = data.vehicle.description; // Set from API response.vehicle.description
      document.getElementById('sellerName').textContent = data.seller.name; // Set from API response.seller.name
      document.getElementById('sellerEmail').textContent = data.seller.email; // Set from API response.seller.email
      document.getElementById('sellerPhone').textContent = data.seller.phone; // Set from API response.seller.phone
      document.getElementById('sellerAddress').textContent = data.seller.address; // Set from API response.seller.address

      // Show modal
      postModal.style.display = 'block';
    }
  });
});

if (postCloseBtn) {
  postCloseBtn.addEventListener('click', function () {
    postModal.style.display = 'none';
  });
}

window.addEventListener('click', function (e) {
  if (e.target === postModal) {
    postModal.style.display = 'none';
  }
});

// Handle approve/reject in post modal (simulate action)
const modalApprove = document.getElementById('modalApprove');
if (modalApprove) {
  modalApprove.addEventListener('click', function () {
    showNotification('Bài đăng đã được duyệt!');
    postModal.style.display = 'none';
  });
}

const modalReject = document.getElementById('modalReject');
if (modalReject) {
  modalReject.addEventListener('click', function () {
    showNotification('Bài đăng đã bị từ chối!');
    postModal.style.display = 'none';
  });
}

// User Detail Modal functionality (used for both users and admins)
const userModal = document.getElementById('userDetailModal');
const userCloseBtn = userModal ? userModal.querySelector('.close') : null;
const userDetailButtons = document.querySelectorAll('.user-detail-btn');
const adminDetailButtons = document.querySelectorAll('.admin-detail-btn');

function openUserModal(data) {
  // Populate modal
  // Here is where you will set the values from the API response into each field.
  // For example, replace data.avatar with apiResponse.avatar, etc.

  document.getElementById('userAvatar').src = data.avatar; // Set from API response.avatar
  document.getElementById('userFullName').textContent = data.fullName; // Set from API response.fullName
  document.getElementById('userEmail').textContent = data.email; // Set from API response.email
  document.getElementById('userPhone').textContent = data.phone; // Set from API response.phone
  document.getElementById('userBirthDate').textContent = data.birthDate; // Set from API response.birthDate
  document.getElementById('userAddress').textContent = data.address; // Set from API response.address
  document.getElementById('userCode').textContent = data.code; // Set from API response.code
  document.getElementById('userReferrer').textContent = data.referrer; // Set from API response.referrer
  document.getElementById('userRegisterDate').textContent = data.registerDate; // Set from API response.registerDate
  document.getElementById('userStatus').textContent = data.status; // Set from API response.status

  // Show modal
  userModal.style.display = 'block';
}

userDetailButtons.forEach(button => {
  button.addEventListener('click', function (e) {
    const row = this.closest('tr');
    const userId = row.getAttribute('data-user-id');

    // In the future, here you will connect to the API to fetch data for the specific userId.
    // Example:
    // async function fetchUserDetail(userId) {
    //   const response = await fetch(`/api/users/${userId}`);
    //   const data = await response.json();
    //   return data; // Assuming data has { avatar, fullName, email, ... }
    // }
    // const data = await fetchUserDetail(userId);

    // For now, using hardcoded data
    const data = userData[userId];

    if (data) {
      openUserModal(data);
    }
  });
});

adminDetailButtons.forEach(button => {
  button.addEventListener('click', function (e) {
    const row = this.closest('tr');
    const userId = row.getAttribute('data-user-id');

    // Similar API fetch for admins
    // const data = await fetchAdminDetail(userId);

    const data = adminData[userId];

    if (data) {
      openUserModal(data);
    }
  });
});

if (userCloseBtn) {
  userCloseBtn.addEventListener('click', function () {
    userModal.style.display = 'none';
  });
}

window.addEventListener('click', function (e) {
  if (e.target === userModal) {
    userModal.style.display = 'none';
  }
});

// Handle close in user modal
const modalCloseUser = document.getElementById('modalCloseUser');
if (modalCloseUser) {
  modalCloseUser.addEventListener('click', function () {
    userModal.style.display = 'none';
  });
}

// Comment/Review Modal functionality
const commentModal = document.getElementById('commentDetailModal');
const commentCloseBtn = commentModal ? commentModal.querySelector('.close') : null;
const commentDetailButtons = document.querySelectorAll('.comment-detail-btn');
const reviewDetailButtons = document.querySelectorAll('.review-detail-btn');

function openCommentModal(data, isReview = false) {
  document.querySelector('#commentDetailModal h2').textContent = isReview ? 'Chi tiết đánh giá' : 'Chi tiết bình luận';
  document.getElementById('commentUser').textContent = data.user;
  document.getElementById('commentDate').textContent = data.date;
  document.getElementById('commentContent').textContent = data.content;
  document.getElementById('commentStatus').textContent = data.status;

  // Show or hide actions based on status
  const actions = commentModal.querySelector('.modal-actions');
  if (data.status === 'Đang đợi') {
    actions.style.display = 'flex';
  } else {
    actions.style.display = 'none';
  }

  commentModal.style.display = 'block';
}

commentDetailButtons.forEach(button => {
  button.addEventListener('click', function (e) {
    const row = this.closest('tr');
    const id = row.getAttribute('data-comment-id');
    const data = commentData[id];
    if (data) {
      openCommentModal(data);
    }
  });
});

reviewDetailButtons.forEach(button => {
  button.addEventListener('click', function (e) {
    const row = this.closest('tr');
    const id = row.getAttribute('data-review-id');
    const data = reviewData[id];
    if (data) {
      openCommentModal(data, true);
    }
  });
});

if (commentCloseBtn) {
  commentCloseBtn.addEventListener('click', function () {
    commentModal.style.display = 'none';
  });
}

window.addEventListener('click', function (e) {
  if (e.target === commentModal) {
    commentModal.style.display = 'none';
  }
});

// Handle approve/reject in comment modal
const modalCommentApprove = document.getElementById('modalCommentApprove');
if (modalCommentApprove) {
  modalCommentApprove.addEventListener('click', function () {
    showNotification('Đã duyệt!');
    commentModal.style.display = 'none';
    // TODO: update status via API
  });
}

const modalCommentReject = document.getElementById('modalCommentReject');
if (modalCommentReject) {
  modalCommentReject.addEventListener('click', function () {
    showNotification('Đã từ chối!');
    commentModal.style.display = 'none';
    // TODO: update status via API
  });
}

// Handle approve/reject buttons in tables
const commentApproveButtons = document.querySelectorAll('.comment-approve-btn, .review-approve-btn');
const commentRejectButtons = document.querySelectorAll('.comment-reject-btn, .review-reject-btn');

commentApproveButtons.forEach(button => {
  button.addEventListener('click', function () {
    showNotification('Đã duyệt!');
    // TODO: update status via API
  });
});

commentRejectButtons.forEach(button => {
  button.addEventListener('click', function () {
    showNotification('Đã từ chối!');
    // TODO: update status via API
  });
});

// Tab switching for user management
const tabButtons = document.querySelectorAll('[data-tab]');
const tabContents = document.querySelectorAll('.tab-content');

tabButtons.forEach(button => {
  button.addEventListener('click', function () {
    tabButtons.forEach(btn => btn.classList.remove('active'));
    this.classList.add('active');

    tabContents.forEach(content => content.classList.remove('active'));
    document.getElementById(this.getAttribute('data-tab') + 'Tab').classList.add('active');
  });
});

// Dropdown filter functionality
const referrerFilterIcon = document.getElementById('referrerFilterIcon');
const statusFilterIcon = document.getElementById('statusFilterIcon');
const dateFilterIcon = document.getElementById('dateFilterIcon');
const referrerDropdown = document.getElementById('referrerDropdown');
const statusDropdown = document.getElementById('statusDropdown');
const dateDropdown = document.getElementById('dateDropdown');
const searchInput = document.querySelector('#usersTab .search-box input');
const userRows = document.querySelectorAll('#userTable tbody tr');

let selectedReferrer = '';
let selectedStatus = '';
let fromDate = null;
let toDate = null;
let sortAsc = true;

function toggleDropdown(dropdown) {
  if (dropdown.style.display === 'block') {
    dropdown.style.display = 'none';
  } else {
    // Close other dropdowns
    document.querySelectorAll('.filter-dropdown').forEach(dd => dd.style.display = 'none');
    dropdown.style.display = 'block';
  }
}

if (referrerFilterIcon) referrerFilterIcon.addEventListener('click', () => toggleDropdown(referrerDropdown));
if (statusFilterIcon) statusFilterIcon.addEventListener('click', () => toggleDropdown(statusDropdown));
if (dateFilterIcon) dateFilterIcon.addEventListener('click', () => toggleDropdown(dateDropdown));

// Handle referrer selection
if (referrerDropdown) {
  referrerDropdown.querySelectorAll('.filter-option').forEach(option => {
    option.addEventListener('click', function () {
      selectedReferrer = this.getAttribute('data-value').toLowerCase();
      filterUsers();
      toggleDropdown(referrerDropdown);
    });
  });
}

// Handle status selection
if (statusDropdown) {
  statusDropdown.querySelectorAll('.filter-option').forEach(option => {
    option.addEventListener('click', function () {
      selectedStatus = this.getAttribute('data-value').toLowerCase();
      filterUsers();
      toggleDropdown(statusDropdown);
    });
  });
}

// Handle date sort selection
if (dateDropdown) {
  dateDropdown.querySelectorAll('.filter-option').forEach(option => {
    option.addEventListener('click', function () {
      sortAsc = this.getAttribute('data-sort') === 'asc';
      sortUsersByDate();
      toggleDropdown(dateDropdown);
    });
  });
}

// Handle custom date range
const applyDateRange = document.getElementById('applyDateRange');
if (applyDateRange) {
  applyDateRange.addEventListener('click', function () {
    fromDate = document.getElementById('fromDate').value;
    toDate = document.getElementById('toDate').value;
    filterUsers();
    toggleDropdown(dateDropdown);
  });
}

function filterUsers() {
  const search = searchInput.value.toLowerCase();

  userRows.forEach(row => {
    const name = row.querySelector('td:nth-child(2)').textContent.toLowerCase();
    const email = row.querySelector('td:nth-child(3)').textContent.toLowerCase();
    const ref = row.querySelector('td:nth-child(4)').textContent.toLowerCase();
    const stat = row.querySelector('.status-select').value.toLowerCase();
    const regDate = row.getAttribute('data-register-date');

    let show = (selectedReferrer === '' || ref.includes(selectedReferrer)) &&
      (selectedStatus === '' || stat === selectedStatus) &&
      (name.includes(search) || email.includes(search));

    if (fromDate && toDate) {
      const rowDate = new Date(regDate);
      show = show && rowDate >= new Date(fromDate) && rowDate <= new Date(toDate);
    }

    row.style.display = show ? '' : 'none';
  });
}

function sortUsersByDate() {
  const tbody = document.querySelector('#userTable tbody');
  const rows = Array.from(tbody.rows);

  rows.sort((a, b) => {
    const dateA = new Date(a.getAttribute('data-register-date'));
    const dateB = new Date(b.getAttribute('data-register-date'));
    return sortAsc ? dateA - dateB : dateB - dateA;
  });

  rows.forEach(row => tbody.appendChild(row));
}

if (searchInput) searchInput.addEventListener('input', filterUsers);

// Status select change and color coding
const statusSelects = document.querySelectorAll('.status-select');
statusSelects.forEach(select => {
  updateStatusColor(select);
  select.addEventListener('change', function () {
    updateStatusColor(this);
    showNotification('Trạng thái đã cập nhật!');
    // In real, call API to update status
  });
});

function updateStatusColor(select) {
  select.classList.remove('status-active', 'status-temp-locked', 'status-perm-locked');
  const value = select.value;
  if (value === 'Hoạt động') {
    select.classList.add('status-active');
  } else if (value === 'Tạm khóa') {
    select.classList.add('status-temp-locked');
  } else if (value === 'Cấm vĩnh viễn') {
    select.classList.add('status-perm-locked');
  }
}

// Close dropdowns when clicking outside
document.addEventListener('click', function (e) {
  if (!e.target.closest('.filter-icon') && !e.target.closest('.filter-dropdown')) {
    document.querySelectorAll('.filter-dropdown').forEach(dd => dd.style.display = 'none');
  }
});

// Admin actions
const adminLockButtons = document.querySelectorAll('#adminsTab .lock');
const adminDeleteButtons = document.querySelectorAll('#adminsTab .delete');

adminLockButtons.forEach(button => {
  button.addEventListener('click', function () {
    showNotification('Tài khoản đã bị khóa!');
    // In real, API call
  });
});

adminDeleteButtons.forEach(button => {
  button.addEventListener('click', function () {
    if (confirm('Xác nhận xóa tài khoản?')) {
      showNotification('Tài khoản đã bị xóa!');
      // In real, API call, remove row
    }
  });
});

// To fetch real filter options from API later
// Example for referrer:
// async function fetchReferrerOptions() {
//   const response = await fetch('/api/referrer-types');
//   const options = await response.json();
//   // Populate referrerDropdown with options
// }
// fetchReferrerOptions();

// Similar for status and dates