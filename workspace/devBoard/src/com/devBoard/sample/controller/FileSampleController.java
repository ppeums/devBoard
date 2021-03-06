package com.devBoard.sample.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devBoard.sample.vo.SampleFileVO;

/**
 * @Class Name : FileSampleController.java
 * @Description : 파일업로드 다운로드 샘플 Controller Class
 * @Modification Information
 * @
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2012. 12. 13.  송제승     최초생성
 * 
 * @author 송제승
 * @since 2012. 12. 13.
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Sinsuldong All right reserved.
 */
@Controller
public class FileSampleController {

	/**
	 * 파일업로드를 위한 샘플 페이지를 조회한다. 
	 * @param loginVO - 로그인 조건이 담긴 VO
	 * @param model
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping(value="/lw/file.xx")
	public String retrieveFile(@ModelAttribute("sampleFileVO") SampleFileVO sampleFileVO, ModelMap model, HttpServletRequest request) throws Exception {

		return "/sample/sampleFile";
	}
}
