package com.sj.food.response;

import com.sj.food.domain.USER_ROLE;

import lombok.Data;

@Data
public class AuthResponse {

	private String jwt;
	private  String  message;
	private USER_ROLE role;
	
}
