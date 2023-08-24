package de.openvalue.example.recipes.controller;

import de.openvalue.example.recipes.Recipe;
import de.openvalue.example.recipes.RecipesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class RecipeController {

    private final RecipesService recipeService;


    public RecipeController(RecipesService recipesService) {
        this.recipeService = recipesService;
    }

    @PostMapping("/recipes")
    @ResponseStatus(HttpStatus.CREATED)
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return recipeService.store(recipe);
    }

    @GetMapping("/recipes")
    public List<Recipe> findRecipes(@RequestParam Optional<String> title,
                                    @RequestParam Optional<Boolean> onlyHealthy) {
        return recipeService.search(title, onlyHealthy);
    }

}
