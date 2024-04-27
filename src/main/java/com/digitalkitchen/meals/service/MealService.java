package com.digitalkitchen.meals.service;

import com.digitalkitchen.meals.model.entities.Meal;
import com.digitalkitchen.meals.model.entities.MealPlan;
import com.digitalkitchen.meals.model.entities.MealRecipe;
import com.digitalkitchen.meals.model.request.MealRequest;
import com.digitalkitchen.meals.model.request.MealRequestMealInfo;
import com.digitalkitchen.meals.model.request.MealRequestPlanInfo;
import com.digitalkitchen.meals.model.response.MealResponse;
import com.digitalkitchen.meals.repository.MealPlanRepository;
import com.digitalkitchen.meals.repository.MealRepository;
import com.digitalkitchen.recipes.model.entities.Recipe;
import com.digitalkitchen.recipes.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {

    private final MealRepository mealRepository;
    private final MealPlanRepository mealPlanRepository;
    private final RecipeRepository recipeRepository;

    MealService(MealRepository mealRepository, MealPlanRepository mealPlanRepository, RecipeRepository recipeRepository) {
        this.mealRepository = mealRepository;
        this.mealPlanRepository = mealPlanRepository;
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

    public MealResponse createMealPlan(MealRequest request) {
        MealRequestMealInfo mealInfo = request.getMeals().get(0);
        MealPlan mealPlan = buildMealPlan(request.getPlan());
        Meal meal = buildMeal(mealInfo);
        List<Recipe> recipes = recipeRepository.findAllById(mealInfo.getRecipeIds());
        List<MealRecipe> mealRecipes = recipes.stream()
                .map(recipe -> buildMealRecipes(meal, recipe))
                .toList();
        meal.setMealRecipes(mealRecipes);
        meal.setMealPlanId(mealPlan.getId());
        mealRepository.save(meal);
        mealPlanRepository.save(mealPlan);

        return MealResponseMapper.buildCreateResponse(meal, mealPlan);
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

    private MealPlan buildMealPlan(MealRequestPlanInfo requestInfo) {
        return MealPlan.builder()
                .startDate(requestInfo.getStartDate())
                .endDate(requestInfo.getEndDate())
                .build();
    }
}
