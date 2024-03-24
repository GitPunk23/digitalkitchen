package com.digitalkitchen.controller;

import com.digitalkitchen.model.request.RecipeSearchRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.digitalkitchen.model.request.RecipeRequest;
import com.digitalkitchen.model.response.RecipeResponse;
import com.digitalkitchen.service.RecipeService;

@RestController
@RequestMapping("/recipes")
@Validated
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(final RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RecipeResponse> createRecipe( @RequestParam(name = "bypass", defaultValue = "false") Boolean bypassFlag,
                                                        @RequestBody RecipeRequest request) {
        RecipeResponse response = recipeService.createRecipe(request, bypassFlag);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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

    @PatchMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public ResponseEntity<RecipeResponse> updateRecipe(@RequestBody RecipeRequest request) {
        //RecipeResponse response = recipeService.updateRecipe(request);
        //return ResponseEntity.status(HttpStatus.CREATED).body(response);
        /* Method is receiving several issues that are very hard to debug. Would be best to design a better update algorithm
        from scratch */
        return null;
    }

    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<RecipeResponse> deleteRecipe(final @RequestParam int recipeId) {
        recipeService.deleteRecipe(recipeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
