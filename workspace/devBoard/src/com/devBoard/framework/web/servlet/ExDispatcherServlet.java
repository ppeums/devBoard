package com.devBoard.framework.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;

import xecure.servlet.XecureConfig;
import xecure.servlet.XecureHttpServletRequest;
import xecure.servlet.XecureHttpServletResponse;
import xecure.servlet.XecureServlet;

/**
 * @Class Name : ExDispatcherServlet.java
 * @Description : ExDispatcherServlet.java Class
 * @Modification Information
 * @
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2012. 8. 8.  송제승     최초생성
 * 
 * @author 송제승
 * @since 2012. 8. 8.
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Sinsuldong All right reserved.
 */
public class ExDispatcherServlet extends DispatcherServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7470111116448030830L;
	
	private static XecureConfig xconfig = null;
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.DispatcherServlet#doDispatch(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {        
		if(getXecureSessionId(request)==null) {
			super.doDispatch(request, response);
		} else {
			if(xconfig==null) xconfig = new XecureConfig();
			
			XecureServlet xecureServlet = new XecureServlet(xconfig, request, response);
			XecureHttpServletRequest xRequest = xecureServlet.request;
			XecureHttpServletResponse xResponse	= xecureServlet.response;
			
			super.doDispatch(xRequest, xResponse);
		}
	}
	
	/**
	 * HttpServletRequest로부터 Xecure Session ID를 반환한다.
	 * 
	 * @param request
	 * @return Xecure Session ID
	 */
	protected String getXecureSessionId(HttpServletRequest request) {
		String s = request.getParameter("q");
  
		if(s == null || s.equals("")) return null;      

		int i = s.indexOf(';');
      
		if(i == 0) return null;
		if(i < 0)  i = s.length();

		return  s.substring(0, i);      
	}
}
