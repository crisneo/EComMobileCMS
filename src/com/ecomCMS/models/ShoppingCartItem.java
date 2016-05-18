package com.ecomCMS.models;

public class ShoppingCartItem {
	
	private String productCode;
	private String productName;
	private double price;
	
	private int productId;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public ShoppingCartItem(String productCode, String productName,
			double price, int productId) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.price = price;
		this.productId=productId;
	}
	
	public ShoppingCartItem(){
		
	}
	
	

}
