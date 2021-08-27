package com.devBoard.framework.mail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.devBoard.framework.config.ExXMLConfiguration;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;

/**
 * @Class Name : EgovMultiPartEmail.java
 * @Description : 전자정부 프레임워크 메일 전송기능에 대한 공통 클래스 구현
 * @Modification Information
 * @
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2012. 8. 20.  송제승     최초생성
 * 
 * @author 송제승
 * @since 2012. 8. 20.
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Sinsuldong All right reserved.
 */
public class EgovMultiPartEmail{
	
	Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 메일을 발송한다.
	 * 
	 * @param vo 메일 내용
	 * @return 발송 결과
	 */
	public String send(EgovMailVO vo){
	
		MultiPartEmail mail = new MultiPartEmail();
		try{
			ExXMLConfiguration conf = ExXMLConfiguration.getInstance();

			// 기본 메일 정보를 생성합니다
			mail.setCharset("utf-8");
			
			mail.setHostName(conf.getString("mail.smtp-host"));
			mail.setSmtpPort(conf.getInt("mail.smtp-port"));
			mail.setTLS(true);
//			mail.setAuthenticator(new DefaultAuthenticator(conf.getString("mail.id"), conf.getString("mail.passwd")));
			mail.setSocketConnectionTimeout(conf.getInt("mail.connection-timeout"));
			mail.setSocketTimeout(conf.getInt("mail.send-timeout"));
			mail.setFrom(vo.getFrom(), vo.getFromNm());
			mail.addTo(vo.getTo(), vo.getToNm());
			mail.setSubject(vo.getSubject());
			mail.setMsg(vo.getText());
			//mail.setContent(vo.getText(), "text/html; charset=utf-8");
		
			
			if(vo.getFile1() != null && vo.getFile1().trim().length() > 0){
				File f = new File(vo.getFile1());
				EmailAttachment attachment = new EmailAttachment();
				attachment.setPath(f.getPath());	
				attachment.setDisposition(EmailAttachment.ATTACHMENT);
//				attachment.setDescription("첨부파일입니다");
				attachment.setName(f.getName()); 
				mail.attach(attachment);
			}
			if(vo.getFile2() != null && vo.getFile2().trim().length() > 0){
				File f = new File(vo.getFile2());
				EmailAttachment attachment = new EmailAttachment();
				attachment.setPath(f.getPath());	
				attachment.setDisposition(EmailAttachment.ATTACHMENT);
//				attachment.setDescription("첨부파일입니다");
				attachment.setName(f.getName()); 
				mail.attach(attachment);
			}
			mail.send();
			
	    }catch(MailParseException ex){
	    	logger.error("Sending Mail Exception : " +  ex.getCause() + " [failure when parsing the message]", ex);
	    	return "failure when parsing the message";
	    }catch(MailAuthenticationException ex){
	    	logger.error("Sending Mail Exception : " +  ex.getCause() + " [authentication failure]", ex);
	    	return "authentication failure";
	    }catch(MailSendException ex){
	    	logger.error("Sending Mail Exception : " +  ex.getCause() + " [failure when sending the message]", ex);
	    	return "failure when sending the message";
	    }catch(Exception ex){
	    	logger.error("Sending Mail Exception : " +  ex.getCause(), ex);
	    	return "Sending Mail Exception";
	    }
	    return "Sending Sucess.";
	}
	
}
