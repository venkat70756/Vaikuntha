(function () {
    "use strict";

    document.addEventListener("DOMContentLoaded", function () {
        var buyButton = document.querySelector(".book-buy-btn");

        if (buyButton) {
            buyButton.addEventListener("click", function () {
                alert("Thank you for your interest! This book will be available soon.");
            });
        }
    });
})();
