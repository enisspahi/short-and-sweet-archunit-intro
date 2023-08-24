package de.openvalue.example.recipes.repository;

import de.openvalue.example.recipes.Recipe;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class RecipeRepository {

    private final List<Recipe> recipes = new ArrayList();

    public RecipeRepository() {

    }

    public Recipe save(Recipe recipe) {
        recipes.add(recipe);
        return recipe;
    }

    public List<Recipe> findBy(Optional<String> title) {
        return recipes.stream()
                .filter(filterByTitle(title))
                .collect(Collectors.toList());
    }

    private static Predicate<Recipe> filterByTitle(Optional<String> title) {
        return recipe -> title.map(t -> recipe.title().toLowerCase().contains(t.toLowerCase())).orElse(true);
    }

    @PostConstruct
    void init() {
        var cake = new Recipe("Cake",
                List.of(
                        new Recipe.Ingredient("Sugar", 2.0, "cups"),
                        new Recipe.Ingredient("Eggs", 2.0, "count"),
                        new Recipe.Ingredient("Flour", 1.0, "cup")
                ),
                30, 20, 2,
                List.of("1. Prepare ingredients", "2. Put it to the oven", "3. Serve")
        );
        var proteinShake = new Recipe("Cake",
                List.of(
                        new Recipe.Ingredient("Milk", 200.0, "milligrams"),
                        new Recipe.Ingredient("Protein powder", 1.0, "cup")
                ),
                30, 20, 2,
                List.of("1. Add milk", "2. Add powder", "3. Shake", "4. Drink")
        );


        recipes.add(cake);
        recipes.add(proteinShake);
    }

}
