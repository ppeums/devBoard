<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="lw" uri="/WEB-INF/tld/lw.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
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
%>
<lw:cacheable/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Language" content="utf-8" >
<title>답글게시판 - 답글게시판</title>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" flush="true" />
<jsp:useBean id="now" class="java.util.Date" />
<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 글 조회 화면 function */
function fn_retrieveComBBsView(id) {
	var frm = $("#comBbsVO");

	$("#selectedId").val(id);

	frm.attr("action", "<c:url value='/comBbs/retrieveComBBsView.do'/>");
	frm.submit();
}

/* 글 등록 화면 function */
function fn_insertComBbsReg() {
	var frm = $("#comBbsVO");

	frm.attr("action", "<c:url value='/comBbs/insertComBbsReg.do'/>");
	frm.submit();
}

/* pagination 페이지 링크 function */
function fn_retrieveComBbsList(pageNo){
	var frm = $("#comBbsVO");

	$("#pageIndex").val(pageNo);

	frm.attr("action", "<c:url value='/comBbs/retrieveComBbsList.do'/>");
	frm.submit();
}

//엔터키 입력 
$(function() {
	$("#searchKeyword").keypress(function (e) { 
	    if (e.which == 13) { 
	    	fn_retrieveComBbsList('1');
	    	return false; 
	    }
	}); 
});
-->
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
			<lw:title2 type="type1" params="답글게시판|답글게시판" delim="|"/>
			<!-- //현재위치 네비게이션 끝 -->
			<!-- 타이틀 이미지 -->			
			<div id="content_img_div"><img src="/static/images/subtitle/img_subtitle03-02.gif" width="776" height="230" /></div>
			<!-- main content 시작 -->
			<div class="content_field"><h2>글 목록</h2></div>
			<!-- //main content 끝 -->			
			<form:form commandName="comBbsVO" method="post">
			<form:hidden path="selectedId" />	 
			<!-- 검색 필드 박스 시작 -->				 
			<div id="search_field">
			  	<fieldset><legend>조건정보 영역</legend>	  
			  	<div class="sf_start">
			  		<ul id="search_first_ul">
			  			<li>
			  				<div class="search_leftselect">
							<form:select path="searchCondition" cssClass="use">
								<form:option value="1" label="제목" />
								<form:option value="0" label="등록자" />
							</form:select>
							</div>					
			  			</li>
			  			<li><div class="inputbox_style01"><form:input path="searchKeyword"/></div></li>
			  			<li>
		  				    <div class="buttons" style="float:left;padding-left:2px;">
							<a href="#" onclick="javascript:fn_retrieveComBbsList('1');"><img src="/static/images/img_search.gif" alt="search" />검색 </a>
							</div>	  		
			  			</li>	
			  		</ul>
			  	</fieldset>
			  	<fieldset><legend>조건정보 영역</legend>	  
			  	<div class="sf_start">
			  		<ul id="search_first_ul">
			  			<lw:calendarTag2 />
			  		</ul>
			  	</fieldset>
			</div> 
			<!-- //검색 필드 박스 끝 -->
			<div id="page_info">
				<div id="page_info_align">
					[ <span class="orenge_s">총 <c:out value="${paginationInfo.totalRecordCount}"/> 건</span> (<c:out value="${paginationInfo.currentPageNo}"/> / <c:out value="${paginationInfo.totalPageCount}"/> page) ]
					<form:select path="recordCountPerPage" cssClass="use" onchange="fn_retrieveComBbsList('1')">
						<form:option value="10" label="10개씩 보기" />
						<form:option value="20" label="20개씩 보기" />
						<form:option value="30" label="30개씩 보기" />
						<form:option value="40" label="40개씩 보기" />
						<form:option value="50" label="50개씩 보기" />
					</form:select>
				</div>
			</div>
			<!-- table add start -->
			<div class="default_tablestyle">
				<!-- List -->
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<colgroup>
						<col width="40">
						<col width="150">
						<col width="50">
						<col width="50">
						<col width="40">
					</colgroup>
					<tr>
						<th align="center">No</th>
						<th align="center">제목</th>
						<th align="center">등록자</th>
						<th align="center">등록일</th>
						<th align="center">조회수</th>
					</tr>
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr>
						<td align="center" class="listtd"><c:out value="${status.count}"/></td>
						<td align="left" class="listtd"><a href="javascript:fn_retrieveComBBsView('<c:out value="${result.comBbsSeq}"/>')">
						<c:forEach var="i" begin="2" end="${result.replyLoc}" step="1"> 
						&nbsp;&nbsp;&nbsp;</c:forEach>
						<c:if test="${result.replyLoc >=2}">
						<img src="/static/images/re4_11.png"></c:if>
						<%-- <span style="color: blue;">[re]</span></c:if> --%>
						<c:if test="${result.viewCnt > 20}">
						<img src="/static/images/hit8_9.png"></c:if>
						<c:out value="${result.nttTitle}"/></div></a>
							<c:if test="${result.comFileSeq != 0}">
							<img src="/static/images/file.gif"></c:if>
							<c:if test="${result.comCheck > 0}">
							<span style="color: red;">[${result.comCheck}]</span></c:if>
							<fmt:parseNumber value="${now.time / (1000*60*60*24)}" integerOnly="true" var="nowDays" scope="request"/>
							<fmt:parseNumber value="${result.rgstDt.time / (1000*60*60*24)}" integerOnly="true" var="oldDays" scope="page"/>
							<c:set value="${nowDays - oldDays}" var="dateDiff"/>
							<%-- <c:out value="${dateDiff}"/> --%>
							<%-- ${nowDays} - ${oldDays} = ${dateDiff} --%>
							<c:if test="${dateDiff <= 1}">
							<%-- <c:if test="${result.comCheck == 0}">&nbsp;</c:if> --%>
							<img src="/static/images/new.jpg"></c:if>
							
							<%-- <c:out value="${now} - ${result.rgstDt}"/> --%>
							<%-- &nbsp;<c:out value="${result.rgstDt}"/> --%>
							<%-- <fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="today"/> --%>
							<%-- <<c:if test="${now - result.rgstDt  }">
							<img src="/static/images/clip5_14.png"></c:if> --%>
							
							</td>
						<td align="center" class="listtd"><c:out value="${result.rgstId}"/>&nbsp;</td>
						<td align="center" class="listtd">
						<c:if test="${dateDiff < 1}">
						<fmt:formatDate value="${result.rgstDt}" pattern="HH:mm:ss"/></c:if>
						<c:if test="${dateDiff >= 1}">
						<fmt:formatDate value="${result.rgstDt}" pattern="yyyy.MM.dd"/></c:if>
						&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.viewCnt}"/>&nbsp;</td>
					</tr>
					</c:forEach>
				</table>
				<!-- /List -->
			</div>
			<div id="paging">
				<ul class="paging_align">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_retrieveComBbsList" />
				<form:hidden path="pageIndex" />
				</ul>
			</div>			
			<div class="buttons" style="clear:both;float:right;padding-left:2px;">
				<a href='#' onclick="fn_insertComBbsReg();">등록</a>
			</div>
		</div>
		</form:form>
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