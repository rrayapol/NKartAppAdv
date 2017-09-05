package com.nisum.model;

import org.springframework.data.annotation.Id;

public class Order {

	@Id
	private String orderId;
	private String productId;
	private String productName;
	private int quantity;
	private double amount;
	private String timeStamp;
	private String username;
	private String imageUrl;
	

	public Order() {

	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public Order(String productId,String productName,int quantity,double amount,String timeStamp,String username,String imageUrl) {
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.amount = amount;
		this.timeStamp = timeStamp;
		this.username = username;
		this.imageUrl = imageUrl;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
