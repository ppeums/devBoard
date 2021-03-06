package com.devBoard.login.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devBoard.common.security.UserSession;
import com.devBoard.common.service.MenuService;
import com.devBoard.common.vo.MenuVO;
import com.devBoard.login.service.LoginService;
import com.devBoard.login.vo.LoginVO;

/**
 * @Class Name : LoginController.java
 * @Description : 로그인을 처리하는 Controller Class
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
@Controller
public class LoginController {
	
	/** LoginService */
	@Resource(name = "loginService")
	private LoginService loginService;
	
	/** MenuService */
	@Resource(name = "menuService")
	private MenuService menuService;
	
	/**
	 * 로그인 페이지를 조회한다. 
	 * @param loginVO - 로그인 조건이 담긴 VO
	 * @param model
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping(value="/login/retrieveLogin.xx")
	public String retrieveLogin(@ModelAttribute("loginVO") LoginVO loginVO, ModelMap model, HttpServletRequest request) throws Exception {
//		request.getSession().setAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE", Locale.US);
		request.getSession().setAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE", Locale.KOREA);
		
		String userId = UserSession.getUserId(request);
		System.out.println("[로그인 페이지 조회] 세션 아이디: " + userId);
		model.addAttribute("userId", userId);
		
		return "/login/login";
	}
	
	/**
	 * 로그아웃 한다. 
	 * @param loginVO - 로그인 조건이 담긴 VO
	 * @param model
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping(value="/login/retrieveLogout.xx")
	public String retrieveLogout(ModelMap model, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		session.invalidate();
		model.addAttribute("loginVO", new LoginVO());
		return "/login/login";
	} 
	
	/**
	 * 로그인을 처리한다.
	 * @param loginVO - 로그인 조건이 담긴 VO
	 * @param model
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping(value="/login/retrieveLoginProc.xx")
	public String loginProc(@ModelAttribute("loginVO") LoginVO loginVO, ModelMap model, HttpServletRequest request) throws Exception {
		String lwurl = "/login/retrieveLogin.xx";
		
		LoginVO loginVO2 = loginService.retrieveLoginUser(loginVO);
		if( loginVO2 == null) {
			// 사용자가 없는 경우
			model.addAttribute("msgCd", "login.alert.003");
			return "/login/login";
		}
		if( loginVO2.getUserPwd().equals(loginVO.getUserPwd())) {
			MenuVO menuVO = new MenuVO(); 
			menuVO.setUserId(loginVO2.getUserId());
			List<MenuVO> menuList = menuService.retrieveMenuList(menuVO);
			ArrayList<MenuVO> menuResultList = new ArrayList<MenuVO>();
			for( int i = 0; i < menuList.size(); i++) {
				menuResultList.add(((MenuVO)menuList.get(i)));
				MenuVO menuVO1 = new MenuVO(); 
				menuVO1.setMenuId(((MenuVO)menuList.get(i)).getMenuId());
				List<MenuVO> menuList1 = menuService.retrieveMenuSubList(menuVO1);
				for( int j = 0; j < menuList1.size(); j++) {
					menuResultList.add(((MenuVO)menuList1.get(j)));
				}
			}
			UserSession.setUserMenuList(request, menuResultList);
			// 세션생성
			UserSession.setUserId(request, loginVO.getUserId());
			UserSession.setUserPw(request, loginVO.getUserPwd());
		} else {
			// 비밀번호가 다른경우
			model.addAttribute("msgCd", "login.alert.003");
			return "/login/login";
		}
		return "redirect:" + lwurl;
	} 
	
	/**
	 * 이전url처리
	 * @param model
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping(value="/sinsul/sinsulMain.do")
	public String sinsulMain(ModelMap model, HttpServletRequest request) throws Exception {

		return "redirect:/login/retrieveLogin.xx";
	}
	
	/**
	 * 이전url처리
	 * @param model
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping(value="/sinsul/sinsulView.do")
	public String sinsulView(ModelMap model, HttpServletRequest request) throws Exception {
		
		return "redirect:/login/retrieveLogin.xx";
	}
}
