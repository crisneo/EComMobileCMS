package com.ecomCMS.controllers;

import org.springframework.mobile.device.Device;

public class BaseController {
	
	public String getSpecificDeviceURL(Device device, String path){
		if(device.isMobile()){
			return "mobile/"+path;
			
		}
		else if(device.isTablet()){
			return "tablet/"+path;
		}
		else{
			return path;
		}
	}

}
