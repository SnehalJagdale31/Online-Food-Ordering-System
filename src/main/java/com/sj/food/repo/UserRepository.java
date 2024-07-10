package com.sj.food.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sj.food.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmail(String username);

}
