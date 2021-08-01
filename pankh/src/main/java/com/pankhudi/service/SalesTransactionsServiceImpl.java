package com.pankhudi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pankhudi.model.SalesTransactions;
import com.pankhudi.repository.SalesTransactionsRepository;

@Service
public class SalesTransactionsServiceImpl implements DaoServices<SalesTransactions>{
	
	private SalesTransactionsRepository salesRepository;
	
	@Autowired
	public SalesTransactionsServiceImpl(SalesTransactionsRepository salesRepository) {
		this.salesRepository = salesRepository;
	}
	

	@Override
	public List<SalesTransactions> getAllobjects() {
		return salesRepository.findAll();
	}

	@Override
	public void saveObject(SalesTransactions objects) {
		salesRepository.save(objects);
	}

	@Override
	public SalesTransactions getPkObjectId(int id) {
		return salesRepository.getById(id);
	}

	@Override
	public void deleteObjectByPkId(int id) {
		salesRepository.deleteById(id);
	}

}
