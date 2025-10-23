window.addEventListener('load', function() {
    const plans = document.querySelectorAll('td.plan');
    plans.forEach(function(plan) {
        const planText = plan.textContent.trim();
        plan.innerHTML = planText.replace(/\d/g, "")
    });

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