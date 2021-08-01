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
@Table(name = "map_vendor_products")
public class VendorProducts {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "pk_map_vendor_products")
	private Integer pkMapVendorProductId;
	
	@ManyToOne
	@JoinColumn( name = "fk_vendor_id" )
	private Vendors vendor;
	
	@ManyToOne
	@JoinColumn( name = "fk_product_id" )
	private Products product;

	public VendorProducts() {
	}
	
	public VendorProducts(Vendors vendor, Products product) {
		this.vendor = vendor;
		this.product = product;
	}

	public Integer getPkMapVendorProductId() {
		return pkMapVendorProductId;
	}

	public void setPkMapVendorProducId(Integer pkMapVendorProductId) {
		this.pkMapVendorProductId = pkMapVendorProductId;
	}

	public Vendors getVendor() {
		return vendor;
	}

	public void setVendor(Vendors vendor) {
		this.vendor = vendor;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}
}
