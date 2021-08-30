package com.devBoard.comBbs.vo;

import com.devBoard.framework.vo.CommonVO;

/**  
 * @Class Name : ComBbsCommentVO
 * @Description : 댓글을 처리하는 VO Class
 * @Modification Information  
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2014. 2. 25 eduBoard          최초생성
 * 
 * @author eduBoard 
 * @since 2014. 8. 25.
 * @version 1.0
 * @see
 * 
 *  Copyright (C) by devBoard All right reserved.
 */
public class ComBbsCommentVO extends CommonVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3181017570505674488L;
	
	private Integer comBbsSeq;          // 게시판번호
	private int comBbsCommentSeq;   // 게시판댓글번호
	private String writeId;         // 작성자ID
	private String commentContent;  // 댓글내용
	private String useYn;           // 사용여부
	private String rgstId;          // 등록자ID
	private String rgstDt;          // 등록일

	public Integer getComBbsSeq() {
		return comBbsSeq;
	}
	public void setComBbsSeq(Integer comBbsSeq) {
		this.comBbsSeq = comBbsSeq;
	}
	public int getComBbsCommentSeq() {
		return comBbsCommentSeq;
	}
	public void setComBbsCommentSeq(int comBbsCommentSeq) {
		this.comBbsCommentSeq = comBbsCommentSeq;
	}
	public String getWriteId() {
		return writeId;
	}
	public void setWriteId(String writeId) {
		this.writeId = writeId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
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
	public String getRgstDt() {
		return rgstDt;
	}
	public void setRgstDt(String rgstDt) {
		this.rgstDt = rgstDt;
	}
	
}
