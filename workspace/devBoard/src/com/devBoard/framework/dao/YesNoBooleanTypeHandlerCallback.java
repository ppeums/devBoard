package com.devBoard.framework.dao;

import java.sql.SQLException;

import com.devBoard.framework.exception.ExRuntimeException;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

/**  
 * @Class Name : YesNoBooleanTypeHandlerCallback.java
 * @Description : YesNoBooleanTypeHandlerCallback Class
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
public class YesNoBooleanTypeHandlerCallback implements TypeHandlerCallback {

	private static final String YES = "Y";
	private static final String NO = "N";
	
	/* (non-Javadoc)
	 * @see com.ibatis.sqlmap.client.extensions.TypeHandlerCallback#getResult(com.ibatis.sqlmap.client.extensions.ResultGetter)
	 */
	public Object getResult(ResultGetter getter) throws SQLException {
		String s = getter.getString();
		
		if(YES.equals(s)) {
			return true;
		} else if(NO.equals(s)) {
			return false;
		} else {
			throw new SQLException("Unexpected value " + s + " found where " + YES + " or " + NO + " was expected.");
		}
	}

	/* (non-Javadoc)
	 * @see com.ibatis.sqlmap.client.extensions.TypeHandlerCallback#setParameter(com.ibatis.sqlmap.client.extensions.ParameterSetter, java.lang.Object)
	 */
	public void setParameter(ParameterSetter setter, Object param) throws SQLException {
		boolean b = ((Boolean)param).booleanValue();
		
		if(b) {
			setter.setString(YES);
		} else {
			setter.setString(NO);
		}
	}

	/* (non-Javadoc)
	 * @see com.ibatis.sqlmap.client.extensions.TypeHandlerCallback#valueOf(java.lang.String)
	 */
	public Object valueOf(String s) {
		if(YES.equals(s)) {
			return new Boolean(true);
		} else if(NO.equals(s)) {
			return new Boolean(false);
		} else {
			throw new ExRuntimeException("Unexpected value " + s + " found where " + YES + " or " + NO + " was expected.");
		}
	}

}
