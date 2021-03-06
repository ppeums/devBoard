package com.devBoard.framework.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;

/**  
 * @Class Name : ExXMLConfiguration.java
 * @Description : ExXMLConfiguration Class
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
public class ExXMLConfiguration extends XMLConfiguration {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3523381408933521341L;
	
	private static final Logger logger = Logger.getLogger(ExXMLConfiguration.class);
	
	private static ExXMLConfiguration xmlConfiguration = null;
	
	private ExXMLConfiguration() {
		// Do nothing
	}
	
	/**
	 * XMLConfiguration Singleton 객체를 생성하여 리턴한다.
	 * 
	 * @return XMLConfiguration Singleton 객체
	 */
	public static ExXMLConfiguration getInstance() {
		if(xmlConfiguration==null) {
			xmlConfiguration = getInstance(System.getProperty("waspath"), "lw");
		}
		
		return xmlConfiguration;
    }
	
	/**
	 * XMLConfiguration Singleton 객체를 생성하여 리턴한다.
	 * 
	 * @param home home directory
	 * @param fileName Configuration file name
	 * @return XMLConfiguration Singleton 객체
	 */
	public static ExXMLConfiguration getInstance(String home, String fileName) {
		if(xmlConfiguration==null) {
    		xmlConfiguration = new ExXMLConfiguration();
    		
			String ps = getPathSeparator(home);
	        String configFilePath = home + ps + "WEB-INF" + ps + "config" + ps + fileName + ".xml";
	        
	        try {
	        	logger.debug("Load XML Configuration");
	        	logger.debug(configFilePath);
	        	
	        	xmlConfiguration.load(configFilePath);
	        } catch(ConfigurationException ce) {
	        	ce.printStackTrace();
	        }
    	}
    	
    	return xmlConfiguration;
	}
    
    private static String getPathSeparator(String path) {
		if(path.indexOf("/")!=-1) {
			return "/";
		} else {
			return "\\";
		}
	}
}