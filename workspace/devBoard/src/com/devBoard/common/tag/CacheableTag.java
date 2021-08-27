package com.devBoard.common.tag;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**  
 * @Class Name : CacheableTag.java
 * @Description : CacheableTag Class
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
public class CacheableTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5533820613893697968L;
	
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		HttpServletResponse response = (HttpServletResponse)pageContext.getResponse();
		
		response.setHeader("Cache-Control", "");
		response.setHeader("Pragma", "");
		response.setHeader("Expires", "");

		return SKIP_BODY; // no body processing
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#release()
	 */
	public void release() {
    	// 필요시 로직 추가
    }
	
}
