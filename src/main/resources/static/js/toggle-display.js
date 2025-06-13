function toggleDisplay(element) {
    element.classList.toggle('hidden');
}

document.querySelectorAll('[data-toggle-modal]').forEach((button) => {
    button.addEventListener('click', () => {
        const targetId = button.getAttribute('data-toggle-modal');
        const modal = document.querySelector(`#${targetId}`);
        
        toggleDisplay(modal);

        function clickOutside(event) {
            if (!modal.contains(event.target) && !button.contains(event.target)) {
                toggleDisplay(modal);
                window.removeEventListener('click', clickOutside);
            }
        } setTimeout(() => {
            window.addEventListener('click', clickOutside); }, 0);
    });
});
