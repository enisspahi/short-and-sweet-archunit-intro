package de.openvalue.example.ingredients.controller;

import de.openvalue.example.ingredients.Ingredient;
import de.openvalue.example.ingredients.service.IngredientsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IngredientController {

    private final IngredientsService ingredientsService;

    public IngredientController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping("/ingredients")
    public List<Ingredient> ingredients() {
        return ingredientsService.allIngredients();
    }

}
