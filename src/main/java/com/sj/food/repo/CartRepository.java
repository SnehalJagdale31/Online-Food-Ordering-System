package com.sj.food.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sj.food.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	
	public Cart findByCustomerId(Long userId);
}
