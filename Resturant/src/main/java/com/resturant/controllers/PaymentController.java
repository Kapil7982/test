package com.resturant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resturant.models.Payment;
import com.resturant.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/orders/{orderId}")
    public ResponseEntity<Payment> makePayment(@PathVariable(value = "orderId") Long orderId,
                                                @RequestParam double amount) {
        Payment payment = paymentService.makePayment(orderId, amount);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }
}