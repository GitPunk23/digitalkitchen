package com.digitalkitchen.meals.service;

import com.digitalkitchen.exceptions.DataException;
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
import static java.util.Objects.nonNull;
import static org.apache.logging.log4j.util.Strings.isBlank;
import static org.springframework.util.CollectionUtils.isEmpty;

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

    public List<MealRecord> buildAndSaveMealRecords(List<MealRecordInfo> recordInfos) {
        List<MealRecord> records = new ArrayList<>();
        recordInfos.forEach(recordInfo ->{
            Meal meal = mealRepository.findById(Long.valueOf(recordInfo.getMealId())).get();
            MealPlan mealPlan = mealPlanRepository.findById(Long.valueOf(recordInfo.getMealPlanId())).get();
            records.add(buildMealRecord(recordInfo, meal, mealPlan));
        });
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

        MealPlan plan = request.getPlan() != null
                ? buildAndSaveMealPlan(request.getPlan())
                : null;

        List<MealRecord> records;
        if (request.getRecords() != null) {
            List<MealRecordInfo> recordInfos = request.getRecords();
            setMealRecordMealPlanIds(recordInfos, plan);
            setMealRecordMealIds(recordInfos, meals, request.getMeals());
            records = buildAndSaveMealRecords(recordInfos);
        } else {
            records = null;
        }

        return MealResponseMapper.buildCreateResponse(meals, plan, records);
    }

    @Transactional
    public MealResponse processUpdateRequest(MealRequest request) {
        return MealResponseMapper.buildEmptyResponse(NOTHING_CREATED);
    }

    public MealResponse getMeal(Long mealId) {
        Optional<Meal> meal = mealRepository.findById(mealId);
        return meal.isPresent()
                ? buildSearchResponse(meal.get())
                : MealResponseMapper.buildEmptyResponse(NOT_FOUND);
    }

    public MealResponse getMealRecord(Long mealRecordId) {
        Optional<MealRecord> mealRecord = mealRecordRepository.findById(mealRecordId);
        return mealRecord.isPresent()
                ? buildSearchResponse(mealRecord.get())
                : MealResponseMapper.buildEmptyResponse(NOT_FOUND);
    }

    public MealResponse getMealPlan(Long planId) {
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

    private void setMealRecordMealPlanIds(List<MealRecordInfo> recordInfos, MealPlan plan) {
        if(nonNull(plan)) {
            recordInfos.stream()
                    .filter(mealRecordInfo -> mealRecordInfo.getMealPlanId().isEmpty())
                    .forEach(mealRecordInfo -> mealRecordInfo.setMealPlanId(String.valueOf(plan.getId())));
        }
    }

    private void setMealRecordMealIds(List<MealRecordInfo> recordInfos, List<Meal> meals, List<MealInfo> requestMeals) {
        if (!isEmpty(meals)) {

            Map<String, Long> relationIdToMealIdMap = requestMeals.stream()
                    .collect(Collectors.toMap(
                            MealInfo::getRelationId,
                            requestMeal -> meals.stream()
                                    .filter(meal -> meal.getName().equalsIgnoreCase(requestMeal.getName()))
                                    .findFirst()
                                    .map(Meal::getId)
                                    .orElseThrow(()-> new DataException("", "An error occurred while mapping relationIds, request failed"))//DATAEXCEPTION
                    ));

            recordInfos.stream()
                    .filter(recordInfo -> isBlank(recordInfo.getMealId()))
                    .forEach(recordInfo -> {
                        Long mealId = relationIdToMealIdMap.get(recordInfo.getRelationId());
                        if (mealId != null) {
                            recordInfo.setMealId(mealId.toString());
                        }
                    });
        }
    }
}
