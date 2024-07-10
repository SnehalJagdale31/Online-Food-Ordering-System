package com.sj.food.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sj.food.model.Order;

public interface OrderRpository extends JpaRepository<Order, Long> {

	public List<Order> findByCustomerId(Long userId);
	public List<Order> findByRestaurantId(Long restaurantId);
}
