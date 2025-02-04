package com.digitalkitchen.recipes.controller;

import com.digitalkitchen.authors.model.entities.Author;
import com.digitalkitchen.recipes.service.FormService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RequestMapping("/form")
@Validated
public class FormController {

    private final FormService formService;

    public FormController(FormService formService) { this.formService = formService; }

    /**
     * This returns the list of all the categories in the database
     * @return list of all categories in the database
     */
    @GetMapping(value="/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllCategories() {
        return formService.getAllCategories();
    }

    /**
     * This returns the list of all the measurements in the database
     * @return list of all measurements in the database
     */
    @GetMapping(value="/measurements", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllMeasurements() {
        return formService.getAllMeasurements();
    }

    /**
     * This returns the list of all the authors in the database
     * @return list of all authors in the database
     */
    @GetMapping(value="/authors", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllAuthors() {
        return formService.getAllAuthors();
    }

    /**
     * This returns the list of all the tags in the database
     * @return list of all tags in the database
     */
    @GetMapping(value="/tags", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllTags() {
        return formService.getAllTags();
    }

    /**
     * This returns the list of all the ingredients in the database
     * @return list of all ingredients in the database
     */
    @GetMapping(value="/ingredients", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllIngredients() {
        return formService.getAllIngredients();
    }
}
