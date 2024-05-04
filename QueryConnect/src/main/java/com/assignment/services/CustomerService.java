package com.assignment.services;
import java.util.List;

import com.assignment.exception.CustomerException;
import com.assignment.models.Customer;


public interface CustomerService {
	
	public Customer registerCustomer(Customer customer);
	
	public Customer getCustomerDetailsByEmail(String email)throws CustomerException;
	
	public List<Customer> getAllCustomerDetails()throws CustomerException;

}
