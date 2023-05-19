package com.digitalkitchen.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalkitchen.service.RecipesEndpointService;
import com.digitalkitchen.service.RecipesService;

@RestController
@RequestMapping("/recipes")
@CrossOrigin(origins = "http://localhost:3000")
public class RecipesController {

    @Autowired
    private RecipesService recipesService;
    @Autowired
    private RecipesEndpointService endpointService;

    //GET requests

    @GetMapping("/status")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> status() {
        //Return 202
        return ResponseEntity.accepted().build();
    }

    //POST requests

    @PostMapping("/createRecipe")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> createRecipe(@RequestBody Map<String, Object> body) throws Exception {
        try {
            ResponseEntity<?> response = endpointService.initalizeRecipe(body);
            System.out.println(response);
            return response;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
