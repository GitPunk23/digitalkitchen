package com.digitalkitchen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RecipeResponse> createRecipe( @RequestParam(name = "bypass", defaultValue = "false") Boolean bypassFlag,
                                                        @RequestBody RecipeRequest request) {
        RecipeResponse response = recipeService.createRecipe(request, bypassFlag);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<RecipeResponse> searchRecipes(@RequestParam String param) {
        return null;
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<RecipeResponse> retrieveRecipe(final @PathVariable("id") String id) {
        return null;
    }

    @PatchMapping(value = "/update")
    public ResponseEntity<RecipeResponse> updateRecipe(@RequestBody RecipeRequest request) {
        recipeService.updateRecipe(request);
        return null;
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<RecipeResponse> deleteRecipe(final @RequestParam int recipeID) {
        recipeService.deleteRecipe(recipeID);
        return null;
    }
}
