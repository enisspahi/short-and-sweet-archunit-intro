package de.openvalue.example.ingredients;

public record Ingredient(String name,
                         NutritionFact nutritionFact) {

    public enum NutritionFact {LOW_CALORIE, HIGH_CALORIE, HIGH_PROTEIN, CARBS, NOT_KNOWN}

}


