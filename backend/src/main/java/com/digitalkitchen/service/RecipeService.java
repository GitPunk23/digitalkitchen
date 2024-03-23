package com.digitalkitchen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.digitalkitchen.enums.Category;
import com.digitalkitchen.model.entities.*;
import com.digitalkitchen.model.request.RecipeRequestInfo;
import com.digitalkitchen.model.request.RecipeSearchRequest;
import com.digitalkitchen.repository.IngredientRepository;
import com.digitalkitchen.repository.RecipeRepositoryExtension;
import com.digitalkitchen.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.digitalkitchen.model.request.RecipeRequest;
import com.digitalkitchen.model.response.RecipeResponse;
import com.digitalkitchen.repository.RecipeRepository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class RecipeService {
    
    private final RecipeRepository recipeRepository;
    private final RecipeRepositoryExtension recipeRepositoryExtension;
    private final IngredientRepository ingredientRepository;
    private final TagRepository tagRepository;

    RecipeService(RecipeRepository recipeRepository, RecipeRepositoryExtension recipeRepositoryExtension, IngredientRepository ingredientRepository, TagRepository tagRepository) {
        this.recipeRepository = recipeRepository;
        this.recipeRepositoryExtension = recipeRepositoryExtension;
        this.ingredientRepository = ingredientRepository;
        this.tagRepository = tagRepository;
    }

    private Recipe buildRecipe(RecipeRequestInfo requestInfo) {
        return Recipe.builder()
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
    }

    private Recipe processRecipeRequest(RecipeRequestInfo requestInfo) {
        Recipe recipe = buildRecipe(requestInfo);
        List<RecipeIngredient> recipeIngredients = recipe.getIngredients();
        List<Step> steps = recipe.getSteps();
        List<RecipeTag> recipeTags = recipe.getTags();
        List<Ingredient> ingredients = saveIngredients(recipeIngredients.stream()
                                                                        .map(RecipeIngredient::getIngredient)
                                                                        .collect(Collectors.toList()));
        List<Tag> tags = saveTags(recipeTags.stream()
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

    /**
     * Saves a list of ingredients to the database. If an ingredient already exists, the record is retrieved.
     * @param ingredients list of ingredients to save to the database
     * @return the list of saved ingredients with the id field populated
     */
    private List<Ingredient> saveIngredients(List<Ingredient> ingredients) {
        List<Ingredient> outputIngredients = new ArrayList<>();

        ingredients.forEach(ingredient -> {
            Optional<Ingredient> foundIngredient = ingredientRepository.findByName(ingredient.getName());
            if (foundIngredient.isPresent()) {
                outputIngredients.add(foundIngredient.get());
            } else {
                outputIngredients.add(ingredientRepository.save(ingredient));
        }});
        return outputIngredients;
    }

    /**
     * Saves a list of tags to the database. If a tag already exists, the record is retrieved.
     * @param tags list of tags to save to the database
     * @return the list of saved tags with the id field populated
     */
    private List<Tag> saveTags(List<Tag> tags) {
        List<Tag> outputTags = new ArrayList<>();

        tags.forEach(tag -> {
            Optional<Tag> foundTag = tagRepository.findByName(tag.getName());
            if (foundTag.isPresent()) {
                outputTags.add(foundTag.get());
            } else {
                outputTags.add(tagRepository.save(tag));
            }});
        return outputTags;
    }

    @Transactional
    public RecipeResponse createRecipe(RecipeRequest request, boolean force) {
        String name = request.getRecipes().get(0).getName();
        String author = request.getRecipes().get(0).getAuthor();
        Optional<Recipe> duplicate = recipeRepository.findByNameAndAuthor(name, author);
        if (duplicate.isPresent()) {
            return ResponseMapper.buildRecipeDuplicateResponse(duplicate.get());
        } else {
            Recipe recipe = processRecipeRequest(request.getRecipes().get(0));
            recipeRepository.save(recipe);
            return ResponseMapper.buildRecipeCreationResponse(recipe);
        }
    }

    public RecipeResponse searchRecipes(RecipeSearchRequest searchParams) {
        List<Recipe> recipes = recipeRepositoryExtension.searchRecipes(searchParams);
        return ResponseMapper.buildRecipeSearchResponse(recipes);
    }

    public void updateRecipe(RecipeRequest request) {
        throw new UnsupportedOperationException("Unimplemented method 'updateRecipe'");
    }

    public void deleteRecipe(int recipeID) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteRecipe'");
    }



}
