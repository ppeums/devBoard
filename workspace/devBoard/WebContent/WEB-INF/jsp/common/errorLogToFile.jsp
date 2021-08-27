<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.io.*, org.apache.log4j.*, org.springframework.web.context.*, org.springframework.web.context.support.*, com.devBoard.framework.config.*, com.devBoard.framework.util.*" %>
<%
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
%>
<?xml version="1.0" encoding="EUC-KR" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>에러 발생</title>
<style type="text/css">
/* <![CDATA[ */

body {
    text-align:center;
}
#LblockMain {
    width:450px;
    text-align:center; 
    padding-top:100px;
}
#errorImg{text-align:left; width:401px; height:94px; background-image:url(/static/images/error.gif); background-position:top left; background-repeat:no-repeat; margin-left:20px;}
#errorImg .errTxt{position:relative; top:69px; left:129px; font-size:13px; line-height:16px; color:#fb6e06;}

/* ]]> */
</style>
</head>
<body>
<div id="LblockMain" class="LblockMain">
  <div id="errorImg">
    <p class="errTxt">Error ID : <%=fileName%></p>
  </div>
</div>
</body>
</html>