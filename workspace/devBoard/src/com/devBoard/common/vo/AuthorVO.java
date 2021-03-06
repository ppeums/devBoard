package com.devBoard.common.vo;

import com.devBoard.framework.vo.CommonVO;

/**
 * @Class Name : uthorVO.java
 * @Description : 권한정보를  처리하는                VO Class
 * @Modification Information
 * @
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2012. 12. 27.  송제승	     최초생성
 * 
 * @author 송제승
 * @since 2012. 12. 27.
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Sinsuldong All right reserved.
 */
public class AuthorVO extends CommonVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5667944428359778221L;
	
	/** 사용자ID */
	private String userId;
	/** 메뉴ID */
	private String menuId;
	/** 등록일자 */
	private String regDate;
	/** 등록자 */
	private String regId;
	/** 수정일자 */
	private String updDate;
	/** 수정자 */
	private String updId;
	/** 삭제일자 */
	private String delDate;
	/** 삭제자  */
	private String delId;
	/** 삭제여부*/
	private String delYn;
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the menuId
	 */
	public String getMenuId() {
		return menuId;
	}
	/**
	 * @param menuId the menuId to set
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	/**
	 * @return the regDate
	 */
	public String getRegDate() {
		return regDate;
	}
	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	/**
	 * @return the regId
	 */
	public String getRegId() {
		return regId;
	}
	/**
	 * @param regId the regId to set
	 */
	public void setRegId(String regId) {
		this.regId = regId;
	}
	/**
	 * @return the updDate
	 */
	public String getUpdDate() {
		return updDate;
	}
	/**
	 * @param updDate the updDate to set
	 */
	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}
	/**
	 * @return the updId
	 */
	public String getUpdId() {
		return updId;
	}
	/**
	 * @param updId the updId to set
	 */
	public void setUpdId(String updId) {
		this.updId = updId;
	}
	/**
	 * @return the delDate
	 */
	public String getDelDate() {
		return delDate;
	}
	/**
	 * @param delDate the delDate to set
	 */
	public void setDelDate(String delDate) {
		this.delDate = delDate;
	}
	/**
	 * @return the delId
	 */
	public String getDelId() {
		return delId;
	}
	/**
	 * @param delId the delId to set
	 */
	public void setDelId(String delId) {
		this.delId = delId;
	}
	/**
	 * @return the delYn
	 */
	public String getDelYn() {
		return delYn;
	}
	/**
	 * @param delYn the delYn to set
	 */
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

}
