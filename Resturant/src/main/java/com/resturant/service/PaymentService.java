package com.resturant.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resturant.exception.InvalidRequestException;
import com.resturant.exception.ResourceNotFoundException;
import com.resturant.models.Order;
import com.resturant.models.Payment;
import com.resturant.repositories.OrderRepository;
import com.resturant.repositories.PaymentRepository;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Payment makePayment(Long orderId, double amount) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
        
        if (!order.getStatus().equals("pending")) {
            throw new InvalidRequestException("Order", "status", "Payment can't be made for completed order");
        }

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(amount);
        payment.setCreatedAt(LocalDateTime.now());
        return paymentRepository.save(payment);
    }
}
