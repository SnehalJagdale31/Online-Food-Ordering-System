package com.sj.food.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sj.food.model.IngredientsItem;

public interface IngrediantItemRepository extends JpaRepository<IngredientsItem, Long> {
	
	
  List<IngredientsItem> findByRestaurantId(Long id);
}
