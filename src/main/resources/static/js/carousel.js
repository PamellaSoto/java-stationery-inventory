window.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('.carousel--wrapper, .nav__category-carousel').forEach(carousel => {
        const prevBtn = carousel.querySelector('.carousel--prev');
        const nextBtn = carousel.querySelector('.carousel--next');
        const viewport = carousel.querySelector('.carousel--overflow');
        const list = carousel.querySelector('[data-carousel-list]');
        const items = carousel.querySelectorAll('[data-carousel-item]');

        if (!viewport || !list || items.length === 0) return;

        const itemStyle = getComputedStyle(items[0]);
        const itemGap = parseInt(itemStyle.marginRight) || 24;
        const itemWidth = items[0].offsetWidth + itemGap;
        const scrollStep = itemWidth;

        // Scroll Right
        nextBtn?.addEventListener('click', () => {
            const maxScroll = list.scrollWidth - viewport.clientWidth;
            const nextPos = Math.min(viewport.scrollLeft + scrollStep, maxScroll);
            viewport.scrollTo({ left: nextPos, behavior: 'smooth' });
        });

        // Scroll Left
        prevBtn?.addEventListener('click', () => {
            const prevPos = Math.max(viewport.scrollLeft - scrollStep, 0);
            viewport.scrollTo({ left: prevPos, behavior: 'smooth' });
        });
    });
});
