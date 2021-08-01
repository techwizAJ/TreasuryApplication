package com.pankhudi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sales_transactions")
public class SalesTransactions {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name="pk_sale_transaction_id")
	private Integer pkSaleTransaction;
	
	private float amount;
	
	private String quantity;
	
	@ManyToOne
	@JoinColumn( name = "fk_productid" )
	private Products product;
	
	@ManyToOne
	@JoinColumn( name = "fk_invoice_number" )
	private Invoice invoice;

	public SalesTransactions() {
	}

	public SalesTransactions(float amount, String quantity, Products product, Invoice invoice) {
		this.amount = amount;
		this.quantity = quantity;
		this.product = product;
		this.invoice = invoice;
	}

	public Integer getPkSaleTransaction() {
		return pkSaleTransaction;
	}

	public void setPkSaleTransaction(Integer pkSaleTransaction) {
		this.pkSaleTransaction = pkSaleTransaction;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
	
	
}
