package com.digitalkitchen.meals.util;

import com.digitalkitchen.meals.model.entities.Meal;
import com.digitalkitchen.meals.model.entities.MealPlan;
import com.digitalkitchen.meals.model.entities.MealRecipe;
import com.digitalkitchen.meals.model.request.MealRequest;
import com.digitalkitchen.meals.model.request.MealRequestMealInfo;
import com.digitalkitchen.meals.model.request.MealRequestPlanInfo;
import com.digitalkitchen.meals.model.response.MealResponse;
import com.digitalkitchen.meals.model.response.MealResponseInfo;
import com.digitalkitchen.recipes.model.entities.Recipe;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static com.digitalkitchen.meals.enums.MealType.DINNER;
import static com.digitalkitchen.recipes.util.RecipeTestUtils.getTestRecipe;

public class MealTestUtils {

    public static Meal getTestMeal() {
        Meal meal = Meal.builder()
            .id(555)
            .name("Star Wars Night")
            .date(LocalDate.now())
            .type(DINNER)
            .notes("Use extra cheese")
            .build();

        MealRecipe mealRecipe = MealRecipe.builder()
            .meal(meal)
            .recipe(getTestRecipe())
            .build();

        meal.setMealRecipes(Collections.singletonList(mealRecipe));
        return meal;
    }

    public static MealPlan getTestMealPlan_OneWeek() {
        return MealPlan.builder()
                .id(666)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusWeeks(1))
                .build();
    }

    public static MealResponse getTestMealResponse_NoPlan() {
        Meal meal = getTestMeal();

        return MealResponse.builder()
            .meals(List.of(
                MealResponseInfo.builder()
                .plan(null)
                .meals(List.of(meal))
                .build()
            ))
            .build();
    }

    public static MealResponse getTestMealResponse_WithPlan() {
        MealPlan mealPlan = getTestMealPlan_OneWeek();
        Meal meal = getTestMeal();
        meal.setMealPlanId(mealPlan.getId());

        return MealResponse.builder()
                .meals(List.of(
                        MealResponseInfo.builder()
                                .plan(mealPlan)
                                .meals(List.of(meal))
                                .build()
                ))
                .build();
    }

    public static MealRequest getTestMealRequest_WithPlan() {
        MealRequest mealRequest = getTestMealRequest_NoPlan();
        mealRequest.setPlan(
            MealRequestPlanInfo.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusWeeks(1))
                .build());

        return mealRequest;
    }

    public static MealRequest getTestMealRequest_NoPlan() {
        Meal meal = getTestMeal();
        MealRequest mealRequest = MealRequest.builder()
            .plan(null)
            .meals(List.of(
                MealRequestMealInfo.builder()
                .name(meal.getName())
                .date(meal.getDate())
                .type(meal.getType())
                .notes(meal.getNotes())
                .recipeIds(meal.getMealRecipes().stream()
                    .map(MealRecipe::getRecipe)
                    .map(Recipe::getId)
                    .toList())
                .build()
            ))
            .build();
        return mealRequest;
    }

    public static MealRequestPlanInfo getTestMealRequestPlanInfo() {
        return MealRequestPlanInfo.builder()
            .id(7)
            .startDate(LocalDate.now())
            .endDate(LocalDate.now().plusWeeks(1))
            .build();
    }
}
