package com.sj.food.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sj.food.dto.RestaurantDto;
import com.sj.food.model.Restaurant;
import com.sj.food.model.User;
import com.sj.food.request.CreateRestaurantRequest;


public interface RestaurantService {

	

	public Restaurant createRestaurant(CreateRestaurantRequest req,User user);

	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant)
			throws Exception;

	public void deleteRestaurant(Long restaurantId) throws Exception;

	public List<Restaurant>getAllRestaurant();

	public List<Restaurant>searchRestaurant(String keyword);
	
	public Restaurant findRestaurantById(Long id) throws Exception;

	public Restaurant getRestaurantsByUserId(Long userId) throws Exception;
	
	public RestaurantDto addToFavorites(Long restaurantId,User user) throws Exception;

	public Restaurant updateRestaurantStatus(Long id)throws Exception;
	
}
