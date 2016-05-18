package com.ecomCMS.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;







import com.ecomCMS.DAO.CategoryDAO;
import com.ecomCMS.DAO.ProductDAO;
import com.ecomCMS.DAO.SettingsDAO;
import com.ecomCMS.models.Product;
import com.mysql.jdbc.Blob;

@Controller
public class ProductController implements ServletContextAware {

	@Autowired
	private ProductDAO productDAO;	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private SettingsDAO settingsDAO;
	
	//@Autowired
	//ServletContext context;
	
	private ServletContext servletContext;
	
    public void setServletContext(ServletContext servletCtx){
       this.servletContext=servletCtx;
    }
	@RequestMapping(value="/admin/newProductForm")
	public ModelAndView newProductForm(){
		
		 ModelAndView mav = new ModelAndView("admin/createProduct", "command", new Product());
		 mav.addObject("categories", categoryDAO.getAll());
		 return mav;
	}
	
	@RequestMapping(value="/admin/editProductForm/{id}")
	public ModelAndView editProductForm(@PathVariable int id){
		//Product product = productDAO.getByCode(code);
		Product product = productDAO.getById(id);
		ModelAndView mav = new ModelAndView("admin/editProduct", "command", product);
		 mav.addObject("categories", categoryDAO.getAll());
		return mav;
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "/admin/addProduct", method = RequestMethod.POST)
	public ModelAndView addProduct(@ModelAttribute("command")
    @Valid Product product, BindingResult result, @RequestParam("imageFileUpload") MultipartFile file, HttpServletRequest request){
		String fileUrl = "";
		//String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
		try{

			List<String> errors = validate(product, file); 
			if(errors.size() > 0){
				ModelAndView mav = newProductForm();
				mav.addObject("errorMessage", convertToMessage(errors));
				return mav;
			}

			else{
				productDAO.createProduct(product,file.getInputStream(), file.getOriginalFilename());
			}
			
			//saveImageFile(file, product.getCode());
		}
		catch(Exception exc){
			
		}
		
		//return "redirect:/admin/products";
		return viewProducts();
	}
	
	@RequestMapping(value = "/admin/deleteProduct/{id}", method = RequestMethod.GET)
	public ModelAndView deleteProduct(@PathVariable int id){
		//Product product = productDAO.getByCode(code);
		Product product = productDAO.getById(id);
		if(product!=null){
			if(id == settingsDAO.getStoreInfo().getStarProductId()){
				ModelAndView mav = viewProducts();
				mav.addObject("errorMessage","el producto principal no se puede eliminar");
				return mav;
			}
			
			
		}
		productDAO.deleteProduct(product);
		return viewProducts();
		//return "redirect:/admin/products";
	}
	
	@RequestMapping(value = "/admin/updateProduct", method = RequestMethod.POST)
	public ModelAndView updateProduct(@ModelAttribute("product")
    @Valid Product product, BindingResult result, @RequestParam("image") MultipartFile file, HttpServletRequest request){
		try{
			InputStream stream = null;
			List<String> errors = validateUpdate(product, file);
			if(errors.size()>0){
				ModelAndView mav = this.editProductForm(product.getId());
				mav.addObject("errorMessage", convertToMessage(errors));
				return mav;
			}
			else{
				if(file!=null && file.getSize()>0){
					stream = file.getInputStream();
				}
				productDAO.updateProduct(product, stream, file.getOriginalFilename());
			}
			
			
		}
		catch(Exception exc){
			
		}
		
		//return "redirect:/admin/products";
		return viewProducts();
	}
	
	@RequestMapping(value="/admin/products")
	public ModelAndView viewProducts(){
		ModelAndView mav= new ModelAndView();
		List<Product> products = productDAO.getAll();
		mav.setViewName("admin/products");
		for(Product p : products){
			p.setCategoryCode(categoryDAO.getById(p.getCategoryId()).getCode());
		}
		mav.addObject("products", products);
		return mav;
	}
	

	
	private String saveImageFile(MultipartFile file, String code){
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				
				// Creating the directory to store file
				//String rootPath = System.getProperty("catalina.home");
				//HttpServletRequest.getSession().getServletContext();
				String rootPath = servletContext.getRealPath("/");
				File dir = new File(rootPath+"resources/uploadedImages");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				String fName = dir.getAbsolutePath()
						+ File.separator + code+".jpg";
				File serverFile = new File(fName);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				
					return fName;
				
			} catch (Exception e) {
				return "";
			}
		} else {
			return "";
		}
	}
	
	public String saveImage(HttpServletRequest request, MultipartFile file) throws IllegalStateException, IOException{

	    //List<MultipartFile> files = uploadForm.getFiles();
	    List<String> fileUrl = new ArrayList<String>();;
	    String fileName2 = null;
	    fileName2 = request.getSession().getServletContext().getRealPath("/");


	    String saveDirectory = fileName2+"images\\";

	    List<String> fileNames = new ArrayList<String>();

	    //System.out.println("user directory : "+System.getProperty("user.dir"));
	    System.out.println("applied directory : " + saveDirectory);
	    if(null != file  ) {
	         

	            String fileName = file.getOriginalFilename();
	            System.out.println("applied directory : " + saveDirectory+fileName);
	            if(!"".equalsIgnoreCase(fileName)){
	                   //Handle file content - multipartFile.getInputStream()
	                   fileUrl.add(new String(saveDirectory + fileName));

	                   file.transferTo(new File(saveDirectory + fileName));   //Here I Added
	                   fileNames.add(fileName);
	            }
	            //fileNames.add(fileName);
	            //Handle file content - multipartFile.getInputStream()
	            //multipartFile.transferTo(new File(saveDirectory + multipartFile.getOriginalFilename()));   //Here I Added
	        
	    }

	    //map.addAttribute("files", fileNames);
	    //map.addAttribute("imageurl",fileUrl);
	    //return "file_upload_success";
	         return fileUrl.get(0);
	}
	
	private List<String> validate(Product product, MultipartFile file){
		List<String> messages = new ArrayList<String>();
		if(product.getCode().length()<4){
			messages.add("la longitud del codigo debe ser 4 caracteres como minimo");
		}
		if(product.getCategoryId()<=0){
			messages.add("el producto debe pertenecer a una categoria");
		}
		
		if(product.getPrice()<=0){
			messages.add("el precio del producto debe ser mayor a 0");
		}
		
		if(product.getAvailableUnits()<=0){
			messages.add("debe existir unidades disponibles");
		}
		
		String fileExtension="";
		if(file!=null){
			fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
		}
		
		if(!fileExtension.equalsIgnoreCase("png") 
				&& !fileExtension.equalsIgnoreCase("jpg")
				&& !fileExtension.equalsIgnoreCase("gif") && !fileExtension.equalsIgnoreCase("jpeg")){
			messages.add("debe seleccionar una imagen valida (jpg, png o gif)");
		}
		return messages;
	}
	
	private List<String> validateUpdate(Product product, MultipartFile file){
		List<String> messages = new ArrayList<String>();
		if(product.getCode().length()<4){
			messages.add("la longitud del codigo debe ser 4 caracteres como minimo");
		}
		if(product.getCategoryId()<=0){
			messages.add("el producto debe pertenecer a una categoria");
		}
		
		if(product.getPrice()<=0){
			messages.add("el precio del producto debe ser mayor a 0");
		}
		
		if(product.getAvailableUnits()<=0){
			messages.add("debe existir unidades disponibles");
		}
		
		if(file !=null && file.getSize()>0){
			String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
			if(!fileExtension.equalsIgnoreCase("png") 
					&& !fileExtension.equalsIgnoreCase("jpg")
					&& !fileExtension.equalsIgnoreCase("gif") && !fileExtension.equalsIgnoreCase("jpeg")){
				messages.add("debe seleccionar una imagen valida (jpg, png o gif)");
			}
		}
		return messages;
	}
	
	private String convertToMessage(List<String> strings){
		StringBuffer buffer = new StringBuffer();
		for(String str: strings){
			buffer.append(str+"\n");
		}
		return buffer.toString();
	}
	
	
}
