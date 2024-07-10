package com.sj.food.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sj.food.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	
	
}
