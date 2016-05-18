package com.ecomCMS.DAO;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecomCMS.models.Product;
import com.ecomCMS.models.StoreInfo;

public class SettingsDAOImpl implements SettingsDAO {

	@Autowired
	private DataSource dataSource;
	 
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public StoreInfo getStoreInfo() {
		StoreInfo info=new StoreInfo();
		try{
			//DbConnection connection = new DbConnection();
			PreparedStatement stmt = this.dataSource.getConnection().prepareStatement("select * from storeinfo");
			ResultSet set = stmt.executeQuery();
			while(set.next()){
				info.setName(set.getString("storeName"));
				info.setInfo(set.getString("info"));
				info.setStarProductId(set.getInt("starProduct"));
				info.setStoreEmail(set.getString("email"));
				info.setPayPalAccount(set.getString("payPalAccount"));
				
			}
			set.close();
			stmt.close();
			this.dataSource.getConnection().close();
		}
		catch(Exception exc){
			
		}
		finally{
			
		}
		return info;
	}

	@Override
	public void updateStoreInfo(StoreInfo info) {
		String query = "update storeinfo set storeName=?, info=?, starProduct=?, email=?, payPalAccount=? where id=1";
		try{
			//String formattedQuery = String.format(query, info.getName(), info.getInfo(), info.getStarProductId()
				//	, info.getStoreEmail(), info.getPayPalAccount());
			PreparedStatement stmt = this.dataSource.getConnection().prepareStatement(query);
			stmt.setString(1, info.getName());
			stmt.setString(2,  info.getInfo());
			stmt.setInt(3, info.getStarProductId());
			stmt.setString(4, info.getStoreEmail());
			stmt.setString(5, info.getPayPalAccount());
			//String formattedQuery = String.format(query, info.getName(), info.getInfo(),info.getName());
					
			stmt.executeUpdate();
			stmt.close();
			this.dataSource.getConnection().close();
			
		}
		catch(Exception exc){
			String arg = exc.getMessage();
			
		}
		
	}

	@Override
	public InputStream getStreamBlobLogo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getStreamBlobBanner() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*@Override
	public String getStoreName() {
		// TODO Auto-generated method stub
		String storeName = "";
		try{
			//DbConnection connection = new DbConnection();
			PreparedStatement stmt = this.dataSource.getConnection().prepareStatement("select storeName from storeinfo");
			ResultSet set = stmt.executeQuery();
			while(set.next()){
				storeName = set.getString("storeName");
			}
			set.close();
			stmt.close();
			this.dataSource.getConnection().close();
		}
		catch(Exception exc){
			
		}
		finally{
			
		}
		return storeName;
	}

	@Override
	public String getStoreInfo() {
		// TODO Auto-generated method stub
		String storeInfo = "";
		try{
			//DbConnection connection = new DbConnection();
			PreparedStatement stmt = this.dataSource.getConnection().prepareStatement("select storeInfo from storeinfo");
			ResultSet set = stmt.executeQuery();
			while(set.next()){
				storeInfo = set.getString("storeInfo");
			}
			set.close();
			stmt.close();
			this.dataSource.getConnection().close();
		}
		catch(Exception exc){
			
		}
		finally{
			
		}
		return storeInfo;
	}

	@Override
	public InputStream getStreamBlobLogo() {
		// TODO Auto-generated method stub
		InputStream inputStream=null;
		try{
			//DbConnection connection = new DbConnection();
			PreparedStatement stmt = this.dataSource.getConnection().prepareStatement("select logo from storeinfo");
			ResultSet set = stmt.executeQuery();
			while(set.next()){
				Blob imageBlob = set.getBlob("logo");
				inputStream = imageBlob.getBinaryStream(1, imageBlob.length());
				
			}
			set.close();
			stmt.close();
			this.dataSource.getConnection().close();
		}
		catch(Exception exc){
			String p = exc.getMessage();
			int pe =0;
		}
		finally{
			
		}
		return inputStream;
	}

	@Override
	public InputStream getStreamBlobBanner() {
		// TODO Auto-generated method stub
		InputStream inputStream=null;
		try{
			//DbConnection connection = new DbConnection();
			PreparedStatement stmt = this.dataSource.getConnection().prepareStatement("select banner from storeinfo");
			ResultSet set = stmt.executeQuery();
			while(set.next()){
				Blob imageBlob = set.getBlob("banner");
				inputStream = imageBlob.getBinaryStream(1, imageBlob.length());
				
			}
			set.close();
			stmt.close();
			this.dataSource.getConnection().close();
		}
		catch(Exception exc){
			String p = exc.getMessage();
			int pe =0;
		}
		finally{
			
		}
		return inputStream;
	}

	@Override
	public String getStarProductCode() {
		// TODO Auto-generated method stub
		String starProduct = "";
		try{
			//DbConnection connection = new DbConnection();
			PreparedStatement stmt = this.dataSource.getConnection().prepareStatement("select starProduct from storeinfo");
			ResultSet set = stmt.executeQuery();
			while(set.next()){
				starProduct = set.getString("starProduct");
			}
			set.close();
			stmt.close();
			this.dataSource.getConnection().close();
		}
		catch(Exception exc){
			
		}
		finally{
			
		}
		return starProduct;
	}

	@Override
	public void upateStoreName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void upateStoreInfo(String info) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void upateStarProductCode(String code) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateLogo(InputStream inputStream) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBanner(InputStream inputStream) {
		// TODO Auto-generated method stub
		
	}*/

}
