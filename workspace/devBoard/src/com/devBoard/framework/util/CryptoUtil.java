package com.devBoard.framework.util;

import java.io.File;

import com.devBoard.framework.config.ExXMLConfiguration;
import com.devBoard.framework.exception.ExRuntimeException;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import com.initech.safedb.SimpleSafeDB;
import com.initech.safedb.common.SafeDBException;

/**
 * @Class Name : CryptoUtil.java
 * @Description : CryptoUtil.java Class
 * @Modification Information
 * @
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2012. 8. 24.  송제승     최초생성
 * 
 * @author 송제승
 * @since 2012. 8. 24.
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Sinsuldong All right reserved.
 */
public class CryptoUtil {
	// 암복호화 Class
	private static StandardPBEStringEncryptor standardStringEncryptor = null;
	
	// 단방향 암호화 Class
	private static SimpleSafeDB ssdb = null;
	
	/**
	 * 암호화 Class 초기화
	 */
	private static void init() {
		if(standardStringEncryptor==null) {
			ExXMLConfiguration config = ExXMLConfiguration.getInstance();
			
			String algorithm = config.getString("security.crypto.algorithm");
	    	String password = config.getString("security.crypto.password");
	    	
	    	if(algorithm==null || "".equals(algorithm)) {
	    		throw new ExRuntimeException("알고리즘이 설정되지 않았습니다.");
	    	}
	    	if(password==null || "".equals(password)) {
	    		throw new ExRuntimeException("암호가 설정되지 않았습니다.");
	    	}
	    	
	    	standardStringEncryptor = new StandardPBEStringEncryptor();
			standardStringEncryptor.setAlgorithm(algorithm);
			standardStringEncryptor.setPassword(password);
		}
	}
	
	/**
	 * 복호화
	 * @param encryptedMessage 암호화된 문자열
	 * @return 복호화된 문자열
	 */
	public static String decrypt(String encryptedMessage) {
		// 초기화
		init();
		
		String decryptedMessage = standardStringEncryptor.decrypt(encryptedMessage);
		
		return decryptedMessage;
	}
    
	/**
     * 암호화
     * 
     * @param message 복호화된 문자열
     * @return 암호화된 문자열
     */
	public static String encrypt(String message) {
		// 초기화
		init();
		
		String encryptedMessage = standardStringEncryptor.encrypt(message);
		
		return encryptedMessage;
	}
	
	/**
	 * 단방향 암호화 초기화
	 */
	private static void initUniDirection() {
		if(ssdb == null) {
			try {
				String config = ExXMLConfiguration.getInstance().getString("security.safedb.config");
				
				if(File.pathSeparator.equals("\\")) config = config.replaceAll("/", File.pathSeparator);
				
				ssdb = SimpleSafeDB.getInstance(config);
			} catch(SafeDBException e) {
				throw new ExRuntimeException(e);
			}
		}
	}
	
	/**
     * 단방향 암호화
     * 
     * @param message 복호화된 문자열
     * @return 암호화된 문자열
     */
	public static String encryptUniDirection(String message) {
		return encryptSafeDB(message, true);
	}

	/**
     * 단방향 / 양방향 암호화 
     * 
     * @param message 일반 문자열
     * @param isUniDirection 단방향(true) / 양방향(false)
     * @return 암호화된 문자열
     */
	public static String encryptSafeDB(String message, boolean isUniDirection) {
		ExXMLConfiguration conf = ExXMLConfiguration.getInstance();
		// 초기화
		initUniDirection();

		// 암호화를 위한 로그인 계정
		String userId = conf.getString("security.safedb.user-id");
		// 암호화 정책 테이블
		String policyTable = conf.getString("security.safedb.policy-table");
		// 암호화 정책 컬럼
		// String policyColumn = "GENERAL_ARIA256"; // 양방향
		String policyColumn = null;
		if(isUniDirection){
			policyColumn = conf.getString("security.safedb.policy-column"); // 단방향
		} else {
			policyColumn = "GENERAL_ARIA256";
		}
		try {
			if(ssdb.login()) {
				byte[] plainData = message.getBytes();
				byte[] encryptedData = ssdb.encrypt(userId, policyTable, policyColumn, plainData);
			
				return new String(encryptedData);
			} else {
				return null;
			}
		} catch(SafeDBException e) {
			throw new ExRuntimeException(e);
		}
	}
	
	/**
     * 양방향 복호화. 
     * 
     * @param message 암호화된 문자열
     * @return 암호화된 문자열
     */
	public static String decryptSafeDB(String message) {
		ExXMLConfiguration conf = ExXMLConfiguration.getInstance();
		// 초기화
		initUniDirection();

		// 암호화를 위한 로그인 계정
		String userId = conf.getString("security.safedb.user-id");
		// 암호화 정책 테이블
		String policyTable = conf.getString("security.safedb.policy-table");
		// 암호화 정책 컬럼
		String policyColumn = "GENERAL_ARIA256"; // 양방향
		try {
			if(ssdb.login()) {
				byte[] plainData = message.getBytes();
				byte[] decryptedData = ssdb.decrypt(userId, policyTable, policyColumn, plainData);
			
				return new String(decryptedData);
			} else {
				return null;
			}
		} catch(SafeDBException e) {
			throw new ExRuntimeException(e);
		}
	}
	
	
	
	
	
	/**
	 * 환경설정 파일에 패스워드를 암호화해서 저장할 때 사용
	 * 
	 * @param args Arguments
	 */
	public static void main(String[] args) {
		if(args.length<1) {
			System.out.println("암호화할 메시지를 입력하십시오.");
		} else {
			System.out.println("- Plain     message: " + args[0]);
			System.out.println("- Encrypted message: " + encrypt(args[0]));
		}
	}
}
