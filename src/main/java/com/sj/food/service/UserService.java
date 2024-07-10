package com.sj.food.service;

import org.springframework.stereotype.Service;

import com.sj.food.model.User;


public interface UserService {
	
	
	public User findUserByJwtToken(String jwt) throws Exception;
	
	public User findUserByEmail(String email) throws Exception;

}
