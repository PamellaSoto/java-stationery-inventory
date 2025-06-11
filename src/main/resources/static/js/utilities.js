function toggleDisplay(element) { 
    element.classList.toggle('hidden');
}

document.querySelectorAll(`[data-toggle-modal]`).forEach((button) => {
    button.addEventListener("click", () => {
        const target = button.getAttribute('data-toggle-modal');
        toggleDisplay(document.querySelector(`#${target}`));
    })
});

function systemMessages() {
    let message = document.querySelector('.message');

    message.classList.add("show");
    message.addEventListener('click', () => {
        message.classList.remove("show");
        message.style.display = "none";
    })
    setTimeout(() => {
        message.classList.remove("show");
        message.style.display = "none";
    }, 5000);
}

function emptyContent() {
    const products = document.querySelectorAll('.product');
    const noResultMsg = document.querySelector('#message--noresult');

    let visibleCount = 0;
    products.forEach(product => {
        if (product.offsetParent !== null) {
            visibleCount++;
        }
    });

    if (visibleCount === 0) {
        noResultMsg.classList.remove('hidden');
    } else {
        noResultMsg.classList.add('hidden');
    }
}


//Number input controller (cant be added inside a function and I don't know why)
let input = document.querySelector('#quantity');
const max = parseInt(input.max, 10) || Infinity;
const increaseBtn = document.querySelector('.quantity__button.quantity-increase');
const decreaseBtn = document.querySelector('.quantity__button.quantity-decrease');

increaseBtn.addEventListener('click', () => {
    let currentValue = parseInt(input.value, 10) || 1;
    if (currentValue < max) {
        input.value = currentValue + 1;
    } else {
        input.value = max;
    }
});

decreaseBtn.addEventListener('click', () => {
    let currentValue = parseInt(input.value, 10) || 1;
    if (currentValue > 1) {
        input.value = currentValue - 1;
    } else {
        input.value = 1;
    }
});


function searchBar() {
    const input = document.querySelector('#search-bar-input').value.trim().toLowerCase();
    console.log(input);    
    window.location.href = "/search/" + encodeURIComponent(input);
    searchBarResults(input);
}

function searchBarResults(input) {
    let allProducts = document.querySelectorAll('.product');
    allProducts.forEach(product => {
        let productName = product.querySelector('.product__title').textContent.toLowerCase();
        if (!productName.includes(input)) {
            product.remove();
        }        
    });
    emptyContent();
}

document.addEventListener('DOMContentLoaded', () => {
    systemMessages();
    emptyContent();
});
