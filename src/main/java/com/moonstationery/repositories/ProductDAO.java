package com.moonstationery.repositories;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.moonstationery.model.Product;

import jakarta.annotation.PostConstruct;

@Repository
public class ProductDAO {
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbc;
    private final String PRODUCT_QUERY = 
    "SELECT p.*, a.name AS anime_name, a.slug AS anime_slug, " +
    "pt.name AS product_type_name, pt.slug AS product_type_slug " +
    "FROM product p " +
    "JOIN anime a ON p.anime_id = a.id " +
    "JOIN product_type pt ON p.product_type_id = pt.id ";

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public void insertProduct(Product product) {
        String query = """
            INSERT INTO product(
                product_name, 
                product_description, 
                price, 
                stock, 
                image_url, 
                anime_id, 
                product_type_id, 
                is_available)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?);
        """;
        Object[] values = {
            product.getProduct_name(),
            product.getProduct_description(),
            product.getPrice(),
            product.getStock(),
            product.getImage_url(),
            product.getAnime_id(),
            product.getProduct_type_id(),
            product.getIs_available()
        };
        jdbc.update(query, values);
    }

    public void editProduct(Product product) {
        String query = """
            UPDATE product 
            SET product_name = ?, 
                product_description = ?, 
                price = ?, 
                stock = ?, 
                image_url = ?, 
                anime_id = ?, 
                product_type_id = ?, 
                is_available = ? 
            WHERE id = ?;
        """;
        Object[] values = {
            product.getProduct_name(),
            product.getProduct_description(),
            product.getPrice(),
            product.getStock(),
            product.getImage_url(),
            product.getAnime_id(),
            product.getProduct_type_id(),
            product.getIs_available(),
            product.getId()
        };
        jdbc.update(query, values);
    }

    public void switchAvailable(Integer id) {
        String query = "UPDATE product SET is_available = NOT product.is_available WHERE id = ?";
        jdbc.update(query, id);
    }

    public void deleteProduct(Integer id) {
        String query = "DELETE FROM product WHERE id = ?";
        jdbc.update(query, id);
    }

    public Product getAvailableProductById(Integer id) {
        String query = PRODUCT_QUERY + "WHERE p.id = ? AND p.is_available = true";
        return jdbc.queryForObject(query, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setProduct_name(rs.getString("product_name"));
            product.setProduct_description(rs.getString("product_description"));
            product.setPrice(rs.getBigDecimal("price"));
            product.setStock(rs.getInt("stock"));
            product.setImage_url(rs.getString("image_url"));
            product.setIs_available(rs.getBoolean("is_available"));
            product.setAnime_id(rs.getInt("anime_id"));
            product.setProduct_type_id(rs.getInt("product_type_id"));
            product.setAnime_name(rs.getString("anime_name"));
            product.setProduct_type_name(rs.getString("product_type_name"));
            product.setAnime_slug(rs.getString("anime_slug"));
            product.setProduct_type_slug(rs.getString("product_type_slug"));
            return product;
        }, id);
    }

    public Product getProductById(Integer id) {
        String query = PRODUCT_QUERY + "WHERE p.id = ?";
        return jdbc.queryForObject(query, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setProduct_name(rs.getString("product_name"));
            product.setProduct_description(rs.getString("product_description"));
            product.setPrice(rs.getBigDecimal("price"));
            product.setStock(rs.getInt("stock"));
            product.setImage_url(rs.getString("image_url"));
            product.setIs_available(rs.getBoolean("is_available"));
            product.setAnime_id(rs.getInt("anime_id"));
            product.setProduct_type_id(rs.getInt("product_type_id"));
            product.setAnime_name(rs.getString("anime_name"));
            product.setProduct_type_name(rs.getString("product_type_name"));
            product.setAnime_slug(rs.getString("anime_slug"));
            product.setProduct_type_slug(rs.getString("product_type_slug"));
            return product;
        }, id);
    }

    public List<Map<String, Object>> getProductsByCategory(Integer categoryId) {
        String query = PRODUCT_QUERY + "WHERE p.product_type_id = ? AND p.is_available = true";
        return jdbc.queryForList(query, categoryId);
    }

    public List<Map<String, Object>> getProductsByAnime(Integer animeId) {
        String query = PRODUCT_QUERY + "WHERE p.anime_id = ? AND p.is_available = true";
        return jdbc.queryForList(query, animeId);
    }

    public Integer getInventoryQuantity() {
        String query = "SELECT COUNT(*) FROM product";
        return jdbc.queryForObject(query, Integer.class);
    }

    public List<Map<String, Object>> listAllAvailable() {
        String query = PRODUCT_QUERY + "WHERE p.is_available = true";
        return jdbc.queryForList(query);
    }

    public List<Map<String, Object>> listAll() {
        String query = PRODUCT_QUERY + "ORDER BY p.id";
        return jdbc.queryForList(query);
    }
}
