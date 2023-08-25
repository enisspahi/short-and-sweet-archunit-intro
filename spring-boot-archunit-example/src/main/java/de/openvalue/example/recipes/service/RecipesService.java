package de.openvalue.example.recipes.service;

import de.openvalue.example.ingredients.IngredientsHealthService;
import de.openvalue.example.recipes.Recipe;
import de.openvalue.example.recipes.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class RecipesService {

    private final RecipeRepository recipeRepository;

    private final IngredientsHealthService ingredientsHealthService;

    public RecipesService(RecipeRepository recipeRepository, IngredientsHealthService ingredientsHealthService) {
        this.recipeRepository = recipeRepository;
        this.ingredientsHealthService = ingredientsHealthService;

    }

    public List<Recipe> search(Optional<String> title, Optional<Boolean> onlyHealthy) {
        return recipeRepository.findBy(title)
                .stream()
                .filter(onlyHealthyFilter(onlyHealthy))
                .collect(Collectors.toList());
    }

    private Predicate<Recipe> onlyHealthyFilter(Optional<Boolean> onlyHealthy) {
        return recipe -> {
            if (!onlyHealthy.isPresent() || !onlyHealthy.get()) {
                return true;
            }

            var ingredientNames = recipe.ingredients().stream().map(Recipe.Ingredient::name).collect(Collectors.toList());
            return ingredientsHealthService.isHealthy(ingredientNames);
        };
    }

    public Recipe store(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

}
