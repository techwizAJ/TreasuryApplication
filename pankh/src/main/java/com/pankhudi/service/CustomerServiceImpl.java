package com.pankhudi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pankhudi.model.Customers;
import com.pankhudi.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements DaoServices<Customers>{
	
	private CustomerRepository customerRepository;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customers> getAllobjects() {
		// TODO Auto-generated method stub
		return this.customerRepository.findAll();
	}

	@Override
	public void saveObject(Customers customers) {
		this.customerRepository.save(customers);
	}

	@Override
	public Customers getPkObjectId(int id) {
		Optional<Customers> optional = this.customerRepository.findById(id);
		Customers customer = null;
		if (optional.isPresent()) {
			customer = optional.get();
		}
		return customer;
	}

	@Override
	public void deleteObjectByPkId(int id) {
		this.customerRepository.deleteById(id);
	}
	
	@Override
	public Customers getObjectByName(String name) {
		Optional<Customers> optional = this.customerRepository.findByName(name) ;
		Customers customer = null;
		if (optional.isPresent()) {
			customer = optional.get();
		}
		return customer;
	}
	
	public List<Customers> findPkCustomBasedOnRegion(String region) {
		return (List<Customers>) this.customerRepository.findPkIdBasedOnRegion(region);
	}

}
