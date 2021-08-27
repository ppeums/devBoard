package com.devBoard.common.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**  
 * @Class Name : AccessFilter.java
 * @Description : AccessFilter Class
 * @Modification Information  
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2012.12.11           최초생성
 * 
 * @author 송제승
 * @since 2012. 12.11
 * @version 1.0
 * @see
 * 
 *  Copyright (C) by Sinsuldong All right reserved.
 */
public class AccessFilter implements Filter {

	private final Logger accessLogger = Logger.getLogger("lwbooks.access");
	private final Logger logger = Logger.getLogger(this.getClass());
	
	/**
     * 초기화 처리
     * 
     * @param config
     *            FilterConfig 객체
     * @exception ServletException
     */
    public void init(FilterConfig config) throws ServletException {
    	// 필요시 로직 추가
    }

    /**
     * 종료 처리
     */
    public void destroy() {
    	// 필요시 로직 추가
    }

    /**
     * 필터링 처리
     * 
     * @param request
     *            ServletRequest 객체
     * @param response
     *            ServletResponse 객체
     * @param chain
     *            FilterChain 객체
     * @exception IOException
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	HttpServletRequest req = (HttpServletRequest) request;
    	// WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(req.getSession().getServletContext());
        // ExXMLConfiguration conf = (ExXMLConfiguration)wac.getBean("xmlConfiguration");
        
        boolean accessLogEnabled = true;
    	StringBuffer logStart = null;
        StringBuffer logEnd = null;
        long sTime = 0;
        String dateStart = "";
        
        try {
        	sTime = System.currentTimeMillis();
        	dateStart = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS", Locale.getDefault()).format(new Date(sTime));
        	
        	if (accessLogEnabled) {
	        	boolean headerEnabled = false;
	        	boolean parameterEnabled = false;
	            String userId = "";
	            
	            logStart = new StringBuffer();
	            logStart.append("[REFERER : ").append(req.getHeader("Referer")).append("] ")
	                    .append("[IP : ").append(req.getRemoteAddr()).append("] ")
	                    .append("[USER ID : ").append(userId).append("] ")
	                    .append("[URL : ").append(req.getServletPath()).append("] ")
	                    .append("[PARAM : ").append(req.getQueryString()).append("] ")
	                    .append("[User-Agent : ").append(req.getHeader("User-Agent")).append("] ")
	                    .append("[DATE START : ").append(dateStart).append("] ");
	
	            // Log Start
	            accessLogger.info(logStart.toString());
	            
	            // Header
	            if (headerEnabled) printHeader(req);
	            // Parameter
	            if (parameterEnabled) printParameter(req);
	            
	            chain.doFilter(request, response);
	        } else {
	            chain.doFilter(request, response);
	        }
    	} catch(Throwable t) {
    		logger.error(t.getMessage(), t);
    		throw new ServletException(t);
    	} finally {
    		if (accessLogEnabled) {
    			logEnd = new StringBuffer();
	            logEnd.append(logStart.toString());
    			
	            // Log End
	            long executionTime = System.currentTimeMillis() - sTime;
	            
	            accessLogger.info(logEnd.append("[EXECUTION TIME : ").append(executionTime).append("]").toString());
    		}
    	}
    }
    
    private void printHeader(HttpServletRequest req) {
    	@SuppressWarnings("rawtypes")
		Enumeration headerNames = req.getHeaderNames();
        String headerName = "";

        accessLogger.info("==== Header Name : Header ================");

        while (headerNames.hasMoreElements()) {
            headerName = (String) headerNames.nextElement();

            accessLogger.info("---- " + headerName + " : " + req.getHeader(headerName));
        }
    }
    
    private void printParameter(HttpServletRequest req) {
    	@SuppressWarnings("rawtypes")
		Enumeration parameterNames = req.getParameterNames();
        String parameterName = "";

        accessLogger.info("==== Parameter Name : Parameter ================");

        while (parameterNames.hasMoreElements()) {
            parameterName = (String) parameterNames.nextElement();

            accessLogger.info("---- " + parameterName + " : " + req.getParameter(parameterName));
        }
    }
}
