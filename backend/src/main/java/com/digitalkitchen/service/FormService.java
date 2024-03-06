package com.digitalkitchen.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.digitalkitchen.model.entities.Category;
import com.digitalkitchen.model.entities.Measurements;
import com.digitalkitchen.repository.CategoryRepository;
import com.digitalkitchen.repository.IngredientsRepository;
import com.digitalkitchen.repository.MeasurementsRepository;
import com.digitalkitchen.repository.TagsRepository;

@Service
public class FormService {

    private CategoryRepository categoryRepository;
    private MeasurementsRepository measurementsRepository;
    private TagsRepository tagsRepository;
    private IngredientsRepository ingredientsRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Measurements> getAllMeasurements() {
        return measurementsRepository.findAll();
    }

    public List<String> getAllTags() {
        return tagsRepository.getAllTags();
    }

    public List<String> getAllIngredients() {
        return ingredientsRepository.getAllIngredients();
    }
    
}
