package com.digitalkitchen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.digitalkitchen.model.entities.*;
import com.digitalkitchen.model.request.RecipeRequestInfo;
import com.digitalkitchen.model.request.RecipeSearchRequest;
import com.digitalkitchen.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.digitalkitchen.model.request.RecipeRequest;
import com.digitalkitchen.model.response.RecipeResponse;
import org.springframework.transaction.annotation.Transactional;

import static com.digitalkitchen.enums.ResponseStatus.NOT_FOUND;

@Slf4j
@Service
public class RecipeService {
    
    private final RecipeRepository recipeRepository;
    private final RecipeRepositoryExtension recipeRepositoryExtension;
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final StepRepository stepRepository;
    private final TagRepository tagRepository;
    private final RecipeTagRepository recipeTagRepository;

    RecipeService(RecipeRepository recipeRepository, RecipeRepositoryExtension recipeRepositoryExtension,
                  IngredientRepository ingredientRepository, RecipeIngredientRepository recipeIngredientRepository,
                  StepRepository stepRepository, TagRepository tagRepository, RecipeTagRepository recipeTagRepository) {
        this.recipeRepository = recipeRepository;
        this.recipeRepositoryExtension = recipeRepositoryExtension;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.stepRepository = stepRepository;
        this.tagRepository = tagRepository;
        this.recipeTagRepository = recipeTagRepository;
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

    private void buildRecipeIngredientList(Recipe recipe) {
        List<RecipeIngredient> recipeIngredients = recipe.getIngredients();
        List<Ingredient> ingredients = saveIngredients(recipeIngredients.stream()
                .map(RecipeIngredient::getIngredient)
                .map(Ingredient::getName)
                .collect(Collectors.toList()));

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
                .collect(Collectors.toList()));

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
        recipeToUpdate.setAuthor(requestInfo.getAuthor());
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
                .collect(Collectors.toList());

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
    public RecipeResponse createRecipe(RecipeRequest request, boolean force) {
        String name = request.getRecipes().get(0).getName();
        String author = request.getRecipes().get(0).getAuthor();
        Optional<com.digitalkitchen.model.entities.Recipe> duplicate = recipeRepository.findByNameAndAuthor(name, author);
        RecipeResponse response;
        if (duplicate.isPresent()) {
            response = ResponseMapper.buildRecipeDuplicateResponse(duplicate.get());
        } else {
            com.digitalkitchen.model.entities.Recipe recipe = processRecipeRequest(request.getRecipes().get(0));
            recipe = recipeRepository.save(recipe);
            response = ResponseMapper.buildRecipeCreationResponse(recipe);
        }
        return response;
    }

    public RecipeResponse searchRecipes(RecipeSearchRequest searchParams) {
        List<com.digitalkitchen.model.entities.Recipe> recipes = recipeRepositoryExtension.searchRecipes(searchParams);
        return ResponseMapper.buildRecipeSearchResponse(recipes);
    }

    public RecipeResponse retrieveRecipe(String idString) {
        int recipeId = Integer.parseInt(idString);
        List<com.digitalkitchen.model.entities.Recipe> recipes = new ArrayList<>();
        Optional<com.digitalkitchen.model.entities.Recipe> recipeOptional = recipeRepository.findById(recipeId);
        recipes.add(recipeOptional.get());
        return ResponseMapper.buildRecipeSearchResponse(recipes);
    }

    @Transactional
    public RecipeResponse updateRecipe(RecipeRequest request) {
        int recipeId = request.getRecipes().get(0).getId();
        Optional<Recipe> recipeOpt = recipeRepository.findById(recipeId);
        if (recipeOpt.isPresent()) {
            Recipe recipe = recipeOpt.get();
            setUpdatedRecipeData(recipe, request.getRecipes().get(0));
            Recipe updatedRecipe = recipeRepository.save(recipe);
            return ResponseMapper.buildRecipeCreationResponse(updatedRecipe);
        } else {
            return RecipeResponse.builder().status(NOT_FOUND).build();
        }
    }

    @Transactional
    public void deleteRecipe(int recipeId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        recipeOptional.ifPresent(recipeRepository::delete);
    }
}
