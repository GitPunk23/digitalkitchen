package com.digitalkitchen.meals.service;

import com.digitalkitchen.meals.model.entities.Meal;
import com.digitalkitchen.meals.model.entities.MealPlan;
import com.digitalkitchen.meals.model.entities.MealRecord;
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
import java.util.Optional;

import static com.digitalkitchen.enums.ResponseStatus.EMPTY;
import static com.digitalkitchen.enums.ResponseStatus.FOUND;
import static com.digitalkitchen.meals.util.MealTestUtils.*;
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
    void processCreateRequestTest_mealPlanOnly_success() {
        MealRequest request = buildCreateRequest(null, buildMealPlanInfo(), null);
        MealPlan plan = buildMealPlan();

        when(mealPlanRepository.save(any())).thenReturn(plan);
        MealResponse response = testObject.processCreateRequest(request);
        verify(testObject, never()).buildAndSaveMealRecords(any());
        verify(testObject, never()).buildAndSaveMeal(any());
        assertNull(response.getMeals().get(0).getMeals());
        assertNull(response.getMeals().get(0).getRecords());
    }

    @Test
    void processCreateRequestTest_mealOnly_success() {
        List<Meal> meals = List.of(buildMeal());
        MealRequest request = buildCreateRequest(List.of(buildMealInfo(null, null)), null, null);
        Recipe recipe = getTestRecipe();

        when(recipeRepository.findAllById(any())).thenReturn(List.of(recipe));
        when(mealRepository.saveAll(any())).thenReturn(meals);
        MealResponse response = testObject.processCreateRequest(request);
        assertEquals(CREATED, response.getStatus());
        verify(testObject, never()).buildAndSaveMealRecords(any());
        verify(testObject, never()).buildAndSaveMealPlan(any());
        assertNull(response.getMeals().get(0).getPlans());
        assertNull(response.getMeals().get(0).getRecords());
    }

    @Test
    void processCreateRequestTest_mealRecordOnly_success() {
        List<MealRecord> mealRecords = List.of(buildMealRecord());
        MealRequest request = buildCreateRequest(null, null, List.of(buildMealRecordInfo(null)));
        Optional<Meal> meal = Optional.of(buildMeal());
        Optional<MealPlan> mealPlan = Optional.of(buildMealPlan());

        when(mealRepository.findById(any())).thenReturn(meal);
        when(mealPlanRepository.findById(any())).thenReturn(mealPlan);
        when(mealRecordRepository.saveAll(any())).thenReturn(mealRecords);
        MealResponse response = testObject.processCreateRequest(request);
        assertEquals(CREATED, response.getStatus());
        verify(testObject, never()).buildAndSaveMeal(any());
        verify(testObject, never()).buildAndSaveMealPlan(any());
        assertNull(response.getMeals().get(0).getPlans());
        assertNull(response.getMeals().get(0).getMeals());
    }

    @Test
    void processCreateRequestTest_fullRequest_success() {
        MealRequest request = buildCreateRequest(List.of(buildMealInfo(null, "1")),
                buildMealPlanInfo(),
                List.of(buildMealRecordInfo("1")));
        List<MealRecord> mealRecords = List.of(buildMealRecord());
        Optional<Meal> meal = Optional.of(buildMeal());
        Optional<MealPlan> mealPlan = Optional.of(buildMealPlan());
        Recipe recipe = getTestRecipe();

        when(mealPlanRepository.save(any())).thenReturn(mealPlan.get());
        when(recipeRepository.findAllById(any())).thenReturn(List.of(recipe));
        when(mealRepository.saveAll(any())).thenReturn(List.of(meal.get()));
        when(mealRepository.findById(any())).thenReturn(meal);
        when(mealPlanRepository.findById(any())).thenReturn(mealPlan);
        when(mealRecordRepository.saveAll(any())).thenReturn(mealRecords);
        MealResponse response = testObject.processCreateRequest(request);
        assertEquals(CREATED, response.getStatus());
        verify(testObject, atMostOnce()).buildAndSaveMeal(any());
        verify(testObject, atMostOnce()).buildAndSaveMealRecords(any());
        verify(testObject, atMostOnce()).buildAndSaveMealPlan(any());

    }

    @Test
    void processCreateRequestTest_emptyRequest_success() {
        MealRequest request = buildCreateRequest(null, null, null);
        MealResponse response = testObject.processCreateRequest(request);
        assertEquals(EMPTY, response.getStatus());
    }

    @Test
    void getMealTest_success() {
        Meal meal = buildMeal();
        when(mealRepository.findById(any())).thenReturn(Optional.of(meal));
        MealResponse response = testObject.getMeal(meal.getId());
        assertEquals(FOUND, response.getStatus());
    }

    @Test
    void getMealPlanTest_success() {
        MealPlan mealPlan = buildMealPlan(buildMealRecord());
        when(mealPlanRepository.findById(any())).thenReturn(Optional.of(mealPlan));
        MealResponse response = testObject.getMealPlan(mealPlan.getId());
        assertEquals(FOUND, response.getStatus());
    }

    @Test
    void getMealRecordTest_success() {
        MealRecord mealRecord = buildMealRecord();
        when(mealRecordRepository.findById(any())).thenReturn(Optional.of(mealRecord));
        MealResponse response = testObject.getMealRecord(mealRecord.getId());
        assertEquals(FOUND, response.getStatus());
    }

}
