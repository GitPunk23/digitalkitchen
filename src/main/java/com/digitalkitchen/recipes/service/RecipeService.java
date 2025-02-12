package com.digitalkitchen.recipes.service;

import com.digitalkitchen.authors.model.entities.Author;
import com.digitalkitchen.authors.repository.AuthorRepository;
import com.digitalkitchen.recipes.model.entities.*;
import com.digitalkitchen.recipes.model.request.RecipeRequest;
import com.digitalkitchen.recipes.model.request.RecipeRequestInfo;
import com.digitalkitchen.recipes.model.request.RecipeSearchRequest;
import com.digitalkitchen.recipes.model.response.RecipeResponse;
import com.digitalkitchen.recipes.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.digitalkitchen.enums.ResponseStatus.NOT_FOUND;
import static com.digitalkitchen.recipes.service.ResponseMapper.*;

@Service
public class RecipeService {
    
    private final RecipeRepository recipeRepository;
    private final RecipeRepositoryExtension recipeRepositoryExtension;
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final StepRepository stepRepository;
    private final TagRepository tagRepository;
    private final RecipeTagRepository recipeTagRepository;
    private final AuthorRepository authorRepository;

    RecipeService(RecipeRepository recipeRepository, RecipeRepositoryExtension recipeRepositoryExtension,
                  IngredientRepository ingredientRepository, RecipeIngredientRepository recipeIngredientRepository,
                  StepRepository stepRepository, TagRepository tagRepository, RecipeTagRepository recipeTagRepository,
                  AuthorRepository authorRepository) {
        this.recipeRepository = recipeRepository;
        this.recipeRepositoryExtension = recipeRepositoryExtension;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.stepRepository = stepRepository;
        this.tagRepository = tagRepository;
        this.recipeTagRepository = recipeTagRepository;
        this.authorRepository = authorRepository;
    }

    private Author getAuthor(String authorName) {
        Optional<Author> authorOpt = authorRepository.findByName(authorName);
        return authorOpt.orElseGet(() -> Author.builder().name(authorName).build());
    }

    private Recipe buildRecipe(RecipeRequestInfo requestInfo) {
        return Recipe.builder()
                .name(requestInfo.getName())
                .author(getAuthor(requestInfo.getAuthorName()))
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

    private void buildRecipeIngredientList(Recipe recipe) {
        List<RecipeIngredient> recipeIngredients = recipe.getIngredients();
        List<Ingredient> ingredients = saveIngredients(recipeIngredients.stream()
                .map(RecipeIngredient::getIngredient)
                .map(Ingredient::getName)
                .toList());

        for (int i = 0; i < recipeIngredients.size(); i++) {
            RecipeIngredient recipeIngredient = recipeIngredients.get(i);
            Ingredient ingredient = ingredients.get(i);
            recipeIngredient.setRecipe(recipe);
            recipeIngredient.setIngredient(ingredient);
        }
    }

    private void buildRecipeTagList(Recipe recipe) {
        List<RecipeTag> recipeTags = recipe.getTags();
        List<Tag> tags = saveTags(recipeTags.stream()
                .map(RecipeTag::getTag)
                .map(Tag::getName)
                .toList());

        for (int i = 0; i < recipeTags.size(); i++) {
            RecipeTag recipeTag = recipeTags.get(i);
            Tag tag = tags.get(i);
            recipeTag.setRecipe(recipe);
            recipeTag.setTag(tag);
        }
    }

    private void buildStepsList(Recipe recipe) {
        List<Step> steps = recipe.getSteps();
        steps.forEach(step -> step.setRecipe(recipe));
    }

    private Recipe processRecipeRequest(RecipeRequestInfo recipeRequestInfo) {
        Recipe recipe = buildRecipe(recipeRequestInfo);
        buildRecipeIngredientList(recipe);
        buildRecipeTagList(recipe);
        buildStepsList(recipe);

        return recipe;
    }

    /**
     * Saves a list of ingredients to the database. If an ingredient already exists, the record is retrieved.
     * @param ingredientNames list of ingredient names to save to the database
     * @return the list of saved ingredients with the id field populated
     */
    private List<Ingredient> saveIngredients(List<String> ingredientNames) {
        List<Ingredient> outputIngredients = new ArrayList<>();

        ingredientNames.forEach(name -> {
            Optional<Ingredient> foundIngredient = ingredientRepository.findByName(name);
            if (foundIngredient.isPresent()) {
                outputIngredients.add(foundIngredient.get());
            } else {
                outputIngredients.add(ingredientRepository.save(Ingredient.builder()
                        .name(name)
                        .build()));
        }});
        return outputIngredients;
    }

    /**
     * Saves a list of tags to the database. If a tag already exists, the record is retrieved.
     * @param tagNames list of tags to save to the database
     * @return the list of saved tags with the id field populated
     */
    private List<Tag> saveTags(List<String> tagNames) {
        List<Tag> outputTags = new ArrayList<>();

        tagNames.forEach(name -> {
            Optional<Tag> foundTag = tagRepository.findByName(name);
            if (foundTag.isPresent()) {
                outputTags.add(foundTag.get());
            } else {
                outputTags.add(tagRepository.save(Tag.builder()
                        .name(name)
                        .build()));
            }});
        return outputTags;
    }

    private void setUpdatedRecipeData(Recipe recipeToUpdate, RecipeRequestInfo requestInfo) {
        recipeToUpdate.setCategory(requestInfo.getCategory());
        recipeToUpdate.setName(requestInfo.getName());
        recipeToUpdate.setDescription(requestInfo.getDescription());
        recipeToUpdate.setServings(requestInfo.getServings());
        recipeToUpdate.setCaloriesPerServing(requestInfo.getCaloriesPerServing());
        recipeToUpdate.setNotes(requestInfo.getNotes());
        recipeToUpdate.setAuthor(getAuthor(requestInfo.getAuthorName()));
        updateAllRecipeIngredients(recipeToUpdate.getIngredients(), requestInfo.getIngredients());
        updateAllSteps(recipeToUpdate.getSteps(), requestInfo.getSteps());
        updateAllRecipeTags(recipeToUpdate.getTags(), requestInfo.getTags());
    }

    public void updateAllRecipeIngredients(List<RecipeIngredient> currentRecipeIngredients, List<RecipeIngredient> newRecipeIngredients) {
        RecipeIngredient currentRecipeIngredient;
        for (RecipeIngredient newRecipeIngredient : newRecipeIngredients) {
            Optional<RecipeIngredient> recipeOpt = currentRecipeIngredients.stream()
                    .filter(recipeIngredient -> recipeIngredient.getIngredient().equals(newRecipeIngredient.getIngredient()))
                    .findFirst();

            if (recipeOpt.isPresent()) {
                currentRecipeIngredient = recipeOpt.get();
                currentRecipeIngredient.setMeasurement(newRecipeIngredient.getMeasurement());
                currentRecipeIngredient.setQuantity(newRecipeIngredient.getQuantity());
                currentRecipeIngredient.setNotes(newRecipeIngredient.getNotes());
                recipeIngredientRepository.save(currentRecipeIngredient);
            } else {
                recipeIngredientRepository.save(newRecipeIngredient);
            }
        }
        List<RecipeIngredient> recipeIngredientsToDelete = currentRecipeIngredients.stream()
                .filter(recipeIngredient -> newRecipeIngredients.stream()
                        .noneMatch(newIngredient -> newIngredient.getIngredient().equals(recipeIngredient.getIngredient())))
                .toList();

        for (RecipeIngredient toDelete : recipeIngredientsToDelete) {
            recipeIngredientRepository.delete(toDelete);
        }
    }

    public void updateAllRecipeTags(List<RecipeTag> currentRecipeTags, List<RecipeTag> newRecipeTags) {
        if (!newRecipeTags.isEmpty()) {
            RecipeTag currentTag;
            RecipeTag newTag;
            for (int i = 0; i < newRecipeTags.size(); i++) {
                newTag = newRecipeTags.get(i);
                if (i < currentRecipeTags.size()) {
                    currentTag = currentRecipeTags.get(i);
                    currentTag.setTag(newTag.getTag());
                    recipeTagRepository.save(currentTag);
                } else {
                    recipeTagRepository.save(newTag);
                }
            }
            for (int i = newRecipeTags.size(); i < currentRecipeTags.size(); i++) {
                RecipeTag tagToDelete = currentRecipeTags.get(i);
                recipeTagRepository.delete(tagToDelete);
            }
        }
    }

    public void updateAllSteps(List<Step> currentSteps, List<Step> newSteps) {
        Step currentStep;
        Step newStep;
        for (int i = 0; i < newSteps.size(); i++) {
            newStep = newSteps.get(i);
            if (i < currentSteps.size()) {
                currentStep = currentSteps.get(i);
                currentStep.setDescription(newStep.getDescription());
                stepRepository.save(currentStep);
            } else {
                stepRepository.save(newStep);
            }
        }

        for (int i = newSteps.size(); i < currentSteps.size(); i++) {
            Step stepToDelete = currentSteps.get(i);
            stepRepository.delete(stepToDelete);
        }
    }

    @Transactional
    public RecipeResponse createRecipe(RecipeRequest request) {
        String recipeName = request.getRecipes().get(0).getName();
        Optional<Recipe> duplicate = recipeRepository.findByName(recipeName);
        RecipeResponse response;
        if (duplicate.isPresent()) {
            response = buildRecipeDuplicateResponse(duplicate.get());
        } else {
            Recipe recipe = processRecipeRequest(request.getRecipes().get(0));
            recipe = recipeRepository.save(recipe);
            response = buildRecipeCreationResponse(recipe);
        }
        return response;
    }

    public RecipeResponse searchRecipes(RecipeSearchRequest searchParams) {
        List<Recipe> recipes = recipeRepositoryExtension.searchRecipes(searchParams);
        return buildRecipeSearchResponse(recipes);
    }

    public RecipeResponse retrieveRecipe(String idString) {
        Long recipeId = Long.parseLong(idString);
        Optional<Recipe> optRecipe = recipeRepository.findById(recipeId);
        List<Recipe> recipes = Collections.singletonList(optRecipe.orElse(null));
        return buildRecipeSearchResponse(recipes);
    }

    @Transactional
    public RecipeResponse updateRecipe(RecipeRequest request) {
        Long recipeId = request.getRecipes().get(0).getId();
        Optional<Recipe> recipeOpt = recipeRepository.findById(recipeId);
        if (recipeOpt.isPresent()) {
            Recipe recipe = recipeOpt.get();
            setUpdatedRecipeData(recipe, request.getRecipes().get(0));
            Recipe updatedRecipe = recipeRepository.save(recipe);
            return buildRecipeCreationResponse(updatedRecipe);
        } else {
            return RecipeResponse.builder().status(NOT_FOUND).build();
        }
    }

    @Transactional
    public void deleteRecipe(Long recipeId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        recipeOptional.ifPresent(recipeRepository::delete);
    }
}
