package com.devBoard.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.devBoard.common.dao.MenuDAO;
import com.devBoard.common.service.MenuService;
import com.devBoard.common.vo.MenuVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * @Class Name : MenuServiceImpl.java
 * @Description : MenuServiceImpl.java Class
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
@Service("menuService")
public class MenuServiceImpl extends AbstractServiceImpl implements MenuService {
	
	/** MenuDAO */
	@Resource(name="menuDAO")
	private MenuDAO menuDAO;
	
	/**
	 * 메뉴 목록을 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 메뉴목록
	 * @exception Exception
	 */
	public List<MenuVO> retrieveMenuList(MenuVO vo) throws Exception {
		return menuDAO.selectMenuList(vo);
	}
	
	/**
	 * 메뉴 목록을 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 메뉴목록
	 * @exception Exception
	 */
	public List<MenuVO> retrieveMenuSubList(MenuVO vo) throws Exception {
		return menuDAO.selectMenuSubList(vo);
	}

}
