package com.devBoard.comBbs.vo;

import java.util.Date;

import com.devBoard.framework.vo.CommonVO;

/**  
 * @Class Name : ComBbsVO.java
 * @Description : 답변형게시판을 처리하는 VO Class
 * @Modification Information  
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2014. 2. 6. 송제승          최초생성
 * 
 * @author 송제승
 * @since 2014. 2. 6.
 * @version 1.0
 * @see
 * 
 *  Copyright (C) 2014 by Sinsuldong All right reserved.
 */
public class ComBbsVO extends CommonVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6962495561796302755L;
	private int comBbsSeq          ; // 게시판번호
	private String nttTitle        ; // 게시물제목
	private String nttContent      ; // 게시물내용
	private String prntsNo         ; // 부모글번호
	private String replyLoc        ; // 답변위치
	private String sortOrder       ; // 정렬순서
	private String useYn           ; // 사용여부
	private String rgstId          ; // 등록자ID
	private Date rgstDt            ; // 등록일
	private String updId           ; // 수정자ID
	private String updDt           ; // 수정일
	private int comCheck		   ; // 댓글개수
	private int comFileCnt		   ; // 첨부파일개수
	private int viewCnt			   ; // 조회수
	
	public int getComBbsSeq() {
		return comBbsSeq;
	}
	public void setComBbsSeq(int comBbsSeq) {
		this.comBbsSeq = comBbsSeq;
	}
	public String getNttTitle() {
		return nttTitle;
	}
	public void setNttTitle(String nttTitle) {
		this.nttTitle = nttTitle;
	}
	public String getNttContent() {
		return nttContent;
	}
	public void setNttContent(String nttContent) {
		this.nttContent = nttContent;
	}
	public String getPrntsNo() {
		return prntsNo;
	}
	public void setPrntsNo(String prntsNo) {
		this.prntsNo = prntsNo;
	}
	public String getReplyLoc() {
		return replyLoc;
	}
	public void setReplyLoc(String replyLoc) {
		this.replyLoc = replyLoc;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRgstId() {
		return rgstId;
	}
	public void setRgstId(String rgstId) {
		this.rgstId = rgstId;
	}
	public Date getRgstDt() {
		return rgstDt;
	}
	public void setRgstDt(Date rgstDt) {
		this.rgstDt = rgstDt;
	}
	public String getUpdId() {
		return updId;
	}
	public void setUpdId(String updId) {
		this.updId = updId;
	}
	public String getUpdDt() {
		return updDt;
	}
	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}
	public int getComCheck() {
		return comCheck;
	}
	public void setComCheck(int comCheck) {
		this.comCheck = comCheck;
	}
	public int getComFileCnt() {
		return comFileCnt;
	}
	public void setComFileCnt(int comFileCnt) {
		this.comFileCnt = comFileCnt;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	
}
