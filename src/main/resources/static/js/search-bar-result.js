import { emptyContent } from './empty-content.js';

document.addEventListener('DOMContentLoaded', () => {
    let searchValue = document.querySelector('body');
    let searchInput = searchValue.getAttribute('data-search');
    console.log(searchInput)
    if (searchInput) {
        const allProducts = document.querySelectorAll('.product');
        allProducts.forEach(product => {
            const productName = product.querySelector('.product-card__title').textContent.toLowerCase();
            if (!productName.includes(searchInput)) {
                product.remove();
            }
        });
        
        const products = document.querySelectorAll('.product');
        const noResultMsg = document.querySelector('#message--noresult');

        let visibleCount = 0;
        products.forEach(product => {
            if (product.offsetParent !== null) {
                visibleCount++;
            }
        });
        console.log(visibleCount);
        if (visibleCount === 0) {
            noResultMsg.classList.remove('hidden');
        } else {
            noResultMsg.classList.add('hidden');
        }
    }
});
