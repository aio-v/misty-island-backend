package com.aiov.mistyislandref.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Recipe {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int recipeId;
	private String unlock;
	private String type;
	@OneToMany(mappedBy="recipe",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JsonManagedReference 
	private Set<Ingredient> ingredients = new HashSet<>();
	@OneToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="item_id")
	private Item item;
	
	public Recipe(int recipeId, Item item, String unlock, String type) {
		this.recipeId = recipeId;
		this.item = item;
		this.unlock = unlock;
		this.type = type;
	}
	
	public Recipe() { }
	
	public int getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(int id) {
		this.recipeId = id;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public String getUnlock() {
		return unlock;
	}
	public void setUnlock(String unlock) {
		this.unlock = unlock;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Set<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
}
