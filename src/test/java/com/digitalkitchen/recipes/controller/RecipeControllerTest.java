package com.digitalkitchen.recipes.controller;

import static com.digitalkitchen.enums.ResponseStatus.*;
import static com.digitalkitchen.recipes.util.RecipeTestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.digitalkitchen.recipes.model.response.RecipeResponse;
import com.digitalkitchen.recipes.service.RecipeService;

class RecipeControllerTest {

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
        sampleResponse.setStatus(CREATED);

        when(recipeService.createRecipe(any())).thenReturn(sampleResponse);
        ResponseEntity<RecipeResponse> response = testObject.createRecipe( getTestRecipeRequest());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(sampleResponse, response.getBody());
    }

    @Test
    void testSearchRecipes() {
        RecipeResponse sampleResponse = getTestRecipeResponse();
        sampleResponse.setStatus(FOUND);

        when(recipeService.searchRecipes(any())).thenReturn(sampleResponse);
        ResponseEntity<RecipeResponse> response = testObject.searchRecipes(getRecipeSearchRequest());
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(sampleResponse, response.getBody());
    }

    @Test
    void testRetrieveRecipe() {
        RecipeResponse sampleResponse = getTestRecipeResponse();
        sampleResponse.setStatus(FOUND);

        when(recipeService.retrieveRecipe(any())).thenReturn(sampleResponse);
        ResponseEntity<RecipeResponse> response = testObject.retrieveRecipe("1");
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(sampleResponse, response.getBody());
    }

    @Test
    void testUpdateRecipe() {
        RecipeResponse sampleResponse = getTestRecipeResponse();
        sampleResponse.setStatus(UPDATED);

        when(recipeService.updateRecipe(any())).thenReturn(sampleResponse);
        ResponseEntity<RecipeResponse> response = testObject.updateRecipe(getTestRecipeRequest());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sampleResponse, response.getBody());
    }

    @Test
    void testDeleteRecipe() {
        Mockito.doNothing().when(recipeService).deleteRecipe(any());
        ResponseEntity<RecipeResponse> response = testObject.deleteRecipe(7L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }
}
