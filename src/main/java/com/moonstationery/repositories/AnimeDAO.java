package com.moonstationery.repositories;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.moonstationery.model.Anime;
import com.moonstationery.model.SlugUrl;

import jakarta.annotation.PostConstruct;

@Repository
public class AnimeDAO {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbc;

    @Autowired
    private ProductDAO productDAO;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public boolean insertAnime(Anime anime) {
        String slug = SlugUrl.createSlug(anime.getName());

        String queryCheck = "SELECT COUNT(*) FROM anime WHERE slug = ?";
        Integer count = jdbc.queryForObject(queryCheck, Integer.class, slug);
        if (count != null && count > 0) {
            return false;
        }

        String query = "INSERT INTO anime (name, slug) VALUES (?, ?)";
        jdbc.update(query, anime.getName(), slug);
        return true;
    }

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

    public List<Map<String, Object>> listAllWithProductCount(Boolean dashboard) {
        String query;
        if (dashboard) {
            query = """
                SELECT an.id, an.name, an.slug,
                    COUNT(p.id) AS product_count
                FROM anime an
                LEFT JOIN product p ON an.id = p.anime_id
                GROUP BY an.id, an.name, an.slug
            """;
        } else {
            query = """
                SELECT an.id, an.name, an.slug,
                    COUNT(p.id) AS product_count
                FROM anime an
                LEFT JOIN product p ON an.id = p.anime_id AND p.is_available = true
                GROUP BY an.id, an.name, an.slug
            """;
        }

        return jdbc.queryForList(query);
    }

}
