package com.devBoard.comBbs.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.devBoard.comBbs.service.ComBbsService;
import com.devBoard.comBbs.vo.ComBbsVO;
import com.devBoard.common.security.UserSession;
import com.devBoard.framework.util.PaginationUtil;
import com.devBoard.framework.util.StringUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * @Class Name : ComBbsController.java
 * @Description : 답변형게시판을 처리하는 Controller Class
 * @Modification Information  
 * @
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2014. 2. 6. 송제승          최초생성
 * 
 * @author 송제승
 * @since 2014. 2. 6.
 * @version 1.0
 * @see
 * 
 * Copyright (C) 2014 by Sinsuldong All right reserved.
 */
@Controller
@SessionAttributes("trxComBbsVO")
public class ComBbsController {
	
	/** 게시판 처리를 위한  service */
	@Resource(name = "comBbsService")
	private ComBbsService comBbsService;
	
	/**
	 * 글 목록을 조회한다. (paging)
	 * @param comBbsVO - 조회 조건이 담긴 VO
	 * @param model
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping(value="/comBbs/retrieveComBbsList.do")
	public String retrieveComBbsList(@ModelAttribute("comBbsVO") ComBbsVO comBbsVO, Model model) throws Exception {
		
		// 페이지 정보 설정
		PaginationInfo paginationInfo = PaginationUtil.getPaginationInfo(comBbsVO);
		
		// 글 목록
		List<ComBbsVO> resultList = comBbsService.retrieveComBbsList(comBbsVO);
		List<ComBbsVO> resultNoticeList = comBbsService.retrieveComBbsList(comBbsVO);
		
		model.addAttribute("resultNoticeList", resultNoticeList);
		model.addAttribute("resultList", resultList);
		
		// 건수
		paginationInfo.setTotalRecordCount(comBbsService.retrieveComBbsListCount(comBbsVO));
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "/comBbs/comBbsList";
	}
	
	/**
	 * 글 등록 화면을 조회한다.
	 * @param comBbsVO - 조회 조건이 담긴 VO
	 * @param model
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping("/comBbs/insertComBbsReg.do")
	public String insertComBbsReg(@ModelAttribute("comBbsVO") ComBbsVO comBbsVO, Model model, HttpServletRequest request) throws Exception {
		
		comBbsVO.setRgstId(UserSession.getUserId(request));
		model.addAttribute("trxComBbsVO", comBbsVO); // SessionAttributes명과 일치
		
		return "/comBbs/comBbsReg";
	}
	
	/**
	 * 글을 등록한다.
	 * @param comBbsVO - 조회 조건, 등록 정보가 담긴 VO (ModelAttribute명은 SessionAttributes명과 일치)
	 * @param bindingResult
	 * @param model
	 * @param status
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping("/comBbs/insertComBbs.do")
	public String insertComBbs(@ModelAttribute("trxComBbsVO") ComBbsVO comBbsVO, BindingResult bindingResult, Model model, SessionStatus status, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		comBbsVO.setRgstId(UserSession.getUserId(request));// 작성자 ID
		comBbsVO.setPrntsNo("0");
		comBbsVO.setReplyLoc("1");
		// 작성글 등록
		comBbsService.insertComBbs(comBbsVO);
		status.setComplete(); // 세션 객체 삭제
		
		request.setAttribute("msgCd", "info.common.insert");
		return "forward:/comBbs/retrieveComBbsList.do";
	}
	
	/**
	 * 글을 조회한다.
	 * @param comBbsVO - 상세 조회 Key, 조회 조건이 담긴 VO
	 * @param model
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping("/comBbs/retrieveComBBsView.do")
	public String retrieveLibFreeNoticeView(@ModelAttribute("comBbsVO") ComBbsVO comBbsVO, Model model) throws Exception {
		
		comBbsVO.setComBbsSeq(Integer.parseInt(StringUtil.nvl(comBbsVO.getSelectedId(), "0")));
		ComBbsVO resultVO = null;
		
		resultVO = comBbsService.retrieveComBbsView(comBbsVO);
		resultVO.setNttContent(resultVO.getNttContent().replaceAll("\r\n", "<br/>"));
		model.addAttribute("result", resultVO);
		
		return "/comBbs/comBbsView";
	}
	
	/**
	 * 글 수정화면을 조회한다.
	 * @param selectedId - 상세 조회 Key
	 * @param comBbsVO - 조회 조건이 담긴 VO
	 * @param model
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping("/comBbs/updateComBbsModi.do")
	public String updateComBbsModi(@ModelAttribute("comBbsVO") ComBbsVO comBbsVO, Model model) throws Exception {
		
		comBbsVO.setComBbsSeq(Integer.parseInt(StringUtil.nvl(comBbsVO.getSelectedId(), "0")));
		ComBbsVO resultVO = null;
		resultVO = comBbsService.retrieveComBbsView(comBbsVO);
		
		model.addAttribute("trxComBbsVO", resultVO); // SessionAttributes명과 일치
		
		return "/comBbs/comBbsModi";
	}
	
	/**
	 * 글을 수정한다.
	 * @param comBbsVO - 조회 조건, 수정 정보가 담긴 VO (ModelAttribute명은 SessionAttributes명과 일치)
	 * @param bindingResult
	 * @param model
	 * @param status
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping("/comBbs/updateComBbs.do")
	public String updateComBbs(@ModelAttribute("trxComBbsVO") ComBbsVO comBbsVO,
			BindingResult bindingResult, Model model, SessionStatus status, HttpServletRequest request, HttpServletResponse response) throws Exception {
		comBbsVO.setUpdId(UserSession.getUserId(request));// 작성자 ID
		comBbsVO.setComBbsSeq(Integer.parseInt(StringUtil.nvl(comBbsVO.getSelectedId(), "0")));
		// 작성글 수정
		comBbsService.updateComBbs(comBbsVO);
		status.setComplete(); // 세션 객체 삭제
		
		request.setAttribute("msgCd", "info.common.update");	
		return "forward:/comBbs/retrieveComBbsList.do";
	}
	
	/**
	 * 글을 삭제한다.
	 * @param sampleVO - 조회 조건, 수정 정보가 담긴 VO (ModelAttribute명은 SessionAttributes명과 일치)
	 * @param bindingResult
	 * @param model
	 * @param status
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping("/comBbs/deleteComBbsList.do")
	public String deleteComBbsList(@ModelAttribute("comBbsVO") ComBbsVO comBbsVO,
			BindingResult bindingResult, Model model, SessionStatus status, HttpServletRequest request, HttpServletResponse response) throws Exception {
		comBbsVO.setUpdId(UserSession.getUserId(request));
		comBbsVO.setComBbsSeq(Integer.parseInt(comBbsVO.getSelectedId()));
		comBbsService.delteComBbs(comBbsVO);
		status.setComplete(); // 세션 객체 삭제
		
		request.setAttribute("msgCd", "info.common.delete");
		return "forward:/comBbs/retrieveComBbsList.do";
	}

	/**
	 * 글 답글화면을 조회한다.
	 * @param selectedId - 상세 조회 Key
	 * @param comBbsVO - 조회 조건이 담긴 VO
	 * @param model
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping("/comBbs/insertComBbsReply.do")
	public String insertComBbsReply(@ModelAttribute("comBbsVO") ComBbsVO comBbsVO, Model model) throws Exception {
		
		comBbsVO.setComBbsSeq(Integer.parseInt(StringUtil.nvl(comBbsVO.getSelectedId(), "0")));
		ComBbsVO resultVO = null;
		resultVO = comBbsService.retrieveComBbsView(comBbsVO);
		ComBbsVO comBbsVO1 = new ComBbsVO();
		comBbsVO1.setNttTitle("답변 : " + resultVO.getNttTitle());
		comBbsVO1.setRgstId(resultVO.getRgstId());
		comBbsVO1.setNttContent(" " + "\r\n\r\n================ 원본메시지 ===============\r\n\r\n"+StringUtil.nvl(resultVO.getNttContent(), ""));
		comBbsVO1.setPrntsNo(resultVO.getPrntsNo());
		comBbsVO1.setReplyLoc(resultVO.getReplyLoc());
		comBbsVO1.setComBbsSeq(resultVO.getComBbsSeq());
		model.addAttribute("trxComBbsVO", comBbsVO1); // SessionAttributes명과 일치
		
		return "/comBbs/comBbsReply";
	}
	
	/**
	 * 글을 등록한다.
	 * @param comBbsVO - 조회 조건, 등록 정보가 담긴 VO (ModelAttribute명은 SessionAttributes명과 일치)
	 * @param bindingResult
	 * @param model
	 * @param status
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping("/comBbs/insertComBbsReply1.do")
	public String insertComBbsReply1(@ModelAttribute("trxComBbsVO") ComBbsVO comBbsVO, BindingResult bindingResult, Model model, SessionStatus status, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		comBbsVO.setRgstId(UserSession.getUserId(request));// 작성자 ID
		
		comBbsVO.setPrntsNo(String.valueOf(comBbsVO.getComBbsSeq()));
		comBbsVO.setReplyLoc(String.valueOf(Integer.parseInt(comBbsVO.getReplyLoc())+1));
		
		// 작성글 등록
		comBbsService.insertComBbs(comBbsVO);
		status.setComplete(); // 세션 객체 삭제
		
		request.setAttribute("msgCd", "info.common.insert");
		return "forward:/comBbs/retrieveComBbsList.do";
	}
}