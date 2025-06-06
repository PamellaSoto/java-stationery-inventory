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
    DataSource dataSource;
	JdbcTemplate jdbc;

    @PostConstruct
	private void initialize() {
		jdbc = new JdbcTemplate(dataSource);
	}

    //Verify if name attribute already exists
    public boolean existsByName(String anime) {
        String query = "SELECT COUNT(*) FROM anime WHERE name = ?";
        Integer result = jdbc.queryForObject(query, Integer.class, anime);
        //Return a number (1 or 0) if the name exists on the table
        return result != null && result > 0;
    }

    //Post data into the table
    public void insertAnime(Anime anime) {
        String query = "INSERT INTO anime (name) VALUES (?);";
        jdbc.update(query, anime.getName());
    }

    public List<Map<String,Object>> listAll(){
        String query = "SELECT id, name FROM anime;";
        return jdbc.queryForList(query);
    }
}
