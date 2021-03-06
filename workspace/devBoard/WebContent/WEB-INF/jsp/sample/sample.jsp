<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator"
	uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="lw" uri="/WEB-INF/tld/lw.tld"%>
<%
	/**
	 * @Class Name : sample.jsp
	 * @Description : 카테고리 상세 화면
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
<lw:cacheable />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Language" content="ko">
<jsp:include page="/WEB-INF/jsp/common/header.jsp" flush="true" />

<script type="text/javaScript" language="javascript" defer="defer">
/* 글 목록 화면 function */
function fn_retrieveSampleList() {
	var frm = $("#trxSampleVO");
	
	frm.attr("action", "<c:url value='/sample/retrieveSampleList.do'/>");
	frm.submit();
}

/* 글 삭제 function */
function fn_deleteSample() {
	var cnt = "${cnt}";
	if(cnt > 0){
		alert("댓글이 있는 게시글은 삭제할 수 없습니다.");
		return;
	}
	
	if(confirm("<spring:message code='confirm.common.delete'/>")!=true) return;
	
	var frm = $("#trxSampleVO");	

	frm.attr("action", "<c:url value='/sample/deleteSample.do'/>");
	frm.submit();
}

/* 글 수정 function */
function fn_updateSampleView() {
	var frm = $("#trxSampleVO");
	
	/* var cnt = "${cnt}";
	if(cnt > 0){
		alert("댓글이 있는 게시글은 수정할 수 없습니다.");
		return;
	} */

	frm.attr("action", "<c:url value='/sample/updateSampleView.do'/>");
	frm.submit();
}

$(document).ready(function() {
	//한줄의견 조회 
	fn_searchCommentList('1', '/sample/ajaxLibFreeNoticeCommentList.do', '<c:out value='${trxSampleVO.id}'/>', '/sample/ajaxDeleteLibFreeNoticeComment.do');
});
//엔터키 입력 
$(function() {
	$("#commentContent").keypress(function (e) { 
	    if (e.which == 13) { 
	    	fn_insertComment("/sample/ajaxInsertLibFreeNoticeComment.do", "<c:out value='${trxSampleVO.id}'/>");
	    	return false; 
	    }
	}); 
});

/* 답글 등록 화면 function */
function fn_insertComBbsReply() {
	var frm = $("#trxSampleVO");

	frm.attr("action", "<c:url value='/comBbs/insertComBbsReply.do'/>");
	frm.submit();
}

-->
</script>
<title>공지사항 - 글조회</title>

</head>
<body>
	<!-- 전체 레이어 시작 -->
	<div id="wrap">
		<lw:header />
		<!-- container 시작 -->
		<div id="container">
			<!-- 좌측메뉴 시작 -->
			<div id="leftmenu">
				<div id="nav">
					<lw:menuList />
				</div>
			</div>
			<!-- //좌측메뉴 끝 -->
			<!-- content 시작 -->
			<div id="content">
				<!-- 현재위치 네비게이션 시작 -->
				<!-- 타이틀 -->
				<lw:title2 type="type1" params="샘플게시판|샘플게시판" delim="|" />
				<!-- //현재위치 네비게이션 끝 -->
				<!-- 검색 필드 박스 시작 -->
				<div id="search_field">
					<div id="search_field_loc">
						<h2>
							<strong>글 조회</strong>
						</h2>
					</div>
				</div>
				<form:form commandName="trxSampleVO">
					<div class="modify_user">
						<table>
							<tr>
								<th width="15%" height="23" nowrap>제목</th>
								<td width="85%" colspan="5" nowrap="nowrap"><c:out
										value="${trxSampleVO.name}" />
								</td>
							</tr>
							<tr>
								<th width="15%" height="23" nowrap>등록자</th>
								<td width="85%" nowrap="nowrap"><c:out
										value="${trxSampleVO.regUser}" />
								</td>
							</tr>
							<tr>
								<th scope="row">첨부파일</th>
								<td><c:forEach var="result" items="${fileList}" varStatus="status">
										<a href="javascript:gfn_downloadFile('${result.comFileDtlSeq}');">
											<c:out value="${result.fileStreOriNm}" /> </a>
										<br />
									</c:forEach>
								</td>
							</tr>
							<tr>
								<th height="23">내용</th>
								<td colspan="5">
									<div id="bbs_cn">
										<form:textarea path="description" rows="20" cols="75"
											readyonly="true"/>
										<%-- <c:out value="${trxSampleVO.description}" escapeXml="false"/> --%>
									</div></td>
							</tr>
						</table>
					</div>

					<div class="replyWrite">
						<label for=""><strong>한줄의견</strong> 
						<input type="text" id="commentContent" name="commentContent" style="width: 450px"
							onkeyup="fn_commentByteChk();" /> </label> &nbsp;
						<span class="btn_gray_01"><a href="#" id="insertComment"
							onclick="fn_insertComment('/sample/ajaxInsertLibFreeNoticeComment.do', '<c:out value='${trxSampleVO.id}'/>');">등록</a>
						<span id="currentBytes">0 BYTES / 1,000 BYTES</span> (한글 500자 / 영문 1,000자)</span>
					</div>

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
								<th align="center">작성자</th>
								<th align="center">한줄의견</th>
								<th align="center">작성일</th>
								<th align="center">삭제</th>
							</tr>
							<tbody id="commentList">
							</tbody>
						</table>

						<!-- 버튼 시작(상세지정 style로 div에 지정) -->
						<div class="buttons"
							style="padding-top: 10px; padding-bottom: 10px;">
							<!-- 목록/저장버튼  -->
							<table border="0" cellspacing="0" cellpadding="0" align="center">
								<tr>
									<td width="10"></td>
									<td><a href="#LINK"
										onclick="javascript:fn_retrieveSampleList(); return false;">목록</a>
										<a href="#LINK"
										onclick="javascript:fn_updateSampleView(); return false;">수정</a>
										<!-- <a href="#LINK"
										onclick="javascript:fn_insertComBbsReply(); return false;">답글</a> -->
										<a href="#LINK"
										onclick="javascript:fn_deleteSample(); return false;">삭제</a></td>
								</tr>
							</table>
						</div>
						<!-- 버튼 끝 -->
						<!-- 검색조건 유지 -->
						<input type="hidden" name="selectedId"
							value="<c:out value='${sampleVO.id}'/>" /> <input type="hidden"
							name="searchCondition"
							value="<c:out value='${sampleVO.searchCondition}'/>" /> <input
							type="hidden" name="searchKeyword"
							value="<c:out value='${sampleVO.searchKeyword}'/>" /> <input
							type="hidden" name="pageIndex"
							value="<c:out value='${sampleVO.pageIndex}'/>" /> <input
							type="hidden" name="recordCountPerPage"
							value="<c:out value='${sampleVO.recordCountPerPage}'/>" /> <input
							type="hidden" name="startDate"
							value="<c:out value='${sampleVO.startDate}'/>" /> <input
							type="hidden" name="endDate"
							value="<c:out value='${sampleVO.endDate}'/>" />
				</form:form>
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