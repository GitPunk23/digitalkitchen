package com.digitalkitchen.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.digitalkitchen.entities.Recipes;
import com.digitalkitchen.entities.Category;
import com.digitalkitchen.service.CategoryService;
import com.digitalkitchen.service.RecipesService;

@RestController
@RequestMapping("/recipes")
@CrossOrigin(origins = "http://localhost:3000")
public class RecipesController {

    @Autowired
    private RecipesService recipesService;

    @GetMapping("/get")
    @ResponseBody
    @Transactional
    public String get() {
        return "HelloWorld";
    }

    @PostMapping("/createRecipe")
    public ResponseEntity<?> createRecipe(@RequestBody Recipes recipe) {
        
        recipesService.addRecipe(recipe);
        Map<String, Integer> response = new HashMap<>();
        response.put("recipeId", recipe.getID());
        return ResponseEntity.ok().body(response);

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
