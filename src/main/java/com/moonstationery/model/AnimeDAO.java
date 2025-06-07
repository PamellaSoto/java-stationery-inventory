package com.moonstationery.model;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class AnimeDAO {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public boolean existsByName(String name) {
        String query = "SELECT COUNT(*) FROM anime WHERE name = ?";
        Integer count = jdbc.queryForObject(query, Integer.class, name);
        return count != null && count > 0;
    }

    public void insertAnime(Anime anime) {
        String slug = SlugUrl.createSlug(anime.getName());
        String query = "INSERT INTO anime (name, slug) VALUES (?, ?)";
        jdbc.update(query, anime.getName(), slug);
    }

    public Anime getAnimeById(Integer id) {
        String query = "SELECT * FROM anime WHERE id = ?";
        List<Anime> animes = jdbc.query(query, (rs, rowNum) -> {
            Anime a = new Anime();
            a.setId(rs.getInt("id"));
            a.setName(rs.getString("name"));
            return a;
        }, id);
        return animes.isEmpty() ? null : animes.get(0);
    }

    public Anime getAnimeBySlug(String slug) {
        String query = "SELECT * FROM anime WHERE slug = ?";
        List<Anime> animes = jdbc.query(query, (rs, rowNum) -> {
            Anime a = new Anime();
            a.setId(rs.getInt("id"));
            a.setName(rs.getString("name"));
            return a;
        }, slug);
        return animes.isEmpty() ? null : animes.get(0);
    }

    public List<Map<String, Object>> listAll() {
        return jdbc.queryForList("SELECT id, name FROM anime");
    }
}
