package com.sj.food.service;

import java.util.List;

import com.sj.food.model.IngredientCategory;
import com.sj.food.model.IngredientsItem;

public interface IngrediantsService {
	
	public IngredientCategory createIngredientCategory(String name,Long restaurantId)throws Exception;
	
	public  IngredientCategory findIngredientCategoryById(Long id)throws Exception;
	
	public  List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id)throws Exception;
	
	public IngredientsItem createIngredientsItem(Long restaurantId,String ingrediantName, Long categoryId)throws Exception;
	
	public List<IngredientsItem> findRestaurantsIngredints(Long restaurantId);
	
	public IngredientsItem updateStock(Long id) throws Exception;
	

}
