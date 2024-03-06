package com.digitalkitchen.service;
import com.digitalkitchen.model.entities.Category;
import com.digitalkitchen.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    public Optional<Category> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void updateCategory(Category category) {
        categoryRepository.save(category);
    }

    public void deleteCategoryById(int id) {
        categoryRepository.deleteById(id);
    }
}
