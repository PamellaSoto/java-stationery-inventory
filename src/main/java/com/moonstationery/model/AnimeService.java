package com.moonstationery.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimeService {
    @Autowired
    AnimeDAO animeDAO;

    public boolean insertAnime(Anime anime){
        if(animeDAO.existsByName(anime.getName())) {
            return false;
        }
        animeDAO.insertAnime(anime);
        return true;
    }

    public List<Map<String,Object>> listAll(){
        return animeDAO.listAll();
    }
}
