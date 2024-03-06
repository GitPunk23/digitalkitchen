package com.digitalkitchen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalkitchen.model.entities.Appliances;
import com.digitalkitchen.repository.AppliancesRepository;

@Service
public class AppliancesService {
    
    @Autowired
    private AppliancesRepository appliancesRepository;

    public List<Appliances> getAllAppliances() {
        return appliancesRepository.findAll();
    }
}
