package com.digitalkitchen.service;

import com.digitalkitchen.model.entities.Recipe;
import com.digitalkitchen.model.entities.RecipeIngredient;
import com.digitalkitchen.model.entities.RecipeTag;
import com.digitalkitchen.model.request.RecipeRequest;
import com.digitalkitchen.model.response.RecipeResponse;
import com.digitalkitchen.repository.*;
import com.digitalkitchen.util.RecipeTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RecipeServiceTest {

    private RecipeService testObject;
    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private RecipeRepositoryExtension recipeRepositoryExtension;
    @Mock
    private IngredientRepository ingredientRepository;
    @Mock
    private RecipeIngredientRepository recipeIngredientRepository;
    @Mock
    private StepRepository stepRepository;
    @Mock
    private TagRepository tagRepository;
    @Mock
    private RecipeTagRepository recipeTagRepository;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
        testObject = Mockito.spy(new RecipeService(recipeRepository, recipeRepositoryExtension, ingredientRepository,
                recipeIngredientRepository, stepRepository, tagRepository, recipeTagRepository));
    }

    @Test
    void testCreateRecipe() {
        Recipe recipe = RecipeTestUtils.getTestRecipe();
        RecipeRequest request = RecipeTestUtils.getTestRecipeRequest();

        when(recipeRepository.findByNameAndAuthor(any(), any())).thenReturn(Optional.empty());
        when(ingredientRepository.save(any())).thenReturn(recipe.getIngredients().get(0).getIngredient());
        when(tagRepository.save(any())).thenReturn(recipe.getTags().get(0).getTag());
        when(recipeRepository.save(any())).thenReturn(recipe);
        RecipeResponse response = testObject.createRecipe(request, false);
        verify(recipeRepository, times(1)).save(any());
        assertNotNull(response.getRecipes());
    }

    @Test
    void testCreateRecipe_Duplicate() {
        Recipe recipe = RecipeTestUtils.getTestRecipe();
        RecipeRequest request = RecipeTestUtils.getTestRecipeRequest();

        when(recipeRepository.findByNameAndAuthor(any(), any())).thenReturn(Optional.of(recipe));
        RecipeResponse response = testObject.createRecipe(request, false);
        assertNotNull(response.getRecipes());
    }

    @Test
    void testRetrieveRecipe() {
        Recipe recipe = RecipeTestUtils.getTestRecipe();
        String recipeId = String.valueOf(recipe.getId());

        when(recipeRepository.findById(any())).thenReturn(Optional.of(recipe));
        RecipeResponse response = testObject.retrieveRecipe(recipeId);
        assertNotNull(response.getRecipes());
    }

    @Test
    void testUpdateRecipe() {
        Recipe recipe = RecipeTestUtils.getTestRecipe();
        RecipeRequest request = RecipeTestUtils.getTestRecipeRequest();

        when(recipeRepository.findById(any())).thenReturn(Optional.of(recipe));
        when(ingredientRepository.save(any())).thenReturn(recipe.getIngredients().get(0).getIngredient());
        when(tagRepository.save(any())).thenReturn(recipe.getTags().get(0).getTag());
        when(recipeRepository.save(any())).thenReturn(recipe);
        RecipeResponse response = testObject.updateRecipe(request);
        verify(recipeRepository, times(1)).save(any());
        assertNotNull(response.getRecipes());
    }
}
