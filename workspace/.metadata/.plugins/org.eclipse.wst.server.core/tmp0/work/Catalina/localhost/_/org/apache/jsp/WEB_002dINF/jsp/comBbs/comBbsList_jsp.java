package org.apache.jsp.WEB_002dINF.jsp.comBbs;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class comBbsList_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/WEB-INF/tld/lw.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005flw_005fcacheable_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005flw_005fheader_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005flw_005fmenuList_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005flw_005ftitle2_005ftype_005fparams_005fdelim_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fform_005fform_005fmethod_005fcommandName;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fform_005fhidden_005fpath_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fform_005fselect_005fpath_005fcssClass;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fform_005foption_005fvalue_005flabel_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fform_005finput_005fpath_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005flw_005fcalendarTag2_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fform_005fselect_005fpath_005fonchange_005fcssClass;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_005fvarStatus_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fui_005fpagination_005ftype_005fpaginationInfo_005fjsFunction_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005flw_005fcacheable_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005flw_005fheader_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005flw_005fmenuList_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005flw_005ftitle2_005ftype_005fparams_005fdelim_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fform_005fform_005fmethod_005fcommandName = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fform_005fhidden_005fpath_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fform_005fselect_005fpath_005fcssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fform_005foption_005fvalue_005flabel_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fform_005finput_005fpath_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005flw_005fcalendarTag2_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fform_005fselect_005fpath_005fonchange_005fcssClass = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_005fvarStatus_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fui_005fpagination_005ftype_005fpaginationInfo_005fjsFunction_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005flw_005fcacheable_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005flw_005fheader_005fnobody.release();
    _005fjspx_005ftagPool_005flw_005fmenuList_005fnobody.release();
    _005fjspx_005ftagPool_005flw_005ftitle2_005ftype_005fparams_005fdelim_005fnobody.release();
    _005fjspx_005ftagPool_005fform_005fform_005fmethod_005fcommandName.release();
    _005fjspx_005ftagPool_005fform_005fhidden_005fpath_005fnobody.release();
    _005fjspx_005ftagPool_005fform_005fselect_005fpath_005fcssClass.release();
    _005fjspx_005ftagPool_005fform_005foption_005fvalue_005flabel_005fnobody.release();
    _005fjspx_005ftagPool_005fform_005finput_005fpath_005fnobody.release();
    _005fjspx_005ftagPool_005flw_005fcalendarTag2_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fform_005fselect_005fpath_005fonchange_005fcssClass.release();
    _005fjspx_005ftagPool_005fc_005fforEach_005fvarStatus_005fvar_005fitems.release();
    _005fjspx_005ftagPool_005fui_005fpagination_005ftype_005fpaginationInfo_005fjsFunction_005fnobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

/**
 * @Class Name : comBbsList.jsp
 * @Description : 답글게시판 목록 화면
 * @Modification Information
 *
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2013.12.11  eduBoard     최초생성
 *
 * @author eduBoard
 * @since 2013.12.11
 * @version 1.0
 * @see
 *
 * Copyright (C) 2013 by devBoard All right reserved.
 */

      out.write('\r');
      out.write('\n');
      if (_jspx_meth_lw_005fcacheable_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Language\" content=\"utf-8\" >\r\n");
      out.write("<title>답글게시판 - 답글게시판</title>\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/jsp/common/header.jsp", out, true);
      out.write("\r\n");
      out.write("<script type=\"text/javaScript\" language=\"javascript\" defer=\"defer\">\r\n");
      out.write("<!--\r\n");
      out.write("/* 글 조회 화면 function */\r\n");
      out.write("function fn_retrieveComBBsView(id) {\r\n");
      out.write("\tvar frm = $(\"#comBbsVO\");\r\n");
      out.write("\r\n");
      out.write("\t$(\"#selectedId\").val(id);\r\n");
      out.write("\r\n");
      out.write("\tfrm.attr(\"action\", \"");
      if (_jspx_meth_c_005furl_005f0(_jspx_page_context))
        return;
      out.write("\");\r\n");
      out.write("\tfrm.submit();\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/* 글 등록 화면 function */\r\n");
      out.write("function fn_insertComBbsReg() {\r\n");
      out.write("\tvar frm = $(\"#comBbsVO\");\r\n");
      out.write("\r\n");
      out.write("\tfrm.attr(\"action\", \"");
      if (_jspx_meth_c_005furl_005f1(_jspx_page_context))
        return;
      out.write("\");\r\n");
      out.write("\tfrm.submit();\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/* pagination 페이지 링크 function */\r\n");
      out.write("function fn_retrieveComBbsList(pageNo){\r\n");
      out.write("\tvar frm = $(\"#comBbsVO\");\r\n");
      out.write("\r\n");
      out.write("\t$(\"#pageIndex\").val(pageNo);\r\n");
      out.write("\r\n");
      out.write("\tfrm.attr(\"action\", \"");
      if (_jspx_meth_c_005furl_005f2(_jspx_page_context))
        return;
      out.write("\");\r\n");
      out.write("\tfrm.submit();\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//엔터키 입력 \r\n");
      out.write("$(function() {\r\n");
      out.write("\t$(\"#searchKeyword\").keypress(function (e) { \r\n");
      out.write("\t    if (e.which == 13) { \r\n");
      out.write("\t    \tfn_retrieveComBbsList('1');\r\n");
      out.write("\t    \treturn false; \r\n");
      out.write("\t    }\r\n");
      out.write("\t}); \r\n");
      out.write("});\r\n");
      out.write("-->\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<!-- 전체 레이어 시작 -->\r\n");
      out.write("<div id=\"wrap\">\r\n");
      out.write("    ");
      if (_jspx_meth_lw_005fheader_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("    <!-- container 시작 -->\r\n");
      out.write("    <div id=\"container\">\r\n");
      out.write("        <!-- 좌측메뉴 시작 -->\r\n");
      out.write("        <div id=\"leftmenu\">\r\n");
      out.write("\t\t\t<div id=\"nav\">");
      if (_jspx_meth_lw_005fmenuList_005f0(_jspx_page_context))
        return;
      out.write("</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("        <!-- //좌측메뉴 끝 -->\t\r\n");
      out.write("\t\t<!-- content 시작 -->\t\t\t\r\n");
      out.write("\t\t<div id=\"content\">\r\n");
      out.write("\t\t\t<!-- 현재위치 네비게이션 시작 -->\r\n");
      out.write("\t\t\t<!-- 타이틀 -->\r\n");
      out.write("\t\t\t");
      if (_jspx_meth_lw_005ftitle2_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t\t<!-- //현재위치 네비게이션 끝 -->\r\n");
      out.write("\t\t\t<!-- 타이틀 이미지 -->\t\t\t\r\n");
      out.write("\t\t\t<div id=\"content_img_div\"><img src=\"/static/images/subtitle/img_subtitle03-02.gif\" width=\"776\" height=\"230\" /></div>\r\n");
      out.write("\t\t\t<!-- main content 시작 -->\r\n");
      out.write("\t\t\t<div class=\"content_field\"><h2>방명록</h2></div>\r\n");
      out.write("\t\t\t<!-- //main content 끝 -->\t\t\t\r\n");
      out.write("\t\t\t");
      //  form:form
      org.springframework.web.servlet.tags.form.FormTag _jspx_th_form_005fform_005f0 = (org.springframework.web.servlet.tags.form.FormTag) _005fjspx_005ftagPool_005fform_005fform_005fmethod_005fcommandName.get(org.springframework.web.servlet.tags.form.FormTag.class);
      _jspx_th_form_005fform_005f0.setPageContext(_jspx_page_context);
      _jspx_th_form_005fform_005f0.setParent(null);
      // /WEB-INF/jsp/comBbs/comBbsList.jsp(95,3) name = commandName type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_form_005fform_005f0.setCommandName("comBbsVO");
      // /WEB-INF/jsp/comBbs/comBbsList.jsp(95,3) name = method type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_form_005fform_005f0.setMethod("post");
      int[] _jspx_push_body_count_form_005fform_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_form_005fform_005f0 = _jspx_th_form_005fform_005f0.doStartTag();
        if (_jspx_eval_form_005fform_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("\t\t\t");
            if (_jspx_meth_form_005fhidden_005f0(_jspx_th_form_005fform_005f0, _jspx_page_context, _jspx_push_body_count_form_005fform_005f0))
              return;
            out.write("\t \r\n");
            out.write("\t\t\t<!-- 검색 필드 박스 시작 -->\t\t\t\t \r\n");
            out.write("\t\t\t<div id=\"search_field\">\r\n");
            out.write("\t\t\t  \t<fieldset><legend>조건정보 영역</legend>\t  \r\n");
            out.write("\t\t\t  \t<div class=\"sf_start\">\r\n");
            out.write("\t\t\t  \t\t<ul id=\"search_first_ul\">\r\n");
            out.write("\t\t\t  \t\t\t<li>\r\n");
            out.write("\t\t\t  \t\t\t\t<div class=\"search_leftselect\">\r\n");
            out.write("\t\t\t\t\t\t\t");
            //  form:select
            org.springframework.web.servlet.tags.form.SelectTag _jspx_th_form_005fselect_005f0 = (org.springframework.web.servlet.tags.form.SelectTag) _005fjspx_005ftagPool_005fform_005fselect_005fpath_005fcssClass.get(org.springframework.web.servlet.tags.form.SelectTag.class);
            _jspx_th_form_005fselect_005f0.setPageContext(_jspx_page_context);
            _jspx_th_form_005fselect_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
            // /WEB-INF/jsp/comBbs/comBbsList.jsp(104,7) name = path type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
            _jspx_th_form_005fselect_005f0.setPath("searchCondition");
            // /WEB-INF/jsp/comBbs/comBbsList.jsp(104,7) name = cssClass type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
            _jspx_th_form_005fselect_005f0.setCssClass("use");
            int[] _jspx_push_body_count_form_005fselect_005f0 = new int[] { 0 };
            try {
              int _jspx_eval_form_005fselect_005f0 = _jspx_th_form_005fselect_005f0.doStartTag();
              if (_jspx_eval_form_005fselect_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                do {
                  out.write("\r\n");
                  out.write("\t\t\t\t\t\t\t\t");
                  //  form:option
                  org.springframework.web.servlet.tags.form.OptionTag _jspx_th_form_005foption_005f0 = (org.springframework.web.servlet.tags.form.OptionTag) _005fjspx_005ftagPool_005fform_005foption_005fvalue_005flabel_005fnobody.get(org.springframework.web.servlet.tags.form.OptionTag.class);
                  _jspx_th_form_005foption_005f0.setPageContext(_jspx_page_context);
                  _jspx_th_form_005foption_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fselect_005f0);
                  // /WEB-INF/jsp/comBbs/comBbsList.jsp(105,8) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_form_005foption_005f0.setValue(new String("1"));
                  // /WEB-INF/jsp/comBbs/comBbsList.jsp(105,8) name = label type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_form_005foption_005f0.setLabel("제목");
                  int[] _jspx_push_body_count_form_005foption_005f0 = new int[] { 0 };
                  try {
                    int _jspx_eval_form_005foption_005f0 = _jspx_th_form_005foption_005f0.doStartTag();
                    if (_jspx_th_form_005foption_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      return;
                    }
                  } catch (Throwable _jspx_exception) {
                    while (_jspx_push_body_count_form_005foption_005f0[0]-- > 0)
                      out = _jspx_page_context.popBody();
                    _jspx_th_form_005foption_005f0.doCatch(_jspx_exception);
                  } finally {
                    _jspx_th_form_005foption_005f0.doFinally();
                    _005fjspx_005ftagPool_005fform_005foption_005fvalue_005flabel_005fnobody.reuse(_jspx_th_form_005foption_005f0);
                  }
                  out.write("\r\n");
                  out.write("\t\t\t\t\t\t\t\t");
                  //  form:option
                  org.springframework.web.servlet.tags.form.OptionTag _jspx_th_form_005foption_005f1 = (org.springframework.web.servlet.tags.form.OptionTag) _005fjspx_005ftagPool_005fform_005foption_005fvalue_005flabel_005fnobody.get(org.springframework.web.servlet.tags.form.OptionTag.class);
                  _jspx_th_form_005foption_005f1.setPageContext(_jspx_page_context);
                  _jspx_th_form_005foption_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fselect_005f0);
                  // /WEB-INF/jsp/comBbs/comBbsList.jsp(106,8) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_form_005foption_005f1.setValue(new String("0"));
                  // /WEB-INF/jsp/comBbs/comBbsList.jsp(106,8) name = label type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_form_005foption_005f1.setLabel("등록자");
                  int[] _jspx_push_body_count_form_005foption_005f1 = new int[] { 0 };
                  try {
                    int _jspx_eval_form_005foption_005f1 = _jspx_th_form_005foption_005f1.doStartTag();
                    if (_jspx_th_form_005foption_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      return;
                    }
                  } catch (Throwable _jspx_exception) {
                    while (_jspx_push_body_count_form_005foption_005f1[0]-- > 0)
                      out = _jspx_page_context.popBody();
                    _jspx_th_form_005foption_005f1.doCatch(_jspx_exception);
                  } finally {
                    _jspx_th_form_005foption_005f1.doFinally();
                    _005fjspx_005ftagPool_005fform_005foption_005fvalue_005flabel_005fnobody.reuse(_jspx_th_form_005foption_005f1);
                  }
                  out.write("\r\n");
                  out.write("\t\t\t\t\t\t\t");
                  int evalDoAfterBody = _jspx_th_form_005fselect_005f0.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
              }
              if (_jspx_th_form_005fselect_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                return;
              }
            } catch (Throwable _jspx_exception) {
              while (_jspx_push_body_count_form_005fselect_005f0[0]-- > 0)
                out = _jspx_page_context.popBody();
              _jspx_th_form_005fselect_005f0.doCatch(_jspx_exception);
            } finally {
              _jspx_th_form_005fselect_005f0.doFinally();
              _005fjspx_005ftagPool_005fform_005fselect_005fpath_005fcssClass.reuse(_jspx_th_form_005fselect_005f0);
            }
            out.write("\r\n");
            out.write("\t\t\t\t\t\t\t</div>\t\t\t\t\t\r\n");
            out.write("\t\t\t  \t\t\t</li>\r\n");
            out.write("\t\t\t  \t\t\t<li><div class=\"inputbox_style01\">");
            if (_jspx_meth_form_005finput_005f0(_jspx_th_form_005fform_005f0, _jspx_page_context, _jspx_push_body_count_form_005fform_005f0))
              return;
            out.write("</div></li>\r\n");
            out.write("\t\t\t  \t\t\t<li>\r\n");
            out.write("\t\t  \t\t\t\t    <div class=\"buttons\" style=\"float:left;padding-left:2px;\">\r\n");
            out.write("\t\t\t\t\t\t\t<a href=\"#\" onclick=\"javascript:fn_retrieveComBbsList('1');\"><img src=\"/static/images/img_search.gif\" alt=\"search\" />검색 </a>\r\n");
            out.write("\t\t\t\t\t\t\t</div>\t  \t\t\r\n");
            out.write("\t\t\t  \t\t\t</li>\t\r\n");
            out.write("\t\t\t  \t\t</ul>\r\n");
            out.write("\t\t\t  \t</fieldset>\r\n");
            out.write("\t\t\t  \t<fieldset><legend>조건정보 영역</legend>\t  \r\n");
            out.write("\t\t\t  \t<div class=\"sf_start\">\r\n");
            out.write("\t\t\t  \t\t<ul id=\"search_first_ul\">\r\n");
            out.write("\t\t\t  \t\t\t");
            if (_jspx_meth_lw_005fcalendarTag2_005f0(_jspx_th_form_005fform_005f0, _jspx_page_context, _jspx_push_body_count_form_005fform_005f0))
              return;
            out.write("\r\n");
            out.write("\t\t\t  \t\t</ul>\r\n");
            out.write("\t\t\t  \t</fieldset>\r\n");
            out.write("\t\t\t</div> \r\n");
            out.write("\t\t\t<!-- //검색 필드 박스 끝 -->\r\n");
            out.write("\t\t\t<div id=\"page_info\">\r\n");
            out.write("\t\t\t\t<div id=\"page_info_align\">\r\n");
            out.write("\t\t\t\t\t[ <span class=\"orenge_s\">총 ");
            if (_jspx_meth_c_005fout_005f0(_jspx_th_form_005fform_005f0, _jspx_page_context, _jspx_push_body_count_form_005fform_005f0))
              return;
            out.write(" 건</span> (");
            if (_jspx_meth_c_005fout_005f1(_jspx_th_form_005fform_005f0, _jspx_page_context, _jspx_push_body_count_form_005fform_005f0))
              return;
            out.write(' ');
            out.write('/');
            out.write(' ');
            if (_jspx_meth_c_005fout_005f2(_jspx_th_form_005fform_005f0, _jspx_page_context, _jspx_push_body_count_form_005fform_005f0))
              return;
            out.write(" page) ]\r\n");
            out.write("\t\t\t\t\t");
            //  form:select
            org.springframework.web.servlet.tags.form.SelectTag _jspx_th_form_005fselect_005f1 = (org.springframework.web.servlet.tags.form.SelectTag) _005fjspx_005ftagPool_005fform_005fselect_005fpath_005fonchange_005fcssClass.get(org.springframework.web.servlet.tags.form.SelectTag.class);
            _jspx_th_form_005fselect_005f1.setPageContext(_jspx_page_context);
            _jspx_th_form_005fselect_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
            // /WEB-INF/jsp/comBbs/comBbsList.jsp(129,5) name = path type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
            _jspx_th_form_005fselect_005f1.setPath("recordCountPerPage");
            // /WEB-INF/jsp/comBbs/comBbsList.jsp(129,5) name = cssClass type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
            _jspx_th_form_005fselect_005f1.setCssClass("use");
            // /WEB-INF/jsp/comBbs/comBbsList.jsp(129,5) name = onchange type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
            _jspx_th_form_005fselect_005f1.setOnchange("fn_retrieveComBbsList('1')");
            int[] _jspx_push_body_count_form_005fselect_005f1 = new int[] { 0 };
            try {
              int _jspx_eval_form_005fselect_005f1 = _jspx_th_form_005fselect_005f1.doStartTag();
              if (_jspx_eval_form_005fselect_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
                do {
                  out.write("\r\n");
                  out.write("\t\t\t\t\t\t");
                  //  form:option
                  org.springframework.web.servlet.tags.form.OptionTag _jspx_th_form_005foption_005f2 = (org.springframework.web.servlet.tags.form.OptionTag) _005fjspx_005ftagPool_005fform_005foption_005fvalue_005flabel_005fnobody.get(org.springframework.web.servlet.tags.form.OptionTag.class);
                  _jspx_th_form_005foption_005f2.setPageContext(_jspx_page_context);
                  _jspx_th_form_005foption_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fselect_005f1);
                  // /WEB-INF/jsp/comBbs/comBbsList.jsp(130,6) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_form_005foption_005f2.setValue(new String("10"));
                  // /WEB-INF/jsp/comBbs/comBbsList.jsp(130,6) name = label type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_form_005foption_005f2.setLabel("10개씩 보기");
                  int[] _jspx_push_body_count_form_005foption_005f2 = new int[] { 0 };
                  try {
                    int _jspx_eval_form_005foption_005f2 = _jspx_th_form_005foption_005f2.doStartTag();
                    if (_jspx_th_form_005foption_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      return;
                    }
                  } catch (Throwable _jspx_exception) {
                    while (_jspx_push_body_count_form_005foption_005f2[0]-- > 0)
                      out = _jspx_page_context.popBody();
                    _jspx_th_form_005foption_005f2.doCatch(_jspx_exception);
                  } finally {
                    _jspx_th_form_005foption_005f2.doFinally();
                    _005fjspx_005ftagPool_005fform_005foption_005fvalue_005flabel_005fnobody.reuse(_jspx_th_form_005foption_005f2);
                  }
                  out.write("\r\n");
                  out.write("\t\t\t\t\t\t");
                  //  form:option
                  org.springframework.web.servlet.tags.form.OptionTag _jspx_th_form_005foption_005f3 = (org.springframework.web.servlet.tags.form.OptionTag) _005fjspx_005ftagPool_005fform_005foption_005fvalue_005flabel_005fnobody.get(org.springframework.web.servlet.tags.form.OptionTag.class);
                  _jspx_th_form_005foption_005f3.setPageContext(_jspx_page_context);
                  _jspx_th_form_005foption_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fselect_005f1);
                  // /WEB-INF/jsp/comBbs/comBbsList.jsp(131,6) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_form_005foption_005f3.setValue(new String("20"));
                  // /WEB-INF/jsp/comBbs/comBbsList.jsp(131,6) name = label type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_form_005foption_005f3.setLabel("20개씩 보기");
                  int[] _jspx_push_body_count_form_005foption_005f3 = new int[] { 0 };
                  try {
                    int _jspx_eval_form_005foption_005f3 = _jspx_th_form_005foption_005f3.doStartTag();
                    if (_jspx_th_form_005foption_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      return;
                    }
                  } catch (Throwable _jspx_exception) {
                    while (_jspx_push_body_count_form_005foption_005f3[0]-- > 0)
                      out = _jspx_page_context.popBody();
                    _jspx_th_form_005foption_005f3.doCatch(_jspx_exception);
                  } finally {
                    _jspx_th_form_005foption_005f3.doFinally();
                    _005fjspx_005ftagPool_005fform_005foption_005fvalue_005flabel_005fnobody.reuse(_jspx_th_form_005foption_005f3);
                  }
                  out.write("\r\n");
                  out.write("\t\t\t\t\t\t");
                  //  form:option
                  org.springframework.web.servlet.tags.form.OptionTag _jspx_th_form_005foption_005f4 = (org.springframework.web.servlet.tags.form.OptionTag) _005fjspx_005ftagPool_005fform_005foption_005fvalue_005flabel_005fnobody.get(org.springframework.web.servlet.tags.form.OptionTag.class);
                  _jspx_th_form_005foption_005f4.setPageContext(_jspx_page_context);
                  _jspx_th_form_005foption_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fselect_005f1);
                  // /WEB-INF/jsp/comBbs/comBbsList.jsp(132,6) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_form_005foption_005f4.setValue(new String("30"));
                  // /WEB-INF/jsp/comBbs/comBbsList.jsp(132,6) name = label type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_form_005foption_005f4.setLabel("30개씩 보기");
                  int[] _jspx_push_body_count_form_005foption_005f4 = new int[] { 0 };
                  try {
                    int _jspx_eval_form_005foption_005f4 = _jspx_th_form_005foption_005f4.doStartTag();
                    if (_jspx_th_form_005foption_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      return;
                    }
                  } catch (Throwable _jspx_exception) {
                    while (_jspx_push_body_count_form_005foption_005f4[0]-- > 0)
                      out = _jspx_page_context.popBody();
                    _jspx_th_form_005foption_005f4.doCatch(_jspx_exception);
                  } finally {
                    _jspx_th_form_005foption_005f4.doFinally();
                    _005fjspx_005ftagPool_005fform_005foption_005fvalue_005flabel_005fnobody.reuse(_jspx_th_form_005foption_005f4);
                  }
                  out.write("\r\n");
                  out.write("\t\t\t\t\t\t");
                  //  form:option
                  org.springframework.web.servlet.tags.form.OptionTag _jspx_th_form_005foption_005f5 = (org.springframework.web.servlet.tags.form.OptionTag) _005fjspx_005ftagPool_005fform_005foption_005fvalue_005flabel_005fnobody.get(org.springframework.web.servlet.tags.form.OptionTag.class);
                  _jspx_th_form_005foption_005f5.setPageContext(_jspx_page_context);
                  _jspx_th_form_005foption_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fselect_005f1);
                  // /WEB-INF/jsp/comBbs/comBbsList.jsp(133,6) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_form_005foption_005f5.setValue(new String("40"));
                  // /WEB-INF/jsp/comBbs/comBbsList.jsp(133,6) name = label type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_form_005foption_005f5.setLabel("40개씩 보기");
                  int[] _jspx_push_body_count_form_005foption_005f5 = new int[] { 0 };
                  try {
                    int _jspx_eval_form_005foption_005f5 = _jspx_th_form_005foption_005f5.doStartTag();
                    if (_jspx_th_form_005foption_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      return;
                    }
                  } catch (Throwable _jspx_exception) {
                    while (_jspx_push_body_count_form_005foption_005f5[0]-- > 0)
                      out = _jspx_page_context.popBody();
                    _jspx_th_form_005foption_005f5.doCatch(_jspx_exception);
                  } finally {
                    _jspx_th_form_005foption_005f5.doFinally();
                    _005fjspx_005ftagPool_005fform_005foption_005fvalue_005flabel_005fnobody.reuse(_jspx_th_form_005foption_005f5);
                  }
                  out.write("\r\n");
                  out.write("\t\t\t\t\t\t");
                  //  form:option
                  org.springframework.web.servlet.tags.form.OptionTag _jspx_th_form_005foption_005f6 = (org.springframework.web.servlet.tags.form.OptionTag) _005fjspx_005ftagPool_005fform_005foption_005fvalue_005flabel_005fnobody.get(org.springframework.web.servlet.tags.form.OptionTag.class);
                  _jspx_th_form_005foption_005f6.setPageContext(_jspx_page_context);
                  _jspx_th_form_005foption_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fselect_005f1);
                  // /WEB-INF/jsp/comBbs/comBbsList.jsp(134,6) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_form_005foption_005f6.setValue(new String("50"));
                  // /WEB-INF/jsp/comBbs/comBbsList.jsp(134,6) name = label type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
                  _jspx_th_form_005foption_005f6.setLabel("50개씩 보기");
                  int[] _jspx_push_body_count_form_005foption_005f6 = new int[] { 0 };
                  try {
                    int _jspx_eval_form_005foption_005f6 = _jspx_th_form_005foption_005f6.doStartTag();
                    if (_jspx_th_form_005foption_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                      return;
                    }
                  } catch (Throwable _jspx_exception) {
                    while (_jspx_push_body_count_form_005foption_005f6[0]-- > 0)
                      out = _jspx_page_context.popBody();
                    _jspx_th_form_005foption_005f6.doCatch(_jspx_exception);
                  } finally {
                    _jspx_th_form_005foption_005f6.doFinally();
                    _005fjspx_005ftagPool_005fform_005foption_005fvalue_005flabel_005fnobody.reuse(_jspx_th_form_005foption_005f6);
                  }
                  out.write("\r\n");
                  out.write("\t\t\t\t\t");
                  int evalDoAfterBody = _jspx_th_form_005fselect_005f1.doAfterBody();
                  if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                    break;
                } while (true);
              }
              if (_jspx_th_form_005fselect_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                return;
              }
            } catch (Throwable _jspx_exception) {
              while (_jspx_push_body_count_form_005fselect_005f1[0]-- > 0)
                out = _jspx_page_context.popBody();
              _jspx_th_form_005fselect_005f1.doCatch(_jspx_exception);
            } finally {
              _jspx_th_form_005fselect_005f1.doFinally();
              _005fjspx_005ftagPool_005fform_005fselect_005fpath_005fonchange_005fcssClass.reuse(_jspx_th_form_005fselect_005f1);
            }
            out.write("\r\n");
            out.write("\t\t\t\t</div>\r\n");
            out.write("\t\t\t</div>\t\t\t\t\t\r\n");
            out.write("\t\t\t<!-- table add start -->\r\n");
            out.write("\t\t\t<div class=\"default_tablestyle\">\r\n");
            out.write("\t\t\t\t<!-- List -->\r\n");
            out.write("\t\t\t\t<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n");
            out.write("\t\t\t\t\t<colgroup>\r\n");
            out.write("\t\t\t\t\t\t<col width=\"40\">\r\n");
            out.write("\t\t\t\t\t\t<col width=\"100\">\r\n");
            out.write("\t\t\t\t\t\t<col width=\"150\">\r\n");
            out.write("\t\t\t\t\t\t<col width=\"80\">\r\n");
            out.write("\t\t\t\t\t\t<col width=\"\">\r\n");
            out.write("\t\t\t\t\t\t<col width=\"60\">\r\n");
            out.write("\t\t\t\t\t</colgroup>\r\n");
            out.write("\t\t\t\t\t<tr>\r\n");
            out.write("\t\t\t\t\t\t<th align=\"center\">No</th>\r\n");
            out.write("\t\t\t\t\t\t<th align=\"center\">제목</th>\r\n");
            out.write("\t\t\t\t\t\t<th align=\"center\">등록자</th>\r\n");
            out.write("\t\t\t\t\t\t<th align=\"center\">등록일</th>\r\n");
            out.write("\t\t\t\t\t</tr>\r\n");
            out.write("\t\t\t\t\t");
            if (_jspx_meth_c_005fforEach_005f0(_jspx_th_form_005fform_005f0, _jspx_page_context, _jspx_push_body_count_form_005fform_005f0))
              return;
            out.write("\r\n");
            out.write("\t\t\t\t</table>\r\n");
            out.write("\t\t\t\t<!-- /List -->\r\n");
            out.write("\t\t\t</div>\r\n");
            out.write("\t\t\t<div id=\"paging\">\r\n");
            out.write("\t\t\t\t<ul class=\"paging_align\">\r\n");
            out.write("\t\t\t\t");
            if (_jspx_meth_ui_005fpagination_005f0(_jspx_th_form_005fform_005f0, _jspx_page_context, _jspx_push_body_count_form_005fform_005f0))
              return;
            out.write("\r\n");
            out.write("\t\t\t\t");
            if (_jspx_meth_form_005fhidden_005f1(_jspx_th_form_005fform_005f0, _jspx_page_context, _jspx_push_body_count_form_005fform_005f0))
              return;
            out.write("\r\n");
            out.write("\t\t\t\t</ul>\r\n");
            out.write("\t\t\t</div>\t\t\t\r\n");
            out.write("\t\t\t<div class=\"buttons\" style=\"clear:both;float:right;padding-left:2px;\">\r\n");
            out.write("\t\t\t\t<a href='#' onclick=\"fn_insertComBbsReg();\">등록</a>\r\n");
            out.write("\t\t\t</div>\r\n");
            out.write("\t\t</div>\r\n");
            out.write("\t\t");
            int evalDoAfterBody = _jspx_th_form_005fform_005f0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_form_005fform_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return;
        }
      } catch (Throwable _jspx_exception) {
        while (_jspx_push_body_count_form_005fform_005f0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_form_005fform_005f0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_form_005fform_005f0.doFinally();
        _005fjspx_005ftagPool_005fform_005fform_005fmethod_005fcommandName.reuse(_jspx_th_form_005fform_005f0);
      }
      out.write("\t\t\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!-- //container 끝 -->\r\n");
      out.write("\t<!-- footer 시작 -->\r\n");
      out.write("\t<div id=\"footer\">\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!-- //footer 끝 -->\t\t\t\t\r\n");
      out.write("</div>\r\n");
      out.write("<!-- //전체 레이어 끝 -->\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_lw_005fcacheable_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  lw:cacheable
    com.devBoard.common.tag.CacheableTag _jspx_th_lw_005fcacheable_005f0 = (com.devBoard.common.tag.CacheableTag) _005fjspx_005ftagPool_005flw_005fcacheable_005fnobody.get(com.devBoard.common.tag.CacheableTag.class);
    _jspx_th_lw_005fcacheable_005f0.setPageContext(_jspx_page_context);
    _jspx_th_lw_005fcacheable_005f0.setParent(null);
    int _jspx_eval_lw_005fcacheable_005f0 = _jspx_th_lw_005fcacheable_005f0.doStartTag();
    if (_jspx_th_lw_005fcacheable_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005flw_005fcacheable_005fnobody.reuse(_jspx_th_lw_005fcacheable_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005flw_005fcacheable_005fnobody.reuse(_jspx_th_lw_005fcacheable_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f0 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f0.setParent(null);
    // /WEB-INF/jsp/comBbs/comBbsList.jsp(39,21) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f0.setValue("/comBbs/retrieveComBBsView.do");
    int _jspx_eval_c_005furl_005f0 = _jspx_th_c_005furl_005f0.doStartTag();
    if (_jspx_th_c_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f1 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f1.setParent(null);
    // /WEB-INF/jsp/comBbs/comBbsList.jsp(47,21) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f1.setValue("/comBbs/insertComBbsReg.do");
    int _jspx_eval_c_005furl_005f1 = _jspx_th_c_005furl_005f1.doStartTag();
    if (_jspx_th_c_005furl_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f2 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f2.setParent(null);
    // /WEB-INF/jsp/comBbs/comBbsList.jsp(57,21) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f2.setValue("/comBbs/retrieveComBbsList.do");
    int _jspx_eval_c_005furl_005f2 = _jspx_th_c_005furl_005f2.doStartTag();
    if (_jspx_th_c_005furl_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f2);
    return false;
  }

  private boolean _jspx_meth_lw_005fheader_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  lw:header
    com.devBoard.common.tag.HeaderTag _jspx_th_lw_005fheader_005f0 = (com.devBoard.common.tag.HeaderTag) _005fjspx_005ftagPool_005flw_005fheader_005fnobody.get(com.devBoard.common.tag.HeaderTag.class);
    _jspx_th_lw_005fheader_005f0.setPageContext(_jspx_page_context);
    _jspx_th_lw_005fheader_005f0.setParent(null);
    int _jspx_eval_lw_005fheader_005f0 = _jspx_th_lw_005fheader_005f0.doStartTag();
    if (_jspx_th_lw_005fheader_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005flw_005fheader_005fnobody.reuse(_jspx_th_lw_005fheader_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005flw_005fheader_005fnobody.reuse(_jspx_th_lw_005fheader_005f0);
    return false;
  }

  private boolean _jspx_meth_lw_005fmenuList_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  lw:menuList
    com.devBoard.common.tag.MenuListTag _jspx_th_lw_005fmenuList_005f0 = (com.devBoard.common.tag.MenuListTag) _005fjspx_005ftagPool_005flw_005fmenuList_005fnobody.get(com.devBoard.common.tag.MenuListTag.class);
    _jspx_th_lw_005fmenuList_005f0.setPageContext(_jspx_page_context);
    _jspx_th_lw_005fmenuList_005f0.setParent(null);
    int _jspx_eval_lw_005fmenuList_005f0 = _jspx_th_lw_005fmenuList_005f0.doStartTag();
    if (_jspx_th_lw_005fmenuList_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005flw_005fmenuList_005fnobody.reuse(_jspx_th_lw_005fmenuList_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005flw_005fmenuList_005fnobody.reuse(_jspx_th_lw_005fmenuList_005f0);
    return false;
  }

  private boolean _jspx_meth_lw_005ftitle2_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  lw:title2
    com.devBoard.common.tag.TitleTag2 _jspx_th_lw_005ftitle2_005f0 = (com.devBoard.common.tag.TitleTag2) _005fjspx_005ftagPool_005flw_005ftitle2_005ftype_005fparams_005fdelim_005fnobody.get(com.devBoard.common.tag.TitleTag2.class);
    _jspx_th_lw_005ftitle2_005f0.setPageContext(_jspx_page_context);
    _jspx_th_lw_005ftitle2_005f0.setParent(null);
    // /WEB-INF/jsp/comBbs/comBbsList.jsp(88,3) name = type type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_lw_005ftitle2_005f0.setType("type1");
    // /WEB-INF/jsp/comBbs/comBbsList.jsp(88,3) name = params type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_lw_005ftitle2_005f0.setParams("방명록|방명록");
    // /WEB-INF/jsp/comBbs/comBbsList.jsp(88,3) name = delim type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_lw_005ftitle2_005f0.setDelim("|");
    int _jspx_eval_lw_005ftitle2_005f0 = _jspx_th_lw_005ftitle2_005f0.doStartTag();
    if (_jspx_th_lw_005ftitle2_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005flw_005ftitle2_005ftype_005fparams_005fdelim_005fnobody.reuse(_jspx_th_lw_005ftitle2_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005flw_005ftitle2_005ftype_005fparams_005fdelim_005fnobody.reuse(_jspx_th_lw_005ftitle2_005f0);
    return false;
  }

  private boolean _jspx_meth_form_005fhidden_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fform_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fform_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  form:hidden
    org.springframework.web.servlet.tags.form.HiddenInputTag _jspx_th_form_005fhidden_005f0 = (org.springframework.web.servlet.tags.form.HiddenInputTag) _005fjspx_005ftagPool_005fform_005fhidden_005fpath_005fnobody.get(org.springframework.web.servlet.tags.form.HiddenInputTag.class);
    _jspx_th_form_005fhidden_005f0.setPageContext(_jspx_page_context);
    _jspx_th_form_005fhidden_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
    // /WEB-INF/jsp/comBbs/comBbsList.jsp(96,3) name = path type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005fhidden_005f0.setPath("selectedId");
    int[] _jspx_push_body_count_form_005fhidden_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_form_005fhidden_005f0 = _jspx_th_form_005fhidden_005f0.doStartTag();
      if (_jspx_th_form_005fhidden_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_form_005fhidden_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_form_005fhidden_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_form_005fhidden_005f0.doFinally();
      _005fjspx_005ftagPool_005fform_005fhidden_005fpath_005fnobody.reuse(_jspx_th_form_005fhidden_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_form_005finput_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fform_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fform_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  form:input
    org.springframework.web.servlet.tags.form.InputTag _jspx_th_form_005finput_005f0 = (org.springframework.web.servlet.tags.form.InputTag) _005fjspx_005ftagPool_005fform_005finput_005fpath_005fnobody.get(org.springframework.web.servlet.tags.form.InputTag.class);
    _jspx_th_form_005finput_005f0.setPageContext(_jspx_page_context);
    _jspx_th_form_005finput_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
    // /WEB-INF/jsp/comBbs/comBbsList.jsp(110,42) name = path type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005finput_005f0.setPath("searchKeyword");
    int[] _jspx_push_body_count_form_005finput_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_form_005finput_005f0 = _jspx_th_form_005finput_005f0.doStartTag();
      if (_jspx_th_form_005finput_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_form_005finput_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_form_005finput_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_form_005finput_005f0.doFinally();
      _005fjspx_005ftagPool_005fform_005finput_005fpath_005fnobody.reuse(_jspx_th_form_005finput_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_lw_005fcalendarTag2_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fform_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fform_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  lw:calendarTag2
    com.devBoard.common.tag.CalendarTag2 _jspx_th_lw_005fcalendarTag2_005f0 = (com.devBoard.common.tag.CalendarTag2) _005fjspx_005ftagPool_005flw_005fcalendarTag2_005fnobody.get(com.devBoard.common.tag.CalendarTag2.class);
    _jspx_th_lw_005fcalendarTag2_005f0.setPageContext(_jspx_page_context);
    _jspx_th_lw_005fcalendarTag2_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
    int _jspx_eval_lw_005fcalendarTag2_005f0 = _jspx_th_lw_005fcalendarTag2_005f0.doStartTag();
    if (_jspx_th_lw_005fcalendarTag2_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005flw_005fcalendarTag2_005fnobody.reuse(_jspx_th_lw_005fcalendarTag2_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005flw_005fcalendarTag2_005fnobody.reuse(_jspx_th_lw_005fcalendarTag2_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fform_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fform_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
    // /WEB-INF/jsp/comBbs/comBbsList.jsp(128,32) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${paginationInfo.totalRecordCount}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f0 = _jspx_th_c_005fout_005f0.doStartTag();
    if (_jspx_th_c_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fform_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fform_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f1 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
    // /WEB-INF/jsp/comBbs/comBbsList.jsp(128,94) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${paginationInfo.currentPageNo}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f1 = _jspx_th_c_005fout_005f1.doStartTag();
    if (_jspx_th_c_005fout_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fform_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fform_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f2 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
    // /WEB-INF/jsp/comBbs/comBbsList.jsp(128,145) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f2.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${paginationInfo.totalPageCount}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f2 = _jspx_th_c_005fout_005f2.doStartTag();
    if (_jspx_th_c_005fout_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fform_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fform_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_005fvarStatus_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
    // /WEB-INF/jsp/comBbs/comBbsList.jsp(156,5) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("result");
    // /WEB-INF/jsp/comBbs/comBbsList.jsp(156,5) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/jsp/comBbs/comBbsList.jsp(156,5) '${resultList}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${resultList}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /WEB-INF/jsp/comBbs/comBbsList.jsp(156,5) name = varStatus type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVarStatus("status");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t<tr>\r\n");
          out.write("\t\t\t\t\t\t<td align=\"center\" class=\"listtd\">");
          if (_jspx_meth_c_005fout_005f3(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("</td>\r\n");
          out.write("\t\t\t\t\t\t<td align=\"left\" class=\"listtd\"><a href=\"javascript:fn_retrieveComBBsView('");
          if (_jspx_meth_c_005fout_005f4(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("')\">");
          if (_jspx_meth_c_005fout_005f5(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("</a>&nbsp;</td>\r\n");
          out.write("\t\t\t\t\t\t<td align=\"center\" class=\"listtd\">");
          if (_jspx_meth_c_005fout_005f6(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("&nbsp;</td>\r\n");
          out.write("\t\t\t\t\t\t<td align=\"center\" class=\"listtd\"><fmt:formatDate value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${result.rgstDt}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\" pattern=\"yyyy.MM.dd\"/>&nbsp;</td>\r\n");
          out.write("\t\t\t\t\t</tr>\r\n");
          out.write("\t\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_005fvarStatus_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f3 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /WEB-INF/jsp/comBbs/comBbsList.jsp(158,40) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f3.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${status.count}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f3 = _jspx_th_c_005fout_005f3.doStartTag();
    if (_jspx_th_c_005fout_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f4 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /WEB-INF/jsp/comBbs/comBbsList.jsp(159,81) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f4.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${result.comBbsSeq}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f4 = _jspx_th_c_005fout_005f4.doStartTag();
    if (_jspx_th_c_005fout_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f4);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f5 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f5.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /WEB-INF/jsp/comBbs/comBbsList.jsp(159,121) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f5.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${result.nttTitle}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f5 = _jspx_th_c_005fout_005f5.doStartTag();
    if (_jspx_th_c_005fout_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f5);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f6 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f6.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /WEB-INF/jsp/comBbs/comBbsList.jsp(160,40) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f6.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${result.rgstId}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f6 = _jspx_th_c_005fout_005f6.doStartTag();
    if (_jspx_th_c_005fout_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f6);
    return false;
  }

  private boolean _jspx_meth_ui_005fpagination_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fform_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fform_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  ui:pagination
    egovframework.tag.PaginationTag _jspx_th_ui_005fpagination_005f0 = (egovframework.tag.PaginationTag) _005fjspx_005ftagPool_005fui_005fpagination_005ftype_005fpaginationInfo_005fjsFunction_005fnobody.get(egovframework.tag.PaginationTag.class);
    _jspx_th_ui_005fpagination_005f0.setPageContext(_jspx_page_context);
    _jspx_th_ui_005fpagination_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
    // /WEB-INF/jsp/comBbs/comBbsList.jsp(169,4) name = paginationInfo type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_ui_005fpagination_005f0.setPaginationInfo((egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${paginationInfo}", egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo.class, (PageContext)_jspx_page_context, null, false));
    // /WEB-INF/jsp/comBbs/comBbsList.jsp(169,4) name = type type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_ui_005fpagination_005f0.setType("image");
    // /WEB-INF/jsp/comBbs/comBbsList.jsp(169,4) name = jsFunction type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_ui_005fpagination_005f0.setJsFunction("fn_retrieveSampleList");
    int _jspx_eval_ui_005fpagination_005f0 = _jspx_th_ui_005fpagination_005f0.doStartTag();
    if (_jspx_th_ui_005fpagination_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fui_005fpagination_005ftype_005fpaginationInfo_005fjsFunction_005fnobody.reuse(_jspx_th_ui_005fpagination_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fui_005fpagination_005ftype_005fpaginationInfo_005fjsFunction_005fnobody.reuse(_jspx_th_ui_005fpagination_005f0);
    return false;
  }

  private boolean _jspx_meth_form_005fhidden_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fform_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fform_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  form:hidden
    org.springframework.web.servlet.tags.form.HiddenInputTag _jspx_th_form_005fhidden_005f1 = (org.springframework.web.servlet.tags.form.HiddenInputTag) _005fjspx_005ftagPool_005fform_005fhidden_005fpath_005fnobody.get(org.springframework.web.servlet.tags.form.HiddenInputTag.class);
    _jspx_th_form_005fhidden_005f1.setPageContext(_jspx_page_context);
    _jspx_th_form_005fhidden_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fform_005f0);
    // /WEB-INF/jsp/comBbs/comBbsList.jsp(170,4) name = path type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005fhidden_005f1.setPath("pageIndex");
    int[] _jspx_push_body_count_form_005fhidden_005f1 = new int[] { 0 };
    try {
      int _jspx_eval_form_005fhidden_005f1 = _jspx_th_form_005fhidden_005f1.doStartTag();
      if (_jspx_th_form_005fhidden_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_form_005fhidden_005f1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_form_005fhidden_005f1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_form_005fhidden_005f1.doFinally();
      _005fjspx_005ftagPool_005fform_005fhidden_005fpath_005fnobody.reuse(_jspx_th_form_005fhidden_005f1);
    }
    return false;
  }
}
