package com.devBoard.login.dao;

import com.devBoard.login.vo.LoginVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


/**
 * @Class Name : LoginDAO.java
 * @Description : 로그인을 처리하는 DAO Class
 * @Modification Information  
 * @
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2012.12.11  송제승     최초생성
 * 
 * @author 송제승
 * @since 2012.12.11
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Sinsuldong All right reserved.
 */

@Repository("loginDAO")
public class LoginDAO extends EgovAbstractDAO {

	/**
	 * 사용자 아이디를 조회한다.
	 * @param vo - 로그인할 정보가 담긴 VO
	 * @return 글 총 갯수
	 */
	public LoginVO selectLoginUser(LoginVO vo) {
		return (LoginVO)getSqlMapClientTemplate().queryForObject("loginDAO.selectLoginUser", vo);
	}

}
