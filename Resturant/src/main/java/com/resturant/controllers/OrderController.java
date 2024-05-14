package com.resturant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resturant.models.Order;
import com.resturant.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/tables/{tableId}")
    public ResponseEntity<Order> createOrder(@PathVariable(value = "tableId") Long tableId,
                                             @RequestBody Order order) {
        Order createdOrder = orderService.createOrder(tableId, order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable(value = "orderId") Long orderId,
                                                @RequestParam String status) {
        return orderService.updateOrderStatus(orderId, status);
    }
}
