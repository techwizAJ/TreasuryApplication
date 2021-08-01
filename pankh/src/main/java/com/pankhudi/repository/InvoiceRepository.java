package com.pankhudi.repository;


import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pankhudi.model.Customers;
import com.pankhudi.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{

	@Query("select SUM(s.paymentAmount) from Invoice s where s.customer IN (:id)")
	String getTotalPaymentAmountByCustomerId( @Param("id") Customers id);

	@Query("select s from Invoice s where s.createDate > (:date)")
	List<Invoice> getInvoiceFromDate(@Param("date") Date date);
}
