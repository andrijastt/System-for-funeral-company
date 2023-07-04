package rs.ac.bg.etf.funeral.company.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.funeral.company.backend.entity.Category;
import rs.ac.bg.etf.funeral.company.backend.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategoryName(Category category) {
        Category categoryTemp = categoryRepository.findById(category.getCategoryID()).get();
        categoryTemp.setName(category.getName());
        return categoryRepository.save(categoryTemp);
    }
}
