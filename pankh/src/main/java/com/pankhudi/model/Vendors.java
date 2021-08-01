package com.pankhudi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vendors" , schema = "pankhudi")
public class Vendors {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name="pk_vendors_id")
	private Integer pkVendorId;
	
	@Column(name = "contact")
	private String contact;
	
	@Column(name = "shortname")
	private String shortName;
	
	@Column(name="mail")
	private String mail;

	@Column(name="create_date")
	private Date createDate;
	
	@Column(name="region")
	private String region;
	
	@Column(name="country")
	private String country;

	public Vendors() {
	}
	
	public Vendors(String contact, String shortName, String mail, Date createDate, String region, String country) {
		this.contact = contact;
		this.shortName = shortName;
		this.mail = mail;
		this.createDate = createDate;
		this.region = region;
		this.country = country;
	}


	public Integer getPkVendorId() {
		return pkVendorId;
	}

	public void setPkVendorId(Integer pkVendorId) {
		this.pkVendorId = pkVendorId;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
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
