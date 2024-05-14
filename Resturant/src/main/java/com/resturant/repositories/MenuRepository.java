package com.resturant.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resturant.models.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByRestaurantId(Long restaurantId);
}
