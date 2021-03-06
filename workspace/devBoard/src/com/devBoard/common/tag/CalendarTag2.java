package com.devBoard.common.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class CalendarTag2 extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3383750222233669758L;

	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		StringBuffer html = new StringBuffer();
		
		try {
			html.append("<li>");
			html.append("<div class=\"search_leftselect\">");
			html.append("시작일자 : <input name=\"startDate\" id=\"startDate\" class=\"input [{date:true}]\" maxlength=\"10\" size=\"10\" alt=\"시작일\" />");
			html.append("<span id='bt_calendar' class='btn_calendar'><img src='/static/images/calendar/ic_calendar.gif' onclick=\"gfn_openCalendar('startDate', event);\"></span>");
			html.append("</div>");
			html.append("</li>");
			html.append("<li>");
			html.append("<div class=\"search_leftselect\">");
			html.append("~");
			html.append("</div>");
			html.append("</li>");
			html.append("<li>");
			html.append("<div class=\"search_leftselect\">");
			html.append("종료일자 : <input name=\"endDate\" id=\"endDate\" class=\"input [{date:true}]\" maxlength=\"10\" size=\"10\" alt=\"시작일\" />");
			html.append("<span id='bt_calendar' class='btn_calendar'><img src='/static/images/calendar/ic_calendar.gif' onclick=\"gfn_openCalendar('endDate', event);\"></span>");
			html.append("</div>");
			html.append("</li>");
			html.append("<a href=\"#\" onclick=\"fn_weekCalendar()\">1주일</a> | ");
			html.append("<a href=\"#\" onclick=\"fn_halfMonthCalendar()\">15일</a> | ");
			html.append("<a href=\"#\" onclick=\"fn_monthCalendar()\">1개월</a> | ");
			html.append("<a href=\"#\" onclick=\"fn_threeMonthCalendar()\">3개월</a> | ");
			html.append("<a href=\"#\" onclick=\"fn_clearCalendar()\">전체</a>");
			html.append("<script type=\"text/javaScript\">");
			html.append("function fn_weekCalendar(){");
			html.append("gfn_ajaxSubmit(");
			html.append("\"/common/weekCalendar.do\",");
			html.append("\"\",");
			html.append("fn_calendarResult,");
			html.append("false");
			html.append(");");
			html.append("}");
			html.append("function fn_halfMonthCalendar(){");
			html.append("gfn_ajaxSubmit(");
			html.append("\"/common/halfMonthCalendar.do\",");
			html.append("\"\",");
			html.append("fn_calendarResult,");
			html.append("false");
			html.append(");");
			html.append("}");
			html.append("function fn_monthCalendar(){");
			html.append("gfn_ajaxSubmit(");
			html.append("\"/common/monthCalendar.do\",");
			html.append("\"\",");
			html.append("fn_calendarResult,");
			html.append("false");
			html.append(");");
			html.append("}");
			html.append("function fn_threeMonthCalendar(){");
			html.append("gfn_ajaxSubmit(");
			html.append("\"/common/threeMonthCalendar.do\",");
			html.append("\"\",");
			html.append("fn_calendarResult,");
			html.append("false");
			html.append(");");
			html.append("}");
			html.append("function fn_calendarResult(xml){");
			html.append("$(\"#startDate\").val($(xml).find(\"startDate\").text().substring(0, 4)+\".\"+$(xml).find(\"startDate\").text().substring(4, 6)+\".\"+$(xml).find(\"startDate\").text().substring(6, 8));");
			html.append("$(\"#endDate\").val($(xml).find(\"endDate\").text().substring(0, 4)+\".\"+$(xml).find(\"endDate\").text().substring(4, 6)+\".\"+$(xml).find(\"endDate\").text().substring(6, 8));");
			html.append("}");
			html.append("function fn_clearCalendar(xml){");
			html.append("$(\"#startDate\").val(\"\");");
			html.append("$(\"#endDate\").val(\"\");");
			html.append("}");
			html.append("</script>");
			
			out.print(html.toString());
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}

		return SKIP_BODY; // no body processing
	}

    // Releases any resources we may have (or inherit)
    public void release() {
    	super.release();
    }
}
