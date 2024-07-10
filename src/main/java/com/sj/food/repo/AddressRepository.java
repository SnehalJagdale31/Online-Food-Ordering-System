package com.sj.food.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sj.food.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
