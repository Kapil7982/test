package com.resturant.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resturant.models.Table;

public interface TableRepository extends JpaRepository<Table, Long> {
    List<Table> findByRestaurantId(Long restaurantId);
}
