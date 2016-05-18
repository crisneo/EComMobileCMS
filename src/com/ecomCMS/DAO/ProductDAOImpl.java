package com.ecomCMS.DAO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ecomCMS.models.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {
	@Autowired
	private DataSource dataSource;
	 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		//return null;
		List<Product> products = new ArrayList<Product>();
		try{
			//DbConnection connection = new DbConnection();
			PreparedStatement stmt = this.dataSource.getConnection().prepareStatement("select * from product where deleted=0");
			ResultSet set = stmt.executeQuery();
			while(set.next()){
				Product product = new Product();
				product.setId(set.getInt("id"));
				product.setCode(set.getString("code").toString());
				product.setName(set.getString("name").toString());
				product.setDescription(set.getString("description").toString());
				product.setImage(set.getString("imageurl").toString());
				product.setPrice(Double.parseDouble(set.getString("price")));
				product.setAvailableUnits(Integer.parseInt(set.getString("availableunits")));
				product.setCategoryId(set.getInt("categoryId"));
				product.setOffered(Integer.parseInt(set.getString("offered"))==1?true:false);
				//product.setCategoryCode(new CategoryDAOImpl().getById(product.getCategoryId()).getCode());
				//Blob imageBlob = set.getBlob("imageFile");
				//InputStream binaryStream = imageBlob.getBinaryStream(0, imageBlob.length());
				//product.setImageFile(binaryStream.rea)
				products.add(product);
			}
			set.close();
			stmt.close();
			this.dataSource.getConnection().close();
		}
		catch(Exception exc){
			String m = exc.getMessage();
		}
		finally{
			
		}
		return products;
		
	}
	
	public InputStream getStreamBlobImage(String productCode){
		//List<Product> products = new ArrayList<Product>();
		InputStream inputStream=null;
		try{
			//DbConnection connection = new DbConnection();
			PreparedStatement stmt = this.dataSource.getConnection().prepareStatement("select imageFile from product where " +
					"code = '"+productCode+"'");
			ResultSet set = stmt.executeQuery();
			while(set.next()){
				Blob imageBlob = set.getBlob("imageFile");
				inputStream = imageBlob.getBinaryStream(1, imageBlob.length());
				
			}
			set.close();
			stmt.close();
			this.dataSource.getConnection().close();
		}
		catch(Exception exc){
			String p = exc.getMessage();
			
		}
		finally{
			
		}
		return inputStream;
	}

	@Override
	public List<Product> getByCategory(int categoryId) {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<Product>();
		try{
			//DbConnection connection = new DbConnection();
			PreparedStatement stmt = this.dataSource.getConnection().prepareStatement("select * from product where " +
					"categoryId = "+categoryId+" and deleted=0");
			ResultSet set = stmt.executeQuery();
			while(set.next()){
				Product product = new Product();
				product.setId(set.getInt("id"));
				product.setCode(set.getString("code").toString());
				product.setName(set.getString("name").toString());
				product.setDescription(set.getString("description").toString());
				//product.setImage(set.getString("imageurl").toString());
				product.setPrice(Double.parseDouble(set.getString("price")));
				product.setAvailableUnits(Integer.parseInt(set.getString("availableunits")));
				product.setOffered(Integer.parseInt(set.getString("offered"))==1?true:false);
				product.setCategoryId(set.getInt("categoryId"));
				products.add(product);
			}
			set.close();
			stmt.close();
			this.dataSource.getConnection().close();
		}
		catch(Exception exc){
			
		}
		finally{
			
		}
		return products;
	}
	
	public List<Product> getOfferedProducts(){
		List<Product> products = new ArrayList<Product>();
		try{
			//DbConnection connection = new DbConnection();
			PreparedStatement stmt = this.dataSource.getConnection().prepareStatement("select * from product where " +
					" offered=1 and deleted=0");
			ResultSet set = stmt.executeQuery();
			while(set.next()){
				Product product = new Product();
				product.setId(set.getInt("id"));
				product.setCode(set.getString("code").toString());
				product.setName(set.getString("name").toString());
				product.setDescription(set.getString("description").toString());
				//product.setImage(set.getString("imageurl").toString());
				product.setPrice(Double.parseDouble(set.getString("price")));
				product.setAvailableUnits(Integer.parseInt(set.getString("availableunits")));
				product.setOffered(Integer.parseInt(set.getString("offered"))==1?true:false);
				product.setCategoryId(set.getInt("categoryId"));
				products.add(product);
			}
			set.close();
			stmt.close();
			this.dataSource.getConnection().close();
		}
		catch(Exception exc){
			
		}
		finally{
			
		}
		return products;
		
	}

	@Override
	public Product getByCode(String code) {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<Product>();
		try{
			//DbConnection connection = new DbConnection();
			PreparedStatement stmt = this.dataSource.getConnection().prepareStatement("select * from product where " +
					"code = '"+code+"'");
			ResultSet set = stmt.executeQuery();
			while(set.next()){
				Product product = new Product();
				product.setId(set.getInt("id"));
				product.setCode(set.getString("code").toString());
				product.setName(set.getString("name").toString());
				product.setDescription(set.getString("description").toString());
				product.setImage(set.getString("imageurl").toString());
				product.setPrice(Double.parseDouble(set.getString("price")));
				product.setAvailableUnits(Integer.parseInt(set.getString("availableunits")));
				product.setCategoryId(set.getInt("categoryId"));
				product.setOffered(Integer.parseInt(set.getString("offered"))==1?true:false);
				products.add(product);
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
		return products.get(0);
	}

	@Override
	public String createProduct(Product product, InputStream inputStream, String fileName) {
		String query = "insert into product(code, name, description, imageurl, price, availableunits, categoryId, offered, imageFile)"+
				" values('%1$s', '%2$s', '%3$s', '%4$s', %5$s, %6$s, '%7$s', %8$s)";
		try{
			
		
			/*String formattedQuery = String.format(query, product.getCode(), product.getName()
					, product.getDescription(), product.getImage(), product.getPrice(), product.getAvailableUnits(),
					product.getCategoryCode(), product.isOffered()?1:0);*/
			String formattedQuery = "insert into product(code, name, description, imageurl, price, availableunits, categoryId, offered, imageFile, deleted)"+
					" values(?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
			PreparedStatement stmt = this.dataSource.getConnection().prepareStatement(formattedQuery);
			
			stmt.setString(1, product.getCode());
			stmt.setString(2, product.getName());
			stmt.setString(3, product.getDescription());
			stmt.setString(4, saveFileImage(inputStream, product.getCode(), fileName));
			stmt.setDouble(5, product.getPrice());
			stmt.setInt(6, product.getAvailableUnits());
			stmt.setInt(7, product.getCategoryId());
			stmt.setBoolean(8, product.isOffered());
			//stmt.setBlob(9, inputStream);
			stmt.setNull(9, java.sql.Types.BLOB);
			stmt.setInt(10, 0);
			//stmt.executeUpdate(formattedQuery);
			stmt.executeUpdate();
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
	public void updateProduct(Product product, InputStream inputStream, String fileName) {
		
		
		String formattedQuery = "update product set code=?, name=?, description=?, price=?, availableunits=?, offered=? ,imageurl= ?,  categoryId=? where"
				+" id="+product.getId();
		if(inputStream == null){
			formattedQuery = "update product set code=?, name=?, description=?, price=?, availableunits=?, offered=?,  categoryId=? where"
					+" id="+product.getId();
		}
		try{
			PreparedStatement stmt = this.dataSource.getConnection().prepareStatement(formattedQuery);
			
			stmt.setString(1, product.getCode());
			stmt.setString(2, product.getName());
			stmt.setString(3, product.getDescription());
			stmt.setDouble(4, product.getPrice() );
			//stmt.setDouble(5, product.getPrice());
			stmt.setInt(5, product.getAvailableUnits());
			stmt.setInt(6, product.isOffered()?1:0);
			if(inputStream!=null){
				//stmt.setBlob(8, inputStream);
				stmt.setString(7, this.saveFileImage(inputStream, product.getCode(), fileName));
				stmt.setInt(8, product.getCategoryId());
			}else{
				stmt.setInt(7, product.getCategoryId());
			}
			
			stmt.executeUpdate();
			stmt.close();
			this.dataSource.getConnection().close();
		}
		catch(Exception exc){
			String arg = exc.getMessage();
			//return arg;
		}
		
		
	}

	@Override
	public boolean deleteProduct(Product product) {
		// TODO Auto-generated method stub
		//String query = "delete from product where code='%1$s'";
		String query = "update product set deleted=1 where code='%1$s'";
		try{
			Statement stmt = this.dataSource.getConnection().createStatement();
			String formattedQuery = String.format(query, product.getCode());
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
	public Product getById(int id) {
		// TODO Auto-generated method stub
				List<Product> products = new ArrayList<Product>();
				try{
					//DbConnection connection = new DbConnection();
					PreparedStatement stmt = this.dataSource.getConnection().prepareStatement("select * from product where " +
							"id = "+id);
					ResultSet set = stmt.executeQuery();
					while(set.next()){
						Product product = new Product();
						product.setId(set.getInt("id"));
						product.setCode(set.getString("code").toString());
						product.setName(set.getString("name").toString());
						product.setDescription(set.getString("description").toString());
						product.setImage(set.getString("imageurl").toString());
						product.setPrice(Double.parseDouble(set.getString("price")));
						product.setAvailableUnits(Integer.parseInt(set.getString("availableunits")));
						product.setCategoryId(set.getInt("categoryId"));
						product.setOffered(Integer.parseInt(set.getString("offered"))==1?true:false);
						products.add(product);
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
				return products.get(0);
	}
	@Override
	public List<Product> getByCategoryCode(String categoryCode) {
		// TODO Auto-generated method stub
				//int categoryId = new CategoryDAOImpl().getByCode(categoryCode).getId();
				//return this.getByCategory(categoryId);
		List<Product> products=new ArrayList<Product>();
		int categoryId =0;
		try{
			PreparedStatement stmt = this.dataSource.getConnection().prepareStatement("select id from category where code='"+categoryCode+"'");
			ResultSet set = stmt.executeQuery();
			while(set.next()){
				categoryId = set.getInt("id");
			}
			set.close();
			stmt.close();
			this.dataSource.getConnection().close();
			return this.getByCategory(categoryId);
		}
		catch(Exception exc){
			
		}
		return products;
	}
	@Override
	public List<Product> searchProduct(String criteria) {
		List<Product> products = new ArrayList<Product>();
		try{
			//DbConnection connection = new DbConnection();
			PreparedStatement stmt = this.dataSource.getConnection().prepareStatement("select * from product where "+criteria);
			ResultSet set = stmt.executeQuery();
			while(set.next()){
				Product product = new Product();
				product.setId(set.getInt("id"));
				product.setCode(set.getString("code").toString());
				product.setName(set.getString("name").toString());
				product.setDescription(set.getString("description").toString());
				product.setImage(set.getString("imageurl").toString());
				product.setPrice(Double.parseDouble(set.getString("price")));
				product.setAvailableUnits(Integer.parseInt(set.getString("availableunits")));
				product.setCategoryId(set.getInt("categoryId"));
				product.setOffered(Integer.parseInt(set.getString("offered"))==1?true:false);
				//product.setCategoryCode(new CategoryDAOImpl().getById(product.getCategoryId()).getCode());
				//Blob imageBlob = set.getBlob("imageFile");
				//InputStream binaryStream = imageBlob.getBinaryStream(0, imageBlob.length());
				//product.setImageFile(binaryStream.rea)
				products.add(product);
			}
			set.close();
			stmt.close();
			this.dataSource.getConnection().close();
		}
		catch(Exception exc){
			String m = exc.getMessage();
		}
		finally{
			
		}
		return products;
	}
	
	private String saveFileImage(InputStream inputStream, String code, String fileName) {
		String url = "";
		try {
			// InputStream inputStream = null;
			OutputStream outputStream = null;

			
			 Properties props = System.getProperties();  
			 String jbossServerHomeUrl = props.getProperty( "jboss.home.dir");
			 
			 File directory = new File(jbossServerHomeUrl+"/EcomCMSData/images/");
			 directory.mkdirs();
			 url = directory.getPath() + "/"+code+"_"+fileName;
			//outputStream = new FileOutputStream("F:\\test111\\" + code+".jpg");
			outputStream = new FileOutputStream(url);
			
		
			int readBytes = 0;
			byte[] buffer = new byte[1024];
			  
			//int bytesRead;  
			while ((readBytes = inputStream.read(buffer)) != -1) {  
				outputStream.write(buffer, 0, readBytes);  
			}
			outputStream.close();
			inputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;

	}

}
