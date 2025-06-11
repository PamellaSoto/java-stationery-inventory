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
<<<<<<< HEAD
    let messages = document.querySelectorAll('.message');
    
    messages.forEach(message => {
        message.classList.add("show");
        setTimeout(() => {
            message.classList.remove("show");
            message.style.display = "none";
        }, 5000);
    });
}
function emptyContent() {
    const products = document.querySelectorAll('.product');
    console.log(products)
=======
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
>>>>>>> cc1b82b (feat: update add category logic and implement delete category feature)
    const noResultMsg = document.querySelector('#message--noresult');

    if (products.length === 0) {
        noResultMsg.classList.remove('hidden');
    }
}
<<<<<<< HEAD
function loadEvents() {
    systemMessages();
    emptyContent();
}
window.addEventListener('DOMContentLoaded', loadEvents);
=======

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
        input.value = max; // Optional: force clamp to max
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

document.addEventListener('DOMContentLoaded', () => {
    systemMessages();
    emptyContent();
});
>>>>>>> cc1b82b (feat: update add category logic and implement delete category feature)
