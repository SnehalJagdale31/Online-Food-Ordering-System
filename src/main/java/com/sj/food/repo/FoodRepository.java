package com.sj.food.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sj.food.model.Food;

@Repository

public interface FoodRepository extends JpaRepository<Food, Long> {
	
	List<Food> findByRestaurantId(Long restaurantId);
	
	
//	@Query("SELECT f FROM Food f WHERE f.name LIKE %:keyword% OR f.foodCategory.name LIKE %:keyword%")
//	List<Food> searchFood(@Param("Keyword") String keyword);

}
