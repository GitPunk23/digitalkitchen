package com.digitalkitchen.service;

import com.digitalkitchen.controller.RecipeController;
import com.digitalkitchen.model.request.RecipeRequest;
import com.digitalkitchen.model.response.RecipeResponse;
import com.digitalkitchen.repository.RecipeRepository;
import com.digitalkitchen.util.RecipeTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecipeServiceTest {

    private RecipeService testObject;
    @Mock
    private RecipeRepository recipeRepository;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
        testObject = Mockito.spy(new RecipeService(recipeRepository));
    }

    @Test
    void testCreateRecipe() {
        RecipeRequest request = RecipeTestUtils.getTestRecipeRequest();

        RecipeResponse response = testObject.createRecipe(request, false);
        assertNotNull(response.getRecipes());
    }
}
