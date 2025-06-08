package com.moonstationery.model;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class CategoryDAO {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbc;

    @PostConstruct    
    @SuppressWarnings("unused")
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public boolean existsByName(String name) {
        String query = "SELECT COUNT(*) FROM product_type WHERE name = ?";
        Integer count = jdbc.queryForObject(query, Integer.class, name);
        return count != null && count > 0;
    }

    public void insertCategory(Category category) {
        String slug = SlugUrl.createSlug(category.getName());
        String query = "INSERT INTO product_type (name, slug) VALUES (?, ?)";
        jdbc.update(query, category.getName(), slug);
    }

    public Category getCategoryById(Integer id) {
        String query = "SELECT * FROM product_type WHERE id = ?";
        List<Category> categories = jdbc.query(query, (rs, rowNum) -> {
            Category c = new Category();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            return c;
        }, id);
        return categories.isEmpty() ? null : categories.get(0);
    }

    public Category getCategoryBySlug(String slug) {
        String query = "SELECT * FROM product_type WHERE slug = ?";
        List<Category> categories = jdbc.query(query, (rs, rowNum) -> {
            Category c = new Category();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            return c;
        }, slug);
        return categories.isEmpty() ? null : categories.get(0);
    }

    public List<Map<String, Object>> listAll() {
        return jdbc.queryForList("SELECT id, name, slug FROM product_type");
    }
}
