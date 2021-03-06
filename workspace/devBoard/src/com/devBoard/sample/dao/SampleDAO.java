package com.devBoard.sample.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.devBoard.sample.vo.ComCommentVO;
import com.devBoard.sample.vo.SampleVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;


/**
 * @Class Name : SampleDAO.java
 * @Description : 샘플게시판을 처리하는 DAO Class
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

@Repository("sampleDAO")
public class SampleDAO extends EgovAbstractDAO {

	/**
	 * 글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 VO
	 * @return void
	 */
	public int insertSample(SampleVO vo) {
		return (Integer)insert("sampleDAO.insertSample", vo);
	}

	/**
	 * 글을 수정한다.
	 * @param vo - 수정할 정보가 담긴 VO
	 * @return void
	 */
	public void updateSample(SampleVO vo) {
		update("sampleDAO.updateSample", vo);
	}

	/**
	 * 글을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 VO
	 * @return void
	 */
	public void deleteSample(SampleVO vo) {
		delete("sampleDAO.deleteSample", vo);
	}

	/**
	 * 글을 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 조회한 글
	 */
	public SampleVO selectSample(SampleVO vo) {
		return (SampleVO) selectByPk("sampleDAO.selectSample", vo);
	}

	/**
	 * 글 목록을 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 */
	@SuppressWarnings("unchecked")
	public List<SampleVO> selectSampleList(SampleVO vo) {
		return list("sampleDAO.selectSampleList", vo);
	}

	/**
	 * 글 총 갯수를 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 */
	public int selectSampleListCount(SampleVO vo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("sampleDAO.selectSampleListCount", vo);
	}
	
	/**
	 * 댓글 조회.
	 * @param vo - 등록할 정보가 담긴 VO
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public List<ComCommentVO> retrieveComCommentList(ComCommentVO vo) {
		return list("sampleDAO.selectComCommentList", vo);
	}
	public int retrieveComCommentListCount(ComCommentVO vo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("sampleDAO.selectComCommentListCount", vo);
	}
	
	/**
	 * 댓글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 VO
	 * @return void
	 */
	public int insertCommentList(ComCommentVO vo) {
		return (Integer)insert("sampleDAO.insertCommentList", vo);
	}
	
	/**
	 * 댓글을 삭제한다.
	 * @param vo - 등록할 정보가 담긴 VO
	 * @return void
	 */
	public void delteLibFreeNoticeComment(ComCommentVO vo) {
		delete("sampleDAO.deleteComment", vo);
	}
	
	/**
	 * 댓글의 개수를 구한다.
	 */
	public int commentCount(SampleVO vo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("sampleDAO.selectCommentCount", vo);
	}
	
	public int commentCount(ComCommentVO vo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("sampleDAO.selectCommentCount", vo);
	}
}
