<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.io.*,java.net.*,java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String objectId = request.getParameter("objectId");
	int startYear = request.getParameter("startYear")==null || request.getParameter("startYear").equals("") ? 0 : Integer.parseInt(request.getParameter("startYear"));
	int endYear = request.getParameter("endYear")==null || request.getParameter("endYear").equals("") ? 0 : Integer.parseInt(request.getParameter("endYear"))+1;
	String callback = request.getParameter("callback")==null ? "" : request.getParameter("callback");
	String fixed = request.getParameter("fixed")==null ? "" : request.getParameter("fixed");
	String disabled = "";
	
	if("true".equals(fixed)) {
		disabled = " disabled='disabled' ";
	}
	
	// 달력객체 생성
	Calendar calendar = Calendar.getInstance();
	
	int tYear = calendar.get(Calendar.YEAR);
	int tMonth = calendar.get(Calendar.MONTH);
	int tDay = calendar.get(Calendar.DAY_OF_MONTH);
	
	int year = 0;
	int month = 0;
	
	if(request.getParameter("year")==null) {
		year = calendar.get(Calendar.YEAR);
	} else {
		year = Integer.parseInt(request.getParameter("year"));
	}
	
	if(request.getParameter("month")==null) {
		month = calendar.get(Calendar.MONTH);
	} else {
		month = Integer.parseInt(request.getParameter("month"));
	}
	
	calendar.set(Calendar.YEAR, year);
	calendar.set(Calendar.MONTH, month);
	calendar.set(Calendar.DAY_OF_MONTH, 1); // WebLogic 10.3 버그로 인해 추가
	
	// Calendar.add()를 사용하면 WebLogic 10.3에서 비정상 처리되므로 아래와 같이 경계값 처리 방식으로 변경
	if(request.getParameter("addYear")!=null && !"".equals(request.getParameter("addYear"))) {
		int addYear = Integer.parseInt(request.getParameter("addYear"));
		
		year += addYear;
		
		calendar.set(Calendar.YEAR, year);
	} else if(request.getParameter("addMonth")!=null && !"".equals(request.getParameter("addMonth"))) {
		int addMonth = Integer.parseInt(request.getParameter("addMonth"));
		
		if(month==0 && addMonth==-1) {
			year--;
			month = 11;
		} else if(month==11 && addMonth==1) {
			year++;
			month = 0;
		} else {
			month += addMonth;
		}
		
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
	}
	
	startYear = startYear==0 ? year-5 : startYear;
	endYear = endYear==0 ? year+5 : endYear;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>달력</title>
<style type="text/css">
/* <![CDATA[ */
body {
	margin:3px 3px 3px 3px;
}

select { 
    font-family:Dotum;
    font-size:1em; 
    padding:2px;
    color:#666;
    border:1px solid #c9c9c9;
    vertical-align:middle;
}

a:link{font-size:9pt; font-family:"굴림"; text-decoration:none; color:#3E3E3E}
a:visited{font-size:9pt; font-family:"굴림"; text-decoration:none; color:#3E3E3E}
a:hover {font-size: 9pt; font-family:"굴림"; text-decoration: underline; color:#F29708;}

.title {
	font-size: 9pt;
	font-family:"굴림";
	color:#504F4F;
	background:#FFFFFF;
}
.date {
	font-size: 9pt;
	font-family:"굴림";
	background:#FFFFFF;
}
/* ]]> */
</style>
<script type="text/javascript" src="<c:url value='/static/js/jquery/jquery-1.6.4.min.js' />"></script>
<script type="text/javascript">
// <![CDATA[
            
    //document.domain = "";  
    
    function f_selectDate(year, month, day) {
		var objectId = "<%=objectId%>";
		var varDate = $('#' + objectId , opener.document)[0]; // IE 버그 때문에 jquery 사용 (IE는 id/name을 구분하지 않음)
		var callback = "<%=callback%>";
		
		if(("" + month).length==1) { month = "0" + month; }
		if(("" + day).length==1) { day = "0" + day; }

		varDate.value = year + "." + month + "." + day;

    	if(callback!="") eval("opener." + callback);
    	
		self.close();
	}

	function f_addYear(addYear) {
		document.getElementById("addYear").value = addYear;

		f_submit();
	}
	
	function f_addMonth(addMonth) {
		document.getElementById("addMonth").value = addMonth;

		f_submit();
	}

	function f_submit() {
		document.getElementById("mainForm").submit();
	}

	function f_init() {
		
	}
// ]]>
</script>
</head>
<body onload="f_init();">
<%--FORM 태그 시작--%>
<form id="mainForm" method="post" action="<c:url value='/common/openCalendar.do' />">
<input type="hidden" name="addYear" id="addYear" />
<input type="hidden" name="addMonth" id="addMonth" />
<input type="hidden" name="objectId" id="objectId" value="<%=objectId%>" />
<input type="hidden" name="callback" id="callback" value="<%=callback%>" />
<table border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td align="center" valign="top" >
      <table border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td width="20%" height="25px" align="center">
            <%
            	if(!"true".equals(fixed)) {
            %>
            <a href="#" onclick="f_addYear(-1);return false;"><img src="<c:url value='/static/images/calendar/left_year.gif'/>" alt="" border="0" align="middle" /></a>
            <a href="#" onclick="f_addMonth(-1);return false;"><img src="<c:url value='/static/images/calendar/left_month.gif'/>" alt="" border="0" align="middle" /></a>
            <%
            	}
            %>
          </td>
          <td width="60%" align="center" class="title">
            <select name="year" id="year" onchange="f_submit();" <%=disabled %>>				
            <%
                String selected = new String();
        		int tempYear = 0;
        		
				for(int i=startYear; i<endYear; i++){
					tempYear = i;
					
					if(year==tempYear) {
						selected = "selected='selected'";
					} else {
						selected = "";
					}
			%>
					<option value = "<%= tempYear %>" <%= selected %>><%= tempYear %></option>
			<%
				}
			%>
				</select>년
				<select name="month" id="month" onchange="f_submit();" <%=disabled %>>
			<%
				for(int i=0; i<=11; i++) {
					if(month==i) {
						selected = "selected='selected'";
					} else {
						selected = "";
					}
			%>
					<option value="<%= i %>" <%= selected %>><%= i+1 %></option>
			<%
				}
			%>
			</select>월
			<!--
			<input type="button" value="이동" style="border-width:1; color:#FFFFFF; background-color:#44A5E9;" class="title"
			       onclick="f_submit();" />
			-->
          </td>
          <td width="20%" align="center" >
            <%
            	if(!"true".equals(fixed)) {
            %>
            <a href="#" onclick="f_addMonth(1);return false;"><img src="<c:url value='/static/images/calendar/right_month.gif'/>" alt="" border="0" align="middle" /></a>
            <a href="#" onclick="f_addYear(1);return false;"><img src="<c:url value='/static/images/calendar/right_year.gif'/>" alt="" border="0" align="middle" /></a>
            <%
            	}
            %>
          </td>
		</tr>
	  </table>
	</td>
  </tr>
  <tr>
    <td height="10"></td>
  </tr>
  <tr>
    <td>
	  <table border="0" cellspacing="1" cellpadding="0" align="center" bgcolor="#CCCCCC">
		<tr align="center" valign="middle" height="25px">
			<td width="30px" class="title"><font color="red">일</font></td>
			<td width="30px" class="title">월</td>
			<td width="30px" class="title">화</td>
			<td width="30px" class="title">수</td>
			<td width="30px" class="title">목</td>
			<td width="30px" class="title">금</td>
			<td width="30px" class="title"><font color="blue">토</font></td>
		</tr>
	<%
		String url = "";
		int tempDay = 0;
		
		while(true){
			tempDay++;
			
			//날짜를 tempDay 값으로 세팅
			calendar.set(Calendar.DAY_OF_MONTH, tempDay);
			
			//달력의 날짜가 다음달로 넘어가면 tempDay 값이랑 달라짐
			if (tempDay != calendar.get(Calendar.DAY_OF_MONTH)) {
				break;
			}
			
			//TR 열기 및 빈칸 만들기
			if (tempDay == 1) {
				out.println("<tr align='center' valign='middle' height='25px'>");
				
				for (int j = 1; j < calendar.get(Calendar.DAY_OF_WEEK); j++) {
					out.print("<td width='30px' class='date'>&nbsp;</td>");
				}
			} else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				out.println("<tr align='center' valign='middle' height='25px'>");
			}
			
			out.println("<td width='30px' class='date'>");
			url = "javascript:f_selectDate('"+ year +"', '" + (month+1) +"', '" + tempDay + "');";
			out.println("<a href=\""+url+"\">");
			
			if(year==tYear && month==tMonth && tempDay==tDay) {
				//오늘일 경우 폰트는 크고 색깔은 보라색으로 표시		
				out.println("<font color='#EE7EAD'><b>");
				out.println(calendar.get(Calendar.DAY_OF_MONTH));
				out.println("</b></font>");
			} else {
				//요일이 일요일이면 빨간색으로 표시
				if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
					out.println("<font color='red'>");
					out.println(calendar.get(Calendar.DAY_OF_MONTH));
					out.println("</font>");					
				} else if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
					//요일이 토요일이면 파란색으로 표시					
					out.println("<font color='blue'>");
					out.println(calendar.get(Calendar.DAY_OF_MONTH));
					out.println("</font>");
				} else {
					out.println(calendar.get(Calendar.DAY_OF_MONTH));
				}
			}
			
			out.println("</a></td>");
			
			//토요일이면 TR 닫음
			if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				out.println("</tr>");
			}
		}
		
		//빈칸 만들기
		if(calendar.get(Calendar.DAY_OF_WEEK)!=1) {
			for(int j=0; j<=7-calendar.get(Calendar.DAY_OF_WEEK); j++) {
				out.println("<td width='30px' class='date'>&nbsp;</td>");
			}
		}
		
		//마지막이 토요일로 끝나지 않았으면 줄을 닫아줌
		if(calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
			out.println("</tr>");
		}		
	%>	
	  </table>
	</td>
  </tr>
</table>
</form>
</body>
</html>