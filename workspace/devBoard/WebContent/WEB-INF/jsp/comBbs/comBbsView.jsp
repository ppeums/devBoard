<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator"
	uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="lw" uri="/WEB-INF/tld/lw.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	/**
	 * @Class Name : comBbsView.jsp
	 * @Description : 카테고리 상세 화면
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
<lw:cacheable />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Language" content="ko">
<jsp:include page="/WEB-INF/jsp/common/header.jsp" flush="true" />

<style>
      :-ms-input-placeholder{
        color:darkgray;
      }
    </style>

<script type="text/javaScript" language="javascript" defer="defer">
/* 글 목록 화면 function */
function fn_retrieveComBbsList() {
	var frm = $("#trxComBbsVO");

	frm.attr("action", "<c:url value='/comBbs/retrieveComBbsList.do'/>");
	frm.submit();
}

/* 글 삭제 function */
function fn_deleteComBbsList() {
	var cnt = "${cnt}";
	if(cnt > 0) {
		alert("'댓글'이 있는 게시글은 삭제할 수 없습니다.");
		return;
	}
	
	var comBbsCount = "${comBbsCount}";
	if(comBbsCount > 0) {
		alert("'답글'이 있는 게시글은 삭제할 수 없습니다.");
		return;
	}
	
	if(confirm("<spring:message code='confirm.common.delete'/>")!=true) return;
	
	var frm = $("#trxComBbsVO");

	frm.attr("action", "<c:url value='/comBbs/deleteComBbsList.do'/>");
	frm.submit();
}

/* 글 수정 function */
function fn_updateComBbsModi() {
	var frm = $("#trxComBbsVO");
	
	/* var cnt = "${cnt}";
	if(cnt > 0) {
		alert("댓글이 있는 게시글은 수정할 수 없습니다.");
		return;
	} */ 
	
	/* var comBbsCount = "${comBbsCount}";
	if(comBbsCount > 0) {
		alert("답글이 있는 게시글은 수정할 수 없습니다.");
		return;
	} */

	frm.attr("action", "<c:url value='/comBbs/updateComBbsModi.do'/>");
	frm.submit();
}

/* 답글 등록 화면 function */
function fn_insertComBbsReply() {
	var frm = $("#trxComBbsVO");

	frm.attr("action", "<c:url value='/comBbs/insertComBbsReply.do'/>");
	frm.submit();
}

$(document).ready(function() {
	var userId = "${userId}";
	
	//한줄의견 조회 
	fn_searchCommentList('1', '/comBbs/ajaxLibFreeNoticeCommentList.do', '<c:out value='${trxComBbsVO.comBbsSeq}'/>', '/comBbs/ajaxDeleteLibFreeNoticeComment.do', userId);
});
//엔터키 입력 
$(function() {
	$("#commentContent").keypress(function (e) { 
	    if (e.which == 13) { 
	    	fn_insertComment("/comBbs/ajaxInsertLibFreeNoticeComment.do", "<c:out value='${trxComBbsVO.comBbsSeq}'/>");
	    	return false; 
	    }
	}); 
});

-->
</script>
<title>답글게시판 - 답글게시판</title>

</head>
<body>
	<!-- 전체 레이어 시작 -->
	<div id="wrap">
		<lw:header />
		<div align="right" id="loginId" style="font-size:14px;">
    	<c:if test="${userId != null }">
		<br><b>${userId}</b> 님이 로그인 중입니다.</c:if></div>
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
				<lw:title2 type="type1" params="답글게시판|답글게시판" delim="|" />
				<!-- //현재위치 네비게이션 끝 -->
				<!-- 검색 필드 박스 시작 -->
				<div id="search_field">
					<div id="search_field_loc">
						<h2>
							<strong>글 조회</strong>
						</h2>
					</div>
				</div>
				<form:form commandName="trxComBbsVO">
					<div class="modify_user">
						<table>
							<tr>
								<th width="15%" height="23" nowrap>제목</th>
								<td width="35%" nowrap="nowrap"><c:out
										value="${result.nttTitle}" />
								</td>
								<th width="15%">조회수</th>
								<td width="35%"><c:out value="${result.viewCnt}" /></td>
							</tr>
							<tr>
								<th width="15%" height="23" nowrap>등록자</th>
								<td width="35%" nowrap="nowrap"><c:out value="${result.rgstId}" />
								</td>
								<th width="15%">등록일</th>
								<td width="35%"><fmt:formatDate value="${result.rgstDt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							</tr>
							<c:if test="${result.comFileCnt != 0}">
							<tr>
								<th scope="row">첨부파일</th>
								<c:if test="${result.comFileCnt >= 2}">
								<td colspan="3" style="padding:6px 9px 6px 9px;">
								</c:if>
								<c:if test="${result.comFileCnt <= 1}">
								<td colspan="3">
								</c:if>
									<c:forEach var="result" items="${fileList}" varStatus="status">
										<a href="javascript:gfn_downloadFile('${result.comFileDtlSeq}');">
											<c:out value="${result.fileStreOriNm}" /> </a>
										<br />
									</c:forEach>
								</td>
							</tr>
							</c:if>
							<tr>
								<th height="23">내용</th>
								<td colspan="5">
									<div id="bbs_cn" style="padding:6px 0px 6px 0px;">
										<c:out value="${result.nttContent}" escapeXml="false" />
									</div></td>
							</tr>
						</table>
					</div>
					<div class="replyWrite" style="padding:8px 0px 3px 0px;">
						<label for=""><strong>한줄의견</strong> 
						<input type="text" id="commentContent" name="commentContent" style="width:450px"
							onkeyup="fn_commentByteChk();" placeholder="한줄의견을 입력하세요."/> </label> &nbsp;
						<span class="btn_gray_01"><a href="#" id="insertComment"
							onclick="fn_insertComment('/comBbs/ajaxInsertLibFreeNoticeComment.do', '<c:out value='${trxComBbsVO.comBbsSeq}'/>');">등록</a>
						<span id="currentBytes">0 BYTES / 1,000 BYTES</span> (한글 500자 / 영문 1,000자)</span>
					</div>

					<div class="default_tablestyle">
						<!-- List -->
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<colgroup>
								<col width="40">
								<col width="150">
								<col width="80">
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
									onclick="javascript:fn_retrieveComBbsList(); return false;">목록</a>
									<c:if test="${userId == 'admin' || result.rgstId == userId}">
									<a href="#LINK"
									onclick="javascript:fn_updateComBbsModi(); return false;">수정</a>
									</c:if>
									<a href="#LINK"
									onclick="javascript:fn_insertComBbsReply(); return false;">답글</a>
									<c:if test="${userId == 'admin' || result.rgstId == userId}">
									<a href="#LINK"
									onclick="javascript:fn_deleteComBbsList(); return false;">삭제</a>
									</c:if>
								</td>
							</tr>
						</table>
					</div>
					<!-- 버튼 끝 -->
					<!-- 검색조건 유지 -->
					<input type="hidden" name="selectedId"
						value="<c:out value='${comBbsVO.comBbsSeq}'/>" />
					<input type="hidden" name="searchCondition"
						value="<c:out value='${comBbsVO.searchCondition}'/>" />
					<input type="hidden" name="searchKeyword"
						value="<c:out value='${comBbsVO.searchKeyword}'/>" />
					<input type="hidden" name="pageIndex"
						value="<c:out value='${comBbsVO.pageIndex}'/>" />
					<input type="hidden" name="recordCountPerPage"
						value="<c:out value='${comBbsVO.recordCountPerPage}'/>" />
					<input type="hidden" name="startDate"
						value="<c:out value='${comBbsVO.startDate}'/>" />
					<input type="hidden" name="endDate"
						value="<c:out value='${comBbsVO.endDate}'/>" />
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