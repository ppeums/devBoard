package com.devBoard.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class CalendarTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6718485188695284653L;
	
	private String path = "";
	private String onclick = "";
	private String targetId = "";

	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		StringBuffer html = new StringBuffer();
		
		try {
			html.append("<span id='" + this.getPath() + "' class='btn_calendar'><img src='/static/images/calendar/ic_calendar.gif' onclick=\"gfn_openCalendar('" + this.getTargetId() + "', event);\"></span>");
			
			out.print(html.toString());
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}

		return SKIP_BODY; // no body processing
	}

    // Releases any resources we may have (or inherit)
    public void release() {
    	super.release();
    	
    	path = "";
    	onclick = "";
    	targetId = "";
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
