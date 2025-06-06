package com.moonstationery.model;

public class Category {
    private int id;
    private String name;

    public Category() {}
    public Category(String name) {
        this.name = name;
    }
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
}