package com.digitalkitchen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.digitalkitchen.entities.Recipes;
import com.digitalkitchen.service.RecipesService;

@RestController
@RequestMapping("/recipes")
public class RecipesController {

    @Autowired
    private RecipesService recipesService;

    @PostMapping("/addRecipe")
    public ResponseEntity<?> createRecipe(@RequestBody Recipes recipe) {



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
