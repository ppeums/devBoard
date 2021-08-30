<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="validator"
	uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="lw" uri="/WEB-INF/tld/lw.tld"%>
<%
	/**
	 * @Class Name : sampleRegister.jsp
	 * @Description : 카테고리 등록/수정 화면
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
<meta http-equiv="Content-Language" content="utf-8">
<c:set var="registerFlag" value="${sampleVO.id==0 ? '등록' : '수정'}" />
<title>샘플게시판 <c:out value="${registerFlag}" />
</title>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" flush="true" />

<script type="text/javaScript" language="javascript" defer="defer">

/* 글 목록 화면 function */
function fn_retrieveSampleList() {
	var frm = $("#trxSampleVO");

	frm.attr("action", "<c:url value='/sample/retrieveSampleList.do'/>");
	frm.submit();
}

/* 글 등록 function */
function fn_saveSample() {
	var frm = $("#trxSampleVO");

	var confirmMsg = "<spring:message code="${registerFlag == '등록' ? 'confirm.common.insert' : 'confirm.common.save'}"/>";
	
	if(confirm(confirmMsg)!=true) return;
	
	frm.attr("action", "<c:url value="${registerFlag == '등록' ? '/sample/insertSample.do' : '/sample/updateSample.do'}"/>");
	frm.submit();
}

/* 이미지 업로드 시 미리보기 */
/* var InputImage = 
	 (function loadImageFile() {
	    if (window.FileReader) {
	        var ImagePre;
	        var ImgReader = new window.FileReader();
	        var fileType = /^(?:image\/bmp|image\/gif|image\/jpeg|image\/png|image\/x\-xwindowdump|image\/x\-portable\-bitmap)$/i; 
	 
	        ImgReader.onload = function (Event) {
	            if (!ImagePre) {
	                var newPreview = document.getElementById("imagePreview");
	                ImagePre = new Image();
	                ImagePre.style.width = "200px";
	                ImagePre.style.height = "140px";
	                newPreview.appendChild(ImagePre);
	            }
	            ImagePre.src = Event.target.result;
	        };
	 
	        return function () {
	         
	            var img = document.getElementById("upfile").files;
	           
	            if (!fileType.test(img[0].type)) { 
	             alert("이미지 파일만 미리보기를 제공합니다."); 
	             return; 
	            }
	            ImgReader.readAsDataURL(img[0]);
	        }
	    }
	            document.getElementById("imagePreview").src = document.getElementById("upfile").value;
	})(); */
	
	/* //임의의 file object영역
    var files = {};
    var previewIndex = 0;

    // image preview 기능 구현
    // input = file object[]
    function addPreview(input) {
        if (input[0].files) {
            //파일 선택이 여러개였을 시의 대응
            for (var fileIndex = 0; fileIndex < input[0].files.length; fileIndex++) {
                var file = input[0].files[fileIndex];

                if (validation(file.name))
                    continue;

                var reader = new FileReader();
                reader.onload = function(img) {
                    //div id="preview" 내에 동적코드추가.
                    //이 부분을 수정해서 이미지 링크 외 파일명, 사이즈 등의 부가설명을 할 수 있을 것이다.
                    var imgNum = previewIndex++;
                    $("#preview")
                            .append(
                                    "<div class=\"preview-box\" value=\"" + imgNum +"\">"
                                            + "<img class=\"thumbnail\" src=\"" + img.target.result + "\"\/>"
                                            + "<p>"
                                            + file.name
                                            + "</p>"
                                            + "<a href=\"#\" value=\""
                                            + imgNum
                                            + "\" onclick=\"deletePreview(this)\">"
                                            + "삭제" + "</a>" + "</div>");
                    files[imgNum] = file;
                };
                reader.readAsDataURL(file);
            }
        } else
            alert('invalid file input'); // 첨부클릭 후 취소시의 대응책은 세우지 않았다.
    }
    
    $(document).ready(function() {
        //submit 등록. 실제로 submit type은 아니다.
        $('.submit a').on('click',function() {                        
            var form = $('#uploadForm')[0];
            var formData = new FormData(form);
			
            for (var index = 0; index < Object.keys(files).length; index++) {
                //formData 공간에 files라는 이름으로 파일을 추가한다.
                //동일명으로 계속 추가할 수 있다.
                formData.append('files',files[index]);
            }

            //ajax 통신으로 multipart form을 전송한다.
            $.ajax({
                type : 'POST',
                enctype : 'multipart/form-data',
                processData : false,
                contentType : false,
                cache : false,
                timeout : 600000,
                url : '/imageupload',
                dataType : 'JSON',
                data : formData,
                success : function(result) {
                    //이 부분을 수정해서 다양한 행동을 할 수 있으며,
                    //여기서는 데이터를 전송받았다면 순수하게 OK 만을 보내기로 하였다.
                    //-1 = 잘못된 확장자 업로드, -2 = 용량초과, 그외 = 성공(1)
                    if (result === -1) {
                        alert('jpg, gif, png, bmp 확장자만 업로드 가능합니다.');
                        // 이후 동작 ...
                    } else if (result === -2) {
                        alert('파일이 10MB를 초과하였습니다.');
                        // 이후 동작 ...
                    } else {
                        alert('이미지 업로드 성공');
                        // 이후 동작 ...
                    }
                }
                //전송실패에대한 핸들링은 고려하지 않음
            });
        });
        // <input type=file> 태그 기능 구현
        $('#attach input[type=file]').change(function() {
            addPreview($(this)); //preview form 추가하기
        });
    }); */

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
							<strong>글조회</strong>
						</h2>
					</div>
				</div>
				<form:form commandName="trxSampleVO" enctype="multipart/form-data"
					runat="server">
					<div class="modify_user" id="imagePreview">
						<table>
							<tr>
								<th width="15%" height="23" nowrap>제목</th>
								<td width="85%" colspan="5" nowrap="nowrap"><form:input
										path="name" />
								</td>
							</tr>
							<tr>
								<th width="15%" height="23" nowrap>등록자</th>
								<td width="85%" nowrap="nowrap"><form:input path="regUser" />
								</td>
							</tr>
							<tr>
								<th scope="row">첨부파일</th>
								<c:if test="${registerFlag == '등록' }">
									<td><input multiple="multiple" type="file" name="upfile"
										id="upfile" class="" title="첨부파일 첨부" onchange="InputImage();" />
									</td>
								</c:if>
								<c:if test="${registerFlag != '등록' }">
									<td><input multiple="multiple" type="file" name="upfile"
										id="upfile" class="" title="첨부파일 첨부" /><br /> <c:forEach
											var="result" items="${fileList}" varStatus="status">
											<a
												href="javascript:gfn_downloadFile('${result.comFileDtlSeq}');">
												<c:out value="${result.fileStreOriNm}" /> </a>
											<br />
										</c:forEach>
									</td>
								</c:if>
							</tr>

							<tr>
								<th height="23">내용</th>
								<td colspan="5">
									<div id="bbs_cn">
										<form:textarea path="description" rows="20" cols="75"
											readyonly="true" />
									</div></td>
							</tr>
						</table>
					</div>

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
									onclick="javascript:fn_saveSample()(); return false;">저장</a></td>
							</tr>
						</table>
					</div>
					<!-- 버튼 끝 -->
					<!-- 검색조건 유지 -->
					<input type="hidden" name="selectedId"
						value="<c:out value='${sampleVO.id}'/>" />
					<input type="hidden" name="searchCondition"
						value="<c:out value='${sampleVO.searchCondition}'/>" />
					<input type="hidden" name="searchKeyword"
						value="<c:out value='${sampleVO.searchKeyword}'/>" />
					<input type="hidden" name="pageIndex"
						value="<c:out value='${sampleVO.pageIndex}'/>" />
					<input type="hidden" name="recordCountPerPage"
						value="<c:out value='${sampleVO.recordCountPerPage}'/>" />
					<input type="hidden" name="startDate"
						value="<c:out value='${sampleVO.startDate}'/>" />
					<input type="hidden" name="endDate"
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