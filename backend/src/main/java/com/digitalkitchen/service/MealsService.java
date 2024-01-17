package com.digitalkitchen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalkitchen.entities.Meal;
import com.digitalkitchen.repository.MealsRepository;

@Service
public class MealsService {

    @Autowired
    private MealsRepository repository;

    public List<Meal> getAllMeals() {
        return repository.findAll();
    }
    
}
