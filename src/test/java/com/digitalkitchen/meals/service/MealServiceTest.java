package com.digitalkitchen.meals.service;

import com.digitalkitchen.meals.model.entities.Meal;
import com.digitalkitchen.meals.model.entities.MealPlan;
import com.digitalkitchen.meals.model.entities.MealRecord;
import com.digitalkitchen.meals.model.request.MealInfo;
import com.digitalkitchen.meals.model.request.MealRequest;
import com.digitalkitchen.meals.model.response.MealResponse;
import com.digitalkitchen.meals.repository.MealPlanRepository;
import com.digitalkitchen.meals.repository.MealRecordRepository;
import com.digitalkitchen.meals.repository.MealRepository;
import com.digitalkitchen.recipes.model.entities.Recipe;
import com.digitalkitchen.recipes.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static com.digitalkitchen.meals.util.MealTestUtils.*;
import static com.digitalkitchen.meals.util.TestConstants.MEAL_ID;
import static com.digitalkitchen.enums.ResponseStatus.CREATED;
import static com.digitalkitchen.recipes.util.RecipeTestUtils.getTestRecipe;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MealServiceTest {

    private MealService testObject;
    @Mock
    private MealRepository mealRepository;
    @Mock
    private MealPlanRepository mealPlanRepository;
    @Mock
    private MealRecordRepository mealRecordRepository;
    @Mock
    private RecipeRepository recipeRepository;


    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
        testObject = Mockito.spy(new MealService(mealRepository, mealPlanRepository, mealRecordRepository, recipeRepository));
    }

    @Test
    void testCreate_NewMeal_Successful() {
        List<Meal> meals = List.of(buildMeal());
        List<MealRecord> records = List.of(buildMealRecord());
        MealPlan plan = buildMealPlan();
        MealRequest request = buildCreateRequest(List.of(buildMealInfo()), buildMealPlanInfo(), List.of(buildMealRecordInfo()));
        Recipe recipe = getTestRecipe();

        when(recipeRepository.findAllById(any())).thenReturn(List.of(recipe));
        when(mealRepository.saveAll(any())).thenReturn(meals);
        when(mealPlanRepository.save(any())).thenReturn(plan);
        when(mealRecordRepository.saveAll(any())).thenReturn(records);
        MealResponse response = testObject.processCreateRequest(request);

        assertEquals(CREATED, response.getStatus());
    }

    @Test
    void testCreate_ExistingMeal_Successful() {
        List<Meal> meals = List.of(buildMeal());
        List<MealRecord> records = List.of(buildMealRecord());
        MealPlan plan = buildMealPlan();
        MealInfo mealInfo = buildMealInfo();
        mealInfo.setId(""+MEAL_ID);
        MealRequest request = buildCreateRequest(List.of(mealInfo), buildMealPlanInfo(), List.of(buildMealRecordInfo()));
        Recipe recipe = getTestRecipe();

        when(recipeRepository.findAllById(any())).thenReturn(List.of(recipe));
        when(mealRepository.saveAll(any())).thenReturn(meals);
        when(mealPlanRepository.save(any())).thenReturn(plan);
        when(mealRecordRepository.saveAll(any())).thenReturn(records);
        MealResponse response = testObject.processCreateRequest(request);

        assertEquals(CREATED, response.getStatus());
    }
}
