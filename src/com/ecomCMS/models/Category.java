package com.ecomCMS.models;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Category {
	
	int id;
	

	@NotNull
	@Size(min=4)
	private String code;
	@NotNull
	@Size(min=1)
	private String name;
	@NotNull
	
	private String description;
	private String imageUrl;
	private List<Product> products;
	
	public Category(){
		
	}
	
	
	public Category(String code, String name, String description,
			String imageUrl) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	
	@Override
	public String toString(){
		return this.code;
	}

}
