package de.openvalue.example.recipes;

import java.util.ArrayList;
import java.util.List;

public record Recipe(String title,
                     List<Ingredient> ingredients,
                     Integer preparationTime,
                     Integer cookingTime,
                     Integer servings,
                     List<String> instructions) {

    public record Ingredient(String name,
                             Double quantity,
                             String unit) {
    }


}


