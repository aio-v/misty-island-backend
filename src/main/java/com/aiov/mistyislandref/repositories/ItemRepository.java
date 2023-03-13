package com.aiov.mistyislandref.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aiov.mistyislandref.entities.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
