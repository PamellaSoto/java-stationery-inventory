addEventListener('DOMContentLoaded', () => {
    const products = document.querySelectorAll('.product');
    const noResultMsg = document.querySelector('#message--noresult');
    console.log(products);
    if (products.length === 0) {
        noResultMsg.classList.remove('hidden');
    }

})
