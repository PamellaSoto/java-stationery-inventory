package com.moonstationery.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    public boolean insertCategory(Category category) {
        if (categoryDAO.existsByName(category.getName())) {
            return false;
        }
        categoryDAO.insertCategory(category);
        return true;
    }

    public Category getCategoryById(Integer id) {
        return categoryDAO.getCategoryById(id);
    }

    public Category getCategoryBySlug(String slug) {
        return categoryDAO.getCategoryBySlug(slug);
    }

    public List<Map<String, Object>> listAll() {
        return categoryDAO.listAll();
    }
}
