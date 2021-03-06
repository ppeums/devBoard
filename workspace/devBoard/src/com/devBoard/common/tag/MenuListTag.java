package com.devBoard.common.tag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.devBoard.common.security.UserSession;
import com.devBoard.common.vo.MenuVO;

/**  
 * @Class Name : MenuListTag.java
 * @Description : 메뉴 목록 처리를 위한 Tag
 * @Modification Information  
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2012.12.12           송제승
 * 
 * @author 송제승
 * @since 2012. 12.12
 * @version 1.0
 * @see
 * 
 *  Copyright (C) by Sinsuldong All right reserved.
 */
public class MenuListTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -185258745370857416L;
	
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		
		if( UserSession.getUserMenuList((HttpServletRequest)pageContext.getRequest()) == null) {
			try {
				String blank = "<br>&emsp;&emsp;&ensp;로그인이 필요합니다.";
				out.print(blank);
			} catch (Exception e) {
				throw new JspException(e.getMessage());
			}
		} else {
			String strMenu = " <div class=\"top\"></div>"            
			+ "<div class=\"nav_style\">"
			+ "<ul>"
			+ "<li class=\"leftmenu_dept01\">";
			
			try {
				for( int i = 0; i < UserSession.getUserMenuList((HttpServletRequest)pageContext.getRequest()).size(); i++) {
					MenuVO menuVO = ((List<MenuVO>)UserSession.getUserMenuList((HttpServletRequest)pageContext.getRequest())).get(i);
					if( menuVO.getParentId().equals("0")) {
						if( i == 0 ) {
							strMenu += "<a href=\"#\">" + menuVO.getMenuNameKor() + "</a><ul>";
//							strMenu += "<a href=\"#\">" + menuVO.getMenuNameEng() + "</a><ul>";
						} else if( i == UserSession.getUserMenuList((HttpServletRequest)pageContext.getRequest()).size() - 1 ) {
							strMenu += "<a href=\"#\">" + menuVO.getMenuNameKor() + "</a></ul>";
//							strMenu += "<a href=\"#\">" + menuVO.getMenuNameEng() + "</a></ul>";
						} else {
							strMenu += "</ul><a href=\"#\">" + menuVO.getMenuNameKor() + "</a><ul>";
//							strMenu += "</ul><a href=\"#\">" + menuVO.getMenuNameEng() + "</a><ul>";
						}
					} else {
						if( menuVO.getMenuUrl().equals("javascript:alert('준비중 입니다.');") )
							strMenu += "<li class=\"dept02\"><a href=\"" + menuVO.getMenuUrl() + "\" >" + menuVO.getMenuNameKor() + "</a></li>";
						else
							strMenu += "<li class=\"dept02\"><a href=\"#a\" onclick=\"javascript:fn_moveMenu('" + menuVO.getMenuUrl() + "')\">" + menuVO.getMenuNameKor() + "</a></li>";
//						strMenu += "<li class=\"dept02\"><a href=\"" + menuVO.getMenuUrl() + "\">" + menuVO.getMenuNameEng() + "</a></li>";
					}
				}
				
				strMenu += "</li>"
				+ "</ul>"
				+ "</div>"
				+ "<div class=\"bottom\"></div><script>function fn_moveMenu(targetUrl){var frm = $(\"#menuForm\");"
				+ "frm.attr(\"action\", targetUrl);"
				+ "frm.submit();}</script><form name=\"menuForm\" id=\"menuForm\"></form>";
				out.print(strMenu);
				
			} catch (Exception e) {
				throw new JspException(e.getMessage());
			}
		}
		return SKIP_BODY; // no body processing
	}

	// Releases any resources we may have (or inherit)
    public void release() {
    	super.release();
    }

}
