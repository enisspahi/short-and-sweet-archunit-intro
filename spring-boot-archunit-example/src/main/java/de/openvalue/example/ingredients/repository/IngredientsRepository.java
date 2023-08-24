package de.openvalue.example.ingredients.repository;

import de.openvalue.example.ingredients.Ingredient;
import de.openvalue.example.recipes.Recipe;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class IngredientsRepository {

    private final List<Ingredient> ingredients = new ArrayList();


    public Optional<Ingredient> findByName(String name) {
        return ingredients.stream().filter(ingredient -> ingredient.name().equals(name)).findFirst();
    }

    @PostConstruct
    void init() {
        ingredients.add(new Ingredient("Sugar", Ingredient.NutritionFact.HIGH_CALORIE));
        ingredients.add(new Ingredient("Eggs", Ingredient.NutritionFact.HIGH_PROTEIN));
        ingredients.add(new Ingredient("Flour", Ingredient.NutritionFact.CARBS));
        ingredients.add(new Ingredient("Milk", Ingredient.NutritionFact.NOT_KNOWN));
        ingredients.add(new Ingredient("Protein powder", Ingredient.NutritionFact.HIGH_PROTEIN));
    }
}
