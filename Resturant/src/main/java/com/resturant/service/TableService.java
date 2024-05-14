package com.resturant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resturant.exception.ResourceNotFoundException;
import com.resturant.models.Restaurant;
import com.resturant.models.Table;
import com.resturant.repositories.RestaurantRepository;
import com.resturant.repositories.TableRepository;

@Service
public class TableService {
    @Autowired
    private TableRepository tableRepository;
   
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Table> getAllTables(Long restaurantId) {
        restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", restaurantId));
        return tableRepository.findByRestaurantId(restaurantId);
    }

    public Table createTable(Long restaurantId, Table table) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", restaurantId));
        table.setRestaurant(restaurant);
        return tableRepository.save(table);
    }
}
