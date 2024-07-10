package com.sj.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sj.food.model.Food;
import com.sj.food.model.Restaurant;
import com.sj.food.model.User;
import com.sj.food.request.CreateFoodRequest;
import com.sj.food.request.CreateRestaurantRequest;
import com.sj.food.response.MessageResponse;
import com.sj.food.service.FoodService;
import com.sj.food.service.RestaurantService;
import com.sj.food.service.UserService;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

	
	@Autowired
	private FoodService foodService;
	@Autowired
	private  UserService userService;
	@Autowired
	private RestaurantService restaurantService;
	
	
	@PostMapping
	public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
			                                @RequestHeader("Authorization") String jwt) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());
		Food food = foodService.createFood(req, req.getCategory(), restaurant);
		return new ResponseEntity<>(food,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        foodService.deleteFood(id);
        MessageResponse res = new MessageResponse();
        res.setMessage("deleted successfully");
        return new ResponseEntity<>(res,HttpStatus.CREATED);

}
	@PutMapping("/{id}")
	public ResponseEntity<Food> updateFoodAvailability(@PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.updateAvailability(id);
       
        return new ResponseEntity<>(food,HttpStatus.CREATED);

}
	
	
}
