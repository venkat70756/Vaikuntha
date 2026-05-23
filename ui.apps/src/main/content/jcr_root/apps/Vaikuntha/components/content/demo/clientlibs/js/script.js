// (function () {
//     "use strict";

//     document.addEventListener("DOMContentLoaded", function () {
//         var buyButton = document.querySelector(".book-buy-btn");

//         if (buyButton) {
//             buyButton.addEventListener("click", function () {
//                 alert("Thank you for your interest! This book will be available soon.");
//             });
//         }
//     });
// })();



document.addEventListener("DOMContentLoaded", function () {

    fetch('/bin/first')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            const grid = document.getElementById("product-grid");

            data.forEach(item => {

                const card = document.createElement("div");
                card.className = "product-card";

                let details = "";

                if (item.data) {
                    for (const key in item.data) {
                        details += `<p><strong>${key}:</strong> ${item.data[key]}</p>`;
                    }
                } else {
                    details = "<p>No additional details available</p>";
                }

                card.innerHTML = `
                    <h3>${item.name}</h3>
                    <p><strong>ID:</strong> ${item.id}</p>
                    <div class="product-details">
                        ${details}
                    </div>
                `;

                grid.appendChild(card);
            });
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById("product-grid").innerHTML =
                "<p>Failed to load products.</p>";
        });
});
