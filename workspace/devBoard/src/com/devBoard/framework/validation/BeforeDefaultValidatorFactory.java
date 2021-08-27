package com.devBoard.framework.validation;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**  
 * @Class Name : BeforeDefaultValidatorFactory.java
 * @Description : BeforeDefaultValidatorFactory Class
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
public class BeforeDefaultValidatorFactory {
	private Level originalLevel;
	
	/**
	 * Default 생성자
	 * 로그 레벨이 Debug일 때 Validation rule을 전체 출력하는 문제를 해결하기 위해 임시로 로그 레벨을 Info로 변경 
	 */
	public BeforeDefaultValidatorFactory() {
		originalLevel = Logger.getRootLogger().getLevel();
		
		Logger.getRootLogger().setLevel(Level.INFO);
		
		Logger logger = Logger.getLogger(this.getClass());
		logger.info("set log level to INFO");
	}
	
	/**
	 * 로그 레벨 변경 전의 원 로그 레벨을 리턴한다.
	 * 
	 * @return 로그 레벨 변경 전의 원 로그 레벨
	 */
	public Level getOriginalLevel() {
		return this.originalLevel;
	}
}
