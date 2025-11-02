window.addEventListener('DOMContentLoaded', function() {
    const paymentButton = document.querySelector(".qr-code-wrapper h1");
    if (paymentButton !== null) {
        paymentButton.addEventListener("click", function() {
            const amount = this.getAttribute("data-amount");
            const id = this.getAttribute("data-transaction-id");
            window.location.href = `/banking/create/${id}/${amount}`;
        });
    }
    const deliveryButton = document.querySelector(".action-section button");
    deliveryButton.addEventListener("click", function(e) {
        let input = document.getElementById("contractImage")
        if (input.files.length === 0) {
            alert("Vui lòng tải lên hình ảnh hợp đồng .");
            e.preventDefault();
            return;
        }
        // if (confirm("Bạn có chắc chắn đơn hàng này đã được giao không?")) {
        //     const transactionId = this.getAttribute("data-transaction-id");
        //     window.location.href = '/home/buyTransaction/update-status/' + transactionId+'/DELIVERED';
        // }
    });
});