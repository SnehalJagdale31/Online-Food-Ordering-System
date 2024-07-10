package com.sj.food.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.food.model.Category;
import com.sj.food.model.Restaurant;
import com.sj.food.repo.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository  categoryRepository;
	@Autowired
	private RestaurantService restaurantService;
	
	
	@Override
	public Category createCategory(String name, Long userId) throws Exception {

		Restaurant restaurant = restaurantService.getRestaurantsByUserId(userId);
		Category category = new Category();
		category.setName(name);
		category.setRestaurant(restaurant);
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
		Restaurant restaurant = restaurantService.getRestaurantsByUserId(id);
		return categoryRepository.findByRestaurantId(restaurant.getId());
	}

	@Override
	public Category findCategoryById(Long id) throws Exception {

		Optional<Category> optionalCategory = categoryRepository.findById(id);
		if(optionalCategory.isEmpty()) {
			throw new Exception("Category not found...");
		}
		return optionalCategory.get();
	}
	


}
