package com.devBoard.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class TitleTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4654284178377154823L;

	private String type = "";
	private String text = "";
	private String subTitleId = "";

	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		StringBuffer html = new StringBuffer();
		
		// String menuTitle = pageContext.getRequest().getParameter("menuTitle");
		// String menuPath = pageContext.getRequest().getParameter("menuPath");
		
		try {
			// Main Title
			if("main".equals(this.getType())) {
				html.append("<div id='title' class='maintitle'>")
				    .append("<ul>")
				    .append("<li><img src='/static/images/egovframework/rte/title_dot.gif'>")
				    .append(" ").append(this.getText())
				    .append("</li>")
				    .append("</ul>")
				    .append("</div>");
			}
			// Sub Title
			else if("sub".equals(this.getType())) {
				html.append("<p id='" + this.getSubTitleId() + "' class='subtitle'>")
				    .append("■ ").append(this.getText())
				    .append("</p>");
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
    	text = "";
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
	 * @return the subTitleId
	 */
	public String getSubTitleId() {
		return subTitleId;
	}

	/**
	 * @param subTitleId the subTitleId to set
	 */
	public void setSubTitleId(String subTitleId) {
		this.subTitleId = subTitleId;
	}
	
}
