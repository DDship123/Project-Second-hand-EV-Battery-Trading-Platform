// adminPage.js

const permissionMenu = document.getElementById('permissionMenu');
const submenu = document.getElementById('submenu');
const dropdownArrow = permissionMenu.querySelector('.dropdown-arrow');
const submenuItems = document.querySelectorAll('.submenu-item');
const contentSections = document.querySelectorAll('.content-section');
const notification = document.getElementById('notification');

// Hardcoded data for demonstration purposes.
// In the future, this will be replaced by fetching data from an API.
// Example:
// async function fetchPostData() {
//   const response = await fetch('/api/posts');
//   const data = await response.json();
//   return data; // Assuming data is an object with postId as keys
// }
// Then, use const postData = await fetchPostData();
const postData = {
  1: {
    vehicle: {
      image: 'https://via.placeholder.com/300x200?text=Vehicle+1',
      brand: 'Honda',
      model: 'Civic',
      registerYear: 2020,
      mileage: 15000,
      condition: 'Tốt',
      batteryCapacity: 50,
      origin: 'Nhật Bản',
      price: 500000000,
      description: 'Xe đẹp, chạy tốt, ít sử dụng.'
    },
    seller: {
      name: 'Nguyen Tien Linh',
      email: 'nguyentienlinh@gmail.com',
      phone: '0123456789',
      address: '123 Đường ABC, Quận 1, TP. HCM'
    }
  },
  2: {
    vehicle: {
      image: 'https://via.placeholder.com/300x200?text=Vehicle+2',
      brand: 'Toyota',
      model: 'Camry',
      registerYear: 2018,
      mileage: 30000,
      condition: 'Bình thường',
      batteryCapacity: 60,
      origin: 'Mỹ',
      price: 600000000,
      description: 'Xe gia đình, bảo dưỡng định kỳ.'
    },
    seller: {
      name: 'Nguyen Van An',
      email: 'nguyenvanae@gmail.com',
      phone: '0987654321',
      address: '456 Đường DEF, Quận 2, TP. HCM'
    }
  },
  3: {
    vehicle: {
      image: 'https://via.placeholder.com/300x200?text=Vehicle+3',
      brand: 'Ford',
      model: 'Mustang',
      registerYear: 2022,
      mileage: 5000,
      condition: 'Xuất sắc',
      batteryCapacity: 70,
      origin: 'Mỹ',
      price: 1200000000,
      description: 'Xe thể thao, động cơ mạnh mẽ.'
    },
    seller: {
      name: 'Tran Trung Tam',
      email: 'trungtamtran@gmail.com',
      phone: '0112233445',
      address: '789 Đường GHI, Quận 3, TP. HCM'
    }
  },
  4: {
    vehicle: {
      image: 'https://via.placeholder.com/300x200?text=Vehicle+4',
      brand: 'BMW',
      model: 'X5',
      registerYear: 2019,
      mileage: 25000,
      condition: 'Tốt',
      batteryCapacity: 80,
      origin: 'Đức',
      price: 1500000000,
      description: 'Xe SUV cao cấp, đầy đủ tiện nghi.'
    },
    seller: {
      name: 'Huan Hoa Hong',
      email: 'huanrose@gmail.com',
      phone: '0556677889',
      address: '101 Đường JKL, Quận 4, TP. HCM'
    }
  },
  5: {
    vehicle: {
      image: 'https://via.placeholder.com/300x200?text=Vehicle+5',
      brand: 'Mercedes-Benz',
      model: 'C-Class',
      registerYear: 2021,
      mileage: 10000,
      condition: 'Xuất sắc',
      batteryCapacity: 55,
      origin: 'Đức',
      price: 900000000,
      description: 'Xe sang trọng, nội thất da cao cấp.'
    },
    seller: {
      name: 'Tran Trung Tien',
      email: 'tienbjp@gmail.com',
      phone: '0334455667',
      address: '112 Đường MNO, Quận 5, TP. HCM'
    }
  },
  6: {
    vehicle: {
      image: 'https://via.placeholder.com/300x200?text=Vehicle+6',
      brand: 'Audi',
      model: 'A4',
      registerYear: 2017,
      mileage: 40000,
      condition: 'Bình thường',
      batteryCapacity: 65,
      origin: 'Đức',
      price: 700000000,
      description: 'Xe Đức, bền bỉ và tiết kiệm nhiên liệu.'
    },
    seller: {
      name: 'Do Giam Doc',
      email: 'giamdoc@gmail.com',
      phone: '0778899001',
      address: '131 Đường PQR, Quận 6, TP. HCM'
    }
  },
  7: {
    vehicle: {
      image: 'https://via.placeholder.com/300x200?text=Vehicle+7',
      brand: 'Volkswagen',
      model: 'Golf',
      registerYear: 2023,
      mileage: 2000,
      condition: 'Tốt',
      batteryCapacity: 45,
      origin: 'Đức',
      price: 800000000,
      description: 'Xe hatchback thể thao, dễ lái.'
    },
    seller: {
      name: 'Nguyen Gan Gui',
      email: 'gangui@gmail.com',
      phone: '0223344556',
      address: '415 Đường STU, Quận 7, TP. HCM'
    }
  },
  8: {
    vehicle: {
      image: 'https://via.placeholder.com/300x200?text=Vehicle+8',
      brand: 'Tesla',
      model: 'Model 3',
      registerYear: 2024,
      mileage: 1000,
      condition: 'Xuất sắc',
      batteryCapacity: 75,
      origin: 'Mỹ',
      price: 2000000000,
      description: 'Xe điện hiện đại, tự lái cấp độ 2.'
    },
    seller: {
      name: 'The Tinh Thon',
      email: 'tinhthon@gmail.com',
      phone: '0445566778',
      address: '161 Đường VWX, Quận 8, TP. HCM'
    }
  }
};

// Hardcoded user data for demonstration purposes.
// In the future, this will be replaced by fetching data from an API.
// Example:
// async function fetchUserData() {
//   const response = await response.json();
//   return data; // Assuming data is an object with userId as keys
// }
// Then, use const userData = await fetchUserData();
const userData = {
  1: {
    avatar: 'https://via.placeholder.com/200?text=User+A',
    fullName: 'Nguyen Van A',
    email: 'nguyenvana@gmail.com',
    phone: '0123456789',
    birthDate: 'January 1st, 1990',
    address: '123 ABC Street, Ho Chi Minh City',
    code: 'U001',
    referrer: 'Chưa đăng ký',
    registerDate: '06/10/2025',
    status: 'Hoạt động'
  },
  2: {
    avatar: 'https://via.placeholder.com/200?text=User+B',
    fullName: 'Tran Thi B',
    email: 'tranthib@gmail.com',
    phone: '0987654321',
    birthDate: 'February 2nd, 1992',
    address: '456 DEF Street, Hanoi',
    code: 'U002',
    referrer: 'Gói chuyên nghiệp',
    registerDate: '07/10/2025',
    status: 'Tạm khóa'
  },
  3: {
    avatar: 'https://via.placeholder.com/200?text=User+C',
    fullName: 'Le Van C',
    email: 'levanc@gmail.com',
    phone: '0112233445',
    birthDate: 'March 3rd, 1985',
    address: '789 GHI Street, Da Nang',
    code: 'U003',
    referrer: 'Gói doanh nghiệp',
    registerDate: '08/10/2025',
    status: 'Cấm vĩnh viễn'
  },
  4: {
    avatar: 'https://via.placeholder.com/200?text=User+D',
    fullName: 'Pham Thi D',
    email: 'phamthid@gmail.com',
    phone: '0556677889',
    birthDate: 'April 4th, 1995',
    address: '101 JKL Street, Can Tho',
    code: 'U004',
    referrer: 'Gói chuyên nghiệp',
    registerDate: '09/10/2025',
    status: 'Hoạt động'
  }
};

// Hardcoded admin data
const adminData = {
  a1: {
    avatar: 'https://via.placeholder.com/200?text=Admin+L',
    fullName: 'Nguyen Van L',
    email: 'nguyenvanl@gmail.com',
    phone: '0905123456',
    birthDate: 'May 5th, 1980',
    address: '222 Admin St, Ho Chi Minh City',
    code: 'A001',
    referrer: 'Admin',
    registerDate: '01/01/2025',
    status: 'Hoạt động'
  },
  a2: {
    avatar: 'https://via.placeholder.com/200?text=Admin+M',
    fullName: 'Tran Van M',
    email: 'tranvanm@gmail.com',
    phone: '0905234567',
    birthDate: 'June 6th, 1982',
    address: '333 Admin St, Hanoi',
    code: 'A002',
    referrer: 'Admin',
    registerDate: '02/01/2025',
    status: 'Hoạt động'
  },
  a3: {
    avatar: 'https://via.placeholder.com/200?text=Admin+N',
    fullName: 'Le Thi N',
    email: 'lethin@gmail.com',
    phone: '0905345678',
    birthDate: 'July 7th, 1984',
    address: '444 Admin St, Da Nang',
    code: 'A003',
    referrer: 'Admin',
    registerDate: '03/01/2025',
    status: 'Hoạt động'
  },
  a4: {
    avatar: 'https://via.placeholder.com/200?text=Admin+O',
    fullName: 'Pham Van O',
    email: 'phamvano@gmail.com',
    phone: '0905456789',
    birthDate: 'August 8th, 1986',
    address: '555 Admin St, Can Tho',
    code: 'A004',
    referrer: 'Admin',
    registerDate: '04/01/2025',
    status: 'Hoạt động'
  }
};

// Hardcoded comment data
const commentData = {
  1: {
    user: 'Nguyen Van A',
    date: '19/10/2025',
    content: 'Bình luận rất tốt về sản phẩm.',
    status: 'Đang đợi'
  },
  2: {
    user: 'Tran Thi B',
    date: '18/10/2025',
    content: 'Sản phẩm chất lượng cao.',
    status: 'Đã duyệt'
  },
  3: {
    user: 'Le Van C',
    date: '17/10/2025',
    content: 'Không hài lòng với dịch vụ.',
    status: 'Từ chối'
  }
};

// Hardcoded review data
const reviewData = {
  1: {
    user: 'Nguyen Van A',
    date: '19/10/2025',
    content: 'Đánh giá 5 sao, sản phẩm tuyệt vời.',
    status: 'Đang đợi'
  },
  2: {
    user: 'Tran Thi B',
    date: '18/10/2025',
    content: '4 sao, giao hàng nhanh.',
    status: 'Đã duyệt'
  },
  3: {
    user: 'Le Van C',
    date: '17/10/2025',
    content: '2 sao, sản phẩm lỗi.',
    status: 'Từ chối'
  }
};

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
    this.classList.add('active');
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