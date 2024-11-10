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
import java.util.Optional;
import java.util.stream.Collectors;

import static com.digitalkitchen.meals.service.MealResponseMapper.buildSearchResponse;
import static com.digitalkitchen.util.Constants.NOTHING_CREATED;
import static com.digitalkitchen.util.Constants.NOT_FOUND;
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

    public List<Meal> buildAndSaveMeal(List<MealInfo> request) {
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

    public MealPlan buildAndSaveMealPlan(MealPlanInfo request) {
        MealPlan mealPlan = buildMealPlan(request);
        return mealPlanRepository.save(mealPlan);
    }

    public List<MealRecord> buildAndSaveMealRecords(MealRequest request, MealPlan plan, List<Meal> meals) {
        Map<String, Meal> newMeals = request.getMeals().stream()
                .filter(mealInfo -> isBlank(mealInfo.getId()))
                .collect(Collectors.toMap(
                        MealInfo::getRelationId,
                        mealInfo -> meals.stream()
                                .filter(meal -> meal.getName().equals(mealInfo.getName()))
                                .findFirst()
                                .orElseThrow()));

        List<MealRecord> records = request.getRecords().stream()
                .map(recordInfo -> buildMealRecord(recordInfo, getMealFromMealRecordInfo(recordInfo, meals, newMeals), plan))
                .toList();
        return mealRecordRepository.saveAll(records);
    }

    @Transactional
    public MealResponse processCreateRequest(MealRequest request) {
        if (request.isEmpty()) {
            return MealResponseMapper.buildEmptyResponse(NOTHING_CREATED);
        }

        List<Meal> meals = request.getMeals() != null
                ? buildAndSaveMeal(request.getMeals())
                : null;
        List<MealPlan> plans = List.of(request.getPlan() != null
                ? buildAndSaveMealPlan(request.getPlan())
                : null);
        List<MealRecord> records = request.getRecords() != null
                ? buildAndSaveMealRecords(request, plans.get(0), meals)
                : null;

        return MealResponseMapper.buildCreateResponse(meals, plans, records);
    }

    public MealResponse getMeal(Long mealId) {
        Optional<Meal> meal = mealRepository.findById(mealId);
        return meal.isPresent()
                ? buildSearchResponse(meal.get())
                : MealResponseMapper.buildEmptyResponse(NOT_FOUND);
    }

    public MealResponse getMealRecord(Long mealRecordId) {
        MealResponse response;
        Optional<MealRecord> mealRecord = mealRecordRepository.findById(mealRecordId);
        return mealRecord.isPresent()
                ? buildSearchResponse(mealRecord.get())
                : MealResponseMapper.buildEmptyResponse(NOT_FOUND);
    }

    public MealResponse getMealPlan(Long planId) {
        MealResponse response;
        Optional<MealPlan> plan = mealPlanRepository.findById(planId);

        return plan.isPresent()
                ? buildSearchResponse(plan.get())
                : MealResponseMapper.buildEmptyResponse(NOT_FOUND);
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

    private Meal getMealFromMealRecordInfo(MealRecordInfo request, List<Meal> meals, Map<String, Meal> newMeals) {
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
