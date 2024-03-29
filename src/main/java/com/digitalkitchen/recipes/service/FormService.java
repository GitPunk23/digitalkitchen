package com.digitalkitchen.recipes.service;

import com.digitalkitchen.recipes.enums.Category;
import com.digitalkitchen.recipes.enums.Measurement;
import com.digitalkitchen.recipes.model.entities.Ingredient;
import com.digitalkitchen.recipes.model.entities.Tag;
import com.digitalkitchen.recipes.repository.IngredientRepository;
import com.digitalkitchen.recipes.repository.RecipeRepository;
import com.digitalkitchen.recipes.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormService {

    private final RecipeRepository recipeRepository;
    private final TagRepository tagRepository;
    private final IngredientRepository ingredientRepository;

    public FormService(RecipeRepository recipeRepository, TagRepository tagRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.tagRepository = tagRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<String> getAllCategories() {
        return Category.getAllCategoryStrings();
    }

    public List<String> getAllMeasurements() {
        return Measurement.getAllMeasurementStrings();
    }

    public List<String> getAllAuthors() {
        return recipeRepository.getAllAuthors();
    }

    public List<String> getAllTags() {
        return tagRepository.findAll().stream()
                .map(Tag::getName)
                .collect(Collectors.toList());
    }

    public List<String> getAllIngredients() {
        return ingredientRepository.findAll().stream()
                .map(Ingredient::getName)
                .collect(Collectors.toList());
    }
}
