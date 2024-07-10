package com.sj.food.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sj.food.model.IngredientCategory;

public interface IngrediantCategoryRepository extends JpaRepository<IngredientCategory,Long> {
	
	List<IngredientCategory> findByRestaurantId(Long id);

}
