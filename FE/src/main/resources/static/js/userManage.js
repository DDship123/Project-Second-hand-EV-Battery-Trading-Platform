window.addEventListener('load', function() {

    const statusSelects = document.querySelectorAll('tr .status-select');
    statusSelects.forEach(function(select) {
        select.addEventListener('change', function() {
            const userId = select.getAttribute('data-user-id');
            console.log(this.value);
            switch (this.value) {
                case 'Hoạt động':
                    window.location.href = `/home/admin/member-manage/update-status?memberId=${userId}&status=ACTIVE`;
                    break;
                case 'Tạm khóa':
                    window.location.href = `/home/admin/member-manage/update-status?memberId=${userId}&status=SUSPENDED`;
                    break;
                case 'Cấm vĩnh viễn':
                    window.location.href = `/home/admin/member-manage/update-status?memberId=${userId}&status=BANNED`;
                    break;
            }
        });
    });

    let postDetailsBtns = document.querySelectorAll('td .action-btn-small');

    postDetailsBtns.forEach(function(btn) {
        btn.addEventListener('click', function() {
            const memberId = btn.getAttribute('member-id');
            window.location.href = `/home/admin/member-manage/detail/${memberId}`;
        });
    });

    const userDetailModal = document.getElementById('userDetailModal');
    const closeUserDetailModalBtn = userDetailModal.querySelector('.close');
    const modalCloseUser = document.getElementById('modalCloseUser')

    closeUserDetailModalBtn.addEventListener('click', function() {
        userDetailModal.style.display = 'none';
    });

    modalCloseUser.addEventListener('click', function() {
        userDetailModal.style.display = 'none';
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