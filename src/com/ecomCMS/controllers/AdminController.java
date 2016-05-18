package com.ecomCMS.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
//import org.springframework.security.core.*;
//import org.springframework.security.core.userdetails.*;




import com.ecomCMS.DAO.CategoryDAO;
import com.ecomCMS.DAO.ProductDAO;
import com.ecomCMS.DAO.UserDAO;
import com.ecomCMS.models.Product;
import com.ecomCMS.models.User;
import com.ecomCMS.models.UserSession;

@Controller
@SessionAttributes
public class AdminController implements ServletContextAware {
	
	@Autowired
	private UserDAO userDAO;
	
	
	
	//@Autowired
	//ServletContext context;
	
	private ServletContext servletContext;
	
    public void setServletContext(ServletContext servletCtx){
       this.servletContext=servletCtx;
    }
    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin/home";
        }
        return "redirect:/viewproducts";
    }
    
    	@RequestMapping("/admin/home")
	    public ModelAndView loadAdminHome() {
	       
	    	return new ModelAndView("admin/adminHome");
	    }
		
		@RequestMapping("/admin")
	    public ModelAndView loadIndexAdmin() {
	       
	    	return new ModelAndView("admin/adminHome");
	    }
		
		//for 403 access denied page
		@RequestMapping(value="/403")
		//@RequestMapping(value = "/403", method = RequestMethod.GET || RequestMethod.POST)
		public ModelAndView accesssDenied() {
	 
		  ModelAndView model = new ModelAndView();
	 
		  //check if user is login
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();	
			model.addObject("username", userDetail.getUsername());
		  }
	 
		  model.setViewName("common/403");
		  return model;
	 
		}
		
	
		//Spring Security see this :
		@RequestMapping(value="/login")
		//@RequestMapping(value = "/login", method = RequestMethod.GET)
		public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
	 
			ModelAndView model = new ModelAndView();
			if (error != null) {
				model.addObject("error", "nombre de usuario o password incorrectos!");
			}
	 
			if (logout != null) {
				model.addObject("msg", "desconexión exitosa");
			}
			model.setViewName("admin/Login");
	 
			return model;
	 
		}
		
		@RequestMapping(value="/admin/newUserForm")
		public ModelAndView newUserForm(){
			
			 return new ModelAndView("admin/createUser", "command", new com.ecomCMS.models.User());
		}
		
		@RequestMapping(value="/admin/editUserForm/{username}")
		public ModelAndView editUserForm(@PathVariable String username){
			//Product product = productDAO.getByCode(code);
			User user = userDAO.getByName(username);
			return new ModelAndView("admin/editUser", "command", user);
		}
		
		//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
		@RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
		public String addUser(@ModelAttribute("command")/**era user**/
	    @Valid User user, BindingResult result){
			if(result.hasErrors()) {
		    	
				return "admin/createUser";
				
				
		    }else{
		    	userDAO.createUser(user);
				//return viewUsers();
				return "redirect:/admin/users";
		    }
			
		}
		
		@RequestMapping(value = "/admin/deleteUser/{username}", method = RequestMethod.GET)
		public String deleteUser(@PathVariable String username){
			//Product product = productDAO.getByCode(code);
			User user = userDAO.getByName(username);
			if(user!=null){
				userDAO.deleteUser(user);
			}
			
			return "redirect:/admin/users";
		}
		
		@RequestMapping(value = "/admin/updateUser", method = RequestMethod.POST)
		public String updateUser(@ModelAttribute("command")
	    @Valid User user, BindingResult result){
			if(result.hasErrors()){
				return "admin/editUser";
			}
			else{
				userDAO.updateUser(user);
				return "redirect:/admin/users";
			}
			
		}
		
		@RequestMapping(value="/admin/users")
		public ModelAndView viewUsers(){
			ModelAndView mav= new ModelAndView();
			//List<Product> products = productDAO.getAll();
			List<User> users = userDAO.getAll();
			mav.setViewName("admin/users");
			mav.addObject("users", users);
			return mav;
		}
		
		
		
		

}
