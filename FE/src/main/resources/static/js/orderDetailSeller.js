window.addEventListener('load', function() {
    const actionButtons = document.querySelectorAll('.action-buttons button');
    console.log(actionButtons);
    actionButtons[0].addEventListener('click', function() {
        if (confirm('Bạn có chắc chắn muốn chấp nhận đơn hàng này không?')) {
            const transactionId = this.getAttribute('data-transaction-id');
            window.location.href = '/home/transaction/update-status/' + transactionId+'/ACCEPTED';
        }
    });

    actionButtons[1].addEventListener('click', function() {
        if (confirm('Bạn có chắc chắn muốn từ chối đơn hàng này không?')) {
            const transactionId = this.getAttribute('data-transaction-id');
            window.location.href = '/home/transaction/update-status/' + transactionId+'/CANCELLED';
        }
    });
});