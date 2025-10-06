window.addEventListener('load', function() {
    const goBackButton = document.querySelector(".back");
    goBackButton.addEventListener('click', function() {
        window.location.href = "/home";
    });
});
// =====================================================
//    GET INPUT ELEMENTS
// =====================================================

// Function to get all specified input elements
function getInputElements() {
    const nameInput = document.getElementById('name');
    const addressSelect = document.getElementById('address');
    const phoneInput = document.getElementById('phone');
    const emailInput = document.getElementById('email');

    return {
        name: nameInput,
        address: addressSelect,
        phone: phoneInput,
        email: emailInput
    };
}

// Function to get values from all inputs
function getInputValues() {
    const elements = getInputElements();

    return {
        name: elements.name?.value || '',
        address: elements.address?.value || '',
        phone: elements.phone?.value || '',
        email: elements.email?.value || ''
    };
}

// Function to set values to all inputs
function setInputValues(values) {
    const elements = getInputElements();

    if (elements.name && values.name !== undefined) {
        elements.name.value = values.name;
    }
    if (elements.address && values.address !== undefined) {
        elements.address.value = values.address;
    }
    if (elements.phone && values.phone !== undefined) {
        elements.phone.value = values.phone;
    }
    if (elements.email && values.email !== undefined) {
        elements.email.value = values.email;
    }
}

// Function to enable/disable inputs
function toggleInputsEnabled(enabled) {
    const elements = getInputElements();

    Object.values(elements).forEach(element => {
        if (element && element.id !== 'createdDate') { // Skip createdDate field
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

// =====================================================
//    EDIT BUTTON FUNCTIONALITY
// =====================================================

document.addEventListener('DOMContentLoaded', function() {
    // Get elements
    const editButton = document.querySelector('.profile__edit');
    const submitButton = document.querySelector('.profile__container__submit');
    const cancelButton = document.querySelector('.profile__container__cancel');

    // Get all editable form elements using the function above
    const inputElements = getInputElements();
    const editableElements = Object.values(inputElements).filter(element => element !== null);

    // Store original values
    let originalValues = {};

    // Store initial values
    function storeOriginalValues() {
        originalValues = getInputValues();
        console.log('Original values stored:', originalValues);
    }

    // Edit button click handler
    editButton.addEventListener('click', function(e) {
        e.preventDefault();

        // Store current values before editing
        storeOriginalValues();

        // Enable all editable elements
        toggleInputsEnabled(true);

        // Add 'show' class to both buttons
        submitButton.classList.add('show');
        cancelButton.classList.add('show');

        // Hide edit button
        editButton.style.display = 'none';

        // Focus on first editable element
        if (editableElements.length > 0 && editableElements[0]) {
            editableElements[0].focus();
        }

        console.log('Edit mode enabled');
    });

    // Cancel button click handler
    cancelButton.addEventListener('click', function(e) {
        e.preventDefault();

        // Restore original values
        setInputValues(originalValues);

        // Disable all elements
        toggleInputsEnabled(false);

        // Remove 'show' class from both buttons
        submitButton.classList.remove('show');
        cancelButton.classList.remove('show');

        // Show edit button again
        editButton.style.display = 'block';

        console.log('Edit cancelled, values restored:', originalValues);
    });

    // Submit button - just let form submit naturally
    submitButton.addEventListener('click', function(e) {
        const currentValues = getInputValues();
        console.log('Form submitted with values:', currentValues);
        // Form will submit normally
        // You can add validation here if needed
    });

    // Initialize
    storeOriginalValues();

    // Debug: Log all elements on page load
    console.log('Input elements found:', inputElements);
    console.log('Current values:', getInputValues());
});

