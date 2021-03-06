package com.devBoard.framework.util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

/**  
 * @Class Name : FtpUtil.java
 * @Description : FtpUtil Class
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
public class FtpUtil {
	
	FTPClient ftp = null;
	Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 기본 포트로 FTP 연결
	 * 
	 * @param ip IP Address
	 * @return 접속 여부
	 */
	public boolean connect(String ip){
		return connect(ip, 21);
	}
	
	/**
	 * FTP 연결
	 * @param ip IP Address
	 * @param port port 
	 * @return 접속 여부
	 */
	public boolean connect(String ip, int port) {
		ftp = new FTPClient();
		boolean result = false;
		
		try{
			FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
			config.setServerLanguageCode("ko");
			ftp.configure(config);
			ftp.setDataTimeout(5000);
			ftp.setDefaultTimeout(5000);
			
			ftp.connect(ip, port);
			int reply = ftp.getReplyCode();
			
			if(! FTPReply.isPositiveCompletion(reply)){
				disConnect();
				logger.debug(String.format("FTP서버 연결 거부 IP [%s], port [%d]", ip, port));
			}
			
			result = true;
			
		} catch (IOException ioe) {
			
			logger.error("ftp IOException ", ioe);
			ioe.printStackTrace();
			
			if(ftp.isConnected()) {
				disConnect();
			}
			logger.debug(String.format("FTP서버 연결 실패 IP [%s], port [%d]", ip, port));
			
		}
		return result;
	}
	
	
	/**
	 * 연결 끊기
	 */
	public void disConnect() {
		if(ftp.isConnected()){
			try{ 
				ftp.logout(); 
				ftp.disconnect(); 
			
			} catch(Exception e){}
		}
		ftp = null;
	}
	
	
	/**
	 * FTP Login
	 * 
	 * @param acc 계정
	 * @param pwd 비밀번호
	 * @return 성공여부
	 */
	public boolean login(String acc, String pwd) {
		boolean result = false;
		
		try {
			result = ftp.login(acc, pwd);
			ftp.enterLocalPassiveMode();
			
			if(! result){
				disConnect();
			}
			
			logger.debug(String.format("FTP Login : ID [%s] password [%s] result [%s]", acc, pwd, result));
		} catch (IOException e) {
			logger.error("ftp IOException ", e);
			disConnect();
		}
		return result;
	}
	
	
	/**
	 * 작업경로 변경
	 * 
	 * @param dirPath 변경 Path
	 * @return 변경된 경로의 File 목록을 반환
	 */
	public FTPFile[] changeDir(String dirPath){
		
		FTPFile[] result = null;
		try {
			if( ftp.changeWorkingDirectory(dirPath)){
				logger.debug(String.format("FTP Change Dir : ID [%s] ", dirPath));
				result = getList();
			}
		} catch (IOException e) {
			logger.error("ftp IOException ", e);
		}
		return result;
	}
	
	
	
	/**
	 * 현재경로의 File 목록을 반환
	 * 
	 * @return 파일목록
	 */
	public FTPFile[] getList(){
		FTPFile[] result = null;
		
		try {
			result = ftp.listFiles();
			if(result != null){
				logger.debug(String.format("FTP GetList : FileCount [%d] ", result.length));
			} else {
				logger.debug(String.format("FTP GetList : File Not Exist "));
			}
		} catch (IOException e) {
			logger.error("ftp IOException ", e);
		}
		
		return result;
	}
	
	
	/**
	 * 파일을 Download
	 * 
	 * @param localPath 로컬 경로
	 * @param fileName 파일 명
	 * @return 다운로드 성공 여부
	 */
	public boolean getFile(String localPath, String fileName){
		boolean result = false;
		DataOutputStream dos = null;
		
		try{
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			
			if(! localPath.endsWith(File.separator)){
				localPath += File.separator;
			}
			
			File lDir = new File(localPath);
			if(!lDir.exists()){
				lDir.mkdirs();
		    }
			
			dos = new DataOutputStream(new FileOutputStream(localPath + fileName));
			result = ftp.retrieveFile(fileName, dos);
			ftp.noop();
			 
			logger.debug(String.format("FTP GetFile : localPath [%s] fileName [%s] result [%s] ", localPath, fileName, result));
		} catch(Exception e){
			e.printStackTrace();
			logger.error(ftp.getReplyString());
			logger.error("ftp getFile Exception ", e);
			result = false;
		}
		finally {
            if (dos != null) try { dos.close(); } catch(IOException ex) {}
        }
		
		return result;
	}
	
	
	// 파일 upload
	public boolean upload(String upFile){
		boolean isSuccess = false;
		File uploadFile = new File(upFile);
		FileInputStream fis = null;
		try{ 
			fis = new FileInputStream(uploadFile);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			isSuccess = ftp.storeFile(uploadFile.getName(), fis);

		}catch(IOException ioe){
			logger.error(ioe.getMessage(), ioe); 
		}catch(Exception e){
			logger.error(e.getMessage(), e); 
		}finally{
			if(fis != null){try{fis.close();}catch(IOException ioe){}}
		}
		return isSuccess; 
	}	
}


