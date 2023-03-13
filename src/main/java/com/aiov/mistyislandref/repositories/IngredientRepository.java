package com.aiov.mistyislandref.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aiov.mistyislandref.entities.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
	List<Ingredient> findByRecipeRecipeId(int recipeId);
}
