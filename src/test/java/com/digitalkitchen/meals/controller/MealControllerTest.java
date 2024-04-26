package com.digitalkitchen.meals.controller;

import com.digitalkitchen.meals.model.entities.Meal;
import com.digitalkitchen.meals.model.request.MealRequest;
import com.digitalkitchen.meals.model.response.MealResponse;
import com.digitalkitchen.meals.service.MealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.digitalkitchen.meals.util.MealTestUtils.*;
import static com.digitalkitchen.recipes.enums.ResponseStatus.CREATED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MealControllerTest {

    private MealController testObject;
    @Mock
    private MealService mealService;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
        testObject = Mockito.spy(new MealController(mealService));
    }

    @Test
    void testCreateMeal() {
        MealRequest mealRequest = getTestMealRequest();
        MealResponse sampleResponse = getTestMealResponse();
        sampleResponse.setStatus(CREATED);

        when(mealService.createMeal(any())).thenReturn(sampleResponse);
        ResponseEntity<MealResponse> response = testObject.createMeal(mealRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(sampleResponse, response.getBody());
    }

}
