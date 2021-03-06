package com.devBoard.comBbs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.devBoard.comBbs.service.ComBbsService;
import com.devBoard.comBbs.vo.ComBbsCommentVO;
import com.devBoard.comBbs.vo.ComBbsVO;
import com.devBoard.common.security.UserSession;
import com.devBoard.common.service.FileService;
import com.devBoard.framework.util.MultipartFileUtil;
import com.devBoard.framework.util.PaginationUtil;
import com.devBoard.framework.util.StringUtil;
import com.devBoard.framework.vo.FileVO;

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
	
	/** 첨부파일 처리를 위한  service */
	@Resource(name = "fileService")
	private FileService fileService;
	
	private Integer getId;
	
	/**
	 * 글 목록을 조회한다. (paging)
	 * @param comBbsVO - 조회 조건이 담긴 VO
	 * @param model
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping(value="/comBbs/retrieveComBbsList.do")
	public String retrieveComBbsList(@ModelAttribute("comBbsVO") ComBbsVO comBbsVO, Model model, HttpServletRequest request) throws Exception {
		
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
		
		String userId = UserSession.getUserId(request);
		System.out.println("[글 목록 조회] 세션 아이디: " + userId);
		model.addAttribute("userId", userId);
		
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
		
		String userId = UserSession.getUserId(request);
		System.out.println("[글 등록화면 조회] 세션 아이디: " + userId);
		model.addAttribute("userId", userId);
		
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
	public String insertComBbs(@ModelAttribute("trxComBbsVO") ComBbsVO comBbsVO, BindingResult bindingResult, Model model, SessionStatus status, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		
		comBbsVO.setRgstId(UserSession.getUserId(request));// 작성자 ID
		comBbsVO.setPrntsNo("0");
		comBbsVO.setReplyLoc("1");
		comBbsVO.setComCheck(0);
		// 작성글 등록
		comBbsService.insertComBbs(comBbsVO, MultipartFileUtil.getFileList(request, "sample"), UserSession.getUserId(request));
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
	public String retrieveLibFreeNoticeView(@ModelAttribute("comBbsVO") ComBbsVO comBbsVO, Model model, HttpServletRequest request) throws Exception {
		
		
		comBbsVO.setComBbsSeq(Integer.parseInt(StringUtil.nvl(comBbsVO.getSelectedId(), "0")));
		
		ComBbsVO resultVO = null;
		
		resultVO = comBbsService.retrieveComBbsView(comBbsVO);
		
		// 첨부파일상세 목록을 조회한다.
		FileVO fileVO = new FileVO();
		//fileVO.setComFileSeq(Integer.parseInt(StringUtil.nvl(resultVO.getComFileSeq(), "0")));
		if (Integer.toString(resultVO.getComFileSeq()) != null
				|| Integer.toString(resultVO.getComFileSeq()) != "") {
			fileVO.setComFileSeq(resultVO.getComFileSeq());
		} else {
			fileVO.setComFileSeq(0);
		}
		List<FileVO> fileList = fileService.retrieveComFileDtlList(fileVO);
		model.addAttribute("fileList", fileList);
		
		resultVO.setNttContent(resultVO.getNttContent().replaceAll("\r\n", "<br/>"));

		System.out.println("[글 조회] comBbsVO = " + comBbsVO);
		System.out.println("[글 조회] resultVO = " + resultVO);
		int cnt = comBbsService.commentCount(resultVO);
		System.out.println("[글 조회] 댓글리스트 cnt = " + cnt);
		resultVO.setComCheck(cnt);
		System.out.println("[글 조회2] comBbsVO = " + comBbsVO);
		System.out.println("[글 조회2] resultVO = " + resultVO);
		model.addAttribute("cnt", cnt);
		
		int comBbsCount = comBbsService.comBbsCount(resultVO);
		System.out.println("[글 조회] 답글리스트 comBbsCount = " + comBbsCount);
		model.addAttribute("comBbsCount", comBbsCount);
		
		int comFileCount = comBbsService.comFileCount(resultVO);
		System.out.println("[글 조회] 첨부파일리스트 comFileCount = " + comFileCount);
		resultVO.setComFileCnt(comFileCount);
		
		getId = resultVO.getComBbsSeq();
		
		String userId = UserSession.getUserId(request);
		System.out.println("[글 조회] 세션 아이디: " + userId);
		model.addAttribute("userId", userId);
		
		comBbsService.increaseViewCnt(resultVO);
		resultVO.setViewCnt(resultVO.getViewCnt() + 1);
		
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
	public String updateComBbsModi(@ModelAttribute("comBbsVO") ComBbsVO comBbsVO, Model model, HttpServletRequest request) throws Exception {
		
		comBbsVO.setComBbsSeq(Integer.parseInt(StringUtil.nvl(comBbsVO.getSelectedId(), "0")));
		ComBbsVO resultVO = null;
		resultVO = comBbsService.retrieveComBbsView(comBbsVO);
		
		// 첨부파일상세 목록을 조회한다.
		FileVO fileVO = new FileVO();
		//fileVO.setComFileSeq(Integer.parseInt(StringUtil.nvl(resultVO.getComFileSeq(), "0")));
		if (Integer.toString(resultVO.getComFileSeq()) != null
				|| Integer.toString(resultVO.getComFileSeq()) != "") {
			fileVO.setComFileSeq(resultVO.getComFileSeq());
		} else {
			fileVO.setComFileSeq(0);
		}
		List<FileVO> fileList = fileService.retrieveComFileDtlList(fileVO);
		model.addAttribute("fileList", fileList);
		
		String userId = UserSession.getUserId(request);
		System.out.println("[글 수정화면 조회] 세션 아이디: " + userId);
		model.addAttribute("userId", userId);
		
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
			BindingResult bindingResult, Model model, SessionStatus status, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		
		comBbsVO.setUpdId(UserSession.getUserId(request));// 작성자 ID
		comBbsVO.setComBbsSeq(Integer.parseInt(StringUtil.nvl(comBbsVO.getSelectedId(), "0")));
		
		ComBbsVO resultVO = comBbsService.retrieveComBbsView(comBbsVO);

		// 첨부파일 수정
		int comFileSeq = MultipartFileUtil.updateComFileDtl2(fileService, MultipartFileUtil.getFileList(request, "sample"), UserSession.getUserId(request), resultVO, true);

		if( comFileSeq != 0) 
			comBbsVO.setComFileSeq(comFileSeq);
		else
			comBbsVO.setComFileSeq(0);

		
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
		
		int comFileSeq = comBbsVO.getComFileSeq();
		
		MultipartFileUtil.deleteComFileDtl(fileService, comFileSeq, true);
		
		comBbsService.deleteComBbs(comBbsVO);
		
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
	public String insertComBbsReply(@ModelAttribute("comBbsVO") ComBbsVO comBbsVO, Model model, HttpServletRequest request) throws Exception {
		
		comBbsVO.setComBbsSeq(Integer.parseInt(StringUtil.nvl(comBbsVO.getSelectedId(), "0")));
		ComBbsVO resultVO = null;
		resultVO = comBbsService.retrieveComBbsView(comBbsVO);
		ComBbsVO comBbsVO1 = new ComBbsVO();
		comBbsVO1.setNttTitle("답변 : " + resultVO.getNttTitle());
		
		comBbsVO1.setRgstId(UserSession.getUserId(request));
		
		//comBbsVO1.setRgstId(resultVO.getRgstId());
		comBbsVO1.setNttContent(" " + "\r\n\r\n================ 원본메시지 ===============\r\n\r\n"+StringUtil.nvl(resultVO.getNttContent(), "")+"\n\n==========================================\n\n");
		comBbsVO1.setPrntsNo(resultVO.getPrntsNo());
		comBbsVO1.setReplyLoc(resultVO.getReplyLoc());
		comBbsVO1.setComBbsSeq(resultVO.getComBbsSeq());
		
		String userId = UserSession.getUserId(request);
		System.out.println("[글 답글화면 조회] 세션 아이디: " + userId);
		model.addAttribute("userId", userId);
		
		//comBbsService.insertComBbs(comBbsVO, MultipartFileUtil.getFileList(request, "sample"), UserSession.getUserId(request));
		//insertComBbs로 해도 될지, 아니면 insertComBbsReply를 만들어야 할지...
		model.addAttribute("trxComBbsVO", comBbsVO1); // SessionAttributes명과 일치
		
		return "/comBbs/comBbsReply";
	}
	
	/**
	 * 답글을 등록한다.
	 * @param comBbsVO - 조회 조건, 등록 정보가 담긴 VO (ModelAttribute명은 SessionAttributes명과 일치)
	 * @param bindingResult
	 * @param model
	 * @param status
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping("/comBbs/insertComBbsReply1.do")
	public String insertComBbsReply1(@ModelAttribute("trxComBbsVO") ComBbsVO comBbsVO, BindingResult bindingResult, Model model, SessionStatus status, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		
		comBbsVO.setRgstId(UserSession.getUserId(request));// 작성자 ID
		
		comBbsVO.setPrntsNo(String.valueOf(comBbsVO.getComBbsSeq()));
		comBbsVO.setReplyLoc(String.valueOf(Integer.parseInt(comBbsVO.getReplyLoc())+1));
		comBbsVO.setComCheck(0);
		
		// 작성글 등록
		comBbsService.insertComBbs(comBbsVO, MultipartFileUtil.getFileList(request, "sample"),
				UserSession.getUserId(request));
		status.setComplete(); // 세션 객체 삭제
		
		request.setAttribute("msgCd", "info.common.insert");
		return "forward:/comBbs/retrieveComBbsList.do";
	}

	/**
	 * 한줄의견을 등록한다.
	 * 
	 * @param comBbsCommentVO
	 *            - 조회 조건, 등록 정보가 담긴 VO (ModelAttribute명은 SessionAttributes명과
	 *            일치)
	 * @param bindingResult
	 * @param model
	 * @param status
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping("/comBbs/ajaxInsertLibFreeNoticeComment.do")
	@ResponseBody
	public ResponseEntity<String> ajaxInsertLibFreeNoticeComment(
			@ModelAttribute("comBbsVO") ComBbsCommentVO comBbsCommentVO,
			HttpServletRequest request) throws Exception {
		JSONObject jsonObject = new JSONObject();
		
		System.out.println("[댓글 등록] jsp에서 가져온 getId = " + getId);
		comBbsCommentVO.setComBbsSeq(getId);
		
		try {
			// 코맨트 등록
			comBbsCommentVO.setRgstId(UserSession.getUserId(request));// 작성자 ID
			comBbsCommentVO.setWriteId(UserSession.getUserId(request));// 작성자 ID

			if (comBbsCommentVO.getCommentContent().equals("")) {
				jsonObject.put("resultList", "한줄의견을 입력하세요.");
			} else {
				if (comBbsCommentVO.getComBbsCommentSeq() == 0) {
					comBbsService.insertCommentList(comBbsCommentVO);
					jsonObject.put("resultList", "등록되었습니다.");
					
					comBbsService.increaseComCheck(comBbsCommentVO);
					System.out.println("[댓글 등록] 댓글 개수 1 증가");
				}
			}

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
			return new ResponseEntity<String>(jsonObject.toString(),
					responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 한줄의견을 조회한다.
	 * 
	 * @param comBbsCommentVO
	 *            - 조회 조건, 등록 정보가 담긴 VO (ModelAttribute명은 SessionAttributes명과
	 *            일치)
	 * @param bindingResult
	 * @param model
	 * @param status
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping("/comBbs/ajaxLibFreeNoticeCommentList.do")
	@ResponseBody
	public ResponseEntity<String> ajaxLibFreeNoticeCommentList(
			@ModelAttribute("comBbsVO") ComBbsCommentVO comBbsCommentVO,
			HttpServletRequest request) throws Exception {
		JSONObject jsonObject = new JSONObject();
		
		System.out.println("[댓글 조회] jsp에서 가져온 getId = " + getId);
		comBbsCommentVO.setComBbsSeq(getId);
		
		try {
			// 코맨트 등록
			// 페이지 정보 설정
			PaginationInfo paginationInfo = PaginationUtil
					.getPaginationInfo(comBbsCommentVO);
			comBbsCommentVO.setRecordCountPerPage(9999999);
			List<ComBbsCommentVO> commentList = comBbsService
					.retrieveComBbsCommentList(comBbsCommentVO);
			
			List<ComBbsCommentVO> commentListTemp = new ArrayList<ComBbsCommentVO>();
			for (ComBbsCommentVO comBbsCommentVO2 : commentList) {
				comBbsCommentVO2.setCommentContent(StringUtil
						.disableScript(comBbsCommentVO2.getCommentContent()));
				commentListTemp.add(comBbsCommentVO2);
			}
			jsonObject.put("resultList", commentListTemp);

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
			return new ResponseEntity<String>(jsonObject.toString(),
					responseHeaders, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 한줄의견을 삭제한다.
	 * 
	 * @param comBbsCommentVO
	 *            - 조회 조건, 등록 정보가 담긴 VO (ModelAttribute명은 SessionAttributes명과
	 *            일치)
	 * @param bindingResult
	 * @param model
	 * @param status
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping("/comBbs/ajaxDeleteLibFreeNoticeComment.do")
	@ResponseBody
	public ResponseEntity<String> ajaxDeleteLibFreeNoticeComment(
			@ModelAttribute("comBbsVO") ComBbsCommentVO comBbsCommentVO,
			HttpServletRequest request) throws Exception {
		JSONObject jsonObject = new JSONObject();
		
		comBbsCommentVO.setComBbsSeq(getId);
		
		try {
			// 삭제
			System.out.println("[댓글 삭제] comBbsCommentVO: " + comBbsCommentVO);
			System.out.println("[댓글 삭제] 댓글 번호: " + comBbsCommentVO.getComBbsCommentSeq());
			
			comBbsService.deleteLibFreeNoticeComment(comBbsCommentVO);
			
			comBbsService.decreaseComCheck(comBbsCommentVO);
			System.out.println("[댓글 삭제] 댓글 개수 1 감소");
			
			jsonObject.put("resultList", "삭제되었습니다.");

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
			return new ResponseEntity<String>(jsonObject.toString(),
					responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}
