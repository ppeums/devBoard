package com.devBoard.framework.validation;

import java.io.UnsupportedEncodingException;

import org.apache.commons.validator.GenericValidator;
import org.apache.log4j.Logger;

/**  
 * @Class Name : LwGenericValidator.java
 * @Description : LwGenericValidator Class
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
public class LwGenericValidator {
	private static Logger logger = Logger.getLogger(LwGenericValidator.class);
	
	/**
	 * 입력된 값이 유효한 연월인지 체크한다
	 *  
	 * @param value 입력값
	 * @param yearMonthPatternStrict pattern
	 * @return 유효성 여부
	 */
	public static boolean isValidYearMonth(String value, String yearMonthPatternStrict) {
		String maskChar = yearMonthPatternStrict.replaceAll("yyyy", "").replaceAll("MM", "");
		
		return GenericValidator.isDate(value + maskChar + "01", yearMonthPatternStrict + maskChar + "dd", true);
	}
	
	/**
	 * 입력된 값이 minbyte 설정값 미만인지를 체크한다
	 *  
	 * @param value 입력값
	 * @param minbyte 최소 byte 길이
	 * @return 유효성 여부
	 */
	public static boolean isValidMinByte(String value, int minbyte) {
		if(value!=null && !"".equals(value) && getByteLength(value)<minbyte) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 입력된 값이 maxbyte 설정값을 초과하는지 체크한다
	 *  
	 * @param value 입력값
	 * @param maxbyte 최대 byte 길이
	 * @return 유효성 여부
	 */
	public static boolean isValidMaxByte(String value, int maxbyte) {
		if(value!=null && !"".equals(value) && getByteLength(value)>maxbyte) {
			return false;
		} else {
			return true;
		}
	}
	
	private static int getByteLength(String value) {
		int byteLength = 0;
		
		try {
			// WAS에서 한글이 3byte로 처리되는 것을 강제로 2byte로 처리되도록 변경
			byteLength = value.getBytes("MS949").length;
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		
		return byteLength;
	}
	
	/*
	public static void main(String[] args) {
		System.out.println(GenericValidator.isValidMaxByte("201101", 5));
	}
	*/
}
