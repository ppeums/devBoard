<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="lw" uri="/WEB-INF/tld/lw.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
/**
 * @Class Name : sampleList.jsp
 * @Description : 카테고리 목록 화면
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
<lw:cacheable/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Language" content="utf-8" >
<title>샘플게시판 - 샘플게시판</title>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" flush="true" />
<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 글 수정 화면 function */
function fn_retrieveSample(id) {
	var frm = $("#sampleVO");

	$("#selectedId").val(id);

	frm.attr("action", "<c:url value='/sample/retrieveSample.do'/>");
	frm.submit();
}

/* 글 등록 화면 function */
function fn_insertSampleView() {
	var frm = $("#sampleVO");

	frm.attr("action", "<c:url value='/sample/insertSampleView.do'/>");
	frm.submit();
}

/* pagination 페이지 링크 function */
function fn_retrieveSampleList(pageNo){
	var frm = $("#sampleVO");

	$("#pageIndex").val(pageNo);

	frm.attr("action", "<c:url value='/sample/retrieveSampleList.do'/>");
	frm.submit();
}

/* 엑셀 다운로드 */
function fn_sampleExcelDown(){
	var frm = $('#sampleVO') ;
	
	frm.attr("action", "<c:url value='/sample/sampleExcelDown.do'/>");
	frm.submit();
}

/* 엑셀 업로드 */
function fn_sampleExcelupload(){
	var frm = $('#sampleVO2') ;
	
	frm.attr("action", "<c:url value='/sample/retrieveSampleExcelUpload.do'/>");
	frm.submit();
}

//파일추가
function fn_addFile(){
	
	var size = $("#mboardAttchInsertIputArea").find("[type=file]").size();
	
	if(size > 5){
		alert('첨부파일은 5개이상 추가할 수 없습니다.');
		return;
	}

	var fileDiv = '<input type="file" name="file'+size+'" title="첨부파일" value="첨부파일" size="50" />';
	$("#mboardAttchInsertIputArea").append(fileDiv);
}

//파일삭제
function fn_delFile(){
	if($("#mboardAttchInsertIputArea").find("[type=file]").size() > 1){
		$("#mboardAttchInsertIputArea").find("[type=file]").last().remove();
	}
}

// 갯수별로 보기
function fn_recordCountPerPage() {
	fn_retrieveSampleList("1");
}
-->
</script>
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
			<!-- 타이틀 이미지 -->			
			<div id="content_img_div"><img src="/static/images/subtitle/img_subtitle03-02.gif" width="776" height="230" /></div>
			<!-- main content 시작 -->
			<div class="content_field"><h2>글 목록</h2></div>
			<!-- //main content 끝 -->			
			<form:form commandName="sampleVO" method="post">
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
							<a href="#" onclick="javascript:fn_retrieveSampleList(1);"><img src="/static/images/img_search.gif" alt="search" />검색 </a>
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
					<a href='#' onclick="fn_sampleExcelDown();">엑셀다운로드</a>
					<form:select path="recordCountPerPage" cssClass="use" onchange="fn_recordCountPerPage()">
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
						<col width="100">
						<col width="150">
						<col width="80">
						<col width="">
						<col width="60">
					</colgroup>
					<tr>
						<th align="center">No</th>
						<th align="center">제목</th>
						<th align="center">등록자</th>
						<th align="center">등록일</th>
					</tr>
					<c:set var="seq" value="${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage)}"/>
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr>
						<td align="center" class="listtd"><c:out value="${seq }" /></td>
						<td align="left" class="listtd"><a href="javascript:fn_retrieveSample('<c:out value="${result.id}"/>')"><c:out value="${result.name}"/></a>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.regUser}"/>&nbsp;</td>
						<td align="center" class="listtd"><c:out value="${result.regDate}"/>&nbsp;</td>
					</tr>
					<c:set var="seq" value="${seq-1}"/>
					</c:forEach>
				</table>
				<!-- /List -->
			</div>
			<div id="paging">
				<ul class="paging_align">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_retrieveSampleList" />
				<form:hidden path="pageIndex" />
				</ul>
			</div>			
			<div class="buttons" style="clear:both;float:right;padding-left:2px;">
				<a href='#' onclick="fn_insertSampleView();">등록</a>
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