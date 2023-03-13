package com.aiov.mistyislandref.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aiov.mistyislandref.entities.Item;
import com.aiov.mistyislandref.repositories.ItemRepository;

@RestController
@RequestMapping("api/v1")
public class ItemController {

	private ItemRepository itemRepository;
	
	public ItemController(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	
	@GetMapping("/item/{itemId}")
	public Item getItem(@PathVariable int itemId) {
		return itemRepository.findById(itemId).orElseGet(() -> null);
	}
	
	@GetMapping("/items")
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}
	
	@PostMapping("/item")
	public void addItem(@RequestBody Item request) {
		Item item = new Item();
		item.setImg(request.getImg());
		item.setName(request.getName());
		itemRepository.save(item);
	}
	
	@PostMapping("/items")
	public void addManyItems(@RequestBody List<Item> requests) {
		for(Item request : requests) {
			Item item = new Item();
			item.setImg(request.getImg());
			item.setName(request.getName());
			itemRepository.save(item);
		}
	}
}
