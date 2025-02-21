package com.digitalkitchen.meals.controller;

import com.digitalkitchen.meals.model.request.MealRequest;
import com.digitalkitchen.meals.model.response.MealResponse;
import com.digitalkitchen.meals.service.MealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.digitalkitchen.meals.util.MealTestUtils.buildMealCreatedResponse;
import static com.digitalkitchen.meals.util.MealTestUtils.buildMealSearchResponse;
import static com.digitalkitchen.meals.util.MealTestUtils.buildMealUpdateResponse;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MealControllerTest {

    @InjectMocks
    private MealController mealController;

    @Mock
    private MealService mealService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mealController).build();
    }

    @Test
    void testCreateMeal() throws Exception {
        MealRequest request = new MealRequest();
        MealResponse response = buildMealCreatedResponse();

        when(mealService.processCreateRequest(request)).thenReturn(response);
        mockMvc.perform(post("/meals/create")
                        .contentType("application/json")
                        .content("{\"field\":\"value\"}"))
                .andExpect(status().isCreated());
        verify(mealService, times(1)).processCreateRequest(request);
    }

    @Test
    void testUpdateMeal() throws Exception {
        MealRequest request = new MealRequest();
        MealResponse response = buildMealUpdateResponse();

        when(mealService.processUpdateRequest(request)).thenReturn(response);
        mockMvc.perform(patch("/meals/update")
                        .contentType("application/json")
                        .content("{\"field\":\"value\"}"))
                .andExpect(status().isOk());
        verify(mealService, times(1)).processUpdateRequest(request);
    }

    @Test
    void testRetrieveMeal() throws Exception {
        Long mealId = 1L;
        MealResponse response = buildMealSearchResponse();

        when(mealService.getMeal(mealId)).thenReturn(response);
        mockMvc.perform(get("/meals/meal/{id}", mealId))
                .andExpect(status().isFound());
        verify(mealService, times(1)).getMeal(mealId);
    }

    @Test
    void testRetrieveMealRecord() throws Exception {
        Long mealRecordId = 1L;
        MealResponse response = buildMealSearchResponse();

        when(mealService.getMealRecord(mealRecordId)).thenReturn(response);
        mockMvc.perform(get("/meals/mealrecord/{id}", mealRecordId))
                .andExpect(status().isFound());
        verify(mealService, times(1)).getMealRecord(mealRecordId);
    }

    @Test
    void testRetrieveMealPlan() throws Exception {
        Long planId = 1L;
        MealResponse response = buildMealSearchResponse();

        when(mealService.getMealPlan(planId)).thenReturn(response);
        mockMvc.perform(get("/meals/mealplan/{id}", planId))
                .andExpect(status().isFound());
        verify(mealService, times(1)).getMealPlan(planId);
    }

    @Test
    void testRetrieveMealPlans() throws Exception {
        mockMvc.perform(get("/meals/mealplans"))
                .andExpect(status().isFound());
        verify(mealService, times(1)).getMealPlans();
    }
}
