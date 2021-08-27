package com.devBoard.common.tag;

import java.io.File;
import java.util.StringTokenizer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.devBoard.framework.config.ExXMLConfiguration;
import com.devBoard.framework.util.FileUtil;

import org.apache.log4j.Logger;

public class TitleTag2 extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4654284178377154823L;

	private String type = "";
	private String params = "";
	private String delim = "";
	
	Logger logger = Logger.getLogger(this.getClass());

	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		
		try {
			// Template File Load..
			ExXMLConfiguration conf = ExXMLConfiguration.getInstance();
			String ttRoot = System.getProperty("waspath");
			String tFilePath = ttRoot + File.separator + File.separator + "WEB-INF" + File.separator + "config" + File.separator + "tag_template" + File.separator + "title" + File.separator + type + ".html";
			
			String fileContent = FileUtil.getFileContent(tFilePath);

			// Parameter Parsing
			StringTokenizer st = new StringTokenizer(params, delim);
			int seq = 1;
			while(st.hasMoreTokens()){
				String pVal = st.nextToken();
				fileContent = fileContent.replace("${@p" + seq++ + "}", pVal);
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
    	params = "";
    	delim = "";
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
