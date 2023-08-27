package com.digitalkitchen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.digitalkitchen.entities.Category;
import com.digitalkitchen.entities.Ingredients;
import com.digitalkitchen.entities.Measurements;
import com.digitalkitchen.entities.RecipeIngredients;
import com.digitalkitchen.entities.RecipeTags;
import com.digitalkitchen.entities.Recipes;
import com.digitalkitchen.entities.Steps;
import com.digitalkitchen.entities.Tags;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    private CategoryService categoriesService;
    @Autowired
    private MeasurementsService measurementsService;

    ObjectMapper objectMapper = new ObjectMapper();

    public ResponseEntity<?> updateRecipe(Map<String, Object> body) throws Exception {
        try {
        //Get recipe
        Recipes recipe = recipesService.getRecipeById((int) body.get("id")).get();
        
        //Name
        if( (String) body.get("name") != recipe.getName() && body.get("name") != null) {
            recipe.setName(((String) body.get("name")).toLowerCase());
        }
        //Author
        if( (String) body.get("author") != recipe.getAuthor() && body.get("author") != null) {
            recipe.setAuthor(((String) body.get("author")).toLowerCase());
        }
        //Category
        if( (String) body.get("category") != recipe.getCategory().getName() && body.get("category") != null) {
            recipe.setCategory(categoriesService.getCategoryByName(((String) body.get("category")).toLowerCase()).get());
        }
        //Description
        if( (String) body.get("description") != recipe.getDescription() && body.get("description") != null) {
            recipe.setDescription(((String) body.get("description")).toLowerCase());
        }
        //Servings
        if( (int) body.get("servings") != recipe.getServings() && body.get("servings") != null) {
            recipe.setServings((int) body.get("servings"));
        }
        //Calories
        if( (int) body.get("caloriesPerServing") != recipe.getCaloriesPerServing() && body.get("caloriesPerServing") != null) {
            recipe.setCaloriesPerServing((int) body.get("caloriesPerServing"));
        }
        //Notes
        if( (String) body.get("notes") != recipe.getNotes() && body.get("notes") != null) {
            recipe.setNotes(((String) body.get("notes")).toLowerCase());
        }

        recipe = recipesService.updateRecipe(recipe);

        //Ingredients
        List<Map<String, Object>> ingredientStrings = (List<Map<String, Object>>) body.get("ingredients");
        List<RecipeIngredients> currentIngredients = recipeIngredientsService.getAllRecipeIngredientsByRecipe(recipe);

        for (int i = 0; i < currentIngredients.size(); i++) {
            RecipeIngredients currentIngredient = currentIngredients.get(i);
            Map<String, Object> ingredientMap = (Map<String, Object>) ingredientStrings.get((i));
        
            // Retrieve updated values from ingredientMap
            String updatedName = (String) ingredientMap.get("ingredient");
            String updatedMeasurement = (String) ingredientMap.get("measurement");
            float updatedQuantity = Float.parseFloat(((Object) ingredientMap.get("quantity")).toString());
            String updatedNotes = (String) ingredientMap.get("notes");
        
            // Check for difference
            if (currentIngredient.getIngredient().getIngredient() != updatedName) {
                currentIngredient.setIngredient((ingredientsService.getIngredientByName(updatedName)).get());
            }
            if (currentIngredient.getMeasurement().getMeasurement() != updatedMeasurement) {
                currentIngredient.setMeasurement((measurementsService.getMeasurementByName(updatedMeasurement)).get());
            }; 
            if (currentIngredient.getQuantity() != updatedQuantity) {
                currentIngredient.setQuantity(updatedQuantity);
            }
            if (currentIngredient.getNotes() != updatedNotes) {
                currentIngredient.setNotes(updatedNotes);
            };
        }
           
        //Steps
        List<Map<String, Object>> updatedStepList = (List<Map<String, Object>>) body.get("steps");
        List<Steps> currentSteps = stepsService.getAllStepsByRecipe(recipe);

        for (int i = 0; i < currentSteps.size(); i++) {
            Steps currentStep = currentSteps.get(i);
            Map<String, Object> stepMap = (Map<String, Object>) updatedStepList.get((0));
        
            // Retrieve updated values from ingredientMap
            int updatedNumber = (int) stepMap.get("stepNumber");
            String updatedDescription = (String) stepMap.get("description");
        
            // Check for difference
            if (currentStep.getStepNumber() != updatedNumber) {
                currentStep.setStepNumber(updatedNumber);
            }
            if (currentStep.getDescription() != updatedDescription) {
                currentStep.setDescription(updatedDescription);
            }; 
        }

        //Tags
        List<String> updatedTagsList = (List<String>) body.get("tags");
        List<RecipeTags> currentTags = recipeTagsService.getAllRecipeTagsByRecipe(recipe);

        for (int i = 0; i < currentTags.size(); i++) {
            RecipeTags currentTag = currentTags.get(i);
        
            String updatedTag = updatedTagsList.get(i);
            
            // Check for difference
            if (currentTag.getTag().getName() != updatedTag) {
                currentTag.setTag((tagsService.getTagByName(updatedTag)).get());
            }
        }

        return ResponseEntity.ok().body(recipesService.createTransferObject(recipe));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    public ResponseEntity<?> initalizeRecipe(Map<String, Object> body, boolean force) throws Exception {
        //Recipe creation
        Recipes recipe = createRecipeFromMap((Map<String, Object>) body.get("recipe"));

        try  {
            List<Recipes> duplicates = recipesService.getRecipeByNameAndAuthor(recipe.getName(), recipe.getAuthor());
            if ( (!force) & (!duplicates.isEmpty()) ) {
                recipe = recipesService.getExpandedRecipe(duplicates.get(0)); //TODO: The frontend needs to be configured to take a list
                return ResponseEntity.status(HttpStatus.CONFLICT).body(recipesService.createTransferObject(recipe));
            } 
            recipe = recipesService.addRecipe(recipe);
        
            // RecipeIngredients List
            List<RecipeIngredients> recipeIngredients = createRecipeIngredientsListFromMap((List<Map<String, Object>>) body.get("ingredients"), recipe);
            recipeIngredientsService.addRecipeIngredients(recipeIngredients);
    
            // Steps List
            List<Steps> steps = createStepsListFromMap((List<Map<String, Object>>) body.get("steps"), recipe);
            stepsService.addSteps(steps);
    
            // Tags List
            List<RecipeTags> tagList = createRecipeTagsListFromMap((List<String>) body.get("tags"), recipe);
            recipeTagsService.addRecipeTags(tagList);
    
            recipe = recipesService.getExpandedRecipe(recipe);
            return ResponseEntity.status(HttpStatus.CREATED).body(recipesService.createTransferObject(recipe));
        } catch (Exception e) {
            recipesService.deleteRecipeById(recipe.getID());
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e);
        }

    }

    private Recipes createRecipeFromMap(Map<String, Object> recipeMap) throws Exception {
        try { 
            Optional<Category> tempCat = categoriesService.getCategoryById(Integer.parseInt((String)recipeMap.get("category")));
            Category category = tempCat.orElse(new Category());
            String name = ((String)recipeMap.get("name")).toLowerCase();
            String author = ((String)recipeMap.get("author")).toLowerCase();

            String desc = ((String)recipeMap.get("description")).toLowerCase();
            String servings = (String)recipeMap.get("servings");
            int serv = (servings!="") ? Integer.parseInt(servings) : -1;
            String calories = (String)recipeMap.get("caloriesPerServing");
            int cals = (calories!="") ? Integer.parseInt(calories) : -1;
            String notes = ((String)recipeMap.get("notes")).toLowerCase();
            
            
            return new Recipes(category, name, desc, serv, cals, notes, author);
        } catch (Exception e) {
            throw new Exception("recipe field(s) are missing or have errors");
        }
    }
    private List<RecipeIngredients> createRecipeIngredientsListFromMap(List<Map<String, Object>> ingredientsMap, Recipes recipe) {
        
        List<RecipeIngredients> ingredientsList = new ArrayList<>();

        for (int i = 0; i < ingredientsMap.size(); i++) {
            Map<String, Object> currentMap = ingredientsMap.get(i);

            //Get Ingredient Object
            String name = ((String) currentMap.get("ingredient")).toLowerCase();
            Optional<Ingredients> tempIngredient = ingredientsService.getIngredientByName(name);
            Ingredients ingredient;
            if (tempIngredient.isEmpty()) {
                ingredient = ingredientsService.addIngredient(new Ingredients(name));
            } else {
                ingredient = tempIngredient.orElse(new Ingredients(name));
            }

            //Get Measurement Object
            int msmt = Integer.parseInt((String)currentMap.get("measurement"));
            Optional<Measurements> tempmsmt = measurementsService.getMeasurementById(msmt);
            Measurements measurement = tempmsmt.orElse(new Measurements());

            //Set Quantity
            float quantity = Float.parseFloat((String)currentMap.get("quantity"));

            //Set Notes
            String notes = ((String) currentMap.get("notes")).toLowerCase();

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
            String desc = ((String) currentMap.get("description")).toLowerCase();
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
            String name = ((String) tagsMap.get(i)).toLowerCase();
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
