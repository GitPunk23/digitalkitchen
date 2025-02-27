package com.digitalkitchen.meals.service;

import com.digitalkitchen.meals.model.entities.Meal;
import com.digitalkitchen.meals.model.entities.MealPlan;
import com.digitalkitchen.meals.model.entities.MealRecord;
import com.digitalkitchen.meals.model.request.MealRequest;
import com.digitalkitchen.meals.model.response.MealResponse;
import com.digitalkitchen.meals.repository.MealPlanRepository;
import com.digitalkitchen.meals.repository.MealRecordRepository;
import com.digitalkitchen.meals.repository.MealRepository;
import com.digitalkitchen.meals.repository.MealRepositoryExtension;
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
import static com.digitalkitchen.enums.ResponseStatus.UPDATED;
import static com.digitalkitchen.meals.util.MealTestUtils.*;
import static com.digitalkitchen.enums.ResponseStatus.CREATED;
import static com.digitalkitchen.meals.util.TestConstants.MEAL_ID;
import static com.digitalkitchen.meals.util.TestConstants.MEAL_RECIPE_ID;
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
    @Mock
    private MealRepositoryExtension mealRepositoryExtension;


    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
        testObject = Mockito.spy(new MealService(mealRepository, mealPlanRepository, mealRecordRepository, recipeRepository, mealRepositoryExtension));
    }

    @Test
    void processCreateRequestTest_mealPlanOnly_success() {
        MealRequest request = buildRequest(null, buildMealPlanInfo(), null);
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
        MealRequest request = buildRequest(List.of(buildMealInfo(null, null)), null, null);
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
        MealRequest request = buildRequest(null, null, List.of(buildMealRecordInfo(null)));
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
        MealRequest request = buildRequest(List.of(buildMealInfo(null, "1")),
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
        MealRequest request = buildRequest(null, null, null);
        MealResponse response = testObject.processCreateRequest(request);
        assertEquals(EMPTY, response.getStatus());
    }

    @Test
    void processUpdateRequestTest_mealOnly_success() {
        MealRequest request = buildRequest(List.of(buildMealInfo(String.valueOf(MEAL_ID), null)), null, null);
        request.getMeals().get(0).setMealRecipeIds(List.of(MEAL_RECIPE_ID));

        when(mealRepository.findById(any())).thenReturn(Optional.of(buildMeal()));
        MealResponse response = testObject.processUpdateRequest(request);
        assertEquals(UPDATED, response.getStatus());
        verify(mealPlanRepository, never()).save(any());
        verify(mealRepository, atMostOnce()).save(any());
        verify(mealRecordRepository, never()).save(any());
    }

    @Test
    void processUpdateRequestTest_mealPlanOnly_success() {
        MealRequest request = buildRequest(null, buildMealPlanInfo(), null);

        when(mealPlanRepository.findById(any())).thenReturn(Optional.of(buildMealPlan()));
        MealResponse response = testObject.processUpdateRequest(request);
        assertEquals(UPDATED, response.getStatus());
        verify(mealPlanRepository, atMostOnce()).save(any());
        verify(mealRepository, never()).save(any());
        verify(mealRecordRepository, never()).save(any());
    }

    @Test
    void processUpdateRequestTest_mealRecordOnly_success() {
        MealRequest request = buildRequest(null, null, List.of(buildMealRecordInfo()));

        when(mealRecordRepository.findById(any())).thenReturn(Optional.of(buildMealRecord()));
        MealResponse response = testObject.processUpdateRequest(request);
        assertEquals(UPDATED, response.getStatus());
        verify(mealPlanRepository, never()).save(any());
        verify(mealRepository, never()).save(any());
        verify(mealRecordRepository, atMostOnce()).save(any());
    }

    @Test
    void processUpdateRequestTest_fullRequest_success() {
        MealRequest request = buildRequest(List.of(buildMealInfo(String.valueOf(MEAL_ID), null)), buildMealPlanInfo(), List.of(buildMealRecordInfo()));
        request.getMeals().get(0).setMealRecipeIds(List.of(MEAL_RECIPE_ID));

        when(mealRepository.findById(any())).thenReturn(Optional.of(buildMeal()));
        when(mealPlanRepository.findById(any())).thenReturn(Optional.of(buildMealPlan()));
        when(mealRecordRepository.findById(any())).thenReturn(Optional.of(buildMealRecord()));
        MealResponse response = testObject.processUpdateRequest(request);
        assertEquals(UPDATED, response.getStatus());
        verify(mealPlanRepository, atMostOnce()).save(any());
        verify(mealRepository, atMostOnce()).save(any());
        verify(mealRecordRepository, atMostOnce()).save(any());
    }

    @Test
    void processUpdateRequestTest_emptyRequest_success() {
        MealRequest request = buildRequest(null, null, null);
        MealResponse response = testObject.processUpdateRequest(request);
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
