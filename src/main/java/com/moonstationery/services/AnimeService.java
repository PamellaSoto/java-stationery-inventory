package com.moonstationery.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonstationery.model.Anime;
import com.moonstationery.repositories.AnimeDAO;

@Service
public class AnimeService {

    @Autowired
    private AnimeDAO animeDAO;

    public boolean insertAnime(Anime anime) {
        // First letter must be always capitalized
        String animeTitle = anime.getName();
        animeTitle = animeTitle.substring(0, 1).toUpperCase() + animeTitle.substring(1).toLowerCase();
        anime.setName(animeTitle);
        if (animeDAO.existsByName(anime.getName())) {
            return false;
        }
        animeDAO.insertAnime(anime);
        return true;
    }

    public boolean deleteAnime(Integer id) {
        return animeDAO.deleteAnime(id);
    }

    public Anime getAnimeById(Integer id) {
        return animeDAO.getAnimeById(id);
    }

    public Anime getAnimeBySlug(String slug) {
        return animeDAO.getAnimeBySlug(slug);
    }

    public List<Map<String, Object>> listAllWithProductCount(Boolean dashboard) {
        return animeDAO.listAllWithProductCount(dashboard);
    }
}
