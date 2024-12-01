package com.codewithvinod.fullstack_backend.repository;


import com.codewithvinod.fullstack_backend.model.Customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	

	
	List<Customer> findAll();
	    

}
