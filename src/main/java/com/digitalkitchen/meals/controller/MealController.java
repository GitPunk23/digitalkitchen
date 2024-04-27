package com.digitalkitchen.meals.controller;

import com.digitalkitchen.meals.model.request.MealRequest;
import com.digitalkitchen.meals.model.response.MealResponse;
import com.digitalkitchen.meals.service.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meals")
@Validated
public class MealController {

    private final MealService mealService;

    public MealController(final MealService mealService) { this.mealService = mealService; }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MealResponse> createMeal(@RequestBody MealRequest request) {
        MealResponse response = mealService.createMeal(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(value = "/create-plan", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MealResponse> createMealPlan(@RequestBody MealRequest request) {
        MealResponse response = mealService.createMealPlan(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
