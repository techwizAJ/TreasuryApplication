package com.pankhudi.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="invoices")
public class Invoice {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer pkInvoiceId;
	
	@Column(name="total_invoice_amount")
	private float totalAmount;
	
	@Column(name="discount_amount")
	private float discountAmount;
	
	@Column(name="payment_amount")
	private float paymentAmount;
	
	@Column(name="date")
	private Date createDate ;
	
	@ManyToOne
	@JoinColumn( name = "fk_customer_id" )
	private Customers customer ;

	public Invoice(float totalAmount, float discountAmount, float paymentAmount, Date createDate, Customers customer) {
		super();
		this.totalAmount = totalAmount;
		this.discountAmount = discountAmount;
		this.paymentAmount = paymentAmount;
		this.createDate = createDate;
		this.customer = customer;
	}

	public Invoice() {
		super();
	}

	public Integer getPkInvoiceId() {
		return pkInvoiceId;
	}

	public void setPkInvoiceId(Integer pkInvoiceId) {
		this.pkInvoiceId = pkInvoiceId;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public float getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(float discountAmount) {
		this.discountAmount = discountAmount;
	}

	public float getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(float paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}
	
	
}
