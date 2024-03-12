package com.digitalkitchen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.digitalkitchen.model.entities.*;
import com.digitalkitchen.model.request.RecipeRequestInfo;
import com.digitalkitchen.repository.IngredientRepository;
import com.digitalkitchen.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.digitalkitchen.model.request.RecipeRequest;
import com.digitalkitchen.model.response.RecipeResponse;
import com.digitalkitchen.repository.RecipeRepository;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.criteria.CriteriaBuilder;

@Slf4j
@Service
public class RecipeService {
    
    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;
    private TagRepository tagRepository;

    RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, TagRepository tagRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.tagRepository = tagRepository;
    }

    private Recipe buildRecipe(RecipeRequestInfo requestInfo) {
        final Recipe recipe = Recipe.builder()
                .name(requestInfo.getName())
                .author(requestInfo.getAuthor())
                .category(requestInfo.getCategory())
                .description(requestInfo.getDescription())
                .servings(requestInfo.getServings())
                .caloriesPerServing(requestInfo.getCaloriesPerServing())
                .notes(requestInfo.getNotes())
                .ingredients(requestInfo.getIngredients())
                .steps(requestInfo.getSteps())
                .tags(requestInfo.getTags())
                .build();

        List<RecipeIngredient> recipeIngredients = recipe.getIngredients();
        List<Step> steps = recipe.getSteps();
        List<RecipeTag> recipeTags = recipe.getTags();

        List<Ingredient> ingredients = saveIngredientsFromCreateRequest(recipeIngredients.stream()
                                                                                         .map(RecipeIngredient::getIngredient)
                                                                                         .collect(Collectors.toList()));

        List<Tag> tags = saveTagsFromCreateRequest(recipeTags.stream()
                                                             .map(RecipeTag::getTag)
                                                             .collect(Collectors.toList()));

        for (int i = 0; i < recipeIngredients.size(); i++) {
            RecipeIngredient recipeIngredient = recipeIngredients.get(i);
            Ingredient ingredient = ingredients.get(i);
            recipeIngredient.setRecipe(recipe);
            recipeIngredient.setIngredient(ingredient);
        }
        for (int i = 0; i < recipeTags.size(); i++) {
            RecipeTag recipeTag = recipeTags.get(i);
            Tag tag = tags.get(i);
            recipeTag.setRecipe(recipe);
            recipeTag.setTag(tag);
        }
        steps.forEach(step -> step.setRecipe(recipe));

        return recipe;
    }

    private List<Ingredient> saveIngredientsFromCreateRequest(List<Ingredient> ingredients) {
        List<Ingredient> outputIngredients = new ArrayList<>();

        ingredients.forEach(ingredient -> {
            try {
                outputIngredients.add(ingredientRepository.save(ingredient));
            } catch(DataIntegrityViolationException e) {
                outputIngredients.add(ingredientRepository.findByName(ingredient.getName()));
            }
        });
        return outputIngredients;
    }

    private List<Tag> saveTagsFromCreateRequest(List<Tag> tags) {
        List<Tag> outputTags = new ArrayList<>();

        tags.forEach(tag -> {
            try {
                outputTags.add(tagRepository.save(tag));
            } catch(DataIntegrityViolationException e) {
                outputTags.add(tagRepository.findByName(tag.getName()));
            }
        });
        return outputTags;
    }

    public RecipeResponse createRecipe(RecipeRequest request, boolean force) {
        //TODO::CHECK FOR DUPLICATE
        //TODO::PUT INTO A TRANSACTION SO THAT IF IT FAILS HALFWAY IN, THE INGREDIENTS OR TAGS AREN'T SAVED
        Recipe recipe = buildRecipe(request.getRecipes().get(0));
        recipeRepository.save(recipe);
        return ResponseMapper.buildRecipeResponse(recipe);
    }

    public List<Recipe> searchRecipes(Map<String, Object> searchParams) { 
        //return recipeRepository.searchRecipes(searchParams);
        throw new UnsupportedOperationException("Unimplemented method 'searchRecipes'");
    }

    public void updateRecipe(RecipeRequest request) {
        throw new UnsupportedOperationException("Unimplemented method 'updateRecipe'");
    }

    public void deleteRecipe(int recipeID) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteRecipe'");
    }
}
