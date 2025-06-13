package com.moonstationery.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonstationery.model.Product;
import com.moonstationery.repositories.ProductDAO;

@Service
public class ProductService {
    @Autowired
    ProductDAO productDAO;

    public void insertProduct(Product product) {
        productDAO.insertProduct(product);
    }
    
    public void editProduct(Product product) {
        productDAO.editProduct(product);
    }

    public void switchAvailable(Integer id) {
        productDAO.switchAvailable(id);
    }
    public void deleteProduct(Integer id) {
        productDAO.deleteProduct(id);
    }

    public Product getAvailableProductById(Integer id) {
        return productDAO.getAvailableProductById(id);
    }

    public Product getProductById(Integer id) {
        return productDAO.getProductById(id);
    }

    public List<Map<String, Object>> getProductsByCategory(Integer categoryId) {
        return productDAO.getProductsByCategory(categoryId);
    }

    public List<Map<String, Object>> getProductsByAnime(Integer animeId) {
        return productDAO.getProductsByAnime(animeId);
    }
    public Integer getInventoryQuantity() {
        return productDAO.getInventoryQuantity();
    }
    //For admin-side view only
    public List<Map<String,Object>> listAll(){
        return productDAO.listAll();
    }

    //For client-side
    public List<Map<String,Object>> listAllAvailable(){
        return productDAO.listAllAvailable();
    }
}
