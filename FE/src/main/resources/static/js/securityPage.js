document.addEventListener('DOMContentLoaded', function() {
    // Auto-hide alert messages after 2.5 seconds
    const alerts = document.querySelectorAll('.alert');
    alerts.forEach(alert => {
        setTimeout(() => {
            alert.classList.add('fade-out');
            setTimeout(() => {
                alert.remove();
            }, 500); // Wait for fade-out animation to complete
        }, 2500);
    });

    // Handle cancel button click
    const cancelButton = document.querySelector('.updatePassword__container__cancel');
    if (cancelButton) {
        cancelButton.addEventListener('click', function() {
            // Clear all form inputs
            const form = document.querySelector('.updatePassword__container');
            form.reset();

            // Optional: redirect back to member page or previous page
            // window.history.back();
        });
    }

    // Handle back button click
    const backButton = document.querySelector('.back');
    if (backButton) {
        backButton.addEventListener('click', function() {
            window.location.href = '/home';
        });
    }

    // Form validation before submit
    const form = document.querySelector('.updatePassword__container');
    if (form) {
        form.addEventListener('submit', function(e) {
            const currentPassword = document.getElementById('currentPassword').value;
            const newPassword = document.getElementById('newPassword').value;
            const confirmPassword = document.getElementById('confirmPassword').value;

            // Check if all fields are filled
            if (!currentPassword || !newPassword || !confirmPassword) {
                e.preventDefault();
                showAlert('Vui lòng điền đầy đủ thông tin', 'error');
                return;
            }

            // // Check if new password and confirm password match
            // if (newPassword !== confirmPassword) {
            //     e.preventDefault();
            //     showAlert('Mật khẩu mới và xác nhận mật khẩu không khớp', 'error');
            //     return;
            // }
            //
            // // Check password strength (minimum 6 characters)
            // if (newPassword.length < 6) {
            //     e.preventDefault();
            //     showAlert('Mật khẩu mới phải có ít nhất 6 ký tự', 'error');
            //     return;
            // }
        });
    }

    // Function to show alert messages dynamically
    function showAlert(message, type) {
        // Remove existing alerts
        const existingAlerts = document.querySelectorAll('.alert');
        existingAlerts.forEach(alert => alert.remove());

        // Create new alert
        const alertDiv = document.createElement('div');
        alertDiv.className = `alert alert-${type}`;
        alertDiv.textContent = message;

        // Insert alert before the form
        const form = document.querySelector('.updatePassword__container');
        form.parentNode.insertBefore(alertDiv, form);

        // Auto-hide after 2.5 seconds
        setTimeout(() => {
            alertDiv.classList.add('fade-out');
            setTimeout(() => {
                alertDiv.remove();
            }, 500);
        }, 2500);
    }
});
