console.log("Hello, this is the hero component script!");

// Click event handler for footer-store-images
document.addEventListener('DOMContentLoaded', function() {
    const storeImages = document.querySelectorAll('.footer-store-images');
    
    storeImages.forEach(function(image) {
        image.addEventListener('click', function(event) {
            event.preventDefault();
            console.log('Clicked on store image:', this);
            console.log('Image src:', this.src);
            console.log('Image alt:', this.alt);

        });
    });
});

document.ge