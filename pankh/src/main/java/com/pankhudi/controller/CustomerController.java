package com.pankhudi.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pankhudi.model.Customers;
import com.pankhudi.service.DaoServices;

@CrossOrigin( origins = "*" )
@RestController
@RequestMapping( path = "api/v1" )
public class CustomerController {

	private final DaoServices<Customers> customerService;

	@Autowired
	public CustomerController(DaoServices<Customers> customerService) {
		this.customerService = customerService;
	}

	// list url mappings
	@GetMapping("/customers")
	private List<Customers> getCustomer() {
		return customerService.getAllobjects();
	}

	@PostMapping(("/customers/createCustomer/"))
	private ResponseEntity<Customers> saveCustomers( @RequestBody Customers customer1 ){
		Customers customer = customerService.getObjectByName(customer1.getName() );
		if( customer != null ) {
			return (ResponseEntity<Customers>) ResponseEntity.badRequest().body(customer1);
		}
		customerService.saveObject(customer1);
		return ResponseEntity.ok(customer1);	
	}

	@PutMapping("/customers/{id}")
	private ResponseEntity<Customers> updateCustomers( @PathVariable int id,@RequestBody Customers customer ){
		Customers customer1 = customerService.getPkObjectId(id);
		if( customer1 == null || (id != customer.getPkCustomerId()) ) {
			return (ResponseEntity<Customers>) ResponseEntity.badRequest().body(customer);
		}
		customer1.setName(customer.getName());
		customer1.setMail(customer.getMail());
		customer1.setContact(customer.getContact());
		customer1.setCreateUser("Web API Call");
		customer1.setCreateDate(new Date() );
		customerService.saveObject(customer1);
		return ResponseEntity.ok(customer1);	
	}

	@DeleteMapping("/customers/{id}")
	private ResponseEntity<Customers> deleteCustomers( @PathVariable int id ){
		Customers customer1 = customerService.getPkObjectId(id);
		if( customer1 == null ) {
			return (ResponseEntity<Customers>) ResponseEntity.badRequest().body(customer1);
		}
		customerService.deleteObjectByPkId(id);
		return ResponseEntity.ok(customer1);	
	}	
}