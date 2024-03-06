package com.digitalkitchen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalkitchen.model.entities.Category;
import com.digitalkitchen.model.entities.Measurements;
import com.digitalkitchen.service.FormService;
import com.digitalkitchen.service.RecipesService;

@RestController
@RequestMapping("/form")
@CrossOrigin(origins = "http://localhost:8080")
public class FormController {

    @Autowired
    private RecipesService recipesService;
    @Autowired
    private FormService formService;
    
    
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return formService.getAllCategories();
    }

    @GetMapping("/measurements")
    public List<Measurements> getAllMeasurements() {
        return formService.getAllMeasurements();
    }
    
    @GetMapping("/authors")
    public List<String> getAllAuthors() {
        return recipesService.getAllAuthors();
    }

    @GetMapping("/tags")
    public List<String> getAllTags() {
        return formService.getAllTags();
    }

    @GetMapping("/ingredients")
    public List<String> getAllIngredients() {
        return formService.getAllIngredients();
    }
}
