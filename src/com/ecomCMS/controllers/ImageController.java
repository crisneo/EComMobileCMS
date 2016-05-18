package com.ecomCMS.controllers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ecomCMS.DAO.CategoryDAO;
import com.ecomCMS.DAO.ProductDAO;
import com.ecomCMS.DAO.SettingsDAO;
import com.ecomCMS.models.Product;

@Controller
public class ImageController {
	
	@Autowired
	private ProductDAO productDAO;	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private SettingsDAO settingsDAO;
	
	 /*@RequestMapping(value="/imageProcuct/{prodCode}", method=RequestMethod.GET)
	 public void showImageFromProduct(@PathVariable String prodCode, HttpServletResponse response){
			InputStream stream = productDAO.getStreamBlobImage(prodCode);	
			 try {
				 response.setContentType("image/jpeg");
				  //byte[] buffer = tweetService.getTweetByID(tweetID).getUserImage();
				  //InputStream in1 = new ByteArrayInputStream(buffer);
				  IOUtils.copy(stream, response.getOutputStream());    
		         
		        } catch (Exception e) {
		         e.printStackTrace();
		   }
				
	 }*/
	 
	 @RequestMapping(value="/imageProcuct/showLogo", method=RequestMethod.GET)
	 public void showCompanyLogo(HttpServletResponse response){
		 InputStream stream = settingsDAO.getStreamBlobLogo();	
		 try {
			 	response.setContentType("image/jpeg");
			  //byte[] buffer = tweetService.getTweetByID(tweetID).getUserImage();
			  //InputStream in1 = new ByteArrayInputStream(buffer);
			  IOUtils.copy(stream, response.getOutputStream());    
	         
	        } 
		 catch (Exception e) {
	        	e.printStackTrace();
	   }
		 
	 }
	 
	 @RequestMapping(value="/imageProcuct/showBanner", method=RequestMethod.GET)
	 public void showCompanyBanner(HttpServletResponse response){
		 InputStream stream = settingsDAO.getStreamBlobBanner();
		 
		 try {
			 	response.setContentType("image/jpeg");
			  //byte[] buffer = tweetService.getTweetByID(tweetID).getUserImage();
			  //InputStream in1 = new ByteArrayInputStream(buffer);
			  IOUtils.copy(stream, response.getOutputStream());    
	         
	        } 
		 catch (Exception e) {
	        	e.printStackTrace();
	   }
		 
	 }
	 
	 @RequestMapping(value="/imageProcuct/showStarProductImage", method=RequestMethod.GET)
	 public void showStarProductImage(HttpServletResponse response){
		 
		 String code = productDAO.getById(settingsDAO.getStoreInfo().getStarProductId()).getCode();
		 InputStream stream = productDAO.getStreamBlobImage(code);	
		 //InputStream stream = settingsDAO.getStreamBlobBanner();	
		 try {
			 	response.setContentType("image/jpeg");
			  //byte[] buffer = tweetService.getTweetByID(tweetID).getUserImage();
			  //InputStream in1 = new ByteArrayInputStream(buffer);
			  IOUtils.copy(stream, response.getOutputStream());    
	         
	        } 
		 catch (Exception e) {
	        	e.printStackTrace();
	   }
		 
	 }
	 
	 
	 /*@RequestMapping(value="/imageProductFile/{prodCode}", method=RequestMethod.GET)
	 public void showImageFromProduct(@PathVariable String prodCode, HttpServletResponse response){
		 try{
			 File file = new File("F:/rvi.jpg");
			 FileInputStream fis=new FileInputStream(file);
			 ByteArrayOutputStream bos=new ByteArrayOutputStream();
			 int b;
			 byte[] buffer = new byte[1024];
			 while((b=fis.read(buffer))!=-1){
			    bos.write(buffer,0,b);
			 }
			 byte[] fileBytes=bos.toByteArray();
			 fis.close();
			 bos.close();


			 byte[] encoded=Base64.encode(fileBytes);
			 String encodedString = new String(encoded);

			 ModelMap map = new ModelMap();
			 map.put("image", encodedString); 
		 }
		 catch(Exception exc){
			 
		 }
		
		 
	 }*/
	 
	 @RequestMapping(value="/imageProcuct/{prodCode}", method=RequestMethod.GET)
	 public ResponseEntity<byte[]> getImage(@PathVariable String prodCode, HttpServletRequest request) {
		 
		 /*String path = request.getContextPath();
		 String real = request.getSession().getServletContext().getRealPath(path);*/
		 Product product = productDAO.getByCode(prodCode);
		 Properties props = System.getProperties();  
		 String jbossServerHomeUrl = props.getProperty( "jboss.home.dir");

		 //File directory = new File(jbossServerHomeUrl+"/EcomCMSData/images/");
		 File directory = new File(jbossServerHomeUrl+"/EcomCMSData/images/");
		 //String url = directory.getPath() + product.getImage();
		 String url = product.getImage();
		 byte[] fileBytes=null;
		 try{
			 //File file = new File("F:/rvi.jpg");
			 //F:\\test111\\" + code"
			 //File file = new File("F:\\test111\\"+prodCode+".jpg");
			 File file = new File(url);
			 FileInputStream fis=new FileInputStream(file);
			 ByteArrayOutputStream bos=new ByteArrayOutputStream();
			 int b;
			 byte[] buffer = new byte[1024];
			 while((b=fis.read(buffer))!=-1){
			    bos.write(buffer,0,b);
			 }
			 fileBytes=bos.toByteArray();
			 fis.close();
			 bos.close();
		 }
		 catch(Exception exc){
			 
		 }
		
	    byte[] content = fileBytes;
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_PNG);
	    return new ResponseEntity<byte[]>(content, headers, HttpStatus.OK);
	 }
	
	

}
