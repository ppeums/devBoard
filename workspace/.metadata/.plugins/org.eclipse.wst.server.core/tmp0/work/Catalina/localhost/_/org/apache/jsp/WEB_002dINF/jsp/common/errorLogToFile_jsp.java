package org.apache.jsp.WEB_002dINF.jsp.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import org.apache.log4j.*;
import org.springframework.web.context.*;
import org.springframework.web.context.support.*;
import com.devBoard.framework.config.*;
import com.devBoard.framework.util.*;

public final class errorLogToFile_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
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

    response.setStatus(500);

    Throwable e = (Exception)request.getAttribute("exception");

    WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
    ExXMLConfiguration conf = ExXMLConfiguration.getInstance();
    Logger logger = Logger.getLogger(this.getClass());
    
    String filePath = System.getProperty("logpath") + "/log/error";
    String fileDate = DateUtil.getToday("yyyyMMddHHmmssSSS");
    
    File f = new File(filePath);
    
    if(!f.exists()) f.mkdir();
    
    String fileName = "error_" + fileDate; // + "_" + request.getRemoteAddr();

    FileWriter fw = new FileWriter(filePath + "/" + fileName + ".log");
    
    try {
        if(e != null) {
            logger.error("## " + filePath + "/" + fileName + ".log" + " ##");
            e.printStackTrace(new PrintWriter(fw,true));
        }
    } catch(Exception ex) {
        ex.printStackTrace();
    } finally {
        fw.close();
    }
    
    String msg = "오류가 발생하였습니다.\n시스템 담당자에게 문의하십시오!\n";

      out.write("\r\n");
      out.write("<?xml version=\"1.0\" encoding=\"EUC-KR\" ?>\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\r\n");
      out.write("<title>에러 발생</title>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("/* <![CDATA[ */\r\n");
      out.write("\r\n");
      out.write("body {\r\n");
      out.write("    text-align:center;\r\n");
      out.write("}\r\n");
      out.write("#LblockMain {\r\n");
      out.write("    width:450px;\r\n");
      out.write("    text-align:center; \r\n");
      out.write("    padding-top:100px;\r\n");
      out.write("}\r\n");
      out.write("#errorImg{text-align:left; width:401px; height:94px; background-image:url(/static/images/error.gif); background-position:top left; background-repeat:no-repeat; margin-left:20px;}\r\n");
      out.write("#errorImg .errTxt{position:relative; top:69px; left:129px; font-size:13px; line-height:16px; color:#fb6e06;}\r\n");
      out.write("\r\n");
      out.write("/* ]]> */\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div id=\"LblockMain\" class=\"LblockMain\">\r\n");
      out.write("  <div id=\"errorImg\">\r\n");
      out.write("    <p class=\"errTxt\">Error ID : ");
      out.print(fileName);
      out.write("</p>\r\n");
      out.write("  </div>\r\n");
      out.write("</div>\r\n");
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
}
