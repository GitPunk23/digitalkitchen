package com.digitalkitchen.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.digitalkitchen.service.RecipesEndpointService;
import com.digitalkitchen.service.RecipesService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/recipes")
public class RecipesController {

    @Autowired
    private RecipesService recipesService;
    @Autowired
    private RecipesEndpointService endpointService;

    //GET requests

    @GetMapping("/status")
    public ResponseEntity<?> status() {
        //Return 202
        return ResponseEntity.accepted().build();
    }

    //POST requests

    @PostMapping(value = "/createRecipe", produces = "application/json")
    public ResponseEntity<?> createRecipe(
        @RequestParam(name = "bypass", defaultValue = "false") Boolean bypassFlag,
        @RequestBody Map<String, Object> body
    ) throws Exception {
        try {
            ResponseEntity<?> response = endpointService.initalizeRecipe(body, bypassFlag);
            System.out.println(response);
            return response;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/update", produces = "application/json")
    public ResponseEntity<?> updateRecipe(@RequestBody Map<String, Object> body) throws Exception {
        try {
            ResponseEntity<?> response = endpointService.updateRecipe(body);
            System.out.println(response);

            // Convert the response object to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(response.getBody());
            // Create a new ResponseEntity with the JSON response
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(jsonResponse, headers, response.getStatusCode());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/delete", produces = "application/json")
    public ResponseEntity<?> deleteRecipe(@RequestBody int recipeID) throws Exception {
        try {
            recipesService.deleteRecipeById(recipeID);
            System.out.println("Successfully removed recipe " + recipeID);
            return ResponseEntity.ok().body("Successfully removed recipe " + recipeID);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
