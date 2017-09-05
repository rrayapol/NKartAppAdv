package com.nisum.model;

import org.springframework.data.annotation.Id;

public class Product {

	@Id
	private String productId;
	private String name;
	private String description;
	private double price;
	private String seller;
	private String imageUrl;
	private boolean isOrdered;

	public boolean isOrdered() {
		return isOrdered;
	}

	public void setOrdered(boolean isOrdered) {
		this.isOrdered = isOrdered;
	}

	public Product() {

	}

	public Product(String name, String description, double price, String seller, String imageUrl) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.seller = seller;
		this.imageUrl = imageUrl;
	}
	
	

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
