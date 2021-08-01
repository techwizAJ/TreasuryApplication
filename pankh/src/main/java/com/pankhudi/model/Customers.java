package com.pankhudi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers" , schema = "pankhudi")
public class Customers {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name="pk_customer_id")
	private Integer pkCustomerId;
	
	@Column(name = "contact")
	private String contact;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "create_user")
	private String createUser;
	
	@Column(name="mail")
	private String mail;

	@Column(name="create_date")
	private Date createDate;
	
	@Column(name="region")
	private String region;
	
	@Column(name="country")
	private String country;

	public Customers() {
	}

	public Customers(String contact, String name, String createUser, String mail, Date createDate, String region,
			String country) {
		this.contact = contact;
		this.name = name;
		this.createUser = createUser;
		this.mail = mail;
		this.createDate = createDate;
		this.region = region;
		this.country = country;
	}

	public Integer getPkCustomerId() {
		return pkCustomerId;
	}

	public void setPkCustomerId(Integer pkCustomerId) {
		this.pkCustomerId = pkCustomerId;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}