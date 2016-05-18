package com.ecomCMS.DAO;

import java.util.Date;
import java.util.List;

import com.ecomCMS.models.ProductSale;

public interface ProductSaleDAO {
	
	List<ProductSale> getAllSales();
	ProductSale getById(int id);
	ProductSale getByDate(Date date);
	List<ProductSale> getBetweenDates(Date dateMin, Date dateMax);
	void addSale(ProductSale sale);
	void editSale(ProductSale sale);
	void deleteSale(int id);

}
