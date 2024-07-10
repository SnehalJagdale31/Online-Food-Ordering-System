package com.sj.food.request;

import java.util.List;

import com.sj.food.model.Category;
import com.sj.food.model.IngredientsItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFoodRequest {
	
	
	private String name;
	private String Description;
	private Long price;
	private Category category;
	private List<String> images;
	private boolean seasonal;
	private Long restaurantId;
	private boolean vegetarian;
	
	private List<IngredientsItem> ingredients;

}
