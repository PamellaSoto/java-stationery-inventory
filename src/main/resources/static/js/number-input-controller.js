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