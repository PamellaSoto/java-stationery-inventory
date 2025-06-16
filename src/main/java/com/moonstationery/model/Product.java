package com.moonstationery.model;

import java.math.BigDecimal;

public class Product {
    private Integer id, 
                    stock,
                    anime_id,
                    product_type_id;
    private BigDecimal price;
    private String product_name, 
                   product_description,
                   image_url,
                   anime_name,
                   product_type_name,
                   anime_slug, 
                   product_type_slug;

    private Boolean is_available;

    public Product() {}

    //With id
    public Product(Integer id, Integer anime_id, String anime_name, String image_url, Boolean is_available, BigDecimal price, String product_description, String product_name, Integer product_type_id, String product_type_name, Integer stock, String anime_slug, String product_type_slug) {
        this.id = id;
        this.anime_id = anime_id;
        this.anime_name = anime_name;
        this.image_url = image_url;
        this.is_available = is_available;
        this.price = price;
        this.product_description = product_description;
        this.product_name = product_name;
        this.product_type_id = product_type_id;
        this.product_type_name = product_type_name;
        this.stock = stock;
        this.anime_slug = anime_slug;
        this.product_type_slug = product_type_slug;
    }
    
    //Without id
    public Product(Integer anime_id, String anime_name, String image_url, Boolean is_available, BigDecimal price, String product_description, String product_name, Integer product_type_id, String product_type_name, Integer stock, String anime_slug, String product_type_slug) {
        this.anime_id = anime_id;
        this.anime_name = anime_name;
        this.image_url = image_url;
        this.is_available = is_available;
        this.price = price;
        this.product_description = product_description;
        this.product_name = product_name;
        this.product_type_id = product_type_id;
        this.product_type_name = product_type_name;
        this.stock = stock;
        this.anime_slug = anime_slug;
        this.product_type_slug = product_type_slug;
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

    public BigDecimal getPrice() {
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

        public String getAnime_name() {
        return anime_name;
    }

    public String getProduct_type_name() {
        return product_type_name;
    }
    
    public String getAnime_slug() {
        return anime_slug;
    }
    
    public String getProduct_type_slug() {
        return product_type_slug;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setPrice(BigDecimal price) {
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

    public void setAnime_name(String anime_name) {
        this.anime_name = anime_name;
    }

    public void setProduct_type_name(String product_type_name) {
        this.product_type_name = product_type_name;
    }

    public void setAnime_slug(String anime_slug) {
        this.anime_slug = anime_slug;
    }

    public void setProduct_type_slug(String product_type_slug) {
        this.product_type_slug = product_type_slug;
    }
}
