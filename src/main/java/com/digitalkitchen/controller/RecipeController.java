package com.digitalkitchen.controller;

import com.digitalkitchen.model.request.RecipeSearchRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.digitalkitchen.model.request.RecipeRequest;
import com.digitalkitchen.model.response.RecipeResponse;
import com.digitalkitchen.service.RecipeService;

import static com.digitalkitchen.enums.ResponseStatus.*;

@RestController
@RequestMapping("/recipes")
@Validated
@CrossOrigin(origins = "http://localhost:3000")
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
        HttpStatus status = response.getStatus().equals(CREATED) ? HttpStatus.CREATED : HttpStatus.CONFLICT;
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<RecipeResponse> searchRecipes(@RequestBody RecipeSearchRequest searchParams) {
        RecipeResponse response = recipeService.searchRecipes(searchParams);
        HttpStatus status = response.getStatus().equals(FOUND) ? HttpStatus.FOUND : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<RecipeResponse> retrieveRecipe(final @PathVariable("id") String recipeId) {
        RecipeResponse response = recipeService.retrieveRecipe(recipeId);
        HttpStatus status = response.getStatus().equals(FOUND) ? HttpStatus.FOUND : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @PatchMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RecipeResponse> updateRecipe(@RequestBody RecipeRequest request) {
        RecipeResponse response = recipeService.updateRecipe(request);
        HttpStatus status = response.getStatus().equals(UPDATED) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(value = "/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<RecipeResponse> deleteRecipe(final @RequestParam int recipeId) {
        recipeService.deleteRecipe(recipeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
