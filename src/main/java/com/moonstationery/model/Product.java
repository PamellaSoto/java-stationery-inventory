package com.moonstationery.model;

public class Product {
    private Integer id, 
                    stock,
                    anime_id,
                    product_type_id;
    private Float price;
    private String product_name, 
                   product_description,
                   image_url;
    private Boolean is_available;

    public Product() {}

    public Product(Integer anime_id, String image_url, Boolean is_available, Float price, String product_description, String product_name, Integer product_type_id, Integer stock) {
        this.anime_id = anime_id;
        this.image_url = image_url;
        this.is_available = is_available;
        this.price = price;
        this.product_description = product_description;
        this.product_name = product_name;
        this.product_type_id = product_type_id;
        this.stock = stock;
    }

    public Product(Integer id, Integer anime_id, String image_url, Boolean is_available, Float price, String product_description, String product_name, Integer product_type_id, Integer stock) {
        this.id = id;
        this.anime_id = anime_id;
        this.image_url = image_url;
        this.is_available = is_available;
        this.price = price;
        this.product_description = product_description;
        this.product_name = product_name;
        this.product_type_id = product_type_id;
        this.stock = stock;
    }
    public Product(Integer id, Boolean is_available) {
        this.id = id;
        this.is_available = is_available;
    }
    // Getters
    public Integer getId() {
        return id;
    }
    public Integer getStock() {
        return stock;
    }
    public Float getPrice() {
        return price;
    }
    public Integer getAnime_id() {
        return anime_id;
    }
    public Integer getProduct_type_id() {
        return product_type_id;
    }
    public String getProduct_name() {
        return product_name;
    }
    public String getProduct_description() {
        return product_description;
    }
    public String getImage_url() {
        return image_url;
    }
    public Boolean getIs_available() {
        return is_available;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
    public void setAnime_id(Integer anime_id) {
        this.anime_id = anime_id;
    }
    public void setProduct_type_id(Integer product_type_id) {
        this.product_type_id = product_type_id;
    }   
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    public void setIs_available(Boolean is_available) {
        this.is_available = is_available;
    }
}
