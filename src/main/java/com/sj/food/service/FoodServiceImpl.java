package com.sj.food.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.food.model.Category;
import com.sj.food.model.Food;
import com.sj.food.model.Restaurant;
import com.sj.food.repo.FoodRepository;
import com.sj.food.request.CreateFoodRequest;

import ch.qos.logback.core.filter.Filter;
@Service
public class FoodServiceImpl implements FoodService {
	
	@Autowired
	private FoodRepository foodRepository;

	@Override
	public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {

		Food food = new Food();
		food.setFoodCategory(category);
		food.setRestaurant(restaurant);
		food.setDescription(null);
		food.setImages(req.getImages());
		food.setName(req.getName());
		food.setPrice(req.getPrice());
		food.setIngredients(req.getIngredients());
		food.setSeasonal(req.isSeasonal());
		food.setVegetarian(req.isVegetarian());
		
		
		Food savedFood = foodRepository.save(food);
	    restaurant.getFoods().add(savedFood);
		return savedFood;
	}

	@Override
	public void deleteFood(Long foodId) throws Exception {

		Food food = findFoodById(foodId);
		food.setRestaurant(null);
		foodRepository.save(food);
	}

	@Override
	public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegiterian, boolean isNonVeg, boolean isSeasonal,String foodCategory) {

		List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
		if(isVegiterian) {
			foods = filterByVegetarian(foods,isVegiterian);
		}
		
		if(isNonVeg) {
			foods = filterByNonVeg(foods,isNonVeg);
		}
		if(isSeasonal) {
			foods = filterBySeasonal(foods,isSeasonal);
		}
		
		if(foodCategory!=null && !foodCategory.equals("")) {
			foods = filterByCategory(foods,foodCategory);
		}
		return null;
	}

	private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
		return foods.stream().filter(food -> {
			if(food.getFoodCategory()!=null) {
				return food.getFoodCategory().getName().equals(foodCategory);
			}
			return false;
		}).collect(Collectors.toList());
	}

	
	
	private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
		return foods.stream().filter(food -> food.isSeasonal()==isSeasonal).collect(Collectors.toList());
	}
	
	

	private List<Food> filterByVegetarian(List<Food> foods, boolean isVegiterian) {
		return foods.stream().filter(food -> food.isVegetarian()==isVegiterian).collect(Collectors.toList());
	}

	
	
	private List<Food> filterByNonVeg(List<Food> foods,boolean isNonVeg) {
		return foods.stream().filter(food -> food.isVegetarian()==false).collect(Collectors.toList());
	}

	@Override
	public List<Food> searchFood(String keyword) {
//		return foodRepository.searchFood(keyword);
		return null;
	}

	@Override
	public Food findFoodById(Long foodId) throws Exception {
		Optional<Food> optionalFood = foodRepository.findById(foodId);
		
		if(optionalFood.isEmpty()) {
			throw new Exception("food not found...");
		}
		return optionalFood.get();
	}

	@Override
	public Food updateAvailability(Long foodId) throws Exception {

		Food food = findFoodById(foodId);
		food.setAvailable(!food.isAvailable());
		return foodRepository.save(food);
	}

}
