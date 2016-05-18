package com.ecomCMS.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
	
	@NotNull
	@Size(min=4)
	private String name;
	@NotNull
	@Size(min=6)
	private String password;
	@NotNull
	private String role;
	private boolean enabled;

	public User(){
		
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

}
