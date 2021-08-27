package com.devBoard.common.tag;

import java.io.File;
import java.util.StringTokenizer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.devBoard.framework.config.ExXMLConfiguration;
import com.devBoard.framework.util.FileUtil;

import org.apache.log4j.Logger;

public class ButtonTag2 extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4654284178377154823L;

	private String type = "";
	private String title = "";
	private String onclick = "";
	
	private String params = "";
	private String delim = "";

	Logger logger = Logger.getLogger(this.getClass());

	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		
		try {
			
			// Template File Load..
			ExXMLConfiguration conf = ExXMLConfiguration.getInstance();
			String ttRoot = System.getProperty("waspath");
			String tFilePath = ttRoot + File.separator + "WEB-INF" + File.separator + "config" + File.separator + "tag_template" + File.separator + "button" + File.separator + type + ".html";
			
			String fileContent = FileUtil.getFileContent(tFilePath);

			// Parameter Setting
			fileContent = fileContent.replace("${title}", title);
			fileContent = fileContent.replace("${onclick}", onclick);

			// additional Parameter Parsing
			if(params != null && params.length() != 0){
				StringTokenizer st = new StringTokenizer(params, delim);
				int seq = 1;
				while(st.hasMoreTokens()){
					String pVal = st.nextToken();
					fileContent = fileContent.replace("${@p" + seq++ + "}", pVal);
				}
			}
			
			out.print(fileContent);
			
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}

		return SKIP_BODY; // no body processing
	}

	// Releases any resources we may have (or inherit)
    public void release() {
    	super.release();
    	
    	type = "";
    	title = "";
    	onclick = "";
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getDelim() {
		return delim;
	}

	public void setDelim(String delim) {
		this.delim = delim;
	}



}
