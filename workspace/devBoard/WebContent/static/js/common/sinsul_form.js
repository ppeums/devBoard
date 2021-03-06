	/* jquery-*.min.js가 필요하다. */	
	
	// String - begin ////////////////////////////////////////////////////////////////////////////
	String.prototype.rtrim = function() {
		var i = this.length;
		
		while(i--) {
			if(this.charCodeAt(i)!=32) {
				return this.substring(0, i+1);
			}
		}

		return "";
	};

	String.prototype.ltrim = function() {
		var i = -1, l = this.length;
		
		while(i++<l) {
			if(this.charCodeAt(i)!=32) {
				return this.substring(i);
			}
		}

		return "";
	};

	String.prototype.trim = function() {
		return this.rtrim().ltrim();
	};
	
	String.prototype.cut = function(start, length) {
	    return this.substring(0, start) + this.substr(start + length);
	};
	
	String.prototype.replaceAll = function(matchStr, newStr) {
		var result = this;
		
		if(matchStr=="") return result;
		
		while(result.indexOf(matchStr)!=-1) {
			result = result.replace(matchStr, newStr);
		}
		
	    return result;
	};
	// String - begin ////////////////////////////////////////////////////////////////////////////
	
	// MaskUtil - begin ////////////////////////////////////////////////////////////////////////////
	function MaskUtil() {}
	
	MaskUtil.prototype.getEventSource = function (evt) {
		if(evt.target) return evt.target;
		else return evt.srcElement;
	};
	
	maskUtil = new MaskUtil();
	// MaskUtil - end ////////////////////////////////////////////////////////////////////////////
	
	// FormMask - begin ////////////////////////////////////////////////////////////////////////////
	function FormMask() {}
	
	FormMask.prototype.getExpressions = function (obj) {
		var result = null;
		var className = obj.className;
		
		if( className!=null && className!="" ) {
			var startInx = className.indexOf("[{");
			
			if(startInx!=-1) {
				result = eval(className.substring(startInx))[0]; // [{required:true,length=3}]
			}
		}
		
		return result;
	};
	
	FormMask.prototype.getMaskChar = function (mask) {
		var tmp = mask.replaceAll("#", "");
		
		if(tmp!="") {
			return tmp.substring(0, 1);
		} else {
			return "";
		}
	};
	
	FormMask.prototype.isCursorMoveKeyCode = function (keyCode) {
		// cf_debug("keyCode : " + keyCode);
		
		var keyCodeArr = [9, 10, 13, 35, 36, 37, 38, 39, 40]; // Tab, LF, CR, End, Home, 좌, 상, 우, 하
		var result = false;
		
		for(var i=0; i<keyCodeArr.length; i++) {
			if(keyCode==keyCodeArr[i]) {
				result = true;
				break;
			}
		}
		
		return result;
	};
	
	FormMask.prototype.isNumericInput = function (evt) {
		var keyCode = evt.keyCode;
		var obj = maskUtil.getEventSource(evt);
		
		// cf_debug("keyCode : " + keyCode);
		
		if(keyCode==16) {
			obj.shiftKey = true;
			return false;
		}
		if(keyCode==17 || keyCode==229) {
			obj.ctrlKey = true;
			return false;
		}
		
		// Backspace:8, Delete:46
        // 0~9:48~57(Shift를 누른 상태는 제외), 0~9(Numeric Pad):96~105
		// Ctrl+C/V 처리, C:67, V:86
		if( keyCode==8 || keyCode==46 ||
			(!obj.shiftKey && keyCode>=48 && keyCode<=57) || (keyCode>=96 && keyCode<=105) ||
			(obj.ctrlKey && (keyCode==67 || keyCode==86)) ||
			this.isCursorMoveKeyCode(keyCode) ) {
			return true;
		} else {
			return false;
		}
	};
	
	FormMask.prototype.maskNumber = function(obj) {
		var orgVal = obj.value;
		var rsltVal = "";
		
		var hasMinusChar = false;
		
		if(orgVal.indexOf("-")==0) {
			hasMinusChar = true;				
		}
		
		// "-" 제거
		orgVal = orgVal.replaceAll("-", "");
		// "," 제거
		orgVal = orgVal.replaceAll(",", "");
		
		var inxDot = orgVal.indexOf(".");			
		var integralValue = "";
		var decimalValue = "";
		
		if(inxDot!=-1) {
			integralValue = orgVal.substring(0, inxDot);
			// "."가 두개 이상인 경우 뒤에 있는 "."는 제거
			decimalValue = orgVal.substring(inxDot+1).replaceAll(".", "");
		} else {
			integralValue = orgVal;
		}
		
		var count = 0;
		
		for(var i=integralValue.length-1; i>=0; i--) {
			count++;
			rsltVal = integralValue.charAt(i) + rsltVal;
			
			if((count)%3==0 && count<integralValue.length) rsltVal = "," + rsltVal;
		}
		
		if(inxDot!=-1) rsltVal += "." + decimalValue;
		
		// "-" 추가
		if(hasMinusChar==true) rsltVal = "-" + rsltVal;
		
		obj.value = rsltVal;
	};
	
	FormMask.prototype.maskDate = function(obj) {
		
		if(event != null && event.keyCode == 8){
			return;
		}
		var orgVal = obj.value.replaceAll(".", "");
		var rsltVal = "";
		
		for(var i=0; i<orgVal.length; i++) {
			rsltVal += orgVal.charAt(i);
			
			if(i==3) rsltVal += ".";
			else if(i==5) rsltVal += ".";
			else if(i==8-1) break;
		}
		
		obj.value = rsltVal;
	};
	
	FormMask.prototype.maskYearMonth = function(obj) {
		var orgVal = obj.value.replaceAll(".", "");
		var rsltVal = "";
		
		for(var i=0; i<orgVal.length; i++) {
			rsltVal += orgVal.charAt(i);
			
			if(i==3) rsltVal += ".";
			else if(i==6-1) break;
		}
		
		obj.value = rsltVal;
	};
	
	FormMask.prototype.maskTime = function(obj) {
		var orgVal = obj.value;
		var rsltVal = "";
		
		// ":" 제거
		orgVal = orgVal.replaceAll(":", "");
		
		for(var i=0; i<orgVal.length; i++) {
			rsltVal += orgVal.charAt(i);
			
			if(i==1) rsltVal += ":";
			else if(i==4-1) break;
		}
		
		obj.value = rsltVal;
	};
	
	FormMask.prototype.maskSsn = function(obj) {
		var orgVal = obj.value;
		var rsltVal = "";
		
		// "-" 제거
		orgVal = orgVal.replace('-', '');
		
		for(var i=0; i<orgVal.length; i++) {
			rsltVal += orgVal.charAt(i);
			
			if(i==5) rsltVal += "-";
			else if(i==13-1) break;
		}
		
		obj.value = rsltVal;
	};
	
	FormMask.prototype.maskBrn = function(obj) {
		var orgVal = obj.value;
		var rsltVal = "";
		
		// "-" 제거
		orgVal = orgVal.replace('-', '').replace('-', '');
		
		for(var i=0; i<orgVal.length; i++) {
			rsltVal += orgVal.charAt(i);
			
			if(i==2) rsltVal += "-";
			else if(i==4) rsltVal += "-";
			else if(i==10-1) break;
		}
		
		obj.value = rsltVal;
	};
	
	FormMask.prototype.maskZip = function(obj) {
		var orgVal = obj.value;
		var rsltVal = "";
		
		// "-" 제거
		orgVal = orgVal.replace('-', '');
		
		for(var i=0; i<orgVal.length; i++) {
			rsltVal += orgVal.charAt(i);
			
			if(i==2) rsltVal += "-";
			else if(i==6-1) break;
		}
		
		obj.value = rsltVal;
	};
	
	FormMask.prototype.maskMask = function(obj, mask) {
		var orgVal = obj.value;
		var rsltVal = "";
		
		// 마스크 문자 제거
		orgVal = orgVal.replaceAll(this.getMaskChar(mask), "");
		
		var tmp = "";
		var j = 0;
		
		for(var i=0; i<mask.length; i++) {
			tmp = mask.substring(i, i+1);
			
			if(tmp=="#") {
				if(orgVal.length-1>=j) {
					rsltVal += orgVal.substring(j, j+1);
					j++;
				} else {
					break;
				}
			} else {
				rsltVal += tmp;
			}
		}
		
		obj.value = rsltVal;
	};
	
	// Common Event Handler ///////////////////////////////////////////////////
	FormMask.prototype.commonNumberTypeKeyupHandler = function (evt, maskChar) {
		var obj = maskUtil.getEventSource(evt);
		var keyCode = evt.keyCode;
		
		if(keyCode==16) obj.shiftKey = false;
		if(keyCode==17 || keyCode==229) obj.ctrlKey = false;
		
		// Ctrl+V에 의한 문자 입력 방지
		if( keyCode==86 && isNaN(obj.value.replaceAll(maskChar, "")) ) {
			alert("숫자 값만 입력 가능합니다.");
			obj.value = "";
		}
	};
	
	// Number Event Handler ///////////////////////////////////////////////////
	FormMask.prototype.numberKeydownHandler = function (evt) {
		return FormMask.prototype.isNumericInput(evt);
	};
	FormMask.prototype.numberKeyupHandler = function (evt) {
		var obj = maskUtil.getEventSource(evt);
		
		FormMask.prototype.commonNumberTypeKeyupHandler(evt, ",");
		
		if(!FormMask.prototype.isCursorMoveKeyCode(evt.keyCode)) {
			// 01, -01와 입력 방지
			if( obj.value.charAt(0)=="0" && obj.value.charAt(1)!="." ) {
				obj.value = "0";
			} else if( obj.value.charAt(0)=="-" && obj.value.charAt(1)=="0" && obj.value.charAt(2)!="." ) {
				obj.value = "-0";
			}
			
			FormMask.prototype.maskNumber(obj);
		}
	};
	FormMask.prototype.numberBlurHandler = function (evt) {
		FormMask.prototype.maskNumber(maskUtil.getEventSource(evt));
	};
	
	// Date Event Handler ///////////////////////////////////////////////////
	FormMask.prototype.dateKeydownHandler = function (evt) {
		return FormMask.prototype.isNumericInput(evt);
	};
	FormMask.prototype.dateKeyupHandler = function (evt) {
		var obj = maskUtil.getEventSource(evt);
		
		FormMask.prototype.commonNumberTypeKeyupHandler(evt, ".");
		
		if(!FormMask.prototype.isCursorMoveKeyCode(evt.keyCode)) {
			FormMask.prototype.maskDate(obj);
		}
	};
	FormMask.prototype.dateBlurHandler = function (evt) {
		FormMask.prototype.maskDate(maskUtil.getEventSource(evt));
	};
	
	// YearMonth Event Handler ///////////////////////////////////////////////////
	FormMask.prototype.yearMonthKeydownHandler = function (evt) {
		return FormMask.prototype.isNumericInput(evt);
	};
	FormMask.prototype.yearMonthKeyupHandler = function (evt) {
		var obj = maskUtil.getEventSource(evt);
		
		FormMask.prototype.commonNumberTypeKeyupHandler(evt, ".");
		
		if(!FormMask.prototype.isCursorMoveKeyCode(evt.keyCode)) {
			FormMask.prototype.maskYearMonth(obj);
		}
	};
	FormMask.prototype.yearMonthBlurHandler = function (evt) {
		FormMask.prototype.maskYearMonth(maskUtil.getEventSource(evt));
	};
	
	// Time Event Handler ///////////////////////////////////////////////////
	FormMask.prototype.timeKeydownHandler = function (evt) {
		return FormMask.prototype.isNumericInput(evt);
	};
	FormMask.prototype.timeKeyupHandler = function (evt) {
		var obj = maskUtil.getEventSource(evt);
		
		FormMask.prototype.commonNumberTypeKeyupHandler(evt, ":");
		
		if(!FormMask.prototype.isCursorMoveKeyCode(evt.keyCode)) {
			FormMask.prototype.maskTime(obj);
		}
	};
	FormMask.prototype.timeBlurHandler = function (evt) {
		FormMask.prototype.maskTime(maskUtil.getEventSource(evt));
	};
	
	// SSN Event Handler ///////////////////////////////////////////////////
	FormMask.prototype.ssnKeydownHandler = function (evt) {
		return FormMask.prototype.isNumericInput(evt);
	};
	FormMask.prototype.ssnKeyupHandler = function (evt) {
		var obj = maskUtil.getEventSource(evt);
		
		FormMask.prototype.commonNumberTypeKeyupHandler(evt, "-");
		
		if(!FormMask.prototype.isCursorMoveKeyCode(evt.keyCode)) {
			FormMask.prototype.maskSsn(obj);
		}
	};
	FormMask.prototype.ssnBlurHandler = function (evt) {
		FormMask.prototype.maskSsn(maskUtil.getEventSource(evt));
	};
	
	// BRN Event Handler ///////////////////////////////////////////////////
	FormMask.prototype.brnKeydownHandler = function (evt) {
		return FormMask.prototype.isNumericInput(evt);
	};
	FormMask.prototype.brnKeyupHandler = function (evt) {
		var obj = maskUtil.getEventSource(evt);
		
		FormMask.prototype.commonNumberTypeKeyupHandler(evt, "-");
		
		if(!FormMask.prototype.isCursorMoveKeyCode(evt.keyCode)) {
			FormMask.prototype.maskBrn(obj);
		}
	};
	FormMask.prototype.brnBlurHandler = function (evt) {
		FormMask.prototype.maskBrn(maskUtil.getEventSource(evt));
	};
	
	// ZIP Event Handler ///////////////////////////////////////////////////
	FormMask.prototype.zipKeydownHandler = function (evt) {
		return FormMask.prototype.isNumericInput(evt);
	};
	FormMask.prototype.zipKeyupHandler = function (evt) {
		var obj = maskUtil.getEventSource(evt);
		
		FormMask.prototype.commonNumberTypeKeyupHandler(evt, "-");
		
		if(!FormMask.prototype.isCursorMoveKeyCode(evt.keyCode)) {
			FormMask.prototype.maskZip(obj);
		}
	};
	FormMask.prototype.zipBlurHandler = function (evt) {
		FormMask.prototype.maskZip(maskUtil.getEventSource(evt));
	};
	
	// Mask Event Handler ///////////////////////////////////////////////////
	FormMask.prototype.maskKeydownHandler = function (evt) {
		
	};
	FormMask.prototype.maskKeyupHandler = function (evt) {
		var obj = maskUtil.getEventSource(evt);
		var exprs = FormMask.prototype.getExpressions(this);
		
		if(!FormMask.prototype.isCursorMoveKeyCode(evt.keyCode)) {
			FormMask.prototype.maskMask(maskUtil.getEventSource(evt), exprs["mask"]);
		}
	};
	FormMask.prototype.maskBlurHandler = function (evt) {
		var exprs = FormMask.prototype.getExpressions(this);
		
		FormMask.prototype.maskMask(maskUtil.getEventSource(evt), exprs["mask"]);
	};
	
	FormMask.prototype.initMask = function (frmObj) {
		$(frmObj).find(":input").each(function() {
			var exprs = FormMask.prototype.getExpressions(this);
			
			if(exprs!=null) {
				// 숫자
				if(exprs["number"]==true) {
					formMask.maskNumber(this);
					$(this).bind("keydown", FormMask.prototype.numberKeydownHandler);
					$(this).bind("keyup", FormMask.prototype.numberKeyupHandler);
					$(this).bind("blur", FormMask.prototype.numberBlurHandler);
				}
				// 날짜
				else if(exprs["date"]==true) {
					formMask.maskDate(this);
					$(this).bind("keydown", FormMask.prototype.dateKeydownHandler);
					$(this).bind("keyup", FormMask.prototype.dateKeyupHandler);
					$(this).bind("blur", FormMask.prototype.dateBlurHandler);
				}
				// 연월
				else if(exprs["yearMonth"]==true) {
					formMask.maskYearMonth(this);
					$(this).bind("keydown", FormMask.prototype.yearMonthKeydownHandler);
					$(this).bind("keyup", FormMask.prototype.yearMonthKeyupHandler);
					$(this).bind("blur", FormMask.prototype.yearMonthBlurHandler);
				}
				// 시간
				else if(exprs["time"]==true) {
					formMask.maskTime(this);
					$(this).bind("keydown", FormMask.prototype.timeKeydownHandler);
					$(this).bind("keyup", FormMask.prototype.timeKeyupHandler);
					$(this).bind("blur", FormMask.prototype.timeBlurHandler);
				}
				// 주민등록번호
				else if(exprs["ssn"]==true) {
					formMask.maskSsn(this);
					$(this).bind("keydown", FormMask.prototype.ssnKeydownHandler);
					$(this).bind("keyup", FormMask.prototype.ssnKeyupHandler);
					$(this).bind("blur", FormMask.prototype.ssnBlurHandler);
				}
				// 사업자등록번호
				else if(exprs["brn"]==true) {
					formMask.maskBrn(this);
					$(this).bind("keydown", FormMask.prototype.brnKeydownHandler);
					$(this).bind("keyup", FormMask.prototype.brnKeyupHandler);
					$(this).bind("blur", FormMask.prototype.brnBlurHandler);
				}
				// 우편번호
				else if(exprs["zip"]==true) {
					formMask.maskZip(this);
					$(this).bind("keydown", FormMask.prototype.zipKeydownHandler);
					$(this).bind("keyup", FormMask.prototype.zipKeyupHandler);
					$(this).bind("blur", FormMask.prototype.zipBlurHandler);
				}
				// 사용자정의 Mask
				else if(exprs["mask"]!=null) {
					formMask.maskMask(this, exprs["mask"]);
					$(this).bind("keydown", FormMask.prototype.maskKeydownHandler);
					$(this).bind("keyup", FormMask.prototype.maskKeyupHandler);
					$(this).bind("blur", FormMask.prototype.maskBlurHandler);
				}
			}
		});
	};
	
	FormMask.prototype.removeMask = function (frmObj) {
		$(frmObj).find(":input").each(function() {
			var exprs = FormMask.prototype.getExpressions(this);
			
			if(exprs!=null) {
				// 숫자
				if(exprs["number"]==true) {
					this.value = this.value.replaceAll(",", "");
				}
				// 날짜
				else if(exprs["date"]==true) {
					this.value = this.value.replaceAll(".", "");
				}
				// 연월
				else if(exprs["yearMonth"]==true) {
					this.value = this.value.replaceAll(".", "");
				}
				// 시간
				else if(exprs["time"]==true) {
					this.value = this.value.replaceAll(":", "");
				}
				// 주민등록번호
				else if(exprs["ssn"]==true) {
					this.value = this.value.replaceAll("-", "");
				}
				// 사업자등록번호
				else if(exprs["brn"]==true) {
					this.value = this.value.replaceAll("-", "");
				}
				// 우편번호
				else if(exprs["zip"]==true) {
					this.value = this.value.replaceAll("-", "");
				}
				// 사용자정의 Mask
				else if(exprs["mask"]!=null) {
					// mask일 때만 mask character를 제거하지 않음
					// this.value = this.value.replaceAll(FormMask.prototype.getMaskChar(exprs["mask"]), "");
				}
			}
		});
	};
	
	FormMask.prototype.checkFileExt = function (frmObj) {
		var result = true;
		
		$(frmObj).find(":input").each(function() {
			if(!result) return; // 첫번째 오류 발생 시 루핑 탈출

			var fileName = this.value;
			
			if(this.type=="file" && fileName!="" && fileName.indexOf(".")!=-1) {
				var policy = G_COMMON.UPLOAD_POLICY;
				var allowExt = G_COMMON.UPLOAD_ALLOW_EXT.split(",");
				var denyExt = G_COMMON.UPLOAD_DENY_EXT.split(",");
				
				var inx = fileName.lastIndexOf(".");
				var ext = fileName.substring(inx+1, fileName.length).toLowerCase();

				if("allow"==policy) {
					result = false;
					
					for(var i=0; i<allowExt.length; i++) {
						if(ext==allowExt[i]) {
							result = true;
							break;
						}
					}
					
					if(!result) alert("업로드할 수 없는 파일입니다.");
				} else if("deny"==policy) {
					result = true;
					
					for(var i=0; i<denyExt.length; i++) {
						if(ext==denyExt[i]) {
							result = false;
							break;
						}
					}
					
					if(!result) alert("업로드할 수 없는 파일입니다.");
				} else {
					result = false;
					alert("업로드 정책이 올바르지 않습니다.");
				}
			} else {
				result = true;
			}
		});
		
		return result;
	};
	
	var formMask = new FormMask();
	// FormMask - end ////////////////////////////////////////////////////////////////////////////
	
	// 암복호화 관련 global variable
	var ENCRYPT_FORM = false;
	// WebFilter 관련 global variable (WebFilter는 $("#webfilterTargetFrame")[0]로 WEBFILTER_ENABLED를 대신함)
	var WEBFILTER_FORM = false;
	var WEBFILTER_RESULT_FLAG = "";
	
	$(document).ready(function() {
		$("form").each(function() {
			// 초기화 처리
			formMask.initMask(this);
			
			// form의 submit 메소드 override
			this._submit = this.submit;
			
			// submit() 호출 시 onsubmit Event가 발생하지 않으므로 Event Handler로 처리하는 방식은 부적절
			// 따라서, submit 메소드를 override하는 방식으로 처리
			this.fireSubmit = function () {
				// 파일 확장자 체크로직 적용
				if(!formMask.checkFileExt(this)) {
					return;
				}
				
				// mask 문자 제거
				formMask.removeMask(this);
				
				// alert("default submit");
				
				//sumit시 진행중이미지 처리
				if(!$(this).attr("target") && $(this).attr('action').indexOf("Excel")==-1){
					var left = ($(window).width() - 32)/2;
					var top = ($(window).height() - 32)/2;
					$("body").append('<div style="position:absolute;left:'+left+'px;top:'+top+'px;"><img src="/static/images/ajax-loader.gif"/></div>');
					
				}
				
				this._submit();
			};
			
			this.submit = this.fireSubmit;
		});
		
		$.fn.encrypt = function () {
			ENCRYPT_FORM = true;
		};
		
		$.fn.webfilter = function () {
			WEBFILTER_FORM = true;
		};
	});