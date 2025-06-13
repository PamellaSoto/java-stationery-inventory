let message = document.querySelector('.message');

message.classList.add("show");
message.addEventListener('click', () => {
    message.classList.remove("show");
    message.style.display = "none";
})
setTimeout(() => {
    message.classList.remove("show");
}, 4000).then(
    console.log("then works!")
).then( message.style.display = "none" );

   