package com.digitalkitchen.meals.util;

import com.digitalkitchen.meals.model.entities.Meal;
import com.digitalkitchen.meals.model.entities.MealPlan;
import com.digitalkitchen.meals.model.entities.MealRecipe;
import com.digitalkitchen.meals.model.entities.MealRecord;
import com.digitalkitchen.meals.model.request.MealRecordInfo;
import com.digitalkitchen.meals.model.request.MealRequest;
import com.digitalkitchen.meals.model.request.MealInfo;
import com.digitalkitchen.meals.model.request.MealPlanInfo;

import java.time.LocalDate;
import java.util.List;

import static com.digitalkitchen.meals.enums.MealType.DINNER;
import static com.digitalkitchen.meals.util.TestConstants.*;
import static com.digitalkitchen.recipes.util.RecipeTestUtils.getTestRecipe;

public class MealTestUtils {

    public static MealRequest buildCreateRequest(List<MealInfo> mealInfo, MealPlanInfo planInfo, List<MealRecordInfo> recordInfo) {
        return MealRequest.builder()
                .plan(planInfo)
                .meals(mealInfo)
                .records(recordInfo)
                .build();
    }

    public static MealInfo buildMealInfo() {
        return MealInfo.builder()
                .name("Star Wars Night")
                .recipeIds(List.of(RECIPE_ID))
                .relationId("R01")
                .build();
    }

    public static MealPlanInfo buildMealPlanInfo() {
        return MealPlanInfo.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(3))
                .build();
    }

    public static MealRecordInfo buildMealRecordInfo() {
        return MealRecordInfo.builder()
                .mealType(DINNER)
                .date(LocalDate.now())
                .relationId("R01")
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
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(3))
                .build();
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
}
