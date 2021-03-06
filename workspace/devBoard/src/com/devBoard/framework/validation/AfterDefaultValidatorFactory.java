package com.devBoard.framework.validation;

import org.apache.log4j.Logger;

/**  
 * @Class Name : AfterDefaultValidatorFactory.java
 * @Description : AfterDefaultValidatorFactory Class
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
public class AfterDefaultValidatorFactory {
	/**
	 * BeforeDefaultValidatorFactory를 설정하여 원 로그 레벨로 되돌린다.
	 * 
	 * @param beforeDefaultValidatorFactory BeforeDefaultValidatorFactory
	 */
	public void setBeforeDefaultValidatorFactory(BeforeDefaultValidatorFactory beforeDefaultValidatorFactory) {
		Logger.getRootLogger().setLevel(beforeDefaultValidatorFactory.getOriginalLevel());
		
		Logger logger = Logger.getLogger(this.getClass());
		logger.info("set log level to " + beforeDefaultValidatorFactory.getOriginalLevel());
	}
}
