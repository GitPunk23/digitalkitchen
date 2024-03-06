package com.digitalkitchen.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalkitchen.model.entities.RecipeIngredients;
import com.digitalkitchen.service.GroceryListService;


@RestController
@RequestMapping("/grocery")
public class GroceryListController {

    @Autowired
    private GroceryListService groceryListService;

    @PostMapping(value = "/createGroceryList", produces = "application/json")
    public ResponseEntity<?> createGroceryList(
            @RequestBody List<Map<String, Object>> recipeList
    ) throws Exception {
        try {
            System.out.println(recipeList);
            return groceryListService.createGroceryList(recipeList);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
