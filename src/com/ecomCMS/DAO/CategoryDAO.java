package com.ecomCMS.DAO;

import java.util.List;

import com.ecomCMS.models.Category;

public interface CategoryDAO {
	
	public List<Category> getAll();
	public Category getByCode(String code);
	public Category getById(int id);
	public String createCategory(Category category);
	public void updateCategory(Category category);
	public boolean deleteCategory(Category category);
}
