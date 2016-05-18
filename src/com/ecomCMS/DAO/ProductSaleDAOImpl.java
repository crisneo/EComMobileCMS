package com.ecomCMS.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecomCMS.models.Product;
import com.ecomCMS.models.ProductSale;

public class ProductSaleDAOImpl implements ProductSaleDAO {

	@Autowired
	private DataSource dataSource;
	 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public List<ProductSale> getAllSales() {
		// TODO Auto-generated method stub
		List<ProductSale> sales = new ArrayList<ProductSale>();
		try{
			//DbConnection connection = new DbConnection();
			PreparedStatement stmt = this.dataSource.getConnection().prepareStatement("select ps.id, ps.username, ps.id_product, p.code, p.name, ps.date, ps.quantity, ps.totalPrice from product_sale ps, product p where ps.id_product=p.id");
			ResultSet set = stmt.executeQuery();
			while(set.next()){
				ProductSale sale = new ProductSale();
				sale.setId(set.getInt("id"));
				//sale.setUser(set.getString("ProductSale"));
				sale.setProductId(set.getInt("id_product"));
				sale.setDate(set.getDate("date"));
				sale.setQuantity(set.getInt("quantity"));
				sale.setProductName(set.getString("name"));
				sale.setProductCode(set.getString("code"));
				sale.setUser(set.getString("username"));
				sale.setTotalPrice(set.getDouble("totalPrice"));
				sales.add(sale);
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
		return sales;
	}

	@Override
	public ProductSale getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductSale getByDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductSale> getBetweenDates(Date dateMin, Date dateMax) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addSale(ProductSale sale) {
		String query = "insert into product_sale(username, id_product, date, quantity, totalPrice)"+
				" values(?, ?, ?, ?,?)";
		try{
			PreparedStatement stmt = this.dataSource.getConnection().prepareStatement(query);
			//String formattedQuery = String.format(query, user.getName(), user.getPassword(), user.isEnabled()?1:0);
					
			//stmt.executeUpdate(formattedQuery);
			stmt.setString(1, sale.getUser());
			stmt.setInt(2,  sale.getProductId());
			stmt.setDate(3, (java.sql.Date) sale.getDate());
			stmt.setInt(4, sale.getQuantity());
			stmt.setDouble(5, sale.getTotalPrice());
			
			stmt.executeUpdate();
			stmt.close();
			this.dataSource.getConnection().close();
			//createRole(user.getName(), user.getRole());
		}
		catch(Exception exc){
			String arg = exc.getMessage();
			
		}
		
		
	}

	@Override
	public void editSale(ProductSale sale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSale(int id) {
		// TODO Auto-generated method stub
		
	}

}
