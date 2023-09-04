package com.digitalkitchen.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalkitchen.entities.RecipeIngredients;
import com.digitalkitchen.entities.Recipes;
import com.digitalkitchen.repository.RecipeIngredientsRepository;

@Service
public class RecipeIngredientsService {

    @Autowired
    private RecipeIngredientsRepository repository;
    
    @Autowired
    private IngredientsService ingredientsService;

    @Autowired
    private MeasurementsService measurementsService;
    
    public List<RecipeIngredients> getAllRecipeIngredients() {
        return repository.findAll();
    }

    public List<RecipeIngredients> getAllRecipeIngredientsByRecipe(Recipes recipe) {
        return repository.findByRecipe(recipe);
    }
    
    public Optional<RecipeIngredients> getRecipeIngredientById(int id) {
        return repository.findById(id);
    }

    public Optional<RecipeIngredients> getRecipeIngredientByName(String ingredient) {
        return repository.findByIngredient(ingredient);
    }

    public List<RecipeIngredients> getRecipeIngredientsFromJSON(Recipes recipe, List<Map<String, Object>> ingredientMap) {
        ArrayList<RecipeIngredients> recipeIngredientsList = new ArrayList<>();

        for (int index = 0; index < ingredientMap.size(); index++) {

            recipeIngredientsList.add(new RecipeIngredients(recipe, 
                ingredientsService.getIngredientByName((String) ingredientMap.get(index).get("ingredient")).get(),
                measurementsService.getMeasurementByName((String) ingredientMap.get(index).get("measurement")).get(),
                Float.parseFloat(((Object) ingredientMap.get(index).get("quantity")).toString()),
                (String) ingredientMap.get(index).get("notes")));

        }

        return recipeIngredientsList; 
    }

    public RecipeIngredients addRecipeIngredient(RecipeIngredients ingredient) {
        return repository.save(ingredient);
    }

    public List<RecipeIngredients> addRecipeIngredients(List<RecipeIngredients> recipeIngredients) {
        List<RecipeIngredients> newRecipeIngredients = new ArrayList();
        RecipeIngredients recipeIngredient;

        for (int i = 0; i < recipeIngredients.size(); i++) {
            recipeIngredient = this.addRecipeIngredient(recipeIngredients.get(i));
            newRecipeIngredients.add(recipeIngredient);
        }
        return repository.saveAll(newRecipeIngredients);
    }

    public void updateRecipeIngredient(RecipeIngredients ingredient) {
        repository.save(ingredient);
    }

    public void updateAllRecipeIngredients(List<RecipeIngredients> newRecipeIngredients) {
        List<RecipeIngredients> currentRecipeIngredients = getAllRecipeIngredientsByRecipe(newRecipeIngredients.get(0).getRecipe());
        RecipeIngredients currentIngredient;

        for (RecipeIngredients newRecipeIngredient : newRecipeIngredients) {
            Optional<RecipeIngredients> matchingIngredient = currentRecipeIngredients.stream()
                    .filter(currentRecipeIngredient -> currentRecipeIngredient.getIngredient().equals(newRecipeIngredient.getIngredient()))
                    .findFirst();

            if (matchingIngredient.isPresent()) {
                currentIngredient = matchingIngredient.get();
                currentIngredient.setMeasurement(newRecipeIngredient.getMeasurement());
                currentIngredient.setQuantity(newRecipeIngredient.getQuantity());
                currentIngredient.setNotes(newRecipeIngredient.getNotes());
                repository.save(currentIngredient);
            } else {
                repository.save(newRecipeIngredient);
            }
        }
        List<RecipeIngredients> recipeIngredientsToDelete = currentRecipeIngredients.stream()
            .filter(currentRecipeIngredient -> newRecipeIngredients.stream()
            .noneMatch(newIngredient -> newIngredient.getIngredient().equals(currentRecipeIngredient.getIngredient())))
            .collect(Collectors.toList());

        System.out.println(recipeIngredientsToDelete);
        for (RecipeIngredients toDelete : recipeIngredientsToDelete) {
            repository.delete(toDelete);
        }
    }

    public void deleteRecipeIngredientById(int id) {
        repository.deleteById(id);
    }



}
