package com.moonstationery.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    CategoryDAO categoryDAO;

     // All category names must be written in capitalize formatting
    public boolean insertCategory(Category category){
        if (categoryDAO.existsByName(category.getName())) {
            return false;
        }
        categoryDAO.insertCategory(category);
        return true;
    }

    public List<Map<String,Object>> listAll(){
        return categoryDAO.listAll();
    }
}
