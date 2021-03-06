<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="lw" uri="/WEB-INF/tld/lw.tld" %>
<%
/**
 * @Class Name : login.jsp
 * @Description : 게시판 개발  로그인 페이지
 * @Modification Information
 *
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2012.12.11  송제승     최초생성
 *
 * @author 송제승
 * @since 2012.12.11
 * @version 1.0
 * @see
 *
 * Copyright (C) 2012 by Sinsuldong All right reserved.
 */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<meta http-equiv="content-language" content="utf-8">
<title>게시판 개발  - 로그인</title>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" flush="true" />
<script type="text/javascript">
<!--
// 서브밋 및 밸리데이션
function fn_loginSubmit()
{
	var frm = $("#loginVO");
	
	if( $("#userId").val().replaceAll(" ","") == '') {
		alert("<spring:message code='login.alert.001'/>");
		return;
	}
	
	else if( $("#userPwd").val().replaceAll(" ","") == '') {
		alert("<spring:message code='login.alert.002'/>");
		return;
	}
    
    $("#loginVO").attr("action", "<c:url value='/login/retrieveLoginProc.xx'/>");
    $("#loginVO").submit();
}

// userId로 포커스 이동
$(document).ready(function() {
	var formObj = $("#loginVO");
	$("#userId").focus();
	return;
});

// 엔터입력
$(function() {
	$("#userPwd").keydown(function(event){ 
		if( event.keyCode == 13) {
			fn_loginSubmit(); 
	    	return false; 
		}
	});
});
//-->
</script>
</head>
<body>
<!-- 전체 레이어 시작 -->
<div id="wrap">
    <lw:header/>
    <div align="right" id="loginId" style="font-size:14px;">
    <c:if test="${userId != null }">
	<br><b>${userId}</b> 님이 로그인 중입니다.</c:if></div>
    <!-- container 시작 -->
    <div id="container">
        <!-- 좌측메뉴 시작 -->
        <div id="leftmenu">
			<div id="nav"><lw:menuList /></div>
		</div>
        <!-- //좌측메뉴 끝 -->
            <!-- content 시작 --> 
            <div id="content">
                <!-- 현재위치 네비게이션 시작 -->
				<!-- 타이틀 -->
				<lw:title2 type="type1" params="로그인|로그인" delim="|"/>
				<!-- //현재위치 네비게이션 끝 -->
                <!-- 타이틀 이미지 -->            
                <div id="content_img_div"><img  alt="LOGIN 표준프레임워크 경량환경 단순 홈페이지에 오신것을 환영합니다." src="/static/images/subtitle/img_subtitle_login.gif" width="776" height="230" /></div>       
                <div class="user_login">
					<form:form commandName="loginVO" method="post">
					<div class="user_login_ultop">
						<ul>
							<li>
								<label for="id"><img alt="login" src="/static/images/login/img_idtext.gif" /></label>
								<form:input path="userId" cssClass="input_style" cssStyle="width:100px;" maxlength="18" alt="아이디" style="ime-mode:inactive"/>
							</li>
							<li>
								<label for="password"><img alt="password" src="/static/images/login/img_pwtext.gif" /></label>
								<form:password path="userPwd" cssClass="input_style"  cssStyle="width:100px;" maxlength="18"/>
							</li>
						</ul>
						<input type="image" alt="로그인 버튼" class="btn_style" onclick="javascript:fn_loginSubmit()" src="/static/images/login/btn_login.gif"  />
					</div>
					<!-- <br><br>
					<input type="image" alt="회원가입 버튼" class="btn_style" onclick="javascript:fn_registSubmit()" src="/static/images/login/btn_regist.gif" /> -->
					</form:form>
					<div class="text_area">
					</div>     		
				</div>
            </div>                      
            <!-- //content 끝 -->    
    </div>  
    <!-- //container 끝 -->
    <!-- footer 시작 -->
    <div id="footer"></div>
    <!-- //footer 끝 -->
</div>
<!-- //전체 레이어 끝 -->
</body>
</html>