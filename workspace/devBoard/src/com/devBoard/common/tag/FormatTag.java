package com.devBoard.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**  
 * @Class Name : FormatTag.java
 * @Description : FormatTag Class
 * @Modification Information  
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2012.12.11           최초생성
 * 
 * @author 송제승
 * @since 2012. 12.11
 * @version 1.0
 * @see
 * 
 *  Copyright (C) by Sinsuldong All right reserved.
 */
public class FormatTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5533820613893697968L;
	
	// ex) pattern : ####-##-##, value : 20080910 --> 2008-09-10
	private String pattern = "";
	private String value = "";
	
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		String tmp = "";
		StringBuffer result = new StringBuffer();
		int j = 0;
		
		try {
			for(int i=0; i<pattern.length(); i++) {
				tmp = pattern.substring(i, i+1);
				
				if(tmp.equals("#")) {
					if(value.length()-1>=j) {
						result.append(value.substring(j, j+1));
						j++;
					} else {
						break;
					}
				} else {
					result.append(tmp);
				}
			}
			
			out.print(result.toString());
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}

		return SKIP_BODY; // no body processing
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#release()
	 */
	public void release() {
    	// 필요시 로직 추가
    }

	/**
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * @param pattern the pattern to set
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
}
