package com.sj.food.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.food.model.IngredientCategory;
import com.sj.food.model.IngredientsItem;
import com.sj.food.model.Restaurant;
import com.sj.food.repo.IngrediantCategoryRepository;
import com.sj.food.repo.IngrediantItemRepository;

@Service
public class IngrediantsServiceImpl implements IngrediantsService{
	
	@Autowired
	private IngrediantItemRepository ingrediantItemRepository;
	@Autowired
	private IngrediantCategoryRepository ingrediantCategoryRepository;
	@Autowired
	private RestaurantService restaurantService;
	
	
	@Override
	public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {

		Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
		IngredientCategory category = new IngredientCategory();
		category.setRestaurant(restaurant);
		category.setName(name);
		return ingrediantCategoryRepository.save(category);
	}
	
	@Override
	public IngredientCategory findIngredientCategoryById(Long id) throws Exception {

		Optional<IngredientCategory> opt = ingrediantCategoryRepository.findById(id);
		if(opt.isEmpty()) {
			throw new Exception("Ingredeiant category not found...");
		}
		
		return opt.get();
	}
	@Override
	public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
		restaurantService.findRestaurantById(id);
		return ingrediantCategoryRepository.findByRestaurantId(id);
	}
	@Override
	public IngredientsItem createIngredientsItem(Long restaurantId, String ingrediantName, Long categoryId)
			throws Exception {
		Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
		IngredientCategory category = new IngredientCategory();
		IngredientsItem item = new IngredientsItem();
		item.setName(ingrediantName);
        item.setRestaurant(restaurant);
        item.setCategory(category);

        IngredientsItem ingredients = ingrediantItemRepository.save(item);
        category.getIngredients().add(ingredients);
		return ingredients;
	}
	@Override
	public List<IngredientsItem> findRestaurantsIngredints(Long restaurantId) {
		return ingrediantItemRepository.findByRestaurantId(restaurantId);
	}
	
	
	@Override
	public IngredientsItem updateStock(Long id) throws Exception {
		Optional<IngredientsItem> optionalIngredientsItem = ingrediantItemRepository.findById(id);
		if(optionalIngredientsItem.isEmpty()) {
			throw new Exception("Ingredeiant  not found...");
		}
		
		IngredientsItem ingredientsItem = optionalIngredientsItem.get();
		ingredientsItem.setInStoke(!ingredientsItem.isInStoke());
		return ingrediantItemRepository.save(ingredientsItem);
	}

}
