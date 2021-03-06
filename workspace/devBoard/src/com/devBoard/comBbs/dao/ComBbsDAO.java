package com.devBoard.comBbs.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.devBoard.comBbs.vo.ComBbsCommentVO;
import com.devBoard.comBbs.vo.ComBbsVO;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * @Class Name : ComBbsDAO.java
 * @Description : 답변형게시판을 처리하는 DAO Class
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
 *  Copyright (C) 2014 by Sinsuldong All right reserved.
 */
@Repository("comBbsDAO")
public class ComBbsDAO extends EgovAbstractDAO {
	/**
	 * 글 목록을 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 */
	@SuppressWarnings("unchecked")
	public List<ComBbsVO> selectComBbsList(ComBbsVO vo) {
		return list("comBbsDAO.selectComBbsList", vo);
	}
	
	public ComBbsVO selectComBbsView(ComBbsVO vo) {
		return (ComBbsVO) selectByPk("comBbsDAO.selectComBbsView", vo);
	}
	
	public ComBbsVO retrieveComBbs(ComBbsVO vo) {
		return (ComBbsVO) selectByPk("comBbsDAO.selectComBbsList", vo);
	}
	
	/**
	 * 글 총 갯수를 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 */
	public int selectComBbsListCount(ComBbsVO vo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("comBbsDAO.selectComBbsListCount", vo);
	}
	
	/**
	 * 글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 VO
	 * @return void
	 */
	public int insertComBbs(ComBbsVO vo) {
		return (Integer)insert("comBbsDAO.insertComBbs", vo);
	}
	
	/**
	 * 글을 수정한다.
	 * @param vo - 등록할 정보가 담긴 VO
	 * @return void
	 */
	public void updateComBbs(ComBbsVO vo) {
		update("comBbsDAO.updateComBbs", vo);
	}

	public void updateComBbsCnt(ComBbsVO vo) {
		update("comBbsDAO.updateComBbsCnt", vo);
	}
	
	/**
	 * 글을 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 VO
	 * @return void
	 */
	public void deleteComBbs(ComBbsVO vo) {
		delete("comBbsDAO.deleteComBbs", vo);
	}
	
	/**
	 * 댓글 조회.
	 * @param vo - 등록할 정보가 담긴 VO
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public List<ComBbsCommentVO> retrieveComBbsCommentList(ComBbsCommentVO vo) {
		return list("comBbsDAO.selectComBbsCommentList", vo);
	}
	public int retrieveComBbsCommentListCount(ComBbsCommentVO vo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("comBbsDAO.selectComBbsCommentListCount", vo);
	}
	
	/**
	 * 댓글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 VO
	 * @return void
	 */
	public int insertCommentList(ComBbsCommentVO vo) {
		return (Integer)insert("comBbsDAO.insertCommentList", vo);
	}
	
	/**
	 * 댓글을 삭제한다.
	 * @param vo - 등록할 정보가 담긴 VO
	 * @return void
	 */
	public void deleteLibFreeNoticeComment(ComBbsCommentVO vo) {
		delete("comBbsDAO.deleteComment", vo);
	}
	
	/**
	 * 댓글의 개수를 구한다.
	 */
	public int commentCount(ComBbsVO vo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("comBbsDAO.selectCommentCount", vo);
	}
	
	public int commentCount(ComBbsCommentVO vo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("comBbsDAO.selectCommentCount", vo);
	}
	
	/**
	 * 답글의 개수를 구한다.
	 */
	public int comBbsCount(ComBbsVO vo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("comBbsDAO.selectComBbsCount", vo);
	}
	
	/**
	 * 댓글의 개수를 증가시킨다.
	 */
	public void increaseComCheck (ComBbsCommentVO vo) {
		update("comBbsDAO.increaseComCheck", vo);
	}
	
	/**
	 * 댓글의 개수를 감소시킨다.
	 */
	public void decreaseComCheck (ComBbsCommentVO vo) {
		update("comBbsDAO.decreaseComCheck", vo);
	}
	
	/**
	 * 첨부파일의 개수를 구한다.
	 */
	public int comFileCount (ComBbsVO vo) {
		return (Integer)getSqlMapClientTemplate().queryForObject("comBbsDAO.selectComFileCount", vo);
	}
	
	/**
	 * 조회수를 증가시킨다.
	 */
	public void increaseViewCnt (ComBbsVO vo) {
		update("comBbsDAO.increaseViewCnt", vo);
	}
	
}
