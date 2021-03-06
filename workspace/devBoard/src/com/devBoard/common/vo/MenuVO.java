package com.devBoard.common.vo;

import com.devBoard.framework.vo.CommonVO;

/**
 * @Class Name : MenuVO.java
 * @Description : 메뉴목록을 처리하는 VO Class
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
public class MenuVO extends CommonVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 110569762373367793L;

	/** 메뉴 아이디 */
	private String menuId;
	/** 상위 메뉴 아이디 */
	private String parentId;
	/** 메뉴 명  한글*/
	private String menuNameKor;
	/** 메뉴 명 영어*/
	private String menuNameEng;
	/** 정렬순번 */
	private String menuOrder;
	/** 메뉴 경로 */
	private String menuUrl;
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
	/** 사용자ID*/
	private String userId;
	
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
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the menuNameKor
	 */
	public String getMenuNameKor() {
		return menuNameKor;
	}
	/**
	 * @param menuNameKor the menuNameKor to set
	 */
	public void setMenuNameKor(String menuNameKor) {
		this.menuNameKor = menuNameKor;
	}
	/**
	 * @return the menuNameEng
	 */
	public String getMenuNameEng() {
		return menuNameEng;
	}
	/**
	 * @param menuNameEng the menuNameEng to set
	 */
	public void setMenuNameEng(String menuNameEng) {
		this.menuNameEng = menuNameEng;
	}
	/**
	 * @return the menuOrder
	 */
	public String getMenuOrder() {
		return menuOrder;
	}
	/**
	 * @param menuOrder the menuOrder to set
	 */
	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}
	/**
	 * @return the menuUrl
	 */
	public String getMenuUrl() {
		return menuUrl;
	}
	/**
	 * @param menuUrl the menuUrl to set
	 */
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
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
