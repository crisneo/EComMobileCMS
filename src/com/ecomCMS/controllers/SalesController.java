package com.ecomCMS.controllers;

import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.ecomCMS.DAO.ProductSaleDAO;
import com.ecomCMS.models.ProductSale;
import com.ecomCMS.models.ShoppingCartItem;


@Controller
public class SalesController implements ServletContextAware {
	
	private ServletContext servletContext;
	@Autowired
	private ProductSaleDAO salesDAO;

	
    public void setServletContext(ServletContext servletCtx){
       this.servletContext=servletCtx;
    }
    
    @RequestMapping(value="/admin/sales")
    public ModelAndView viewSales(){
    	List<ProductSale> sales = salesDAO.getAllSales();
    	ModelAndView view = new ModelAndView("admin/sales");
    	view.addObject("sales", sales);
    	return view;
    }
    
    /*@RequestMapping(value = "/loggedUser/addSale", method = RequestMethod.POST)
    public void addSale(@ModelAttribute("command")
    @Valid ProductSale sale, BindingResult result){
    	if(!result.hasErrors()){
    		salesDAO.addSale(sale);
    		
    	}
    	
    }*/
    
   
    @RequestMapping(value="/loggedUser/commitCart")
    public String commitShoppingCart(HttpSession session){
    	List<ShoppingCartItem> items = new ArrayList<ShoppingCartItem>();
    	Map<Integer, List<Object>> table=new HashMap<Integer, List<Object>>();
    	if(session!=null){
    		if(session.getAttribute("cart") != null){
    			items = (List<ShoppingCartItem>)session.getAttribute("cart");
    		}
    	}
    	if(items.size()==0){
    		return"";
    	}
    	//String username = session.getAttribute("username").toString();
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); //get logged in username
    	for(ShoppingCartItem item : items){
    		int quantity=1;
    		double price = item.getPrice();
    		
    		if(table.containsKey(item.getProductId())){
    			quantity++;
    			price=price+item.getPrice();
    			
    		}
    		List<Object> values = new ArrayList<Object>();
			values.add(quantity);//quantity 0
			values.add(price);//price 1
			table.put(item.getProductId(), values);
    		
    		
    		
    	}
    	java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date( date.getTime() );
    	for (Map.Entry<Integer, List<Object>> entry : table.entrySet()) {
    	    Integer productId = entry.getKey();
    	    List<Object> values = entry.getValue();
    	    ProductSale sale = new ProductSale(username, productId, sqlDate, (double)values.get(1), (int)values.get(0));
    		salesDAO.addSale(sale);
    	}
    	
    	return "";
    	
    }

}
