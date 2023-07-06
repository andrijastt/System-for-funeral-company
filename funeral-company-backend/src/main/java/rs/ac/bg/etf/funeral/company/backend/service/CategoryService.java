package rs.ac.bg.etf.funeral.company.backend.service;

import rs.ac.bg.etf.funeral.company.backend.entity.Category;

import java.util.List;

public interface CategoryService {

    public Category saveCategory(Category category);
    public List<Category> getAllCategories();
    public Category updateCategoryName(Category category);
    public String deleteCategoryById(Long categoryID);

    public List<Category> findByNameContaining(String name);
}
