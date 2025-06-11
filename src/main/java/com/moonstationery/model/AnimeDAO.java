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

<<<<<<< HEAD
=======
    @Autowired
    private ProductDAO productDAO;

>>>>>>> cc1b82b (feat: update add category logic and implement delete category feature)
    @PostConstruct
    @SuppressWarnings("unused")
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

<<<<<<< HEAD
    public boolean existsByName(String name) {
        String query = "SELECT COUNT(*) FROM anime WHERE name = ?";
        Integer count = jdbc.queryForObject(query, Integer.class, name);
        return count != null && count > 0;
    }

=======
>>>>>>> cc1b82b (feat: update add category logic and implement delete category feature)
    public void insertAnime(Anime anime) {
        String slug = SlugUrl.createSlug(anime.getName());
        String query = "INSERT INTO anime (name, slug) VALUES (?, ?)";
        jdbc.update(query, anime.getName(), slug);
    }

<<<<<<< HEAD
=======
    public boolean deleteAnime(Integer id) {
        List<Map<String, Object>> products = productDAO.getProductsByCategory(id);

        if (products.isEmpty()) {
            String query = "DELETE FROM anime WHERE id = ?";
            jdbc.update(query, id);
            return true;
        }
        return false;
    }

    public boolean existsByName(String name) {
        String query = "SELECT COUNT(*) FROM anime WHERE name = ?";
        Integer count = jdbc.queryForObject(query, Integer.class, name);
        return count != null && count > 0;
    }

>>>>>>> cc1b82b (feat: update add category logic and implement delete category feature)
    public Anime getAnimeById(Integer id) {
        String query = "SELECT * FROM anime WHERE id = ?";
        List<Anime> animes = jdbc.query(query, (rs, rowNum) -> {
            Anime a = new Anime();
            a.setId(rs.getInt("id"));
            a.setName(rs.getString("name"));
            a.setSlug(rs.getString("slug"));
            return a;
        }, id);
        return animes.isEmpty() ? null : animes.get(0);
    }

<<<<<<< HEAD

=======
>>>>>>> cc1b82b (feat: update add category logic and implement delete category feature)
    public Anime getAnimeBySlug(String slug) {
        String query = "SELECT * FROM anime WHERE slug = ?";
        List<Anime> animes = jdbc.query(query, (rs, rowNum) -> {
            Anime a = new Anime();
            a.setId(rs.getInt("id"));
            a.setName(rs.getString("name"));
            a.setSlug(rs.getString("slug"));
            return a;
        }, slug);
        return animes.isEmpty() ? null : animes.get(0);
    }

<<<<<<< HEAD

=======
>>>>>>> cc1b82b (feat: update add category logic and implement delete category feature)
    public List<Map<String, Object>> listAll() {
        return jdbc.queryForList("SELECT id, name, slug FROM anime");
    }
}
