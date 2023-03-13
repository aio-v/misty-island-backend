package com.aiov.mistyislandref.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aiov.mistyislandref.entities.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

}
