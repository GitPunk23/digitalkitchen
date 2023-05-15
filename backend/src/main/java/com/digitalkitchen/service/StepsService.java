package com.digitalkitchen.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalkitchen.entities.Steps;
import com.digitalkitchen.repository.StepsRepository;

@Service
public class StepsService {

    @Autowired
    private StepsRepository repository;
    
    public List<Steps> getAllSteps() {
        return repository.findAll();
    }
    
    public Optional<Steps> getStepById(int id) {
        return repository.findById(id);
    }

    public Steps addTag(Steps tag) {
        return repository.save(tag);
    }

    public void updateStep(Steps step) {
        repository.save(step);
    }

    public void deleteStepById(int id) {
        repository.deleteById(id);
    }

}

