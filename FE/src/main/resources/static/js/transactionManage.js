window.addEventListener('load', function() {
    const listActionBtns = document.querySelectorAll('.action-buttons');
    listActionBtns.forEach(function(actionBtns) {
        const btns = actionBtns.querySelectorAll('button');
        if (btns.length > 1) {
            btns.forEach(function(btn) {
                if (btn.innerText === 'Chi tiết') {
                    btn.addEventListener('click', function() {
                        const transactionId = this.getAttribute('data-transaction-id');
                        window.location.href = `/home/order/sell/detail/${transactionId}`;
                    });
                }else if (btn.innerText === 'Duyệt') {
                    btn.addEventListener('click', function() {
                        const transactionId = this.getAttribute('data-transaction-id');
                        if (confirm('Bạn có chắc chắn muốn duyệt giao dịch này không?')) {
                            window.location.href = `/home/admin/transaction-manage/update-status?transactionId=${transactionId}&status=COMPLETED`;
                        }
                    })
                }else{
                    btn.addEventListener('click', function() {
                        const transactionId = this.getAttribute('data-transaction-id');
                        if (confirm('Bạn có chắc chắn muốn hủy giao dịch này không?')) {
                            window.location.href = `/home/admin/transaction-manage/update-status?transactionId=${transactionId}&status=CANCELLED`;
                        }
                    })
                }
            });
        }else {
            const btn = btns[0];
            btn.addEventListener('click', function() {
                const transactionId = this.getAttribute('data-transaction-id');
                window.location.href = `/home/order/detail/${transactionId}`;
            });
        }
    })
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