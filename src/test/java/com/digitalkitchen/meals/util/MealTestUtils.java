package com.digitalkitchen.meals.util;

import com.digitalkitchen.meals.model.entities.Meal;
import com.digitalkitchen.meals.model.entities.MealPlan;
import com.digitalkitchen.meals.model.entities.MealRecipe;
import com.digitalkitchen.meals.model.entities.MealRecord;
import com.digitalkitchen.meals.model.request.MealRecordInfo;
import com.digitalkitchen.meals.model.request.MealRequest;
import com.digitalkitchen.meals.model.request.MealInfo;
import com.digitalkitchen.meals.model.request.MealPlanInfo;
import com.digitalkitchen.meals.model.response.MealResponse;
import com.digitalkitchen.meals.model.response.MealResponseInfo;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static com.digitalkitchen.enums.ResponseStatus.CREATED;
import static com.digitalkitchen.enums.ResponseStatus.FOUND;
import static com.digitalkitchen.enums.ResponseStatus.UPDATED;
import static com.digitalkitchen.meals.enums.MealType.DINNER;
import static com.digitalkitchen.meals.util.TestConstants.*;
import static com.digitalkitchen.recipes.util.RecipeTestUtils.getTestRecipe;

public class MealTestUtils {

    public static MealRequest buildRequest(List<MealInfo> mealInfo, MealPlanInfo planInfo, List<MealRecordInfo> recordInfo) {
        return MealRequest.builder()
                .plan(planInfo)
                .meals(mealInfo)
                .records(recordInfo)
                .build();
    }

    public static MealInfo buildMealInfo(String id, String relationId) {
        return MealInfo.builder()
                .id(id)
                .relationId(relationId)
                .name("Star Wars Night")
                .recipeIds(List.of(RECIPE_ID))
                .build();
    }

    public static MealPlanInfo buildMealPlanInfo() {
        return MealPlanInfo.builder()
                .id(String.valueOf(MEAL_PLAN_ID))
                .nickname("Super Diet")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(3))
                .build();
    }

    public static MealRecordInfo buildMealRecordInfo() {
        return MealRecordInfo.builder()
                .id(String.valueOf(MEAL_RECORD_ID))
                .mealType(DINNER)
                .date(LocalDate.now())
                .mealId(String.valueOf(MEAL_ID))
                .mealPlanId(String.valueOf(MEAL_PLAN_ID))
                .build();
    }

    public static MealRecordInfo buildMealRecordInfo(String relationId) {
        return MealRecordInfo.builder()
                .mealType(DINNER)
                .date(LocalDate.now())
                .mealId(String.valueOf(MEAL_ID))
                .mealPlanId(String.valueOf(MEAL_PLAN_ID))
                .relationId(relationId)
                .build();
    }

    public static Meal buildMeal() {
        Meal meal = Meal.builder()
                .id(MEAL_ID)
                .name("Star Wars Night")
                .notes("Use extra cheese")
                .build();

        meal.setMealRecipes(List.of(buildMealRecipe(meal)));
        return meal;
    }

    private static MealRecipe buildMealRecipe(Meal meal) {
        return MealRecipe.builder()
                .id(MEAL_RECIPE_ID)
                .meal(meal)
                .recipe(getTestRecipe())
                .build();
    }

    public static MealPlan buildMealPlan() {
        return MealPlan.builder()
                .id(MEAL_PLAN_ID)
                .nickname("Super Diet")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(3))
                .build();
    }

    public static MealPlan buildMealPlan(MealRecord mealRecord) {
        MealPlan mealPlan = buildMealPlan();
        mealPlan.setMealRecords(List.of(mealRecord));
        return mealPlan;
    }

    public static MealRecord buildMealRecord() {
        return MealRecord.builder()
                .id(MEAL_RECORD_ID)
                .mealPlan(buildMealPlan())
                .meal(buildMeal())
                .type(DINNER)
                .date(LocalDate.now())
                .build();
    }

    public static MealRecord buildMealRecord(MealPlan mealPlan) {
        return MealRecord.builder()
                .id(MEAL_RECORD_ID)
                .mealPlan(mealPlan)
                .meal(buildMeal())
                .type(DINNER)
                .date(LocalDate.now())
                .build();
    }

    public static MealResponse buildMealCreatedResponse() {
        MealPlan mealPlan = buildMealPlan();
        MealRecord mealRecord = buildMealRecord();
        Meal meal = buildMeal();
        return MealResponse.builder()
                .status(CREATED)
                .meals(Collections.singletonList(
                        MealResponseInfo.builder()
                                .plans(List.of(mealPlan))
                                .meals(List.of(meal))
                                .records(List.of(mealRecord))
                                .build()))
                .build();
    }

    public static MealResponse buildMealSearchResponse() {
        return MealResponse.builder()
                .status(FOUND)
                .meals(Collections.singletonList(
                        MealResponseInfo.builder()
                                .plans(List.of(buildMealPlan()))
                                .meals(List.of(buildMeal()))
                                .records(List.of(buildMealRecord()))
                                .build()))
                .build();
    }

    public static MealResponse buildMealUpdateResponse() {
        MealResponse response = buildMealCreatedResponse();
        response.setStatus(UPDATED);
        return response;
    }
}
