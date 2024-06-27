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
import com.digitalkitchen.meals.repository.MealRecordRepository;
import com.digitalkitchen.meals.repository.MealRepository;
import com.digitalkitchen.recipes.model.entities.Recipe;
import com.digitalkitchen.recipes.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
public class MealService {

    private final MealRepository mealRepository;
    private final MealPlanRepository mealPlanRepository;
    private final MealRecordRepository mealRecordRepository;
    private final RecipeRepository recipeRepository;

    MealService(MealRepository mealRepository, MealPlanRepository mealPlanRepository, MealRecordRepository mealRecordRepository, RecipeRepository recipeRepository) {
        this.mealRepository = mealRepository;
        this.mealPlanRepository = mealPlanRepository;
        this.mealRecordRepository = mealRecordRepository;
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

    public List<MealRecord> createMealRecords(MealRequest request, MealPlan plan, List<Meal> meals) {
        Map<String, Meal> newMeals = request.getMeals().stream()
                .filter(mealInfo -> isBlank(mealInfo.getId()))
                .collect(Collectors.toMap(
                        MealInfo::getRelationId,
                        mealInfo -> meals.stream()
                                .filter(meal -> meal.getName().equals(mealInfo.getName()))
                                .findFirst()
                                .orElseThrow()));

        List<MealRecord> records = request.getRecords().stream()
                .map(recordInfo -> buildMealRecord(recordInfo, findMeal(recordInfo, meals, newMeals), plan))
                .toList();
        return mealRecordRepository.saveAll(records);
    }

    @Transactional
    public MealResponse processCreateRequest(MealRequest request) {
        boolean mealsPresent = request.getMeals() != null;
        boolean planPresent = request.getPlan() != null;
        boolean recordsPresent = request.getRecords() != null;

        if (!mealsPresent && !planPresent && !recordsPresent) {
            return MealResponseMapper.buildEmptyResponse();
        }

        List<Meal> meals = mealsPresent ? createMeal(request.getMeals()) : null;
        MealPlan plan = planPresent ? createMealPlan(request.getPlan()) : null;
        List<MealRecord> records = recordsPresent ? createMealRecords(request, plan, meals) : null;

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
                .type(mealRecordInfo.getMealType())
                .date(mealRecordInfo.getDate())
                .build();
    }

    private Meal findMeal(MealRecordInfo request, List<Meal> meals, Map<String, Meal> newMeals) {
        Meal foundMeal;

        if (isBlank(request.getMealId())) {
            foundMeal = newMeals.get(request.getRelationId());
        } else {
            long requestId = Long.parseLong(request.getMealId());
            foundMeal = meals.stream()
                    .filter(meal -> meal.getId() == requestId)
                    .findFirst()
                    .orElseThrow();
        }
        return foundMeal;
    }
}
