package com.digitalkitchen.recipes.service;

import com.digitalkitchen.authors.model.entities.Author;
import com.digitalkitchen.authors.repository.AuthorRepository;
import com.digitalkitchen.recipes.enums.Category;
import com.digitalkitchen.recipes.enums.Measurement;
import com.digitalkitchen.recipes.model.entities.Ingredient;
import com.digitalkitchen.recipes.model.entities.Tag;
import com.digitalkitchen.recipes.repository.IngredientRepository;
import com.digitalkitchen.recipes.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormService {

    private final TagRepository tagRepository;
    private final IngredientRepository ingredientRepository;
    private final AuthorRepository authorRepository;

    public FormService(TagRepository tagRepository, IngredientRepository ingredientRepository, AuthorRepository authorRepository) {
        this.tagRepository = tagRepository;
        this.ingredientRepository = ingredientRepository;
        this.authorRepository = authorRepository;
    }

    public List<String> getAllCategories() {
        return Category.getAllCategoryStrings();
    }

    public List<String> getAllMeasurements() {
        return Measurement.getAllMeasurementStrings();
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public List<String> getAllTags() {
        return tagRepository.findAll().stream()
                .map(Tag::getName)
                .toList();
    }

    public List<String> getAllIngredients() {
        return ingredientRepository.findAll().stream()
                .map(Ingredient::getName)
                .toList();
    }
}
