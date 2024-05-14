package com.resturant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.resturant.exception.ResourceNotFoundException;
import com.resturant.models.Order;
import com.resturant.models.Table;
import com.resturant.repositories.OrderRepository;
import com.resturant.repositories.TableRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TableRepository tableRepository;

    public Order createOrder(Long tableId, Order order) {
        Table table = tableRepository.findById(tableId)
                .orElseThrow(() -> new ResourceNotFoundException("Table", "id", tableId));
        order.setTable(table);
        order.setStatus("pending"); // Assuming order status is initially pending
        return orderRepository.save(order);
    }

    public ResponseEntity<?> updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
        order.setStatus(status);
        orderRepository.save(order);
        return ResponseEntity.ok().build();
    }
}