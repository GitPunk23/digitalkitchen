package com.digitalkitchen.recipes.controller;

import com.digitalkitchen.recipes.model.request.RecipeSearchRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.digitalkitchen.recipes.model.request.RecipeRequest;
import com.digitalkitchen.recipes.model.response.RecipeResponse;
import com.digitalkitchen.recipes.service.RecipeService;

import static com.digitalkitchen.enums.ResponseStatus.*;

@RestController
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RequestMapping("/recipes")
@Validated
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(final RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RecipeResponse> createRecipe(@RequestBody RecipeRequest request) {
        RecipeResponse response = recipeService.createRecipe(request);
        HttpStatus status = response.getStatus().equals(CREATED) ? HttpStatus.CREATED : HttpStatus.CONFLICT;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RecipeResponse> searchRecipes(@RequestBody RecipeSearchRequest searchParams) {
        RecipeResponse response = recipeService.searchRecipes(searchParams);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RecipeResponse> retrieveRecipe(final @PathVariable("id") String recipeId) {
        RecipeResponse response = recipeService.retrieveRecipe(recipeId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/recipes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RecipeResponse> retrieveRecipes() {
        RecipeResponse response = recipeService.retrieveRecipes();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RecipeResponse> updateRecipe(@RequestBody RecipeRequest request) {
        RecipeResponse response = recipeService.updateRecipe(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RecipeResponse> deleteRecipe(final @RequestParam Long recipeId) {
        recipeService.deleteRecipe(recipeId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
