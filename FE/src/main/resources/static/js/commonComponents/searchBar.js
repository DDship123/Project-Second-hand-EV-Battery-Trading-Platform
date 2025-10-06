document.addEventListener("DOMContentLoaded", function () {
    // ========================================
    // DROPDOWN FUNCTIONALITY
    // ========================================

    // Category Dropdown Elements
    const categoryDropdownSelected = document.getElementById("categoryDropdown");
    const categoryDropdownOptions = document.getElementById("categoryOptions");
    const categorySelectedText =
        categoryDropdownSelected.querySelector(".selected-text");
    const categoryOptions =
        categoryDropdownOptions.querySelectorAll(".dropdown-option");

    // Location Dropdown Elements
    const locationDropdownSelected = document.getElementById("locationDropdown");
    const locationDropdownOptions = document.getElementById("locationOptions");
    const locationSelectedText =
        locationDropdownSelected.querySelector(".selected-text");
    const locationOptions =
        locationDropdownOptions.querySelectorAll(".dropdown-option");

    /**
     * Setup dropdown functionality
     * @param {Element} dropdownSelected - The dropdown trigger element
     * @param {Element} dropdownOptions - The dropdown options container
     * @param {Element} selectedText - The selected text element
     * @param {NodeList} options - The dropdown option elements
     */
    function setupDropdown(
        dropdownSelected,
        dropdownOptions,
        selectedText,
        options
    ) {
        // Toggle dropdown on click
        dropdownSelected.addEventListener("click", function (e) {
            e.stopPropagation();

            // Close other open dropdowns
            document
                .querySelectorAll(".dropdown-selected.active")
                .forEach((dropdown) => {
                    if (dropdown !== dropdownSelected) {
                        dropdown.classList.remove("active");
                        dropdown.nextElementSibling.classList.remove("show");
                    }
                });

            // Toggle current dropdown
            dropdownSelected.classList.toggle("active");
            dropdownOptions.classList.toggle("show");
        });

        // Handle option selection
        options.forEach((option) => {
            option.addEventListener("click", function (e) {
                e.stopPropagation();

                // Update selection state
                options.forEach((opt) => opt.classList.remove("selected"));
                this.classList.add("selected");

                // Update displayed text
                selectedText.textContent = this.textContent;

                // Close dropdown
                dropdownSelected.classList.remove("active");
                dropdownOptions.classList.remove("show");

                // Store selected value
                dropdownSelected.dataset.value = this.dataset.value;

                // Optional: Log selection for debugging
                console.log("Selected:", this.dataset.value, ":", this.textContent);
            });
        });
    }

    // Initialize both dropdowns
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
