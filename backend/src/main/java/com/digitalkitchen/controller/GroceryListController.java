package com.digitalkitchen.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/grocery")
public class GroceryListController {
    
    @PostMapping(value = "/createGroceryList", produces = "application/json")
    public ResponseEntity<?> createGroceryList() {
        return null;
    }

}
