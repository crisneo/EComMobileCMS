package com.ecomCMS.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecomCMS.models.Category;
import com.ecomCMS.models.Product;

public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private DataSource dataSource;
	 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				//return null;
				List<Category> categories = new ArrayList<Category>();
				try{
					//DbConnection connection = new DbConnection();
					PreparedStatement stmt = this.dataSource.getConnection().prepareStatement("select * from category");
					ResultSet set = stmt.executeQuery();
					while(set.next()){
						Category category = new Category();
						category.setId(set.getInt("id"));
						category.setCode(set.getString("code").toString());
						category.setName(set.getString("name").toString());
						category.setDescription(set.getString("description").toString());
						category.setImageUrl(set.getString("image"));
						//category.setImageUrl(set.getString("").toString());
						//product.setPrice(Double.parseDouble(set.getString("price")));
						//product.setAvailableUnits(Integer.parseInt(set.getString("availableunits")));
						categories.add(category);
					}
					set.close();
					stmt.close();
					this.dataSource.getConnection().close();
				}
				catch(Exception exc){
					
				}
				finally{
					
				}
				return categories;
	}

	@Override
	public Category getByCode(String code) {
		// TODO Auto-generated method stub
		List<Category> categories = new ArrayList<Category>();
		try{
			//DbConnection connection = new DbConnection();
			PreparedStatement stmt = this.dataSource.getConnection().prepareStatement("select * from category where code = '"+code+"'");
			ResultSet set = stmt.executeQuery();
			while(set.next()){
				Category category = new Category();
				category.setId(set.getInt("id"));
				category.setCode(set.getString("code").toString());
				category.setName(set.getString("name").toString());
				category.setDescription(set.getString("description").toString());
				category.setImageUrl(set.getString("image"));
				//category.setImageUrl(set.getString("").toString());
				//product.setPrice(Double.parseDouble(set.getString("price")));
				//product.setAvailableUnits(Integer.parseInt(set.getString("availableunits")));
				categories.add(category);
			}
			set.close();
			stmt.close();
			this.dataSource.getConnection().close();
		}
		catch(Exception exc){
			return null;
		}
		finally{
			
		}
		return categories.get(0);
	}

	@Override
	public String createCategory(Category category) {
		// TODO Auto-generated method stub
		String query = "insert into category(code, name, description, image)"+
				" values('%1$s', '%2$s', '%3$s', '%4$s')";
		try{
			Statement stmt = this.dataSource.getConnection().createStatement();
			String formattedQuery = String.format(query, category.getCode(), category.getName()
					, category.getDescription(), 
					category.getImageUrl());
			stmt.executeUpdate(formattedQuery);
			stmt.close();
			this.dataSource.getConnection().close();
		}
		catch(Exception exc){
			String arg = exc.getMessage();
			return arg;
		}
		return "";
	}

	@Override
	public void updateCategory(Category category) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String query = "update category set code = '%1$s', name='%2$s', description='%3$s', image='%4$s' where id=%5$s";
				try{
					Statement stmt = this.dataSource.getConnection().createStatement();
					String formattedQuery = String.format(query,  category.getCode(), category.getName()
							, category.getDescription(), 
							category.getImageUrl(), category.getId());
					stmt.executeUpdate(formattedQuery);
					stmt.close();
					this.dataSource.getConnection().close();
				}
				catch(Exception exc){
					String arg = exc.getMessage();
					
				}
				
		
	}

	@Override
	public boolean deleteCategory(Category category) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String query = "delete from category where code='%1$s'";
				try{
					
					Statement stmt = this.dataSource.getConnection().createStatement();
					String formattedQuery = String.format(query, category.getCode());
					stmt.executeUpdate(formattedQuery);
					stmt.close();
					this.dataSource.getConnection().close();
				}
				catch(Exception exc){
					return false;
				}
				return true;
	}

	@Override
	public Category getById(int id) {
		// TODO Auto-generated method stub
				List<Category> categories = new ArrayList<Category>();
				try{
					//DbConnection connection = new DbConnection();
					PreparedStatement stmt = this.dataSource.getConnection().prepareStatement("select * from category where id = "+id);
					ResultSet set = stmt.executeQuery();
					while(set.next()){
						Category category = new Category();
						category.setId(set.getInt("id"));
						category.setCode(set.getString("code").toString());
						category.setName(set.getString("name").toString());
						category.setDescription(set.getString("description").toString());
						category.setImageUrl(set.getString("image"));
						//category.setImageUrl(set.getString("").toString());
						//product.setPrice(Double.parseDouble(set.getString("price")));
						//product.setAvailableUnits(Integer.parseInt(set.getString("availableunits")));
						categories.add(category);
					}
					set.close();
					stmt.close();
					this.dataSource.getConnection().close();
				}
				catch(Exception exc){
					
				}
				finally{
					
				}
				return categories.get(0);
	}

}
