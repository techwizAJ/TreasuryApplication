package com.pankhudi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pankhudi.model.Vendors;
import com.pankhudi.repository.VendorsRepository;

@Service
public class VendorServicesImpl implements DaoServices<Vendors>{

	private VendorsRepository vendorRepository;

	@Autowired
	public VendorServicesImpl(VendorsRepository vendorRepository) {
		this.vendorRepository = vendorRepository;
	}

	@Override
	public List<Vendors> getAllobjects() {
		// TODO Auto-generated method stub
		return this.vendorRepository.findAll();
	}

	@Override
	public void saveObject(Vendors objects) {
		this.saveObject(this.vendorRepository.save(objects));
	}

	@Override
	public Vendors getPkObjectId(int id) {
		Optional<Vendors> optional = this.vendorRepository.findById(id);
		Vendors vendor = null;
		if (optional.isPresent()) {
			vendor = optional.get();
		} else {
			throw new RuntimeException(" product not found for id :: " + id);
		}
		return vendor;
	}

	@Override
	public void deleteObjectByPkId(int id) {
		this.vendorRepository.deleteById(id);
	}

	@Override
	public Vendors getObjectByName(String name) {
		Optional<Vendors> optional = this.vendorRepository.findByName(name) ;
		Vendors vendor = null;
		if (optional.isPresent()) {
			vendor = optional.get();
		}
		return vendor;
	}
	
	public List<Vendors> findPkCustomBasedOnRegion(String region) {
		return (List<Vendors>) this.vendorRepository.findPkIdBasedOnRegion(region);
	}
}
