package com.resturant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resturant.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
}
