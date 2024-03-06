package com.digitalkitchen.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mock;

import com.digitalkitchen.model.request.RecipeRequest;
import com.digitalkitchen.repository.RecipesRepository;
import com.digitalkitchen.util.RecipeServiceTestUtils;

public class RecipeServiceTest {

    RecipeService testObject;

    @Mock
    RecipesRepository recipesRepository;
    
    public void BeforeEach() {

    }

    @Test
    public void createRecipeTest() throws Exception {
        RecipeRequest request = RecipeServiceTestUtils.getRecipeRequest();

        when(recipesRepository.existsByNameAndAuthor(any(), any())).thenReturn(false);
        testObject.createRecipe(null, false);


    }


}
