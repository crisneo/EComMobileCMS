package com.ecomCMS.DAO;

import java.io.InputStream;

import com.ecomCMS.models.StoreInfo;

public interface SettingsDAO {
	
	
	public StoreInfo getStoreInfo();
	public void updateStoreInfo(StoreInfo info);
	public InputStream getStreamBlobLogo();
	public InputStream getStreamBlobBanner();
	
	
	

}
