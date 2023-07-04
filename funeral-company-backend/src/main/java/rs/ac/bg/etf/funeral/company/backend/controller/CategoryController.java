package rs.ac.bg.etf.funeral.company.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.etf.funeral.company.backend.entity.Category;
import rs.ac.bg.etf.funeral.company.backend.service.CategoryService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(path = "/category")
    public Category saveCategory(@Valid @RequestBody Category category){
        return categoryService.saveCategory(category);
    }

    @GetMapping(path = "/categories")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }
}
