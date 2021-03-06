package com.devBoard.framework.vo;


/**  
 * @Class Name : FileVO.java
 * @Description : FileVO Class
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
public class FileVO extends CommonVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7958935495094688815L;
	
	String originalFilename = "";
	String directory = "";
	String filename = "";
	long size = 0;
	
	long id = 0; // 파일 SEQ
	String fid = ""; // 첨부 SEQ
	String createUserId = ""; // 파일 사용자
	int downCnt = 0; // 다운로드 Count
	String createDate = ""; // 파일 Upload 일자
	
	/** 내가 추가한 변수 */
	String fileStrePath = ""; //파일 스트림 경로?
	String fileStreNm = ""; //파일 스트림 이름?
	String fileStreOriNm = ""; //파일 스트림 원래이름?
	long fileSize = 0;
	
	String rgstId = "";
	String fileExt = "";
	String fileCd = "";
	int fileOrder = 0;
	
	String rgstDt = "";
	String updId = "";
	String updDt = "";
	
	
	/**
	 * @return the originalFilename
	 */
	public String getOriginalFilename() {
		return originalFilename;
	}
	/**
	 * @param originalFilename the originalFilename to set
	 */
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	/**
	 * @return the directory
	 */
	public String getDirectory() {
		return directory;
	}
	/**
	 * @param directory the directory to set
	 */
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(long size) {
		this.size = size;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the fid
	 */
	public String getFid() {
		return fid;
	}
	/**
	 * @param fid the fid to set
	 */
	public void setFid(String fid) {
		this.fid = fid;
	}
	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}
	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * @return the downCnt
	 */
	public int getDownCnt() {
		return downCnt;
	}
	/**
	 * @param downCnt the downCnt to set
	 */
	public void setDownCnt(int downCnt) {
		this.downCnt = downCnt;
	}
	/**
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * @param directory2
	 */
	public void setFileStrePath(String fileStrePath) {
		// TODO Auto-generated method stub
		this.fileStrePath = fileStrePath;
	}
	/**
	 * @return
	 */
	public String getFileStrePath() {
		// TODO Auto-generated method stub
		return fileStrePath;
	}
	/**
	 * @param filenameToSave
	 */
	public void setFileStreNm(String fileStreNm) {
		// TODO Auto-generated method stub
		this.fileStreNm = fileStreNm;
	}
	/**
	 * @return
	 */
	public String getFileStreNm() {
		// TODO Auto-generated method stub
		return fileStreNm;
	}
	/**
	 * @param originalFilename2
	 */
	public void setFileStreOriNm(String fileStreOriNm) {
		// TODO Auto-generated method stub
		this.fileStreOriNm = fileStreOriNm;
	}
	/**
	 * @return
	 */
	public String getFileStreOriNm() {
		// TODO Auto-generated method stub
		return fileStreOriNm;
	}
	/**
	 * @param size2
	 */
	public void setFileSize(long fileSize) {
		// TODO Auto-generated method stub
		this.fileSize = fileSize;
	}
	/**
	 * @param userId
	 */
	public void setRgstId(String rgstId) {
		// TODO Auto-generated method stub
		this.rgstId = rgstId;
	}
	/**
	 * @param fileExt2
	 */
	public void setFileExt(String fileExt) {
		// TODO Auto-generated method stub
		this.fileExt = fileExt;
	}
	/**
	 * @param string
	 */
	public void setFileCd(String fileCd) {
		// TODO Auto-generated method stub
		this.fileCd = fileCd;
	}
	/**
	 * @param i
	 */
	public void setFileOrder(int fileOrder) {
		// TODO Auto-generated method stub
		this.fileOrder = fileOrder;
	}
	
	
	
	
}
