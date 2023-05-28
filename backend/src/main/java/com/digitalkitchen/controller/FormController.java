package com.digitalkitchen.controller;

import java.beans.Expression;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalkitchen.entities.Category;
import com.digitalkitchen.entities.Measurements;
import com.digitalkitchen.entities.Recipes;
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
    @Autowired
    private EntityManager entityManager;
    
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
    
    /**
     * This returns the list of all the authors in the database
     * @return list of all authors in the database
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/authors")
    public List<String> getAllAuthors() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
        Root<Recipes> root = query.from(Recipes.class);
    
        // Create a predicate to group by author and select distinct authors
        query.groupBy(root.get("author"));
        query.select(root.get("author"));
    
        List<String> authors = entityManager.createQuery(query).getResultList();
    
        return authors;
    }
}
