package com.sj.food.request;

import lombok.Data;

@Data
public class IngredientRequest {
	
	private String name;
	private Long categoryId;
	private Long restaurantId;

}
