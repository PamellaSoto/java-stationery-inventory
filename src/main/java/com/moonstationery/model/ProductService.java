package com.moonstationery.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void deleteProduct(Integer id) {
        productDAO.deleteProduct(id);
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
