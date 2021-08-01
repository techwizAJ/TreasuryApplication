package com.pankhudi.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pankhudi.model.Customers;
import com.pankhudi.model.Invoice;
import com.pankhudi.repository.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements DaoServices<Invoice>{
	
	private final InvoiceRepository invoiceRepository;
	
	@Autowired
	public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
		this.invoiceRepository = invoiceRepository;
	}

	@Override
	public List<Invoice> getAllobjects() {
		// TODO Auto-generated method stub
		return invoiceRepository.findAll();
	}

	@Override
	public void saveObject(Invoice objects) {
		invoiceRepository.save(objects);
	}

	@Override
	public Invoice getPkObjectId(int id) {
		return invoiceRepository.getById(id);
	}

	@Override
	public void deleteObjectByPkId(int id) {
		invoiceRepository.deleteById(id);
	}
	
	public String getTotalPaymentAmountByCustomerId(Customers customerPk) {
		return this.invoiceRepository.getTotalPaymentAmountByCustomerId(customerPk);
		
	}

	public List<Invoice> getInvoiceFromDate(Date time) {
		List<Invoice> invoiceList = this.invoiceRepository.getInvoiceFromDate(time);
		return invoiceList;
	}

}
