package com.digitalkitchen.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalkitchen.model.entities.Measurements;
import com.digitalkitchen.repository.MeasurementsRepository;

@Service
public class MeasurementsService {

    @Autowired
    private MeasurementsRepository repository;
    
    public List<Measurements> getAllMeasurements() {
        return repository.findAll();
    }
    
    public Optional<Measurements> getMeasurementById(int id) {
        return repository.findById(id);
    }

    public Measurements addMeasurement(Measurements measurement) {
        return repository.save(measurement);
    }

    public void updateMeasurement(Measurements measurement) {
        repository.save(measurement);
    }

    public void deleteMeasurementById(int id) {
        repository.deleteById(id);
    }

    public Optional<Measurements> getMeasurementByName(String updatedName) {
        return repository.findByMeasurement(updatedName);
    }

}

