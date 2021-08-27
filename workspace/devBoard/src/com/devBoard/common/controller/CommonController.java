package com.devBoard.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.devBoard.framework.util.DateUtil;
import com.devBoard.framework.view.EgovMapXmlView;

import egovframework.rte.psl.dataaccess.util.EgovMap;

/**  
 * @Class Name : CommonController.java
 * @Description : 화면 처리를 위한 시스템 공통모듈 Controller
 * @Modification Information  
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2012.12.11 송제승          최초생성
 * 
 * @author 송제승
 * @since 2012. 12.11
 * @version 1.0
 * @see
 * 
 *  Copyright (C) by Sinsuldong All right reserved.
 */
@Controller
public class CommonController {
	// private final Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * 달력 팝업을 오픈한다.
	 * 
	 * @param model ModelMap
	 * @return JSP 경로
	 * @throws Exception
	 */
    @RequestMapping(value = "/common/openCalendar.do")
    public String openCalendar(ModelMap model) throws Exception {
    	return "/common/calendarPop";
    }
    
    /**
	 * 달력 날짜를 1주일 셋팅한다.
	 * 
	 * @param model ModelMap
	 * @return JSP 경로
	 * @throws Exception
	 */
    @RequestMapping(value = "/common/weekCalendar.do")
    public ModelAndView weekCalendar(ModelMap model) throws Exception {
    		
    	ModelAndView mav = new ModelAndView(new EgovMapXmlView());
    	
    	EgovMap result = new EgovMap();
    	
    	result.put("startDate", DateUtil.getDate(-7));
		result.put("endDate", DateUtil.getDate());
		
		mav.addObject("map", result);
		
		return mav;
    }
    
    /**
	 * 달력 날짜를 15일 셋팅한다.
	 * 
	 * @param model ModelMap
	 * @return JSP 경로
	 * @throws Exception
	 */
    @RequestMapping(value = "/common/halfMonthCalendar.do")
    public ModelAndView halfMonthCalendar(ModelMap model) throws Exception {
    		
    	ModelAndView mav = new ModelAndView(new EgovMapXmlView());
    	
    	EgovMap result = new EgovMap();
    	
    	result.put("startDate", DateUtil.getDate(-15));
		result.put("endDate", DateUtil.getDate());
		
		mav.addObject("map", result);
		
		return mav;
    }
    
    /**
	 * 달력 날짜를 1개월 셋팅한다.
	 * 
	 * @param model ModelMap
	 * @return JSP 경로
	 * @throws Exception
	 */
    @RequestMapping(value = "/common/monthCalendar.do")
    public ModelAndView monthCalendar(ModelMap model) throws Exception {
    		
    	ModelAndView mav = new ModelAndView(new EgovMapXmlView());
    	
    	EgovMap result = new EgovMap();
    	
    	result.put("startDate", DateUtil.getPreviousMonth());
		result.put("endDate", DateUtil.getDate());
		
		mav.addObject("map", result);
		
		return mav;
    }
    
    /**
	 * 달력 날짜를 3개월 셋팅한다.
	 * 
	 * @param model ModelMap
	 * @return JSP 경로
	 * @throws Exception
	 */
    @RequestMapping(value = "/common/threeMonthCalendar.do")
    public ModelAndView threeMonthCalendar(ModelMap model) throws Exception {
    		
    	ModelAndView mav = new ModelAndView(new EgovMapXmlView());
    	
    	EgovMap result = new EgovMap();
    	
    	result.put("startDate", DateUtil.getDate(2, null, -3));
		result.put("endDate", DateUtil.getDate());
		
		mav.addObject("map", result);
		
		return mav;
    }
}