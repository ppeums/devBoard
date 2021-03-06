package com.devBoard.sample.ftp;

import com.devBoard.framework.util.FtpUtil;

import org.apache.commons.net.ftp.FTPFile;

/**
 * @Class Name : TestFtp.java
 * @Description : TestFtp.java Class
 * @Modification Information
 * @
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2012. 7. 31.  송제승     최초생성
 * 
 * @author 송제승
 * @since 2012. 7. 31.
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Sinsuldong All right reserved.
 */
public class TestFtp {

	public static void main(String[] args){
		
		System.out.println("###########################");
		FtpUtil ftp = new FtpUtil();
		if( ftp.connect("10.13.65.145") && ftp.login("gkswn96", "1111")){

			FTPFile[] fs = ftp.getList();
			for(FTPFile f : fs){
				System.out.println(f.getName());
			}
			
			if(! ftp.getFile("c:\\Debug", "zterm.exe")){
				System.out.println("파일 download 실패..");
			}
			
			if(! ftp.upload("C:\\Debug\\WinSvch_20120725.log")){
				System.out.println("파일 Upload 실패 ...");
			}
			
		}
		else {
			System.out.println("접속 실패..");
		}
	}
}
