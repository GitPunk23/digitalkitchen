package com.digitalkitchen.service;

import com.digitalkitchen.controller.request.transferobjects.RecipeTransferObject;
import com.digitalkitchen.entities.Category;
import com.digitalkitchen.entities.Ingredients;
import com.digitalkitchen.entities.RecipeIngredients;
import com.digitalkitchen.entities.RecipeTags;
import com.digitalkitchen.entities.Recipes;
import com.digitalkitchen.entities.Tags;
import com.digitalkitchen.repository.RecipesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;


@Service
public class RecipesService {

    @Autowired
    private RecipesRepository recipesRepository;
    @Autowired
    private RecipeIngredientsService recipeIngredientsService;
    @Autowired
    private RecipeTagsService recipeTagsService;
    @Autowired
    private StepsService stepsService;
    @Autowired
    private EntityManager entityManager;

    public RecipesService() {
    }

    public List<Recipes> getAllRecipes() {
        return recipesRepository.findAll();
    }

    public Optional<Recipes> getRecipeById(int id) {
        return recipesRepository.findById(id);
    }

    public Optional<Recipes> getRecipeByName(String name) {
        return recipesRepository.findByName(name);
    }

    public Recipes getExpandedRecipe(Recipes recipe) {
        recipe.setIngredients(recipeIngredientsService.getAllRecipeIngredientsByRecipe(recipe));
        recipe.setSteps(stepsService.getAllStepsByRecipe(recipe));
        recipe.setTags(recipeTagsService.getAllRecipeTagsByRecipe(recipe));
        return recipe;
    }

    public Recipes addRecipe(Recipes recipe) {
        return recipesRepository.save(recipe);
    }

    public void updateRecipe(Recipes recipe) {
        recipesRepository.save(recipe);
    }

    public void deleteRecipeById(int id) {
        recipesRepository.deleteById(id);
    }

    public List<Recipes> search(Map<String, Object> searchDict) {
        return searchRecipes(searchDict);
    }
        

    public boolean recipeExists(Recipes recipe) {
        Optional<Recipes> dbrecipe = recipesRepository.findByName(recipe.getName());
        if (dbrecipe.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    public RecipeTransferObject createTransferObject(Recipes recipe) {
        return new RecipeTransferObject(recipe);
    }

    public List<RecipeTransferObject> createTransferObjectList(List<Recipes> recipes) {
        List<RecipeTransferObject> transferList = new ArrayList();

        for (Recipes recipe : recipes) {
            transferList.add(createTransferObject(recipe));
        }

        return transferList;
    }


    private List<Recipes> searchRecipes(Map<String, Object> searchParams) { 
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Recipes> query = builder.createQuery(Recipes.class);
        Root<Recipes> root = query.from(Recipes.class);
        
        List<Predicate> predicates = new ArrayList<>();
        
        if (searchParams.containsKey("name")) {
            predicates.add(builder.like(root.get("name"), "%" + searchParams.get("name") + "%"));

        } else if (searchParams.containsKey("categories")) {
            Join<Recipes, Category> join = root.join("category");
            predicates.add(builder.equal(join.get("name"), searchParams.get("categories")));
            
        } else if (searchParams.containsKey("authors")) {
            predicates.add(builder.equal(root.get("author"), searchParams.get("authors")));

        } else if (searchParams.containsKey("tags")) {
            Join<Recipes, Tags> join = root.join("tags");
            predicates.add(builder.equal(join.get("tagName"), searchParams.get("tags")));

        } else if (searchParams.containsKey("ingredients")) {
            Join<Recipes, Ingredients> join = root.join("ingredients");
            predicates.add(builder.equal(join.get("ingredientName"), searchParams.get("ingredients")));

        } else if (searchParams.containsKey("servings")) {
            predicates.add(builder.equal(root.get("servings"), searchParams.get("servings")));

        } else if (searchParams.containsKey("calories")) {
            predicates.add(builder.equal(root.get("caloriesPerServing"), searchParams.get("calories")));
        }
        
        
        query.select(root).where(predicates.toArray(new Predicate[0]));
        
        TypedQuery<Recipes> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();
    }
}
