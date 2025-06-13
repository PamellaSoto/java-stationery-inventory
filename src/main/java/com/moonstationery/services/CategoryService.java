package com.moonstationery.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonstationery.model.Category;
import com.moonstationery.repositories.CategoryDAO;

@Service
public class CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    public boolean insertCategory(Category category) {
        // First letter must be always capitalized
        String typeName = category.getName();
        typeName = typeName.substring(0, 1).toUpperCase() + typeName.substring(1).toLowerCase();
        category.setName(typeName);

        if (categoryDAO.existsByName(category.getName())) {
            return false;
        }
        categoryDAO.insertCategory(category);
        return true;
    }

    public boolean deleteCategoryType(Integer id) {
        return categoryDAO.deleteCategoryType(id);
    }

    public Category getCategoryById(Integer id) {
        return categoryDAO.getCategoryById(id);
    }

    public Category getCategoryBySlug(String slug) {
        return categoryDAO.getCategoryBySlug(slug);
    }

    public List<Map<String, Object>> listAllWithProductCount(Boolean dashboard) {
        return categoryDAO.listAllWithProductCount(dashboard);
    }
}
