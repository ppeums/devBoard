package com.devBoard.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.devBoard.common.security.UserSession;
import com.devBoard.framework.config.ExXMLConfiguration;

import org.apache.log4j.Logger;

/**  
 * @Class Name : AuthorityFilter.java
 * @Description : AuthorityFilter Class
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
public class AuthorityFilter implements Filter {

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
     * @param request ServletRequest 객체
     * @param response ServletResponse 객체
     * @param chain FilterChain 객체
     * @exception IOException
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	HttpServletRequest req = (HttpServletRequest) request;
    	HttpServletResponse res = (HttpServletResponse) response;
    	HttpSession session = req.getSession();
        
        try {
        	// 세션 체크
        	if(session.getAttribute("userId")==null && !isSessionExcludeUrl(req.getServletPath())) {
        		// 세션값이 없을 경우 이동 페이지를 설정한다.(ex : 로그인 Page)
        		res.sendRedirect("/login/retrieveLogin.xx");
        		return;
        	}

    		// JSESSIONID 복제 해킹 방어
    		if(isSessionCopied(req, res)) {
    			return;
    		}
    		
    		chain.doFilter(request, response);
    	} catch(Throwable t) {
    		logger.error(t.getMessage(), t);
    		throw new ServletException(t);
    	}
    }
    
    private boolean isSessionExcludeUrl(String url) {
    	boolean isExcludeUrl = false;
    	
    	String excludeUrl = ExXMLConfiguration.getInstance().getString("filter.authority.session.exclude-url");
    	
    	if(excludeUrl.indexOf(url)!=-1) isExcludeUrl = true;
    	
    	return isExcludeUrl;
    }
    
    private boolean isSessionCopied(HttpServletRequest req, HttpServletResponse res) throws IOException {
    	boolean result = false;
    	HttpSession session = req.getSession();
    	
    	String sessionUserIp = (String)session.getAttribute("userIp");
		String requestUserIp = req.getRemoteAddr();
		
		if(sessionUserIp==null) {
			// 최초 접속 시 Session에 Request User IP 설정
			session.setAttribute("userIp", requestUserIp);
		} else {
			// Session User IP와 Request User IP가 일치하지 않으면 해킹 시도
			if(!requestUserIp.equals(sessionUserIp)) {
				logger.info("JSESSIONID 복제를 통한 해킹 시도가 발생하였습니다.");
				
				res.sendRedirect("/common/errorUserSessionRequired.xx?isSessionCopied=true");
        		
				result = true;
			}
		}
		
		return result;
    }
}
