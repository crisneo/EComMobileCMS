package com.ecomCMS.controllers;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.ecomCMS.DAO.ProductDAO;
import com.ecomCMS.DAO.SettingsDAO;
import com.ecomCMS.models.Category;
import com.ecomCMS.models.StoreInfo;

@Controller
public class SettingsController implements ServletContextAware {
	
	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	SettingsDAO settingsDAO;
	
	private ServletContext servletContext;
	
	public void setServletContext(ServletContext servletCtx){
	    this.servletContext=servletCtx;
	}
	
	@RequestMapping(value="/admin/settings")
	public ModelAndView viewSettingsForm(){
		
		ModelAndView mav= new ModelAndView("admin/generalConfig", "command", settingsDAO.getStoreInfo());
		mav.addObject("products", productDAO.getAll());
		
		return mav;
	}
	
	@RequestMapping(value = "/admin/updateSettings", method = RequestMethod.POST)
	public String updateSettings(@ModelAttribute("info")
	@Valid StoreInfo info, BindingResult result){
		//categoryDAO.updateCategory(category);
		settingsDAO.updateStoreInfo(info);
		return "redirect:/admin/settings";
	}
	

}
