package com.aiov.mistyislandref.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aiov.mistyislandref.entities.Ingredient;
import com.aiov.mistyislandref.entities.Item;
import com.aiov.mistyislandref.entities.Recipe;
import com.aiov.mistyislandref.repositories.IngredientRepository;
import com.aiov.mistyislandref.repositories.ItemRepository;
import com.aiov.mistyislandref.repositories.RecipeRepository;

@RestController
@RequestMapping("api/v1")
public class RecipeController {

	private RecipeRepository recipeRepository;
	private IngredientRepository ingredientRepository;
	private ItemRepository itemRepository;
	
	public RecipeController(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, ItemRepository itemRepository) {
		this.recipeRepository = recipeRepository;
		this.itemRepository = itemRepository;
		this.ingredientRepository = ingredientRepository;
	}
	
	@GetMapping("/recipes")
	public List<Recipe> getAllRecipes() {
		return recipeRepository.findAll();
	}
	
	@GetMapping("/recipe/{recipeId}")
	public ResponseEntity<Recipe> getRecipe(@PathVariable int recipeId) {
		Optional<Recipe> r = recipeRepository.findById(recipeId);
		if(!r.isPresent())
			return ResponseEntity.unprocessableEntity().build();
		return ResponseEntity.ok(r.get());
	}
	
	@PostMapping("/recipe")
	public void addRecipe(@RequestBody Recipe request) {
		Recipe r = new Recipe();
		Item i = new Item();
		Set<Ingredient> gList = new HashSet<>();
		
		if(request.getItem().getItemId() == 0 || !itemRepository.findById(request.getItem().getItemId()).isPresent()) {		// item may already exist
			i.setImg(request.getItem().getImg());
			i.setName(request.getItem().getName());
			itemRepository.saveAndFlush(i);
			r.setItem(i);
		} else {
			r.setItem(request.getItem());
		}
		
		request.getIngredients().forEach((x) -> {
			Ingredient g = new Ingredient();
			g.setAmount(x.getAmount());
			g.setItemId(g.getItemId());
			g.setRecipe(r);
			gList.add(g);
		});
		ingredientRepository.saveAllAndFlush(r.getIngredients());
		r.setIngredients(gList);
		
		r.setUnlock(request.getUnlock());
		r.setType(request.getType());
		recipeRepository.save(r);
	}
}
