package com.digitalkitchen.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalkitchen.model.entities.RecipeIngredients;
import com.digitalkitchen.model.entities.Recipes;
import com.digitalkitchen.model.entities.Steps;
import com.digitalkitchen.repository.StepsRepository;

@Service
public class StepsService {

    @Autowired
    private StepsRepository repository;
    
    public List<Steps> getAllSteps() {
        return repository.findAll();
    }

    public List<Steps> getAllStepsByRecipe(Recipes recipe) {
        return repository.findAllByRecipe(recipe).stream()
            .sorted(Comparator.comparingInt(Steps::getStepNumber))
            .collect(Collectors.toList());
    }
    
    public Optional<Steps> getStepById(int id) {
        return repository.findById(id);
    }

    public List<Steps> getStepsFromJSON(Recipes recipe, List<Map<String, Object>> newSteps) {
        ArrayList<Steps> stepList = new ArrayList<>();

        for (int index = 0; index < newSteps.size(); index++) {
            stepList.add(new Steps(
                recipe, 
                (int) newSteps.get(index).get("stepNumber"), 
                (String) newSteps.get(index).get("description")));
        }

        return stepList;
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

    public void updateAllSteps(List<Steps> newSteps) {
        List<Steps> currentSteps = getAllStepsByRecipe(newSteps.get(0).getRecipe());
        Steps currentStep;
        Steps newStep;

        for (int i = 0; i < newSteps.size(); i++) {
            newStep = newSteps.get(i);
            if (i < currentSteps.size()) {
                currentStep = currentSteps.get(i);
                currentStep.setDescription(newStep.getDescription());
                repository.save(currentStep);
            } else {
                repository.save(newStep);
            }
        }
        
        for (int i = newSteps.size(); i < currentSteps.size(); i++) {
            Steps stepToDelete = currentSteps.get(i);
            repository.delete(stepToDelete);
        }
    }

    public void deleteStepById(int id) {
        repository.deleteById(id);
    }

}

