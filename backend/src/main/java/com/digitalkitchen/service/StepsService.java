package com.digitalkitchen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalkitchen.entities.Recipes;
import com.digitalkitchen.entities.Steps;
import com.digitalkitchen.repository.StepsRepository;

@Service
public class StepsService {

    @Autowired
    private StepsRepository repository;
    
    public List<Steps> getAllSteps() {
        return repository.findAll();
    }

    public List<Steps> getAllStepsByRecipe(Recipes recipe) {
        return repository.findAllByRecipe(recipe);
    }
    
    public Optional<Steps> getStepById(int id) {
        return repository.findById(id);
    }

    public Steps addStep(Steps tag) {
        return repository.save(tag);
    }

    public List<Steps> addSteps(List<Steps> steps) {
        List<Steps> newSteps = new ArrayList();
        Steps step;

        for (int i = 0; i < steps.size(); i++) {
            step = this.addStep(steps.get(i));
            newSteps.add(step);
        }
        return newSteps;
    }

    public void updateStep(Steps step) {
        repository.save(step);
    }

    public void deleteStepById(int id) {
        repository.deleteById(id);
    }

}

