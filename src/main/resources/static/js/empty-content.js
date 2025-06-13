export function emptyContent() {
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
