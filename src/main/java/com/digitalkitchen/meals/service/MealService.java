package com.digitalkitchen.meals.service;

import com.digitalkitchen.meals.model.entities.Meal;
import com.digitalkitchen.meals.model.entities.MealRecipe;
import com.digitalkitchen.meals.model.entities.MealRecord;
import com.digitalkitchen.meals.model.request.MealRecordInfo;
import com.digitalkitchen.meals.model.request.MealRequest;
import com.digitalkitchen.meals.model.request.MealInfo;
import com.digitalkitchen.meals.model.request.MealPlanInfo;
import com.digitalkitchen.meals.model.entities.MealPlan;
import com.digitalkitchen.meals.model.response.MealResponse;
import com.digitalkitchen.meals.repository.MealPlanRepository;
import com.digitalkitchen.meals.repository.MealRepository;
import com.digitalkitchen.recipes.model.entities.Recipe;
import com.digitalkitchen.recipes.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Meal> createMeal(List<MealInfo> request) {
        List<Meal> meals = new ArrayList<>();

        for (MealInfo info: request) {
            Meal meal = buildMeal(info);
            List<Recipe> recipes = recipeRepository.findAllById(info.getRecipeIds());
            List<MealRecipe> mealRecipes = recipes.stream()
                    .map(recipe -> buildMealRecipes(meal, recipe))
                    .toList();
            meal.setMealRecipes(mealRecipes);

            meals.add(meal);
        }

        return mealRepository.saveAll(meals);
    }

    public MealPlan createMealPlan(MealPlanInfo request) {
        MealPlan mealPlan = buildMealPlan(request);
        return mealPlanRepository.save(mealPlan);
    }

    public List<MealRecord> createMealRecords(List<MealRecordInfo> recordInfoList, MealPlan plan, List<Meal> meals) {
        return recordInfoList.stream()
                .map(recordInfo -> buildMealRecord(recordInfo, findMeal(recordInfo, meals), plan))
                .toList();
    }

    public MealResponse processCreateRequest(MealRequest request) {
        List<Meal> meals = createMeal(request.getMeals());
        MealPlan plan = createMealPlan(request.getPlan());
        List<MealRecord> records = createMealRecords(request.getRecords(), plan, meals);

        return MealResponseMapper.buildCreateResponse(meals, plan, records);
    }

    private Meal buildMeal(MealInfo requestInfo) {
        return Meal.builder()
                .name(requestInfo.getName())
                .notes(requestInfo.getNotes())
                .build();
    }

    private MealRecipe buildMealRecipes(Meal meal, Recipe recipe) {
        return MealRecipe.builder()
                .meal(meal)
                .recipe(recipe)
                .build();
    }

    private MealPlan buildMealPlan(MealPlanInfo requestInfo) {
        return MealPlan.builder()
                .startDate(requestInfo.getStartDate())
                .endDate(requestInfo.getEndDate())
                .build();
    }

    private MealRecord buildMealRecord(MealRecordInfo mealRecordInfo, Meal meal, MealPlan mealPlan) {
        return MealRecord.builder()
                .meal(meal)
                .mealPlan(mealPlan)
                .mealType(mealRecordInfo.getMealType())
                .date(mealRecordInfo.getDate())
                .build();
    }

    private Meal findMeal(MealRecordInfo request, List<Meal> meals) {
        Long requestId = request.getMealId();

        return meals.stream()
                .filter(meal -> meal.getId() == requestId)
                .findFirst()
                .orElseThrow();
    }
}
