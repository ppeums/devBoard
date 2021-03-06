package com.devBoard.framework.util;

import com.devBoard.framework.config.ExXMLConfiguration;
import com.devBoard.framework.vo.CommonVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**  
 * @Class Name : PaginationUtil.java
 * @Description : PaginationUtil Class
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
public class PaginationUtil {
	/**
	 * 페이지 정보를 리턴한다.
	 * 
	 * @param vo 공통 VO
	 * @return 페이지 정보
	 */
	public static PaginationInfo getPaginationInfo(CommonVO vo) {
		ExXMLConfiguration conf = ExXMLConfiguration.getInstance();
		
		vo.setPageUnit(conf.getInt("paging.unit"));
    	vo.setPageSize(conf.getInt("paging.size"));
		
		PaginationInfo paginationInfo = new PaginationInfo();
		
		paginationInfo.setCurrentPageNo(vo.getPageIndex());
		paginationInfo.setPageSize(vo.getPageSize());
		
		if(vo.getRecordCountPerPage()!=conf.getInt("paging.unit")) {
			paginationInfo.setRecordCountPerPage(vo.getRecordCountPerPage());
		} else {
			paginationInfo.setRecordCountPerPage(vo.getPageUnit());
		}
		
		vo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		vo.setLastIndex(paginationInfo.getLastRecordIndex());
		
		return paginationInfo;
	}
}
