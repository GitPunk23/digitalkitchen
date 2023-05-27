package com.digitalkitchen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalkitchen.entities.Category;
import com.digitalkitchen.entities.Measurements;
import com.digitalkitchen.service.CategoryService;
import com.digitalkitchen.service.MeasurementsService;

@RestController
@RequestMapping("/form")
@CrossOrigin(origins = "http://localhost:3000")
public class FormController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private MeasurementsService measurementsService;
    
    /**
     * This returns the list of all the categories in the database
     * @return list of all categories in the database
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    /**
     * This returns the list of all the measurements in the database
     * @return list of all measurements in the database
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/measurements")
    public List<Measurements> getAllMeasurements() {
        return measurementsService.getAllMeasurements();
    }    
}
