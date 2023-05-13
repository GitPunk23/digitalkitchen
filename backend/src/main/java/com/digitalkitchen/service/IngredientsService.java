package com.digitalkitchen.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalkitchen.entities.Ingredients;
import com.digitalkitchen.repository.IngredientsRepository;

@Service
public class IngredientsService {

    @Autowired
    private IngredientsRepository repository;
    
    public List<Ingredients> getAllIngredients() {
        return repository.findAll();
    }
    
    public Optional<Ingredients> getIngredientById(int id) {
        return repository.findById(id);
    }

    public Ingredients addIngredient(Ingredients ingredient) {
        return repository.save(ingredient);
    }

    public void updateIngredient(Ingredients ingredient) {
        repository.save(ingredient);
    }

    public void deleteIngredientById(int id) {
        repository.deleteById(id);
    }

}

