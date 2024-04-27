package com.digitalkitchen.meals.service;

import com.digitalkitchen.meals.model.request.MealRequest;
import com.digitalkitchen.meals.model.response.MealResponse;
import com.digitalkitchen.meals.model.response.MealResponseInfo;
import com.digitalkitchen.meals.repository.MealPlanRepository;
import com.digitalkitchen.meals.repository.MealRepository;
import com.digitalkitchen.recipes.model.entities.Recipe;
import com.digitalkitchen.recipes.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static com.digitalkitchen.meals.util.MealTestUtils.getTestMealRequest_NoPlan;
import static com.digitalkitchen.meals.util.MealTestUtils.getTestMealRequest_WithPlan;
import static com.digitalkitchen.recipes.util.RecipeTestUtils.getTestRecipe;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MealServiceTest {

    private MealService testObject;
    @Mock
    private MealRepository mealRepository;
    @Mock
    private MealPlanRepository mealPlanRepository;
    @Mock
    private RecipeRepository recipeRepository;


    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
        testObject = Mockito.spy(new MealService(mealRepository, mealPlanRepository, recipeRepository));
    }

    @Test
    void testCreateMeal() {
        MealRequest request = getTestMealRequest_NoPlan();
        Recipe recipe = getTestRecipe();

        when(recipeRepository.findAllById(any())).thenReturn(List.of(recipe));
        MealResponse response = testObject.createMeal(request);
        verify(mealRepository, times(1)).save(any());
        assertNotNull(response.getMeals());
    }

    @Test
    void testCreateMealPlan() {
        MealRequest request = getTestMealRequest_WithPlan();
        Recipe recipe = getTestRecipe();

        when(recipeRepository.findAllById(any())).thenReturn(List.of(recipe));
        MealResponse response = testObject.createMealPlan(request);
        verify(mealRepository, times(1)).save(any());
        verify(mealPlanRepository, times(1)).save(any());
        MealResponseInfo responseInfo = response.getMeals().get(0);
        assertNotNull(responseInfo.getPlan());
        assertNotNull(responseInfo.getMeals());








    }
}
