// =====================================================
//    PERSONAL PAGE MANAGEMENT
// =====================================================

// =====================================================
//    INITIALIZATION
// =====================================================

// Initialize when page loads
window.addEventListener('load', function() {
    handleBackButton();
});

// Initialize when DOM is ready
document.addEventListener('DOMContentLoaded', function() {
    // Initialize form editing functionality
    handleEditButton();

    // Initialize alert auto-hide
    handleAlertMessages();

    // Initialize avatar upload functionality
    handleAvatarUpload();

    // Store initial form values
    storeOriginalValues();

    // Debug logging
    console.log('Input elements found:', getInputElements());
    console.log('Current values:', getInputValues());
});



// Global variables
let originalValues = {};

// =====================================================
//    UTILITY FUNCTIONS
// =====================================================

// Function to get all specified input elements
function getInputElements() {
    return {
        name: document.getElementById('name'),
        address: document.getElementById('address'),
        phone: document.getElementById('phone'),
        email: document.getElementById('email'),
        city: document.getElementById('city')
    };
}

// Function to get values from all inputs
function getInputValues() {
    const elements = getInputElements();
    return {
        name: elements.name?.value || '',
        address: elements.address?.value || '',
        phone: elements.phone?.value || '',
        email: elements.email?.value || '',
        city: elements.city?.value || ''
    };
}

// Function to set values to all inputs
function setInputValues(values) {
    const elements = getInputElements();

    Object.keys(values).forEach(key => {
        if (elements[key] && values[key] !== undefined) {
            elements[key].value = values[key];
        }
    });
}

// Function to enable/disable inputs
function toggleInputsEnabled(enabled) {
    const elements = getInputElements();

    Object.values(elements).forEach(element => {
        if (element && element.id !== 'createdDate') {
            element.disabled = !enabled;

            if (enabled) {
                element.removeAttribute('disabled');
                element.classList.add("active");
            } else {
                element.setAttribute('disabled', 'true');
                element.classList.remove("active");
            }
        }
    });
}

// Store initial values
function storeOriginalValues() {
    originalValues = getInputValues();
    console.log('Original values stored:', originalValues);
}

// =====================================================
//    EVENT HANDLERS
// =====================================================

// Back button handler
function handleBackButton() {
    const goBackButton = document.querySelector(".back");
    if (goBackButton) {
        goBackButton.addEventListener('click', function() {
            window.location.href = "/home";
        });
    }
}

// Edit button handler
function handleEditButton() {
    const editButton = document.querySelector('.profile__edit');
    const submitButton = document.querySelector('.profile__container__submit');
    const cancelButton = document.querySelector('.profile__container__cancel');

    if (!editButton || !submitButton || !cancelButton) return;

    const inputElements = getInputElements();
    const editableElements = Object.values(inputElements).filter(element => element !== null);

    // Edit button click
    editButton.addEventListener('click', function(e) {
        e.preventDefault();

        // Store current values before editing
        storeOriginalValues();

        // Enable all editable elements
        toggleInputsEnabled(true);

        // Show action buttons, hide edit button
        submitButton.classList.add('show');
        cancelButton.classList.add('show');
        editButton.style.display = 'none';

        // Focus on first editable element
        if (editableElements.length > 0 && editableElements[0]) {
            editableElements[0].focus();
        }

        console.log('Edit mode enabled');
    });

    // Cancel button click
    cancelButton.addEventListener('click', function(e) {
        e.preventDefault();

        // Restore original values
        setInputValues(originalValues);

        // Disable all elements
        toggleInputsEnabled(false);

        // Hide action buttons, show edit button
        submitButton.classList.remove('show');
        cancelButton.classList.remove('show');
        editButton.style.display = 'block';

        console.log('Edit cancelled, values restored:', originalValues);
    });

    // Submit button click
    submitButton.addEventListener('click', function(e) {
        const currentValues = getInputValues();
        console.log('Form submitted with values:', currentValues);
        // Form will submit normally - validation can be added here if needed
    });
}

// Auto-hide alert messages
function handleAlertMessages() {
    const alerts = document.querySelectorAll('.alert');

    alerts.forEach(function(alert) {
        // Set timeout to fade out after 2.5 seconds
        setTimeout(function() {
            alert.classList.add('fade-out');

            // Remove element from DOM after animation completes
            setTimeout(function() {
                alert.remove();
            }, 300); // Wait for CSS transition to complete
        }, 2500);
    });
}

// Avatar upload form handler
function handleAvatarUpload() {
    const avatarEditButton = document.querySelector('.profile__avatar__edit');
    const avatarForm = document.querySelector('.profile__avatar__form');

    if (avatarEditButton && avatarForm) {
        avatarEditButton.addEventListener('click', function(e) {
            e.preventDefault();
            avatarForm.classList.toggle('show');
        });

        // Close form when clicking outside
        document.addEventListener('click', function(e) {
            if (!avatarForm.contains(e.target) && !avatarEditButton.contains(e.target)) {
                avatarForm.classList.remove('show');
            }
        });
    }
}

