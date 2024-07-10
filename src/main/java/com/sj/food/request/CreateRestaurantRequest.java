package com.sj.food.request;

import java.util.List;

import com.sj.food.model.Address;
import com.sj.food.model.ContactInformation;

import lombok.Data;

@Data
public class CreateRestaurantRequest {

	
	private Long id;
	private String name;
	private String description;
	private String cusineType;
	private Address address;
	private ContactInformation contactInformation;
	private String opiningHours;
	private List<String> images;
	
	
}
