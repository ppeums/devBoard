package com.devBoard.common.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Form;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.ValidatorResources;
import org.apache.commons.validator.Var;
import org.apache.commons.validator.util.ValidatorUtils;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.MessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springmodules.validation.commons.MessageUtils;
import org.springmodules.validation.commons.ValidatorFactory;
import org.springmodules.validation.commons.taglib.JavascriptValidatorTag;

/**
 * @Class Name : ExJavascriptValidatorTag.java
 * @Description : ExJavascriptValidatorTag.java Class
 *     multi-form 처리를 위해 JavascriptValidatorTag를 상속받아 다시 구현했다.
 *     form이 여러 개인 경우 서로 다른 form 간에 jsFunctionName 중복으로 인해 정상 작동하지 않는다.
 *     이를 해결하기 위해 validate[name] function 내부에서 jsFunctionName에 해당하는 function을 재정의하도록 하였다.
 *     
 *     - 수정 전 -----------------------------------------------------------------------------
 *     
 *     function validateEtcSampleVO2(form) {
 *         if (bCancel)
 *             return true;
 *         else
 *             return validateDate(form) && ...;
 *     }
 *
 *     function DateValidations () {
 *         this.aa = new Array("dateParam", "날짜 은 날짜 유형이 아닙니다.", new Function ("varName", "this.datePatternStrict='yyyyMMdd';  return this[varName];"));
 *     }
 *     
 *     - 수정 후 -----------------------------------------------------------------------------
 *     
 *     var DateValidations;
 *
 *     function validateEtcSampleVO2(form) {
 *         DateValidations = function () {
 *             this.aa = new Array("dateParam", "날짜 은 날짜 유형이 아닙니다.", new Function ("varName", "this.datePatternStrict='yyyyMMdd';  return this[varName];"));
 *         }
 *         
 *         ...
 *         
 *         if (bCancel)
 *             return true;
 *         else
 *             return validateDate(form) && ...;
 *     }
 *
 * @Modification Information
 * @
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2012. 7. 26.  송제승     최초생성
 * 
 * @author 송제승
 * @since 2012. 7. 26.
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Sinsuldong All right reserved.
 */
public class ExJavascriptValidatorTag extends JavascriptValidatorTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8301244300528528321L;

	private String htmlBeginComment;
    @SuppressWarnings("unused")
	private String htmlEndComment;
    
    /**
     * Default 생성자
     */
    public ExJavascriptValidatorTag() {
        super();
        
        htmlBeginComment = "\n<!-- Begin \n";
        htmlEndComment = "//End --> \n";
    }
    
	/* (non-Javadoc)
	 * @see org.springmodules.validation.commons.taglib.JavascriptValidatorTag#doStartTag()
	 */
	@SuppressWarnings("rawtypes")
	public int doStartTag() throws JspException {
		StringBuffer results = new StringBuffer();
        java.util.Locale locale = RequestContextUtils.getLocale((HttpServletRequest)pageContext.getRequest());
        ValidatorResources resources = getValidatorResources();
        Form form = resources.getForm(locale, formName);
        
        if(form != null) {
            if("true".equalsIgnoreCase(dynamicJavascript)) {
                MessageSource messages = getMessageSource();
                List<ValidatorAction> lActions = new ArrayList<ValidatorAction>();
                List<Object> lActionMethods = new ArrayList<Object>();
                
                for(Iterator i = form.getFields().iterator(); i.hasNext();) {
                    Field field = (Field)i.next();
                    Iterator x = field.getDependencyList().iterator();
                    
                    while(x.hasNext()) {
                        Object o = x.next();
                        if(o != null && !lActionMethods.contains(o)) lActionMethods.add(o);
                    }
                }

                for(Iterator i = lActionMethods.iterator(); i.hasNext();) {
                    String depends = (String)i.next();
                    ValidatorAction va = resources.getValidatorAction(depends);
                    
                    if(va == null) throw new NullPointerException("Depends string \"" + depends + "\" was not found in validator-rules.xml.");
                    
                    String javascript = va.getJavascript();
                    
                    if(javascript != null && javascript.length() > 0) lActions.add(va);
                    else i.remove();
                }

                // Collections.sort(lActions, new ValidatorActionComparator());
                
                StringBuffer jsFunctionNames = new StringBuffer();
                
                // jsFunctionName 선언
                for(Iterator i = lActions.iterator(); i.hasNext();) {
                	ValidatorAction va = (ValidatorAction)i.next();
                    String functionName = null;
                    
                    if(va.getJsFunctionName() != null && va.getJsFunctionName().length() > 0)
                        functionName = va.getJsFunctionName();
                    else
                        functionName = va.getName();
                    
                    jsFunctionNames.append("    var " + functionName + "; \n");
                }
                
                StringBuffer methods = null;
                
                for(Iterator i = lActions.iterator(); i.hasNext();) {
                    ValidatorAction va = (ValidatorAction)i.next();
                    
                    if(methods == null) {
                    	methods = new StringBuffer();
                    	methods.append(va.getMethod()).append("(form)");
                    } else {
                    	methods.append(" && ").append(va.getMethod()).append("(form)");
                    }
                }

                // jsFunctionName Define
                StringBuffer jsFunctionNameDefines = new StringBuffer();
                
                for(Iterator i = lActions.iterator(); i.hasNext(); jsFunctionNameDefines.append("        }; \n\n")) {
                    ValidatorAction va = (ValidatorAction)i.next();
                    String jscriptVar = null;
                    String functionName = null;
                    
                    if(va.getJsFunctionName() != null && va.getJsFunctionName().length() > 0)
                        functionName = va.getJsFunctionName();
                    else
                        functionName = va.getName();
                    
                    jsFunctionNameDefines.append("        " + functionName + " = function () { \n");
                    Iterator x = form.getFields().iterator();
                    
                    do {
                        if(!x.hasNext()) break;
                        
                        Field field = (Field)x.next();
                        
                        if(!field.isIndexed() && field.getPage() == page && field.isDependency(va.getName())) {
                            String message = MessageUtils.getMessage(messages, locale, va, field);
                            message = message == null ? "" : message;
                            jscriptVar = getNextVar(jscriptVar);
                            jsFunctionNameDefines.append("            this." + jscriptVar + " = new Array(\"" + field.getKey() + "\", \"" + message + "\", ");
                            jsFunctionNameDefines.append("new Function (\"varName\", \"");
                            Map vars = field.getVars();
                            Iterator varsIterator = vars.keySet().iterator();
                            
                            do {
                                if(!varsIterator.hasNext()) break;
                                
                                String varName = (String)varsIterator.next();
                                Var var = (Var)vars.get(varName);
                                String varValue = var.getValue();
                                String jsType = var.getJsType();
                                
                                if(!varName.startsWith("field")) {
                                    if("int".equalsIgnoreCase(jsType))
                                        jsFunctionNameDefines.append("this." + varName + "=" + ValidatorUtils.replace(varValue, "\\", "\\\\") + "; ");
                                    else
                                    if("regexp".equalsIgnoreCase(jsType))
                                        jsFunctionNameDefines.append("this." + varName + "=/" + ValidatorUtils.replace(varValue, "\\", "\\\\") + "/; ");
                                    else
                                    if("string".equalsIgnoreCase(jsType))
                                        jsFunctionNameDefines.append("this." + varName + "='" + ValidatorUtils.replace(varValue, "\\", "\\\\") + "'; ");
                                    else
                                    if("mask".equalsIgnoreCase(varName))
                                        jsFunctionNameDefines.append("this." + varName + "=/" + ValidatorUtils.replace(varValue, "\\", "\\\\") + "/; ");
                                    else
                                        jsFunctionNameDefines.append("this." + varName + "='" + ValidatorUtils.replace(varValue, "\\", "\\\\") + "'; ");
                                }
                            } while(true);
                            
                            jsFunctionNameDefines.append(" return this[varName];\"));\n");
                        }
                    } while(true);
                }
                
                results.append(getJavascriptBegin(methods.toString(), jsFunctionNames.toString(), jsFunctionNameDefines.toString()));
            } else {
	            if("true".equalsIgnoreCase(staticJavascript)) {
	                results.append(getStartElement());
	                
	                if("true".equalsIgnoreCase(htmlComment)) results.append(htmlBeginComment);
	            }
            }
        }
        
        if("true".equalsIgnoreCase(staticJavascript)) {
            results.append(getJavascriptStaticMethods(resources));
        }
        if(form != null && ("true".equalsIgnoreCase(dynamicJavascript) || "true".equalsIgnoreCase(staticJavascript))) {
            results.append(getJavascriptEnd());
        }
        
        JspWriter writer = pageContext.getOut();
        
        try {
            writer.print(results.toString());
        } catch(IOException e) {
            throw new JspException(e.getMessage());
        }
		
		return SKIP_BODY; // no body processing
	}
	
	/* (non-Javadoc)
	 * @see org.springmodules.validation.commons.taglib.JavascriptValidatorTag#release()
	 */
	public void release() {
    	super.release();
    }
    
    private String getJavascriptBegin(String methods, String jsFunctionNames, String jsFunctionNameDefines) {
        StringBuffer result = new StringBuffer();
        String name = formName.substring(0, 1).toUpperCase() + formName.substring(1, formName.length());
        result.append(getStartElement());
        
        if(isXhtml() && "true".equalsIgnoreCase(cdata)) result.append("//<![CDATA[\r\n");
        if(!isXhtml() && "true".equals(htmlComment)) result.append(htmlBeginComment);
        
        result.append("\n    var bCancel = false; \n\n");
        
        // jsFunctionName 선언 추가
        result.append(jsFunctionNames).append("\n");
        
        if(methodName == null || methodName.length() == 0)
            result.append("    function validate" + name + "(form) { \n");
        else
            result.append("    function " + methodName + "(form) { \n");
        
        // jsFunctionName Define 추가
        result.append(jsFunctionNameDefines).append("\n");
        
        result.append("        if (bCancel) \n");
        result.append("            return true; \n");
        result.append("        else \n");
        
        if(methods == null || methods.length() == 0)
            result.append("            return true; \n");
        else
            result.append("            return " + methods + "; \n");
        
        result.append("   } \n");
        
        return result.toString();
    }
    
    private ValidatorResources getValidatorResources() {
    	WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(
    		pageContext.getServletContext());
    	
        ValidatorFactory factory = (ValidatorFactory)BeanFactoryUtils.beanOfTypeIncludingAncestors(wac, org.springmodules.validation.commons.ValidatorFactory.class, true, true);
        
        return factory.getValidatorResources();
    }
    
    private MessageSource getMessageSource() {
    	WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(
			pageContext.getServletContext());
        
        return (MessageSource)wac.getBean("messageSource");
    }
    
    private String getStartElement() {
        StringBuffer start = new StringBuffer("<script type=\"text/javascript\"");
        
        if(!isXhtml()) start.append(" language=\"Javascript1.1\"");
        
        if(src != null) start.append(" src=\"" + src + "\"");
        
        start.append("> \n");
        
        return start.toString();
    }
    
    private boolean isXhtml() {
        return "true".equalsIgnoreCase(xhtml);
    }
    
    private String getNextVar(String input) {
        if(input == null) return "aa";
        
        input = input.toLowerCase();
        
        for(int i = input.length(); i > 0; i--) {
            int pos = i - 1;
            char c = input.charAt(pos);
            c++;
            
            if(c <= 'z') {
                if(i == 0) return c + input.substring(pos, input.length());
                
                if(i == input.length())
                    return input.substring(0, pos) + c;
                else
                    return input.substring(0, pos) + c + input.substring(pos, input.length() - 1);
            }
            
            input = replaceChar(input, pos, 'a');
        }

        return null;
    }
    
    private String replaceChar(String input, int pos, char c) {
        if(pos == 0) return c + input.substring(pos, input.length());
        
        if(pos == input.length())
            return input.substring(0, pos) + c;
        else
            return input.substring(0, pos) + c + input.substring(pos, input.length() - 1);
    }

}
