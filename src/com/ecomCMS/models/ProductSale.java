package com.ecomCMS.models;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;



public class ProductSale {
	
	private int id;
	@NotNull
	private String user;
	@NotNull
	private int productId;
	@NotNull
	
	private Date date;
	@NotNull
	@NumberFormat(style = Style.NUMBER)
	private int quantity;
	
	/** datos solo para vizualizar***/
	private String productCode;
	private String productName;
	private double totalPrice;
	
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

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public ProductSale(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProductSale(String user, int productId, Date date,
			double price, int quantity) {
		super();
		//this.id = id;
		this.user = user;
		this.productId = productId;
		this.date = date;
		this.totalPrice=price;
		this.quantity = quantity;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
