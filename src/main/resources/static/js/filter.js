const animeFilter = document.querySelectorAll(`[data-anime-filter-id]`);
const categoryFilter = document.querySelectorAll(`[data-category-filter-id]`);
const allProducts = document.querySelectorAll(`[data-product-id]`);
const noResultMsg = document.querySelector('#message--noresult');
const ResultMsg = document.querySelector('#message--result');

animeFilter.forEach((button) => filterByCategory(button, 'data-anime-filter-id', 'data-anime-id'))
categoryFilter.forEach((button) => filterByCategory(button, 'data-category-filter-id', 'data-type-id'))

function filterByCategory(button, itemAttr, prodAttr) {
    button.addEventListener("click", () => {
        const filterId = button.getAttribute(itemAttr);
        let productCounter = 0;

        allProducts.forEach((product) => {
            const productId = product.getAttribute(prodAttr);
            if (filterId == productId) {
                product.style.display = "";
                productCounter++;
            } else {
                product.style.display = "none";
            }            
        });
        if (productCounter === 0) {
            ResultMsg.classList.add('hidden');
            noResultMsg.classList.remove('hidden');
        } else {
            ResultMsg.classList.remove('hidden');
            noResultMsg.classList.add('hidden');
        }
    });
}
