package com.devBoard.comBbs.dao;

import java.util.List;

import com.devBoard.comBbs.vo.ComBbsVO;

import org.springframework.stereotype.Repository;

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
	
}
