package com.digitalkitchen.meals.service;

import com.digitalkitchen.meals.model.entities.Meal;
import com.digitalkitchen.meals.model.entities.MealRecipe;
import com.digitalkitchen.meals.model.request.MealRequest;
import com.digitalkitchen.meals.model.request.MealRequestMealInfo;
import com.digitalkitchen.meals.model.response.MealResponse;
import com.digitalkitchen.meals.repository.MealRepository;
import com.digitalkitchen.recipes.model.entities.Recipe;
import com.digitalkitchen.recipes.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {

    private final MealRepository mealRepository;
    private final RecipeRepository recipeRepository;

    MealService(MealRepository mealRepository, RecipeRepository recipeRepository) {
        this.mealRepository = mealRepository;
        this.recipeRepository = recipeRepository;
    }

    public MealResponse createMeal(MealRequest request) {
        MealRequestMealInfo requestInfo = request.getMeals().get(0);
        Meal meal = buildMeal(requestInfo);
        List<Recipe> recipes = recipeRepository.findAllById(requestInfo.getRecipeIds());
        List<MealRecipe> mealRecipes = recipes.stream()
                .map(recipe -> buildMealRecipes(meal, recipe))
                .toList();
        meal.setMealRecipes(mealRecipes);
        mealRepository.save(meal);

        return MealResponseMapper.buildCreateResponse(meal);
    }

    private Meal buildMeal(MealRequestMealInfo requestInfo) {
        return Meal.builder()
                .name(requestInfo.getName())
                .date(requestInfo.getDate())
                .type(requestInfo.getType())
                .mealPlanId(Long.getLong(requestInfo.getMealPlanId()))
                .notes(requestInfo.getNotes())
                .build();
    }

    private MealRecipe buildMealRecipes(Meal meal, Recipe recipe) {
        return MealRecipe.builder()
                .meal(meal)
                .recipe(recipe)
                .build();

    }
}
