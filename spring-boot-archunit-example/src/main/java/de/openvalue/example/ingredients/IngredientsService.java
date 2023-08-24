package de.openvalue.example.ingredients;

import de.openvalue.example.ingredients.repository.IngredientsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientsService {

    private final IngredientsRepository ingredientsRepository;

    public IngredientsService(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    public boolean isHealthy(List<String> ingredients) {
        return ingredients.stream().allMatch(this::verifyHealthiness);
    }

    private boolean verifyHealthiness(String ingredientName) {
        return ingredientsRepository.findByName(ingredientName).map(ingredient ->
                switch (ingredient.nutritionFact()) {
                    case LOW_CALORIE, CARBS, HIGH_PROTEIN, NOT_KNOWN -> true;
                    case HIGH_CALORIE -> false;
                }
        ).orElseThrow();
    }
}
