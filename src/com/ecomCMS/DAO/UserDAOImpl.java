package com.ecomCMS.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;


import com.ecomCMS.models.User;

public class UserDAOImpl implements UserDAO {

	@Autowired
	private DataSource dataSource;
	 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		try{
			//DbConnection connection = new DbConnection();
			PreparedStatement stmt = this.dataSource.getConnection().prepareStatement("select * from user");
			ResultSet set = stmt.executeQuery();
			while(set.next()){
				
				User user = new User();
				//user.setId(Integer.parseInt(set.getString("id")));
				user.setName(set.getString("username").toString());
				user.setRole(getRoleByUserName(user.getName()));
				user.setEnabled(Integer.parseInt(set.getString("enabled"))==1?true:false);
				users.add(user);
			}
			set.close();
			stmt.close();
			this.dataSource.getConnection().close();
		}
		catch(Exception exc){
			
		}
		finally{
			
		}
		return users;
	}
	
	private String getRoleByUserName(String userName){
		String role="";
		try{
			//DbConnection connection = new DbConnection();
			PreparedStatement stmt = this.dataSource.getConnection().prepareStatement("select ROLE from user_role where username='"+userName+"'");
			ResultSet set = stmt.executeQuery();
			while(set.next()){
				role=set.getString("ROLE");
				
			}
			set.close();
			stmt.close();
			this.dataSource.getConnection().close();
		}
		catch(Exception exc){
			
		}
		return role;
	}

	

	@Override
	public User getByName(String name) {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		try{
			//DbConnection connection = new DbConnection();
			PreparedStatement stmt = this.dataSource.getConnection().prepareStatement("select * from user where username ='"+name+"'");
			ResultSet set = stmt.executeQuery();
			while(set.next()){
				
				User user = new User();
				//user.setId(Integer.parseInt(set.getString("id")));
				user.setName(set.getString("username").toString());
				user.setPassword(set.getString("password"));
				user.setRole(getRoleByUserName(user.getName()));
				user.setEnabled(Integer.parseInt(set.getString("enabled"))==1?true:false);
				users.add(user);
			}
			set.close();
			stmt.close();
			this.dataSource.getConnection().close();
		}
		catch(Exception exc){
			
		}
		finally{
			
		}
		return users.get(0);
	}

	@Override
	public String createUser(User user) {
		// TODO Auto-generated method stub
		String query = "insert into user(username, password, enabled)"+
				" values('%1$s', '%2$s', %3$s)";
		try{
			Statement stmt = this.dataSource.getConnection().createStatement();
			String formattedQuery = String.format(query, user.getName(), user.getPassword(), user.isEnabled()?1:0);
					
			stmt.executeUpdate(formattedQuery);
			stmt.close();
			this.dataSource.getConnection().close();
			createRole(user.getName(), user.getRole());
		}
		catch(Exception exc){
			String arg = exc.getMessage();
			return arg;
		}
		return "";
	}
	
	private void createRole(String userName, String role){
		String query = "insert into user_role(username, ROLE)"+
				" values('%1$s', '%2$s')";
		try{
			Statement stmt = this.dataSource.getConnection().createStatement();
			String formattedQuery = String.format(query, userName, role);
					
			stmt.executeUpdate(formattedQuery);
			stmt.close();
			this.dataSource.getConnection().close();
		}
		catch(Exception exc){
			String arg = exc.getMessage();
			
		}
		
	
	}
	
	private void updateRole(String userName, String role){
		
		String query = "update user_role set ROLE='%1$s' where username='%2$s'";
		try{
			Statement stmt = this.dataSource.getConnection().createStatement();
			String formattedQuery = String.format(query, role, userName);
					
			stmt.executeUpdate(formattedQuery);
			stmt.close();
			this.dataSource.getConnection().close();
		}
		catch(Exception exc){
			String arg = exc.getMessage();
			
		}
		
	
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		String query = "update user set password='%1$s', enabled=%2$s where username='%3$s'";
		try{
			Statement stmt = this.dataSource.getConnection().createStatement();
			String formattedQuery = String.format(query, user.getPassword(), user.isEnabled()?1:0, user.getName());
					
			stmt.executeUpdate(formattedQuery);
			stmt.close();
			this.dataSource.getConnection().close();
			updateRole(user.getName(), user.getRole());
		}
		catch(Exception exc){
			String arg = exc.getMessage();
			
		}
		
		
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		String query = "delete from user where username='%1$s'";
		try{
			Statement stmt = this.dataSource.getConnection().createStatement();
			String formattedQuery = String.format(query, user.getName());
			stmt.executeUpdate("delete from user_role where username='"+user.getName()+"'");
			stmt.executeUpdate(formattedQuery);
			stmt.close();
			this.dataSource.getConnection().close();
		}
		catch(Exception exc){
			return false;
		}
		return true;
	}

}
