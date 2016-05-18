package com.ecomCMS.DAO;

import java.util.List;

import com.ecomCMS.models.User;



public interface UserDAO {
	public List<User> getAll();
	
	public User getByName(String name);
	public String createUser(User user);
	public void updateUser(User user);
	public boolean deleteUser(User user);
}
