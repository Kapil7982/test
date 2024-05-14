package com.resturant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resturant.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
}
