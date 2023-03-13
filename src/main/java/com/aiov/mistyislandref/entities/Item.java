package com.aiov.mistyislandref.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int itemId;
	private String name;
	private String img;
	
	
	public Item(int id, String name, String img) {
		this.img = img;
		this.name = name;
		this.itemId = id;
	}
	
	public Item() { }
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int id) {
		this.itemId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
}
