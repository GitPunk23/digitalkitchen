package com.digitalkitchen.util;

import com.digitalkitchen.enums.Measurement;
import com.digitalkitchen.model.entities.*;
import com.digitalkitchen.model.request.RecipeRequest;
import com.digitalkitchen.model.request.RecipeRequestInfo;
import com.digitalkitchen.model.response.RecipeResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.digitalkitchen.enums.Category;

import static com.digitalkitchen.enums.Measurement.WHOLE;

public class RecipeTestUtils {

    public static Recipe getTestRecipe() {
        Recipe recipe = Recipe.builder()
            .id(1337)
            .name("bantha burgers")
            .author("Han Solo")
            .category(Category.SANDWICHES_AND_WRAPS)
            .description("mos eisley special")
            .servings(4)
            .caloriesPerServing(500)
            .notes(null)
            .ingredients(null)
            .steps(null)
            .tags(null)
            .build();

        Step step = Step.builder()
                .recipeId(recipe.getId())
                .stepNumber(1)
                .description("Just go buy some")
                .build();

        RecipeIngredient ingredient = RecipeIngredient.builder()
                .recipeId(recipe.getId())
                .ingredient(Ingredient.builder()
                        .name("Burger")
                        .build())
                .measurement(WHOLE)
                .quantity(4.0F)
                .build();

        RecipeTag tag = RecipeTag.builder()
                .recipeId(recipe.getId())
                .tag(Tag.builder()
                        .name("cheap")
                        .build())
                .build();

        recipe.setIngredients(Collections.singletonList(ingredient));
        recipe.setSteps(Collections.singletonList(step));
        recipe.setTags(Collections.singletonList(tag));
        return recipe;
    }

    public static RecipeRequest getTestRecipeRequest() {
        Recipe recipe = getTestRecipe();
        RecipeRequestInfo requestInfo = RecipeRequestInfo.builder()
            .name(recipe.getName())
            .author(recipe.getAuthor())
            .category(recipe.getCategory())
            .description(recipe.getDescription())
            .servings(recipe.getServings())
            .caloriesPerServing(recipe.getCaloriesPerServing())
            .notes(recipe.getNotes())
            .ingredients(recipe.getIngredients())
            .steps(recipe.getSteps())
            .tags(recipe.getTags())
            .build();

        List<RecipeRequestInfo> infoList = new ArrayList<>();
        infoList.add(requestInfo);

        return RecipeRequest.builder()
            .recipes(infoList)
            .build();
    }

    public static RecipeResponse getTestRecipeResponse() {
        Recipe recipe = getTestRecipe();
        ArrayList<Recipe> recipeList = new ArrayList<>();

        recipeList.add(recipe);
        return RecipeResponse.builder()
            .recipes(recipeList)
            .build();
    }
}
