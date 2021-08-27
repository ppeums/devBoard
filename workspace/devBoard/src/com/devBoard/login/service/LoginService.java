package com.devBoard.login.service;

import com.devBoard.login.vo.LoginVO;

/**
 * @Class Name : LoginService.java
 * @Description : 로그인 정보를 처리하는 Service Class
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
public interface LoginService {
	/**
	 * 로그인하는 사용자 정보를 조회한다.
	 * @param vo - 로그인할 정보가 담긴 VO
	 * @return 메뉴목록
	 * @exception Exception
	 */
	public LoginVO retrieveLoginUser(LoginVO vo) throws Exception;
}
