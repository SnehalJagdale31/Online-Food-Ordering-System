package com.sj.food.service;

import java.util.List;

import com.sj.food.model.Order;
import com.sj.food.model.User;
import com.sj.food.request.OrderRequest;

public interface OrderService {

	public Order createOrder(OrderRequest order,User user)throws Exception ;
	public Order updateOrder(Long orderId,String orderStatus) throws Exception;
	
	public void cancelOrder(Long orderId) throws Exception;
	
	public List<Order> getUsersOrder(Long userId)throws Exception;
	
	public List<Order> getRestaurantOrder(Long restaurantId,String orderStatus)throws Exception;
	
	public Order findOrderById(Long orderId)throws Exception;
}
