package com.devBoard.sample.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.devBoard.common.service.FileService;
import com.devBoard.framework.util.MultipartFileUtil;
import com.devBoard.framework.vo.FileVO;
import com.devBoard.sample.dao.SampleDAO;
import com.devBoard.sample.service.EgovSampleService;
import com.devBoard.sample.vo.ComCommentVO;
import com.devBoard.sample.vo.SampleVO;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * @Class Name : EgovSampleServiceImpl.java
 * @Description : 샘플게시판을 처리하는 Business Implement Class
 * @Modification Information @ @ 수정일 수정자 수정내용 @ ---------- ---------
 *               ------------------------------- @ 2012.12.11 홍길동 최초생성
 * 
 * @author 송제승
 * @since 2012.12.11
 * @version 1.0
 * @see Copyright (C) by Sinsuldong All right reserved.
 */

@Service("sampleService")
public class EgovSampleServiceImpl extends AbstractServiceImpl implements EgovSampleService {

	/** SampleDAO */
	@Resource(name = "sampleDAO")
	private SampleDAO sampleDAO;

	/** 첨부파일 처리를 위한 service */
	@Resource(name = "fileService")
	private FileService fileService;

	/**
	 * 글을 등록한다.
	 * 
	 * @param vo
	 *            - 등록할 정보가 담긴 VO
	 * @return 등록 결과
	 * @exception Exception
	 */
	public int insertSample(SampleVO vo, List<FileVO> fileList, String userId)
			throws Exception {

		// 첨부파일을 등록한다.
		int comFileSeq = MultipartFileUtil.insertComFileDtl(fileService,
				fileList, userId);

		if (comFileSeq != 0)
			vo.setComFileSeq(comFileSeq);
		else
			vo.setComFileSeq(0);
		
		int id = sampleDAO.insertSample(vo);

		return id;
	}

	/**
	 * 글을 수정한다.
	 * 
	 * @param vo
	 *            - 수정할 정보가 담긴 VO
	 * @return void형
	 */
	public void updateSample(SampleVO vo) {
		sampleDAO.updateSample(vo);
	}

	/**
	 * 글을 삭제한다.
	 * 
	 * @param vo
	 *            - 삭제할 정보가 담긴 VO
	 * @return void형
	 */
	public void deleteSample(SampleVO vo) {
		sampleDAO.deleteSample(vo);
	}

	/**
	 * 글을 조회한다.
	 * 
	 * @param vo
	 *            - 조회할 정보가 담긴 VO
	 * @return 조회한 글
	 * @exception Exception
	 */
	public SampleVO retrieveSample(SampleVO vo) throws Exception {
		SampleVO resultVO = sampleDAO.selectSample(vo);

		if (resultVO == null) {
			throw processException("error.common.retrieve");
		}

		return resultVO;
	}

	/**
	 * 글 목록을 조회한다.
	 * 
	 * @param vo
	 *            - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 */
	public List<SampleVO> retrieveSampleList(SampleVO vo) {
		return sampleDAO.selectSampleList(vo);
	}

	/**
	 * 글 총 갯수를 조회한다.
	 * 
	 * @param vo
	 *            - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 */
	public int retrieveSampleListCount(SampleVO vo) {
		return sampleDAO.selectSampleListCount(vo);
	}
	
	/**
	 * 댓글 목록을 조회한다.
	 */
	@Override
	public List<ComCommentVO> retrieveComCommentList(ComCommentVO vo) throws Exception {
		return sampleDAO.retrieveComCommentList(vo);
	}
	
	/**
	 * 댓글 목록건수를 조회한다.
	 */
	@Override
	public int retrieveComCommentListCount(ComCommentVO vo) throws Exception {
		return sampleDAO.retrieveComCommentListCount(vo);
	}
	
	/**
	 * 댓글을 등록한다.
	 */
	public int insertCommentList(ComCommentVO vo) throws Exception {
		int id = sampleDAO.insertCommentList(vo);
		return id;
	}

	/**
	 * 댓글을 삭제한다.
	 */
	@Override
	public void delteLibFreeNoticeComment(ComCommentVO vo) throws Exception {
		sampleDAO.delteLibFreeNoticeComment(vo);
	}
	
	/**
	 * 댓글의 개수를 구한다.
	 */
	@Override
	public int commentCount(SampleVO vo) throws Exception {
		return sampleDAO.commentCount(vo);
	}
	
	@Override
	public int commentCount(ComCommentVO vo) throws Exception {
		return sampleDAO.commentCount(vo);
	}
	
	
}
