package com.digitalkitchen.meals.controller;

import com.digitalkitchen.meals.service.MealService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MealControllerITTest {

    private MealController testObject;
    @Mock
    private MealService mealService;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
        testObject = Mockito.spy(new MealController(mealService));
    }
}
