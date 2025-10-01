document.addEventListener("DOMContentLoaded", function () {
    // Category Dropdown
    const categoryDropdownSelected =
        document.getElementById("categoryDropdown");

    const categoryDropdownOptions =
        document.getElementById("categoryOptions");

    const categorySelectedText =
        categoryDropdownSelected.querySelector(".selected-text");

    const categoryOptions =
        categoryDropdownOptions.querySelectorAll(".dropdown-option");

    // Location Dropdown
    const locationDropdownSelected =
        document.getElementById("locationDropdown");

    const locationDropdownOptions =
        document.getElementById("locationOptions");

    const locationSelectedText =
        locationDropdownSelected.querySelector(".selected-text");

    const locationOptions =
        locationDropdownOptions.querySelectorAll(".dropdown-option");


    // Function to handle dropdown toggle
    function setupDropdown(
        dropdownSelected,
        dropdownOptions,
        selectedText,
        options
    ) {
        // Toggle dropdown
        dropdownSelected.addEventListener("click", function (e) {
            e.stopPropagation();

            // Close other dropdowns first
            document
                .querySelectorAll(".dropdown-selected.active")
                .forEach((dropdown) => {
                    if (dropdown !== dropdownSelected) {
                        dropdown.classList.remove("active");
                        dropdown.nextElementSibling.classList.remove("show");
                    }
                });

            dropdownSelected.classList.toggle("active");
            dropdownOptions.classList.toggle("show");
        });

        // Handle option selection
        options.forEach((option) => {
            option.addEventListener("click", function (e) {
                e.stopPropagation();

                // Remove selected class from all options
                options.forEach((opt) => opt.classList.remove("selected"));

                // Add selected class to clicked option
                this.classList.add("selected");

                // Update selected text
                selectedText.textContent = this.textContent;

                // Close dropdown
                dropdownSelected.classList.remove("active");
                dropdownOptions.classList.remove("show");

                // Store selected value
                dropdownSelected.dataset.value = this.dataset.value;
            });
        });
    }

    // Setup both dropdowns
    setupDropdown(
        categoryDropdownSelected,
        categoryDropdownOptions,
        categorySelectedText,
        categoryOptions
    );
    setupDropdown(
        locationDropdownSelected,
        locationDropdownOptions,
        locationSelectedText,
        locationOptions
    );

    // Close dropdowns when clicking outside
    document.addEventListener("click", function () {
        document
            .querySelectorAll(".dropdown-selected.active")
            .forEach((dropdown) => {
                dropdown.classList.remove("active");
                dropdown.nextElementSibling.classList.remove("show");
            });
    });
});