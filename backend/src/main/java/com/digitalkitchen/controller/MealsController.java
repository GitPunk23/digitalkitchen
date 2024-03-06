package com.digitalkitchen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalkitchen.model.entities.Meal;
import com.digitalkitchen.service.MealsService;

@RestController
@RequestMapping("/meals")
public class MealsController {

    @Autowired
    private MealsService mealsService;

    @GetMapping
    public ResponseEntity<List<Meal>> getMeals() {
        List<Meal> meals = mealsService.getAllMeals();
        return ResponseEntity.ok(meals);
    }

}
