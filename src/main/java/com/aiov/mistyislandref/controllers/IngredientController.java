package com.aiov.mistyislandref.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aiov.mistyislandref.entities.Ingredient;
import com.aiov.mistyislandref.repositories.IngredientRepository;
import com.aiov.mistyislandref.repositories.RecipeRepository;

@RestController
@RequestMapping("api/v1")
public class IngredientController {

	private RecipeRepository recipeRepository;
	private IngredientRepository ingredientRepository;
	
	public IngredientController(IngredientRepository ingredientRepository, RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
		this.ingredientRepository = ingredientRepository;
	}
	
	@GetMapping("/ingredients/{recipeId}")
	public ResponseEntity<List<Ingredient>> getAllIngredientsByRecipeId(@PathVariable int recipeId) throws Exception {
		if(!recipeRepository.existsById(recipeId))
			throw new Exception("recipe id " + recipeId + " not found");
		List<Ingredient> ingredients = ingredientRepository.findByRecipeRecipeId(recipeId);
		return new ResponseEntity<>(ingredients, HttpStatus.OK);
	}
	
	@PostMapping("/ingredients/{recipeId}")
	public void addManyIngredients(@PathVariable int recipeId, List<Ingredient> requests) throws Exception {
		if(!recipeRepository.existsById(recipeId))
			throw new Exception("recipe id " + recipeId + " not found");
		List<Ingredient> ingredients = new ArrayList<>();
		for(Ingredient r : requests) {
			Ingredient ingredient = new Ingredient();
			ingredient.setAmount(r.getAmount());
			ingredient.setItemId(r.getItemId());
			ingredient.setRecipe(r.getRecipe());
			ingredients.add(ingredient);
		}
		ingredientRepository.saveAll(ingredients);
	}
}
