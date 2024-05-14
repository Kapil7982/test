package com.resturant.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resturant.models.Menu;
import com.resturant.service.MenuService;

@RestController
@RequestMapping("/menus")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<Menu> getAllMenus(@PathVariable(value = "restaurantId") Long restaurantId) {
        return menuService.getAllMenus(restaurantId);
    }

    @PostMapping
    public ResponseEntity<Menu> createMenu(@PathVariable(value = "restaurantId") Long restaurantId,
                                           @RequestBody Menu menu) {
        Menu createdMenu = menuService.createMenu(restaurantId, menu);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMenu);
    }
}