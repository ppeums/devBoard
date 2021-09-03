package com.devBoard.common.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.devBoard.common.vo.MenuVO;

/**
 * @Class Name : UserSession.java
 * @Description : 세션정보를 처리하는 Class
 * @Modification Information
 * @
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2012. 12.11.  송제승     최초생성
 * 
 * @author 송제승
 * @since 2012. 12. 11.
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Sinsuldong All right reserved.
 */
public class UserSession {
	public static String getUserId(HttpServletRequest req) {
		return (String)req.getSession().getAttribute("userId");
	}

	public static void setUserId(HttpServletRequest req, String userId) {
		req.getSession().setAttribute("userId", userId);
	}
	
	public static String getUserPw(HttpServletRequest req) {
		return (String)req.getSession().getAttribute("userPw");
	}

	public static void setUserPw(HttpServletRequest req, String userPw) {
		req.getSession().setAttribute("userPw", userPw);
	}
	
	public static String getUserName(HttpServletRequest req) {
		return (String)req.getSession().getAttribute("userName");
	}

	public static void setUserName(HttpServletRequest req, String userName) {
		req.getSession().setAttribute("userName", userName);
	}
	
	public static String getUserMenu(HttpServletRequest req) {
		return (String)req.getSession().getAttribute("userMenu");
	}

	public static void setUserMenu(HttpServletRequest req, String userMenu) {
		req.getSession().setAttribute("userMenu", userMenu);
	}
	
	public static List<MenuVO> getUserMenuList(HttpServletRequest req) {
		return (List<MenuVO>)req.getSession().getAttribute("userMenuList");
	}

	public static void setUserMenuList(HttpServletRequest req, List<MenuVO> userMenuList) {
		req.getSession().setAttribute("userMenuList", userMenuList);
	}
	
	
}
