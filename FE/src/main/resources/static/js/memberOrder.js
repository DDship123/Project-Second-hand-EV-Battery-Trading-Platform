window.addEventListener('DOMContentLoaded', (event) => {
  setUpTransactionUrl();
  changeTransactionType();
  changeStatus();

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

function setUpTransactionUrl(){
  let transactionUrl = document.querySelectorAll(".order-management__list a");
  if (window.location.href.includes("sell")) {
    transactionUrl.forEach(link => {
      const transactionId = link.getAttribute("data-transaction-id");
      link.setAttribute('href', link.getAttribute('href') + 'sell/detail/'+transactionId);
    })
  }else {
    transactionUrl.forEach(link => {
      const transactionId = link.getAttribute("data-transaction-id");
      link.setAttribute('href', link.getAttribute('href') + 'detail/'+transactionId);
    })
  }
}


function changeTransactionType(){
  const changeTransactionTypeBtn = document.querySelectorAll(".filter-tabs .tab-btn")
  changeTransactionTypeBtn.forEach(btn => btn.classList.remove('active'));

  if (window.location.href.includes("sell")) {
    changeTransactionTypeBtn[1].classList.add('active');
  }else {
    changeTransactionTypeBtn[0].classList.add('active');
  }
  changeTransactionTypeBtn[0].addEventListener('click', function(){
    window.location.href = "/home/order";
  });

  changeTransactionTypeBtn[1].addEventListener('click', function(){
    window.location.href = "/home/order/sell";
  });
}

function changeStatus(){
  const selectBuyStatus = document.querySelector('#purchaseTab .tab-select');
  const options = selectBuyStatus.querySelectorAll('option');

  if (!window.location.href.includes("status") || window.location.href.includes("REQUESTED")) {
    options[0].selected = true;
  }else if (window.location.href.includes("ACCEPTED")) {
    options[1].selected = true;
  }else if (window.location.href.includes("CANCELLED")) {
    options[2].selected = true;
  }

  selectBuyStatus.addEventListener('change', function () {
    const subTab = this.value;
    let windowUrl = new URL(window.location.href);
    windowUrl.searchParams.delete('successMessage');
    windowUrl.searchParams.delete('errorMessage');
    windowUrl.searchParams.set('status', subTab);
    window.location.href = windowUrl.toString();
  });
}
