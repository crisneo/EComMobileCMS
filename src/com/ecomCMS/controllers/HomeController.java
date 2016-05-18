package com.ecomCMS.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ecomCMS.DAO.CategoryDAO;
import com.ecomCMS.DAO.ProductDAO;
import com.ecomCMS.DAO.SettingsDAO;
import com.ecomCMS.models.Category;
import com.ecomCMS.models.Product;
import com.ecomCMS.models.ShoppingCartItem;
import com.ecomCMS.models.StoreInfo;
import com.ecomCMS.models.UserSession;
 
@Controller
public class HomeController extends BaseController {
 
	@Autowired
	private ProductDAO productDAO;	
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private SettingsDAO settingsDAO;
	
    
    @RequestMapping("/")
    public String loadIndexEndUser(Device device) {
        
    	
    	List<Category> categories = categoryDAO.getAll();
    	
    	if(categories.size()>0){
    		return "redirect:/productsByCategory/"+categories.get(0).getCode();
    	}
    	
    	else{
    		return "redirect:/viewproducts";
    	}
    }
    
    @RequestMapping("/home")
    public ModelAndView loadHome(Device device, Model model) {

    	ModelAndView view = new ModelAndView(this.getSpecificDeviceURL(device, "home"));
    	StoreInfo info = settingsDAO.getStoreInfo();
    	List<Product> offered = productDAO.getOfferedProducts();
    	Product product = productDAO.getById(info.getStarProductId());
    	loadStarProduct(view);
    	view.addObject("offeredProducts", offered);
    	view.addObject("info", info);
    	//return new ModelAndView("index");
    	return view;
    }
    
    @RequestMapping("/contact")
    public ModelAndView loadContactForm(Device device, Model model) {

    	ModelAndView view = new ModelAndView(this.getSpecificDeviceURL(device, "contact"));
    	StoreInfo info = settingsDAO.getStoreInfo();
    	List<Product> offered = productDAO.getOfferedProducts();
    	loadStarProduct(view);
    	//view.addObject("starProduct", product);
    	view.addObject("offeredProducts", offered);
    	view.addObject("info", info);
    	//return new ModelAndView("index");
    	return view;
    }
    
    
    
    @RequestMapping("/viewproducts")
    public ModelAndView viewProducts(SitePreference sitePreference, Device device, Model model){
    	List<Product> products = productDAO.getAll();
    	List<Category> categories = categoryDAO.getAll();
    	List<Product> offered = productDAO.getOfferedProducts();
    	ModelAndView mav = new ModelAndView(this.getSpecificDeviceURL(device, "products"));
    	StoreInfo info = settingsDAO.getStoreInfo();
    	/*if(products.size()>0){
    		Product product = productDAO.getById(info.getStarProductId());
        	mav.addObject("starProduct", product);
    	}else{
    		mav.addObject("starProduct", new Product());
    	}*/
    	loadStarProduct(mav);
    	mav.addObject("products", products);
    	//for mobile view we require this
    	if(device.isMobile()){
    		for(Category cat : categories){
    			
    			cat.setProducts(productDAO.getByCategory(cat.getId()));
    		}
    		
    	}
    	mav.addObject("categories", categories);
    	mav.addObject("offeredProducts", offered);
    	
    	return mav;
    	
    }
    
    @RequestMapping("/productsByCategory/{catCode}")
    public ModelAndView viewProducts(SitePreference sitePreference, Device device, Model model, @PathVariable String catCode){
    	Category category = categoryDAO.getByCode(catCode);
    	//List<Product> products = productDAO.getByCategory(catCode);
    	List<Product> products = productDAO.getByCategoryCode(catCode);
    	List<Category> categories = categoryDAO.getAll();
    	List<Product> offered = productDAO.getOfferedProducts();
    	ModelAndView mav = new ModelAndView(this.getSpecificDeviceURL(device, "products"));
    	StoreInfo info = settingsDAO.getStoreInfo();
    	/*if(products.size()>0){
    		Product product = productDAO.getById(info.getStarProductId());
        	mav.addObject("starProduct", product);
    	}*/
    	loadStarProduct(mav);
    	
    	mav.addObject("products", products);
    	//for mobile view we require this
    	if(device.isMobile()){
    		for(Category cat : categories){
    			//cat.setProducts(productDAO.getByCategory(cat.getCode()));
    			cat.setProducts(productDAO.getByCategoryCode(cat.getCode()));
    		}
    		
    	}
    	mav.addObject("categories", categories);
    	mav.addObject("offeredProducts", offered);
    	mav.addObject("category", category);
    	return mav;
    	
    }
    
    /**** este era el oficial para mostrar las imagenes*****////
   /* @RequestMapping(value="/imageProcuct/{prodCode}", method=RequestMethod.GET)
	public void showImageFromProduct(@PathVariable String prodCode, HttpServletResponse response){
		if(prodCode.length()>0){
			InputStream stream = productDAO.getStreamBlobImage(prodCode);	
			 
			try {
				if(stream!=null){
					response.setContentType("image/jpeg");
					  //byte[] buffer = tweetService.getTweetByID(tweetID).getUserImage();
					  //InputStream in1 = new ByteArrayInputStream(buffer);
					  IOUtils.copy(stream, response.getOutputStream());   
				} 

		        } catch (Exception e) {
		        	e.printStackTrace();
		      }
		}
    	
			
	}*/
    
    @RequestMapping(value="/springImage/{prodCode}", method=RequestMethod.GET)
	protected HttpEntity<byte[]> processDefinitionDetailImage(@PathVariable String code, HttpServletRequest request) {
		//ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).latestVersion().singleResult();
		
		//InputStream image = new ProcessDefinitionImageStreamResourceBuilder().buildStreamResource(processDefinition, repositoryService);
		try{
			InputStream image = productDAO.getStreamBlobImage(code);	
			byte[] imageBytes = IOUtils.toByteArray(image);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);
			headers.setContentLength(imageBytes.length);

			
			return new HttpEntity<byte[]>(imageBytes, headers);
		}
		catch(Exception exc){
			return null;
		}
		
	}
    
    @RequestMapping("/categories")
    public ModelAndView viewCategories(SitePreference sitePreference, Device device, Model model){
    	List<Category> categories = categoryDAO.getAll();
    	ModelAndView mav = new ModelAndView(this.getSpecificDeviceURL(device, "categories"));
    	mav.addObject("products", categories);
    	return mav;
    	
    }
    
    @RequestMapping("/Product/{code}")
    public ModelAndView viewProduct(SitePreference sitePreference, Device device, Model model, @PathVariable String code){
    	Product product = productDAO.getByCode(code);
    	List<Category> categories = categoryDAO.getAll();
    	List<Product> offered = productDAO.getOfferedProducts();
    	ModelAndView mav = new ModelAndView(this.getSpecificDeviceURL(device, "product"));
    	mav.addObject("categories", categories);
    	mav.addObject("offeredProducts", offered);
    	mav.addObject("product", product);
    	//List<Product> products = productDAO.getByCategory(categoryDAO.getById(product.getCategoryId()).getId());
    	//Category catp = categoryDAO.getById(product.getCategoryId());
    	List<Product> products = productDAO.getByCategory(product.getCategoryId());
    	StoreInfo info = settingsDAO.getStoreInfo();
    	
    	loadStarProduct(mav);
    	return mav;
    	
    }
    
    @RequestMapping("/Category/{code}")
    public ModelAndView viewCategory(SitePreference sitePreference, Device device, Model model, @PathVariable String code){
    	Category category = categoryDAO.getByCode(code);
    	ModelAndView mav = new ModelAndView(this.getSpecificDeviceURL(device, "category"));
    	mav.addObject("category", category);
    	return mav;
    	
    }
    
    @RequestMapping("/loggedUser/addToCart/{prodCode}")
    public String addToCart(HttpSession session, @PathVariable String prodCode){
    	List<ShoppingCartItem> items = new ArrayList<ShoppingCartItem>();
    	if(session!=null){
    		if(session.getAttribute("cart") != null){
    			items = (List<ShoppingCartItem>)session.getAttribute("cart");
    		}
    		
    		
    		Product product = productDAO.getByCode(prodCode);
    		ShoppingCartItem item = new ShoppingCartItem(product.getCode(), product.getName(), product.getPrice(), product.getId());
    		items.add(item);
    		session.setAttribute("cart", items);
    		session.setAttribute("cartItemsCount", items.size());
    		session.setAttribute("totalPrice",getTotalItemPrice(items) );
    	}
    	return "redirect:/viewproducts";
    }
    
    @RequestMapping("/loggedUser/viewCart")
    public ModelAndView viewCart(HttpSession session, Device device){
    	List<ShoppingCartItem> items = new ArrayList<ShoppingCartItem>();
    	if(session!=null){
    		if(session.getAttribute("cart") != null){
    			items = (List<ShoppingCartItem>)session.getAttribute("cart");
    		}
    	}
    	ModelAndView mav = new ModelAndView(this.getSpecificDeviceURL(device, "viewCart"));
    	mav.addObject("payPalAccount",settingsDAO.getStoreInfo().getPayPalAccount());
    	mav.addObject("productsInCart", items);
    	
    	loadStarProduct(mav);
    	return mav;
    }
    
    @RequestMapping("/loggedUser/emptyCart")
    public String emptyCart(HttpSession session, Device device){
    	List<ShoppingCartItem> items = new ArrayList<ShoppingCartItem>();
    	if(session!=null){
    		session.setAttribute("cart", items);
    	}
    	//ModelAndView mav = new ModelAndView(this.getSpecificDeviceURL(device, "viewCart"));
    	//mav.addObject("productsInCart", items);
    	session.setAttribute("cartItemsCount", 0);
		session.setAttribute("totalPrice",0 );
    	return  "redirect:/viewproducts";
    }
    
    private double getTotalItemPrice(List<ShoppingCartItem> items){
    	double total=0;
    	for(ShoppingCartItem item : items){
    		total+=item.getPrice();
    	}
    	return total;
    }
    
    private void loadStarProduct(ModelAndView mav)
    {
    	try{
    		Product product = productDAO.getById(settingsDAO.getStoreInfo().getStarProductId());
    		if(product!=null){
    			mav.addObject("starProduct",product );
    		}
    		else{
    			if(productDAO.getAll().size()>0)
    			{
    				mav.addObject("starProduct",productDAO.getAll().get(0));
    			}
    			else{
    				mav.addObject("starProduct",new Product());
    			}
    			
    		}
        	
    	}
    	catch(Exception exc){
    		
    	}
    	
    }
    
    @RequestMapping("/searchProduct")
    public ModelAndView searchProduct(SitePreference sitePreference, Device device){
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName(this.getSpecificDeviceURL(device, "search"));
    	List<Category> categories = categoryDAO.getAll();
    	List<Product> offered = productDAO.getOfferedProducts();
    	mav.addObject("categories", categories);
    	mav.addObject("offeredProducts", offered);
    	loadStarProduct(mav);
    	return mav;
    }
    
    @RequestMapping("/searchProductResult/{criteria}")
    //@RequestMapping(value = "/searchProductResult/{criteria}", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public  List<Product> showSearchResults(@PathVariable String criteria, Device device){
    	List<Product> products = new ArrayList<Product>();
    	if(criteria.length()>0){
    		products = productDAO.searchProduct(buildSQLCriteria(criteria));
    	}
    	
    
    	return products;
    
    }
    
    private String buildSQLCriteria(String criteria){
    	String[] params = criteria.split("&");
    	StringBuffer buffer=new StringBuffer();
    	for(String par:params){
    		String[] pair = par.split("-");
    		buffer.append(pair[0]+" like '%"+pair[1]+"%' or ");
    	}
    	String result=buffer.toString();
    	return result.endsWith(" or ")?result.substring(0, result.length() - 4)+";":result+";";
    }
    
    
    
    
    
   
}
