package com.assignment.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.models.Customer;


@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Long> {
    List<Customer> findByRole(String role);
    public Optional<Customer> findByEmail(String email);
}
