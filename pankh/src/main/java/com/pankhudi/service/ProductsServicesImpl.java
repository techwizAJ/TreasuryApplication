package com.pankhudi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pankhudi.model.Products;
import com.pankhudi.repository.ProductsRepository;

@Service
public class ProductsServicesImpl implements DaoServices<Products> {
	
	private ProductsRepository productsRepository;
	
	@Autowired
	public ProductsServicesImpl(ProductsRepository productsRepository) {
		this.productsRepository = productsRepository;
	}


	@Override
	public List<Products> getAllobjects() {
		// TODO Auto-generated method stub
		return this.productsRepository.findAll();
	}

	@Override
	public void saveObject(Products objects) {
		this.productsRepository.save(objects);
	}

	@Override
	public Products getPkObjectId(int id) {
		Optional<Products> optional = this.productsRepository.findById(id);
		Products product = null;
		if (optional.isPresent()) {
			product = optional.get();
		} else {
			throw new RuntimeException(" product not found for id :: " + id);
		}
		return product;
	}

	@Override
	public void deleteObjectByPkId(int id) {
		this.productsRepository.deleteById(id);
	}


	@Override
	public Products getObjectByName(String name) {
		Optional<Products> optional = this.productsRepository.findByName(name) ;
		Products product = null;
		if (optional.isPresent()) {
			product = optional.get();
		}
		return product;
	}

}
