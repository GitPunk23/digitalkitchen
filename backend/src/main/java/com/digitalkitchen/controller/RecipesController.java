package com.digitalkitchen.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.digitalkitchen.controller.request.CreateRecipeRequestWrapper;
import com.digitalkitchen.entities.Recipes;
import com.digitalkitchen.service.RecipesEndpointService;
import com.digitalkitchen.service.RecipesService;

@RestController
@RequestMapping("/recipes")
@CrossOrigin(origins = "http://localhost:3000")
public class RecipesController {

    @Autowired
    private RecipesService recipesService;

    @Autowired
    private RecipesEndpointService endpointService;

    @GetMapping("/get")
    @ResponseBody
    @Transactional
    public String get() {
        return "HelloWorld";
    }

    @PostMapping("/createRecipe")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> createRecipe(@RequestBody Map<String, Object> body) {
        
        endpointService.initalizeRecipe(body);
        return ResponseEntity.ok().build();

    }

    @PostMapping("addSteps")
    public ResponseEntity<?> addSteps() {


        return ResponseEntity.ok().build();
    }

    @PostMapping("addIngredients")
    public ResponseEntity<?> addIngredients() {


        return ResponseEntity.ok().build();
    }
}
