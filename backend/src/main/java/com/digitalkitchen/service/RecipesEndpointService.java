package com.digitalkitchen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalkitchen.entities.Category;
import com.digitalkitchen.entities.Ingredients;
import com.digitalkitchen.entities.Measurements;
import com.digitalkitchen.entities.RecipeIngredients;
import com.digitalkitchen.entities.RecipeTags;
import com.digitalkitchen.entities.Recipes;
import com.digitalkitchen.entities.Steps;
import com.digitalkitchen.entities.Tags;

@Service
public class RecipesEndpointService {
    
    @Autowired
    private RecipesService recipesService;
    @Autowired
    private IngredientsService ingredientsService;
    @Autowired
    private RecipeIngredientsService recipeIngredientsService;
    @Autowired
    private RecipeTagsService recipeTagsService;
    @Autowired
    private StepsService stepsService;
    @Autowired 
    private TagsService tagsService;
    @Autowired
    private CategoryService categories;
    @Autowired
    private MeasurementsService measurements;

    
    public void initalizeRecipe(Map<String, Object> body) {
        //Recipe creation
        Recipes recipe = createRecipeFromMap((Map<String, Object>) body.get("recipe"));

        //Check if recipe is already in the database
        boolean exists = recipesService.recipeExists(recipe);
        if (exists) {
            return;
        } else {
            recipe = recipesService.addRecipe(recipe);
        }
            
        try {
            //RecipeIngredients List
            List<RecipeIngredients> recipeIngredients = createRecipeIngredientsListFromMap((List<Map<String, Object>>)body.get("ingredients"), recipe);
            recipeIngredientsService.addRecipeIngredients(recipeIngredients);
        
            //Steps List
            List<Steps> steps = createStepsListFromMap((List<Map<String, Object>>) body.get("steps"), recipe);
            stepsService.addSteps(steps);

            //Tags List
            List<RecipeTags> tagList = createRecipeTagsListFromMap((List<String>) body.get("tags"), recipe);
            recipeTagsService.addRecipeTags(tagList);

        } catch (Exception e) {
            e.printStackTrace();
            recipesService.deleteRecipeById(recipe.getID());
        }

    }

    private Recipes createRecipeFromMap(Map<String, Object> recipeMap) {
        
        Optional<Category> tempCat = categories.getCategoryById(Integer.parseInt((String)recipeMap.get("category")));
        Category category = tempCat.orElse(new Category());
        String name = (String)recipeMap.get("name");
        String desc = (String)recipeMap.get("description");
        String servings = (String)recipeMap.get("servings");
        int serv = (servings!="") ? Integer.parseInt(servings) : -1;
        String calories = (String)recipeMap.get("caloriesPerServing");
        int cals = (calories!="") ? Integer.parseInt(calories) : -1;
        String notes = (String)recipeMap.get("notes");
        String author = (String)recipeMap.get("author");
        
        return new Recipes(category, name, desc, serv, cals, notes, author);

    }
    private List<RecipeIngredients> createRecipeIngredientsListFromMap(List<Map<String, Object>> ingredientsMap, Recipes recipe) {
        
        List<RecipeIngredients> ingredientsList = new ArrayList<>();

        for (int i = 0; i < ingredientsMap.size(); i++) {
            Map<String, Object> currentMap = ingredientsMap.get(i);

            //Get Ingredient Object
            String name = (String) currentMap.get("ingredient");
            Optional<Ingredients> tempIngredient = ingredientsService.getIngredientByName(name);
            Ingredients ingredient;
            if (tempIngredient.isEmpty()) {
                ingredient = ingredientsService.addIngredient(new Ingredients(name));
            } else {
                ingredient = tempIngredient.orElse(new Ingredients(name));
            }

            //Get Measurement Object
            int msmt = Integer.parseInt((String)currentMap.get("measurement"));
            Optional<Measurements> tempmsmt = measurements.getMeasurementById(msmt);
            Measurements measurement = tempmsmt.orElse(new Measurements());

            //Set Quantity
            float quantity = Float.parseFloat((String)currentMap.get("quantity"));

            //Set Notes
            String notes = (String) currentMap.get("notes");

            //Create Ingredient
            RecipeIngredients recipeIngredient = new RecipeIngredients(recipe, ingredient, measurement, quantity, notes);

            //Add to List
            ingredientsList.add(recipeIngredient);


        }

        return ingredientsList;
    }
    private List<Steps> createStepsListFromMap(List<Map<String, Object>> stepsMap, Recipes recipe) {
        List<Steps> stepsList = new ArrayList<>();
        Map<String, Object> currentMap;

        for (int i = 0; i < stepsMap.size(); i++) {
            currentMap = stepsMap.get(i);
            //Number
            int stepNO = i + 1;
            //Description
            String desc = (String) currentMap.get("description");
            //Create Step
            Steps step = new Steps(recipe, stepNO, desc);
            //Add to List
            stepsList.add(step);
        }
        
        return stepsList;
    }
    private List<RecipeTags> createRecipeTagsListFromMap(List<String> tagsMap, Recipes recipe) {
        List<RecipeTags> tagsList = new ArrayList<>();

        for (int i = 0; i < tagsMap.size(); i++) {
            
            //Find Tag
            String name = (String) tagsMap.get(i);
            Optional<Tags> tempTag = tagsService.getTagByName(name);
            Tags tag;

            if (tempTag.isEmpty()) {
                tag = tagsService.addTag(new Tags(name));
            } else {
                tag = tempTag.orElse(new Tags(name));
            }

            //Create Tag
            RecipeTags recipeTag = new RecipeTags(recipe, tag);
            //Add to List
            tagsList.add(recipeTag);
        }
        
        return tagsList;
    }
}
