package com.devBoard.login.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.devBoard.login.dao.LoginDAO;
import com.devBoard.login.service.LoginService;
import com.devBoard.login.vo.LoginVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * @Class Name : LoginServiceImpl.java
 * @Description : 로그인 정보를 처리하는 Business Implement Class
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
@Service("loginService")
public class LoginServiceImpl extends AbstractServiceImpl implements LoginService {
	
	/** LoginDAO */
	@Resource(name="loginDAO")
	private LoginDAO loginDAO;
	
	public LoginVO retrieveLoginUser(LoginVO vo) throws Exception {
		return loginDAO.selectLoginUser(vo);
	}

}
