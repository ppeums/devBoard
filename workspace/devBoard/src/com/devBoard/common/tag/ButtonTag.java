package com.devBoard.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class ButtonTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3471415957411556486L;
	
	private String type = "";
	private String path = "";
	private String text = "";
	private String onclick = "";
	private String targetId = "";

	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		StringBuffer html = new StringBuffer();
		
		try {
			if("search".equals(this.getType())) {
				html.append("<span id='" + this.getPath() + "' class='btn_blue_l'><a href='#' onclick=\"" + this.getOnclick() + ";\">조회</a><img src='/static/images/egovframework/rte/btn_bg_r.gif' style='margin-left:6px;'/></span>");
			} else if("list".equals(this.getType())) {
				html.append("<span id='" + this.getPath() + "' class='btn_blue_l'><a href='#' onclick=\"" + this.getOnclick() + ";\">목록</a><img src='/static/images/egovframework/rte/btn_bg_r.gif' style='margin-left:6px;'/></span>");
			} else if("insertView".equals(this.getType())) {
				html.append("<span id='" + this.getPath() + "' class='btn_blue_l'><a href='#' onclick=\"" + this.getOnclick() + ";\">등록</a><img src='/static/images/egovframework/rte/btn_bg_r.gif' style='margin-left:6px;'/></span>");
			} else if("save".equals(this.getType())) {
				html.append("<span id='" + this.getPath() + "' class='btn_blue_l'><a href='#' onclick=\"" + this.getOnclick() + ";\">저장</a><img src='/static/images/egovframework/rte/btn_bg_r.gif' style='margin-left:6px;'/></span>");
			} else if("updateView".equals(this.getType())) {
				html.append("<span id='" + this.getPath() + "' class='btn_blue_l'><a href='#' onclick=\"" + this.getOnclick() + ";\">수정</a><img src='/static/images/egovframework/rte/btn_bg_r.gif' style='margin-left:6px;'/></span>");
			} else if("update".equals(this.getType())) {
				html.append("<span id='" + this.getPath() + "' class='btn_blue_l'><a href='#' onclick=\"" + this.getOnclick() + ";\">저장</a><img src='/static/images/egovframework/rte/btn_bg_r.gif' style='margin-left:6px;'/></span>");
			} else if("delete".equals(this.getType())) {
				html.append("<span id='" + this.getPath() + "' class='btn_blue_l'><a href='#' onclick=\"" + this.getOnclick() + ";\">삭제</a><img src='/static/images/egovframework/rte/btn_bg_r.gif' style='margin-left:6px;'/></span>");
			} else if("reset".equals(this.getType())) {
				html.append("<span id='" + this.getPath() + "' class='btn_blue_l'><a href='#' onclick=\"" + this.getOnclick() + ";\">초기화</a><img src='/static/images/egovframework/rte/btn_bg_r.gif' style='margin-left:6px;'/></span>");
			} else if("print".equals(this.getType())) {
				html.append("<span id='" + this.getPath() + "' class='btn_blue_l'><a href='#' onclick=\"" + this.getOnclick() + ";\">출력</a><img src='/static/images/egovframework/rte/btn_bg_r.gif' style='margin-left:6px;'/></span>");
			} else if("close".equals(this.getType())) {
				html.append("<span id='" + this.getPath() + "' class='btn_blue_l'><a href='#' onclick=\"" + this.getOnclick() + ";\">닫기</a><img src='/static/images/egovframework/rte/btn_bg_r.gif' style='margin-left:6px;'/></span>");
			} else if("previous".equals(this.getType())) {
				html.append("<span id='" + this.getPath() + "' class='btn_blue_l'><a href='#' onclick=\"" + this.getOnclick() + ";\">이전</a><img src='/static/images/egovframework/rte/btn_bg_r.gif' style='margin-left:6px;'/></span>");
			} else if("next".equals(this.getType())) {
				html.append("<span id='" + this.getPath() + "' class='btn_blue_l'><a href='#' onclick=\"" + this.getOnclick() + ";\">다음</a><img src='/static/images/egovframework/rte/btn_bg_r.gif' style='margin-left:6px;'/></span>");
			}else if("iconSearch".equals(this.getType())) {
				html.append("<input type='image' id='" + this.getPath() + "' class='Limage' src='/images/btn_icon_search.gif' alt='검색' onclick=\""+ this.getOnclick() +"; return false;\" />");
			} else if("addRow".equals(this.getType())) {
				html.append("<a id='" + this.getPath() + "' class='Lbtn3' href='#'><span onclick=\"" + this.getOnclick() + "; return false;\">행추가</span></a>");
			} else if("deleteRow".equals(this.getType())) {
				html.append("<a id='" + this.getPath() + "' class='Lbtn3' href='#'><span onclick=\"" + this.getOnclick() + "; return false;\">행삭제</span></a>");
			} else if("iconModify".equals(this.getType())) {
				html.append("<a id='" + this.getPath() + "' class='Lbtn3' href='#'><span onclick=\"" + this.getOnclick() + "; return false;\">수정</span></a>");
			} else if("downloadExcel".equals(this.getType())) {
				html.append("<a id='" + this.getPath() + "' class='Lbtn' href='#'><span onclick=\"" + this.getOnclick() + "; return false;\">엑셀 다운로드</span></a>");
			} else if("uploadExcel".equals(this.getType())) {
				html.append("<a id='" + this.getPath() + "' class='Lbtn' href='#'><span onclick=\"" + this.getOnclick() + "; return false;\">엑셀 업로드</span></a>");
			} else if("copy".equals(this.getType())) {
				html.append("<a id='" + this.getPath() + "' class='Lbtn3' href='#'><span onclick=\"" + this.getOnclick() + "; return false;\">복사</span></a>");
			} else if("iconGeneric".equals(this.getType())) {
				html.append("<a id='" + this.getPath() + "' class='Lbtn3' href='#'><span onclick=\"" + this.getOnclick() + "\">" + this.getText() + "</span></a>");
			}
			
			out.print(html.toString());
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}

		return SKIP_BODY; // no body processing
	}

    // Releases any resources we may have (or inherit)
    public void release() {
    	super.release();
    	
    	type = "";
    	path = "";
    	text = "";
    	onclick = "";
    	targetId = "";
    }

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the onclick
	 */
	public String getOnclick() {
		return onclick;
	}

	/**
	 * @param onclick the onclick to set
	 */
	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	/**
	 * @return the targetId
	 */
	public String getTargetId() {
		return targetId;
	}

	/**
	 * @param targetId the targetId to set
	 */
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	
}
