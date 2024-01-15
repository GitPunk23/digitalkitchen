package com.digitalkitchen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalkitchen.entities.RecipeAppliances;
import com.digitalkitchen.repository.RecipeAppliancesRepository;

@Service
public class RecipeAppliancesService {
    
    @Autowired
    private RecipeAppliancesRepository repository;

    public List<RecipeAppliances> getAllAppliances() {
        return repository.findAll();
    }
}