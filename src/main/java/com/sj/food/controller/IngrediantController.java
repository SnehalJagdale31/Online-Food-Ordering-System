package com.sj.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sj.food.model.IngredientCategory;
import com.sj.food.model.IngredientsItem;
import com.sj.food.request.IngredientCategoryRequest;
import com.sj.food.request.IngredientRequest;
import com.sj.food.service.IngrediantsService;

@RestController
@RequestMapping("/api/admin/ingrediants")
public class IngrediantController {

	@Autowired
	private IngrediantsService ingrediantsService;
	
	
	@PostMapping("/category")
	public ResponseEntity<IngredientCategory> createIngredientCategory(@RequestBody IngredientCategoryRequest req ) throws Exception{
		
		IngredientCategory item = ingrediantsService.createIngredientCategory(req.getName(), req.getRestaurantId());
		return new ResponseEntity<>(item,HttpStatus.CREATED);
		
	}
	

	@PostMapping()
	public ResponseEntity<IngredientsItem> createIngredientItem(@RequestBody IngredientRequest req ) throws Exception{
		
	IngredientsItem item = ingrediantsService.createIngredientsItem(req.getRestaurantId(),req.getName(), req.getCategoryId());
		return new ResponseEntity<>(item,HttpStatus.OK);
		
	}
	
	@PutMapping("/{id}/stoke")
	public ResponseEntity<IngredientsItem> createIngredientItem1(@PathVariable Long id) throws Exception{
		
	IngredientsItem item = ingrediantsService.updateStock(id);
		return new ResponseEntity<>(item,HttpStatus.OK);
		
	}
	
	@GetMapping("/restaurant/{id}")
	public ResponseEntity<List<IngredientsItem> > getRestaurantIngredient(@PathVariable Long id) throws Exception{
		
	List<IngredientsItem> items = ingrediantsService.findRestaurantsIngredints(id);
		return new ResponseEntity<>(items,HttpStatus.OK);
		
	}
	

	@GetMapping("/restaurant/{id}/category")
	public ResponseEntity<List<IngredientCategory> > getRestaurantIngredientCategory(@PathVariable Long id) throws Exception{
		
	List<IngredientCategory> items = ingrediantsService.findIngredientCategoryByRestaurantId(id);
		return new ResponseEntity<>(items,HttpStatus.OK);
		
	}
	
}
