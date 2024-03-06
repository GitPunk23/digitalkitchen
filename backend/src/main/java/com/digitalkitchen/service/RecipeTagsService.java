package com.digitalkitchen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalkitchen.model.entities.RecipeTags;
import com.digitalkitchen.model.entities.Recipes;
import com.digitalkitchen.model.entities.Steps;
import com.digitalkitchen.model.entities.Tags;
import com.digitalkitchen.repository.RecipeTagsRepository;

@Service
public class RecipeTagsService {

    @Autowired
    private RecipeTagsRepository repository;
    @Autowired
    private TagsService tagsService;
    
    public List<RecipeTags> getAllRecipeTags() {
        return repository.findAll();
    }

    public List<RecipeTags> getAllRecipeTagsByRecipe(Recipes recipe) {
        return repository.findAllByRecipe(recipe);
    }
    
    public Optional<RecipeTags> getRecipeTagById(int id) {
        return repository.findById(id);
    }

    public Optional<RecipeTags> getRecipeTagByName(String tag) {
        return repository.getByTag(tag);
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

    public void updateAllRecipeTags(List<RecipeTags> tagsList) {
        if (tagsList.isEmpty()) {
            return;
        } else {
            List<RecipeTags> currentTags = repository.findAllByRecipe(tagsList.get(0).getRecipe());
            RecipeTags currentTag;
            RecipeTags newTag;
            
            for (int i = 0; i < tagsList.size(); i++) {
                newTag = tagsList.get(i);
                if (i < currentTags.size()) {
                    currentTag = currentTags.get(i);
                    currentTag.setTag(newTag.getTag());
                    repository.save(currentTag);
                } else {
                    repository.save(newTag);
                }
            }

            for (int i = tagsList.size(); i < currentTags.size(); i++) {
                RecipeTags tagToDelete = currentTags.get(i);
                repository.delete(tagToDelete);
            }
        }
    }

    public void deleteRecipeTagById(int id) {
        repository.deleteById(id);
    }

    public void delete(RecipeTags tag) {
        repository.delete(tag);
    }

    public List<RecipeTags> getRecipeTagsFromJSON(Recipes recipe, List<String> list) {
        ArrayList<RecipeTags> tagsList = new ArrayList<>();
        
        for (String value : list) {
            Optional<Tags> tag = tagsService.getTagByName(value);
            if (tag.isEmpty()) {
                tagsList.add(new RecipeTags(recipe, tagsService.addTag(new Tags(value))));
            } else {
                tagsList.add(new RecipeTags(recipe, tag.get()));
            }
        }
        return tagsList;
    }
    

}
