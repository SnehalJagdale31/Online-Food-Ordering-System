package com.sj.food.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.food.model.Address;
import com.sj.food.model.Cart;
import com.sj.food.model.CartItem;
import com.sj.food.model.Order;
import com.sj.food.model.OrderItem;
import com.sj.food.model.Restaurant;
import com.sj.food.model.User;
import com.sj.food.repo.AddressRepository;
import com.sj.food.repo.OrderItemRepository;
import com.sj.food.repo.OrderRpository;
import com.sj.food.repo.RestaurantRepository;
import com.sj.food.repo.UserRepository;
import com.sj.food.request.OrderRequest;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRpository orderRepository;
	@Autowired
	private OrderItemRepository orederItemRepository;
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private CartService cartService;

	@Override
	public Order createOrder(OrderRequest order, User user) throws Exception {
		Address shippAddress = order.getDeliveryAddress();
		Address savedAddress = addressRepository.save(shippAddress);
		
		if(!user.getAddresses().contains(savedAddress)) {
			user.getAddresses().add(savedAddress);
			userRepository.save(user);
		}
		
		Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());
		Order creaatedOrder = new Order();
		creaatedOrder.setCustomer(user);
		creaatedOrder.setDeliveryAddress(savedAddress);
		creaatedOrder.setCreatedAt(new Date());
		creaatedOrder.setOrderStatus("PENDING");
		creaatedOrder.setDeliveryAddress(savedAddress);
		creaatedOrder.setRestaurant(restaurant);
		
		Cart cart = cartService.findCartByUserId(user.getId());
		
		List<OrderItem> orderItems = new ArrayList<>();
		for(CartItem cartItem:cart.getItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setFood(cartItem.getFood());
			orderItem.setIngredients(cartItem.getIngredients());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setTotalPrice(cartItem.getTotalPrice());
			
			OrderItem savedOrderItem = orederItemRepository.save(orderItem);
			orderItems.add(savedOrderItem);
		}
		
		Long totalPrice= cartService.calculateCartTotals(cart);
		
		creaatedOrder.setItems(orderItems);
		creaatedOrder.setTotalPrice(totalPrice);
		
		Order savesOrder = orderRepository.save(creaatedOrder);
		
		restaurant.getOrders().add(savesOrder);
		
		return creaatedOrder;
	}

	@Override
	public Order updateOrder(Long orderId, String orderStatus) throws Exception {

		Order order = findOrderById(orderId);
		if(orderStatus.equals("OUT FOR DELIVERY")|| orderStatus.equals("DEDLIVERED")||orderStatus.equals("COMPLETED")||orderStatus.equals("PENDING")) {
			order.setOrderStatus(orderStatus);
			return orderRepository.save(order);
		}
		throw new Exception("Please select a valid order status ");
	}

	@Override
	public void cancelOrder(Long orderId) throws Exception {

		Order order = findOrderById(orderId);
		orderRepository.deleteById(orderId);
		
	}

	@Override
	public List<Order> getUsersOrder(Long userId) throws Exception {

		
		return orderRepository.findByCustomerId(userId);
	}

	@Override
	public List<Order> getRestaurantOrder(Long restaurantId, String orderStatus) throws Exception {
		List<Order>orders= orderRepository.findByRestaurantId(restaurantId);
		if(orderStatus!=null) {
			orders = orders.stream().filter(order -> order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
		}
		return orders;
	}

	@Override
	public Order findOrderById(Long orderId) throws Exception {
    Optional<Order> optionalOrder = orderRepository.findById(orderId);
    
    if(optionalOrder.isEmpty()) {
    	throw new Exception("Order not found");
    }
		return optionalOrder.get();
	}

}
