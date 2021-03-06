package com.devBoard.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.devBoard.framework.util.DateUtil;
import com.devBoard.framework.util.StringUtil;

import org.apache.log4j.Logger;

public class DateFormatTag extends TagSupport {
	

	private static final long serialVersionUID = 8689139658477278658L;

	Logger logger = Logger.getLogger(this.getClass());
	
	private String date;
	private String inputFmt;
	private String outputFmt;

	public int doStartTag() throws JspException {
		
		StringBuffer html = new StringBuffer();
			
    	try {
    		html.append(DateUtil.dateToString(DateUtil.stringToDate(date, inputFmt), outputFmt));
			pageContext.getOut().print(html.toString());
		} catch (IOException e) {
		}
    	
		return SKIP_BODY; // no body processing
	}

	// Releases any resources we may have (or inherit)
    public void release() {
    	super.release();
    }

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getInputFmt() {
		return inputFmt;
	}

	public void setInputFmt(String inputFmt) {
		this.inputFmt = inputFmt;
	}

	public String getOutputFmt() {
		return outputFmt;
	}

	public void setOutputFmt(String outputFmt) {
		this.outputFmt = outputFmt;
	}

	
}
