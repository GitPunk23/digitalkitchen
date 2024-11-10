package com.digitalkitchen.meals.controller;

import com.digitalkitchen.meals.model.request.MealRequest;
import com.digitalkitchen.meals.model.response.MealResponse;
import com.digitalkitchen.meals.service.MealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/meals")
@Validated
public class MealController {

    private final MealService mealService;

    public MealController(final MealService mealService) { this.mealService = mealService; }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MealResponse> create(@RequestBody @Valid MealRequest request) {
        MealResponse response = mealService.processCreateRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MealResponse> update(@RequestBody @Valid MealRequest request) {
        MealResponse response = mealService.processUpdateRequest(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/meal/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<MealResponse> retrieveMeal(final @PathVariable("id") Long mealId) {
        MealResponse response = mealService.getMeal(mealId);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @GetMapping(value = "/mealrecord/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<MealResponse> retrieveMealRecord(final @PathVariable("id") Long mealRecordId) {
        MealResponse response = mealService.getMealRecord(mealRecordId);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @GetMapping(value = "/mealplan/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<MealResponse> retrieveMealPlan(final @PathVariable("id") Long planId) {
        MealResponse response = mealService.getMealPlan(planId);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }
}
