package com.devBoard.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class HeaderTag extends TagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7798372984335416141L;

	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		StringBuffer html = new StringBuffer();
		
		try {
			html.append("<!-- header 시작 -->");
			html.append("<div id=\"header_mainsize\">");
			html.append("<!-- 게시판 개발  로고 및 타이틀 시작 -->");
			html.append("<div id=\"logoarea\"><h1><a href=\"/login/retrieveLogin.xx\"><img src=\"/static/images/header/logo.jpg\" alt=\"게시판 개발  홈페이지\" height=\"30\" /></a></h1></div>");
			html.append("<div id=\"project_title\"><span class=\"maintitle\">게시판 개발  </span><strong> 홈페이지 </strong></div>");
			html.append("<div class=\"header_login\">");
			html.append("<div id=\"header_loginname\"><a href=\"#\" ></a></div>");
			html.append("<div class=\"header_loginconnection\"></div>");
			html.append("<ul class=\"login_bg_area\">");
			html.append("<li class=\"righttop_bgleft\">&nbsp;</li>");
			html.append("<li class=\"righttop_bgmiddle\"><a href=\"/login/retrieveLogin.xx\">로그인</a></li>");
			html.append("<li class=\"righttop_bgright\">&nbsp;</li>");
			html.append("</ul>");
			html.append("<ul class=\"login_bg_area\">");
			html.append("<li class=\"righttop_bgleft\">&nbsp;</li>");
			html.append("<li class=\"righttop_bgmiddle\"><a href=\"javascript:fn_logoutSubmit();\">로그아웃</a></li>");
			html.append("<li class=\"righttop_bgright\">&nbsp;</li>");
			html.append("</ul>");
			html.append("</div>");
			html.append("</div>");
			html.append("<div id=\"topnavi\"><ul></ul></div>");
			html.append("<!-- //header 끝 -->");
			html.append("<script type=\"text/javascript\">");
			html.append("function fn_logoutSubmit()");
			html.append("{");
			html.append("location.href=\"/login/retrieveLogout.xx\";");
			html.append("}");
			html.append("</script>");
			
			out.print(html.toString());
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}

		return SKIP_BODY; // no body processing
	}

    // Releases any resources we may have (or inherit)
    public void release() {
    	super.release();
    }

}
