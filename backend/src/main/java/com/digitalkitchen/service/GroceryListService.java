package com.digitalkitchen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.digitalkitchen.entities.Ingredients;
import com.digitalkitchen.entities.Measurements;
import com.digitalkitchen.entities.RecipeIngredients;

@Service
public class GroceryListService {

    @Autowired
    private IngredientsService ingredientsService;

    @Autowired
    private MeasurementsService measurementsService;
    
    public ResponseEntity<?> createGroceryList(
        @RequestBody List<Map<String, Object>> recipeList) 
        {
        return ResponseEntity.ok(extractIngredientList(recipeList));
    }

    private List<RecipeIngredients> extractIngredientList(List<Map<String, Object>> recipeList) {
        List<RecipeIngredients> ingredientsList = new ArrayList<RecipeIngredients>();
        Map<String, Object> currentRecipe;
        List<Map<String, Object>> currentIngredientsList;

        for (int index = 0; index < recipeList.size(); index++) {
            currentRecipe = (Map<String, Object>) recipeList.get(index);
            currentIngredientsList = (List<Map<String, Object>>) currentRecipe.get("ingredients");
            for (int i = 0; i < currentIngredientsList.size(); i++) {
                System.out.println(currentIngredientsList.get(i));
                Map<String, Object> ingredientMap = (Map<String, Object>) currentIngredientsList.get(i);
                String ingredientName = (String) ingredientMap.get("ingredient");
                Ingredients ingredient = ingredientsService.getIngredientByName(ingredientName).get();
                Float quantity = ((Integer) currentIngredientsList.get(i).get("quantity")).floatValue();
                Measurements unit = measurementsService.getMeasurementByName((String) currentIngredientsList.get(i).get("measurement")).get();
                String notes = (String) currentIngredientsList.get(i).get("notes");
                ingredientsList.add(new RecipeIngredients(null, ingredient, unit, quantity, notes));
            }
        }
        
        
        
        return ingredientsList;
    }

}
