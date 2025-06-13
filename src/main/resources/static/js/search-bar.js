function searchBar(event) {
    event.preventDefault();
    const input = document.querySelector('#search-bar-input').value.trim().toLowerCase();
    if (input) {
        window.location.href = "/search/" + encodeURIComponent(input);
    }
}
// Attach event listener dynamically
document.addEventListener('DOMContentLoaded', () => {
    const searchForm = document.querySelector('form.searchbar');
    if (searchForm) {
        searchForm.addEventListener('submit', searchBar);
    }
});
