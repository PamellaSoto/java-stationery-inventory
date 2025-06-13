package com.moonstationery.repositories;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.moonstationery.model.Category;
import com.moonstationery.model.SlugUrl;

import jakarta.annotation.PostConstruct;

@Repository
public class CategoryDAO {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbc;

    @Autowired
    private ProductDAO productDAO;

    @PostConstruct    
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public void insertCategory(Category category) {
        String slug = SlugUrl.createSlug(category.getName());
        String query = "INSERT INTO product_type (name, slug) VALUES (?, ?)";
        jdbc.update(query, category.getName(), slug);
    }

    public boolean deleteCategoryType(Integer id) {
        List<Map<String, Object>> products = productDAO.getProductsByCategory(id);

        if (products.isEmpty()) {
            String query = "DELETE FROM product_type WHERE id = ?";
            jdbc.update(query, id);
            return true;
        }
        return false;
    }
    
    public boolean existsByName(String name) {
        String query = "SELECT COUNT(*) FROM product_type WHERE name = ?";
        Integer count = jdbc.queryForObject(query, Integer.class, name);
        return count != null && count > 0;
    }
    
    public Category getCategoryById(Integer id) {
        String query = "SELECT * FROM product_type WHERE id = ?";
        List<Category> categories = jdbc.query(query, (rs, rowNum) -> {
            Category c = new Category();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            c.setSlug(rs.getString("slug"));
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
            c.setSlug(rs.getString("slug"));
            return c;
        }, slug);
        return categories.isEmpty() ? null : categories.get(0);
    }

    public List<Map<String, Object>> listAllWithProductCount(Boolean dashboard) {
        String query;
        if (dashboard) {
            query = """
                SELECT pt.id, pt.name, pt.slug,
                    COUNT(p.id) AS product_count
                FROM product_type pt
                LEFT JOIN product p ON pt.id = p.product_type_id
                GROUP BY pt.id, pt.name, pt.slug
            """;
        } else {
            query = """
                SELECT pt.id, pt.name, pt.slug,
                    COUNT(p.id) AS product_count
                FROM product_type pt
                LEFT JOIN product p ON pt.id = p.product_type_id AND p.is_available = true
                GROUP BY pt.id, pt.name, pt.slug
            """;
        }

        return jdbc.queryForList(query);
    }

}
