package com.devBoard.comBbs.service;

import java.util.List;

import com.devBoard.comBbs.vo.ComBbsCommentVO;
import com.devBoard.comBbs.vo.ComBbsVO;
import com.devBoard.framework.vo.FileVO;



/**
 * @Class Name : ComBbsService.java
 * @Description : 답변형게시판을 처리하는 Service Class
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
public interface ComBbsService {
	/**
	 * 글 목록을 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 */
	public List<ComBbsVO> retrieveComBbsList(ComBbsVO vo) throws Exception;
	
	/**
	 * 글 상세를 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 상세
	 */
	public ComBbsVO retrieveComBbs(ComBbsVO vo) throws Exception;
	
	/**
	 * 글 총 갯수를 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 */
	public int retrieveComBbsListCount(ComBbsVO vo) throws Exception;
	
	/**
	 * 글을 등록한다.
	 * @param vo - 등록할 정보가 담긴 VO
	 * @param fileList - 등록할 첨부파일 정보가 담긴 fileList
	 * @param userId - 등록자ID
	 * @return 등록 결과
	 * @exception Exception
	 */
	public int insertComBbs(ComBbsVO vo, List<FileVO> fileList, String userId) throws Exception;
	
	/**
	 * 글을 조회한다.
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 조회한 글
	 * @exception Exception
	 */
	public ComBbsVO retrieveComBbsView(ComBbsVO vo) throws Exception;
	
	/**
	 * 글을 수정한다.
	 * @param vo - 수정할 정보가 담긴 VO
	 * @param fileList - 수정할 첨부파일 정보가 담긴 fileList
	 * @param userId - 수정자ID
	 * @return void형
	 * @exception Exception
	 */
	public void updateComBbs(ComBbsVO vo) throws Exception;
	
	/**
	 * 카운터즈가.
	 * @param vo - 수정할 정보가 담긴 VO
	 * @param fileList - 수정할 첨부파일 정보가 담긴 fileList
	 * @param userId - 수정자ID
	 * @return void형
	 * @exception Exception
	 */
	
	public void updateComBbsCnt(ComBbsVO vo) throws Exception;
	
	/**
	 * 코드상세를 삭제한다.
	 * @param vo - 삭제할 정보가 담긴 VO
	 * @return void형
	 */
	public void deleteComBbs(ComBbsVO vo) throws Exception;
	
	/**
	 * 댓글. 목록을 조회
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 */
	public List<ComBbsCommentVO> retrieveComBbsCommentList(ComBbsCommentVO vo) throws Exception;
	
	/**
	 * 댓글. 목록건수를 조회
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 */
	public int retrieveComBbsCommentListCount(ComBbsCommentVO vo) throws Exception;
	
	/**
	 * 댓글.등록
	 * @param vo - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 */
	public int insertCommentList(ComBbsCommentVO vo) throws Exception;
	
	/**
	 * 댓글.삭제.
	 * @param vo - 삭제할 정보가 담긴 VO
	 * @return void형
	 */
	public void deleteLibFreeNoticeComment(ComBbsCommentVO vo) throws Exception;
	
	/**
	 * 댓글의 개수를 구한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int commentCount(ComBbsVO vo) throws Exception;
	
	public int commentCount(ComBbsCommentVO vo) throws Exception;
	
	/**
	 * 답글의 개수를 구한다.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int comBbsCount(ComBbsVO vo) throws Exception;
	
	/**
	 * 댓글의 개수를 증가시킨다.
	 */
	public void increaseComCheck(ComBbsCommentVO vo) throws Exception;
	
	/**
	 * 댓글의 개수를 감소시킨다.
	 */
	public void decreaseComCheck(ComBbsCommentVO vo) throws Exception;
	
	/**
	 * 첨부파일의 개수를 구한다.
	 */
	public int comFileCount(ComBbsVO vo) throws Exception;
	
}
