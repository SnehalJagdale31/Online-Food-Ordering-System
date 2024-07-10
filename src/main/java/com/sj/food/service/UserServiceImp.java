package com.sj.food.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.food.config.JwtProvider;
import com.sj.food.model.User;
import com.sj.food.repo.UserRepository;


@Service
public class UserServiceImp implements UserService {
	
	
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private JwtProvider jwtProvider;

	@Override
	public User findUserByJwtToken(String jwt) throws Exception {
//
//		String email = jwtProvider.getEmailFromJwtToken(jwt);
//		User user = findUserByEmail(email);
//		return user;
        String email=jwtProvider.getEmailFromJwtToken(jwt);
		
		
		User user = userRepository.findByEmail(email);
		
		if(user==null) {
			throw new Exception("user not exist with email "+email);
		}
//		System.out.println("email user "+user.get().getEmail());
		return user;
		
	}

	@Override
	public User findUserByEmail(String email) throws Exception {
       User user = userRepository.findByEmail(email);
       
       if(user ==null) {
    	   throw new Exception("user not found");
       }
		return user;
	}

}
