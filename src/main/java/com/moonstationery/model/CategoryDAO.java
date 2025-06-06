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
    DataSource dataSource;
	JdbcTemplate jdbc;

    @PostConstruct
	private void initialize() {
		jdbc = new JdbcTemplate(dataSource);
	}

    public boolean existsByName(String name) {
        String query = "SELECT COUNT(*) FROM product_type WHERE name = ?;";
        Integer result = jdbc.queryForObject(query, Integer.class, name);
        return result != null && result > 0;
    }

    public void insertCategory(Category category) {
        String query = "INSERT INTO product_type (name) VALUES (?);";
        jdbc.update(query, category.getName());
    }

    public List<Map<String,Object>> listAll() {
        String query = "SELECT id, name FROM product_type;";
        return jdbc.queryForList(query);
    }
}
