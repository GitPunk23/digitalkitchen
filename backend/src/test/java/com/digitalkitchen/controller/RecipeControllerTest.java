package com.digitalkitchen.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.digitalkitchen.model.request.RecipeRequest;
import com.digitalkitchen.model.response.RecipeResponse;
import com.digitalkitchen.service.RecipeService;

import static com.digitalkitchen.util.RecipeTestUtils.getTestRecipeRequest;
import static com.digitalkitchen.util.RecipeTestUtils.getTestRecipeResponse;

public class RecipeControllerTest {

    private RecipeController testObject;
    @Mock
    private RecipeService recipeService;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
        testObject = Mockito.spy(new RecipeController(recipeService));
    }

    @Test
    void testCreateRecipe() {
        RecipeResponse sampleResponse = getTestRecipeResponse();

        when(recipeService.createRecipe(any(), anyBoolean())).thenReturn(sampleResponse);
        ResponseEntity<RecipeResponse> response = testObject.createRecipe(false, getTestRecipeRequest());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(sampleResponse, response.getBody());
    }

    @Test
    void testUpdateRecipe() {
        RecipeResponse sampleResponse = getTestRecipeResponse();

        when(recipeService.updateRecipe(any())).thenReturn(sampleResponse);
        ResponseEntity<RecipeResponse> response = testObject.updateRecipe(getTestRecipeRequest());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(sampleResponse, response.getBody());
    }
}
