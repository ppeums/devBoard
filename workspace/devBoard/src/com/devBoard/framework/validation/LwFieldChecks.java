package com.devBoard.framework.validation;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.ValidatorAction;
import org.springframework.validation.Errors;
import org.springmodules.validation.commons.FieldChecks;

/**  
 * @Class Name : LwFieldChecks.java
 * @Description : LwFieldChecks Class
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
public class LwFieldChecks extends FieldChecks {

	private static final long serialVersionUID = 4163980152557797164L;
	
	/**
	 * 입력된 값이 유효한 연월인지 체크한다
	 * 
	 * @param bean 데이터가 포함된 Bean
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors Errors
	 * @return 유효성 여부
	 */
	public static boolean validateYearMonth(Object bean, ValidatorAction va, Field field, Errors errors) {
		String value = FieldChecks.extractValue(bean, field);
		String yearMonthPatternStrict = field.getVarValue("yearMonthPatternStrict");
		
		if(LwGenericValidator.isValidYearMonth(value, yearMonthPatternStrict)) {
			return true;
		} else {
			FieldChecks.rejectValue(errors, field, va);
			return false;
		}
	}
	
	/**
	 * 입력된 값이 minbyte 설정값 미만인지를 체크한다
	 * 
	 * @param bean 데이터가 포함된 Bean
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors Errors
	 * @return 유효성 여부
	 */
	public static boolean validateMinByte(Object bean, ValidatorAction va, Field field, Errors errors) {
		String value = FieldChecks.extractValue(bean, field);
		int minbyte = Integer.parseInt(field.getVarValue("minbyte"));
		
		if(LwGenericValidator.isValidMinByte(value, minbyte)) {
			return true;
		} else {
			FieldChecks.rejectValue(errors, field, va);
			return false;
		}
	}
	
	/**
	 * 입력된 값이 maxbyte 설정값을 초과하는지 체크한다
	 * 
	 * @param bean 데이터가 포함된 Bean
	 * @param va ValidatorAction
	 * @param field Field
	 * @param errors Errors
	 * @return 유효성 여부
	 */
	public static boolean validateMaxByte(Object bean, ValidatorAction va, Field field, Errors errors) {
		String value = FieldChecks.extractValue(bean, field);
		int maxbyte = Integer.parseInt(field.getVarValue("maxbyte"));
		
		if(LwGenericValidator.isValidMaxByte(value, maxbyte)) {
			return true;
		} else {
			FieldChecks.rejectValue(errors, field, va);
			return false;
		}
	}

}
