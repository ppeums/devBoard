package com.devBoard.framework.mail;

/**
 * @Class Name : MailVO
 * @Description : 메일정보 VO Class
 * @Modification Information  
 * @
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2012.07.20  황경환     최초생성
 * @ 2012.08.16  송제승     메일 전송 로직 공통 모듈화 진행
 * 
 * @author 건강보험공단 정보시스템구축 인터넷(사이버민원) 개발팀
 * @since 2012.07.20
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Sinsuldong All right reserved.
 */
public class EgovMailVO {
	
	/** 메일보내는 사람 메일주소*/
	private String from = "";
	
	/** 메일보내는 사람 이름 */
	private String fromNm = "";
	
	/** 메일받는사람 메일주소 */
	private String to = "";
	
	/** 메일받는사람 이름 */
	private String toNm = "";
	
	/** 메일 참조 */
	private String cc = "";
	
	/** 메일 숨은 참조 */
	private String bcc = "";
	
	/** 메일 제목 */
	private String subject = "";
	
	/** 메일내용 */
	private String text = "";
	
	/** 첨부파일 1 */
	private String file1 = "";
	
	/** 첨부파일 2 */
	private String file2 = "";

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getFromNm() {
		return fromNm;
	}

	public void setFromNm(String fromNm) {
		this.fromNm = fromNm;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getToNm() {
		return toNm;
	}

	public void setToNm(String toNm) {
		this.toNm = toNm;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFile1() {
		return file1;
	}

	public void setFile1(String file1) {
		this.file1 = file1;
	}

	public String getFile2() {
		return file2;
	}

	public void setFile2(String file2) {
		this.file2 = file2;
	}
	

	
}
