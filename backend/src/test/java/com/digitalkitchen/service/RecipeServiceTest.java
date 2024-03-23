package com.digitalkitchen.service;

import com.digitalkitchen.model.entities.Recipe;
import com.digitalkitchen.model.request.RecipeRequest;
import com.digitalkitchen.model.response.RecipeResponse;
import com.digitalkitchen.repository.IngredientRepository;
import com.digitalkitchen.repository.RecipeRepository;
import com.digitalkitchen.repository.RecipeRepositoryExtension;
import com.digitalkitchen.repository.TagRepository;
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
    private TagRepository tagRepository;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);
        testObject = Mockito.spy(new RecipeService(recipeRepository, recipeRepositoryExtension, ingredientRepository, tagRepository));
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
}
