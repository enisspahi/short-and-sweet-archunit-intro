package de.openvalue.example.ingredients.service;

import de.openvalue.example.ingredients.Ingredient;
import de.openvalue.example.ingredients.IngredientsHealthService;
import de.openvalue.example.ingredients.repository.IngredientsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientsService implements IngredientsHealthService {

    private final IngredientsRepository ingredientsRepository;

    public IngredientsService(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    public List<Ingredient> allIngredients() {
        return ingredientsRepository.findBy(Optional.empty());
    }

    @Override
    public boolean isHealthy(List<String> ingredients) {
        return ingredients.stream().allMatch(this::verifyHealthiness);
    }

    private boolean verifyHealthiness(String ingredientName) {
        return ingredientsRepository.findBy(Optional.of(ingredientName)).stream()
                .findFirst()
                .map(ingredient ->
                        switch (ingredient.nutritionFact()) {
                            case LOW_CALORIE, CARBS, HIGH_PROTEIN, NOT_KNOWN -> true;
                            case HIGH_CALORIE -> false;
                        }
                ).orElseThrow();
    }
}
