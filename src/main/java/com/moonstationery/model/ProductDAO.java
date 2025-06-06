package com.moonstationery.model;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class ProductDAO {
    @Autowired
    DataSource dataSource;
	JdbcTemplate jdbc;

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
        Object[] values = new Object[8];
            values[0] = product.getProduct_name();
            values[1] = product.getProduct_description();
            values[2] = product.getPrice();
            values[3] = product.getStock();
            values[4] = product.getImage_url();
            values[5] = product.getAnime_id();
            values[6] = product.getProduct_type_id();
            values[7] = product.getIs_available();
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
       Object[] values = new Object[9];
        values[0] = product.getProduct_name();
        values[1] = product.getProduct_description();
        values[2] = product.getPrice();
        values[3] = product.getStock();
        values[4] = product.getImage_url();
        values[5] = product.getAnime_id();
        values[6] = product.getProduct_type_id();
        values[7] = product.getIs_available();
        values[8] = product.getId();
        jdbc.update(query, values);
    }

    public void deleteProduct(Integer id) {
        String query = "DELETE FROM product where id = ?;";
        jdbc.update(query, id);
    }

    public List<Map<String,Object>> listAllAvailable() {
        String query = """
            SELECT 
                p.product_name,
                p.product_description,
                p.price,
                p.stock,
                p.image_url,
                p.is_available,
                a.name AS anime_name,
                pt.name AS product_type_name
            FROM product p
            JOIN anime a ON p.anime_id = a.id
            JOIN product_type pt ON p.product_type_id = pt.id;
            WHERE p.is_available = true;
        """;
        return jdbc.queryForList(query);
    }

    public List<Map<String,Object>> listAll() {
        String query = """
            SELECT 
                p.id,
                p.product_name,
                p.product_description,
                p.price,
                p.stock,
                p.image_url,
                p.is_available,
                a.name AS anime_name,
                pt.name AS product_type_name
            FROM product p
            JOIN anime a ON p.anime_id = a.id
            JOIN product_type pt ON p.product_type_id = pt.id;
        """;
        return jdbc.queryForList(query);
    }
}
