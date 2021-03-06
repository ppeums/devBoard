<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.devBoard.framework.config.*" %>
<%@ page import="com.devBoard.framework.util.*" %>
<%
    ExXMLConfiguration conf = ExXMLConfiguration.getInstance();
%>
<link rel="stylesheet" type="text/css" href="<c:url value='/static/css/common.css' />" />
<link rel="stylesheet" type="text/css" href="<c:url value='/static/css/login.css' />" />
<script type="text/javascript" src="<c:url value='/static/js/jquery/jquery-1.6.4.min.js' />"></script>
<script type="text/javascript" src="<c:url value='/static/js/common/sinsul_common.js' />"></script>
<script type="text/javascript" src="<c:url value='/static/js/common/sinsul_form.js' />"></script>
<script type="text/javascript">
    function gfn_showMessage() {
        var msg = "<spring:message code='${msgCd}' arguments='${msgArgs}' argumentSeparator='${msgArgsSepr}' javaScriptEscape='true' text='' />";
        
        if (msg != "") alert(msg);
    }

    $(document).ready(function() {
        gfn_showMessage();
        
        // 파일 업로드 정책
        G_COMMON.UPLOAD_POLICY = "<%=conf.getString("file.upload.policy")%>";
        G_COMMON.UPLOAD_ALLOW_EXT = "<%=MultipartFileUtil.getUploadAllowExtList()%>";
        G_COMMON.UPLOAD_DENY_EXT = "<%=MultipartFileUtil.getUploadDenyExtList()%>";
    });
</script>