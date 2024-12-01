package com.codewithvinod.fullstack_backend.controller;



import com.codewithvinod.fullstack_backend.exception.CustomerNotFoundException;
import com.codewithvinod.fullstack_backend.model.Customer;
import com.codewithvinod.fullstack_backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/customer")
    Customer newCustomer(@RequestBody Customer customer){
        System.out.println("rrrrrrr"+customer.getRegistrationDate());
        return customerRepository.save(customer);

    }
    @GetMapping("/customers")
    List<Customer> getAllCustomers(){

    //    return customerRepository.findAll(org.springframework.data.domain.Sort.by("custName").descending());
    	
    	 return customerRepository.findAll( );
    }
    

    @GetMapping("/customersGetCount")
   long getCustomersPrice(){
    	
    	List <Customer> listCustomers = customerRepository.findAll();
    	
    	long sum = 0;
    	
    	for (Customer element : listCustomers) {
    	    // 1 - can call methods of element
    		System.out.println("lement.getPrice()"+element.getPrice());
    		sum= sum + element.getPrice();
    	    // ...
    	}
        
        
        return sum;

        
    }

    @GetMapping("/customer/{id}")
    Customer getCustomerById(@PathVariable Long id ){
     return customerRepository.findById(id)
             .orElseThrow(()-> new CustomerNotFoundException(id));

    }
    @PutMapping("/customer/{id}")
    Customer updateCustomer(@RequestBody Customer newUser, @PathVariable Long id){

        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setCustName(newUser.getCustName());
                    customer.setEmail(newUser.getEmail());
                    customer.setItem(newUser.getItem());
                    customer.setPrice(newUser.getPrice());
                    customer.setRegistrationDate(newUser.getRegistrationDate());
                    return customerRepository.save(customer);



                }).orElseThrow(()-> new CustomerNotFoundException(id));
    }
    @DeleteMapping("customer/{id}")
    String deleteCustomer(@PathVariable Long id){

        if(!customerRepository.existsById(id)){

           throw new CustomerNotFoundException(id);
        }

        customerRepository.deleteById(id);
        return "User with id "+id+" has been deleted success";

    }
}
