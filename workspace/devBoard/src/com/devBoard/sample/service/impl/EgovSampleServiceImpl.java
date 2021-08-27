package com.devBoard.sample.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.devBoard.sample.dao.SampleDAO;
import com.devBoard.sample.service.EgovSampleService;
import com.devBoard.sample.vo.SampleVO;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * @Class Name : EgovSampleServiceImpl.java
 * @Description : 샘플게시판을 처리하는 Business Implement Class
 * @Modification Information  
 * @
 * @ 수정일      수정자     수정내용
 * @ ----------  ---------  -------------------------------
 * @ 2012.12.11  홍길동     최초생성
 * 
 * @author 송제승
 * @since 2012.12.11
 * @version 1.0
 * @see
 * 
 * Copyright (C) by Sinsuldong All right reserved.
 */

@Service("sampleService")
public class EgovSampleServiceImpl extends AbstractServiceImpl implements EgovSampleService {
	
	/** SampleDAO */
	@Resource(name="sampleDAO")
	private SampleDAO sampleDAO;

	/**
	 * 글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 VO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public int insertSample(SampleVO vo) throws Exception {
		log.debug(vo.toString());
		
		int id = sampleDAO.insertSample(vo);
		
		log.debug(vo.toString());
		
		return id;
	}

	/**
	 * 글을 수정한다.
	 * @param vo - 수정할 정보가 담긴 VO
	 * @return void형
	 */
	public void updateSample(SampleVO vo) {
		sampleDAO.updateSample(vo);
	}
   
	/**
	 * 글을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 VO
	 * @return void형
	 */
	public void deleteSample(SampleVO vo) {
		sampleDAO.deleteSample(vo);
	}

	/**
	 * 글을 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 조회한 글
	 * @exception Exception
	 */
	public SampleVO retrieveSample(SampleVO vo) throws Exception {
		SampleVO resultVO = sampleDAO.selectSample(vo);
		
		if(resultVO == null) {
			throw processException("error.common.retrieve");
		}
		
		return resultVO;
	}

	/**
	 * 글 목록을 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 */
	public List<SampleVO> retrieveSampleList(SampleVO vo) {
		return sampleDAO.selectSampleList(vo);
	}

	/**
	 * 글 총 갯수를 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 */
	public int retrieveSampleListCount(SampleVO vo) {
		return sampleDAO.selectSampleListCount(vo);
	}
	
}
