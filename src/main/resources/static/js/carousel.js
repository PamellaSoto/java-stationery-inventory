window.addEventListener('DOMContentLoaded', () => {
    const prevBtn = document.querySelector('#carousel--prev');
    const nextBtn = document.querySelector('#carousel--next');
    const viewport = document.querySelector('.carousel--overflow');
    const list = viewport.querySelector('.featured-categories');
    const items = list.querySelectorAll('.featured-categories__item');
  
    const itemWidth = items[0].offsetWidth + 24;
    const totalItems = items.length;
    const visibleItems = 3;
    const maxScroll = itemWidth * (totalItems - visibleItems);

    viewport.style.width = ((itemWidth * visibleItems) - 24) + 'px';

    nextBtn.addEventListener('click', () => {
        if (viewport.scrollLeft >= maxScroll) {
            viewport.scrollTo({ left: 0, behavior: 'smooth' });
        } else {
            viewport.scrollBy({ left: itemWidth, behavior: 'smooth' });
        }
    });

    prevBtn.addEventListener('click', () => {
        if (viewport.scrollLeft <= 0) {
            viewport.scrollTo({ left: maxScroll, behavior: 'smooth' });
        } else {
            viewport.scrollBy({ left: -itemWidth, behavior: 'smooth' });
        }
    });
});
