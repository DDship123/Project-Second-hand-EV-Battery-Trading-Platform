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

    const filterTabs = document.querySelector(".filter-tabs");
    if (filterTabs) {
        const tabBtn = filterTabs.querySelectorAll("button");
        const windowUrl = new URL(window.location.href);
        const currentStatus = windowUrl.searchParams.get("status");
        if (currentStatus !== null) {
            tabBtn.forEach(function(btn) {
                if (currentStatus === "ALL" && btn.innerText === "Đợi duyệt"){
                    btn.classList.add("active");
                }else if (currentStatus === "COMPLETED" && btn.innerText === "Hoàn thành"){
                    btn.classList.add("active");
                }else if (currentStatus === "CANCELLED" && btn.innerText === "Thất bại"){
                    btn.classList.add("active");
                }else {
                    btn.classList.remove("active");
                }
            });
        }

        tabBtn.forEach(function(btn) {
            btn.addEventListener("click", function() {
                if (windowUrl.searchParams.has("successMessage")){
                    windowUrl.searchParams.delete("successMessage");
                }else if (windowUrl.searchParams.has("errorMessage")){
                    windowUrl.searchParams.delete("errorMessage");
                }

                if (btn.innerText === "Đợi duyệt"){
                    windowUrl.searchParams.set("status", "ALL");
                    window.location.href = windowUrl.toString();
                }else if (btn.innerText === "Hoàn thành"){
                    windowUrl.searchParams.set("status", "COMPLETED");
                    window.location.href = windowUrl.toString();
                }else if (btn.innerText === "Thất bại"){
                    windowUrl.searchParams.set("status", "CANCELLED");
                    window.location.href = windowUrl.toString();
                }
            });
        });
    }
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