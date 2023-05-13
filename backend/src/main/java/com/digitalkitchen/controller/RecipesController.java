package com.digitalkitchen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.digitalkitchen.service.RecipesService;

@Controller
@RequestMapping("/recipes")
public class RecipesController {

    @Autowired
    private RecipesService recipesService;

}
