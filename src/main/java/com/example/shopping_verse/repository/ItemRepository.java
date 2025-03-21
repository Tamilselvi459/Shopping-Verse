package com.example.shopping_verse.repository;

import com.example.shopping_verse.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer> {
}
