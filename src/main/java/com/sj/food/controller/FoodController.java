package com.sj.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sj.food.model.Food;
import com.sj.food.model.Restaurant;
import com.sj.food.model.User;
import com.sj.food.request.CreateFoodRequest;
import com.sj.food.service.FoodService;
import com.sj.food.service.RestaurantService;
import com.sj.food.service.UserService;

@RestController
@RequestMapping("/api/food")
public class FoodController {

	

	@Autowired
	private FoodService foodService;
	@Autowired
	private  UserService userService;
	@Autowired
	private RestaurantService restaurantService;
	
	
	@GetMapping("/search")
	public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
			                                @RequestHeader("Authorization") String jwt) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		List<Food> foods = foodService.searchFood(name);
		return new ResponseEntity<>(foods,HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/restaurant/{restaurantId}")
	public ResponseEntity<List<Food>> getRestaurantFood(@RequestParam boolean vegetarian,@RequestParam boolean seasonal,@RequestParam boolean nonVeg,@PathVariable Long restaurantId,
			                               @RequestParam(required = false) String food_category, @RequestHeader("Authorization") String jwt) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		
		List<Food> foods = foodService.getRestaurantsFood( restaurantId ,vegetarian,  seasonal,  nonVeg,  food_category);
		return new ResponseEntity<>(foods,HttpStatus.CREATED);
		
	}
	
	
}
