package com.digitalkitchen.testutils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.mapping.Array;

import com.digitalkitchen.controller.request.transferobjects.RecipeTransferObject;
import com.digitalkitchen.entities.Category;
import com.digitalkitchen.entities.Ingredients;
import com.digitalkitchen.entities.Measurements;
import com.digitalkitchen.entities.RecipeIngredients;
import com.digitalkitchen.entities.RecipeTags;
import com.digitalkitchen.entities.Recipes;
import com.digitalkitchen.entities.Steps;
import com.digitalkitchen.entities.Tags;

public class TestObjectManager {

    public static Recipes createMockRecipe() {
        Recipes mockRecipe = mock(Recipes.class);
            when(mockRecipe.getName()).thenReturn("test");
            when(mockRecipe.getCategory()).thenReturn(new Category());
            when(mockRecipe.getAuthor()).thenReturn("test");
            when(mockRecipe.getCaloriesPerServing()).thenReturn(-1);
            when(mockRecipe.getDescription()).thenReturn("test");
            when(mockRecipe.getServings()).thenReturn(-1);
        return mockRecipe;
    }

    public static Recipes createMockRecipe(int id) {
        Recipes mockRecipe = mock(Recipes.class);
            when(mockRecipe.getName()).thenReturn("test");
            when(mockRecipe.getID()).thenReturn(id);
            when(mockRecipe.getCategory()).thenReturn(new Category());
            when(mockRecipe.getAuthor()).thenReturn("test");
            when(mockRecipe.getCaloriesPerServing()).thenReturn(-1);
            when(mockRecipe.getDescription()).thenReturn("test");
            when(mockRecipe.getServings()).thenReturn(-1);
        return mockRecipe;
    }

    public static Recipes createMockExpandedRecipe() {
        int listSize = 2;
        Recipes mockRecipe = createMockRecipe();
        mockRecipe.setIngredients(createMockRecipeIngredientsList(listSize, mockRecipe));
        mockRecipe.setSteps(createMockStepsList(listSize, mockRecipe));
        mockRecipe.setTags(createMockRecipeTagsList(listSize, mockRecipe));
        return mockRecipe;
    }
    
    public static List<RecipeIngredients> createMockRecipeIngredientsList(int size) {
        RecipeIngredients mockIngredient;
        List<RecipeIngredients> mockList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            mockIngredient = new RecipeIngredients();
            when(mockIngredient.getID()).thenReturn(i);
            when(mockIngredient.getIngredient()).thenReturn(new Ingredients("test ingredient"));
            when(mockIngredient.getMeasurement()).thenReturn(new Measurements());
            when(mockIngredient.getQuantity()).thenReturn((float)i);
            when(mockIngredient.getNotes()).thenReturn("test");
            mockList.add(mockIngredient);
        }
        return mockList;
    }

    public static List<RecipeIngredients> createMockRecipeIngredientsList(int size, Recipes recipe) {
        RecipeIngredients mockIngredient;
        List<RecipeIngredients> mockList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            mockIngredient = new RecipeIngredients();
            when(mockIngredient.getID()).thenReturn(i);
            when(mockIngredient.getIngredient()).thenReturn(new Ingredients("test ingredient"));
            when(mockIngredient.getMeasurement()).thenReturn(new Measurements());
            when(mockIngredient.getQuantity()).thenReturn((float)i);
            when(mockIngredient.getNotes()).thenReturn("test");
            when(mockIngredient.getRecipe()).thenReturn(recipe);
            mockList.add(mockIngredient);
        }
        return mockList;
    }

    public static List<Steps> createMockStepsList(int size) {
        Steps mockStep;
        List<Steps> mockList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            mockStep = new Steps();
            when(mockStep.getDescription()).thenReturn("test step");
            when(mockStep.getID()).thenReturn(i);
            when(mockStep.getStepNumber()).thenReturn(i);
            mockList.add(mockStep);
        }
        return mockList;
    }

    public static List<Steps> createMockStepsList(int size, Recipes recipe) {
        Steps mockStep;
        List<Steps> mockList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            mockStep = new Steps();
            when(mockStep.getDescription()).thenReturn("test step");
            when(mockStep.getID()).thenReturn(i);
            when(mockStep.getStepNumber()).thenReturn(i);
            when(mockStep.getRecipe()).thenReturn(recipe);
            mockList.add(mockStep);
        }
        return mockList;
    }

    public static List<RecipeTags> createMockRecipeTagsList(int size) {
        RecipeTags mockTag;
        List<RecipeTags> mockList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            mockTag = new RecipeTags();
            when(mockTag.getID()).thenReturn(i);
            when(mockTag.getTag()).thenReturn(new Tags("test"));
            mockList.add(mockTag);
        }
        return mockList;
    }

    public static List<RecipeTags> createMockRecipeTagsList(int size, Recipes recipe) {
        RecipeTags mockTag;
        List<RecipeTags> mockList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            mockTag = new RecipeTags();
            when(mockTag.getID()).thenReturn(i);
            when(mockTag.getTag()).thenReturn(new Tags("test"));
            when(mockTag.getRecipe()).thenReturn(recipe);
            mockList.add(mockTag);
        }
        return mockList;
    }

    public static RecipeTransferObject createMockTransferObject() {
        return new RecipeTransferObject(createMockExpandedRecipe());
    }

    public static Map<String, Object> createRecipeMap() {
        Map<String, Object> recipeMap = new HashMap<>();
        recipeMap.put("id", 185);
        recipeMap.put("name", "super test");
        recipeMap.put("author", "test");
        recipeMap.put("category", "drink");
        recipeMap.put("caloriesPerServing", -1);
        recipeMap.put("description", "");
        recipeMap.put("servings", -1);
        return recipeMap;
    }

    public static Map<String, Object> createExpandedRecipeMap() {
        Map<String, Object> recipeMap = new HashMap<>();
        recipeMap.putAll(createRecipeMap());
        recipeMap.put("ingredients", createIngredientsListMap());
        recipeMap.put("tags", createTagsListMap());
        recipeMap.put("steps", createStepsListMap());
        System.out.println(recipeMap);
        return recipeMap;
    }

    public static List<Map<String, Object>> createIngredientsListMap() {
        List<Map<String, Object>> ingredientsList = new ArrayList<>();
        Map<String, Object> ingredientMap = new HashMap<>();
        ingredientMap.put("ingredient", "TEST");
        ingredientMap.put("measurement", "gallon");
        ingredientMap.put("quantity", 5.0);
        ingredientMap.put("notes", "");
        ingredientsList.add(ingredientMap);
        return ingredientsList;
    }

    public static List<String> createTagsListMap() {
        List<String> tagsList = new ArrayList<>();
        tagsList.add("simple");
        tagsList.add("cheap");
        return tagsList;
    }

    public static List<Map<String, Object>> createStepsListMap() {
        List<Map<String, Object>> stepsList = new ArrayList<>();
        Map<String, Object> stepMap = new HashMap<>();
        stepMap.put("stepNumber", 1);
        stepMap.put("description", "test");
        stepMap.put("id", 245);
        stepsList.add(stepMap);
        return stepsList;
    }
}
