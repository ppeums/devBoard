package com.devBoard.login.vo;

import com.devBoard.framework.vo.CommonVO;

/**
 * @Class Name : LoginVO.java
 * @Description : 로그인을 처리하는 VO Class
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
public class LoginVO extends CommonVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5241771715568818593L;
	/** 사용자ID */
	private String userId;
	/** 사용자비밀번호 */
	private String userPwd;
	/** 사용자구분 */
	private String userType;
	/** 사업자등록번호 */
	private String licenseNo;
	/** 홈페이지 */
	private String siteUrl;
	/** 대표자명 */
	private String chiefName;
	/** 담당자명 */
	private String chargeName;
	/** 전화번호 */
	private String telNo;
	/** 이메일 */
	private String email;
	/** 결재은행 */
	private String bankCode;
	/** 예금주 */
	private String depositor;
	/** 계좌번호 */
	private String accountNo;
	/** FTP경로 */
	private String ftpPath;
	/** FTP아이피 */
	private String ftpIp;
	/** FTP포트 */
	private String ftpPort;
	/** FTP아이디 */
	private String ftpId;
	/** FTP비밀번호 */
	private String ftpPwd;
	/** FTP모드 */
	private String ftpMode;
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
	 * @return the userPwd
	 */
	public String getUserPwd() {
		return userPwd;
	}
	/**
	 * @param userPwd the userPwd to set
	 */
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * @return the licenseNo
	 */
	public String getLicenseNo() {
		return licenseNo;
	}
	/**
	 * @param licenseNo the licenseNo to set
	 */
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	/**
	 * @return the siteUrl
	 */
	public String getSiteUrl() {
		return siteUrl;
	}
	/**
	 * @param siteUrl the siteUrl to set
	 */
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}
	/**
	 * @return the chiefName
	 */
	public String getChiefName() {
		return chiefName;
	}
	/**
	 * @param chiefName the chiefName to set
	 */
	public void setChiefName(String chiefName) {
		this.chiefName = chiefName;
	}
	/**
	 * @return the chargeName
	 */
	public String getChargeName() {
		return chargeName;
	}
	/**
	 * @param chargeName the chargeName to set
	 */
	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}
	/**
	 * @return the telNo
	 */
	public String getTelNo() {
		return telNo;
	}
	/**
	 * @param telNo the telNo to set
	 */
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the bankCode
	 */
	public String getBankCode() {
		return bankCode;
	}
	/**
	 * @param bankCode the bankCode to set
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	/**
	 * @return the depositor
	 */
	public String getDepositor() {
		return depositor;
	}
	/**
	 * @param depositor the depositor to set
	 */
	public void setDepositor(String depositor) {
		this.depositor = depositor;
	}
	/**
	 * @return the accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}
	/**
	 * @param accountNo the accountNo to set
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	/**
	 * @return the ftpPath
	 */
	public String getFtpPath() {
		return ftpPath;
	}
	/**
	 * @param ftpPath the ftpPath to set
	 */
	public void setFtpPath(String ftpPath) {
		this.ftpPath = ftpPath;
	}
	/**
	 * @return the ftpIp
	 */
	public String getFtpIp() {
		return ftpIp;
	}
	/**
	 * @param ftpIp the ftpIp to set
	 */
	public void setFtpIp(String ftpIp) {
		this.ftpIp = ftpIp;
	}
	/**
	 * @return the ftpPort
	 */
	public String getFtpPort() {
		return ftpPort;
	}
	/**
	 * @param ftpPort the ftpPort to set
	 */
	public void setFtpPort(String ftpPort) {
		this.ftpPort = ftpPort;
	}
	/**
	 * @return the ftpId
	 */
	public String getFtpId() {
		return ftpId;
	}
	/**
	 * @param ftpId the ftpId to set
	 */
	public void setFtpId(String ftpId) {
		this.ftpId = ftpId;
	}
	/**
	 * @return the ftpPwd
	 */
	public String getFtpPwd() {
		return ftpPwd;
	}
	/**
	 * @param ftpPwd the ftpPwd to set
	 */
	public void setFtpPwd(String ftpPwd) {
		this.ftpPwd = ftpPwd;
	}
	/**
	 * @return the ftpMode
	 */
	public String getFtpMode() {
		return ftpMode;
	}
	/**
	 * @param ftpMode the ftpMode to set
	 */
	public void setFtpMode(String ftpMode) {
		this.ftpMode = ftpMode;
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
