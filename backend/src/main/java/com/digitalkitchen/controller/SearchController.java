package com.digitalkitchen.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digitalkitchen.entities.Category;
import com.digitalkitchen.entities.Ingredients;
import com.digitalkitchen.entities.Recipes;
import com.digitalkitchen.entities.Tags;
import com.digitalkitchen.service.CategoryService;
import com.digitalkitchen.service.IngredientsService;
import com.digitalkitchen.service.TagsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.digitalkitchen.service.RecipesService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SearchController {

    @Autowired
    private RecipesService recipesService;
    @Autowired
    private IngredientsService ingredientsService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagsService tagsService;

    // GET
    @PostMapping(value = "/search", produces = "application/json")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> searchRecipes(@RequestBody Map<String, Object> body) throws Exception {

        try {
            Map<String, Object> searchDict = new HashMap<>();

            //Name
            searchDict.put("name", body.get("name"));

            //Authors
            if (body.get("authors") != null) {
                List<String> authors = (List<String>) body.get("authors");
                for (String author : authors) {
                    searchDict.put("authors", author);
                }
            }

            //Servings
            if (body.get("servings") != "") {
                searchDict.put("servings", (int) body.get("servings"));
            } 

            //Calories
            if (body.get("calories") != "") {
                searchDict.put("calories", (int) body.get("calories"));
            }

            //Categories
            if (body.get("categories") != null) {
                List<String> categories = (List<String>) body.get("categories");
                for (String category : categories) {
                    Optional<Category> optCat = categoryService.getCategoryByName(category);
                    if (optCat.isPresent()) {
                        searchDict.put("categories", category);
                    } else {
                        throw new Exception();
                    }
                }
            }

            //Ingredients
            if (body.get("ingredients") != null) {
                List<String> ingredients = (List<String>) body.get("ingredients");
                for (String ingredient : ingredients) {
                    Optional<Ingredients> optIng = ingredientsService.getIngredientByName(ingredient);
                    if (optIng.isPresent()) {
                        searchDict.put("ingredients", optIng.get());
                    } else {
                        throw new Exception(ingredient + " not found in database!");
                    }
                }
            }

            //Tags
            if (body.get("tags") != null) {
                List<String> tags = (List<String>) body.get("tags");
                for (String tag : tags) {
                    Optional<Tags> optTags = tagsService.getTagByName(tag);
                    if (optTags.isPresent()) {
                        searchDict.put("tags", optTags.get());
                    } else {
                        throw new Exception(tag + " not found in database!");
                    }
                }
            }

            List<Recipes> recipes = recipesService.search(searchDict);
            
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(recipesService.createTransferObjectList(recipes));
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            System.out.println("Returning " + recipes.size() + " records");
            return new ResponseEntity<>(jsonResponse, headers, 200);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
