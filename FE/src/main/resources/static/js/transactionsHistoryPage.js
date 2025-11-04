// Chức năng dropdown cho trạng thái giao dịch
document.addEventListener("DOMContentLoaded", function () {
    const statusTitle = document.querySelector(
        ".container__header__category__status__title"
    );
    const statusList = document.querySelector(
        ".container__header__category__status__List"
    );
    const statusSpan = statusTitle.querySelector("span");
    const statusListItems = statusList.querySelectorAll("li");
    const playIcon = statusTitle.querySelector("i");

    // Bật/tắt dropdown khi click vào tiêu đề trạng thái
    statusTitle.addEventListener("click", function (e) {
        e.stopPropagation();
        toggleDropdown();
    });

    // Xử lý việc chọn các mục trong danh sách
    statusListItems.forEach(function (item) {
        item.addEventListener("click", function (e) {
            e.stopPropagation();

            // Cập nhật text của span với text của mục được chọn
            // statusSpan.textContent = this.textContent;
            let currentUrl = new URL(window.location.href);
            if (this.innerText === "Tất cả") {
                currentUrl.searchParams.set('status', 'C-C-ALL');
            }else if (this.innerText === "Giao dịch thành công") {
                currentUrl.searchParams.set('status', 'COMPLETED');
            }else {
                currentUrl.searchParams.set('status', 'CANCELLED');
            }
            // Đóng dropdown sau khi chọn
            closeDropdown();

            window.location.href = currentUrl.toString();
        });
    });

    // Đóng dropdown khi click bên ngoài
    document.addEventListener("click", function () {
        closeDropdown();
    });

    // Ngăn dropdown đóng khi click bên trong danh sách
    statusList.addEventListener("click", function (e) {
        e.stopPropagation();
    });

    function toggleDropdown() {
        if (
            statusList.style.clipPath ===
            "polygon(0px 0px, 100% 0px, 100% 100%, 0px 100%)" ||
            statusList.style.clipPath === "polygon(0 0, 100% 0, 100% 100%, 0 100%)"
        ) {
            closeDropdown();
        } else {
            openDropdown();
        }
    }

    function openDropdown() {
        statusList.style.clipPath = "polygon(0 0, 100% 0, 100% 100%, 0 100%)";
        statusList.style.transition = "clip-path 0.3s ease-in-out";
        playIcon.style.transform = "rotate(270deg)";
        playIcon.style.transition = "transform 0.3s ease-in-out";
    }

    function closeDropdown() {
        statusList.style.clipPath = "polygon(0 0, 100% 0, 100% 0, 0 0)";
        statusList.style.transition = "clip-path 0.3s ease-in-out";
        playIcon.style.transform = "rotate(90deg)";
        playIcon.style.transition = "transform 0.3s ease-in-out";
    }

    // URL-based active state logic
    const currentPath = window.location.pathname;
    const categoryBtns = document.querySelectorAll(".container__header__category button");

    // Find specific buttons by their href attributes
    let buyButton = null;
    let sellButton = null;

    categoryBtns.forEach(btn => {
        const link = btn.querySelector('a');
        if (link) {
            const href = link.getAttribute('href');
            if (href && href.includes('/transactionsHistory')) {
                if (href.includes('/seller')) {
                    sellButton = btn;
                } else {
                    buyButton = btn;
                }
            }
        }
    });

    // Remove all active classes first
    categoryBtns.forEach(btn => btn.classList.remove("active"));

    // Add active class based on URL
    if (currentPath.includes('/seller') && sellButton) {
        sellButton.classList.add("active");
    } else if (buyButton) {
        buyButton.classList.add("active");
    }

    // Handle manual button clicks
    categoryBtns.forEach((btn) => {
        btn.addEventListener("click", () => {
            // Don't prevent navigation, just update visual state
            categoryBtns.forEach((b) => b.classList.remove("active"));
            btn.classList.add("active");
        });
    });

    const dateBtn = document.querySelector(".container__header__category__date");
    const dateBtnIcon = dateBtn.querySelector("i");
    dateBtn.addEventListener("click", () => {
        dateBtnIcon.classList.toggle("rotated");
    })



    // Check current URL to set active button
    const buyButtonReview = document.querySelector('button a[href="/home/transactionsHistory"]')?.parentElement;
    const sellButtonReview = document.querySelector('button a[href="/home/transactionsHistory/seller"]')?.parentElement;

    if (buyButton && sellButton) {
        if (currentPath.includes('/seller')) {
            sellButtonReview.classList.add('active');
            buyButtonReview.classList.remove('active');
        } else {
            buyButtonReview.classList.add('active');
            sellButtonReview.classList.remove('active');
        }
    }

    // Rating Form Functionality
    const ratingForm = document.querySelector('.rating-form');
    const ratingButtons = document.querySelectorAll('.container__body__item__rating__btn');
    const closeFormBtn = document.querySelector('.rating-form__close');
    const transactionIdInput = document.querySelector('.rating-form__transaction-id');
    const sellerIdInput = document.querySelector('.rating-form__seller-id');
    const submitBtn = document.querySelector('.rating-form__submit-btn');

    // Show rating form when rating button is clicked
    ratingButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            const transactionId = this.getAttribute('data-transaction-id');
            const sellerId = this.getAttribute('data-seller-id');
            transactionIdInput.value = transactionId;
            sellerIdInput.value = sellerId;
            showRatingForm();
        });
    });

    // Close form when close button is clicked
    if (closeFormBtn) {
        closeFormBtn.addEventListener('click', function(e) {
            e.preventDefault();
            hideRatingForm();
        });
    }

    // Close form when clicking outside the form content
    if (ratingForm) {
        ratingForm.addEventListener('click', function(e) {
            if (e.target === ratingForm) {
                hideRatingForm();
            }
        });
    }

    // Handle form submission
    if (ratingForm) {
        ratingForm.addEventListener('submit', function(e) {
            // Add loading state to submit button
            submitBtn.classList.add('loading');
            submitBtn.disabled = true;

            // The form will submit normally to your Spring Boot controller
            // You can add additional client-side validation here if needed
        });
    }

    // Prevent form content clicks from closing the modal
    const formContent = document.querySelector('.rating-form__content');
    if (formContent) {
        formContent.addEventListener('click', function(e) {
            e.stopPropagation();
        });
    }

    // Form utility functions
    function showRatingForm() {
        if (ratingForm) {
            ratingForm.style.display = 'flex';
            // Trigger reflow to ensure display change takes effect
            ratingForm.offsetHeight;
            ratingForm.classList.add('show');
            document.body.style.overflow = 'hidden';
        }
    }

    function hideRatingForm() {
        if (ratingForm) {
            // ratingForm.classList.remove('show');
            // document.body.style.overflow = '';
            //
            // // Hide form after transition completes
            // setTimeout(() => {
            //     ratingForm.style.display = 'none';
            //     resetForm();
            // }, 300);
            ratingForm.classList.remove('show');
            document.body.style.overflow = '';
            ratingForm.style.display = 'none';
            resetForm();
        }
    }

    function resetForm() {
        if (ratingForm) {
            const textarea = ratingForm.querySelector('.rating-form__textarea');
            const ratingInput = ratingForm.querySelector('.rating-form__rating-input');

            if (textarea) textarea.value = '';
            if (ratingInput) ratingInput.value = '';
            if (transactionIdInput) transactionIdInput.value = '';

            // Reset submit button state
            if (submitBtn) {
                submitBtn.classList.remove('loading');
                submitBtn.disabled = false;
            }
        }
    }

    // Handle Escape key to close form
    document.addEventListener('keydown', function(e) {
        if (e.key === 'Escape' && ratingForm && ratingForm.classList.contains('show')) {
            hideRatingForm();
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
});