package com.devBoard.common.service;

import java.util.List;

import com.devBoard.common.vo.MenuVO;

/**
 * @Class Name : MenuService.java
 * @Description : MenuService.java Class
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
public interface MenuService {
	/**
	 * 메뉴 목록을 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 메뉴목록
	 * @exception Exception
	 */
	public List<MenuVO> retrieveMenuList(MenuVO vo) throws Exception;
	
	/**
	 * 메뉴 목록을 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 메뉴목록
	 * @exception Exception
	 */
	public List<MenuVO> retrieveMenuSubList(MenuVO vo) throws Exception;
}
