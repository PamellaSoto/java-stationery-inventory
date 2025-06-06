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
    let successes = document.querySelectorAll('.message--success');
    let errors = document.querySelectorAll('.message--error');
    
    let allMessages = Array.from(successes).concat(Array.from(errors));
    allMessages.forEach(message => message.style.display = "none");
}
setTimeout(systemMessages, 3000);