package com.ecomCMS.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StoreInfo {
	
	@NotNull
	@Size(min=1)
	private String name;
	@NotNull
	@Size(min=1)
	private String info;
	@NotNull
	
	private int starProductId;
	@NotNull
	@Size(min=1)
	private String storeEmail;
	@NotNull
	@Size(min=1)
	
	private String payPalAccount;
	
	public String getStoreEmail() {
		return storeEmail;
	}

	public void setStoreEmail(String storeEmail) {
		this.storeEmail = storeEmail;
	}

	public String getPayPalAccount() {
		return payPalAccount;
	}

	public void setPayPalAccount(String payPalAccount) {
		this.payPalAccount = payPalAccount;
	}

	public int getStarProductId() {
		return starProductId;
	}

	public void setStarProductId(int starProductId) {
		this.starProductId = starProductId;
	}

	public StoreInfo(){
		
	}
	
	public StoreInfo(String name, String info){
		this.name=name;
		this.info=info;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	

}
