package de.openvalue.example.ingredients.controller;

import de.openvalue.example.ingredients.Ingredient;
import de.openvalue.example.ingredients.service.IngredientsServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IngredientController {

    private final IngredientsServiceImpl ingredientsService;

    public IngredientController(IngredientsServiceImpl ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping("/ingredients")
    public List<Ingredient> ingredients() {
        return ingredientsService.allIngredients();
    }

}
