	/* jQuery Custom Plugin Functions */

	/**
	 * Checkbox 체크 여부를 리턴
	 */
	$.fn.isChecked = function() {
		var result = false;
		
		this.each(function() {
			if(this.checked) {
				result = true;
				return false;
			}
		});
		
		return result;
	};
	// 상태 관리를 위한 global 변수
	var G_COMMON = {};
	/**
	 * Companion.JS와 연동하여 디버깅 로그를 출력, Companion.JS 적용을 위해 파일 인코딩을 반드시 UTF-8로 설정
	 * 
	 * @param obj 출력하고자 하는 Object
	 * @return void
	 */
	function gfn_debug(obj) {
		if(window.console) {
			console.debug(obj);
		}
	}
	/**
	 * event를 발생시킨 Event Source를 리턴
	 * 
	 * @param evt event
	 */
	function gfn_getEventSource(evt) {
		if(evt.target) return evt.target;
		else return evt.srcElement;
	};
	/**
	 * 클릭 이벤트 발생 위치를 리턴
	 * @param evt event
	 */
	function gfn_getClickPosition(evt) {
		if(!evt) {
			return {left:0, top:0};
		}
		
		var x, y;
		var obj = gfn_getEventSource(evt);
		
		if(evt.pageX) {
			alert("other: " + evt.pageX);
			x = evt.pageX - obj.offsetLeft;
			y = evt.pageY - obj.offsetTop;
		} else if(evt.clientX) {
			alert("ie: " + evt.clientX);
			x = evt.clientX + document.body.scrollLeft - document.body.clientLeft - obj.offsetLeft;
			y = evt.clientY + document.body.scrollTop - document.body.clientTop - obj.offsetTop;
		} else {
			alert("none");
		}
		
		if(document.body.parentElement && document.body.parentElement.clientLeft) {
			var b = document.body.parentElement;
			x += b.scrollLeft - b.clientLeft;
			y += b.scrollTop - b.clientTop;
		}
		
		// alert(x + "," + y);
		
		return {left:x, top:y};
	}
	// AJAX Callback 함수를 위한 변수
	var ajaxCallback;
	/**
	 * AJAX Submit
	 * 
	 * @param targetUrl Target URL
	 * @param params Parameters
	 * @param callback Callback function
	 * @param disable disable flag
	 * @return void
	 */
	function gfn_ajaxSubmit(targetUrl, params, callback, disable) {
		/*
		if(showImage!=false) {
			var position = gfn_getEventPosition(G_COMMON.lastEvent);
			var style = "position:absolute;left:" + position.left + "px;top:" + position.top + "px;";
			
			$("#ajaxLoaderImageDiv").remove();
			$("body").append("<div id='ajaxLoaderImageDiv' style='" + style + "'><img id='ajaxLoaderImage' src='/static/images/ajax-loader.gif' /></div>");
		}
		*/
		
		if(disable!=false) {
			G_COMMON.ajaxSourceObject = gfn_getEventSource(G_COMMON.lastEvent);
			$(G_COMMON.ajaxSourceObject).attr("disabled", true);
		}
		
		params["AJAX_PARAM"] = "ajax";
		
		ajaxCallback = callback;
		
		$.ajax({
			url:targetUrl,
			type:'POST',
			data:params,
			dataType:'xml',
			timeout:0,
			error:gfn_ajaxSubmitErrorHandler,
			success:gfn_ajaxSubmitCallback
		});
	}
	/**
	 * AJAX Callback function
	 * 
	 * @param xml 서버에서 수신한 XML 데이터
	 * @return void
	 */
	function gfn_ajaxSubmitCallback(xml) {
		// $("#ajaxLoaderImageDiv").remove();
		$(G_COMMON.ajaxSourceObject).attr("disabled", false);
		
		var sessionCheck = "";

		/*
		$(xml).find("item").each(function() {
			sessionCheck = jQeury("sessionCheck", $(this)).text();
		});
		*/
		
		if(sessionCheck=="false") {
			alert("세션이 만료되어 로그인 페이지로 이동합니다.");

			if(opener) {
				opener.top.location.href = "/";
				self.close();
			} else {
				top.location.href = "/";
			}
		} else {
			ajaxCallback(xml);
		}
	}
	/**
	 * AJAX Error Handler
	 * 
	 * @param XMLHttpRequest XMLHttpRequest
	 * @param textStatus Status
	 * @param errorThrown Error
	 * @return void
	 */
	function gfn_ajaxSubmitErrorHandler(XMLHttpRequest, textStatus, errorThrown) {
		// $("#ajaxLoaderImageDiv").remove();
		$(G_COMMON.ajaxSourceObject).attr("disabled", false);

		if(textStatus=="parsererror") {
			alert("서버에서 에러가 발생하였습니다. 관리자에게 문의하십시오.");
		} else if(textStatus=="error") {
			alert("서버가 응답하지 않습니다. 관리자에게 문의하십시오.");
		} else {
			alert("[" + textStatus + "]");
		}
	}
	/**
	 * AJAX 호출 결과 XML을 Select Box Option에 추가
	 * 
	 * @param objId Select Box ID
	 * @param xml XML Data
	 * @param nameAndValue Name, Value로 사용할 Element Name
	 * @param selectedValue 선택된 값
	 */
	function gfn_setSelectBox(objId, xml, nameAndValue, selectedValue) {
		var obj = $("#" + objId);
		
		var nameAndValueArr = nameAndValue.split(",");
		var nameElementName = nameAndValueArr[0];
		var valueElementName = nameAndValueArr[1];
		
		// option 삭제
		obj.empty();

		// option 추가
		$(xml).find("item").each(function() {
			var item = $(this);

       		//cfDebug($("value", item).text() + "/" + $("text", item).text());
			
			// 조회조건 유지
			var selected = "";
			
			if(selectedValue && selectedValue==$(valueElementName, item).text()) {
				selected = "selected";
			}
			
       		var option = "<option value='" + $(valueElementName, item).text() + "' " + selected + ">"
       		           + $(nameElementName, item).text()
                       + "</option>";
       		
			obj.append(option);
		});
	}
	/**
	 * 달력 팝업 오픈
	 * 
	 * @param objectId
	 * @param evt
	 * @param startYear
	 * @param endYear
	 * @param callback
	 * @param fixed
	 * @return void
	 */
	function gfn_openCalendar(objectId, evt, startYear, endYear, callback, fixed) {
		var extendParam = "";

		if(startYear!=null && startYear!="" && endYear!=null && endYear!="") {
			extendParam += "&startYear=" + startYear + "&endYear=" + endYear;
		}

		if(callback!=null && callback!="") {
			extendParam += "&callback=" + callback;
		}

		var orgVal = $("#" + objectId).val();
		if(orgVal != ''){
			orgVal = orgVal.replaceAll(".", "");
			var year = orgVal.substring(0, 4);
			var month = parseInt(orgVal.substring(4, 6), 10) - 1; // 0으로 시작하면 반드시 10진수 처리
			
			extendParam += "&year=" + year;
			extendParam += "&month=" + month;
		}
		
		var url = "/common/openCalendar.do?objectId=" + objectId + extendParam;
		var winName = "Calendar";
		var winProps = "toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=no,resizable=yes,"
		             + "width=230px,height=225px,left=" + evt.screenX + ",top=" + evt.screenY;
		var win = window.open(url, winName, winProps);

		win.focus();
	}
	/**
	 * 파일 다운로드
	 * 
	 * @param fileId 파일 아이디
	 * @return void
	 */
	function gfn_downloadFile(fileId) {
		$("#fileDownload").remove();
		$("body").append("<iframe id='fileDownload' width='0' height='0' />");
		$("#fileDownload")[0].src = encodeURI("/common/downloadFile.do?fileId="+fileId);
	}
	/**
	 * 파일이력 다운로드
	 * 
	 * @param fileId 파일 아이디
	 * @param menuKey 메뉴키
	 * @return void
	 */
	function gfn_downloadFileHist(fileId, menuKey) {
		$("#fileDownload").remove();
		$("body").append("<iframe id='fileDownload' width='0' height='0' />");
		$("#fileDownload")[0].src = encodeURI("/common/downloadFileHist.do?fileId="+fileId+"&menuKey="+menuKey);
	}
	/**
	 * 테이블 hover시 Lhover 클래스 부여
	 */
	function gfn_applyStyleListTable () {
		$(".LblockListTable table tr").each(function() {
			var tr = $(this);
			
			tr.mouseover(function() {
				tr.addClass("Lhover");
			});
			tr.mouseout(function() {
				tr.removeClass("Lhover");
			});
		});
	}
	/**
	 * 날짜를 비교하여 차이값을 정수값으로 리턴,
	 * 0이면 동일한 날짜,
	 * 양수이면 fromDateStr가 toDateStr보다 큼
	 * 음수이면 fromDateStr가 toDateStr보다 작음
	 *
	 * @param fromDateStr 시작일자
	 * @param toDateStr 종료일자
	 * @return 차이값
	 */
	function gfn_compareDate(fromDateStr, toDateStr) {
		var fromDateArr = fromDateStr.split("-");
		var toDateArr = toDateStr.split("-");
		var fromDate = new Date(parseInt(fromDateArr[0]), parseInt(fromDateArr[1])-1, parseInt(fromDateArr[2]));
		var toDate = new Date(parseInt(toDateArr[0]), parseInt(toDateArr[1])-1, parseInt(toDateArr[2]));
		var gap = Math.floor( (fromDate.getTime() - toDate.getTime()) / (60*60*24*1000) );

		return gap;
	}
	/**
	 * Cookie 설정
	 */
	function gfn_setCookie(name, value, expires, path, domain, secure) {
		var today = new Date();
		today.setTime(today.getTime());
		
		if(expires) {
			expires = expires * 1000 * 60 * 60 * 24; // x number of days
		}
		
		var expiresDate = new Date(today.getTime() + expires);
		
		document.cookie = name + "=" + escape(value)
		                + ( expires ? ";expires="+expiresDate.toGMTString() : "" )
		                + ( path ? ";path="+path : "" )
		                + ( domain ? ";domain="+domain : "" )
		                + ( secure ? ";secure" : "" );
	}
	/**
	 * Cookie 가져오기
	 */
	function gfn_getCookie(name) {
		var bInx = document.cookie.indexOf(name);
		var eInx = 0;
		
		if(bInx==-1) return "";
		
		bInx = bInx + name.length + 1;
		eInx = document.cookie.indexOf(";", bInx);
		
		if(eInx==-1) eInx = document.cookie.length;
		
		var result = document.cookie.substring(bInx, eInx);
		
		return unescape(result);
	}
	/**
	 * URL로부터 File Name을 리턴
	 */
	function gfn_getFileNameFromUrl(url) {
		var fileName = url.substring(url.lastIndexOf("/") + 1);
		return fileName;
	}
	/**
	 * 월 선택에 따른 일자 SelectBox 값 변경.
	 */
	function gfn_setDayRange(selMonth, selDay){
		
		var arr = null;
		var arr1 = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31'];
		var arr2 = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30'];
		var arr3 = ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28'];
		
		
		var this_ = $("#" + selMonth);
		var obj = $("#" + selDay);
		
		if( obj != null){
			
			obj.empty();
			if(this_.val() == '02'){
				arr = arr3;
			}else if(this_.val() == '01' ||
					this_.val() == '03' ||
					this_.val() == '05' ||
					this_.val() == '07' ||
					this_.val() == '08' ||
					this_.val() == '10' || 
					this_.val() == '12'){
				arr = arr1;    				
			} else {
				arr = arr2;
			}
			
			for(i = 0; i < arr.length; ++i){
				obj.append("<option value=\"" + arr[i] + "\">" + arr[i] +"</option>");
			}
		} 
	}
	/**
	 * 페이지 로드 시 초기화 함수
	 */
	$(document).ready(function() {
		$(document).bind("click", function(event) {
			G_COMMON.clickEvent = event;
			G_COMMON.lastEvent = event;
		});
		$(document).bind("keydown", function(event) {
			G_COMMON.keydownEvent = event;
			G_COMMON.lastEvent = event;
		});
		
		// gfn_applyStyleListTable();
	});
	function fn_tinyEditor(){
		//tiny 에디터
		 $('textarea.tinymce').tinymce({
			 
			//스킨설정
			//skin : "o2k7"
			//,skin_variant : "silver"
			// Location of TinyMCE script
			script_url : '/static/js/editor/tiny_mce.js'
				
			//에디터 너비 높이 설정
			,height : "300"
			,wigth  : "750"
			 
			,language : "ko"
			
			,theme_advanced_path : false
			 
			//IE에서 한글입력 문제 해결을 위해서
			,forced_root_block : false 
			,force_br_newlines : true
			,force_p_newlines : false
			
			,mode : "textareas"
			,readonly : false	
			// General options
			,theme : "advanced"
			,plugins : "autolink,lists,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,advlist"

			// Theme options
			/*
			,theme_advanced_buttons1 : "save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,styleselect,formatselect,fontselect,fontsizeselect"
			,theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor"
			,theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen"
			,theme_advanced_buttons4 : "insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak"
			*/
			,theme_advanced_buttons1 : "bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,fontselect,fontsizeselect"
			,theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,|,undo,redo,|,link,unlink,image,code,|,insertdate,inserttime,preview,|,forecolor,backcolor"
			,theme_advanced_buttons3 : ""
			,theme_advanced_buttons4 : ""
			
			,theme_advanced_toolbar_location : "top"
			,theme_advanced_toolbar_align : "left"
			,theme_advanced_statusbar_location : "bottom"
			,theme_advanced_resizing : false
			
			// Example content CSS (should be your site CSS)
			,content_css : "/static/js/editor/css/content.css"

			//에디터에 사용할 폰트 지정 
			,theme_advanced_fonts : "굴림=굴림;굴림체=굴림체;궁서=궁서;궁서 체=궁서체;돋움=돋움;돋움체=돋움체;바탕=바탕;바탕체=바탕체;Arial=Arial; Comic Sans MS='Comic Sans MS';Courier New='Courier New';Tahoma=Tahoma;Times New Roman='Times New Roman';Verdana=Verdana" 
			   
			// Drop lists for link/image/media/template dialogs
			,template_external_list_url : "lists/template_list.js"
			,external_link_list_url : "lists/link_list.js"
			,external_image_list_url : "lists/image_list.js"
			,media_external_list_url : "lists/media_list.js"

			// Replace values for the template plugin
			,template_replace_values : {
				 username	: "Some User"
				,staffid	: "991234"
			}
		 });
	}
	/**
	 * 영문과 숫자만 입력
	 * @param obj
	 * @returns {Boolean}
	 */
	function onOnlyAlphaNumber(obj) {
		str = obj.value; 
		len = str.length; 
		ch = str.charAt(0);
			for(i = 0; i < len; i++) { 
				ch = str.charAt(i); 
				if( (ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') ) { 
					continue; 
				} else { 
					alert("영문과 숫자만 입력이 가능합니다.");
					obj.value="";
					obj.focus();
					return false; 
				} 
			}
		return true; 
	} 
	/**
	 * 영문과 숫자 .만 입력
	 * @param obj
	 * @returns {Boolean}
	 */
	function onOnlyAlphaNumberDot(obj) {
		str = obj.value; 
		len = str.length; 
		ch = str.charAt(0);
			for(i = 0; i < len; i++) { 
				ch = str.charAt(i); 
				if( (ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch == '.') ) { 
					continue; 
				} else { 
					alert("영문과 숫자 .만 입력이 가능합니다.");
					obj.value="";
					obj.focus();
					return false; 
				} 
			}
		return true; 
	} 
	/**
	 * .과 숫자만 입력
	 * @param obj
	 * @returns {Boolean}
	 */
	function onOnlyNumberDot(obj) {
		str = obj.value; 
		len = str.length; 
		ch = str.charAt(0);
			for(i = 0; i < len; i++) { 
				ch = str.charAt(i); 
				if( (ch >= '0' && ch <= '9') || (ch == '.')) { 
					continue; 
				} else { 
					alert(".과 숫자만 입력이 가능합니다.");
					obj.value="";
					obj.focus();
					return false; 
				} 
			}
		return true; 
	} 
	/**
	 * -과 숫자만 입력
	 * @param obj
	 * @returns {Boolean}
	 */
	function onOnlyNumberMinus(obj) {
		str = obj.value; 
		len = str.length; 
		ch = str.charAt(0);
			for(i = 0; i < len; i++) { 
				ch = str.charAt(i); 
				if( (ch >= '0' && ch <= '9') || (ch == '-')) { 
					continue; 
				} else { 
					alert("-과 숫자만 입력이 가능합니다.");
					obj.value="";
					obj.focus();
					return false; 
				} 
			}
		return true; 
	} 
	/**
	 * 숫자만 입력
	 * @param obj
	 * @returns {Boolean}
	 */
	function onOnlyNumber(obj) {
		str = obj.value; 
		len = str.length; 
		ch = str.charAt(0);
			for(i = 0; i < len; i++) { 
				ch = str.charAt(i); 
				if( (ch >= '0' && ch <= '9')) { 
					continue; 
				} else { 
					alert("숫자만 입력이 가능합니다.");
					obj.value="";
					obj.focus();
					return false; 
				} 
			}
		return true; 
	} 
	/**
	 * 숫자만 입력
	 * @param obj
	 * @returns {Boolean}
	 */
	function onOnlyNumberZero(obj) {
		str = obj.value; 
		len = str.length; 
		ch = str.charAt(0);
			for(i = 0; i < len; i++) { 
				ch = str.charAt(i); 
				if( (ch >= '0' && ch <= '9') || (ch == ',')) { 
					continue; 
				} else { 
					alert("숫자만 입력이 가능합니다.");
					obj.value="0";
					obj.focus();
					return false; 
				} 
			}
		return true; 
	} 
	//한줄의견 등록
	function fn_insertComment(url, comBbsSeq){
		$.ajax({
			type: "post"
			,url: url
			,dataType: "JSON"
			,data: {
				  "comBbsSeq": comBbsSeq
				, "comBbsCommentSeq" : $("#comBbsCommentSeq").val()
				, "commentContent": $("#commentContent").val()
			}
			,success: function(data) {
				$("#commentList").html("");
				$("#comBbsCommentSeq").val(0);
				alert(data.resultList);
				fn_searchCommentList('1','/comBbs/ajaxLibFreeNoticeCommentList.do', comBbsSeq, '/comBbs/ajaxDeleteLibFreeNoticeComment.do');
			}
			,error: function(data, status, err) {
				alert("[댓글 등록] 서버와의 통신이 원활하지 않습니다.\n잠시후에 다시 시도해 주세요.");
			}
		});
	}
	//한줄의견 조회
	function fn_searchCommentList(page, url, comBbsSeq, url2, userId){
		$.ajax({
			type: "post"
				,url: url
				,dataType: "JSON"
				,data: {
					  "comBbsSeq": comBbsSeq
				//	, "comCommentSeq" : id
					, "pageIndex" : page
			}
			,success: function(data) {
				var comment = "";
				var pageTag = "";
			
				$.each(data.resultList, function(idx, item) {
					
					comment += "<tr>";
					comment += "</tr>";
					
					comment += "<tr>";
					comment += "<td>"+ item.writeId +"</td>";
					comment += "<td class=\"subject\" id=\"subject_"+ item.comBbsCommentSeq +"\"><div class=\"commentTitle\">" + item.commentContent + "</div></td>";
					comment += "<td>"+ item.rgstDt + "</td>";
					//System.out.println("[댓글 조회] 댓글 작성일: " + item.rgstDt);
					//alert("[댓글 조회] 댓글 작성일: " + item.rgstDt);
					comment += "<td>";
					if(userId == "admin" || item.rgstId == userId){
						comment += "	<a href=\"#;\"><img src=\"/static/images/common/ico_delete.png\" alt=\"삭제\" onclick=\"fn_deleteComment('"+ item.comBbsCommentSeq + "', '"+url2+"', '"+comBbsSeq+"');\"/></a>";
					}
					/*else{
						comment += "	<img src=\"/static/images/common/ico_delete_gray.png\"/></a>";
					}*/
					comment += "</td>";
					comment += "</tr>";
					
				});
				$("#commentList").html(comment);
				$("#commentContent").val("");
				fn_commentByteChk();
				
			}
			,error: function(data, status, err) {
				alert("[댓글 조회] 서버와의 통신이 원활하지 않습니다.\n잠시후에 다시 시도해 주세요.");
			}
		});
		
	}
	//한줄의견 삭제
	function fn_deleteComment(id, url, comBbsSeq){
		if( confirm("삭제하시겠습니까?")){
			$.ajax({
				type: "post"
				,url: url
				,dataType: "JSON"
				,data: {
					  "comBbsSeq": comBbsSeq
					, "comBbsCommentSeq" : id
				}
				,success: function(data) {
					$("#commentList").html("");
					fn_searchCommentList('1','/comBbs/ajaxLibFreeNoticeCommentList.do', comBbsSeq, '/comBbs/ajaxDeleteLibFreeNoticeComment.do');
				}
				,error: function(data, status, err) {
					alert("[댓글 삭제] 서버와의 통신이 원활하지 않습니다.\n잠시후에 다시 시도해 주세요.");
				}
			});
		}
	}
	/*//첨부파일 조회
	function fn_searchComFile(page, url, comBbsSeq, url2, userId){
		$.ajax({
			type: "post"
				,url: url
				,dataType: "JSON"
				,data: {
					  "comBbsSeq": comBbsSeq
				//	, "comCommentSeq" : id
					, "pageIndex" : page
			}
			,success: function(data) {
				var comment = "";
				var pageTag = "";
			
				$.each(data.resultList, function(idx, item) {
					
					comment += "<tr>";
					comment += "</tr>";
					
					comment += "<tr>";
					comment += "<td>"+ item.writeId +"</td>";
					comment += "<td class=\"subject\" id=\"subject_"+ item.comBbsCommentSeq +"\"><div class=\"commentTitle\">" + item.commentContent + "</div></td>";
					comment += "<td>"+ item.rgstDt + "</td>";
					//System.out.println("[댓글 조회] 댓글 작성일: " + item.rgstDt);
					//alert("[댓글 조회] 댓글 작성일: " + item.rgstDt);
					comment += "<td>";
					if(userId == "admin" || item.rgstId == userId){
						comment += "	<a href=\"#;\"><img src=\"/static/images/common/ico_delete.png\" alt=\"삭제\" onclick=\"fn_deleteComment('"+ item.comBbsCommentSeq + "', '"+url2+"', '"+comBbsSeq+"');\"/></a>";
					}
					else{
						comment += "	<img src=\"/static/images/common/ico_delete_gray.png\"/></a>";
					}
					comment += "</td>";
					comment += "</tr>";
					
				});
				$("#commentList").html(comment);
				$("#commentContent").val("");
				fn_commentByteChk();
				
			}
			,error: function(data, status, err) {
				alert("[첨부파일 조회] 서버와의 통신이 원활하지 않습니다.\n잠시후에 다시 시도해 주세요.");
			}
		});
	//첨부파일 삭제
	function fn_deleteComFile(id, url, comBbsSeq){
		if( confirm("삭제하시겠습니까?")){
			$.ajax({
				type: "post"
				,url: url
				,dataType: "JSON"
				,data: {
					  "comBbsSeq": comBbsSeq
					, "comFileSeq" : id
				}
				,success: function(data) {
					$("#fileList").html("");
					fn_searchComFile('1','/comBbs/ajaxLibFreeNoticeCommentList.do', comBbsSeq, '/comBbs/ajaxDeleteLibFreeNoticeComment.do');
				}
				,error: function(data, status, err) {
					alert("[첨부파일 삭제] 서버와의 통신이 원활하지 않습니다.\n잠시후에 다시 시도해 주세요.");
				}
			});
		}
	}*/
	
	
	
	/**
	* 한글을 2바이트 씩 계산하여 입력받은 문자열이 DB에 저장될 때 총 몇바이트를 차지하는지 계산한다.
	* 엔터(\r\n)는 2바이트를 차지한다.
	* @param val : 입력받은 문자열
	*/
	function fn_byteElength(val)
	{
		// 입력받은 문자열을 escape() 를 이용하여 변환한다.
		// 변환한 문자열 중 유니코드(한글 등)는 공통적으로 %uxxxx로 변환된다.
		var temp_estr = escape(val);
		var s_index = 0;
		var e_index = 0;
		var temp_str = "";
		var cnt = 0;
		// 문자열 중에서 유니코드를 찾아 제거하면서 갯수를 센다.
		while ((e_index = temp_estr.indexOf("%u", s_index)) >= 0) // 제거할 문자열이 존재한다면
		{
		temp_str += temp_estr.substring(s_index, e_index);
		s_index = e_index + 6;
		cnt ++;
		}
		temp_str += temp_estr.substring(s_index);
		temp_str = unescape(temp_str); // 원래 문자열로 바꾼다.
		// 유니코드는 2바이트 씩 계산하고 나머지는 1바이트씩 계산한다.
		return ((cnt * 2) + temp_str.length) + "";
	}
	//댓글 Byte검사
	function fn_commentByteChk() {
		var cmntCntn = $("#commentContent").val();
		var byte = fn_byteElength(cmntCntn);
		var tmpTag = byte + " / 1,000 Bytes";
		$("#currentBytes").html(tmpTag);
		
		if (byte > 1000) {
			alert("글자를 초과 입력할 수 없습니다.");
			$("#commentContent").val(checkComment);
			tmpTag = beforeByte;
			$("#currentBytes").html(tmpTag);
		}else{
			checkComment = $("#commentContent").val();
			beforeByte = tmpTag;
		}
	}