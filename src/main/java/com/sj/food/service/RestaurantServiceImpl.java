package com.sj.food.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.sj.food.dto.RestaurantDto;
import com.sj.food.model.Address;
import com.sj.food.model.Restaurant;
import com.sj.food.model.User;
import com.sj.food.repo.AddressRepository;
import com.sj.food.repo.RestaurantRepository;
import com.sj.food.repo.UserRepository;
import com.sj.food.request.CreateRestaurantRequest;

@Service
public class RestaurantServiceImpl implements  RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	

	@Override
	public Restaurant createRestaurant(CreateRestaurantRequest req,User user) {
		Address address=new Address();
		address.setCity(req.getAddress().getCity());
		address.setCountry(req.getAddress().getCountry());
		address.setFullName(req.getAddress().getFullName());
		address.setPostalCode(req.getAddress().getPostalCode());
		address.setState(req.getAddress().getState());
		address.setStreetAddress(req.getAddress().getStreetAddress());
		Address savedAddress = addressRepository.save(address);
		
		Restaurant restaurant = new Restaurant();
		
		restaurant.setAddress(savedAddress);
		restaurant.setContactInformation(req.getContactInformation());
		restaurant.setCuisineType(req.getCusineType());
		restaurant.setDescription(req.getDescription());
		restaurant.setImages(req.getImages());
		restaurant.setName(req.getName());
		restaurant.setOpeningHours(req.getOpiningHours());
//		restaurant.setRegistrationDate(req.getRegistrationDate());
		restaurant.setOwner(user);
		Restaurant savedRestaurant = restaurantRepository.save(restaurant);

		return savedRestaurant;
	}

	@Override
	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedReq)
			throws Exception {
		Restaurant restaurant = findRestaurantById(restaurantId);
		if (restaurant.getCuisineType() != null) {
			restaurant.setCuisineType(updatedReq.getCusineType());
		}
		if (restaurant.getDescription() != null) {
			restaurant.setDescription(updatedReq.getDescription());
		}
		return restaurantRepository.save(restaurant);
	}
	
	@Override
	public Restaurant findRestaurantById(Long restaurantId) throws Exception {
		Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
		if (restaurant.isPresent()) {
			return restaurant.get();
		} else {
			throw new Exception("Restaurant with id " + restaurantId + "not found");
		}
	}

	@Override
	public void deleteRestaurant(Long restaurantId) throws Exception {
		Restaurant restaurant = findRestaurantById(restaurantId);
		if (restaurant != null) {
			restaurantRepository.delete(restaurant);
			return;
		}
		throw new Exception("Restaurant with id " + restaurantId + " Not found");

	}

	@Override
	public List<Restaurant> getAllRestaurant() {
		return restaurantRepository.findAll();
	}


	@Override
	public Restaurant getRestaurantsByUserId(Long userId) throws Exception {
		Restaurant restaurants=restaurantRepository.findByOwnerId(userId);
		return restaurants;
	}



	@Override
	public List<Restaurant> searchRestaurant(String keyword) {
		return restaurantRepository.findBySearchQuery(keyword);
	}

	@Override
	public RestaurantDto addToFavorites(Long restaurantId,User user) throws Exception {
		Restaurant restaurant=findRestaurantById(restaurantId);
		
		RestaurantDto dto=new RestaurantDto();
		dto.setTitle(restaurant.getName());
		dto.setImages(restaurant.getImages());
		dto.setId(restaurant.getId());
		dto.setDescription(restaurant.getDescription());

		boolean isFavorited = false;
		List<RestaurantDto> favorites = user.getFavorites();
		for (RestaurantDto favorite : favorites) {
			if (favorite.getId().equals(restaurantId)) {
				isFavorited = true;
				break;
			}
		}

		if (isFavorited) {
			favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
		} else {
			favorites.add(dto);
		}
		
		User updatedUser = userRepository.save(user);
		return dto;
	}

	@Override
	public Restaurant updateRestaurantStatus(Long id) throws Exception {
		Restaurant restaurant=findRestaurantById(id);
		restaurant.setOpen(!restaurant.isOpen());
		return restaurantRepository.save(restaurant);
	}
	
//	@Override
//	public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
//
//		Address address = addressRepository.save(req.getAddress());
//
//		Restaurant restaurant = new Restaurant();
//		restaurant.setAddress(address);
//		restaurant.setContactInformation(req.getContactInformation());
//		restaurant.setCuisineType(req.getCusineType());
//		restaurant.setDescription(req.getDescription());
//		restaurant.setImages(req.getImages());
//		restaurant.setName(req.getName());
//		restaurant.setOpeningHours(req.getOpiningHours());
//		restaurant.setRegistrationDate(LocalDateTime.now());
//		restaurant.setOwner(user);
//		
//		
//		return restaurantRepository.save(restaurant);
//	}
//
//	@Override
//	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
//
//		Restaurant restaurant = findRestaurantById(restaurantId);
//		if(restaurant.getCuisineType()!=null) {
//			restaurant.setCuisineType(updatedRestaurant.getCusineType());
//		}
//		if(restaurant.getDescription()!=null) {
//			restaurant.setDescription(updatedRestaurant.getDescription());
//		}
//		if(restaurant.getName()!=null) {
//			restaurant.setName(updatedRestaurant.getName());
//		}
//		
//		return restaurantRepository.save(restaurant);
//	}
//
//	@Override
//	public void deleteRestaurant(Long restaurantId) throws Exception {
//
//		Restaurant restaurant = findRestaurantById(restaurantId);
//		restaurantRepository.delete(restaurant);
//		
//	}
//
//	@Override
//	public List<Restaurant> getAllRestaurant() {
//		return restaurantRepository.findAll();
//	}
//
//	@Override
//	public List<Restaurant> searchRestaurant(String keyWord) {
//		return restaurantRepository.findBySearchQuery(keyWord);
//	}
//
//	@Override
//	public Restaurant findRestaurantById(Long id) throws Exception {
//		
//		Optional<Restaurant> opt = restaurantRepository.findById(id);
//		
//		if(opt.isEmpty()) {
//			throw new Exception("restaurant not found with id"+ id);
//		}
//		return opt.get();
//	}
//
//	@Override
//	public Restaurant getRestaurantByUserId(Long userId) throws Exception {
//
//		Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
//		if(restaurant == null) {
//			throw new Exception("restaurant not found with owner id"+userId);
//		}
//		
//		return restaurant;
//	}
//
//	@Override
//	public RestaurantDto addToFavourites(Long restaurantId, User user) throws Exception {
//
//		Restaurant restaurant = findRestaurantById(restaurantId);
//		RestaurantDto dto = new RestaurantDto();
//		dto.setDescription(restaurant.getDescription());
//		dto.setImages(restaurant.getImages());
//		dto.setTitle(restaurant.getName());
//		dto.setId(restaurantId);
//		
//	
//
//		boolean isFavorited = false;
//		List<RestaurantDto> favorites = user.getFavorites();
//		for (Restaurant favorite : favorites) {
//			if (favorite.getId().equals(restaurantId)) {
//				isFavorited = true;
//				break;
//			}
//		}
//
//		if (isFavorited) {
//			favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
//		} else {
//			favorites.add(dto);
//		}
//		
//		User updatedUser = userRepository.save(user);
//		return dto;
//	}
//	
//
//	@Override
//	public Restaurant updateRestaurantStatus(Long id) throws Exception {
//
//		Restaurant restaurant = findRestaurantById(id);
//		restaurant.setOpen(!restaurant.isOpen());
//		return restaurantRepository.save(restaurant);
//	}

}