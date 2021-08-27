package com.devBoard.sample.controller;

import java.io.FileOutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.devBoard.framework.collection.MultiValueMap;
import com.devBoard.framework.exception.ExRuntimeException;
import com.devBoard.framework.util.DateUtil;
import com.devBoard.framework.util.ExcelUtil;
import com.devBoard.framework.util.FileUtil;
import com.devBoard.framework.util.MultipartFileUtil;
import com.devBoard.framework.util.PaginationUtil;
import com.devBoard.framework.util.StringUtil;
import com.devBoard.framework.vo.FileVO;
import com.devBoard.sample.service.EgovSampleService;
import com.devBoard.sample.vo.SampleVO;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * @Class Name : EgovSampleController.java
 * @Description : 샘플게시판을 처리하는 Controller Class
 * @Modification Information  
 * @
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2012.12.11  송제승     최초생성
 * 
 * @author 송제승
 * @since 2012.12.11
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Sinsuldong All right reserved.
 */

@Controller
@SessionAttributes("trxSampleVO")
public class EgovSampleController {
	
	/** EgovSampleService */
	@Resource(name = "sampleService")
	private EgovSampleService sampleService;
	
	/**
	 * 글 목록을 조회한다. (paging)
	 * @param sampleVO - 조회 조건이 담긴 VO
	 * @param model
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping(value="/sample/retrieveSampleList.do")
	public String retrieveSampleList(@ModelAttribute("sampleVO") SampleVO sampleVO, ModelMap model) throws Exception {
		// 페이지 정보 설정
		PaginationInfo paginationInfo = PaginationUtil.getPaginationInfo(sampleVO);
		
		// 글 목록
		List<SampleVO> resultList = sampleService.retrieveSampleList(sampleVO);
		model.addAttribute("resultList", resultList);
		
		// 건수
		paginationInfo.setTotalRecordCount(sampleService.retrieveSampleListCount(sampleVO));
		model.addAttribute("paginationInfo", paginationInfo);
		
		return "/sample/sampleList";
	} 

	/**
	 * 글을 조회한다.
	 * @param sampleVO - 상세 조회 Key, 조회 조건이 담긴 VO
	 * @param model
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping("/sample/retrieveSample.do")
	public String retrieveSample(@ModelAttribute("sampleVO") SampleVO sampleVO, ModelMap model) throws Exception {
		sampleVO.setId(sampleVO.getSelectedId());
		
		SampleVO resultVO = null;
		
		try {
			resultVO = sampleService.retrieveSample(sampleVO);
		} catch(EgovBizException e) {
			resultVO = new SampleVO();
			model.addAttribute("msgCd", e.getMessageKey());
		}
		
		// XSS 방어
		resultVO.setDescription(StringUtil.disableScript(resultVO.getDescription()));
		
		model.addAttribute("trxSampleVO", resultVO);

		return "/sample/sample";
	}
	
	/**
	 * 글 등록 화면을 조회한다.
	 * @param sampleVO - 조회 조건이 담긴 VO
	 * @param model
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping("/sample/insertSampleView.do")
	public String insertSampleView(@ModelAttribute("sampleVO") SampleVO sampleVO, Model model) throws Exception {
		model.addAttribute("trxSampleVO", new SampleVO()); // SessionAttributes명과 일치
		
		return "/sample/sampleRegister";
	}
	
	/**
	 * 글을 등록한다.
	 * @param sampleVO - 조회 조건, 등록 정보가 담긴 VO (ModelAttribute명은 SessionAttributes명과 일치)
	 * @param bindingResult
	 * @param model
	 * @param status
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping("/sample/insertSample.do")
	public String insertSample(@ModelAttribute("trxSampleVO") SampleVO sampleVO,
			BindingResult bindingResult, Model model, SessionStatus status) throws Exception {
		
		sampleService.insertSample(sampleVO);
		status.setComplete(); // 세션 객체 삭제
		
		model.addAttribute("msgCd", "info.common.insert");
		
		return "forward:/sample/retrieveSampleList.do";
	}
	
	/**
	 * 글 수정화면을 조회한다.
	 * @param selectedId - 상세 조회 Key
	 * @param sampleVO - 조회 조건이 담긴 VO
	 * @param model
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping("/sample/updateSampleView.do")
	public String updateSampleView(@ModelAttribute("sampleVO") SampleVO sampleVO, Model model) throws Exception {
		sampleVO.setId(sampleVO.getSelectedId());
		
		SampleVO resultVO = sampleService.retrieveSample(sampleVO);
		
		model.addAttribute("trxSampleVO", resultVO); // SessionAttributes명과 일치
		
		return "/sample/sampleRegister";
	}

	/**
	 * 글을 수정한다.
	 * @param sampleVO - 조회 조건, 수정 정보가 담긴 VO (ModelAttribute명은 SessionAttributes명과 일치)
	 * @param bindingResult
	 * @param model
	 * @param status
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping("/sample/updateSample.do")
	public String updateSample(@ModelAttribute("trxSampleVO") SampleVO sampleVO,
			BindingResult bindingResult, Model model, SessionStatus status) throws Exception {
		
		sampleService.updateSample(sampleVO);
		status.setComplete(); // 세션 객체 삭제
		
		model.addAttribute("msgCd", "info.common.update");
		
		return "forward:/sample/retrieveSampleList.do";
	}
	
	/**
	 * 글을 삭제한다.
	 * @param sampleVO - 조회 조건, 삭제 Key가 담긴 VO (ModelAttribute명은 SessionAttributes명과 일치)
	 * @param status
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping("/sample/deleteSample.do")
	public String deleteSample(@ModelAttribute("trxSampleVO") SampleVO sampleVO, Model model, SessionStatus status) throws Exception {
		sampleService.deleteSample(sampleVO);
		status.setComplete(); // 세션 객체 삭제
		
		model.addAttribute("msgCd", "info.common.delete");
		
		return "forward:/sample/retrieveSampleList.do";
	}
	
	/**
	 * 목록을 엑셀 다운로드 한다.
	 * @param sampleVO - 조회 조건이 담긴 VO
	 * @param model
	 * @return View
	 * @exception Exception
	 */
	@RequestMapping(value="/sample/sampleExcelDown.do")
	public void sampleExcelDown(@ModelAttribute("sampleVO") SampleVO sampleVO, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		sampleVO.setFirstIndex(0);
		sampleVO.setRecordCountPerPage(999999999);
		// 글 목록
		List<SampleVO> resultList = sampleService.retrieveSampleList(sampleVO);
		
		// 중복이 발생하지 않도록 주의
		String excelFileName = "memDesc[" + DateUtil.getToday("yyyyMMddHHmmssSSS") + "].xls";
		
		// Excel 생성 begin
		String excelPath = System.getProperty("uploadpath") + "/temp/" + excelFileName;
		
		HSSFWorkbook workbook = new HSSFWorkbook(); // Workbook
		HSSFSheet sheet = workbook.createSheet("엑셀다운로드샘플"); // Sheet
		ExcelUtil eu = new ExcelUtil(excelPath); // Excel Util
		
		// Cell Style 생성
		HSSFCellStyle headCellStyle = eu.createDefaultHeadCellStyle(workbook);
		HSSFCellStyle defaultDataCellStyle = eu.createDefaultDataCellStyle(workbook);
		HSSFCellStyle numberDataCellStyle = eu.createNumericDataCellStyle(workbook);
		
		// Head Row 생성
		String[] headCellValues = {"NO", "카테고리ID", "카테고리명"
				, "사용여부", "Description", "등록자"};
		eu.createRow(sheet, 0, headCellValues, headCellStyle);
		
		HSSFCellStyle[] cellStyles = {defaultDataCellStyle,defaultDataCellStyle,defaultDataCellStyle
				,defaultDataCellStyle,defaultDataCellStyle,defaultDataCellStyle };
		
		int i = 0;
		
		String wrDt = "" ;
		String openState = "" ;
		for(SampleVO result : resultList){
			++i;
			String[] cellValues = { String.valueOf(i), String.valueOf(result.getId()), result.getName()
					, result.getUseYn(), result.getDescription(), result.getRegUser() };
			
			eu.createRow(workbook, sheet, i, cellValues, cellStyles);
		}
		
		FileOutputStream fos = null;
		
		try {
			// 파일 생성
			fos = new FileOutputStream(excelPath);
			workbook.write(fos);
		} finally {
			// Resource 해제
			if(fos!=null) {
				try { fos.close(); } catch(Exception e) { throw new ExRuntimeException(e); }
			}
		}
		// Excel 생성 end
		
		// Download (보관이 필요없는 파일인 경우 반드시 삭제 플래그를 true로 설정)
		FileUtil.downloadFile("sample.xls", "/temp/" + excelFileName, request, response, true);
	} 
	
}