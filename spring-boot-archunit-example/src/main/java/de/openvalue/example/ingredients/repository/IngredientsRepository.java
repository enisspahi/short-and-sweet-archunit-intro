package de.openvalue.example.ingredients.repository;

import de.openvalue.example.ingredients.Ingredient;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class IngredientsRepository {

    private final List<Ingredient> ingredients = new ArrayList();

    public List<Ingredient> findBy(Optional<String> name) {
        return ingredients.stream()
                .filter(filterByName(name))
                .collect(Collectors.toList());
    }

    private static Predicate<Ingredient> filterByName(Optional<String> name) {
        return ingredient -> name.map(n -> ingredient.name().toLowerCase().contains(n.toLowerCase())).orElse(true);
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
