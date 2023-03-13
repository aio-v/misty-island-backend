package com.aiov.mistyislandref.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ingredientId;
	private int itemId;
	private int amount;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="recipe_id")
	@JsonBackReference
	private Recipe recipe;
	
	public Ingredient(int ingredientId, int itemId, int amount, Recipe recipe) {
		this.ingredientId = ingredientId;
		this.itemId = itemId;
		this.amount = amount;
		this.recipe = recipe;
	}
	public Ingredient() {}
	
	public int getIngredientId() {
		return ingredientId;
	}
	public void setIngredientId(int id) {
		this.ingredientId = id;
	}
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
