package com.devBoard.sample.vo;

import com.devBoard.framework.vo.CommonVO;

/**
 * @Class Name : SampleVO.java
 * @Description : 샘플게시판을 처리하는 VO Class
 * @Modification Information  
 * @
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2012.12.11  송제승     최초생성
 * 
 * @author 송제승
 * @since 2012.12.11
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Sinsuldong All right reserved.
 */
public class SampleVO extends CommonVO {
	
	private static final long serialVersionUID = 1L;
	
	/** 아이디 */
	private int id;
	
	/** 이름 */
	private String name;
	
	/** 내용 */
	private String description;
	
	/** 사용여부 */
	private String useYn;
	
	/** 등록자 */
	private String regUser;
	
	private String regDate;
	
	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getRegUser() {
		return regUser;
	}

	public void setRegUser(String regUser) {
		this.regUser = regUser;
	}

}
