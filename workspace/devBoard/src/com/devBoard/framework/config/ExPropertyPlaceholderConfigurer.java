package com.devBoard.framework.config;

import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**  
 * @Class Name : ExPropertyPlaceholderConfigurer.java
 * @Description : ExPropertyPlaceholderConfigurer Class
 * @Modification Information
 * @
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2012. 12. 11.  송제승     최초생성
 * 
 * @author 송제승
 * @since 2012. 12. 11.
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Sinsuldong All right reserved.
 */
public class ExPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer implements BeanNameAware, BeanFactoryAware {
	/**
	 * Properties 위치를 설정한다.
	 * 
	 * @param locations Properties 위치
	 */
	public void setLocations(Resource[] locations) {
		FileSystemResource[] fsr = new FileSystemResource[locations.length];
		
		for(int i=0; i<fsr.length; i++) {
		//	fsr[i] = new FileSystemResource(System.getProperty("home") + "/config/" + locations[i].getFilename());
		}
		
		super.setLocations(fsr);
	}
}
