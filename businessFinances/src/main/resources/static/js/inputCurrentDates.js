document.addEventListener("DOMContentLoaded", function() {
            const startDateInput = document.getElementById("startDate");
            const endDateInput = document.getElementById("endDate");

            const today = new Date();

            const firstDay = new Date(today.getFullYear(), today.getMonth(), 1);

            const lastDay = new Date(today.getFullYear(), today.getMonth() + 1, 0);

            const formatDate = (date) => {
                const year = date.getFullYear();
                const month = String(date.getMonth() + 1).padStart(2, '0');
                const day = String(date.getDate()).padStart(2, '0');
                return `${year}-${month}-${day}`;
            };

            startDateInput.value = formatDate(firstDay);
            endDateInput.value = formatDate(lastDay);
        });