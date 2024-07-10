package com.sj.food.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sj.food.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	
}
