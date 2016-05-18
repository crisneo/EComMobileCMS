package com.ecomCMS.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;

import com.ecomCMS.DAO.CategoryDAO;
import com.ecomCMS.DAO.ProductDAO;
import com.ecomCMS.models.Category;
import com.ecomCMS.models.Product;

@Controller
public class CategoryController implements ServletContextAware {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	//@Autowired
	//ServletContext context;
	
	private ServletContext servletContext;
	
	public void setServletContext(ServletContext servletCtx){
	    this.servletContext=servletCtx;
	}
	
	@RequestMapping(value="/admin/categories")
	public ModelAndView viewCategories(){
		ModelAndView mav= new ModelAndView();
		List<Category> categories = categoryDAO.getAll();
		mav.setViewName("admin/categories");
		mav.addObject("categories", categories);
		return mav;
	}
	
	@RequestMapping(value="/admin/newCategoryForm")
	public ModelAndView newCategoryForm(){
		
		 return new ModelAndView("admin/createCategory", "command", new Category());
	}
	
	@RequestMapping(value="/admin/editCategoryForm/{id}")
	public ModelAndView editCategoryForm(@PathVariable int id){
		
		//Category category = categoryDAO.getByCode(code);
		Category category = categoryDAO.getById(id);
		return new ModelAndView("admin/editCategory", "command", category);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "/admin/addCategory", method = RequestMethod.POST)
	public String addCategory(@ModelAttribute("command")
	@Valid Category category, BindingResult result/*, @RequestParam("imageUrl") MultipartFile file*/){
		//productDAO.createProduct(product);
		if(result.hasErrors()){
			return "admin/createCategory";
		}
		else{
			categoryDAO.createCategory(category);
			//saveImageFile(file, product.getCode());
			return "redirect:/admin/categories";
		}
		
	}
	
	@RequestMapping(value = "/admin/deleteCategory/{id}", method = RequestMethod.GET)
	public ModelAndView deleteCategory(@PathVariable int id){
		//Product product = productDAO.getByCode(code);
		//Category category = categoryDAO.getByCode(code);
		
		Category category = categoryDAO.getById(id);

		if(category!=null){
			if(productDAO.getByCategory(category.getId()).size() > 0){
				//return "redirect:/admin/categories";
				ModelAndView mav = viewCategories();
				mav.addObject("errorMessage", "la categoria no se puede eliminar porque hay productos existentes");
				return mav;
			}
			categoryDAO.deleteCategory(category);
		}
		
		
		//return "redirect:/admin/categories";
		return viewCategories();
	}
	
	@RequestMapping(value = "/admin/updateCategory", method = RequestMethod.POST)
	public String updateCategory(@ModelAttribute("command")
	@Valid Category category, BindingResult result){
		if(result.hasErrors()){
			return "admin/editCategory";
		}
		else{
			categoryDAO.updateCategory(category);
			return "redirect:/admin/categories";
		}
		
	}
	
	

}
