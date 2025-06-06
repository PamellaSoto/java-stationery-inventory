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
    let messages = document.querySelectorAll('.message');
    
    messages.forEach(message => {
        message.classList.add("show");
        setTimeout(() => {
            message.classList.remove("show");
            message.style.display = "none";
        }, 5000);
    });
}
window.addEventListener('DOMContentLoaded', systemMessages);
