package com.devBoard.common.vo;

import com.devBoard.framework.vo.CommonVO;

/**
 * @Class Name : MoneyVO.java
 * @Description : 회비정보를 처리하는 VO Class
 * @Modification Information
 * @
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2012. 12. 27.  송제승     최초생성
 * 
 * @author 송제승
 * @since 2012. 12. 27.
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Sinsuldong All right reserved.
 */
public class MoneyVO extends CommonVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3009517342899595990L;

	/** rn */
	private String rn;
	/** 회비아이디 */
	private String moneyId;
	/** 회비등록일 */
	private String moneyTradedt;
	/** 회비출금액 */
	private String moneyWithdraw;
	/** 회비입금액 */
	private String moneyDeposit;
	/** 회비잔액 */
	private String moneyBalance;
	/** 회비비고 */
	private String moneyDesc;
	/** 회비계좌번호 */
	private String moneyAcnumtage;
	/** 회비은행 */
	private String moneyBanktage;
	/** 회비구분 */
	private String moneyGubun;
	/**
	 * @return the rn
	 */
	public String getRn() {
		return rn;
	}
	/**
	 * @param rn the rn to set
	 */
	public void setRn(String rn) {
		this.rn = rn;
	}
	/**
	 * @return the moneyId
	 */
	public String getMoneyId() {
		return moneyId;
	}
	/**
	 * @param moneyId the moneyId to set
	 */
	public void setMoneyId(String moneyId) {
		this.moneyId = moneyId;
	}
	/**
	 * @return the moneyTradedt
	 */
	public String getMoneyTradedt() {
		return moneyTradedt;
	}
	/**
	 * @param moneyTradedt the moneyTradedt to set
	 */
	public void setMoneyTradedt(String moneyTradedt) {
		this.moneyTradedt = moneyTradedt;
	}
	/**
	 * @return the moneyWithdraw
	 */
	public String getMoneyWithdraw() {
		return moneyWithdraw;
	}
	/**
	 * @param moneyWithdraw the moneyWithdraw to set
	 */
	public void setMoneyWithdraw(String moneyWithdraw) {
		this.moneyWithdraw = moneyWithdraw;
	}
	/**
	 * @return the moneyDeposit
	 */
	public String getMoneyDeposit() {
		return moneyDeposit;
	}
	/**
	 * @param moneyDeposit the moneyDeposit to set
	 */
	public void setMoneyDeposit(String moneyDeposit) {
		this.moneyDeposit = moneyDeposit;
	}
	/**
	 * @return the moneyBalance
	 */
	public String getMoneyBalance() {
		return moneyBalance;
	}
	/**
	 * @param moneyBalance the moneyBalance to set
	 */
	public void setMoneyBalance(String moneyBalance) {
		this.moneyBalance = moneyBalance;
	}
	/**
	 * @return the moneyDesc
	 */
	public String getMoneyDesc() {
		return moneyDesc;
	}
	/**
	 * @param moneyDesc the moneyDesc to set
	 */
	public void setMoneyDesc(String moneyDesc) {
		this.moneyDesc = moneyDesc;
	}
	/**
	 * @return the moneyAcnumtage
	 */
	public String getMoneyAcnumtage() {
		return moneyAcnumtage;
	}
	/**
	 * @param moneyAcnumtage the moneyAcnumtage to set
	 */
	public void setMoneyAcnumtage(String moneyAcnumtage) {
		this.moneyAcnumtage = moneyAcnumtage;
	}
	/**
	 * @return the moneyBanktage
	 */
	public String getMoneyBanktage() {
		return moneyBanktage;
	}
	/**
	 * @param moneyBanktage the moneyBanktage to set
	 */
	public void setMoneyBanktage(String moneyBanktage) {
		this.moneyBanktage = moneyBanktage;
	}
	/**
	 * @return the moneyGubun
	 */
	public String getMoneyGubun() {
		return moneyGubun;
	}
	/**
	 * @param moneyGubun the moneyGubun to set
	 */
	public void setMoneyGubun(String moneyGubun) {
		this.moneyGubun = moneyGubun;
	}
	
	
}
