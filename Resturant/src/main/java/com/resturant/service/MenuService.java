package com.resturant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resturant.exception.ResourceNotFoundException;
import com.resturant.models.Menu;
import com.resturant.models.Restaurant;
import com.resturant.repositories.MenuRepository;
import com.resturant.repositories.RestaurantRepository;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;
     
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Menu> getAllMenus(Long restaurantId) {
        restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", restaurantId));
        return menuRepository.findByRestaurantId(restaurantId);
    }

    public Menu createMenu(Long restaurantId, Menu menu) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant", "id", restaurantId));
        menu.setRestaurant(restaurant);
        return menuRepository.save(menu);
    }
}
