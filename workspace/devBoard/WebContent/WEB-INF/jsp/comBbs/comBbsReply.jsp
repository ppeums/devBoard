<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="lw" uri="/WEB-INF/tld/lw.tld" %>
<%
/**
 * @Class Name : sampleRegister.jsp
 * @Description : 카테고리 등록/수정 화면
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
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Language" content="utf-8" >
<title>답글게시판 답변</title>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" flush="true" />

<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 글 목록 화면 function */
function fn_retrieveComBbsList() {
	var frm = $("#trxComBbsVO");

	frm.attr("action", "<c:url value='/comBbs/retrieveComBbsList.do'/>");
	frm.submit();
}

/* 글 등록 function */
function fn_insertComBbsReply1() {
	var frm = $("#trxComBbsVO");

	var confirmMsg = "<spring:message code='confirm.common.insert'/>";
	
	if(confirm(confirmMsg)!=true) return;
	
	frm.attr("action", "<c:url value='/comBbs/insertComBbsReply1.do'/>");
	frm.submit();
}
-->
</script>
<title>답글게시판 답변</title>

</head>
<body>
<!-- 전체 레이어 시작 -->
<div id="wrap">
    <lw:header/>
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
			<lw:title2 type="type1" params="샘플게시판|샘플게시판" delim="|"/>
			<!-- //현재위치 네비게이션 끝 -->
				<!-- 검색 필드 박스 시작 -->
				<div id="search_field">
					<div id="search_field_loc"><h2><strong>글조회</strong></h2></div>
				</div>
				<form:form commandName="trxComBbsVO">
				<div class="modify_user" >
	                <table>
                   		<tr> 
						    <th width="15%" height="23" nowrap >제목</th>
						    <td width="85%" colspan="5" nowrap="nowrap"><form:input path="nttTitle"/></td>
					    </tr>
					    <tr> 
					        <th width="15%" height="23" nowrap >등록자</th>
					        <td width="85%" nowrap="nowrap"><form:input path="rgstId"/></td>
					    </tr>    
					    <tr> 
						    <th height="23" >내용</th>
						    <td colspan="5">
							    <div id="bbs_cn"><form:textarea path="nttContent" rows="20" cols="75" readyonly="true"/></div>
						    </td>
					    </tr>
					</table>
				</div>

				<!-- 버튼 시작(상세지정 style로 div에 지정) -->
				<div class="buttons" style="padding-top:10px;padding-bottom:10px;">
				<!-- 목록/저장버튼  -->
				<table border="0" cellspacing="0" cellpadding="0" align="center">
					<tr>
				    	<td width="10"></td>
				        <td>
				        	<a href="#LINK" onclick="javascript:fn_retrieveComBbsList(); return false;">목록</a> 
				        	<a href="#LINK" onclick="javascript:fn_insertComBbsReply1(); return false;">저장</a> 
				        </td>
					</tr>
				</table>
		        </div>
		    	<!-- 버튼 끝 -->                           
            <!-- 검색조건 유지 -->
			<input type="hidden" name="selectedId" value="<c:out value='${comBbsVO.comBbsSeq}'/>"/>
			<input type="hidden" name="searchCondition" value="<c:out value='${comBbsVO.searchCondition}'/>"/>
			<input type="hidden" name="searchKeyword" value="<c:out value='${comBbsVO.searchKeyword}'/>"/>
			<input type="hidden" name="pageIndex" value="<c:out value='${comBbsVO.pageIndex}'/>"/>
			<input type="hidden" name="recordCountPerPage" value="<c:out value='${comBbsVO.recordCountPerPage}'/>"/>
			<input type="hidden" name="startDate" value="<c:out value='${comBbsVO.startDate}'/>"/>
			<input type="hidden" name="endDate" value="<c:out value='${comBbsVO.endDate}'/>"/>
			<form:hidden path="prntsNo"/>
			<form:hidden path="replyLoc"/>
			<form:hidden path="comBbsSeq"/>
			</form:form>
		</div>  
		<!-- //content 끝 -->    
    </div>  
    <!-- //container 끝 -->
    <!-- footer 시작 -->
    <div id="footer">
	</div>
    <!-- //footer 끝 -->
</div>
<!-- //전체 레이어 끝 -->
</body>
</html>