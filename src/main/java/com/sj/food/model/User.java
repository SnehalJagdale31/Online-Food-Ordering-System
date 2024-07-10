package com.sj.food.model;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sj.food.domain.USER_ROLE;
import com.sj.food.dto.RestaurantDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String fullName;
	private String email;
	private String password;

	private USER_ROLE role;

	@JsonIgnore
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Order> orders;

	@ElementCollection
	private List<RestaurantDto> favorites=new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> addresses = new ArrayList<>();
	
	private String status;

//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//
//	private String fullName;
//	private String email;
//	
//	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//	private String password;
//
//	private USER_ROLE role ;
//
//	@JsonIgnore
//	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
//	private List<Order> orders;
//
//	@ElementCollection
//	private List<Restaurant> favorites=new ArrayList<>();
//	
//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Address> addresses = new ArrayList<>();
//	
//	private String status;

}
