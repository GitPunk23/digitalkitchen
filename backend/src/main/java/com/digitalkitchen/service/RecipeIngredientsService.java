package com.digitalkitchen.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalkitchen.entities.RecipeIngredients;
import com.digitalkitchen.repository.RecipeIngredientsRepository;

@Service
public class RecipeIngredientsService {

    @Autowired
    private RecipeIngredientsRepository repository;
    
    public List<RecipeIngredients> getAllRecipeIngredients() {
        return repository.findAll();
    }
    
    public Optional<RecipeIngredients> getRecipeIngredientById(int id) {
        return repository.findById(id);
    }

    public RecipeIngredients addRecipeIngredient(RecipeIngredients ingredient) {
        return repository.save(ingredient);
    }

    public void updateRecipeIngredient(RecipeIngredients ingredient) {
        repository.save(ingredient);
    }

    public void deleteRecipeIngredientById(int id) {
        repository.deleteById(id);
    }

}
