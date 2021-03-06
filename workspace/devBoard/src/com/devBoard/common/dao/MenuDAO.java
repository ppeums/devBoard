package com.devBoard.common.dao;

import java.util.List;

import com.devBoard.common.vo.MenuVO;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


/**
 * @Class Name : MenuDAO.java
 * @Description : 메뉴목록을 처리하는 DAO Class
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

@Repository("menuDAO")
public class MenuDAO extends EgovAbstractDAO {

	/**
	 * 메뉴 목록을 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 */
	@SuppressWarnings("unchecked")
	public List<MenuVO> selectMenuList(MenuVO vo) {
		return list("menuDAO.selectMenuList", vo);
	}

	/**
	 * 메뉴 목록을 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 */
	@SuppressWarnings("unchecked")
	public List<MenuVO> selectMenuSubList(MenuVO vo) {
		return list("menuDAO.selectMenuSubList", vo);
	}
}
