package com.devBoard.framework.exception;


/**  
 * @Class Name : ExRuntimeException.java
 * @Description : ExRuntimeException Class
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
public class ExRuntimeException extends RuntimeException {

	/**
	 * @param string
	 */
	public ExRuntimeException(String string) {
		super(string);
	}

	/**
	 * @param e
	 */
	public ExRuntimeException(Exception e) {
		super(e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4506123808402853131L;

}
