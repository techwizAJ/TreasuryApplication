package com.pankhudi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="products" , schema = "pankhudi")
public class Products {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name="pk_product_id")
	private Integer pkProductId;
	
	@Column(name = "image_path")
	private String imagePath;
	
	@Column(name = "shortname")
	private String shortName;
	
	@Column(name="true_amount")
	private float trueAmount;

	public Products() {
	}

	public Products(Integer pkProductId, String imagePath, String shortName, float trueAmount) {
		this.pkProductId = pkProductId;
		this.imagePath = imagePath;
		this.shortName = shortName;
		this.trueAmount = trueAmount;
	}

	public Integer getPkProductId() {
		return pkProductId;
	}

	public void setPkProductId(Integer pkProductId) {
		this.pkProductId = pkProductId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public float getTrueAmount() {
		return trueAmount;
	}

	public void setTrueAmount(float trueAmount) {
		this.trueAmount = trueAmount;
	}
}
