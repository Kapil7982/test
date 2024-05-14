package com.resturant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.resturant.models.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	
}
