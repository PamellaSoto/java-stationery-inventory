package com.moonstationery.model;

public class Anime {
    private int id;
    private String name, slug;

    public Anime() {}
    public Anime(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }
    public Anime(int id, String name, String slug) {
        this.id = id;
        this.name = name;
        this.slug = slug;
    }

    // Getters
    public int getId() { 
        return id; 
    }
    public String getName() { 
        return name; 
    }
    public String getSlug() { 
        return slug; 
    }

    // Setters
    public void setId(int id) { 
        this.id = id; 
    }
    public void setName(String name) { 
        this.name = name; 
    }
    public String setSlug() { 
        return slug; 
    }
}