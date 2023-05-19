package com.digitalkitchen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalkitchen.entities.RecipeTags;
import com.digitalkitchen.entities.Recipes;
import com.digitalkitchen.repository.RecipeTagsRepository;

@Service
public class RecipeTagsService {

    @Autowired
    private RecipeTagsRepository repository;
    
    public List<RecipeTags> getAllRecipeTags() {
        return repository.findAll();
    }

    public List<RecipeTags> getAllRecipeTagsByRecipe(Recipes recipe) {
        return repository.findAllByRecipe(recipe);
    }
    
    public Optional<RecipeTags> getRecipeTagById(int id) {
        return repository.findById(id);
    }

    public RecipeTags addRecipeTag(RecipeTags tag) {
        return repository.save(tag);
    }

    public List<RecipeTags> addRecipeTags(List<RecipeTags> tags) {
        List<RecipeTags> newTags = new ArrayList();
        RecipeTags newTag;

        for (int i = 0; i < tags.size(); i++) {
            newTag = this.addRecipeTag(tags.get(i));
            newTags.add(newTag);
        }
        return newTags;
    }

    public void updateRecipeTag(RecipeTags tag) {
        repository.save(tag);
    }

    public void deleteRecipeTagById(int id) {
        repository.deleteById(id);
    }

}
