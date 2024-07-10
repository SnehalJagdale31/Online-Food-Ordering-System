package com.sj.food.service;

import java.util.List;

import com.sj.food.model.Category;
import com.sj.food.model.Food;
import com.sj.food.model.Restaurant;
import com.sj.food.request.CreateFoodRequest;

public interface FoodService {
	
	public Food createFood(CreateFoodRequest req , Category category,Restaurant restaurant);
	
	void deleteFood(Long foodId) throws Exception;
	
	public List<Food> getRestaurantsFood(
			Long restaurantId, 
			boolean isVegiterian,
			boolean isNonVeg,
			boolean isSeasonal,
			String foodCategory
			);
	
	public List<Food> searchFood(String keyword);
	
	public Food findFoodById(Long foodId) throws Exception;
	
	public Food updateAvailability(Long foodId)throws Exception;
	
	

}
