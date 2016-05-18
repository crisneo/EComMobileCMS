package com.ecomCMS.DAO;

import java.io.InputStream;
import java.util.List;

import com.ecomCMS.models.*;

public interface ProductDAO {
	
	public List<Product> getAll();
	public List<Product> getByCategory(int category);
	public List<Product> getByCategoryCode(String categoryCode);
	public List<Product> getOfferedProducts();
	public Product getByCode(String code);
	public Product getById(int id);
	public String createProduct(Product product, InputStream inputStream, String fileName);
	public void updateProduct(Product product, InputStream inputStream ,String fileName);
	public boolean deleteProduct(Product product);
	public InputStream getStreamBlobImage(String productCode);
	public List<Product> searchProduct(String criteria);

}
