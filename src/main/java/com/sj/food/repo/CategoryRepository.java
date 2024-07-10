package com.sj.food.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sj.food.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {

	
	public List<Category> findByRestaurantId(Long id);
}
