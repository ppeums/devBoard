package com.devBoard.common.tag;

import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.support.BindStatus;
import org.springframework.web.servlet.support.JspAwareRequestContext;
import org.springframework.web.servlet.support.RequestContext;

/**  
 * @Class Name : ValidationErrorsTag.java
 * @Description : ValidationErrorsTag Class
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
public class ValidationErrorsTag extends TagSupport {
	private static final long serialVersionUID = -8527751283934087338L;
	
	/**
	 * {@link javax.servlet.jsp.PageContext} attribute for the
	 * page-level {@link RequestContext} instance.
	 */
	private static final String REQUEST_CONTEXT_PAGE_ATTRIBUTE = "org.springframework.web.servlet.tags.REQUEST_CONTEXT";
	
	private String formName;

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		RequestContext requestContext = (RequestContext)pageContext.getAttribute(REQUEST_CONTEXT_PAGE_ATTRIBUTE);
		
		if (requestContext == null) {
			requestContext = new JspAwareRequestContext(pageContext);
			pageContext.setAttribute(REQUEST_CONTEXT_PAGE_ATTRIBUTE, requestContext);
		}
		
		MessageSource messageSource = (MessageSource)requestContext.getWebApplicationContext().getBean("messageSource");
		
		try {
			StringBuffer html = new StringBuffer();
			
			BindStatus bindStatus = new BindStatus(requestContext, getFormName(), false);
			Errors errors = bindStatus.getErrors();
			
			if(errors.hasErrors()) {
				String errorMessage = getErrorMessage(errors.getAllErrors(), messageSource);
				
				html.append("<script type=\"text/javascript\">")
					.append("    $(document).ready(function() {")
					.append("        alert(\"").append(errorMessage).append("\");")
					.append("    });")
					.append("</script>");
			}
			
			pageContext.getOut().print(html.toString());
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}

		return SKIP_BODY; // no body processing
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#release()
	 */
	public void release() {
    	super.release();
    }
    
    private String getErrorMessage(List<ObjectError> errors, MessageSource messageSource) {
		StringBuffer result = new StringBuffer();
		FieldError error = null;
		
		for(int i=0; i<errors.size(); i++) {
			if(i!=0) result.append("\\n");
			
			error = (FieldError)errors.get(i);
			
			result.append(messageSource.getMessage(error.getDefaultMessage(), error.getArguments(), Locale.getDefault()));
		}
		
		return result.toString();
	}

	/**
	 * @return the formName
	 */
	public String getFormName() {
		return formName;
	}

	/**
	 * @param formName the formName to set
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}

}
