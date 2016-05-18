package com.ecomCMS.models;

import java.io.File;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class Product {
	
	int id;
	

	@NotNull
	@Size(min=4)
	private String code;
	@NotNull
	@Size(min=1)
	private String name;
	@NotNull
	private String description;
	
	private String image;
	@NotNull 
	@NumberFormat(style = Style.NUMBER)
	private double price;
	@NotNull
	@NumberFormat(style = Style.NUMBER)
	private int availableUnits;
	@NotNull
	
	private int categoryId;
	//only for showing
	private String categoryCode;
	
	private boolean deleted;
	
	
	public void setVisible(boolean deleted) {
		this.deleted = deleted;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	private boolean offered;
	private byte[] imageFile;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public byte[] getImageFile() {
		return imageFile;
	}
	public void setImageFile(byte[] imageFile) {
		this.imageFile = imageFile;
	}
	public boolean isOffered() {
		return offered;
	}
	public void setOffered(boolean offered) {
		this.offered = offered;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getAvailableUnits() {
		return availableUnits;
	}
	public void setAvailableUnits(int availableUnits) {
		this.availableUnits = availableUnits;
	}
	
	@Override
	public String toString(){
		return this.code;
	}
	
	public Product(){
		
	}
	
	public boolean isDeleted() {
		return deleted;
	}
	
	public Product(String code, String name, String description, String image,
			double price, int availableUnits, boolean deleted) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
		this.image = image;
		this.price = price;
		this.availableUnits = availableUnits;
		this.deleted=deleted;
	}
	
	
	

}
