package com.devBoard.sample.vo;

import com.devBoard.framework.vo.CommonVO;

/**
 * @Class Name : SampleFileVO.java
 * @Description : 파일업로드 다운로드 샘플 VO Class
 * @Modification Information
 * @
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2012. 12. 13.  송제승     최초생성
 * 
 * @author 송제승
 * @since 2012. 12. 13.
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Sinsuldong All right reserved.
 */
public class SampleFileVO extends CommonVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3666195132542649225L;
	
	private String inputName;
	private String originalFilename;
	private String directory;
	private String filename;
	private long size;
	
	/**
	 * @return the inputName
	 */
	public String getInputName() {
		return inputName;
	}
	/**
	 * @param inputName the inputName to set
	 */
	public void setInputName(String inputName) {
		this.inputName = inputName;
	}
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
	
}
