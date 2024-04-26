package com.digitalkitchen.meals.service;

import com.digitalkitchen.meals.model.entities.Meal;
import com.digitalkitchen.meals.model.request.MealRequest;
import com.digitalkitchen.meals.model.response.MealResponse;
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

import static com.digitalkitchen.meals.util.MealTestUtils.getTestMeal;
import static com.digitalkitchen.meals.util.MealTestUtils.getTestMealRequest;
import static com.digitalkitchen.recipes.util.RecipeTestUtils.getTestRecipe;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MealServiceTest {

    private MealService testObject;
    @Mock
    private MealRepository mealRepository;
    @Mock
    private RecipeRepository recipeRepository;


    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
        testObject = Mockito.spy(new MealService(mealRepository, recipeRepository));
    }

    @Test
    void testCreateMeal() {
        MealRequest request = getTestMealRequest();
        Recipe recipe = getTestRecipe();

        when(recipeRepository.findAllById(any())).thenReturn(List.of(recipe));
        MealResponse response = testObject.createMeal(request);
        verify(mealRepository, times(1)).save(any());
        assertNotNull(response.getMeals());
    }
}
